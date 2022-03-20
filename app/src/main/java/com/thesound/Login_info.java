package com.thesound;

import java.util.HashMap;

public class Login_info {
    String uid;
    String year;
    String classroom;
    String number;
    String name;
    String type;
    public Login_info(String uid,String year,String classroom,String number,String name){
        this.uid=uid;
        this.year=year;
        this.classroom=classroom;
        this.number=number;
        this.name=name;
        this.type="stu";
    }
    public HashMap getInfo(){
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("uid",uid);
        map.put("year",year);
        map.put("classroom",classroom);
        map.put("number",number);
        map.put("name",name);
        map.put("type",type);


        return map;
    }
}
