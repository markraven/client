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

    //TODO:Szerveren elkészíteni a képfeltöltő funkciót
    int images=R.drawable.user;
    FloatingActionButton fb;
    ArrayList<Messages>mails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mails);

        //Új levél írása
        fb=findViewById(R.id.floatingActionButton);
        fb.setOnClickListener(v ->
                startActivity(new Intent(Mails.this,CreateNewMail.class)));

        mails=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Messages>> call=jsonPlaceHolderApi.getAllMails("Bearer " + token);

        call.enqueue(new Callback<List<Messages>>() {
            @Override
            public void onResponse(Call<List<Messages>> call, Response<List<Messages>> response) {
                if (!response.isSuccessful()) {
                   // tokenview.setText("Code:" + response.code());
                    //TODO: rendes hiba üzenet
                    return;
                }
                List<Messages> messages = response.body();
                for(Messages message : messages){

                    mails.add(message);
                }
                adapter= new MailAdapter(mails,images,Mails.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Messages>> call, Throwable t) {

                //TODO: rendes hiba üzenet
                //tokenview.setText("Hiba" + t.getMessage());
            }
        });


    }




}