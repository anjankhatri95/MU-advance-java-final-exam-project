import java.io.IOException;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.util.Scanner;
import java.net.InetAddress;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CreditCardServer{
    private ServerSocket serversocket;
    private Socket socket;
    private ObjectInputStream inputstream;
    private ObjectOutputStream outputstream;
    private Scanner scanner;
    
  public CreditCardServer(){
    System.out.println("Server is running!!!");
    try{
     serversocket= new ServerSocket(1098,500);
     while(true){
        socket=serversocket.accept();
        inputstream= new ObjectInputStream(socket.getInputStream());
        String Cardnumber=(String) inputstream.readObject();
        String expire = (String) inputstream.readObject();//added
        System.out.println(Cardnumber+expire);
        
        String url="jdbc:mysql://localhost/hotel?user=root&password=";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn= DriverManager.getConnection(url);
        Statement stmt= conn.createStatement();
        String select= "Select creditcard from creditcard where creditcard='" +Cardnumber+expire+"' ";
        ResultSet result= stmt.executeQuery(select);
        
        String message= "Your Reservation cannot be made due to an invalid credit card";
        
        if(result.next()){
          message="yes";
          //message= Cardnumber+expire;
          System.out.println("Credit card validation: "+Cardnumber+expire+" Yes");
        }else{
         System.out.println("Credit card validation: "+Cardnumber+expire+ " No"); 
        }
       
        socket = new Socket(InetAddress.getByName("localhost"),1099);
        outputstream= new ObjectOutputStream(socket.getOutputStream());
        outputstream.writeObject(message);
        outputstream.flush();
        
     }
     
      
    }
    catch(IOException ioe){
      ioe.printStackTrace(); 
    }catch(ClassNotFoundException cnfe){
     cnfe.printStackTrace(); 
    }catch(SQLException sqle){
     sqle.printStackTrace(); 
    }
    System.out.println("Server Stopped!!!");
  }
  public static void main(String [] args){
   CreditCardServer CCnetwrok=new CreditCardServer(); 
  }
}