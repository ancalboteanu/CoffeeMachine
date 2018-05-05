package com.example.albot.test;

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

public class MainActivity extends AppCompatActivity {

    int quantity = 0; //variabila globala  folosita pt metodele increment si decrement

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view){
        if (quantity == 100) {
            return;
        }
        quantity = quantity +1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity == 0) {
            return;
        }
        quantity = quantity -1;
        displayQuantity(quantity);


    }


   public void submitOrder(View view) {
//        gaseste numele utiloizatorului
       EditText nameEditText = findViewById(R.id.name_edit_text);
       String name = nameEditText.getText().toString();
//       Log.v("MainActivity","Name!" + name);

       //ai posibilitatea de a alege drept topping- frisca;
       CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbok);
       boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
      // Log.v("MainActivity", "Has whipped cream:" + hasWhippedCream);

       //ai posibilitatea de a alege drept topping-ciocolata;
       CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbok);
       boolean hasChocolate = chocolateCheckBox.isChecked();
      // Log.v("MainActivity", "Has whipped cream:" + hasWhippedCream);

       double price = calculatePrice(hasWhippedCream, hasChocolate);
       String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, name);
       displayMessage(priceMessage);


       // aceasta secventa de cod comentata face o trimitere catre harta. dataorita metodelor apelate.
//         Intent intent = new Intent(Intent.ACTION_VIEW);
//         intent.setData(Uri.parse("geo:47.6, -122.3"));
//         if (intent.resolveActivity(getPackageManager()) != null) {
//           startActivity(intent);
//         }
    }

   private double calculatePrice(boolean addWhippedCream, boolean addChocolate ) {
        double basePrice =3.5;

        if (addWhippedCream) {
            basePrice =basePrice +0.25;
        }

        if  (addChocolate){
            basePrice =basePrice +0.50;
        }

        return quantity * basePrice;
   }

   private String createOrderSummary(double price, boolean addWhippedCream, boolean addChocolate, String name){
       String priceMessage = getString(R.string.order_summary_name, name);
       priceMessage += "\n" +getString(R.string.order_summary_whipped_cream, addWhippedCream);
       priceMessage += "\n" +getString(R.string.order_summary_chocolate, addChocolate);
       priceMessage += "\n" +getString(R.string.order_summary_quantity, quantity);
       priceMessage += "\n" +getString(R.string.order_summary_price,
               NumberFormat.getCurrencyInstance().format(price));
       priceMessage += "\n";
       return priceMessage;
   }

    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" +numberOfCoffees);
    }
        private void displayMessage(String message) {
            TextView orderSummaryTextView =  findViewById(R.id.order_summary_text_view);
            orderSummaryTextView.setText(message);

    }

}