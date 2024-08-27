package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private StringBuilder input = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        setupButtons();
    }

    private void setupButtons() {
        int[] buttonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.buttonDot, R.id.buttonAdd,
                R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide,
                R.id.buttonEquals, R.id.buttonC
        };

        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(v -> onButtonClick(button));
        }
    }

    private void onButtonClick(Button button) {
        String text = button.getText().toString();

        switch (text) {
            case "C":
                input.setLength(0);
                break;
            case "=":
                calculate();
                break;
            default:
                input.append(text);
                break;
        }

        display.setText(input.toString());
    }

    private void calculate() {
        try {
            String expression = input.toString();
            if (expression.isEmpty()) {
                return;
            }

            double result = evaluateExpression(expression);
            input.setLength(0);
            input.append(result);
        } catch (Exception e) {
            input.setLength(0);
            input.append("Error");
        }
    }

    // Updated evaluator for basic arithmetic expressions
    private double evaluateExpression(String expression) {
        // Tokenize the expression by space
        String[] tokens = expression.split("(?=[-+*/])|(?<=[-+*/])");

        List<Double> numbers = new ArrayList<>();
        List<String> operators = new ArrayList<>();

        // Parse tokens into numbers and operators
        for (String token : tokens) {
            if (token.isEmpty()) continue; // Skip empty tokens

            if (token.matches("[0-9.]+")) {
                numbers.add(Double.parseDouble(token));
            } else if (token.matches("[-+*/]")) {
                operators.add(token);
            }
        }

        // Process multiplication and division first
        int i = 0;
        while (i < operators.size()) {
            String operator = operators.get(i);
            if (operator.equals("*") || operator.equals("/")) {
                double left = numbers.get(i);
                double right = numbers.get(i + 1);

                if (operator.equals("*")) {
                    numbers.set(i, left * right);
                } else if (operator.equals("/")) {
                    if (right == 0) throw new ArithmeticException("Divide by zero");
                    numbers.set(i, left / right);
                }
                numbers.remove(i + 1);
                operators.remove(i);
            } else {
                i++;
            }
        }

        // Process addition and subtraction
        double result = numbers.get(0);
        for (int j = 0; j < operators.size(); j++) {
            String operator = operators.get(j);
            double value = numbers.get(j + 1);

            if (operator.equals("+")) {
                result += value;
            } else if (operator.equals("-")) {
                result -= value;
            }
        }

        return result;
    }
}
