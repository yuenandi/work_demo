package org.example.test;

import cn.com.agree.afa.svc.javaengine.AppLogger;
import cn.com.agree.afa.svc.javaengine.context.JavaDict;
import cn.com.agree.afa.svc.javaengine.context.JavaList;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: YueNandi
 * @time: 2022/6/13 3:47 下午
 */
public class HarborTest {

    public static void main(String[] args) throws Exception {
        JavaDict user = new JavaDict();
        user.setItem("user", "afa5demo");
        user.setItem("password", "afa5demo123");
        String registry = "https://harbor.k8s";
        CloseableHttpClient httpClient = getRegistryClient();
        System.out.println(getImageInfo0V2(user, registry, httpClient));

    }

    private static String getImageInfo0V2(JavaDict user , String registry, CloseableHttpClient httpClient) throws Exception {
        JavaDict result = new JavaDict();


//		HttpGet repoHttpReq = new HttpGet("https://" + registry + "/v2/_catalog");
        HttpGet repoHttpReq = new HttpGet(registry + "/api/search?q=agree/afa/platform/afa5");
        String encoding = user.getStringItem("user") + ":" + user.getStringItem("password");
        String userEncoding = DatatypeConverter.printBase64Binary(encoding.getBytes("UTF-8"));
        repoHttpReq.addHeader("Authorization","Basic " +userEncoding);
        CloseableHttpResponse repoHttpRsp = httpClient.execute(repoHttpReq);
        String repoListStr = EntityUtils.toString(repoHttpRsp.getEntity());
        if (repoHttpRsp != null) {
            try {
                repoHttpRsp.close();
            } catch (IOException e) {
                AppLogger.error(e);
                throw e;
            }
        }
        JavaDict repoDict = new JavaDict();
        strToDict(repoDict, repoListStr);
        JavaList repositoryList = repoDict.getListItem("repository");
        JavaList templateLists = new JavaList();
        result.put("imageTemplates", templateLists);
        if(repositoryList.size() == 0){
            result.put("status", "failure");
            result.put("reason", "User or passWord is worng OR No images for this user");
        }else{
            JSONArray repoArray = JSONArray.parseArray(repositoryList.toString());
            for(int i=0; i<repoArray.size(); i++){
                String imageName = repoArray.getJSONObject(i).getString("repository_name");
                HttpGet imgGet = new HttpGet(registry + "/api/repositories/" + imageName + "/tags");
                imgGet.addHeader("Authorization","Basic " +userEncoding);
                CloseableHttpResponse imgHttpRsp = httpClient.execute(imgGet);
                String tagListStr = EntityUtils.toString(imgHttpRsp.getEntity());
                if(imgHttpRsp != null){
                    try {
                        imgHttpRsp.close();
                    } catch (IOException e) {
                        AppLogger.error(e);
                        throw e;
                    }
                }
                JavaDict versionDict = new JavaDict();
                List<String> tagList = new ArrayList<String>();
                JSONArray imgArray = JSONArray.parseArray(tagListStr);
                for(int n=0; n<imgArray.size(); n++){
                    tagList.add(imgArray.getJSONObject(n).getString("name"));
                }
                JavaDict templateDict = new JavaDict();
                templateLists.add(templateDict);
                templateDict.setItem("tag", versionDict);
                versionDict.setItem("versions",tagList);
                versionDict.setItem("repository", imageName);
            }
            result.put("status", "success");
        }
        return dictToStr(result);
    }

    public static void strToDict(JavaDict dict, String jsonStr) {
        if (jsonStr == null || jsonStr.equals("")) {
            return ;
        }
        if (dict == null) {
            return ;
        }
        JSONObject jsonObj = (JSONObject) JSON.parseObject(jsonStr);
        JavaDict jsonDict = getJavaDict(jsonObj);
        dict.putAll(jsonDict);

    }
    private static JavaDict getJavaDict(JSONObject jsonObj) {
        JavaDict dict = new JavaDict();

        for (@SuppressWarnings("rawtypes")
                Map.Entry entry : jsonObj.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                dict.setItem(key, null);
                continue;
            }
            if (value.getClass() == JSONArray.class) {
                dict.setItem(key, getJavaList((JSONArray) value));
            } else if (value.getClass() == JSONObject.class) {
                dict.setItem(key, getJavaDict((JSONObject) value));
            } else {
                dict.setItem(key, value);
            }
        }
        return dict;
    }
    private static JavaList getJavaList(JSONArray seq) {
        JavaList list = new JavaList();
        for (int i = 0; i < seq.size(); i++) {
            Object value = seq.get(i);
            if (value == null) {
                list.add(null);
                continue;
            }
            if (value.getClass() == JSONArray.class) {
                list.add(getJavaList((JSONArray) value));
            } else if (value.getClass() == JSONObject.class) {
                list.add(getJavaDict((JSONObject) value));
            } else {
                list.add(value);
            }
        }
        return list;
    }

    public static String dictToStr(JavaDict dict) {
        if (dict == null) {
            return "";
        }
        String jsonStr = JSON.toJSONString(dict,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.DisableCircularReferenceDetect);
        return jsonStr;
    }

    private static CloseableHttpClient getRegistryClient() {
            try {
                // Trust everybody
                X509TrustManager tm = new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
                            throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
                            throws CertificateException {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                };
                SSLContext sslCtx = SSLContext.getInstance("TLS");
                sslCtx.init(null, new TrustManager[] { tm }, null);
                SSLConnectionSocketFactory sslConnectionFactory = new SSLConnectionSocketFactory(sslCtx);
                Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("https", sslConnectionFactory).register("http", new PlainConnectionSocketFactory())
                        .build();
                PoolingHttpClientConnectionManager pcm = new PoolingHttpClientConnectionManager(registry);
                HttpClientBuilder hcb = HttpClientBuilder.create();
                hcb.setConnectionManager(pcm);
                CloseableHttpClient httpClient = hcb.build();
                return httpClient;
            } catch (Exception e) {
                AppLogger.error(e);
                return null;
            }

    }
}
