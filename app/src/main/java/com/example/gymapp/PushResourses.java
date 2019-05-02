package com.example.gymapp;

import java.util.ArrayList;

public class PushResourses {

    ArrayList<String> info = new ArrayList<String>();

    public PushResourses() {
        info.add("Informaatio 1. Tässä lukisi ekat infot liikkeistä");
        info.add("Informaatio 2. tokat infot");
        info.add("Informaatio 3. kolmannet");
    }

    public String getInfo(int page){
        return info.get(page-1);
    }
}
