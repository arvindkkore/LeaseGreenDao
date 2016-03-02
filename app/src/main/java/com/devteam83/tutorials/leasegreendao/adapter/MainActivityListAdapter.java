package com.devteam83.tutorials.leasegreendao.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devteam83.tutorials.leasegreendao.R;
import com.devteam83.tutorials.leasegreendao.model.Lease;

import java.util.List;

/**
 * Created by synsoft on 02/03/16.
 */
public class MainActivityListAdapter extends RecyclerView.Adapter<MainActivityListAdapter.ViewHolder>{
    private List<Lease> mData;

    public MainActivityListAdapter(List<Lease> data) {
        this.mData = data;
    }

    @Override
    public MainActivityListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyler_row, null);

        // create ViewHolder

        ViewHolder viewHolder = new MainActivityListAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.textViewItem.setText(mData
                .get(position)
                .getItem());
        viewHolder.textViewPersonName.setText(mData
                .get(position)
                .getPerson()
                .getName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewItem;
        public TextView textViewPersonName;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            textViewItem = (TextView) itemLayoutView.findViewById(R.id.activity_main_item_item);
            textViewPersonName = (TextView) itemLayoutView.findViewById(R.id.activity_main_item_person_name);
        }
    }
}
