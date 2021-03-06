package com.example.searchprov01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ExtraItemInfo extends AppCompatActivity {

    Button button;

    boolean verification = false;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    int idNumber, serialNumber;
    double weight, thickness, profitRatio;

    EditText idNumberInput;
    EditText weightInput;
    EditText thicknessInput;
    EditText profitInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        button = findViewById(R.id.button7);

        idNumberInput = findViewById(R.id.editTextNumber4);
        weightInput = findViewById(R.id.editTextNumberDecimal2);
        thicknessInput = findViewById(R.id.editTextNumber2);
        profitInput = findViewById(R.id.editTextNumber3);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_item_info);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIdNumber();
                setWeight();
                setThickness();
                setProfitRatio();
                finalizeCreation();
                toInventory();
            }
        });
    }


    private void setIdNumber() {
        try {
            idNumber = Integer.parseInt(idNumberInput.getText().toString().trim());
        } catch (NumberFormatException nfe) {
            idNumberInput.setError("Invalid Input. Must be a Numeric Whole Number");
        }
    }

    private void setWeight() {
        try {
            weight = Double.parseDouble(weightInput.getText().toString().trim());
            if (weight <= 0.00) {
                weightInput.setError("Cannot be less than or equal to 0.00");
            }
        } catch (NumberFormatException nfe) {
            weightInput.setError("Invalid Input. Must be a Numeric Number");
        }
    }

    private void setThickness() {
        try {
            thickness = Double.parseDouble(thicknessInput.getText().toString().trim());
            if (thickness <= 0.00) {
                thicknessInput.setError("Cannot be less than or equal to 0.00");
            }
        } catch (NumberFormatException nfe) {
            thicknessInput.setError("Invalid Input. Must be a Numeric Number");
        }
    }

    private void setProfitRatio() {
        try {
            profitRatio = Double.parseDouble(profitInput.getText().toString().trim());
            if (profitRatio <= 0.00) {
                profitInput.setError("Cannot be less or equal of 0.00");
            }
        } catch (NumberFormatException nfe) {
            profitInput.setError("Invalid Input. Must be a Numeric Number");
        }

    }


    private void finalizeCreation() {
        rootNode = FirebaseDatabase.getInstance();
        serialNumber++;
        reference = rootNode.getReference("Users").child("Items").child(String.valueOf(serialNumber)).child("Private Values");
        PrivateInfo item = new PrivateInfo(idNumber, weight, thickness, profitRatio);
        reference.setValue(item);

    }

    // Make sure this is the correct way to put it into the datebase.

    private void toInventory() {
        startActivity(new Intent(ExtraItemInfo.this, InventoryView.class));
    }

}