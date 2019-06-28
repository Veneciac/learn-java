package com.gmail.veneciacalista.helper;

import android.view.MotionEvent;

public class CustomListener {

    public interface Objectlistener{
        public void onItemSelected(Item item);
        public void onTouchDown(MotionEvent event, String text);
        public void onTouchUp(MotionEvent event, String text);
    }

    // Step 2- This variable represents the listener passed in by the owning object
    // The listener must implement the events interface and passes messages up to the parent.
    private Objectlistener listener;

    // Constructor where listener events are ignored
    public CustomListener() {
        this.listener = null; // set null listener
    }

    // Assign the listener implementing events interface that will receive the events (passed in by the owner)
    public void setObjectListener(Objectlistener listener) {
        this.listener = listener;
    }

    // This is a hypothetical method that gets called when the object is touched
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            // Step 3 - Fire listener event to communicate to parent
            listener.onTouchUp(event, "UP");
            return false;
        } else {
            // Fire listener event to communicate to parent
            listener.onTouchDown(event, "DOWN");
            return true;
        }
    }

    // Hypothetical item
    public class Item {
        // ... fields and methods...
    }
}
