package chat_server;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.*;
import java.net.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;

public class login_server extends javax.swing.JFrame 
{
  

   public class ClientHandler implements Runnable	
   {
       BufferedReader reader;
       Socket sock;
       PrintWriter client;

       public ClientHandler(Socket clientSocket, PrintWriter user) 
       {
            client = user;
            try 
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (Exception ex) 
            {
                ta_chat.append("Unexpected error... \n");
            }

       }

       @Override
       public void run() 
       {
            String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat",register="Register" ;
            String[] data;

            try 
            {
                while ((message = reader.readLine()) != null) 
                {
                    ta_chat.append("Received: " + message + "\n");
                    data = message.split(":");
                    
                    for (String token:data) 
                    {
                        ta_chat.append(token + "\n");
                    }

                    if (data[2].equals(connect))
                    {





         //////////////////////// MY CODE
                        boolean flag=false;
                        boolean flag2=true;
                        Connection con=null;
                         try{
                             Class.forName("com.mysql.jdbc.Driver");
                             con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/dbnetbeans","root","");
                            }
                        catch(Exception e){
                            System.out.println(e);
                             }
                        Statement myStt=(Statement) con.createStatement();
                        ResultSet myRes=myStt.executeQuery("SELECT * FROM tbluser");

                        while(myRes.next()){
                            if(myRes.getString("fusername").equals(data[0])&&myRes.getString("fpassword").equals(data[3])&&myRes.getString("flevel").equals(data[4])){
                                 flag=true;
                                  break;
                            }
                         }

                         ///////////to check if already present
                      myRes=myStt.executeQuery("SELECT * FROM activuser");
                      while(myRes.next()){
                            if(myRes.getString("fusername").equals(data[0])&&myRes.getString("fpassword").equals(data[3])&&myRes.getString("flevel").equals(data[4])){
                                 flag2=false;
                                  break;
                            }
                         }

        /////////////////////// MY CODE
                        if(flag==true&&flag2==true){
                                  //Statement myStt=(Statement) con.createStatement();
                       String sql="insert into activuser (fusername,fpassword,flevel) values('"+data[0]+"','"+data[3]+"','"+data[4]+"')";
                       myStt.executeUpdate(sql);

                            tellSomeone((data[0] + ":" + data[1] + ":" + chat),client);

                            
                         }
                        else
                        if(flag==false){
                           // tellEveryone((data[0]+":"+data[1]+":"+"false_connection"));
                             tellSomeone((data[0]+":"+data[1]+":"+"false_connection"),client);
                            ta_chat.append("no conditions met.\n");
                           // clientOutputStreams.remove(client);
                         //   userRemove(data[0]);
                        }
                        else{
                             tellSomeone((data[0]+":"+data[1]+":"+"existing_connection"),client);
                            ta_chat.append("no conditions met.\n");
                            //clientOutputStreams.remove(client);
                        }
                    }
      ////////////////////// MY CODE
                    else if (data[2].equals(register))
                    {
                        boolean flag=false;
                         Connection con=null;
                         try{
                             Class.forName("com.mysql.jdbc.Driver");
                             con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/dbnetbeans","root","");
/////////////////////////////// DUPLICACY REMOVAL////////////////
                             Statement myStt2=(Statement) con.createStatement();
                        ResultSet myRes=myStt2.executeQuery("SELECT * FROM tbluser");

                        while(myRes.next()){
                            if(myRes.getString("fusername").equals(data[0])){
                                 flag=true;
                                  break;
                            }
                         }


                             if(flag==false){
                       //  Statement myStt=(Statement) con.createStatement();
                       //  String sql="insert into tbluser (fusername,fpassword,flevel) values('"+data[0]+"','"+data[3]+"','"+data[4]+"')";
                       //  myStt.executeUpdate(sql);

                        Statement myStt=(Statement) con.createStatement();
                       String sql="insert into activuser (fusername,fpassword,flevel) values('"+data[0]+"','"+data[3]+"','"+data[4]+"')";
                       myStt.executeUpdate(sql);

                         tellSomeone((data[0] + ":" + data[1] + ":" + chat),client);

                           // userAdd(data[0]);
                          }

                             else{
                              tellSomeone((data[0]+":"+data[1]+":"+"duplicate_connection"),client);
                            ta_chat.append("no conditions met.\n");
                             
                             }

                         }
                        catch(Exception e){
                            System.out.println(e);
                             }
             

                    }
                    else if (data[2].equals(disconnect)) 
                    {
                        tellSomeone((data[0] + ":has disconnected." + ":" + chat),client);
                        //userRemove(data[0]);
                    } 
                    
                    else 
                    {
                        ta_chat.append("No Conditions were met. \n");
                    }
                } 
             } 
             catch (Exception ex) 
             {
                ta_chat.append("Lost a connection. \n");
                ex.printStackTrace();
               // clientOutputStreams.remove(client);
             } 
	} 
    }

