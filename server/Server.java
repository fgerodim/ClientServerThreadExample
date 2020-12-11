package server;
// Server code:
import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    public static void main (String args[]) {
        try {
            //Create a socket in port 44
            int port=44;
            ServerSocket ls = new ServerSocket (port);
            //Endless loop that serves clients' requests  
            while (true){
                System.out.println("Server waiting request in port "+port);
                Socket cs = ls.accept();
                //This method is called whenever a client makes a request
                //The method creates a thread which processes the clients' request 
                Connection c= new Connection(cs);
            }
        }
        catch(IOException e){
            System.out.println("IOException: " + e);
        }
    }
}
class Connection extends Thread{
    DataOutputStream out;
    Socket cs;
    public Connection (Socket acs){
            try{
                this.cs=acs;
                this.out = new DataOutputStream(cs.getOutputStream());
                //Start method is used for start the thread. All the code of the 
                //thread it is in the run method. When run method finished, the thread 
                //dies.
                this.start();
            }
            catch(IOException e){
                System.out.println("Connection "+e.getMessage());
            }
        }
    //Thread's method
    public void run(){
        try{
            //Declare a date object in order to return the date event
            Date date = new Date();
            //use sleep method to delay the response
            try {
                sleep(this.randomInt(4)*1000);                 //1000 milliseconds is one second.
            } catch(InterruptedException ex) {
                System.out.println("Unable to sleep. Exception: "+ex);
            }
            //Convert date object to string 
            String data=date.toString();
            //and write it to the output stream using the method writeUTF
            this.out.writeUTF(data);
            this.cs.close();
        }
        catch(EOFException e){System.out.println(e.getMessage());}
        catch(IOException e){System.out.println(e.getMessage());}
    }
    //A simple method that returns an integer between 0 and less than upperNumber argument
    public int randomInt(int upperNumber){
        Random randomNumber = new Random();
        int delay = randomNumber.nextInt(upperNumber);
        return delay;
    }
}    
    
    
                        
