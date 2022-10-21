package org.example.yaml.demo;

/**
 * @description:
 * @author: YueNandi
 * @time: 2022/3/11 6:18 обнГ
 */
public class platform2 {
    private String test;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private static final long serialVersionUID = -5805026908403657851L;
    public platform2(String test){
        this.test = test;
    }
    public  platform2(){

    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
