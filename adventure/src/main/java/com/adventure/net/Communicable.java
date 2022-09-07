package com.adventure.net;

/**
 * A Communicable object represents an object able to exchange data with any
 * others, like a pipeline.
 */
public interface Communicable {
    public <T> void send(T obj);

    public Object recv();
}