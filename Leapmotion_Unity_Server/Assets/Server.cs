using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using UnityEngine;
using UnityEngine.UI;
using Leap;

public class Server : MonoBehaviour
{
    private TcpListener tcpListener;
    private Thread tcpListenerThread;
    private TcpClient connectedTcpClient;
    private Controller controller;
    private Text connected_Text;
    private Text info_Text;
    private long prevID = 0;
    private bool connected = false;
    // Start is called before the first frame update
    void Start()
    {
        tcpListenerThread = new Thread(new ThreadStart(ListenForIncommingRequests));
        tcpListenerThread.IsBackground = true;
        tcpListenerThread.Start();
        connected_Text = GameObject.Find("Canvas/Connected_Text").GetComponent<Text>();
        info_Text = GameObject.Find("Canvas/info_Text").GetComponent<Text>();

        controller = new Controller();
    }

    // Update is called once per frame
    void Update()
    {
        

        if (controller.IsConnected)
        {
            connected_Text.text = "Connected";
            connected_Text.color = Color.green;
            info_Text.text = "You can now minimise this window.";
            info_Text.color = Color.red;

            try
            {
                Frame frame = controller.Frame();
               
                long timestamp = DateTimeOffset.Now.ToUnixTimeMilliseconds();
                frame.Timestamp = timestamp;
                if (frame.Id != prevID)
                {
                    string output = JsonUtility.ToJson(frame);
                    output.Insert(output.Length, "\n");
                    //Debug.Log(frame.Hands[0].StabilizedPalmPosition);
                    prevID = frame.Id;
                    //Debug.Log(frame.Id);
                    send(output);

                }

            }
            catch (Exception e)
            {
                info_Text.text = e.Message;
                info_Text.color = Color.red;
                connected = false;

            }
        }
        else
        {
            connected_Text.text = "Not Connected";
            connected_Text.color = Color.red;
        }
        
            
        

    }

    private void ListenForIncommingRequests()
    {
        try
        {
            tcpListener = new TcpListener(IPAddress.Parse("127.0.0.1"), 6666);
            tcpListener.Start();
            connected = true;
            //Debug.Log("Server is listening");

            Byte[] bytes = new Byte[1024];
            while (true)
            {
                using (connectedTcpClient = tcpListener.AcceptTcpClient())
                {
                    Debug.Log(tcpListener.ToString());
                    // Get a stream object for reading 					
                    using (NetworkStream stream = connectedTcpClient.GetStream())
                    {
                        int length;
                        // Read incomming stream into byte arrary. 						
                        while ((length = stream.Read(bytes, 0, bytes.Length)) != 0)
                        {
                            var incommingData = new byte[length];
                            Array.Copy(bytes, 0, incommingData, 0, length);
                            // Convert byte array to string message. 							
                            string clientMessage = Encoding.ASCII.GetString(incommingData);
                            Debug.Log("client message received as: " + clientMessage);
                        }
                    }
                }
            }

        }
        catch (Exception e)
        {

            info_Text.text = e.Message;
            info_Text.color = Color.red;
            Debug.Log(e.Message);
        }
    }

    private void send(string json)
    {
        if (connectedTcpClient == null)
        {
            return;
        }

        try
        {
            // Get a stream object for writing. 			
            NetworkStream stream = connectedTcpClient.GetStream();
            StreamWriter writer = new StreamWriter(stream);
            if (stream.CanWrite)
            {
                writer.WriteLine(json);
                //Debug.Log(json);
                writer.Flush();

            }
        }
        catch (Exception e)
        {
            info_Text.text = e.Message;
            info_Text.color = Color.red;
            Debug.Log(e.Message);
        }
    }

}
