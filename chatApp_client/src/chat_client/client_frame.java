package chat_client;

import java.awt.Color;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class client_frame extends javax.swing.JFrame 
{
   // static client_frame cf=new client_frame(String,String,String);
   
    String pfrom,pto;
     long startTime;
            long elapsedTime = 0L;
    boolean flag=false;
    int i=1;
    String level;
    String username,password, address = "gunjansharma1512sr.hopto.org";
    ArrayList<String> users = new ArrayList();
    int port = 443;
    Boolean isConnected = false;
    
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;

      int cnt=0;
      boolean first=true;
       String color;
        DefaultListModel demoList = new DefaultListModel();
//

    //--------------------------//
    
    public void ListenThread() 
    {
         Thread IncomingReader = new Thread(new IncomingReader());
         IncomingReader.start();
    }
    
    //--------------------------//
    
    public void userAdd(String data) 
    {
         users.add(data);
         

            

    }
    
    //--------------------------//
    
    public void userRemove(String data) throws BadLocationException
    {
         StyledDocument doc = jTextPane1.getStyledDocument();
        try {
            doc.insertString(doc.getLength(),data + " is now offline.\n", null);
        } catch (BadLocationException ex) {
            Logger.getLogger(client_frame.class.getName()).log(Level.SEVERE, null, ex);
        }
       /////////////........  ta_chat.append(data + " is now offline.\n");
    }
    
    //--------------------------//
    
    public void writeUsers() 
    {
         String[] tempList = new String[(users.size())];
         users.toArray(tempList);
         for (String token:tempList) 
         {
            //users.append(token + "\n");
         }
    }
    
    //--------------------------//

  
    
    public void sendDisconnect() 
    {
        String bye = (username + ": :Disconnect"+":"+" :"+level);
        try
        {
            writer.println(bye); 
            writer.flush(); 
        } catch (Exception e) 
        {
             StyledDocument doc = jTextPane1.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), "Could not send Disconnect message.\n", null);
        } catch (BadLocationException ex) {
            Logger.getLogger(client_frame.class.getName()).log(Level.SEVERE, null, ex);
        }
           //////////////////......... ta_chat.append("Could not send Disconnect message.\n");
        }
    }

    //--------------------------//
    
    public void Disconnect() 
    {
        try 
        {
             StyledDocument doc = jTextPane1.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), "Disconnected.\n", null);
        } catch (BadLocationException ex) {
            Logger.getLogger(client_frame.class.getName()).log(Level.SEVERE, null, ex);
        }
            ////////////////...............ta_chat.append("Disconnected.\n");
            sock.close();

        } catch(Exception ex) {

           StyledDocument doc = jTextPane1.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), "Failed to disconnect.\n", null);
            ex.printStackTrace();
        } catch (BadLocationException e) {
            Logger.getLogger(client_frame.class.getName()).log(Level.SEVERE, null, e);
        }
            /////////////////............. ta_chat.append("Failed to disconnect. \n");
        }
        isConnected = false;
        tf_username.setEditable(true);

    }
    
    public client_frame(String rcvdu, String rcvdp,String rcvdl,String str)
    {
        initComponents();


        getContentPane().setBackground(Color.getHSBColor(255, 255, 204));
                // new client_frame().setBackground(Color.MAGENTA);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

       // setVisible(true);
        tf_username.setText(rcvdu);
        tf_pass.setText(rcvdp);
        jlevel.setText(rcvdl);
        if(str.equals("connect"))
         b_connect.doClick();
        else
            b_register.doClick();
         jList1.setModel(demoList);
         System.out.println("hey!");
        
         //startFunct();
    }
    
    //--------------------------//
    
    public class IncomingReader implements Runnable
    {
        @Override
        public void run() 
        {
            String[] data;
            String styletype;





            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat",false_connection="false_connection",duplicate_connection="duplicate_connection",existing_connection="existing_connection",online_users="online_users",pchat="pchat";

            try 
            {
                

                while ((stream = reader.readLine()) != null) 
                {
                     data = stream.split(":");

                 if (data[2].equals(chat)) {

                         ///////////



                      StyledDocument doc = jTextPane1.getStyledDocument();
                                            Style style = jTextPane1.addStyle("name Font",null);

                       try{
                           if(!data[3].equals("")){
                               color=data[3];
                         if(data[3].equals("1")){
                                StyleConstants.setForeground(style,Color.red );                         }
                         else if(data[3].equals("2")){
                             StyleConstants.setForeground(style,Color.blue );
                         }
                         else if(data[3].equals("3")){
                             StyleConstants.setForeground(style,Color.green );
                         }
                         else if(data[3].equals("0")){
                             StyleConstants.setForeground(style,Color.cyan );
                         }
                           }
                           else{
                               if(color.equals("1")){
                                StyleConstants.setForeground(style,Color.red );                         }
                         else if(color.equals("2")){
                             StyleConstants.setForeground(style,Color.blue );
                         }
                         else if(color.equals("3")){
                             StyleConstants.setForeground(style,Color.green );
                         }
                         else if(color.equals("0")){
                             StyleConstants.setForeground(style,Color.cyan );
                         }
                           }
                      

                       doc.insertString(doc.getLength(), data[0] + ":", style);
                        StyleConstants.setForeground(style, Color.black);
          //  + ": " + data[1] + "\n", null);
               String msg="";
              for(int i=0;i<data[1].length();i++){

                  if(data[1].charAt(i)=='~'&&i+1<data[1].length()&&data[1].charAt(i+1)=='|'){
                     doc.insertString(doc.getLength(), msg, null);
                      jTextPane1.setCaretPosition(jTextPane1.getDocument().getLength());

                    //jTextPane1.insertIcon(new ImageIcon("C:\\Users\\Sk Sharma\\Downloads\\Chat_Client\\Chat_Client_emoji\\src\\resources\\photo11.gif"));
                  //                     doc.insertString(doc.getLength(), "~", null);
                     java.net.URL imgUrl = getClass().getResource("photo11.gif");
                      jTextPane1.insertIcon(new ImageIcon(imgUrl));

                     msg="";
                     i++;
                    }
                else {
                    if(data[1].charAt(i)=='`'&&i+1<data[1].length()&&data[1].charAt(i+1)=='|'){
                     doc.insertString(doc.getLength(), msg, null);
                      jTextPane1.setCaretPosition(jTextPane1.getDocument().getLength());

                    //jTextPane1.insertIcon(new ImageIcon("C:\\Users\\Sk Sharma\\Downloads\\Chat_Client\\Chat_Client_emoji\\src\\resources\\photo2.gif"));
                  //                     doc.insertString(doc.getLength(), "~", null);
                   // jTextPane1.insertIcon(new ImageIcon("C:\\Users\\Sk Sharma\\Documents\\NetBeansProjects\\Chat_Client_emoji_final\\src\\chat_client\\photo2.gif"));
                     java.net.URL imgUrl = getClass().getResource("photo2.gif");
                      jTextPane1.insertIcon(new ImageIcon(imgUrl));
                    msg="";
                    i++;
                    }
                    else{
                          if(data[1].charAt(i)=='^'&&i+1<data[1].length()&&data[1].charAt(i+1)=='|'){
                           doc.insertString(doc.getLength(), msg, null);
                           jTextPane1.setCaretPosition(jTextPane1.getDocument().getLength());

                          // jTextPane1.insertIcon(new ImageIcon("C:\\Users\\Sk Sharma\\Downloads\\Chat_Client\\Chat_Client_emoji\\src\\resources\\photo10.gif"));
                  //                     doc.insertString(doc.getLength(), "~", null);

                            java.net.URL imgUrl = getClass().getResource("photo17.gif");
                      jTextPane1.insertIcon(new ImageIcon(imgUrl));

                             msg="";
                             i++;
                            }
                          else{
                                if(data[1].charAt(i)=='*'&&i+1<data[1].length()&&data[1].charAt(i+1)=='|'){
                              doc.insertString(doc.getLength(), msg, null);
                              jTextPane1.setCaretPosition(jTextPane1.getDocument().getLength());

                             //jTextPane1.insertIcon(new ImageIcon("C:\\Users\\Sk Sharma\\Downloads\\Chat_Client\\Chat_Client_emoji\\src\\resources\\photo17.gif"));
                  //                     doc.insertString(doc.getLength(), "~", null);

                               java.net.URL imgUrl = getClass().getResource("photo10.gif");
                            jTextPane1.insertIcon(new ImageIcon(imgUrl));

                                msg="";
                                i++;
                                }
                                 else{
                                if(data[1].charAt(i)=='$'&&i+1<data[1].length()&&data[1].charAt(i+1)=='|'){
                              doc.insertString(doc.getLength(), msg, null);
                              jTextPane1.setCaretPosition(jTextPane1.getDocument().getLength());

                             //jTextPane1.insertIcon(new ImageIcon("C:\\Users\\Sk Sharma\\Downloads\\Chat_Client\\Chat_Client_emoji\\src\\resources\\photo17.gif"));
                  //                     doc.insertString(doc.getLength(), "~", null);

                               java.net.URL imgUrl = getClass().getResource("photo15.gif");
                            jTextPane1.insertIcon(new ImageIcon(imgUrl));

                                msg="";
                                i++;
                                }
                                 else{
                                if(data[1].charAt(i)=='@'&&i+1<data[1].length()&&data[1].charAt(i+1)=='|'){
                              doc.insertString(doc.getLength(), msg, null);
                              jTextPane1.setCaretPosition(jTextPane1.getDocument().getLength());

                             //jTextPane1.insertIcon(new ImageIcon("C:\\Users\\Sk Sharma\\Downloads\\Chat_Client\\Chat_Client_emoji\\src\\resources\\photo17.gif"));
                  //                     doc.insertString(doc.getLength(), "~", null);

                               java.net.URL imgUrl = getClass().getResource("photo20.gif");
                            jTextPane1.insertIcon(new ImageIcon(imgUrl));

                                msg="";
                                i++;
                                }
                                else{
                                if(data[1].charAt(i)=='#'&&i+1<data[1].length()&&data[1].charAt(i+1)=='|'){
                              doc.insertString(doc.getLength(), msg, null);
                              jTextPane1.setCaretPosition(jTextPane1.getDocument().getLength());

                             //jTextPane1.insertIcon(new ImageIcon("C:\\Users\\Sk Sharma\\Downloads\\Chat_Client\\Chat_Client_emoji\\src\\resources\\photo17.gif"));
                  //                     doc.insertString(doc.getLength(), "~", null);

                               java.net.URL imgUrl = getClass().getResource("photo14.gif");
                            jTextPane1.insertIcon(new ImageIcon(imgUrl));

                                msg="";
                                i++;
                                }
                                else{
                                if(data[1].charAt(i)=='&'&&i+1<data[1].length()&&data[1].charAt(i+1)=='|'){
                              doc.insertString(doc.getLength(), msg, null);
                              jTextPane1.setCaretPosition(jTextPane1.getDocument().getLength());

                             //jTextPane1.insertIcon(new ImageIcon("C:\\Users\\Sk Sharma\\Downloads\\Chat_Client\\Chat_Client_emoji\\src\\resources\\photo17.gif"));
                  //                     doc.insertString(doc.getLength(), "~", null);

                               java.net.URL imgUrl = getClass().getResource("photo16.gif");
                            jTextPane1.insertIcon(new ImageIcon(imgUrl));

                                msg="";
                                i++;
                                }
                             else
                       {
                    // doc.insertString(doc.getLength(), data[1].charAt(i) , null);
                    // doc.
                    msg=msg+data[1].charAt(i);
                        }
                    }
                    }
                }
            }
                          }
                    }

                }
              }
           doc.insertString(doc.getLength(), msg+"\n", null);


          } catch (BadLocationException ex) {
            Logger.getLogger(client_frame.class.getName()).log(Level.SEVERE, null, ex);
        }
               jTextPane1.setCaretPosition(jTextPane1.getDocument().getLength());
                        /////////////////.............ta_chat.append(data[0] + ": " + data[1] + "\n");
                        ////////////////..........ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                     } 
                     else if (data[2].equals(connect))
                     {
                        /////// jTextPane1.removeAll();

                      //////////////////.............  ta_chat.removeAll();
                      tf_username.setEditable(false);
                   //   tf_password.setEditable(false);
                      tf_pass.setEditable(false);
                     isConnected = true;

                     b_disconnect.setEnabled(true);
                     b_send.setEnabled(true);
                     jButton5.setEnabled(true);
                        userAdd(data[0]);



                        status.setBackground(Color.GREEN);

                       

                     } 
                     else if (data[2].equals(disconnect)) 
                     {
                        status.setBackground(Color.RED);
                         userRemove(data[0]);
                       

                     }

                     else if (data[2].equals(done)) 
                     {
                        //users.setText("");
                        writeUsers();
                        users.clear();
                     }

                     ////////////////////
                     else if(data[2].equals(false_connection))
                     {
                          StyledDocument doc = jTextPane1.getStyledDocument();
                           try {
                            doc.insertString(doc.getLength(), "Invalid credentials... Try again.\n", null);
                                    } catch (BadLocationException ex) {
                                    Logger.getLogger(client_frame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                          tf_username.setEditable(true);
                          isConnected=false;
                     }

                     else if(data[2].equals(duplicate_connection))
                     {
                          StyledDocument doc = jTextPane1.getStyledDocument();
                           try {
                            doc.insertString(doc.getLength(), "username already taken... enter another combination.\n", null);
                                    } catch (BadLocationException ex) {
                                    Logger.getLogger(client_frame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                  tf_username.setEditable(true);
                          isConnected=false;
                     }
                     else if(data[2].equals(existing_connection))
                     {
                          StyledDocument doc = jTextPane1.getStyledDocument();
                           try {
                            doc.insertString(doc.getLength(), "You are already logged in via some other window.\n", null);
                                    } catch (BadLocationException ex) {
                                    Logger.getLogger(client_frame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                  tf_username.setEditable(true);
                          isConnected=false;
                     }
                     else if(data[2].equals(online_users))
                     {
                       //  System.out.println("got..");
                       //  online_text.setForeground(Color.BLACK);
                         if(cnt==0){
                             //online_text.setText(data[1]+"\n");
                           demoList.addElement(data[1]+"\n");
                           jList1.setModel(demoList);
                             cnt++;
                         }
                         else{
                         if(data[1]!="~"){
                           //  online_text.append(data[1]+"\n");
                              demoList.addElement(data[1]+"\n");

                            jList1.setModel(demoList);
                         }
                         else{
                                 cnt=0;
                         }
                         }
                     }
                     else if(data[2].equals("announce_chat"))
                     {
                        
                         {
                          StyledDocument doc = jTextPane2.getStyledDocument();
                          Style style = jTextPane2.addStyle("name Font",null);

                           try {
                               if(i%2==0)
                               StyleConstants.setForeground(style,Color.red );
                               else
                                   StyleConstants.setForeground(style,Color.blue );
                               i++;
                            doc.insertString(doc.getLength(), data[1]+"\n", style);
                                    } catch (BadLocationException ex) {
                                    Logger.getLogger(client_frame_manage.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                  tf_username.setEditable(true);
                          isConnected=false;
                         }
                     }
                     else if(data[2].equals("Extend_chat"))
                     {


                     }
                      else if (data[2].equals(pchat))
                     {
                        //  Thread nt=new Thread(new TimeThread());
                         // nt.start();
                          
                          System.out.println("pchat recvd by "+username+"data[1] is: "+data[1]);
                        if(data[1].trim().equals(username)){
                             System.out.println("ss "+data[1]+" "+username);
                           pto=data[0];
                            pfrom=data[1];
                            client_pers cp=new client_pers(pfrom,pto);
                            cp.setVisible(true);
                        }
                     }
                }
           }catch(Exception ex) {
           }
        }
    }

   /* public class TimeThread implements Runnable{

        public void run(){
                tf_chat.setText("hey");
              long startTime = System.currentTimeMillis();
            long elapsedTime = 0L;

                while (elapsedTime <60* 1000) {
                //perform db poll/check
              elapsedTime = (System.currentTimeMillis() - startTime);
            }

            try {
               writer.println(pfrom + ":" + pto + ":" + "Extend_chat"+":"+color);
               writer.flush(); // flushes the buffer
            } catch (Exception ex) {

                          StyledDocument doc = jTextPane1.getStyledDocument();
        try {
            doc.insertString(doc.getLength(),"Message was not sent. \n", null);
        } catch (BadLocationException e) {
            Logger.getLogger(client_frame.class.getName()).log(Level.SEVERE, null, ex);
        }
             //////////////////..............   ta_chat.append("Message was not sent. \n");
            }

        }
    }*/
    //--------------------------//
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_address = new javax.swing.JLabel();
        tf_address = new javax.swing.JTextField();
        lb_port = new javax.swing.JLabel();
        tf_port = new javax.swing.JTextField();
        lb_username = new javax.swing.JLabel();
        tf_username = new javax.swing.JTextField();
        lb_password = new javax.swing.JLabel();
        b_connect = new javax.swing.JButton();
        b_disconnect = new javax.swing.JButton();
        tf_chat = new javax.swing.JTextField();
        b_send = new javax.swing.JButton();
        b_register = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton5 = new javax.swing.JButton();
        status = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        tf_pass = new javax.swing.JPasswordField();
        bpto = new javax.swing.JButton();
        jpto = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        bpto1 = new javax.swing.JButton();
        jlevel = new javax.swing.JTextField();
        close = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        lb_password1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Client's frame");
        setBackground(new java.awt.Color(102, 255, 255));
        setName("client"); // NOI18N

        lb_address.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        lb_address.setForeground(new java.awt.Color(255, 153, 0));
        lb_address.setText("Address : ");

        tf_address.setEditable(false);
        tf_address.setText("aditingunjan.hopto.org");
        tf_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_addressActionPerformed(evt);
            }
        });

        lb_port.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        lb_port.setForeground(new java.awt.Color(255, 153, 0));
        lb_port.setText("Port :");

        tf_port.setEditable(false);
        tf_port.setText("443");
        tf_port.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_portActionPerformed(evt);
            }
        });

        lb_username.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        lb_username.setForeground(new java.awt.Color(255, 153, 0));
        lb_username.setText("Username :");

        tf_username.setForeground(new java.awt.Color(196, 196, 200));
        tf_username.setText(" Type username ...");
        tf_username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_usernameMouseClicked(evt);
            }
        });
        tf_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_usernameActionPerformed(evt);
            }
        });

        lb_password.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        lb_password.setForeground(new java.awt.Color(255, 153, 0));
        lb_password.setText("Password : ");

        b_connect.setBackground(new java.awt.Color(237, 205, 157));
        b_connect.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        b_connect.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        b_connect.setOpaque(false);
        b_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_connectActionPerformed(evt);
            }
        });

        b_disconnect.setBackground(new java.awt.Color(237, 205, 157));
        b_disconnect.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        b_disconnect.setText("DISCONNECT");
        b_disconnect.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        b_disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_disconnectActionPerformed(evt);
            }
        });

        tf_chat.setForeground(new java.awt.Color(181, 176, 176));
        tf_chat.setText("         Type text here ...");
        tf_chat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        tf_chat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_chatMouseClicked(evt);
            }
        });
        tf_chat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_chatActionPerformed(evt);
            }
        });

        b_send.setBackground(new java.awt.Color(237, 205, 157));
        b_send.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        b_send.setText("SEND");
        b_send.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        b_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_sendActionPerformed(evt);
            }
        });

        b_register.setBackground(new java.awt.Color(237, 205, 157));
        b_register.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        b_register.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        b_register.setOpaque(false);
        b_register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_registerActionPerformed(evt);
            }
        });

        jTextPane1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jScrollPane2.setViewportView(jTextPane1);

        jButton5.setBackground(new java.awt.Color(255, 255, 204));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setText("View Online Users");
        jButton5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        status.setBackground(new java.awt.Color(255, 51, 0));

        jPanel1.setBackground(new java.awt.Color(210, 177, 177));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat_client/photo17.gif"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat_client/photo10.gif"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat_client/photo16.gif"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat_client/photo11.gif"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat_client/photo2.gif"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat_client/photo15.gif"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat_client/photo14.gif"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat_client/photo20.gif"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        tf_pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_passActionPerformed(evt);
            }
        });

        bpto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bpto.setText("Chat Personally");
        bpto.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bpto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bptoActionPerformed(evt);
            }
        });

        jpto.setBackground(new java.awt.Color(255, 255, 204));

        jList1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("        SELECTED USER");

        bpto1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bpto1.setText("Send File");
        bpto1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bpto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpto1ActionPerformed(evt);
            }
        });

        close.setBackground(new java.awt.Color(237, 205, 157));
        close.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        close.setText("CLOSE");
        close.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        close.setEnabled(false);
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        jTextPane2.setBackground(new java.awt.Color(255, 255, 204));
        jTextPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jScrollPane4.setViewportView(jTextPane2);

        lb_password1.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        lb_password1.setForeground(new java.awt.Color(255, 153, 0));
        lb_password1.setText("Post:");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/star (2).png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/star (2).png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/star (2).png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/star (2).png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("ANNOUNCEMENTS");

        jLabel6.setText("ENABLE/DISABLE");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("ADD EMOTICONS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_address)
                            .addComponent(lb_username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_address, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(lb_password)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tf_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(lb_password1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(lb_port, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_port, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlevel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(132, 132, 132)
                                .addComponent(bpto1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b_connect, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b_register, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(171, 171, 171))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(bpto, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jpto, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(55, 55, 55)
                                        .addComponent(jLabel7))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tf_chat, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(152, 152, 152)
                                        .addComponent(jLabel8)))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b_disconnect, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b_send, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lb_address)
                                    .addComponent(tf_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lb_port)
                                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))
                            .addComponent(b_register, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bpto1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_username)
                            .addComponent(lb_password)
                            .addComponent(tf_pass)
                            .addComponent(jlevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_password1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_connect, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jpto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bpto))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_chat, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_send, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b_disconnect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(close, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel8))
                .addGap(197, 197, 197))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {bpto, bpto1, jButton5});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {b_disconnect, b_send, close});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_addressActionPerformed
       
    }//GEN-LAST:event_tf_addressActionPerformed

    private void tf_portActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_portActionPerformed
   
    }//GEN-LAST:event_tf_portActionPerformed

    private void tf_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_usernameActionPerformed
    
    }//GEN-LAST:event_tf_usernameActionPerformed

    public void startFunct(){
         if (isConnected == false)
        {
            username = tf_username.getText();
            //password=tf_password.getText();
            password=tf_pass.getText();
            level=jlevel.getText();
            tf_username.setEditable(false);

            try
            {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
               ////////////MY CODE
                System.out.println("writing");
                writer.println(username + ":has connected.:Connect:"+password+":"+level);
                ///////////MY CODE
                writer.flush();
                isConnected = true;


            }
            catch (Exception ex)
            {


                          StyledDocument doc = jTextPane1.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), "Cannot Connect! Try Again. \n", null);
        } catch (BadLocationException e) {
            Logger.getLogger(client_frame.class.getName()).log(Level.SEVERE, null, ex);
        }

          ////////////......      ta_chat.append("Cannot Connect! Try Again. \n");
              //  tf_username.setEditable(true);
            }

            ListenThread();

        } else if (isConnected == true)
        {

                          StyledDocument doc = jTextPane1.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), "You are already connected. \n", null);
        } catch (BadLocationException ex) {
            Logger.getLogger(client_frame.class.getName()).log(Level.SEVERE, null, ex);
        }
            ///////////////............  ta_chat.append("You are already connected. \n");
        }
    }
    private void b_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_connectActionPerformed
        if (isConnected == false) 
        {
            username = tf_username.getText();
            //password=tf_password.getText();
            password=tf_pass.getText();
            level=jlevel.getText();
            tf_username.setEditable(false);

            try 
            {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
               ////////////MY CODE
                System.out.println("writing");
                writer.println(username + ":has connected.:Connect:"+password+":"+level);
                ///////////MY CODE
                writer.flush(); 
                isConnected = true;
                

            } 
            catch (Exception ex) 
            {


                          StyledDocument doc = jTextPane1.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), "Cannot Connect! Try Again. \n", null);
        } catch (BadLocationException e) {
            Logger.getLogger(client_frame.class.getName()).log(Level.SEVERE, null, ex);
        }

          ////////////......      ta_chat.append("Cannot Connect! Try Again. \n");
              //  tf_username.setEditable(true);
            }
            
            ListenThread();
            
        } else if (isConnected == true) 
        {

                          StyledDocument doc = jTextPane1.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), "You are already connected. \n", null);
        } catch (BadLocationException ex) {
            Logger.getLogger(client_frame.class.getName()).log(Level.SEVERE, null, ex);
        }
            ///////////////............  ta_chat.append("You are already connected. \n");
        }
    }//GEN-LAST:event_b_connectActionPerformed

    private void b_disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_disconnectActionPerformed
        sendDisconnect();
        status.setBackground(Color.RED);
        Disconnect();
        close.setEnabled(true);
    }//GEN-LAST:event_b_disconnectActionPerformed

    private void b_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_sendActionPerformed
        String nothing = "";
        if ((tf_chat.getText()).equals(nothing)) {
            tf_chat.setText("");
            tf_chat.requestFocus();
        } else {
            try {
               writer.println(username + ":" + tf_chat.getText() + ":" + "Chat"+":"+color);
               writer.flush(); // flushes the buffer
            } catch (Exception ex) {

                          StyledDocument doc = jTextPane1.getStyledDocument();
        try {
            doc.insertString(doc.getLength(),"Message was not sent. \n", null);
        } catch (BadLocationException e) {
            Logger.getLogger(client_frame.class.getName()).log(Level.SEVERE, null, ex);
        }
             //////////////////..............   ta_chat.append("Message was not sent. \n");
            }
            tf_chat.setText("");
            tf_chat.requestFocus();
        }

        tf_chat.setText("");
        tf_chat.requestFocus();
    }//GEN-LAST:event_b_sendActionPerformed
