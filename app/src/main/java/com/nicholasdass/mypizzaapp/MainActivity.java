package com.nicholasdass.mypizzaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import static com.nicholasdass.mypizzaapp.R.id.grpPizzaSize;

public class MainActivity extends AppCompatActivity {

    // Field Variables****************************************************************

    public final static int TOPPINGS_ACTIVITY = 1;

    TextView txtSelectSize;
    TextView txtFinalOrder;

    RadioButton radSmall;
    RadioButton radMedium;
    RadioButton radLarge;

    RadioGroup radGrpPizzaSize;

    Button btnAddToppings;
    Button btnNewPizza;

    String pizzaSize;

    //*********************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing Variables****************************************************

        txtSelectSize = (TextView) findViewById(R.id.txtSelectSize);
        txtFinalOrder = (TextView) findViewById(R.id.txtFinalOrder);

        radSmall = (RadioButton) findViewById(R.id.radSmall);
        radMedium = (RadioButton) findViewById(R.id.radMedium);
        radLarge = (RadioButton) findViewById(R.id.radLarge);

        btnAddToppings = (Button) findViewById(R.id.btnAddToppings);
        btnNewPizza = (Button) findViewById(R.id.btnNewPizza);

        btnNewPizza.setVisibility(View.GONE);

        radGrpPizzaSize = (RadioGroup) findViewById(grpPizzaSize);


        pizzaSizeSelection();

        addToppings();

        newPizza();

    }

    // This method displays the user's pizza size selection and sets it.
    public void pizzaSizeSelection() {

        radGrpPizzaSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                //Check which pizza size is selected and display it in Main activity
                switch (radGrpPizzaSize.getCheckedRadioButtonId()) {

                    case R.id.radSmall:
                        pizzaSize = Pizza.SMALL;
                        break;
                    case R.id.radMedium:
                        pizzaSize = Pizza.MEDIUM;
                        break;
                    case R.id.radLarge:
                        pizzaSize = Pizza.LARGE;
                        break;
                }

                txtSelectSize.setText(pizzaSize);
            }
        });
    }

    // This method sends the user pizza size selection to the add toppings activity
    public void addToppings(){
        btnAddToppings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Intent for the ToppingsActivity
                Intent intent = new Intent(getApplicationContext(), ToppingsActivity.class);

                //String pizzaSize =
                // An instance of student
                Pizza pSize = new Pizza(pizzaSize);
                intent.putExtra("pizzaSize", pSize);

                //starts the activity
                startActivityForResult(intent, TOPPINGS_ACTIVITY);

            }
        });
    }

    // After the user clicks on new pizza
    public void newPizza(){

        btnNewPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtFinalOrder.setText("");
                radSmall.setChecked(true);
                txtSelectSize.setText("Select the Size");

                btnNewPizza.setVisibility(View.GONE);
                btnAddToppings.setVisibility(View.VISIBLE);

            }
        });
    }

    // This method will check the result coming in from Toppings Activity and display data.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check to make sure we're coming back from RegisterActivity
        if (requestCode == TOPPINGS_ACTIVITY) {

            if (resultCode == RESULT_OK) {

                btnAddToppings.setVisibility(View.GONE);
                btnNewPizza.setVisibility(View.VISIBLE);

                // Get the student from the data intent and set the text
                Pizza pizzaOrder = data.getParcelableExtra("pizzaSize");

                if (pizzaOrder != null) {

                    // create a format String for displaying and set the text
                    //String format = "Registration Successful\n\nStudent ID = %d";

                    String order = pizzaOrder.getSize() + " with: ";


                    if (pizzaOrder.getHasCheese())
                    {
                        order += "\n\t- Cheese";
                    }

                    if (pizzaOrder.getHasPepperoni())
                    {
                        order += "\n\t- Pepperoni";
                    }

                    if (pizzaOrder.getHasGreenPepper())
                    {
                        order += "\n\t- Green Pepper";
                    }

                    txtFinalOrder.setText(order);

                } else {
                    // failed!
                    txtFinalOrder.setText("Order Did not come through");
                }
            }
        }
    }
}
