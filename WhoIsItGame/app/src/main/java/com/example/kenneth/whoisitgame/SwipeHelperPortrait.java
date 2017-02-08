package com.example.kenneth.whoisitgame;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Kenneth on 28/01/2017.
 */

public class SwipeHelperPortrait extends ItemTouchHelper.SimpleCallback {
    PersoonAdapterPortrait persoonAdapterPortrait;

    public SwipeHelperPortrait(PersoonAdapterPortrait persoonAdapterPortrait){
        super(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);
        this.persoonAdapterPortrait = persoonAdapterPortrait;
    }
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        persoonAdapterPortrait.dismiss(viewHolder.getAdapterPosition());
    }
}
