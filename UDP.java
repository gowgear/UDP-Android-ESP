
import android.app.Activity;
import android.util.Log;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class UDP extends Activity
{
    UDP_Listening.runcontrol.setrun(true);
    new Thread(new UDP_Listening()).start();


    /*//////////////////////function to send an UDP packet//////////////////////////////////*/

    public void SEND_UDP()
    {
        UDP_Listening.runcontrol.setrun(false);         ///////Disable the listener to avoid IOExeption
        String messageStr="Hello ESP!";                //what you want to send
        int server_port = 2380;
        DatagramSocket s = null;
        try {
            s = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        InetAddress local = null;
        try {
            local = InetAddress.getByName("11.11.11.11"); ///////IP to send the packet
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        int msg_length=messageStr.length();
        byte[] message = messageStr.getBytes();
        DatagramPacket p = new DatagramPacket(message, msg_length,local,server_port);
        try {
            s.send(p);


        } catch (IOException e) {
            e.printStackTrace();

        }
        finally {
            s.close();
            Log.d("UDP send","closed" );
            UDP_Listening.runcontrol.setrun(true);                    ///////Enable the listener
        }
    }

    /*//////////////////////////////////////////////////////////////////////////////////////*/

    /*////////////////////////method to GET and SET text from the udp Receiver/////////////////*/


    public static class RECEIVE_UDP
    {
        private static String test;

        public static String gettext(){return test;}
        public static void settext(String received)
        {
            test=received;

        }



    }
   /*//////////////////////////////////////////////////////////////////////////////////////////://*/

}



