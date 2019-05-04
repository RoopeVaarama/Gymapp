package com.example.gymapp;

import java.util.ArrayList;

public class PushResourses {

        ArrayList<String> info = new ArrayList<String>();

    public PushResourses() {

        this.info.add("Informaatio 1. Tässä lukisi ekat infot liikkeistä");
        this.info.add("Informaatio 2. tokat infot");
        this.info.add("Informaatio 3. kolmannet");
        this.info.add("Informaatio 4. kolmannet");
        this.info.add("Informaatio 5. kolmannet");

    }

    public String getInfo(int page){
        String tuloste = info.get(page);
        return tuloste;
    }
}