    public login_server() 
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        b_start = new javax.swing.JButton();
        b_end = new javax.swing.JButton();
        b_users = new javax.swing.JButton();
        b_clear = new javax.swing.JButton();
        lb_name = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Server's frame");
        setName("server"); // NOI18N
        setResizable(false);

        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        jScrollPane1.setViewportView(ta_chat);

        b_start.setText("START");
        b_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_startActionPerformed(evt);
            }
        });

        b_end.setText("END");
        b_end.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_endActionPerformed(evt);
            }
        });

        b_users.setText("Online Users");
        b_users.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_usersActionPerformed(evt);
            }
        });

        b_clear.setText("Clear");
        b_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_clearActionPerformed(evt);
            }
        });

        lb_name.setText("TechWorld3g");
        lb_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(b_end, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(b_start, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 291, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(b_clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(b_users, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lb_name)
                        .addGap(209, 209, 209))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_start)
                    .addComponent(b_users))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_clear)
                    .addComponent(b_end))
                .addGap(4, 4, 4)
                .addComponent(lb_name))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_endActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_endActionPerformed
        try 
        {
            Thread.sleep(5000);                 //5000 milliseconds is five second.
        } 
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        
      //  tellEveryone("Server:is stopping and all users will be disconnected.\n:Chat");
        ta_chat.append("Server stopping... \n");
        
        ta_chat.setText("");
    }//GEN-LAST:event_b_endActionPerformed

    private void b_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_startActionPerformed
        Thread starter = new Thread(new ServerStart());
        starter.start();
        
        ta_chat.append("Server started...\n");
    }//GEN-LAST:event_b_startActionPerformed

    private void b_usersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_usersActionPerformed
        ta_chat.append("\n Online users : \n");
        
        
    }//GEN-LAST:event_b_usersActionPerformed

   

    private void b_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_clearActionPerformed
        ta_chat.setText("");
    }//GEN-LAST:event_b_clearActionPerformed

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                new login_server().setVisible(true);
            }
        });
    }
    
    public class ServerStart implements Runnable 
    {
        @Override
        public void run() 
        {
            

            try 
            {
                ServerSocket serverSock = new ServerSocket(4125);

                while (true) 
                {
				Socket clientSock = serverSock.accept();
				PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
				//clientOutputStreams.add(writer);

				Thread listener = new Thread(new ClientHandler(clientSock, writer));
				listener.start();
				ta_chat.append("Got a connection. \n");
                }
            }
            catch (Exception ex)
            {
                ta_chat.append("Error making a connection. \n");
            }
        }
    }
    
     public void tellSomeone(String message,PrintWriter client)
    {
        client.println(message);
        client.flush();
    }

   
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_clear;
    private javax.swing.JButton b_end;
    private javax.swing.JButton b_start;
    private javax.swing.JButton b_users;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_name;
    private javax.swing.JTextArea ta_chat;
    // End of variables declaration//GEN-END:variables
}
