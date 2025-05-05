package com.example.firstandroidapp;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText Weight ,  HeightFt , HeightIn;
Button submitBtn ;
TextView resultText;
LinearLayout main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

//        Handling Window is set
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        Weight = findViewById(R.id.weight);
        HeightFt = findViewById(R.id.heightFt);
        HeightIn = findViewById(R.id.heightIn);
        submitBtn = findViewById(R.id.submitButton);
        resultText = findViewById(R.id.result);
        main = findViewById(R.id.main);

        submitBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String wStr = Weight.getText().toString();
                String ftStr = HeightFt.getText().toString();
                String inStr = HeightIn.getText().toString();
                if (!wStr.isEmpty() && !ftStr.isEmpty() && !inStr.isEmpty()) {
                    try {
                        int weight = Integer.parseInt(wStr);
                        int feet = Integer.parseInt(ftStr);
                        int inches = Integer.parseInt(inStr);

                        int totalInches = feet * 12 + inches;
                        double heightInMeters = totalInches * 0.0254;
                        double bmi = weight / (heightInMeters * heightInMeters);
                        String category ;

                        int bgColor;
                        if(bmi>25){
                            category = "You are overweight";
                            bgColor = Color.parseColor("#FFCDD2"); // Light Red
                        }else if (bmi<18.5){
                            category = "You are underweight";
                            bgColor = Color.parseColor("#BBDEFB"); // Light Blue
                        }else{
                            category = "You are completely fit";
                            bgColor = Color.parseColor("#C8E6C9"); // Light Green
                        }

                        String result = String.format(category+"\nYour BMI is: %.2f", bmi );
                        resultText.setText(result);
                        main.setBackgroundColor(bgColor);
//                        Toast.makeText(getApplicationContext(), category, Toast.LENGTH_LONG).show();
                    } catch (NumberFormatException e) {
                        Toast.makeText(getApplicationContext(), "Please enter valid numbers", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
