package com.workaround.ajeesh.wafercodechallenge.WaferCountires.Helpers;

import android.support.v7.widget.RecyclerView;

/**
 * Package Name : com.workaround.ajeesh.wafercodechallenge.WaferCountires.Helpers
 * Created by ajesh on 16-08-2018.
 * Project Name : WaferCodeChallenge
 */
public interface RecyclerViewSwipeListener {
    void onSwipeCompletion(RecyclerView.ViewHolder viewHolder, int direction, int position);
}
