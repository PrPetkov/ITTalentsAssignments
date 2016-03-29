package com.example.dragdropexample;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Pipi on 28.3.2016 г..
 */
public class FoodOnDragListener implements View.OnDragListener {

    private int grumpyPictureID;
    private int reachingPictureID;
    private int relaxingPictureID;
    private int defaultPictureID;
    private String mimeType;

    FoodOnDragListener(int defaultPictureID, int grumpyPictureID, int reachingPictureID, int relaxingPictureID, String mimeType){
        this.grumpyPictureID = grumpyPictureID;
        this.reachingPictureID = reachingPictureID;
        this.relaxingPictureID = relaxingPictureID;
        this.defaultPictureID = defaultPictureID;
        this.mimeType = mimeType;
    }

    // This is the method that the system calls when it dispatches a drag event to the
    // listener.
    public boolean onDrag(View v, DragEvent event) {

        // Defines a variable to store the action type for the incoming event
        final int action = event.getAction();

        // Handles each of the expected events
        switch(action) {

            case DragEvent.ACTION_DRAG_STARTED:

                // Determines if this View can accept the dragged data
                if (event.getClipDescription().hasMimeType(mimeType)) {

                    // As an example of what your application might do,
                    // applies a blue color tint to the View to indicate that it can accept
                    // data.
                    ((ImageView)v).setImageResource(this.defaultPictureID);

                    // Invalidate the view to force a redraw in the new tint
                    v.invalidate();

                    // returns true to indicate that the View can accept the dragged data.
                    return true;

                } else {
                    ((ImageView)v).setImageResource(this.grumpyPictureID);

                    // Invalidate the view to force a redraw in the new tint
                    v.invalidate();

                    // Returns false. During the current drag and drop operation, this View will
                    // not receive events again until ACTION_DRAG_ENDED is sent.
                    return false;
                }



            case DragEvent.ACTION_DRAG_ENTERED:

                // Applies a green tint to the View. Return true; the return value is ignored.

                ((ImageView)v).setImageResource(this.reachingPictureID);

                // Invalidate the view to force a redraw in the new tint
                v.invalidate();

                return true;

            case DragEvent.ACTION_DRAG_LOCATION:

                // Ignore the event
                return true;

            case DragEvent.ACTION_DRAG_EXITED:

                // Re-sets the color tint to blue. Returns true; the return value is ignored.
                ((ImageView)v).setImageResource(this.defaultPictureID);

                // Invalidate the view to force a redraw in the new tint
                v.invalidate();

                return true;

            case DragEvent.ACTION_DROP:

//                // Gets the item containing the dragged data
//                ClipData.Item item = event.getClipData().getItemAt(0);
//
//                // Gets the text data from the item.
//                dragData = item.getText();
//
//                // Displays a message containing the dragged data.
//                Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_LONG);

                // Turns off any color tints
                ((ImageView)v).setImageResource(this.relaxingPictureID);

                // Invalidates the view to force a redraw
                v.invalidate();

                // Returns true. DragEvent.getResult() will return true.
                return true;

            case DragEvent.ACTION_DRAG_ENDED:

                // Turns off any color tinting
//                v.clearColorFilter();

                // Invalidates the view to force a redraw


                // Does a getResult(), and displays what happened.
                if (!event.getResult()) {
                    ((ImageView)v).setImageResource(this.defaultPictureID);
                }

                v.invalidate();
                // returns true; the value is ignored.
                return true;

            // An unknown action type was received.
            default:
                Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                break;
        }

        return false;
    }

}
