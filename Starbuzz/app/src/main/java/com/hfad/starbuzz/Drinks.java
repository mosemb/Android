package com.hfad.starbuzz;

public class Drinks {
    private String name;
    private String description;
    private int imageResourceId;

    private Drinks(String name, String description, int imageResourceId){
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;

    }

    public static final Drinks[] drinks = {
            new Drinks("Latte", "A couple of expresso milk with milk", R.drawable.latte),
            new Drinks("Capucino", "Expresso, hot milk and steamed milk foam", R.drawable.cappuccino),
            new Drinks("Filter", "Highest quality beans roasted and steamed", R.drawable.filter)
    };

    public String getDescription(){
        return this.description;
    }

    public String getName(){
        return  this.name;
    }

    public int getImageResourceId(){
        return this.imageResourceId;
    }

    public String toString(){
        return this.name;
    }

}
