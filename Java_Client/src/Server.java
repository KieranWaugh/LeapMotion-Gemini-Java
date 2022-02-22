
import java.net.*;
import java.io.*;

import java.time.Instant;
import java.util.ArrayList;
import com.google.gson.Gson;

@Deprecated
public class Server extends Thread {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private Gson gson = new Gson();
    private int port;
    private Frame currentFrame;
    public Server(int port){
        this.port = port;
        try{
            serverSocket = new ServerSocket(this.port);
            clientSocket = serverSocket.accept();
            System.out.println("Accepted Client: " + clientSocket.toString());
            out = new PrintWriter(clientSocket.getOutputStream(), true);


        }catch(Exception e){

            System.out.println(e);
        }
    }


    /**
     * Starts the socket server to receive data from Unity.
     *
     */


    /**
     *
     * @throws IOException
     */
    public void run() {
        try{
            InputStream inStream = clientSocket.getInputStream();
            InputStreamReader inStreamReader = new InputStreamReader(inStream);
            BufferedReader reader= new BufferedReader(inStreamReader);
            while(clientSocket.isConnected()) {
                String input = reader.readLine();
                if(input != null){
                    currentFrame = gson.fromJson(input, Frame.class);
                }

            }
        }catch (IOException e){
            this.stop();
        }
// [86, 72, 21, 29, 33, 37, 43, 49, 54, 60]
    }

    /**
     * Gets the current Leap Motion Frame Object from Unity
     * @return The current frame received from Unity
     */
    public Frame getCurrentFrame(){
        //System.out.println("Returned Frame: " + currentFrame);
        return currentFrame;

    }
}
