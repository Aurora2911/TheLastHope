package com.adventure.net;

import java.io.Closeable;

/**
 * Implements the generic client operations.
 */
interface Client extends Communicable {
    public void connect() throws Exception;

    public void close();

    public Object serverHandler();

    public Closeable getFileDescriptor();
}