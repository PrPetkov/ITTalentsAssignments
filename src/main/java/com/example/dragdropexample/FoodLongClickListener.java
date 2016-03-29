package com.example.dragdropexample;

import android.content.ClipData;
import android.content.ClipDescription;
import android.view.View;

/**
 * Created by Pipi on 28.3.2016 г..
 */
public class FoodLongClickListener implements View.OnLongClickListener {

    String mimeType;

    public FoodLongClickListener(String mimeType){
        this.mimeType = mimeType;
    }

    @Override
    public boolean onLongClick(View v) {
        // Create a new ClipData.
        // This is done in two steps to provide clarity. The convenience method
        // ClipData.newPlainText() can create a plain text ClipData in one step.

        // Create a new ClipData.Item from the ImageView object's tag
        ClipData.Item item = new ClipData.Item("food");

        // Create a new ClipData using the tag as a label, the plain text MIME type, and
        // the already-created item. This will create a new ClipDescription object within the
        // ClipData, and set its MIME type entry to "text/plain"
        ClipData dragData = new ClipData("food", new String[]{this.mimeType},item);

        // Instantiates the drag shadow builder.
        View.DragShadowBuilder myShadow = new FoodDragShadowBuilder(v);

        // Starts the drag

        v.startDrag(dragData,  // the data to be dragged
                myShadow,  // the drag shadow builder
                null,      // no need to use local data
                0          // flags (not currently used, set to 0)
        );

        return true;
    }
}
