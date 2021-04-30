package hu.mark.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    protected static String token;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView editTextTextPersonName=findViewById(R.id.editTextTextPersonName);
        TextView editTextTextPassword=findViewById(R.id.editTextTextPassword);
        Button loginBtn=(Button)findViewById(R.id.logButton);
        loginBtn.setOnClickListener(v -> {
            TextView textViewResult = findViewById(R.id.text_view_result);
            JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
            User user=new User(editTextTextPersonName.getText().toString(),editTextTextPassword.getText().toString());
            Call<User> call = jsonPlaceHolderApi.login(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (!response.isSuccessful()) {
                        textViewResult.setText("Code:" + response.code());
                        return;
                    }
                    User userResponse = response.body();
                    token=userResponse.getToken();
                    OpenNewActivity();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    textViewResult.setText("Code:" + t.getMessage());
                }
            });
        });
    }

    private void OpenNewActivity(){
        Intent intent = new Intent( this,Mails.class);
        startActivity(intent);
    }

}