package org.example.annotation.demo;

import org.example.annotation.demo.annotations.Format;
import org.example.annotation.demo.annotations.Label;
import org.example.annotation.demo.annotations.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
    @Label("姓名")
    String name;
    @Label("出生日期")
    @Format(pattern = "yyyy/MM/dd")
    Date born;
    @Label("分数")
    double score;

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Student(String name, Date born, double score) {
        this.name = name;
        this.born = born;
        this.score = score;
    }


    public static void main(String[] args) throws ParseException, IllegalAccessException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Student ynd = new Student("ynd", sdf.parse("1995-06-11"), 96.9d);
        System.out.println(utils.format(ynd));


        String yndStr = utils.toString(ynd);
        System.out.println(yndStr);
        Student newYnd = (Student) utils.fromString(yndStr);
        System.out.printf("%s [name=%s, born=%s, score=%s]", newYnd.getClass().getSimpleName(),newYnd.getName(),newYnd.getBorn(), newYnd.getScore());


    }
}
