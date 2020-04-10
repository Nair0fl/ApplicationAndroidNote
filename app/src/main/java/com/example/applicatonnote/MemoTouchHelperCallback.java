package com.example.applicatonnote;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import static androidx.recyclerview.widget.ItemTouchHelper.Callback.makeMovementFlags;

public class MemoTouchHelperCallback extends ItemTouchHelper.Callback{
    private MemosAdapter adapter;
    // Constructeur.
    public MemoTouchHelperCallback(MemosAdapter adapter)
    {
        this.adapter = adapter;
    }
    @Override
    public boolean isLongPressDragEnabled() { return true; }
    @Override
    public boolean isItemViewSwipeEnabled() { return true; }
    @Override
    public int getMovementFlags( RecyclerView recyclerView, ViewHolder viewHolder)
    {
        int dragFlagsUpDown = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int dragFlagsLeftRight = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlagsUpDown, dragFlagsLeftRight);
    }
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          ViewHolder target)
    {
        adapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }
    @Override
    public void onSwiped(ViewHolder viewHolder, int direction)
    {
        adapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

}
