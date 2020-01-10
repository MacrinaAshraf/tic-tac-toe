package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.json.JSONException;
import org.json.JSONObject;

public class Client {

    Socket clientSocket;
    BufferedReader dis;
    PrintStream ps;
    JSONObject message;
    Boolean keepRunning;
    //SampleUI ui = new SampleUI();

    public static void main(String[] args) {
       
            new Client();
        
    }

    public Client()  {
        try {
            //ui.setSize(500, 500);
            //ui.setVisible(true);
            keepRunning = true;
            clientSocket = new Socket("127.0.0.1", 5008);
            dis = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ps = new PrintStream(clientSocket.getOutputStream());

            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (keepRunning) {

                        try {
                            System.out.println("client !");
                            String msg = dis.readLine();
                            //ui.appendMsg(msg);
                        } catch (IOException ex) {

                            keepRunning = false;
                            System.out.print("server has closed");
                        }

                    }
                }

            });
            th.start();
        } catch (IOException e) {
            System.out.print("hi");
            e.printStackTrace();
            keepRunning = false;
            stopConnection();
        }

    }

    class SampleUI extends JFrame {

        JTextArea ta;
        JTextArea t;

        public SampleUI() {
            /*this.setLayout(new FlowLayout());
            ta = new JTextArea(10, 40);
            JScrollPane scroll = new JScrollPane(ta);
            scroll.setViewportView(ta);
            JTextField tf = new JTextField(30);
            t = new JTextArea(10, 40);
            JButton okButton = new JButton("Send");
            JButton closeButton = new JButton("close");*/
            
            /*okButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {

                    ps.println(tf.getText());
                    tf.setText("");
                }
            });
            
            closeButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {*/
                    try {
                       String msg = "{\"type\":\"stop\"}";
                       message.put("type", "stop");
                       keepRunning = false;
                       System.out.print(msg);
                       ps.print(msg);
                       
                    } catch (JSONException ex) {
                         ps.print("hi");
                      //   System.exit(0);
                       // Logger.getLogger(ClientChat.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    catch(NullPointerException e){
                        try {
                            //JSONObject message=new JSONObject();
                            message.put("type", "stop");
                            System.out.print(message);
                            ps.print(message);
                            System.out.print("closed");
                            stopConnection();
                            System.exit(0);
                            
                            
                        } catch (JSONException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        }
             }

                    
                //}
            //});
            /*add(scroll);
            add(tf);
            add(okButton);
            add(closeButton);
            add(t);*/
        }

        /*public void appendMsg(String msg) {

            ta.append(msg + "\n");
        }*/
    }
    
    
    
    public void sendStopSignalToServer() {
    	try {
			message.put("type", "stop");
			System.out.print(message);
	        ps.print(message);
	        System.out.print("closed");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void stopConnection() {
        try {
            clientSocket.close();   
        } catch (IOException ex) {
            System.out.print("hi");
            //Logger.getLogger(ClientChat.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(NullPointerException e) {System.out.print("closed");}
    }

}
