import javax.swing.JFrame;  //gives GUI frame
import javax.swing.JLabel; //gives label
import javax.swing.JPanel; //add panel
import javax.swing.JTextField;//add text field
import javax.swing.JTextArea;//add text area
import javax.swing.JComboBox; // add dropbox
import javax.swing.JCheckBox; //add check box
import javax.swing.ImageIcon; //add icons
import javax.swing.ButtonGroup; //add button group for single selection
import javax.swing.JRadioButton; //adding from circular button
import javax.swing.JButton; //adding buttons;
import java.awt.BorderLayout; //layout
import java.awt.GridLayout;//griding layout
import java.awt.FlowLayout;//importing flow layout
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

//Database importing
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//networking importing
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.net.ServerSocket;
import java.io.ObjectInputStream;
import java.net.UnknownHostException;


public class HotelReservationWdb extends JFrame implements ActionListener {
    private JLabel FirstNamelabel,LastNamelabel,Checkindate,Checkoutdate,NuOfRoom,GuestRoom,
                    Specialrates,RoomType,Request,biling,street,city,state,zip,phone,creditinfo,cardnu,expdate,space,
                     Visalabel,Mclabel,Aelabel,Dislabel;
    private JTextField firstnameTF,lastnameTF,checkinTF,checkoutTF,streetTF,cityTF,zipTF,
                        phoneTF,cardnuTF,expdateTF;
    private JPanel northpanel, westpanel,westpanel1,westpanel2,centerpanel,centerpanel1,centerpanel2,
                      centerpanel3,centerpanel4, southpanel;
    private JComboBox roomsdrop,Guestroomdrop,statedrop;
    private JRadioButton None,AAA,Seniordiscount,GvtandMilt,Visa,Mc,Ae,Dis;
    private JCheckBox stu,std,fam,suit;
    private ButtonGroup rates,cards;
    private JTextArea request;
    private ImageIcon VisaIcon, McIcon, AeIcon,DisIcon;
    private JButton Submitbutton, Cancelbutton;
    
