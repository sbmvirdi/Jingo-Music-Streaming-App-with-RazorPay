package com.shubamvirdi.jingo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.shubamvirdi.jingo.Adapters.MyAdapter;
import com.shubamvirdi.jingo.DataModels.JingoModel;
import com.shubamvirdi.jingo.Interfaces.GetdataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rec;
    private Button portfolio;
    private ShimmerFrameLayout sfl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Binding Views with thier Respective Id's
        rec = findViewById(R.id.musicRecyclerView);
        portfolio = findViewById(R.id.portfolio);
        sfl = findViewById(R.id.shimmer);
        rec.setLayoutManager(new LinearLayoutManager(this));
        rec.setHasFixedSize(true);
        rec.setVisibility(View.GONE);
        sfl.startShimmer();
        // Fetching Data from the API using Retrofit

        GetdataService service = RetrofitInstance.getInstance().create(GetdataService.class);
        Call<List<JingoModel>> call = service.getMusicData();
        call.enqueue(new Callback<List<JingoModel>>() {
            @Override
            public void onResponse(Call<List<JingoModel>> call, Response<List<JingoModel>> response) {
                rec.setVisibility(View.VISIBLE);
                sfl.setVisibility(View.GONE);
                MyAdapter myAdapter = new MyAdapter(getApplicationContext(),response.body());
                rec.setAdapter(myAdapter);
            }

            @Override
            public void onFailure(Call<List<JingoModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Onclick Listener for Portfolio Button

        portfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Portfolio.class));
            }
        });


    }


}
