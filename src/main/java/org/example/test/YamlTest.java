package org.example.test;

import com.alibaba.fastjson.serializer.StringCodec;
import com.sun.deploy.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: YueNandi
 * @time: 2022/3/23 2:08 ����
 */
public class YamlTest {

    public static void main(String[] args) {
        String file = readAllContent("/Users/yuenandi/Project/work_demo/src/main/java/org/example/test/test.yaml", "utf-8", false);
        System.out.println(file);
    }

    public static String readAllContent(String fileName, String charset, boolean exceptEmpty) {
        BufferedReader bufin = null;
        try {
            bufin = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), charset));
            String lineInfo = null;
            StringBuilder sb = new StringBuilder();
            while ((lineInfo = bufin.readLine()) != null) {
                if (!lineInfo.isEmpty()) {
                    if (exceptEmpty) {
                        //context.xml������ȫ����˫���Ÿ�Ϊ������
                        lineInfo = lineInfo.replaceAll("\"", "'");
                        lineInfo = lineInfo.trim();
                        sb.append(lineInfo);
                    } else {
                        lineInfo = lineInfo.replace("'", "\\\\'").replace("${", "\\${");
                        sb.append(lineInfo + "\\n");
                    }
                }
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        } finally {
            if (bufin != null) {
                try {
                    bufin.close();
                } catch (IOException e) {
                }
                bufin = null;
            }
        }
    }
}
