package com.example.kenneth.whoisitgame;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Kenneth on 24/01/2017.
 */

public class SwipeHelper extends ItemTouchHelper.SimpleCallback {
    PersoonAdapterLandscape persoonAdapterLandscape;

    public SwipeHelper(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    public SwipeHelper(PersoonAdapterLandscape persoonAdapterLandscape) {
        super(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);
        this.persoonAdapterLandscape = persoonAdapterLandscape;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        persoonAdapterLandscape.dismiss(viewHolder.getAdapterPosition());
    }
}
