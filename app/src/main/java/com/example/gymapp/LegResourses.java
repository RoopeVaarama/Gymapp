package com.example.gymapp;

import java.util.ArrayList;

public class LegResourses {
    ArrayList<String> info = new ArrayList<String>();
    ArrayList<String> liike = new ArrayList<String>();

    public LegResourses() {

        this.liike.add("SKIP");
        this.info.add("Skip");

        this.liike.add("Squat");
        this.info.add("Do 3 sets of 10 reps.\nRest 1-3 minutes between sets.");

        this.liike.add("Deadlift");
        this.info.add("Do 3 sets of 10 reps.\nRest 1-3 minutes between sets.\nYou can do this exercise with barbell or dumbbells.");

        this.liike.add("Lunges");
        this.info.add("Do 3 sets of 10 reps.\nRest 1-2 minutes between sets.\nYou can do this exercise with barbell or dumbbells.");

        this.liike.add("Stiff-legged deadlift");
        this.info.add("Do 3 sets of 10 reps.\nRest 1-2 minutes between sets.\nYou can do this exercise with barbell or dumbbells.");

        this.liike.add("Hip thrust");
        this.info.add("Do 3 sets of 10 reps.\nRest 1-2 minutes between sets.\nYou can do this exercise with barbell or dumbbells.");

        this.liike.add("Calf raises");
        this.info.add("Do 3 sets of 10 reps.\nRest 1-2 minutes between sets.\nYou can do this exercise with barbell or dumbbells.");

    }

    public String getInfo(int page){
        String tuloste = info.get(page);
        return tuloste;
    }
    public String getLiike(int page){
        String tuloste = liike.get(page);
        return tuloste;
    }
}