package com.abualrub.inclassactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText weightField;
    private EditText heightField;
    private TextView resultView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightField = findViewById(R.id.editTextWeight);
        heightField = findViewById(R.id.editTextHeight);
        resultView = findViewById(R.id.textViewResult);
    }

    public void onClickCalculate(View view) {
        try{
            if(weightField.getText().toString().trim().isEmpty()) throw new IllegalArgumentException("Weight must be entered!");
            if(heightField.getText().toString().trim().isEmpty()) throw new IllegalArgumentException("Height must be entered!");

            double weight = Double.parseDouble(weightField.getText().toString().trim());
            double height = Double.parseDouble(heightField.getText().toString().trim());

            if(weight < 0 || height < 0) throw new IllegalArgumentException("Positive Numbers Only!");

            double result = weight/(height*height);
            if(result >= 25) showResult(result,"Obese","#ff0000");
            else showResult(result,"Healthy","#00ff00");

            closeKeyboard();
        }
        catch(NumberFormatException ex){
            Toast.makeText(this, "Fields must contain numbers only1", Toast.LENGTH_SHORT).show();
            resultView.setVisibility(View.GONE);

        }
        catch(IllegalArgumentException ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            resultView.setVisibility(View.GONE);
        }
        catch(Exception ex){
            Toast.makeText(this, "An Error Occurred :(", Toast.LENGTH_SHORT).show();
            resultView.setVisibility(View.GONE);
        }
    }

    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    private void showResult(double result,String classification,String color){
        double estimate = Math.round(result*100)/100;
        resultView.setText("Your BMI is: "+estimate+"\nClass: "+classification+"!");
        resultView.setTextColor(Color.parseColor(color));
        resultView.setVisibility(View.VISIBLE);
    }
}