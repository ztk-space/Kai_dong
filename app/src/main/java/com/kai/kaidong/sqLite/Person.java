package com.kai.kaidong.sqLite;

public class Person {

    private String name;
    private String pwd;
    private String phone;

    public Person(String name, String pwd, String phone) {
        this.name = name;
        this.pwd = pwd;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
