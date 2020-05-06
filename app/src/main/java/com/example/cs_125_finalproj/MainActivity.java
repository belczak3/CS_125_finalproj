package com.example.cs_125_finalproj;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Connection().execute();

    }
    private class Connection extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... arg0) {
            final Button calculate = findViewById(R.id.toCalculate);
            calculate.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    TextInputEditText firstInput = findViewById(R.id.firstInput);
                    String firstCoef = firstInput.getText().toString();
                    TextInputEditText secondInput = findViewById(R.id.secondInput);
                    String secondCoef = secondInput.getText().toString();
                    TextInputEditText indVarInput = findViewById(R.id.indVariable);
                    String indVariable = indVarInput.getText().toString();
                    String toGive = firstCoef + " * dy/d" + indVariable + " + " + secondCoef + " * y = 0";
                    String result = WolframResult(toGive);
                    TextView diffEq = findViewById(R.id.answer);
                    diffEq.setText(result);
                    if (indVariable.length() != 1) {
                        String error = "too many characters";
                        diffEq.setText(error);
                    }
                }
            });
            return null;
        }
    }


    // PUT YOUR APPID HERE:
    private static String appid = "95KPE7-Q2HH5795R9";

    public static String WolframResult(String args) {

        // Use "pi" as the default query, or caller can supply it as the lone command-line argument.
        String answer = "Didn't work";
        String input = args;

        // The WAEngine is a factory for creating WAQuery objects,
        // and it also used to perform those queries. You can set properties of
        // the WAEngine (such as the desired API output format types) that will
        // be inherited by all WAQuery objects created from it. Most applications
        // will only need to crete one WAEngine object, which is used throughout
        // the life of the application.
        WAEngine engine = new WAEngine();

        // These properties will be set in all the WAQuery objects created from this WAEngine.
        engine.setAppID(appid);
        engine.addFormat("plaintext");

        // Create the query.
        WAQuery query = engine.createQuery();

        // Set properties of the query.
        query.setInput(input);

        try {
            // For educational purposes, print out the URL we are about to send:
            Log.v("v", "Query URL:");
            System.out.println(engine.toURL(query));

            // This sends the URL to the Wolfram|Alpha server, gets the XML result
            // and parses it into an object hierarchy held by the WAQueryResult object.
            WAQueryResult queryResult = engine.performQuery(query);
            if (queryResult.isError()) {
                Log.v("v", "Query error");
                System.out.println("Query error");
                System.out.println("  error code: " + queryResult.getErrorCode());
                System.out.println("  error message: " + queryResult.getErrorMessage());
            } else if (!queryResult.isSuccess()) {
                Log.v("v","Query was not understood");
            } else {
                // Got a result.
                Log.v("v","Successful query.");
                for (WAPod pod : queryResult.getPods()) {
                    if (pod.getTitle().equals("Differential equation solution")) {
                        System.out.println(pod.getTitle());
                        System.out.println("------------");
                        for (WASubpod subpod : pod.getSubpods()) {
                            for (Object element : subpod.getContents()) {
                                if (element instanceof WAPlainText) {
                                    System.out.println(((WAPlainText) element).getText());
                                    answer = ((WAPlainText) element).getText();
                                }
                            }
                        }
                    }
                }
                // We ignored many other types of Wolfram|Alpha output, such as warnings, assumptions, etc.
                // These can be obtained by methods of WAQueryResult or objects deeper in the hierarchy.
            }
        } catch (WAException e) {
            e.printStackTrace();
        }
        return answer;
    }
}

/** The main activity will contain the labels of terms needed for a first or second linear
 * differential equation. There will be some sort of input to type the coefficients associated
 * with each term. There will also be a place to insert the symbol for the independent variable
 * so that if any other letters are types into the inputs, the application recognizes them as
 * constants. We will need to use some sort of Java math library that deals with calculus so
 * that the math for the coding is easier. There will be a calculate button, that will print
 * the solution to the differential equation.*/
