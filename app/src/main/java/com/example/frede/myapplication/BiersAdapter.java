package com.example.frede.myapplication;

import android.support.v7.widget.DecorToolbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
            JSONObject s = (JSONObject) biers.get(position);
            holder.name.setText(s.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return biers.length();
    }

    public void setNewBiere(JSONArray aj) {
        this.biers = aj;
        notifyDataSetChanged();
    }

    public class BierHolder extends RecyclerView.ViewHolder{
        public TextView name;

        public BierHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.rv_biere_element_name);
        }

    }




}
