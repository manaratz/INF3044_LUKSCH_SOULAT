package com.example.frede.myapplication;

import android.support.v7.widget.DecorToolbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by 5eme AS on 15/11/2016.
 */

class BiersAdapter extends RecyclerView.Adapter<BiersAdapter.BierHolder>{
    private JSONArray biers;

    BiersAdapter(JSONArray biers){
        this.biers = biers;
    }

    @Override
    public BierHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_bier_element,null,false);
        return new BierHolder(v);
    }

    @Override
    public void onBindViewHolder(BierHolder holder, int position) {
        try {
            String s = (String) biers.get(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return biers.length();
    }

    public class BierHolder extends RecyclerView.ViewHolder{


        public BierHolder(View view) {
            super(view);

        }
    }

}
