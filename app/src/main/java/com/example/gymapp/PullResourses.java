package com.example.gymapp;

import java.util.ArrayList;

public class PullResourses {
    ArrayList<String> info = new ArrayList<String>();

    public PullResourses() {

        this.info.add("Informaatio 1. Tässä lukisi ekat infot liikkeistä");
        this.info.add("Informaatio 2. tokat infot");
        this.info.add("Informaatio 3. kolmannet");
        this.info.add("Informaatio 4. kolmannet");
        this.info.add("Informaatio 5. kolmannet");
        this.info.add("Informaatio 5. kolmannet");
        this.info.add("Informaatio 5. kolmannet");

    }

    public String getInfo(int page){
        String tuloste = info.get(page-1);
        return tuloste;
    }
}