package com.example.amita_chand.upgradetest;

/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

import static android.R.attr.name;
import static android.R.id.message;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped);
        boolean hasWhippedCream =  whippedCream.isChecked();

        CheckBox chocolateToppings = (CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate =  chocolateToppings.isChecked();

        EditText txtname =  (EditText) findViewById(R.id.name_input);
        String customer  =  txtname.getText().toString();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(customer, quantity, price, hasWhippedCream, hasChocolate);
//
//        Log.v("MainActivity", "Whipped cream Status" + hasWhippedCream);
          displayMessage(priceMessage);

//        /** intent to send email for order */
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("*/*");
//        //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
//        intent.putExtra(Intent.EXTRA_SUBJECT, "App to Order Coffee for "+ customer);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }

    }

    /**
     * This method is called when the '+' button is clicked.
     */
    public void increment (View view){
        if (quantity == 100){
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    /**
     * This method is called when the "-" button is clicked.
     */
    public void decrement (View view){
        if (quantity == 1){
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen after clicking "+" or "-".
     */
    private void display(int number) {
        TextView quantityText = (TextView) findViewById(R.id.quantity_text);
        quantityText.setText("" + number);
    }

    private void displayMessage(String message){
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    private int calculatePrice(boolean hasWhipped, boolean hasChocolate){
        int basePrice = quantity * 10;
        if (hasWhipped == true){
            basePrice = basePrice + 5 * quantity;
        }
        if (hasChocolate == true){
            basePrice = basePrice + 7 * quantity;
        }
        if (quantity == 0){
            basePrice = 0;
        }
        return basePrice;
    }

    private String  createOrderSummary(String customer, int quantity, int price, boolean hasWhippedCream, boolean hasChocolate){
        String order = "Name: "+customer +"\n Quantity: " + quantity +"\n Add Whipped Cream: " +hasWhippedCream +"\n Add Chocolate toppings: " +hasChocolate+ "\nTotal: $"+ price ;
                return order;
    }

}
