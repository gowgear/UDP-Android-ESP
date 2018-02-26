import android.util.Log;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


/*////Implements runnable to launch it in a new thread and always listen for new packet///*/

public class UDP_Listening implements Runnable

{
    /*//////////////////Method to disable/enable the listening/////////////////////*/

    public static class runcontrol
    {
        public static Boolean run = true;
        public static Boolean getrun(){return run;}
        public static void setrun(Boolean newrun)
        {
            run=newrun;

        }
    }
    /*//////////////////////////////////////////////////////////////////////////////*/


    /*////////////////////////////////The Listener/////////////////////////////////*/
    public void run() {

        if(UDP_Listening.runcontrol.getrun() == false){
            Log.d("UDP", "receiver off");}

        while (UDP_Listening.runcontrol.getrun()== true) {   /*/////Verify that run is true //////*/
            try {
                Log.d("Received data", "im listening");
                DatagramSocket udpSocket = new DatagramSocket(12345);
                byte[] message = new byte[8000];
                DatagramPacket packet = new DatagramPacket(message,message.length);
                udpSocket.receive(packet);
                udpSocket.close();
                String received = new String(message, 0, packet.getLength());
                Log.d("Received data", received);
                UDP.RECEIVE_UDP.settext(received);

            }catch (IOException e) {
                Log.e("UDP client IOException", "error: ", e);
                {
                    Log.d("Received data", "im not listening anymore");
                    UDP_Listening.runcontrol.setrun(false);
                }
            }
        }}

}
