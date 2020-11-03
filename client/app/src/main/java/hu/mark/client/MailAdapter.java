package hu.mark.client;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MailAdapter extends RecyclerView.Adapter<MailAdapter.ViewHolder>  {

    ArrayList<Messages> mails;
    //TODO:képfeltöltő
    int images;
    Context ct;
    public MailAdapter(ArrayList<Messages> mails, int images, Context ct) {
        this.mails = mails;
        this.images=images;
        this.ct=ct;
    }

    @NonNull
    @Override
    public MailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MailAdapter.ViewHolder holder, int position) {
    holder.felado.setText(mails.get(position).getFelado());
    holder.targy.setText(mails.get(position).getTargy());
    holder.message.setText(mails.get(position).getMessage());
        //TODO:képfeltöltő
    holder.user_avatar.setImageResource(images);

    holder.mainLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(ct,OpenedMailActivity.class);
            intent.putExtra("felado",mails.get(position).getFelado());
            intent.putExtra("targy",mails.get(position).getTargy());
            intent.putExtra("message",mails.get(position).getMessage());
            intent.putExtra("images",images);
            ct.startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return mails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView targy,felado,message;
        public ImageView user_avatar;
        public LinearLayout mainLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            targy=itemView.findViewById(R.id.targy_row);
            felado=itemView.findViewById(R.id.felado_row);
            message=itemView.findViewById(R.id.message_row);
            user_avatar=itemView.findViewById(R.id.user_avatar);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }
}
