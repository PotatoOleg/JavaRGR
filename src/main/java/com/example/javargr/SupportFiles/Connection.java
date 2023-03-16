package com.example.javargr.SupportFiles;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection implements Closeable {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Connection(Socket socket) throws IOException{
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());

    }

    public void Send(Message message) throws IOException{
        synchronized (this.out){
            out.writeObject(message);
        }
    }

    public Message Receive() throws  IOException,ClassNotFoundException{
        synchronized (this.in){
            Message message = (Message) in.readObject();
            return message;
        }
    }

    @Override
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}
