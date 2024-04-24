/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Admin
 */
public class ClientHandle implements Runnable{
    private Socket socket;
    private Server server1;
    private String id;
    private InputStream input;
    private OutputStream output;

    public ClientHandle(Socket socket, String id, Server server) {
        this.socket = socket;
        this.server1 = server;
        this.id = id;
         try {
            this.input = socket.getInputStream();
            this.output = socket.getOutputStream();
        } catch (Exception e) {
        e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Server getServer1() {
        return server1;
    }

    public void setServer1(Server server1) {
        this.server1 = server1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public InputStream getInput() {
        return input;
    }

    public void setInput(InputStream input) {
        this.input = input;
    }

    public OutputStream getOutput() {
        return output;
    }

    public void setOutput(OutputStream output) {
        this.output = output;
    }
    @Override
    public void run() {
        try {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while((bytesRead = input.read(buffer)) != -1){
                String mess = new String(buffer, 0, bytesRead);
                server1.broadcastMessage(this.id, mess);
            }
        } catch (Exception e){
        e.printStackTrace();
        }
    }
    public void sendMess(String mess){
        try {
            output.write(mess.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
}
