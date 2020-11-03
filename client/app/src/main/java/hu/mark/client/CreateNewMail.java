package hu.mark.client;

import android.content.Intent;
import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateNewMail extends Mails {
    String token= LoginActivity.token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_mail);
        EditText cimzett,text,targy;
        TextView felado;
        Button button=(Button)findViewById(R.id.send_button);
        cimzett=findViewById(R.id.cimzett_newmail);
        text=findViewById(R.id.mailtex_newmail);
        targy=findViewById(R.id.targy_newmail);
        felado=findViewById(R.id.felado_newmail);


        button.setOnClickListener(v -> {
            Messages sendingMessage= new Messages(cimzett.getText().toString(),
                                                  felado.getText().toString(),
                                                  text.getText().toString(),
                                                  targy.getText().toString());
            JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
            Call<Messages> call = jsonPlaceHolderApi.sendMail("Bearer "+token,sendingMessage);
            call.enqueue(new Callback<Messages>() {
                @Override
                public void onResponse(Call<Messages> call, Response<Messages> response) {
                    if (!response.isSuccessful()) {
                        text.setText("Code:" + response.code());
                        return;
                    }
                    startActivity(new Intent(CreateNewMail.this,Mails.class));

                }

                @Override
                public void onFailure(Call<Messages> call, Throwable t) {
                text.setText("hiba:" + t.getMessage());
                }
            });
        });


    }
}