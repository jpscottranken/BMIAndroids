package edu.ranken.jpscott.bmi02;

/*
        Body Mass Index (BMI) is a person’s weight in pounds
        divided by the square of height in inches * 703.

        A high BMI can be an indicator of high body fatness.
        BMI can be used to screen for weight categories that
        may lead to health problems but it is not diagnostic
        of the body fatness or health of an individual.

        Commonly accepted BMI ranges are:
		============================================================
		Underweight: 						BMI < 18.50
		Optimal weight: 					BMI >= 18.50 and < 25.00
		Overweight: 						BMI >= 25.00 and < 30.00
		Obese: 								BMI >= 30.00
 */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    //  Program Constants
    final int MINHEIGHT     =  12;
    final int MAXHEIGHT     =  96;
    final int MINWEIGHT     =   1;
    final int MAXWEIGHT     = 777;
    final String OORHEIGHT  =   "Height Inputted Out Of Range.\nHeight Must Be Between " +
                                MINHEIGHT + " and " + MAXHEIGHT;
    final String OORWEIGHT  =   "Weight Inputted Out Of Range.\nWeight Must Be Between " +
                                MINWEIGHT + " and " + MAXWEIGHT;
    final double MINOPTIMAL = 18.5;
    final double MINOVER    = 25.0;
    final double MINOBESE   = 30.0;

    //  Program Widget Variables
    EditText etHeight;
    EditText etWeight;
    Button   btnCalculate;
    Button   btnClear;
    Button   btnIndStats;
    Button   btnGrpStats;
    DecimalFormat bmiFormat = new DecimalFormat("##0.00");

    //  Program Non-Widget Variables
    int totalUnderweight    = 0;
    int totalOptimalweight  = 0;
    int totalOverweight     = 0;
    int totalObese          = 0;
    int height              = 0;
    int weight              = 0;
    double bmi              = 0.0;
    String bmiStatus        = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  Set references to widgets
        etHeight        = findViewById(R.id.etHeight);
        etWeight        = findViewById(R.id.etWeight);
        btnCalculate    = findViewById(R.id.btnCalculate);
        btnClear        = findViewById(R.id.btnClear);
        btnIndStats     = findViewById(R.id.btnIndStats);
        btnGrpStats     = findViewById(R.id.btnGrpStats);
    }

    public void calculateBMI(View view)
    {
        boolean keepGoing = validateHeight();

        if (keepGoing)
        {
            keepGoing = validateWeight();
        }

        if (keepGoing)
        {
            bmi       = doBMICalculation();
            bmiStatus = calculateBMIStatus();
            showIndividualStats();
        }
    }

    public boolean validateHeight()
    {
        try
        {
            //  Attempt to read value in from etHeight
            height = Integer.parseInt(etHeight.getText().toString());

            //  Perform range check on height
            if ((height < MINHEIGHT) || (height > MAXHEIGHT))
            {
                height = 0;
                etHeight.setText("");
                etHeight.requestFocus();
                throw new NumberFormatException();
            }

            return true;            //  height was within range
        }
        catch(NumberFormatException nfe)
        {
            Toast toast = Toast.makeText(this, OORHEIGHT, Toast.LENGTH_LONG);
            toast.show();

            return false;
        }
    }

    public boolean validateWeight()
    {
        try
        {
            //  Attempt to read value in from etWeight
            weight = Integer.parseInt(etWeight.getText().toString());

            //  Perform range check on weight
            if ((weight < MINWEIGHT) || (weight > MAXWEIGHT))
            {
                weight = 0;
                etWeight.setText("");
                etWeight.requestFocus();
                throw new NumberFormatException();
            }

            return true;            //  height was within range
        }
        catch(NumberFormatException nfe)
        {
            Toast toast = Toast.makeText(this, OORWEIGHT, Toast.LENGTH_LONG);
            toast.show();

            return false;
        }
    }

    public double doBMICalculation()
    {
        return (703.0 * weight / (Math.pow(height, 2)));
    }

    public String calculateBMIStatus()
    {
        String s;

        if (bmi < MINOPTIMAL)       //  BMI is < 18.5
        {
            s = "Underweight";
            ++totalUnderweight;
        }
        else if (bmi < MINOVER)
        {
            s = "Optimal Weight";
            ++totalOptimalweight;
        }
        else if (bmi < MINOBESE)
        {
            s = "Overweight";
            ++totalOverweight;
        }
        else
        {
            s = "Obese";
            ++totalObese;
        }

        return s;
    }

    public void clear(View view)
    {
    //    String str  = "Total Number Underweights: " + String.valueOf(totalUnderweight);
    //   str += "\nTotal Number Optimalweights: " + String.valueOf(totalOptimalweight);
    //   str += "\nTotal Number Overweights: " + String.valueOf(totalOverweight);
    //   str += "\nTotal Number Obese: " + String.valueOf(totalObese);
    //    Toast toast = Toast.makeText(this, str, Toast.LENGTH_LONG);
    //    toast.show();
        etHeight.setText("");
        etWeight.setText("");
        etHeight.requestFocus();
    }

    public void showIndividualStats()
    {
        String s  = "Inputted Height: "   + height;
               s += "\nInputted Weight: " + weight;
               s += "\nCalculated BMI: "  + bmiFormat.format(bmi);
               s += "\nYour BMI Status: " + bmiStatus;

        Toast toast = Toast.makeText(this, s, Toast.LENGTH_LONG);
        toast.show();
    }

    public void showIndividualStatsActivity(View view)
    {
        //  Create a bundle object
        Bundle extras = new Bundle();
        extras.putInt("height", height);
        extras.putInt("weight", weight);
        extras.putDouble("bmi", bmi);
        extras.putString("bmiStatus", bmiStatus);

        //  Create and initialize intent
        Intent intent = new Intent(getApplicationContext(),
                                   IndividualStatsActivity.class);

        //  Attach the bundle to the intent
        intent.putExtras(extras);

        //  Start the activity
        startActivity(intent);
    }

    public void showGroupStatsActivity(View view)
    {
        //  Create a bundle object
        Bundle extras2 = new Bundle();
        extras2.putInt("totalUnderweight", totalUnderweight);
        extras2.putInt("totalOptimalweight", totalOptimalweight);
        extras2.putInt("totalOverweight", totalOverweight);
        extras2.putInt("totalObese", totalObese);

        //  Create and initialize intent
        Intent intent = new Intent(getApplicationContext(),
                                    GroupStatsActivity.class);

        //  Attach the bundle to the intent
        intent.putExtras(extras2);

        //  Start the activity
        startActivity(intent);
    }

    public void showImage(View view)
    {
        //  Create a bundle object
        Bundle extras = new Bundle();
        extras.putString("bmiStatus", bmiStatus);

        //  Create and initialize intent
        Intent intent = new Intent(getApplicationContext(),
                                    ImageActivity.class);

        //  Attach the bundle to the intent
        intent.putExtras(extras);

        //  Start the activity
        startActivity(intent);
    }
}
