package com.workaround.ajeesh.wafercodechallenge.WaferCountires.Helpers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.workaround.ajeesh.wafercodechallenge.WaferCountires.Modals.Countries;
import com.workaround.ajeesh.wafercodechallenge.WaferCountires.R;


import java.util.List;

/**
 * Package Name : com.workaround.ajeesh.wafercodechallenge.WaferCountires.Helpers
 * Created by ajesh on 15-08-2018.
 * Project Name : WaferCodeChallenge
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private static final String logName = "WFR-ADPTR-RECYCLER";
    List<Countries> listItems;
    Context theContext;

    public RecyclerViewAdapter(List<Countries> listItems, Context theContext) {
        this.listItems = listItems;
        this.theContext = theContext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LogHelper.LogThreadId(logName, "RecyclerViewAdapter : onCreateViewHolder ");
        View theView = LayoutInflater.from(parent.getContext()).inflate(R.layout.countires_layout_source, parent, false);
        return new RecyclerViewHolder(theView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        LogHelper.LogThreadId(logName, "RecyclerViewAdapter : onBindViewHolder ");
        Countries theListItem = listItems.get(position);
        holder.countryName.setText(theListItem.getName());
        holder.countryCurrency.setText(theListItem.getCurrencies()[0].getName());
        holder.countryLanguage.setText(theListItem.getLanguages()[0].getName());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public void removeItem(int position) {
        listItems.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(Countries restoredCountry, int position) {
        listItems.add(position, restoredCountry);
        // notify item added by position
        notifyItemInserted(position);
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView countryName;
        public TextView countryLanguage;
        public TextView countryCurrency;
        public RelativeLayout viewForeground, viewBackground;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.textCountryName);
            countryCurrency = itemView.findViewById(R.id.textCountryCurrency);
            countryLanguage = itemView.findViewById(R.id.textCountryLanguage);
            viewForeground = itemView.findViewById(R.id.view_foreground);
            viewBackground = itemView.findViewById(R.id.view_background);
        }
    }
}
