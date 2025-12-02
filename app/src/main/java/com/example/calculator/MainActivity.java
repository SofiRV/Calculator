package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

private TextView input;
private Button buttonClear;
private Button buttonErase;
private Button buttonPerc;
private Button buttonDiv;
private Button button1;
private Button button2;
private Button button3;
private Button buttonMult;
private Button button4;
private Button button5;
private Button button6;
private Button buttonMinus;
private Button button7;
private Button button8;
private Button button9;
private Button buttonPlus;
private Button button0;
private Button button00;
private Button buttonDot;
private Button buttonEqual;
private String inputText = "";
private double memoryValue=0.0;
private Button buttonMc;
private Button buttonMr;
private Button buttonMplus;
private Button buttonMminus;
private Button buttonMs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        input = findViewById(R.id.input);
        buttonMc = findViewById(R.id.button_mc);
        buttonMr = findViewById(R.id.button_mr);
        buttonMplus = findViewById(R.id.button_mplus);
        buttonMminus = findViewById(R.id.button_mminus);
        buttonMs = findViewById(R.id.button_ms);
        buttonClear = findViewById(R.id.button_clear);
        buttonErase = findViewById(R.id.button_erase);
        buttonPerc = findViewById(R.id.button_perc);
        buttonDiv = findViewById(R.id.button_div);
        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
        buttonMult = findViewById(R.id.button_mult);
        button4 = findViewById(R.id.button_4);
        button5 = findViewById(R.id.button_5);
        button6 = findViewById(R.id.button_6);
        buttonMinus = findViewById(R.id.button_minus);
        button7 = findViewById(R.id.button_7);
        button8 = findViewById(R.id.button_8);
        button9 = findViewById(R.id.button_9);
        buttonPlus = findViewById(R.id.button_plus);
        button0 = findViewById(R.id.button_0);
        button00 = findViewById(R.id.button_00);
        buttonDot = findViewById(R.id.button_dot);
        buttonEqual = findViewById(R.id.button_equal);

        button0.setOnClickListener(v ->
            appendNumber("0"));
        button1.setOnClickListener(v ->
            appendNumber("1"));
        button2.setOnClickListener(v ->
            appendNumber("2"));
        button3.setOnClickListener(v ->
            appendNumber("3"));
        button4.setOnClickListener(v ->
            appendNumber("4"));
        button5.setOnClickListener(v ->
            appendNumber("5"));
        button6.setOnClickListener(v ->
            appendNumber("6"));
        button7.setOnClickListener(v ->
            appendNumber("7"));
        button8.setOnClickListener(v ->
            appendNumber("8"));
        button9.setOnClickListener(v ->
            appendNumber("9"));
        button00.setOnClickListener(v ->
            appendNumber("00"));
        buttonPlus.setOnClickListener(v ->
            appendOperator("+"));
        buttonMinus.setOnClickListener(v ->
            appendOperator("-"));
        buttonMult.setOnClickListener(v ->
            appendOperator("*"));
        buttonDiv.setOnClickListener(v ->
            appendOperator("/"));
        buttonEqual.setOnClickListener(v ->
            calculateResult());
        buttonErase.setOnClickListener(v ->{
            if (!inputText.isEmpty()) {
                inputText=inputText.substring(0,inputText.length()-1);
                input.setText(inputText);
            }
        });
        buttonClear.setOnClickListener(v -> {
            inputText = "";
            input.setText(inputText);
        });
        buttonPerc.setOnClickListener(v -> {
            try{
                double currentValue = evaluateExpression(inputText);
                double percentage = currentValue / 100;
                inputText = String.valueOf(percentage);
                input.setText(inputText);
            }catch (Exception e){
                input.setText("Error");
                inputText = "";
            }
        });
        buttonDot.setOnClickListener(v -> {
            String[] tokens = inputText.split(" ");
            String lastToken = tokens[tokens.length - 1];

            if(!lastToken.contains(".")){
                inputText += ".";
                input.setText(inputText);
            }
        });
        buttonMc.setOnClickListener(v -> {
            memoryValue=0.0;
        });
        buttonMr.setOnClickListener(v -> {
            input.setText(String.valueOf(memoryValue));
            inputText = String.valueOf(memoryValue);
        });
        buttonMplus.setOnClickListener(v -> {
            try{
                double currentValue = Double.parseDouble(input.getText().toString());
                memoryValue += currentValue;
                input.setText(String.valueOf(memoryValue));
                inputText = String.valueOf(memoryValue);
            }catch (Exception e){
                input.setText("Error");
                inputText = "";
            }
        });
        buttonMminus.setOnClickListener(v -> {
            try {
                double currentValue = Double.parseDouble(input.getText().toString());
                memoryValue -= currentValue;
                input.setText(String.valueOf(memoryValue));
                inputText = String.valueOf(memoryValue);
            } catch (Exception e) {
                input.setText("Error");
                inputText = "";
            }
        });
        buttonMs.setOnClickListener(v -> {
            try {
                double currentValue = Double.parseDouble(input.getText().toString());
                memoryValue = currentValue;
                input.setText(String.valueOf(memoryValue));
                inputText = String.valueOf(memoryValue);
            } catch (Exception e) {
                input.setText("Error");
                inputText = "";
            }
        });
    }
    private void appendNumber(String number) {
        inputText += number;
        input.setText(inputText);
    }
    private void appendOperator(String operator) {
        inputText += " " + operator + " ";
        input.setText(inputText);
    }
    private void calculateResult() {
        try {
            double result = evaluateExpression(inputText);
            input.setText(String.valueOf(result));
            inputText = String.valueOf(result);
        } catch (Exception e) {
            input.setText("Error");
            inputText = "";
        }
    }
    private double evaluateExpression(String expression) {
        String[] tokens = expression.split(" ");

        if (tokens.length == 0) return 0;

        double result = Double.parseDouble(tokens[0]);

        for (int i = 1; i < tokens.length; i += 2) {
            String op = tokens[i];
            double next = Double.parseDouble(tokens[i + 1]);

            switch (op) {
                case "+":
                    result += next;
                    break;
                case "-":
                    result -= next;
                    break;
                case "*":
                    result *= next;
                    break;
                case "/":
                    if (next != 0) result /= next;
                    else return 0; // Manejo de división entre 0
                    break;
            }
        }
        return result;
    }
}