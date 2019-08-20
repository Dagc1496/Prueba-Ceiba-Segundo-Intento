package co.com.ceiba.mobile.pruebadeingreso.ui.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.entities.PostEntity;
import co.com.ceiba.mobile.pruebadeingreso.ui.Adapters.PostAdapter;
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.PostActivityViewModel;

public class PostActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private PostActivityViewModel postActivityViewModel;

    private TextView textViewName;
    private TextView textViewPhone;
    private TextView textViewEmail;
    private ProgressDialog progressDialog;

    private ArrayList<PostEntity> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        textViewName = findViewById(R.id.name);
        textViewPhone = findViewById(R.id.phone);
        textViewEmail = findViewById(R.id.email);

        buildRecyclerView();
        buildProgressDialog();

        Intent intent = getIntent();
        final String userId =  showUserInformation(intent);

        showUserInformation(intent);
        postActivityViewModel = ViewModelProviders.of(this).get(PostActivityViewModel.class);
        postActivityViewModel.getPost(userId).observe(this, new Observer<List<PostEntity>>() {
            @Override
            public void onChanged(List<PostEntity> postEntities) {
                adapter.setPost(postEntities);
                progressDialog.dismiss();
            }
        });

    }

    private void buildRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewPostsResults);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new PostAdapter();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private String showUserInformation(Intent intent){
        String userId = intent.getStringExtra(getString(R.string.user_id));
        textViewName.setText(intent.getStringExtra(getString(R.string.user_name)));
        textViewPhone.setText(intent.getStringExtra(getString(R.string.user_phone)));
        textViewEmail.setText(intent.getStringExtra(getString(R.string.user_email)));
        return userId;
    }

    private void buildProgressDialog(){
        progressDialog = new ProgressDialog(PostActivity.this);
        progressDialog.setMessage(getString(R.string.generic_message_progress));
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
}
