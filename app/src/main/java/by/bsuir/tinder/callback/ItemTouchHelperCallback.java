/**
 * Created by Alexander Lomat
 */


package by.bsuir.tinder.callback;

import android.graphics.Canvas;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import by.bsuir.tinder.adapter.ItemTouchHelperAdapter;

import static androidx.recyclerview.widget.ItemTouchHelper.LEFT;
import static androidx.recyclerview.widget.ItemTouchHelper.RIGHT;

/**
 * The type Item touch helper callback.
 */
public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final ItemTouchHelperAdapter adapter;

    /**
     * Instantiates a new Item touch helper callback.
     *
     * @param adapter the adapter
     */
    public ItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView,
                                @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = 0;
        int swipeFlags = LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        switch (direction) {
            case LEFT:
                adapter.onItemSwipedLeft();
                break;
            case RIGHT:
                adapter.onItemSwipedRight();
                break;
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {

        viewHolder.itemView.setBackgroundColor(Color.WHITE);

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            float width = (float) viewHolder.itemView.getWidth();
            float alpha = 1.0f - Math.abs(dX) / width;
            if (dX < -1 && dX > viewHolder.itemView.getWidth() / -2f) {
                viewHolder.itemView.setBackgroundColor(Color.rgb(255, 0, 0));
            } else if (dX > 1 && dX < viewHolder.itemView.getWidth() / 2f) {
                viewHolder.itemView.setBackgroundColor(Color.rgb(0, 255, 0));
            }
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setTranslationX(dX);


        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY,
                    actionState, isCurrentlyActive);
            viewHolder.itemView.setBackgroundColor(Color.WHITE);
        }
    }
}
