package com.example.searchprov01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddingItem extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        button = findViewById(R.id.button6);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_item);
        toExtra();
    }

    private void toExtra() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddingItem.this, ExtraItemInfo.class));
            }
        });
    }

    // NEED JSON METHOD

}