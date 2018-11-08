package com.socServer.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class DataReceiver extends Thread{
    private Socket soc;
    private SeverConn sConn;
    private DataInputStream is;

    public DataReceiver(Socket soc, SeverConn sConn){
        this.soc = soc;
        this.sConn = sConn;

        try{
            is = new DataInputStream(this.soc.getInputStream());
            System.out.println("ServerConn : "+ is.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        super.run();
        while(sConn.ServerConnection){
            if (!sConn.ServerConnection){
                return;
            }
            try {
                System.out.println(is.readUTF());
            } catch (IOException e) {
                try {
                    is.close();
                } catch (IOException ee) {
                    ee.printStackTrace();
                }
            }
        }
        sConn.ServerDisconn();
    }
}
