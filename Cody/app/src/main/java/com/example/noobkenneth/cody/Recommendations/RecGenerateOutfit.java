package com.example.noobkenneth.cody.Recommendations;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.noobkenneth.cody.R;
import com.example.noobkenneth.cody.database.RecQueryDB;

import java.util.ArrayList;
import java.util.Random;

public class RecGenerateOutfit {

    String selectedStyle = "Casual";
    ArrayList<Recommendations> generatedOutfits = new ArrayList<>();
    private String LogCatTAG = "RecommendationsLog";

    RecQueryDB recQueryDB = new RecQueryDB();

    private Bitmap[] apparelIDs = new Bitmap[6]; //allow us to keep track of the combination of outfits

    //TODO: get required clothes from database (implement database methods)
    //If there are items in a category, get a random one
    // otherwise, set a transparent image
    // if we can pass a style parameter in the following methods
    // so that it can just be written once at the start, that would be good

    Bitmap top;
    Bitmap bottom;
    Bitmap overalls;
    Bitmap shoes;
    Bitmap bag;
    Bitmap accessories;
    Bitmap accessories2;
    Bitmap transparent = null;

    Random rand = new Random();
    int randInt = rand.nextInt(1);


    public Bitmap[] getApparelIDs() {
        return apparelIDs;
    }

    public ArrayList<Recommendations> getGeneratedOutfit() {
        return generatedOutfits;
    }

    public ArrayList<Recommendations> getAnotherOutfit(){
        RecGenerateOutfit anotherOutfit = new RecGenerateOutfit(selectedStyle);
        return anotherOutfit.getGeneratedOutfit();
    }

    private void generateCasual(){
        Log.i(LogCatTAG,"entered generateCasual");

        if (randInt==0){

            generatedOutfits.add(new Recommendations(top));
            generatedOutfits.add(new Recommendations(bottom));

            apparelIDs[0] = top;
            apparelIDs[1] = bottom;
        }

        if (randInt==1){
            generatedOutfits.add(new Recommendations(overalls));
            apparelIDs[0] = overalls;
            generatedOutfits.add(new Recommendations(transparent));
            apparelIDs[1] = transparent;
        }

        generatedOutfits.add(new Recommendations(shoes));
        generatedOutfits.add(new Recommendations(bag));
        generatedOutfits.add(new Recommendations(accessories));
        generatedOutfits.add(new Recommendations(transparent));

        apparelIDs[2] = shoes;
        apparelIDs[3] = bag;
        apparelIDs[4] = accessories;
        apparelIDs[5] = accessories2;

    }

    public void generateSmartCasual(){
        //TODO
    }

    public void generateFormal(){
        //TODO
    }

    public void generateBusinessFormal(){
        //TODO
    }



    public RecGenerateOutfit(String selectedStyle) {

        this.selectedStyle = selectedStyle;

        top = recQueryDB.queryRandTopFromDB(selectedStyle);
        bottom = recQueryDB.queryRandBottomFromDB(selectedStyle);
        overalls = recQueryDB.queryRandOverallsFromDB(selectedStyle);
        shoes = recQueryDB.queryRandShoesFromDB(selectedStyle);
        bag = recQueryDB.queryRandBagFromDB(selectedStyle);
        accessories = recQueryDB.queryRandAccessoriesFromDB(selectedStyle);
        accessories2 = recQueryDB.queryRandAccessoriesFromDB(selectedStyle);

        Log.i(LogCatTAG,selectedStyle);
        this.selectedStyle = selectedStyle;
        int randInt = rand.nextInt(1);

        if (selectedStyle.equals("Generate an outfit!")){
            Random rand = new Random();
            int randStyle = rand.nextInt(2);
            if(randStyle == 0){generateCasual();}
            if(randStyle == 1){generateFormal();}
        }
        if (selectedStyle.equals("Casual")){
            generateCasual();
        }

        if (selectedStyle.equals("Smart Casual")){
            generateSmartCasual();
        }

        if (selectedStyle.equals("Formal")){
            generateFormal();
        }

        if (selectedStyle.equals("Business Formal")){
            generateBusinessFormal();
        }
    }



    //future implementation: lock certain items and enable re-generation of other apparels
}

