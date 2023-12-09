package com.example.appdev;

public class MissionVissionItem {
    private String title;
    private String Desc;
    public MissionVissionItem(String title, String d){
        this.title = title;
        this.Desc = d;
    }

    public String getTitle(){
        return title;
    }

    public String getDesc(){
        return Desc;
    }
}
