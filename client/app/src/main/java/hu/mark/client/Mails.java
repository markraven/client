package hu.mark.client;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Mails extends LoginActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    String token= LoginActivity.token;

    int images=R.drawable.user;
    FloatingActionButton fb;
    ArrayList<Root>mails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mails);
        //New mail
        fb=findViewById(R.id.floatingActionButton);
        fb.setOnClickListener(v ->
                startActivity(new Intent(Mails.this,CreateNewMail.class)));

        mails=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Root>> call=jsonPlaceHolderApi.getAllMails("Bearer " + token);

        call.enqueue(new Callback<List<Root>>() {
            @Override
            public void onResponse(Call<List<Root>> call, Response<List<Root>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<Root> messages = response.body();

                adapter= new MailAdapter(messages,images,Mails.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Root>> call, Throwable t) {

            }
        });
    }

}