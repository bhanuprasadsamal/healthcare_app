package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

@SuppressWarnings("resource")
public class BuyMedicineBookActivity extends AppCompatActivity {
    EditText edname,edaddress,edcontact,edpincode;
    Button btnBooking;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edname= findViewById(R.id.editTextBMBFullname);
        edaddress= findViewById(R.id.editTextBMBAddress);
        edcontact= findViewById(R.id.editTextBMBContact);
        btnBooking= findViewById(R.id.buttonBMBBooking);
        edpincode= findViewById(R.id.editTextBMBPincode);

        Intent intent = getIntent();
        String[] price= Arrays.toString(intent.getStringArrayExtra("price")).split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs",Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                db.addOrder(username,edname.getText().toString(),edaddress.getText().toString(),edcontact.getText().toString(), Integer.parseInt(edpincode.getText().toString()),date.toString(), "",Float.parseFloat(price[1].toString()),"medicine");
                db.removeCart(username,"medicine");
                Toast.makeText(getApplication(),"Your booking is done sucessfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        });
    }
}