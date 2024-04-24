/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Client {
    public static final String URL = "localhost";
    private static final int Port = 5000;
    public void startClient(){
        try {
            Socket socket = new Socket("localhost", Port);
            System.out.println("Connected to server");
            
            // Liên tục đọc dữ liệu từ server
            ClientListener cl = new ClientListener(socket);
            new Thread(cl).start();
            // Liên tục đọc dữ liệu từ scanner
            OutputStream outputStream = socket.getOutputStream();
            Scanner sc = new Scanner(System.in);
            while(true){
                String mess = sc.nextLine();
                outputStream.write(mess.getBytes()); 
            }
        } catch (Exception e) {
         e.printStackTrace();
        }
    }
}
