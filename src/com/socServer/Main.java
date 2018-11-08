package com.socServer;

import com.socServer.util.*;
import java.util.Scanner;

public class Main extends SeverConn {

    public static void main(String[] args) {
        Main m = new Main();
        m.serverConnector();
    }

    private void serverConnector() {
        Scanner sc = new Scanner(System.in);

        ServerConn();
        while(true){
            String getText = sc.next();

            if (getText.equalsIgnoreCase("disconnect")){
                Datasend((byte) -1,getText);
                StopGetting();
                return;
            }else if(getText.equalsIgnoreCase("ping")){
                Datasend((byte) 2,getText);
            }else{
                Datasend((byte) 1,getText);
            }
        }
    }
}