/////////////////MY CODE
    private void b_registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_registerActionPerformed
        // TODO add your handling code here:

         if (isConnected == false)
        {
            username = tf_username.getText();
            //password=tf_password.getText();
            password=tf_pass.getText();
            level=jlevel.getText();
            tf_username.setEditable(false);

            try
            {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
               ////////////MY CODE
                writer.println(username + ":has connected.:Register:"+password+":"+level);
                ///////////MY CODE
                writer.flush();
                isConnected = true;
            }
            catch (Exception ex)
            {
                
                          StyledDocument doc = jTextPane1.getStyledDocument();
        try {
            doc.insertString(doc.getLength(),"Cannot Connect! Try Again. \n", null);
        } catch (BadLocationException e) {
            Logger.getLogger(client_frame.class.getName()).log(Level.SEVERE, null, e);
        }
                //////////////////..............ta_chat.append("Cannot Connect! Try Again. \n");
                tf_username.setEditable(true);
            }

            ListenThread();

        } else if (isConnected == true)
        {

                          StyledDocument doc = jTextPane1.getStyledDocument();
        try {
            doc.insertString(doc.getLength(),"You are already connected. \n", null);
        } catch (BadLocationException ex) {
            Logger.getLogger(client_frame.class.getName()).log(Level.SEVERE, null, ex);
        }
           ///////////////................  ta_chat.append("You are already connected. \n");
        }

    }//GEN-LAST:event_b_registerActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
      //    jTextPane1.insertIcon(new ImageIcon("C:\\Users\\Sk Sharma\\Downloads\\Chat_Client\\Chat_Client_emoji\\src\\resources\\photo11.gif"));
                tf_chat.setText(tf_chat.getText() + "~|");

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
                tf_chat.setText(tf_chat.getText() + "`|");

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
                tf_chat.setText(tf_chat.getText() + "*|");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
                tf_chat.setText(tf_chat.getText() + "^|");


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        demoList.clear();
      jList1.setModel(demoList);


     //   online_text.setText("");
       // online_text.setForeground(Color.BLACK);
        try {
               writer.println(username + ":" + "hey" + ":" + "online_users");
               writer.flush(); // flushes the buffer
            } catch (Exception ex) {

                          ex.printStackTrace();
            }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
          tf_chat.setText(tf_chat.getText() + "$|");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
          tf_chat.setText(tf_chat.getText() + "@|");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
          tf_chat.setText(tf_chat.getText() + "#|");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
          tf_chat.setText(tf_chat.getText() + "&|");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void tf_chatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_chatMouseClicked
        // TODO add your handling code here:
        if(first==true){
            tf_chat.setText("");
            tf_chat.setForeground(Color.BLACK);
            first=false;
        }
    }//GEN-LAST:event_tf_chatMouseClicked

    private void tf_chatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_chatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_chatActionPerformed

    private void tf_passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_passActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_passActionPerformed

    private void tf_usernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_usernameMouseClicked
        // TODO add your handling code here:
        tf_username.setText("");
        tf_username.setForeground(Color.BLACK);
    }//GEN-LAST:event_tf_usernameMouseClicked

    private void bptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bptoActionPerformed
        // TODO add your handling code here:
        pfrom=username;

        pto=jpto.getText();
        String text=jList1.getSelectedValue().toString();
        jpto.setText(text);
        pto=jpto.getText();

          try {
               writer.println(username + ":" + jpto.getText() + ":" + "pchat"+":"+color);
               writer.flush(); // flushes the buffer
            } catch (Exception ex) {

                          StyledDocument doc = jTextPane1.getStyledDocument();
        try {
            doc.insertString(doc.getLength(),"Message was not sent. \n", null);
        } catch (BadLocationException e) {
            Logger.getLogger(client_frame.class.getName()).log(Level.SEVERE, null, ex);
        }
             //////////////////..............   ta_chat.append("Message was not sent. \n");
            }
        client_pers cp=new client_pers(pfrom,pto);
        cp.setVisible(true);

}//GEN-LAST:event_bptoActionPerformed

    private void bpto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpto1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bpto1ActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_closeActionPerformed
/////////////////MY CODE
    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
              //  JPanel content=new JPanel();
                
               //  cf.getContentPane().setBackground(Color.getHSBColor(255, 255, 204));
                 //new client_frame("a","a","a","a").setVisible(true);
                //cf.setVisible(true);



              

            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_connect;
    private javax.swing.JButton b_disconnect;
    private javax.swing.JButton b_register;
    private javax.swing.JButton b_send;
    private javax.swing.JButton bpto;
    private javax.swing.JButton bpto1;
    private javax.swing.JButton close;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTextField jlevel;
    private javax.swing.JTextField jpto;
    private javax.swing.JLabel lb_address;
    private javax.swing.JLabel lb_password;
    private javax.swing.JLabel lb_password1;
    private javax.swing.JLabel lb_port;
    private javax.swing.JLabel lb_username;
    private javax.swing.JTextField status;
    private javax.swing.JTextField tf_address;
    private javax.swing.JTextField tf_chat;
    private javax.swing.JPasswordField tf_pass;
    private javax.swing.JTextField tf_port;
    private javax.swing.JTextField tf_username;
    // End of variables declaration//GEN-END:variables
}
