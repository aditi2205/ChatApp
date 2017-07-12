package chat_client;

import java.awt.Color;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.WindowConstants;

public class login_win extends javax.swing.JFrame 
{
    static login_win lw=new login_win();
    String level;
    String str;
    String username,password, address = "gunjansharma1512.hopto.org";
    ArrayList<String> users = new ArrayList();
    int port = 4125;
    Boolean isConnected = false;
    
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
    
    //--------------------------//
    
    public void ListenThread() 
    {
         Thread IncomingReader = new Thread(new IncomingReader());
         IncomingReader.start();
    }
    
    //--------------------------//
    
   
    
    //--------------------------//
    
   
    //--------------------------//
    
   
    
    //--------------------------//
    
  

    //--------------------------//
    
    
    
    public login_win() 
    {
        initComponents();
        getContentPane().setBackground(Color.getColor("D2691E"));

    }
    
    //--------------------------//
    
    public class IncomingReader implements Runnable
    {
        @Override
        public void run() 
        {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

            try 
            {
                while ((stream = reader.readLine()) != null) 
                {
                     data = stream.split(":");

                     if (data[2].equals(chat)) 
                     {
                       // ta_chat.append(data[0] + ": " + data[1] + "\n");
                      //  ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                         //// call the approp window


                         //tf_chat.setText("yeah!");
                         label.setText("Logging you in ...");
                         if(tf_password1.getText().equals("employee")){
                         client_frame cf=new client_frame(tf_username.getText(),tf_password.getText(),tf_password1.getText(),str);
                         cf.setVisible(true);
                         lw.setVisible(false);
                         }
                         else{
                           client_frame_manage cf=new client_frame_manage(tf_username.getText(),tf_password.getText(),tf_password1.getText(),str);
                         cf.setVisible(true);
                         lw.setVisible(false);
                         }


                     } 
                     

                     /////////////
                      else if(data[2].equals("false_connection"))
                     {
                        // tf_chat.setText("Invalid credentials... Try again.\n");
                         label.setText("Invalid credentials... Try again.\n");

                          isConnected=false;
                     }

                     else if(data[2].equals("duplicate_connection"))
                     {
                         // tf_chat.setText("username already taken... enter another combination.\n");
                          label.setText("username already taken... enter another combination.\n");

                          isConnected=false;
                     }
                     else if(data[2].equals("existing_connection"))
                     {
                          // tf_chat.setText( "You are already logged in via some other window.\n");
                          label.setText("You are already logged in via some other window.\n");


                          isConnected=false;
                     }

                }
           }catch(Exception ex) { }
        }
    }

    //--------------------------//
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_port = new javax.swing.JLabel();
        lb_username = new javax.swing.JLabel();
        tf_username = new javax.swing.JTextField();
        lb_password = new javax.swing.JLabel();
        tf_password = new javax.swing.JTextField();
        b_connect = new javax.swing.JButton();
        b_register = new javax.swing.JButton();
        tf_password1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Client's frame");
        setBackground(new java.awt.Color(204, 255, 204));
        setName("client"); // NOI18N

        lb_username.setFont(new java.awt.Font("Tahoma", 0, 14));
        lb_username.setText("Username:");

        tf_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_usernameActionPerformed(evt);
            }
        });

        lb_password.setFont(new java.awt.Font("Tahoma", 0, 14));
        lb_password.setText("Password : ");

        tf_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_passwordActionPerformed(evt);
            }
        });

        b_connect.setFont(new java.awt.Font("Tahoma", 0, 18));
        b_connect.setText("LogIn");
        b_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_connectActionPerformed(evt);
            }
        });

        b_register.setFont(new java.awt.Font("Tahoma", 0, 18));
        b_register.setText("Register");
        b_register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_registerActionPerformed(evt);
            }
        });

        tf_password1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_password1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel1.setText("Position:");

        label.setText(".");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(244, 244, 244)
                .addComponent(lb_port)
                .addContainerGap(238, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lb_password, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_username, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
                    .addComponent(jLabel1))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tf_password, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_password1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(152, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(b_connect, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101)
                .addComponent(b_register, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addContainerGap(86, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(jLabel2)
                .addContainerGap(278, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(label)
                .addContainerGap(392, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tf_password, tf_password1, tf_username});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, lb_password});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {b_connect, b_register});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lb_port)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_username, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(lb_password)
                    .addComponent(tf_password, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_password1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_connect, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_register))
                .addGap(55, 55, 55)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(label)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, lb_password});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {b_connect, b_register});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_usernameActionPerformed
    
    }//GEN-LAST:event_tf_usernameActionPerformed

    private void b_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_connectActionPerformed
        if (isConnected == false) 
        {
            username = tf_username.getText();
            password=tf_password.getText();
           // tf_username.setEditable(false);
            level= tf_password1.getText();

            try 
            {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
               ////////////MY CODE
                str="connect";
                writer.println(username + ":has connected.:Connect:"+password+":"+level);
                ///////////MY CODE
                writer.flush(); 
                isConnected = true; 
            } 
            catch (Exception ex) 
            {
                
                tf_username.setEditable(true);
            }
            
            ListenThread();
            
        } else if (isConnected == true) 
        {
            
        }
    }//GEN-LAST:event_b_connectActionPerformed
/////////////////MY CODE
    private void b_registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_registerActionPerformed
        // TODO add your handling code here:

         if (isConnected == false)
        {
            username = tf_username.getText();
            password=tf_password.getText();
           // tf_username.setEditable(false);
            level=tf_password1.getText();

            try
            {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
               ////////////MY CODE
                str="register";
                writer.println(username + ":has connected.:Register:"+password+":"+level);
                ///////////MY CODE
                writer.flush();
                isConnected = true;
            }
            catch (Exception ex)
            {
               // ta_chat.append("Cannot Connect! Try Again. \n");
                tf_username.setEditable(true);
            }

            ListenThread();

        } 

    }//GEN-LAST:event_b_registerActionPerformed

    private void tf_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_passwordActionPerformed

    private void tf_password1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_password1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_password1ActionPerformed
/////////////////MY CODE
    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                lw.setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_connect;
    private javax.swing.JButton b_register;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel label;
    private javax.swing.JLabel lb_password;
    private javax.swing.JLabel lb_port;
    private javax.swing.JLabel lb_username;
    private javax.swing.JTextField tf_password;
    private javax.swing.JTextField tf_password1;
    private javax.swing.JTextField tf_username;
    // End of variables declaration//GEN-END:variables
}
