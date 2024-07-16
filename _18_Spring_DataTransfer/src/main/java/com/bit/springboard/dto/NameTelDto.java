package com.bit.springboard.dto;

public class NameTelDto {
    private String name;
    private String tel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("setName 메소드 실행");
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        System.out.println("setTel 메소드 실행");
        this.tel = tel;
    }
}
