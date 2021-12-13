package com.hfad.workout;

public class Workout {

    // Each workout has a name and description
    private String name;
    private String description;

    public Workout(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Keep the workouts in arrays
    public static final Workout[] workouts = {
            new Workout("The Limb Loosener", "\n5 Handstand Push ups \n10 1 legged squats \n15 pullups"),
            new Workout("Core Agony", "\n 100 pullups \n100 push-ups \n100 situps \n100 squats"),
            new Workout("The wimp special", "5 pull-ups \n 10 push-ups \n 15 Squats"),
            new Workout("Strength and Length", "500 meter run \n 21 x 1.5 pood kettle ball \n21xpull-ups")
    };

    public String getDescription(){
        return description;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return this.name;
    }

    
}
