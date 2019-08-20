package co.com.ceiba.mobile.pruebadeingreso.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import co.com.ceiba.mobile.pruebadeingreso.R;

public class PostViewHolder extends RecyclerView.ViewHolder {

    private TextView textViewTitle;
    private TextView textViewBody;

    public PostViewHolder(View itemView) {
        super(itemView);

        this.textViewTitle = itemView.findViewById(R.id.title);
        this.textViewBody = itemView.findViewById(R.id.body);
    }

    public void setTextViewTitle(String title){
        textViewTitle.setText(title);
    }

    public void setTextViewBody(String body){
        textViewBody.setText(body);
    }

}
