package com.example.cs_125_finalproj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    int zeroPrime;
    int onePrime;
    int twoPrime;

    public int getZeroPrime() {
        return zeroPrime;
    }

    public void setZeroPrime(int zeroPrime) {
        this.zeroPrime = zeroPrime;
    }

    public int getOnePrime() {
        return onePrime;
    }

    public void setOnePrime(int onePrime) {
        this.onePrime = onePrime;
    }

    public int getTwoPrime() {
        return twoPrime;
    }

    public void setTwoPrime(int twoPrime) {
        this.twoPrime = twoPrime;
    }
}
/** The main activity will contain the labels of terms needed for a first or second linear
 * differential equation. There will be some sort of input to type the coefficients associated
 * with each term. There will also be a place to insert the symbol for the independent variable
 * so that if any other letters are types into the inputs, the application recognizes them as
 * constants. We will need to use some sort of Java math library that deals with calculus so
 * that the math for the coding is easier. There will be a calculate button, that will print
 * the solution to the differential equation.*/
