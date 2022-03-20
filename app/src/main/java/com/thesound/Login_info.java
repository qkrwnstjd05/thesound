package com.thesound;

import java.util.HashMap;

public class Login_info {
    String year;
    String classroom;
    String number;
    String name;
    String type;
    String stu_sch_name;

    public Login_info(String year,String classroom,String number,String name,String stu_sch_name){
        this.year=year;
        this.classroom=classroom;
        this.number=number;
        this.name=name;
        this.type="stu";
        this.stu_sch_name=stu_sch_name;
    }
    public HashMap getInfo(){
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("year",year);
        map.put("classroom",classroom);
        map.put("number",number);
        map.put("name",name);
        map.put("type",type);
        map.put("school",stu_sch_name);
        return map;
    }
}
