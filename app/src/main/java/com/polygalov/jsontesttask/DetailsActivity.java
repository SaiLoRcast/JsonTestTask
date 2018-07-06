package com.polygalov.jsontesttask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getIncomingIntent();
    }

    private void getIncomingIntent(){

        if (getIntent().hasExtra("name") && getIntent().hasExtra("description")) {

            String name = getIntent().getStringExtra("name");
            String descriptipn = getIntent().getStringExtra("description");

            setTexts(descriptipn, name);
        }
    }

    private void setTexts(String descriptionText, String firstName){

        TextView name = findViewById(R.id.name);
        name.setText(firstName);

        TextView description = findViewById(R.id.description);
        description.setText(descriptionText);


    }
}
