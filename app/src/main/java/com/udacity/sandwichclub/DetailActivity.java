package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        //(String mainName, List<String> alsoKnownAs, String placeOfOrigin, String description, String image, List<String> ingredients)

        TextView sandwichOrigin =  findViewById(R.id.origin_tv);
        TextView description = findViewById(R.id.description_tv);
        TextView ingredients = findViewById(R.id.ingredients_tv);
        TextView alsoKnownAs = findViewById(R.id.also_known_tv);

        sandwichOrigin.setText(sandwich.getPlaceOfOrigin());
        description.setText(sandwich.getDescription());
        ArrayList<String> ingredientsList = (ArrayList<String>)sandwich.getIngredients();
        Log.d("Ing.", ingredientsList.get(0));
        String ingredientsListoutput = outputList(ingredientsList);
        ingredients.setText(ingredientsListoutput);
        ArrayList<String> akaList = (ArrayList<String>)sandwich.getAlsoKnownAs();
//        Log.d("Aka", akaList.get(0));
        String akaListoutput = outputList(akaList);
        alsoKnownAs.setText(akaListoutput);
    }

    private String outputList(ArrayList<String> list){
        String output = "";
        if (list != null){
            Log.d("Size", Integer.toString(list.size()));
            if(list.size() == 0){
                output = "N/A";
            }
            else if(list.size() == 1)
                output = list.get(0);
            else if(list.size() == 2)
                output = list.get(0) + ' ' + list.get(1);
            else{
                for(int i = 0; i < list.size(); i++){
                    if( i+1 == list.size()){
                        output += ("and " + list.get(i));
                    }
                    else{
                        output += list.get(i) + ", ";
                    }
                }
            }
        }
        return output;
    }
}
