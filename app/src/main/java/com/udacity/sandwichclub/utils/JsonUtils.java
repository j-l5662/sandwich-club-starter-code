package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json){
//        Sandwich Constructor FYR
//        Sandwich(String mainName, List<String> alsoKnownAs, String placeOfOrigin,
//                String description, String image, List<String> ingredients)
        Sandwich sandwich = null;
        try{
            JSONObject obj = new JSONObject(json);
            ArrayList<String> alsoKnownAs = new ArrayList<>();
            ArrayList<String> ingredients = new ArrayList<>();
            String mainName = obj.getJSONObject("name").getString("mainName");
            JSONArray akaList =  (obj.getJSONObject("name").getJSONArray("alsoKnownAs"));
            if( akaList != null){
                int length = akaList.length();
                for (int i = 0; i < length; i++){
                    alsoKnownAs.add(akaList.get(i).toString());
                }
            }
            String placeofOrigin = obj.getString("placeOfOrigin");
            String description = obj.getString("description");
            String image = obj.getString("image");
            JSONArray ingredientsList = obj.getJSONArray("ingredients");
            if( ingredientsList != null){
                int length = ingredientsList.length();
                for (int i = 0; i < length; i++){
                    ingredients.add(ingredientsList.get(i).toString());
                }
            }

            sandwich = new Sandwich(mainName,alsoKnownAs,placeofOrigin,description,image,ingredients);
        } catch (JSONException e){
            e.printStackTrace();
        }
       return sandwich;
    }
}
