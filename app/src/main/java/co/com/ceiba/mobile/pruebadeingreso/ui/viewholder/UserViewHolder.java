package co.com.ceiba.mobile.pruebadeingreso.ui.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.ui.Adapters.OnItemClickListener;

public class UserViewHolder extends RecyclerView.ViewHolder {

    private TextView textViewName;
    private TextView textViewPhone;
    private TextView textViewEmail;
    public Button buttonViewPost;

    public UserViewHolder(final View itemView, final OnItemClickListener onItemClickListener) {

        super(itemView);
        this.textViewName = itemView.findViewById(R.id.name);
        this.textViewPhone = itemView.findViewById(R.id.phone);
        this.textViewEmail = itemView.findViewById(R.id.email);
        this.buttonViewPost = itemView.findViewById(R.id.btn_view_post);

        buttonViewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener !=null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        onItemClickListener.onItemClick(position);
                    }
                }
            }
        });
    }

    public void setTextViewName(String name){
        textViewName.setText(name);
    }

    public void setTextViewEmail(String email){
        textViewEmail.setText(email);
    }

    public void setTextViewPhone(String phone){
        textViewPhone.setText(phone);
    }
}
