package org.example.yaml.demo;

import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.env.EnvScalarConstructor;
import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.error.MissingEnvironmentVariableException;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.Tag;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: YueNandi
 * @time: 2022/3/11 3:13 ÏÂÎç
 */
public class configConstrutor extends Constructor{
    public static final Tag ENV_TAG = new Tag("!ENV");
    public static final Pattern ENV_FORMAT = Pattern.compile("^\\$\\{\\s*((?<name>\\w+)((?<separator>:?(-|\\?))(?<value>\\S+)?)?)\\s*\\}$");
    public configConstrutor(Class<? extends Object> theRoot) {
        super(theRoot);
        this.yamlConstructors.put(ENV_TAG, new configConstrutor.ConstructEnv());
    }

    public String apply(String name, String separator, String value, String environment) {
        if (environment != null && !environment.isEmpty()) {
            return environment;
        } else {
            if (separator != null) {
                if (separator.equals("?") && environment == null) {
                    throw new MissingEnvironmentVariableException("Missing mandatory variable " + name + ": " + value);
                }

                if (separator.equals(":?")) {
                    if (environment == null) {
                        throw new MissingEnvironmentVariableException("Missing mandatory variable " + name + ": " + value);
                    }

                    if (environment.isEmpty()) {
                        throw new MissingEnvironmentVariableException("Empty mandatory variable " + name + ": " + value);
                    }
                }

                if (separator.startsWith(":")) {
                    if (environment == null || environment.isEmpty()) {
                        return value;
                    }
                } else if (environment == null) {
                    return value;
                }
            }

            return "";
        }
    }

    public String getEnv(String key) {
        return System.getenv(key);
    }

    private class ConstructEnv extends AbstractConstruct {
        private ConstructEnv() {

        }

        public Object construct(Node node) {
            String val = configConstrutor.this.constructScalar((ScalarNode)node);
            Matcher matcher = configConstrutor.ENV_FORMAT.matcher(val);
            matcher.matches();
            String name = matcher.group("name");
            String value = matcher.group("value");
            String separator = matcher.group("separator");
            return configConstrutor.this.apply(name, separator, value != null ? value : "", configConstrutor.this.getEnv(name));
        }
    }
}
