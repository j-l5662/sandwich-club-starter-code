package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws Exception {
//        Sandwich Constructor
//        (String mainName, List<String> alsoKnownAs, String placeOfOrigin,
//                String description, String image, List<String> ingredients)
        JSONObject obj = new JSONObject(json);


        return null;
    }
}
