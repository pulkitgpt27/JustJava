package com.example.android.justjava;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int noc=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    private int calprice()
    {
        int price=noc*5;
        return price;
    }
    private String createsummary(int price)
    {
        CheckBox cb=(CheckBox) findViewById(R.id.w_check_box);
        CheckBox cb1=(CheckBox) findViewById(R.id.w_check_box1);
        EditText ed=(EditText) findViewById(R.id.name_edit_text);
        ;
        String str=getString(R.string.name)+ed.getText()+"\n";
        if(cb.isChecked()) {
            price+=1;
            str = str + getString(R.string.add) + cb.getText() + "?"+getString(R.string.trues);
        }
        else {
            str = str + getString(R.string.add) + cb.getText() + "?"+getString(R.string.falses);
        }
        str+="\n";
        if(cb1.isChecked()) {
            price+=2;
            str = str + getString(R.string.add) + cb1.getText() + "?"+getString(R.string.trues);
        }
        else {
            str = str + getString(R.string.add) + cb1.getText() + "?"+getString(R.string.falses);
        }

        str+="\n"+getString(R.string.quantity)+":"+noc+"\n"+getString(R.string.order_summary_price,price)+"\n"+getString(R.string.thank_you);
        return str;
    }

    public void submitOrder(View view) {

        int price=calprice();
        String sumary=createsummary(price);
       //displaysumary(sumary);
        EditText ed=(EditText) findViewById(R.id.name_edit_text);
        Intent intent=new Intent(Intent.ACTION_SENDTO);

        intent.setData(Uri.parse("mailto:"));

        intent.putExtra(Intent.EXTRA_TEXT,sumary);
        intent.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.order_summary_email_subject)+ed.getText());


        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void increment(View view) {
       if(noc<100)
        noc++;
        else {
           Context context = getApplicationContext();
           String text = "Cant order more coffees";
           int duration = Toast.LENGTH_SHORT;

           Toast toast = Toast.makeText(context, text, duration);
           toast.show();
       }
        display(noc);

    }


    public void decrement(View view) {
       if(noc>1)
        noc--;
        else {

           Toast.makeText(this,"Cant order less coffees",Toast.LENGTH_SHORT).show();

       }
        display(noc);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */

}
