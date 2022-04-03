package com.thesound;

public class listDTO {
    private String num;
    private String name;
    private String view;
    private String key;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public listDTO(String num, String name, String view, String key) {
        this.num = num;
        this.name = name;
        this.view = view;
        this.key=key;
    }
}


