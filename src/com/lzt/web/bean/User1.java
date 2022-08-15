package com.lzt.web.bean;

/**
 * @program:
 * @ClassName:User1
 * @version:1.0
 * @author: lizetao
 * @create: 2022-08-10-17:56
 **/
public class User1 {
    private int id;
    private String name;

    public User1(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User1() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
