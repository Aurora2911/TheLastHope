package com.adventure.net;

import java.io.Closeable;
import java.net.InetAddress;

/**
 * Implements the generic server operations.
 */
public interface Server extends Communicable {
    public void listen();

    public InetAddress accept();

    public void close();

    public Object clientHandler();

    public Closeable getFileDescriptor();
}