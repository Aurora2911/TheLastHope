package com.adventure.graphic;

/**
 * Reacts to the item event.
 */
public abstract class MyActionListenerOnObject<T> extends MyActionListeners {
    private T myObj;
    private ChoiceFrame myChoiceFrame;

    public MyActionListenerOnObject(InitialFrame jf, T obj, ChoiceFrame cf) {
        super(jf);
        this.myObj = obj;
        this.myChoiceFrame = cf;
    }

    public T getMyObj() {
        return myObj;
    }

    public ChoiceFrame getMyChoiceFrame() {
        return myChoiceFrame;
    }
}
