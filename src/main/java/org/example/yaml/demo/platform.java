package org.example.yaml.demo;


import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.ScalarNode;

import java.io.Serializable;
import java.util.regex.Matcher;

/**
 * @description:
 * @author: YueNandi
 * @time: 2022/3/11 2:51 ÏÂÎç
 */
public class platform implements Serializable {
    private String test;
    private platform2 platform22;
    private Boolean var;

    public Boolean getVar() {
        return var;
    }

    public void setVar(Boolean var) {
        this.var = var;
    }

    private static final long serialVersionUID = -5805026908403657851L;
    public platform(String test){
        this.test = test;
    }
    public  platform(){

    }

    public platform2 getPlatform2() {
        return platform22;
    }

    public void setPlatform2(platform2 platform22) {
        this.platform22 = platform22;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
