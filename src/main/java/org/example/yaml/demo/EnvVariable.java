package org.example.yaml.demo;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @description:
 * @author: YueNandi
 * @time: 2022/3/11 2:43 ����
 */
public class EnvVariable {

    public static  <T> T getConfig(File file, Class<T> clazz) throws FileNotFoundException, NoSuchMethodException {
        Constructor constructor = new configConstrutor(clazz);
        TypeDescription platformD = new TypeDescription(platform.class);
        platformD.addPropertyParameters("platform2", platform2.class);
        constructor.addTypeDescription(platformD);
        Yaml yaml = new Yaml(constructor);
        yaml.addImplicitResolver(configConstrutor.ENV_TAG, configConstrutor.ENV_FORMAT, "$");
        FileReader fileReader = new FileReader(file);


        return yaml.loadAs(fileReader, clazz);
    }

    public static void main(String[] args) throws FileNotFoundException, NoSuchMethodException {
        platform yaml = getConfig(new File("/Users/yuenandi/Project/work_demo/src/main/java/org/example/yaml/demo/platform.yaml"), platform.class);
        System.out.println(yaml.getPlatform2().getTest());
    }
}
