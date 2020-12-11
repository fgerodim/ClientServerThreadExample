package client;
// Client code:
import java.net.*;
import java.io.*;

class Client {
    public static void main (String args[]) {
        //Define domain and port for connect to the socket. The client only can receive 
        //messages because it uses only input stream
        String domain="localhost";
        int port=44;
        try {
            Socket mySocket = new Socket(domain,port);
            //Message that informs us when it connects correctly
            System.out.println("Connected to " + mySocket.getInetAddress() + ":" + mySocket.getPort()); 
            DataInputStream myInputStream = new DataInputStream(mySocket.getInputStream());
            //Read from the input stream UTF-8 encoding message that sends the server
            String response = myInputStream.readUTF();
            //Shows us the received message and close the socket finishing communication
            System.out.println("Received: "+response);
            mySocket.close();
        }
        catch (EOFException e){
            System.out.println("EOFException: " + e);
        }
        catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
