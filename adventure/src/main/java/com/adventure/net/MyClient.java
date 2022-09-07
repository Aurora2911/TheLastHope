package com.adventure.net;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient implements Client {

    private Socket socketfd;
    private String msgToServer;
    final Short port = 31337;

    public MyClient(String msg) throws Exception {
        this.connect();
        this.msgToServer = msg;
    }

    @Override
    public void connect() throws UnknownHostException, IOException {
        this.socketfd = new Socket(InetAddress.getLocalHost().getHostName(), port);
    }

    @Override
    public <T> void send(T obj) {
        try {
            new ObjectOutputStream(this.socketfd.getOutputStream()).writeObject(obj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Object recv() {
        try {
            return new ObjectInputStream(this.socketfd.getInputStream()).readObject();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void close() {
        try {
            this.socketfd.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Closeable getFileDescriptor() {
        return this.socketfd;
    }

    @Override
    public Object serverHandler() {
        this.send(this.msgToServer);
        return this.recv();
    }

    public String getMsgToServer() {
        return msgToServer;
    }

    public void setMsgToServer(String msgToServer) {
        this.msgToServer = msgToServer;
    }
}
