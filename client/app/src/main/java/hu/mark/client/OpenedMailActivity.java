package hu.mark.client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OpenedMailActivity extends AppCompatActivity {

    ImageView opened_mail_avatar;
    TextView opened_mail_felado,opened_mail_targy,opened_mail_text;
    Button opened_reply_btn,opened_mail_delete_btn;

    String opened_mail_felado_str,opened_mail_targy_str,opened_mail_text_str;
    int opened_mail_avatar_int;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opened_mail);
        opened_mail_avatar=findViewById(R.id.opened_mail_avatar);
        opened_mail_felado=findViewById(R.id.opened_mail_felado);
        opened_mail_targy=findViewById(R.id.opened_mail_targy);
        opened_mail_text=findViewById(R.id.opened_mail_text);
        opened_reply_btn=(Button)findViewById(R.id.opened_reply_btn);
        opened_mail_delete_btn=(Button)findViewById(R.id.opened_mail_delete_btn);
        getData();
        setData();

    }
    //Mails activityn szerepelt adatok Intenten keresztül áthozatala az OpenedMailActivity-re
    //getData adatok kinyerése a kulcsnevekkel
    private void getData(){
        if (getIntent().hasExtra("felado") && getIntent().hasExtra("targy")
                && getIntent().hasExtra("message") && getIntent().hasExtra("images")) {

            opened_mail_felado_str=getIntent().getStringExtra("felado");
            opened_mail_targy_str=getIntent().getStringExtra("targy");
            opened_mail_text_str=getIntent().getStringExtra("message");
            opened_mail_avatar_int=getIntent().getIntExtra("images",1);
        }else{
            Toast.makeText(this,"Nincs adat",Toast.LENGTH_SHORT).show();
        }

    }
    //OpenedMailActivity-re a kinyert adatok beállítása.
    private void setData(){
        opened_mail_felado.setText(opened_mail_felado_str);
        opened_mail_targy.setText(opened_mail_targy_str);
        opened_mail_text.setText(opened_mail_text_str);
        opened_mail_avatar.setImageResource(opened_mail_avatar_int);
    }
}