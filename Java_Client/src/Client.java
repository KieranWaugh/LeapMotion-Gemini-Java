import com.google.gson.Gson;

import java.net.*;
import java.io.*;

public class Client extends Thread{

    private Socket clientSocket;
    private InputStream in;
    private Gson gson = new Gson();
    private int port;
    private Frame currentFrame;
    private boolean close = false;

    public Client(int port) {

        try{
            this.port = port;
            clientSocket = new Socket("127.0.0.1", port);
            in = clientSocket.getInputStream();
            System.out.println("Connected to Server: " + clientSocket.toString());

        }catch(Exception e){
            System.out.println(e);
        }

    }

    public void run() {
        try{
            InputStreamReader inStreamReader = new InputStreamReader(in);
            BufferedReader reader= new BufferedReader(inStreamReader);
            while(clientSocket.isConnected() && !close) {
                String input = reader.readLine();
                if(input != null){
                    currentFrame = gson.fromJson(input, Frame.class);
                }

            }
        }catch (IOException e){
            this.stop();
        }
    }

    public void close() { // when exiting the program you MUST call close
        try{
            close = true;
            clientSocket.close();

        }catch (Exception e){

        }

    }

    public Frame getCurrentFrame(){
        return currentFrame;

    }

}
