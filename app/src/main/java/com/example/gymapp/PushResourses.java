package com.example.gymapp;

import java.util.ArrayList;

public class PushResourses {

    ArrayList<String> info = new ArrayList<String>();
    ArrayList<String> liike = new ArrayList<String>();

    public PushResourses() {

        this.info.add("Informaatio 1. Tässä lukisi ekat infot liikkeistä");
        this.info.add("Informaatio 2. tokat infot");
        this.info.add("Informaatio 3. kolmannet");
        this.info.add("Informaatio 4. kolmannet");
        this.info.add("Informaatio 5. kolmannet");
        this.info.add("Informaatio 5. kolmannet");
        this.info.add("Informaatio 5. kolmannet");
        this.info.add("Informaatio 5. kolmannet");

        this.liike.add("");
        this.liike.add("");
        this.liike.add("");
        this.liike.add("");
        this.liike.add("");

    }

    public String getInfo(int page){
        String tuloste = info.get(page-1);
        return tuloste;
    }

    public String getLiike(int page){
        String tuloste = liike.get(page-1);
        return tuloste;
    }
}
