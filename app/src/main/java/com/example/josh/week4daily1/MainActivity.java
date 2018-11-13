package com.example.josh.week4daily1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.josh.week4daily1.models.LoadTracker;
import com.example.josh.week4daily1.utils.OkhttpHelper;
import com.example.josh.week4daily1.utils.ScrollListener;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvPersonList;
    OkhttpHelper okhttpHelper;
    LoadTracker loadTracker;
    private PaginationAdapter adapter;
    private LoadDataAsync loadDataAsync;
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar pbLoading;
    int gender = 0;
    int nationality = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pbLoading = findViewById(R.id.pbLoading);


        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvPersonList = findViewById(R.id.rvPersonList);
        okhttpHelper = new OkhttpHelper();
        loadTracker = new LoadTracker(true);
        bindRecyclerView();
        addFirstList();



        rvPersonList.addOnScrollListener(new ScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                loadTracker.setLoading(true);
                loadDataAsync = new LoadDataAsync(adapter,loadTracker,pbLoading,gender,nationality);
                loadDataAsync.execute();
            }
            @Override
            public boolean isLoading() {
                return loadTracker.isLoading();
            }
        });
    }


    public void addToListTest(View view) {
        bindRecyclerView();
        addFirstList();
        adapter.notifyDataSetChanged();

    }

    private void bindRecyclerView(){
        RecyclerView rvPersonList = findViewById(R.id.rvPersonList);
        adapter = new PaginationAdapter();
        rvPersonList.setAdapter(adapter);
        rvPersonList.setLayoutManager(linearLayoutManager);
    }

    public void addFirstList(){
        loadDataAsync = new LoadDataAsync(adapter,loadTracker,pbLoading,gender,nationality);
        loadDataAsync.execute();
    }

    public void genderFlip(View view) {

        switch (view.getId()){
            case R.id.rbBoth:
                gender = 0;
                break;
            case R.id.rbMale:
                gender = 1;
                break;
            case R.id.rbFemale:
                gender = 2;
                break;

        }
    }

    public void natFlip(View view) {
        switch (view.getId()){
            case R.id.rbUS:
                nationality = 0;
                break;
            case R.id.rbCA:
                nationality = 1;
                break;
            case R.id.rbAU:
                nationality = 2;
                break;
            case R.id.rbAll:
                nationality = 3;
                break;

        }
    }
}
