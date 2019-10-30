package com.shubamvirdi.jingo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Time;

public class DetailMusic extends AppCompatActivity implements PaymentResultListener {
    private String name,artist,url,image;
    private TextView Name,Artist;
    private Button playpause,buy;
    private ImageView label;
    private boolean play=false;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_music);

        // Binding id's with views
        Name = findViewById(R.id.detailsongname);
        Artist = findViewById(R.id.detailsongartists);
        playpause = findViewById(R.id.playsample);
        label = findViewById(R.id.musiclabel);
        buy = findViewById(R.id.buy);

        // getting data from bundle
        Bundle b = getIntent().getExtras();
        name = b.getString("name");
        artist =  b.getString("artists");
        url = b.getString("url");
        image = b.getString("image");

        // setting album cover image
        Picasso.get().load(image).into(label);

        Name.setText(name);
        Artist.setText(artist);

        // rotation animation for the ablub cover
        @SuppressLint("ResourceType") Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.layout.animation);
        label.startAnimation(hyperspaceJumpAnimation);
        mp  = new MediaPlayer();
        try {
            mp.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // On click listener for buy button


        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpPayment();
            }
        });

        // On click listener for playpause button

        playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (play){
                        mp.stop();
                        playpause.setText("Start");
                        play =false;

                }else {
                     play =true;
                     playpause.setText("Preparing...");
                     playpause.setClickable(false);

                    try {
                        mp.prepare();
                    } catch (IOException e) {
                        Toast.makeText(DetailMusic.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    mp.start();
                    playpause.setText("Stop");
                    playpause.setClickable(true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            play =false;
                            playpause.setText("Start");
                            mp.stop();
                        }
                    },90000);

                }


            }
        });
    }

    private void setUpPayment() {

        Checkout checkout = new Checkout();


        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Passing  payment options to the Razorpay Checkout as a JSONObject
         */
        try {

            // initiating payment
            JSONObject options = new JSONObject();
            options.put("name", "Shubam Virdi");
            options.put("description", Timestamp());
            options.put("currency", "INR");
            options.put("amount", "10000");

            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e("Error", "Error in starting Razorpay Checkout", e);
        }




    }
    public String Timestamp(){
        long l = System.currentTimeMillis();
        return Long.toString(l);
    }

    @Override
    public void onPaymentSuccess(String s) {

        // Payemnt Successful
        Intent i =new Intent(DetailMusic.this,PaymentSuccess.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onPaymentError(int i, String s) {
        //Payment Failed

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

    }
}
