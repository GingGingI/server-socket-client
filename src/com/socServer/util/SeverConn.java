package com.socServer.util;

import com.socServer.Ref.ServerRef;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SeverConn {

    private Socket soc;
    private DataOutputStream os;
    boolean ServerConnection = true;

    protected void ServerConn(){
        System.out.println("serverConn IP : " + ServerRef.serverIP);

        try{
            soc = new Socket(ServerRef.serverIP, ServerRef.port);
            os = new DataOutputStream(soc.getOutputStream());

            DataReceiver DR = new DataReceiver(soc, this);
            new Thread(() ->{DR.run();}).start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void StopGetting(){
        ServerConnection = false;
    }

    void ServerDisconn(){
        try {
            System.out.println("연결종료.");

            os.close();
            soc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void Datasend(byte bt,String text) {
        if (os != null) {
            try {
                os.writeByte(bt);//데이터가 무슨데이터를 보내는지 구별자
                os.writeUTF(text);//데이터
                os.flush();//보낸후 os 정리
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
