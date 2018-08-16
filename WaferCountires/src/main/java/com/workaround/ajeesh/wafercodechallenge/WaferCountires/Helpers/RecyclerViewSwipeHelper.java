package com.workaround.ajeesh.wafercodechallenge.WaferCountires.Helpers;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

/**
 * Package Name : com.workaround.ajeesh.wafercodechallenge.WaferCountires.Helpers
 * Created by ajesh on 16-08-2018.
 * Project Name : WaferCodeChallenge
 */
public class RecyclerViewSwipeHelper extends ItemTouchHelper.SimpleCallback {
    private static final String logName = "WFR-TCH-HLPR";

    private RecyclerViewSwipeListener listener;

    public RecyclerViewSwipeHelper(int dragDirs, int swipeDirs, RecyclerViewSwipeListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (listener != null) {
            int position = viewHolder.getAdapterPosition();
            LogHelper.LogThreadId(logName, "Current Adapter position : " + position);
            listener.onSwipeCompletion(viewHolder, direction, position);
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        LogHelper.LogThreadId(logName, "Clearing foreground view");
        View foregroundView = ((RecyclerViewAdapter.RecyclerViewHolder) viewHolder).viewForeground;
        getDefaultUIUtil().clearView(foregroundView);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState, boolean isCurrentlyActive) {
        LogHelper.LogThreadId(logName, "OnChildDraw");
        View foregroundView = ((RecyclerViewAdapter.RecyclerViewHolder) viewHolder).viewForeground;
        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                float dX, float dY, int actionState, boolean isCurrentlyActive) {
        LogHelper.LogThreadId(logName, "OnChildDrawOver");
        View foregroundView = ((RecyclerViewAdapter.RecyclerViewHolder) viewHolder).viewForeground;
        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            LogHelper.LogThreadId(logName, "onSelectedChanged");
            View foregroundView = ((RecyclerViewAdapter.RecyclerViewHolder) viewHolder).viewForeground;
            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }
}
