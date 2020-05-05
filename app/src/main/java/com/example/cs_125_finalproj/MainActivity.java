package com.example.cs_125_finalproj;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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
    }

    int zeroPrime;
    int onePrime;

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

    // PUT YOUR APPID HERE:
    private static String appid = "95KPE7-6HAHWW7VGW";

    private static String answer;

    private void clicked() {
        Button calculate = findViewById(R.id.toCalculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {

            }
        });
    }

    public static void WolframResult(String args) {

        // Use "pi" as the default query, or caller can supply it as the lone command-line argument.
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
            System.out.println("Query URL:");
            System.out.println(engine.toURL(query));
            System.out.println("");

            // This sends the URL to the Wolfram|Alpha server, gets the XML result
            // and parses it into an object hierarchy held by the WAQueryResult object.
            WAQueryResult queryResult = engine.performQuery(query);

            if (queryResult.isError()) {
                System.out.println("Query error");
                System.out.println("  error code: " + queryResult.getErrorCode());
                System.out.println("  error message: " + queryResult.getErrorMessage());
            } else if (!queryResult.isSuccess()) {
                System.out.println("Query was not understood; no results available.");
            } else {
                // Got a result.
                System.out.println("Successful query. Pods follow:\n");
                for (WAPod pod : queryResult.getPods()) {
                    if (!pod.isError() && pod.getTitle().equals("Indefinite integral")) {
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
    }
}

/** The main activity will contain the labels of terms needed for a first or second linear
 * differential equation. There will be some sort of input to type the coefficients associated
 * with each term. There will also be a place to insert the symbol for the independent variable
 * so that if any other letters are types into the inputs, the application recognizes them as
 * constants. We will need to use some sort of Java math library that deals with calculus so
 * that the math for the coding is easier. There will be a calculate button, that will print
 * the solution to the differential equation.*/

public String solve(int zeroPrime, int onePrime) {
    String input = onePrime + "y' + " + zeroPrime + "y = 0";
    if ((onePrime == null) || (zeroPrime == null)) {
        /** do not run */
    }
    WAQueryParameters java.lang.String toWebsiteURL(https://www.wolframalpha.com/calculators/integral-calculator/);
    WAPod for "indefinite integral";
    String answer = WAPlainText getText(differential equation solution);
    return answer;
}

    /** Function for differential equation calculation
     * First create a void method that creates a string "plugging in" the coefficients
     * for the first order differential equation, including the "= 0"
     * Copy the URL for the wolfram alpha integral calculator website
     * (https://www.wolframalpha.com/calculators/integral-calculator/)
     *  Insert the created input string into the pod for the wolfram alpha online
     *  integral calculator
     *  Check to see if both zeroPrime and onePrime exist
     *  If both coefficients exist, use "calculate" button to start the function
     *  Have the online integral calculator run
     *  Retrieve the result from the differential equation solution pod
     *  Create a new string with the retrieved result
     *  Return the newly created answer string
     */
