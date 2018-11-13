package com.example.josh.week4daily1;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.josh.week4daily1.models.LoadTracker;
import com.example.josh.week4daily1.models.Person;
import com.example.josh.week4daily1.utils.NetworkHelper;
import com.example.josh.week4daily1.utils.OkhttpHelper;

import java.util.List;

public class LoadDataAsync extends AsyncTask {

    PaginationAdapter adapter;
    LoadTracker loadTracker;
    ProgressBar pbLoading;
    int gender;
    int nationality;

    public static final String TAG = "_TAG";
    public LoadDataAsync(PaginationAdapter adapter, LoadTracker loadTracker, ProgressBar pbLoading,int gender,int nationality) {
        this.adapter = adapter;
        this.loadTracker = loadTracker;
        this.pbLoading = pbLoading;
        this.gender = gender;
        this.nationality = nationality;
    }

    @Override
    protected void onPreExecute() {
        Log.d(TAG, "onPreExecute: ");
        super.onPreExecute();
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        String restrictions = "";
        switch (gender){
            case 0:
                break;
            case 1:
                restrictions = "&gender=male";
                break;
            case 2:
                restrictions = "&gender=female";
                break;
        }
        switch (nationality){

            case 0:
                restrictions.concat("&nat=US");
                break;
            case 1:
                restrictions.concat("&nat=CA");
                break;
            case 2:
                restrictions.concat("&nat=AU");
                break;
            case 3:
                break;

        }


        OkhttpHelper okhttpHelper = new OkhttpHelper();
        Log.d(TAG, "doInBackground: " + restrictions);
        List<Person> personList = okhttpHelper.execute(restrictions);
        adapter.add(personList);
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        loadTracker.setLoading(false);
        pbLoading.setVisibility(View.INVISIBLE);
        adapter.notifyDataSetChanged();
    }
}
