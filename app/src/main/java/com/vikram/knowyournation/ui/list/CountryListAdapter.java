package com.vikram.knowyournation.ui.list;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vikram.knowyournation.R;
import com.vikram.knowyournation.dataservice.RetroResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by M1032130 on 11/2/2017.
 */

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.ViewHolder> {
    private List<RetroResponse> listCountry, listFilter;
    private final OnItemClickListener listener;
    Context mContext;

    public interface OnItemClickListener {
        void onItemClick(RetroResponse item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textCapital;
        RelativeLayout relCountryContainer;

        public ViewHolder(View itemView, int ViewType) {
            super(itemView);
            textName = (TextView) itemView.findViewById(R.id.txt_cname);
            textCapital = (TextView) itemView.findViewById(R.id.txt_capital);
            relCountryContainer = (RelativeLayout) itemView.findViewById(R.id.contain_country);

        }

        public void bind(final RetroResponse item, final OnItemClickListener listener) {
            relCountryContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    CountryListAdapter(Context context, List<RetroResponse> listCountry, OnItemClickListener listener) {
        this.mContext = context;
        this.listCountry = listCountry;
        this.listFilter = new ArrayList<>();
        this.listFilter.addAll(this.listCountry);
        this.listener = listener;
    }

    @Override
    public CountryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_contact, parent, false);
        ViewHolder vhItem = new ViewHolder(v, viewType);
        return vhItem;
    }

    @Override
    public void onBindViewHolder(CountryListAdapter.ViewHolder holder, int position) {
        holder.textName.setText(listFilter.get(position).getName());
        holder.textCapital.setText(listFilter.get(position).getCapital());

        holder.bind(listFilter.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return (null != listFilter ? listFilter.size() : 0);
    }

    //Searching item
    public void filter(final String text) {
        // Searching could be complex..so we will dispatch it to a different thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Clear the filter list
                listFilter.clear();
                // If there is no search value, then add all original list items to filter list
                if (TextUtils.isEmpty(text)) {

                    listFilter.addAll(listCountry);

                } else {
                    // Iterate in the original List and add it to filter list
                    for (RetroResponse item : listCountry) {
                        if (item.getName().toLowerCase().contains(text.toLowerCase()) ) {
                            // Adding Matched items
                            listFilter.add(item);
                        }
                    }
                }
                // Set on UI Thread
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Notify the List that the DataSet has changed
                        notifyDataSetChanged();
                    }
                });

            }
        }).start();
    }
}