package com.nicholasdass.mypizzaapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class ToppingsActivity extends AppCompatActivity {

    // Field Variables****************************************

    TextView txtSizeView;

    CheckBox chkCheese;
    CheckBox chkPepperoni;
    CheckBox chkGreenPepper;

    Button btnCompleteOrder;

    AlertDialog dialog;

    String ingredient;

    Intent intent;

    // private datafield to hold pizza size coming in
    private Pizza pizzaSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toppings);

        // Initializing Vairables******************************************************

        txtSizeView = (TextView) findViewById(R.id.txtSizeView);

        chkCheese = (CheckBox) findViewById(R.id.chkCheese);
        chkPepperoni = (CheckBox) findViewById(R.id.chkPepperoni);
        chkGreenPepper = (CheckBox) findViewById(R.id.chkGreenPepper);

        btnCompleteOrder = (Button) findViewById(R.id.btnCompleteOrder);


        displaySize();
        //ingredientSelection();
        //checkToppings();

        //******************************************************************************
    }

    private void displaySize() {
        // I tried to get this to work but the pizza size was not coming through.
        // get the calling intent and get the student
        Intent intent = getIntent();
        pizzaSize = intent.getParcelableExtra("pizzaSize");

        System.out.println(pizzaSize.toString());

        if (pizzaSize != null) {
            // use a StringBuilder to build the String
            StringBuilder text = new StringBuilder();

            //text.append("Complete registration for student\n\n");
            text.append(pizzaSize.getSize());

            // set the display to the built String
            txtSizeView.setText(text.toString());


//            if (student.needsResidence()) {
//                text.append("\n\nResidence Required");
//                btnFull.setEnabled(true);
//            } else {
//                btnFull.setEnabled(false);
//            }

        } else {

            txtSizeView.setText("Pizza size not coming in!");

        }
    }

    // This method checks for the ingredient selected by the user (This does not make any sense) DO not use.
//    public void ingredientSelection(){
//
//        if (chkCheese.isChecked()){
//            ingredient = "cheese";
//
//        } else if(chkPepperoni.isChecked()){
//            ingredient = "pepperoni";
//
//        } else if(chkGreenPepper.isChecked()){
//            ingredient = "green pepper";
//        }
//
//    }

    // I tried but just couldn't figure out why the data was not being sent back to main activity
    // This method implements send the user back to main activity

    public void completeOrder(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder = new AlertDialog.Builder(this);

        if(chkCheese.isChecked() == false && chkPepperoni.isChecked() == false && chkGreenPepper.isChecked() == false){

            builder.setTitle("Error")
                    .setMessage("You must select at least one topping!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

            dialog = builder.create();

            dialog.show();

        } else {

            pizzaSize.setHasCheese(chkCheese.isChecked());
            pizzaSize.setHasPepperoni(chkPepperoni.isChecked());
            pizzaSize.setHasGreenPepper(chkGreenPepper.isChecked());

            intent = new Intent();
            intent.putExtra("pizzaSize", pizzaSize);


            setResult(RESULT_OK, intent);


            finish();
        }
    }
}
