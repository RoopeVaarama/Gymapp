package com.example.gymapp;

import java.util.ArrayList;

public class PushResourses {

    ArrayList<String> info = new ArrayList<String>();
    ArrayList<String> liike = new ArrayList<String>();

    public PushResourses() {

        this.liike.add("Bench press");
        this.info.add("Do 5 sets of 8 reps or 3 sets of 10 reps.\n Rest 2-3 minutes between each set.\n You can use dumbbells or barbell for this exercise.");

        this.liike.add("Incline bench press");
        this.info.add("Do 3 sets of 10 reps.\n Rest 1-2 minutes between sets.\n You can use barbell or dumbbells for this exercise.");

        this.liike.add("Overhead press");
        this.info.add("Do 3 sets of 10 reps.\n Rest 1-2 minutes between sets.\n You can use barbell or dumbbells for this exercise.\n You can do this exercise while standing or seated.");

        this.liike.add("French bench press");
        this.info.add("Do 3 sets of 10 reps.\n Rest 1-2 minutes between sets.\n You can use barbell or dumbbells for this exercise.");

        this.liike.add("Tricep pushdown elbow behind back");
        this.info.add("Do 3 sets of 10 reps.\n Rest 1-2 minutes between sets. Keep your elbows behind your beck trough the exercise.\n Then its more effective.");

        this.liike.add("Tricep pushdown");
        this.info.add("Do 3 sets of 10 reps.\n Rest 1-2 minutes between sets.");

        this.liike.add("Lateral raises");
        this.info.add("Do 3 sets of 10 reps.\n Rest 1-2 minutes between sets.");

        this.liike.add("Face pulls");
        this.info.add("Do 3 sets of 10 reps.\n Rest 1-2 minutes between sets.");

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
