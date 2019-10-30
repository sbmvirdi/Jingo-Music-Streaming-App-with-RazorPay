package com.shubamvirdi.jingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Portfolio extends AppCompatActivity {
    private Button resume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);

        // Button to Download Resume

        resume = findViewById(R.id.resume);

        // OnClickListener for Resume Downloading

        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://internshala.com/student/resume_download/72C44702-BDB6-C1C0-12F9-B52C5DD12659/2418972"));
                startActivity(i);

            }
        });



    }
}