    private Socket socket;
    private ObjectOutputStream outputStream;
    private Scanner scanner;
    private ServerSocket serverSocket;
    private ObjectInputStream inputStream;
    
    
  public HotelReservationWdb(){
      super("Hotel Reservation System");
      
      try{
       serverSocket= new ServerSocket(1099,500); 
      }catch(IOException ioe){
        ioe.printStackTrace(); 
      }
             //Labeling name and textfield
              FirstNamelabel= new JLabel("First Name: ");
              firstnameTF= new JTextField(10);
              LastNamelabel= new JLabel("Last Name: ");
              lastnameTF= new JTextField(10);
//North panel firstname and last name

northpanel= new JPanel();
northpanel.add(FirstNamelabel);
northpanel.add(firstnameTF);
northpanel.add(LastNamelabel);
northpanel.add(lastnameTF);
add(northpanel, BorderLayout.NORTH);

         //Checkin and check out on west panel

             Checkindate= new JLabel("Check-in date (mm/dd/yy)");
               checkinTF=new JTextField(8);
             Checkoutdate= new JLabel("Check-out date(mm/dd/yy)");
               checkoutTF= new JTextField(8); 
        //Number of rooms drop box     
              NuOfRoom= new JLabel("No. of rooms"); 
              String NoOfRoom []={"1","2","3","4-9","10-25","26+"};
              roomsdrop=new JComboBox(NoOfRoom);
        //guest room drop box
               GuestRoom= new JLabel("Guests/Room");
               String GuestRoomlist []={"1","2","3","4","5","6"};
               Guestroomdrop= new JComboBox(GuestRoomlist);
       //discount in radiobutton
               Specialrates= new JLabel("SPECIAL RATES");
               None = new JRadioButton("None");
               AAA=new JRadioButton("AAA/CAA");
               Seniordiscount= new JRadioButton("Senior Discount");
               GvtandMilt= new JRadioButton("Government & Military");
     //grouping of radiobuttons;
               rates= new ButtonGroup();
               rates.add(None);
               rates.add(AAA);
               rates.add(Seniordiscount);
               rates.add(GvtandMilt);
     //checkbox of room type 
               RoomType= new JLabel("Room Types");
               stu= new JCheckBox("Studio");
               std= new JCheckBox("Standard");
               fam= new JCheckBox("Family");
               suit= new JCheckBox("Suite");
      //special request text area
               Request= new JLabel("Special Request: ");
               request= new JTextArea(4,5);
  
 westpanel= new JPanel();
 westpanel1=new JPanel();
 westpanel2= new JPanel();
 westpanel.add(Checkindate);
 westpanel.add(Checkoutdate);
 westpanel.setLayout(new GridLayout(0,2));
 westpanel.add(checkinTF);
 westpanel.add(checkoutTF);
 westpanel.add(NuOfRoom);
 westpanel.add(GuestRoom);
 westpanel.add(roomsdrop);
 westpanel.add(Guestroomdrop);
 westpanel.add(Specialrates);
 westpanel.add(RoomType);
 westpanel.add(None);
 westpanel.add(stu);  
 westpanel.add(AAA);
 westpanel.add(std);
 westpanel.add(Seniordiscount);
 westpanel.add(fam);
 westpanel.add(GvtandMilt);
 westpanel.add(suit);
 westpanel1.add(westpanel);
 westpanel1.setLayout(new GridLayout(0,1));
 westpanel.add(Request);
 westpanel1.add(request);
 westpanel2.add(westpanel1);
 add(westpanel2, BorderLayout.WEST);
      
              //Center panel of textfields
                 biling= new JLabel("Billing Address: ");
                 space=new JLabel(" ");
                 street= new JLabel("Street: ");
                 streetTF= new JTextField(20);
                 city= new JLabel("City: ");
                 cityTF= new JTextField(20);
              //drop box states
                 state=new JLabel("States: ");
                 String statebox [] = {"DC","VA","MD"};
                 statedrop= new JComboBox(statebox);
              //text fields
                  zip= new JLabel("Zip: ");
                  zipTF= new JTextField(10);
                  phone= new JLabel("Phone: ");
                  phoneTF= new JTextField(10);
              //credit information visa radiobuttons
                 creditinfo=new JLabel("Credit Card Information: ");
                 Visa= new JRadioButton("");
                 VisaIcon= new ImageIcon("visa.gif");
                 Visalabel= new JLabel(VisaIcon);
                 //credit card infromation mastercard radiobuttons
                  Mc= new JRadioButton("");
                 McIcon= new ImageIcon("master.gif");
                 Mclabel= new JLabel(McIcon);
                 //credit card Amex radiobuttons
                 Ae=new JRadioButton("");
                 AeIcon= new ImageIcon("amex.gif");
                 Aelabel=new JLabel(AeIcon);
                 //credit card discover radiobuttons
                 Dis= new JRadioButton("");
                 DisIcon= new ImageIcon("discover.gif");
                 Dislabel= new JLabel(DisIcon);
                 //button grouping
                 cards= new ButtonGroup();
                 cards.add(Visa);
                 cards.add(Mc);
                 cards.add(Ae);
                 cards.add(Dis);
             //textfield of card information 
              cardnu= new JLabel("Credit Card Number: ");
              cardnuTF= new JTextField(20);
              expdate= new JLabel("Expiration date (mm/yy): ");
              expdateTF=new JTextField(5);
      //center panel organization 
    centerpanel=new JPanel();
    centerpanel1=new JPanel();
    centerpanel2=new JPanel();
    centerpanel3= new JPanel();
    centerpanel4= new JPanel();
        //Laying out address  
centerpanel.setLayout(new GridLayout(0,2));
        centerpanel.add(biling);
        centerpanel.add(space);
        centerpanel.add(street);
        centerpanel.add(streetTF);
        centerpanel.add(city);
        centerpanel.add(cityTF);
        centerpanel.add(state);
        centerpanel.add(statedrop);
        centerpanel.add(zip);
        centerpanel.add(zipTF);
        centerpanel.add(phone);
        centerpanel.add(phoneTF);
   //Laying out Icons
       
  centerpanel1.setLayout(new FlowLayout());
        centerpanel1.add(creditinfo);
        centerpanel1.add(Visa);
        centerpanel1.add(Visalabel);
        centerpanel1.add(Mc);
        centerpanel1.add(Mclabel);
        centerpanel1.add(Ae);
        centerpanel1.add(Aelabel);
        centerpanel1.add(Dis);
        centerpanel1.add(Dislabel);
        centerpanel2.add(centerpanel);
        centerpanel2.add(centerpanel1);
   add(centerpanel2, BorderLayout.CENTER);
   
  //layout of credit car number and expiration
   centerpanel3.setLayout(new FlowLayout());
        centerpanel3.add(cardnu);
        centerpanel3.add(cardnuTF);
        centerpanel3.add(expdate);
        centerpanel3.add(expdateTF);
        centerpanel4.add(centerpanel);
        centerpanel4.add(centerpanel1);
        centerpanel4.add(centerpanel2);
        centerpanel4.add(centerpanel3);
        add(centerpanel4, BorderLayout.CENTER);
      
    //submit and cancel button
        southpanel= new JPanel();
        Submitbutton= new JButton("Submit");
          Submitbutton.addActionListener(this);
        Cancelbutton= new JButton("Cancel");
          Cancelbutton.addActionListener(this);
        southpanel.add(Submitbutton);
        southpanel.add(Cancelbutton);
        add(southpanel, BorderLayout.SOUTH);
        
        
        
    setSize(900,600);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  public void actionPerformed(ActionEvent event){
    if(event.getSource()== Submitbutton){
       String firstname= firstnameTF.getText();
       String lastname= lastnameTF.getText();
       String indate= checkinTF.getText();
       String outdate=checkoutTF.getText();
       String rooms= roomsdrop.getSelectedItem().toString();
       String guestroom= Guestroomdrop.getSelectedItem().toString();
       String specialrequest= request.getText();
       String address= streetTF.getText();
       String city= cityTF.getText();
       String zip= zipTF.getText();
       String phone=phoneTF.getText();
       String state=statedrop.getSelectedItem().toString();
       String cardnumber=cardnuTF.getText();
       String expire=expdateTF.getText();
       
    int gperroom=Integer.parseInt(guestroom);
    
       try {
       //rate radiobutton
       String specialrate= "None";
       if(None.isSelected()){
         specialrate="None"; 
       }else if(AAA.isSelected()){
         specialrate="AAA/CAA"; 
       }else if(Seniordiscount.isSelected()){
          specialrate="Senior Discount"; 
         }else if(GvtandMilt.isSelected()){
           specialrate="Government & Military";
         }
         
         //room check box
         String studio="False";
         if(stu.isSelected()){
           studio="True";
         }
         String standard="False";
         if(std.isSelected()){
            standard ="True"; 
         }
         String family= "False";
         if(fam.isSelected()){
           family="True";
         }
         String suite="False";
         if(suit.isSelected()){
           suite="True"; 
         }
         //Credit card selection
         String creditcard="Card Unknown";
           if(Visa.isSelected()){
             creditcard="Visa"; 
         }else if(Mc.isSelected()){
              creditcard="Master"; 
             }else if(Ae.isSelected()){
               creditcard="American Express"; 
              }else if(Dis.isSelected()){
                creditcard="Discover"; 
               }
         
          //networking code
         socket = new Socket(InetAddress.getByName("localhost"),1098);
         outputStream=new ObjectOutputStream(socket.getOutputStream());
         outputStream.writeObject(cardnumber);
         outputStream.writeObject(expire);
         outputStream.flush();
          
         
         socket = serverSocket .accept();
         inputStream= new ObjectInputStream(socket.getInputStream());
         String message=(String) inputStream.readObject();            
           
        //output (cardnumber+expire)
  if(message.equals("yes")){
      //insert into the database     
         String url="jdbc:mysql://localhost/hotel?user=root&password="; //sql url
         Class.forName("com.mysql.jdbc.Driver");//driver
         Connection connection = DriverManager.getConnection(url); //connecting to db
         Statement stmt= connection.createStatement();
         String insert= "INSERT INTO reservation VALUES('"+firstname+"','"+lastname+"','"+indate+"','"+outdate+
           "','"+rooms+"','"+gperroom+"','"+specialrate+"','"+studio+"','"+standard+"','"+family+"','"+suite+
           "','"+specialrequest+"','"+address+"','"+city+"','"+state+"','"+zip+"','"+phone+"','"+creditcard+"','"+cardnumber+"','"+expire+"')";
         stmt.execute(insert);
            
        String output= "Your reservation has been successfully submitted"+"\nFirst Name: "+firstname+ "\nLast Name: "+lastname+ "\nCheck-in Date: "+indate+
                       "\nCheck-out Date: "+outdate+ "\nNo. of rooms: "+rooms+ "\nGuest/Room: "+gperroom+
           "\nSpecial Rate: "+specialrate+ "\nRoom Types: \n    Studio: "+studio+ "\n    Standard: "+standard+ "\n    Family: "+family+ 
                       "\n    Suite: "+suite+ "\nSpecial Request: \n" +specialrequest+ "\nBiling Address: \n"
                       +address+ "\n" +city+", "+state+" "+zip+ "\nPhone: "+phone+ "\nCredit Card: "+creditcard+
                       "\nCredit Card Number: "+cardnumber+ "\nExpiration: "+expire;
         JOptionPane.showMessageDialog(null,output);
         }else{
           JOptionPane.showMessageDialog(null,message);
         }
       }catch(ClassNotFoundException cnfe){
           cnfe.printStackTrace(); 
       }
       catch(SQLException sqle){
        sqle.printStackTrace(); 
       }catch(IOException ioe){
        ioe.printStackTrace(); 
       }
       
    }else if(event.getSource()==Cancelbutton){
      //erasing textfields 
      firstnameTF.setText("");
       lastnameTF.setText("");
       checkinTF.setText("");
       checkoutTF.setText("");
       streetTF.setText("");
       cityTF.setText("");
       zipTF.setText("");
       phoneTF.setText("");
       cardnuTF.setText("");
       expdateTF.setText("");
       request.setText("");
       //erasing check boxes
       stu.setSelected(false);
       std.setSelected(false);
       fam.setSelected(false);
       suit.setSelected(false);
       //erasing radio buttons
       rates.clearSelection();
       cards.clearSelection();
       //combo box first index
       roomsdrop.setSelectedIndex(0);
       Guestroomdrop.setSelectedIndex(0);
       statedrop.setSelectedIndex(0);
    }
    
    
  }
  
  
  public static void main(String [] args){
   HotelReservationWdb app= new HotelReservationWdb(); 
  }
}