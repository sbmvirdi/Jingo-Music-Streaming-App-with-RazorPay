package com.shubamvirdi.jingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class PaymentSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);

        // Payment Success JAVA File which stays upto 3 seconds

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(PaymentSuccess.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },3000);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
