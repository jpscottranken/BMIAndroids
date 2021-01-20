package edu.ranken.jpscott.bmiandroidv1;

/*
 *
 * 	    Write an Android program that calculates and displays
 * 	    a person's body mass index (BMI). The BMI is often used
 * 	    to determine whether a person is underweight, of optimal
 * 	    weight, overweight, or obese for his or her height.
 *
 * 	    A person's BMI is calculated with the following formula:
 *
 * 	    bmi = ((weight / (Math.Pow(height, 2))) * 703.0);
 *
 * 	    Where weight is measured in pounds and height is measured
 * 	    in inches. The program should input the height and weight,
 * 	    verify that they are "valid", display a message indicating
 * 	    whether the person underweight, of optimal weight, over-
 * 	    weight, or obese.
 *
 * 	    A person is considered underweight if his or her BMI
 * 	    is < 18.5.
 *
 * 	    A person is considered to be of optimal weight if his
 * 	    or her BMI is >= 18.5 and < 25.
 *
 * 	    A person is considered overweight if his or her BMI
 * 	    is >= 25 and < 30.
 *
 * 	    A person is considered obese if his or her BMI is
 * 	    >= 30.
 *
 * 	    Also, in this program we have added arbitrary values
 * 	    for minimum height, maximum height, minimum weight,
 * 	    and maximum weight.
 *
 * 	    And do the following (to be implemented in Version 2):
 *
 * 	    1.	Validate height >= 12 & <= 96 (MINHEIGHT & MAXHEIGHT).
 *
 * 	    2.  Validate weight >= 1 & <= 777 (MINWEIGHT & MAXWEIGHT).
 *
 * 	    Finally, the program is now designed to NOT ACCEPT
 * 	    non-numeric input for height or weight but not end
 * 	    the program.  Rather, an error message will be given.
 *      Also to be implemented in Version 2.
 *
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //  Declare and initialize global constants
    final int MINHEIGHT     =  12;      //  Minimum height allowed
    final int MAXHEIGHT     =  96;      //  Maximum height allowed
    final int MINWEIGHT     =   1;      //  Minimum weight allowed
    final int MAXWEIGHT     = 777;      //  Maximum weight allowed
    final double MINOPT     =  18.5;    //  Minimum BMI for optimal
    final double MAXOVER    =  25.0;    //  Maximum BMI for overweight
    final double MINOBESE   =  30.0;    //  Minimum BMI for obese
    final String OORHEIGHT  = "Out-Of-Range Height (< 12 or > 96) Inputted!";
    final String OORWEIGHT  = "Out-Of-Range Weight (< 1 or > 777) Inputted!";

    //  Declare global variables
    EditText heightText;            //  Holds value of height text box
    EditText weightText;            //  Holds value of weight text box
    TextView answerText;            //  Holds value of calculated BMI
    Toast validToast    = null;     //  Toast variable
    int height          = 0;        //  Numeric value in height editText
    int weight          = 0;        //  Numeric value in weight editText
    double bmiValue     = 0.0;      //  Calculated body mass index (BMI)
    String status       = "";       //  Status, i.e. underweight, etc.
    String result       = "";       //  Final result in textView

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Set references to the class variables declared above
        heightText  = findViewById(R.id.editTextHeight);
        weightText  = findViewById(R.id.editTextWeight);
        answerText  = findViewById(R.id.textViewResult);
        heightText.requestFocus();
    }

    //  Code that runs when the calculate button is clicked
    public void calculateTheBMI(View view)
    {
        boolean validFlag = true;

        height = Integer.parseInt(heightText.getText().toString());
        weight = Integer.parseInt(weightText.getText().toString());

        validFlag = validateHeight();

        if (validFlag)
        {
            validFlag = validateWeight();
        }

        if (validFlag)
        {
            calculateBMI();
            calculateBMIStatus();
            displayBMIInfo();
        }
    }

    //  Validate wheight between MINHEIGHT and MAXHEIGHT
    public boolean validateHeight()
    {
        if ((height < MINHEIGHT) || (height > MAXHEIGHT))
        {
            validToast.makeText(this, OORHEIGHT, Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    //  Validate weight between MINWEIGHT and MAXWEIGHT
    public boolean validateWeight()
    {
        if ((weight < MINWEIGHT) || (weight > MAXWEIGHT))
        {
            validToast.makeText(this, OORWEIGHT, Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    //  Calculate BMI using associated formula
    public void calculateBMI()
    {
        bmiValue = ((weight / Math.pow(height, 2)) * 703);
    }

    //  Calculate BMI status of underweight,
    //  optimal weight, overweight or obese
    public void calculateBMIStatus()
    {
        //  Calculate BMI status (Underweight,
        //  Overweight, or Optimal Weight)
        if (bmiValue < MINOPT)
        {
            status = "UNDERWEIGHT";
        }
        else if (bmiValue < MAXOVER)
        {
            status = "OPTIMAL WEIGHT";
        }
        else if (bmiValue < MINOBESE)
        {
            status = "OVERWEIGHT";
        }
        else
        {
            status = "OBESE";
        }
    }

    //  Display height, weight, BMI, and BMI Status
    public void  displayBMIInfo()
    {
        //  Build the output string
        result = "Height: "  + heightText.getText().toString() + "\n";
        result += "Weight: " + weightText.getText().toString() + "\n";
        result += "BMI: "    + String.format("%.2f", bmiValue) + "\n";
        result += "Status: " + status;

        //  Assign output string to the answerText text field
        answerText.setText(result);
    }

    //  Code that runs when the clear button is clicked
    public void clear(View view)
    {
        //  This method:
        //
        //  1. Clears the height text box.
        //  2. Clears the weight text box.
        //  3. Clears the BMI edit text.
        //  4. Sets the focus to the height text box.
        heightText.setText("");
        weightText.setText("");
        answerText.setText("");
        heightText.requestFocus();
    }
}