package com.adventure.net;

import java.net.*;

import com.adventure.crypto.XorCrypter;
import com.adventure.error.Fatal;
import java.io.*;

public class MyServer implements Server {
    private ServerSocket ss;
    private Socket fdSocket;
    final Short port = 31337;

    MyServer() throws IOException {
        this.listen();
        ss.setReuseAddress(true);
    }

    public InetAddress accept() {
        try {
            this.fdSocket = ss.accept();
            return this.fdSocket.getInetAddress();
        } catch (IOException e) {
            Fatal.errorHandle(e.getMessage());
            return null;
        }
    }

    public <T> void send(T obj) {
        try {
            new ObjectOutputStream(this.fdSocket.getOutputStream()).writeObject(obj);
        } catch (IOException e) {
            Fatal.errorHandle(e.getMessage());
        }
    }

    public Object recv() {
        try {
            return new ObjectInputStream(this.fdSocket.getInputStream()).readObject();
        } catch (ClassNotFoundException | IOException e) {
            Fatal.errorHandle(e.getMessage());
            return null;
        }
    }

    public void close() {
        try {
            if (this.ss.isClosed() == false)
                this.ss.close();
            if (this.fdSocket.isClosed() == false)
                this.fdSocket.close();
        } catch (IOException e) {
            Fatal.errorHandle(e.getMessage());
        }
    }

    /**
     * Handle to client
     */
    @Override
    public Object clientHandler() {
        while (true) {
            this.accept();

            new Thread() {
                @Override
                public void run() {
                    try {
                        MyServer.this.task();
                    } catch (Exception e) {
                        Fatal.errorHandle(e.getMessage());
                    }
                }
            }.start();
        }
    }

    public void task() {
        Object obj = this.recv();
        if (obj != null)
            this.send(obj.equals(XorCrypter.hexCrypto("ea94d0fbf794fbe190d7ddfbe894c8", 0xa4)));
    }

    @Override
    public ServerSocket getFileDescriptor() {
        return this.ss;
    }

    @Override
    public void listen() {
        try {
            this.ss = new ServerSocket(port, 0, null);
        } catch (IOException e) {
            Fatal.log(e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            MyServer ms = new MyServer();
            ms.clientHandler();
            ms.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}