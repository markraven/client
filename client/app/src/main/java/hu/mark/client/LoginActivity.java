package hu.mark.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    public static String token;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.14.193:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView editTextTextPersonName=findViewById(R.id.editTextTextPersonName);
        TextView editTextTextPassword=findViewById(R.id.editTextTextPassword);
        Button sendReg=(Button)findViewById(R.id.regButton);
        sendReg.setOnClickListener(v -> {

            //TextView textViewResult = findViewById(R.id.text_view_result);

            JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
            User user=new User(editTextTextPersonName.getText().toString(),editTextTextPassword.getText().toString());
           // Call<User> call = jsonPlaceHolderApi.register(user);
            Call<User> call = jsonPlaceHolderApi.login(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (!response.isSuccessful()) {
                        //textViewResult.setText("Code:" + response.code());
                        return;
                    }
                    User userResponse = response.body();
                    token=userResponse.getToken();

                    OpenNewActivity();
                }


                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    //textViewResult.setText(t.getMessage());
                }
            });
        });



    }

    private void OpenNewActivity(){
        Intent intent = new Intent( this,Mails.class);
        startActivity(intent);
    }


}