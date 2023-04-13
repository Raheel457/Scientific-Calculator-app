package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    String input = "";
    String reserverd = "";
    String currentOperation = "";

    public void onTap(View view){
        Button btnNumber = (Button) view;
        ImageView clear1 = findViewById(R.id.clearOne);
        TextView output = findViewById(R.id.output);
        float num = 0;

        input += (String) btnNumber.getText();
        num = Float.parseFloat(input);
        if (num == 0.0f){
            input = "";
            return;
        }
        clear1.setImageResource(R.drawable.baseline_arrow_forward_24);
        output.setText(input);
    }

    public void clearOne(View view){
        ImageView clear1 = findViewById(R.id.clearOne);
        TextView output = findViewById(R.id.output);
        if (((String) output.getText()).length() == 1){
                output.setText("0");
                clear1.setImageResource(R.drawable.baseline_arrow_forward_24_light);
                input = "";
                return;
        }
        input = input.substring(0,input.length()-1);
        output.setText(input);
        clear1.setTranslationX(20);
        clear1.animate().translationX(-20).setDuration(800);
    }
    public void binOperator(View view){
        Button operationBtn = (Button) view;
        if (!Objects.equals(currentOperation, "")){
            Button toChange = (Button) getBtn(currentOperation);
            toChange.setEnabled(true);
            currentOperation = String.valueOf(operationBtn.getText());
            operationBtn.setEnabled(false);
            return;
        }

        currentOperation = String.valueOf(operationBtn.getText());
        reserverd = Objects.equals(input, "") ?"0":input;
        input = "";
        operationBtn.setEnabled(false);
    }

    public void uniOperator(View view){
        Button currentBtn = (Button) view;
        TextView output = findViewById(R.id.output);
        String out = "";
        float result = 0;
        float toCalculate = 0;
        String operation ="";
        if (input == ""){
            toCalculate = 0;
        }
        else{
            toCalculate = Float.parseFloat(input);
        }
        operation = (String) currentBtn.getText();

        switch (operation){
            case "Ceiling":
                result = (float) Math.ceil(toCalculate);
                break;
            case "Floor":
                result = (float) Math.floor(toCalculate);
                break;
            case "Round":
                result = (float) Math.round(toCalculate);
                break;
            case "Sin":
                result = (float) Math.sin(toCalculate);
                break;
            case "Cos":
                result = (float) Math.cos(toCalculate);
                break;
            case "Tan":
                result = (float) Math.tan(toCalculate);
                break;
            case "Log":
                result = (float) Math.log(toCalculate);
                break;
            case "Log 10":
                result = (float) Math.log10(toCalculate);
                break;
            default:
                break;
        }
        if (result == (int) result){
            out = String.valueOf((int) result);
        }
        else{
            out = String.valueOf(result);
        }
        output.setText(out);
        input = out;
    }
    public void clear(View view){
        TextView output = findViewById(R.id.output);
        ImageView clear1 = findViewById(R.id.clearOne);
        input = "";
        reserverd = "";
        clear1.setImageResource(R.drawable.baseline_arrow_forward_24_light);
        if (currentOperation != ""){
            Button toChange = null;
            toChange = (Button) getBtn(currentOperation);
            toChange.setEnabled(true);
        }
        currentOperation = "";
        output.setText("0");
    }
    public void equals(View view){
        if (reserverd == "") {
            Toast.makeText(getApplicationContext(), "Please, Enter Something to Calculate",
                    Toast.LENGTH_SHORT).show();
            return;
        }else if(reserverd != "" && input == ""){
            Toast.makeText(getApplicationContext(), "Please, Enter second Number",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        TextView output = findViewById(R.id.output);
        float inp1 = Float.parseFloat(reserverd);
        float inp2 = Float.parseFloat(input);
        Button toChange = null;
        float result = 0f;
        String out = "";
        switch (currentOperation){
            case "+":
                result = inp1+inp2;
                break;
            case "-":
                result = inp1-inp2;
                break;
            case "X":
                result = inp1*inp2;
                break;
            case "/":
                result = inp1/inp2;
                break;
            case "%":
                result = inp1%inp2;
                break;
            case "Power":
                result = (float) Math.pow(inp1,inp2);
                break;
            case "Exponent":
                result = (float) Math.pow(inp1,inp2);
                break;
            default:
                break;
        }
        if (result == (int) result){
            out = String.valueOf((int) result);
        }
        else{
            out = String.valueOf(result);
        }
            output.setText(out);



        toChange = (Button) getBtn(currentOperation);
        toChange.setEnabled(true);
        currentOperation = "";
        reserverd = "";
        input = out;

    }

//    public void
    public View getBtn(String sign){
        Button toChange = null;
        switch (sign)
        {
            case "+":
                toChange = findViewById(R.id.btnPlus);
                break;
            case "-":
                toChange = findViewById(R.id.btnSub);
                break;
            case "/":
                toChange = findViewById(R.id.btnDivide);
                break;
            case "X":
                toChange = findViewById(R.id.btnMul);
                break;
            case "%":
                toChange = findViewById(R.id.btnRem);
                break;
            case "Power":
                toChange = findViewById(R.id.power);
                break;
            case "Exponent":
                toChange = findViewById(R.id.expo);
                break;
            default:
                break;
        }
        return  toChange;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}