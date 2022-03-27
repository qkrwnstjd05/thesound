package com.thesound;

public class listDTO {
    private String num;
    private String name;
    private String view;

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

    public listDTO(String num, String name, String view) {
        this.num = num;
        this.name = name;
        this.view = view;
    }
}


