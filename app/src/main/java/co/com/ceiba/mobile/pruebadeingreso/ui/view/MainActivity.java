package co.com.ceiba.mobile.pruebadeingreso.ui.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.entities.UserEntity;
import co.com.ceiba.mobile.pruebadeingreso.ui.Adapters.OnItemClickListener;
import co.com.ceiba.mobile.pruebadeingreso.ui.Adapters.UsersAdapter;
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private EditText searchText;
    private RecyclerView recyclerView;
    private UsersAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainActivityViewModel mainActivityViewModel;
    private ProgressDialog progressDialog;
    private boolean listEmpty = false;
    private View emptyView;
    private ViewGroup parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildRecyclerView();
        buildProgressDialog();
        parentView = (ViewGroup) recyclerView.getParent();
        emptyView = getLayoutInflater().inflate(R.layout.empty_view, parentView, false);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getUsers().observe(this, new Observer<List<UserEntity>>() {
            @Override
            public void onChanged(List<UserEntity> userEntities) {
                adapter.setUsers(userEntities);
                progressDialog.dismiss();
            }
        });

        searchText = findViewById(R.id.editTextSearch);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.setUsers(mainActivityViewModel.filterByKeyWord(s.toString()));
                if(adapter.getItemCount()==0 && !listEmpty){
                    replaceRecyclerViewWithView();
                }
                if(adapter.getItemCount()!= 0 && listEmpty){
                    replaceViewWithRecyclerView();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void replaceViewWithRecyclerView(){
        int index = parentView.indexOfChild(emptyView);
        parentView.removeView(emptyView);
        parentView.addView(recyclerView, index);
        listEmpty = false;
    }

    private void replaceRecyclerViewWithView(){
        int index = parentView.indexOfChild(recyclerView);
        parentView.removeView(recyclerView);
        parentView.addView(emptyView, index);
        listEmpty = true;
    }

    private void buildRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewSearchResults);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new UsersAdapter();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, PostActivity.class);
                intent = mainActivityViewModel.setUserInformationToIntent(intent,position);
                startActivity(intent);
            }
        });
    }

    private void buildProgressDialog(){
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Por favor espere... Estamos obteniendo los datos del servidor");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
}