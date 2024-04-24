/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Server {
    private static final int Port = 5000; // khởi tạo biến toàn cục cho port = 5000
    private List<ClientHandle> client = new ArrayList<>();
    
    public void startServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(Port);
            System.out.println("Server started. Listenning on port" + Port);
            // Client conect
            while (true) {                
                Socket clientSocket = serverSocket.accept(); // chấp nhận kết nối từ phía client
                System.out.println("New client connected: " + clientSocket.getInetAddress());
                ClientHandle clientHandle = new ClientHandle(clientSocket, System.currentTimeMillis()+ "",this);
                client.add(clientHandle);
                new Thread(clientHandle).start();
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    public void broadcastMessage(String id, String mess){
        for (ClientHandle clientHandle : client){
            if(!clientHandle.getId().equals(id))
             clientHandle.sendMess(id + ":" +mess);            
        }
    }
}
