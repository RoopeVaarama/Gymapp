package com.example.gymapp;

import java.util.ArrayList;

public class PullResourses {
    ArrayList<String> info = new ArrayList<String>();
    ArrayList<String> liike = new ArrayList<String>();

    public PullResourses() {

        this.liike.add("Pull ups/ Chin ups / Lat pull down");
        this.info.add("Do 5 sets of 5 reps. \nIf chin ups or pull ups are too difficult do lat pull down.\n" +
                "When 5x5 pull ups or chin ups feels easy add extra weight.");

        this.liike.add("Bent over row");
        this.info.add("Do 3 sets of 10 reps. \nRest 1-2 minutes between sets.\nYou can do this exercise with barbell or dumbbells.");

        this.liike.add("Lat pulldown");
        this.info.add("Do 3 sets of 10 reps. \nRest 1-2 minutes between sets.");

        this.liike.add("Shrugs");
        this.info.add("Do 3 sets of 10 reps. \nRest 1-2 minutes between sets.\nYou can do this exercise with barbell or dumbbells.");

        this.liike.add("Bicep curl (narrow grip)");
        this.info.add("Do 3 sets of 10 reps. \nRest 1-2 minutes between sets.\nYou can do this exercise with barbell or dumbbells.");

        this.liike.add("Bicep curl (wide grip)");
        this.info.add("Do 3 sets of 10 reps. \nRest 1-2 minutes between sets.\nYou can do this exercise with barbell or dumbbells.");

        this.liike.add("Hammer curl");
        this.info.add("Do 3 sets of 10 reps.\nRest 1-2 minutes between sets.");
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