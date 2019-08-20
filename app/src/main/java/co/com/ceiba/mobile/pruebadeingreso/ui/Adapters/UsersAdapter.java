package co.com.ceiba.mobile.pruebadeingreso.ui.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.entities.UserEntity;
import co.com.ceiba.mobile.pruebadeingreso.ui.viewholder.UserViewHolder;


public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<UserEntity> liveUsers = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_item, parent, false);
        return new UserViewHolder(itemView, onItemClickListener);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserEntity currentUser = liveUsers.get(position);

        holder.setTextViewName(currentUser.getName());
        holder.setTextViewEmail(currentUser.getEmail());
        holder.setTextViewPhone(currentUser.getPhone());
    }

    @Override
    public int getItemCount() {
        return liveUsers.size();
    }

    public void setUsers(List<UserEntity> users) {
        this.liveUsers = users;
        notifyDataSetChanged();
    }
}