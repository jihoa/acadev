package com.asianaidt.springrestful.step01.entity;

import java.util.Locale;

public enum SocialType {
    GOOGLE("google"),
    NAVER("naver");

    private final String ROLE_PREFIX="ROLE_";
    private String name;

    SocialType(String name) {
        this.name = name;
    }

    public String getRoleType() {
        return ROLE_PREFIX + name.toUpperCase(Locale.KOREA);
    }

    public String getValue() {
        return name;
    }

    public boolean isEquals(String auhtority){
        return this.getRoleType().equals(auhtority);
    }
}
