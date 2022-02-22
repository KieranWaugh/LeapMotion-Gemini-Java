using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Leap;
using System.IO;
using System.Net.Sockets;
using System;

public class Client : MonoBehaviour
{
    Controller controller;
    long prevID = 0;
    StreamWriter writer;
    NetworkStream stream;
    TcpClient tcpClient;
    DateTime epochStart;
    bool isConnected = false;

    // Start is called before the first frame update
    void Start()
    {
        tcpClient = new TcpClient(); 
        controller = new Controller();
        Connect();

    }

    // Update is called once per frame
    void Update()
    {
        isConnected = Connect();
        if (isConnected)
        {
            Frame frame = controller.Frame();
            Debug.Log(frame.ToString());
            long timestamp = DateTimeOffset.Now.ToUnixTimeMilliseconds();
            frame.Timestamp = timestamp;
            prevID = frame.Id;
            string output = JsonUtility.ToJson(frame);
            output.Insert(output.Length, "\n");
            send(output);
        }
        

    }

    private bool Connect()
    {
        try
        {
            tcpClient.Connect("127.0.0.1", 6666);
            stream = tcpClient.GetStream();
            //stream.ReadTimeout = 10;
            writer = new StreamWriter(stream);
            isConnected = true;
            return true;
        }
        catch (SocketException e)
        {
            Debug.Log(e.SocketErrorCode);

            if(e.ToString() == "SocketException: An existing connection was forcibly closed by the remote host.")
            {
                isConnected = false;
                tcpClient.Close();
                return false;
            }

           if(e.SocketErrorCode == SocketError.IsConnected)
            {
                isConnected = true;
                return true;
            }
            else
            {
                isConnected = false;
                tcpClient.Close();
                return false;
            }

            
            
        }

    }

    private void send(string json)
    {
        writer.WriteLine(json);
        writer.Flush();
    }

   
}
