 /////////////////////////////////
// Generated with a lot of love//
// with TUNIOT FOR ESP8266     //
// Website: Easycoding.tn      //
/////////////////////////////////
#include <ESP8266WiFi.h>
#include <WiFiUdp.h>

unsigned int UDPPort = 2380; // local port to listen on
char packetBuffer[255]; //buffer to hold incoming packet
char  ReplyBuffer[] = "acknowledged";// a string to send back

WiFiUDP Udp;

const IPAddress local(11, 11, 11, 11);        //Ip configuration,put what you want
const IPAddress dns(11, 11, 11, 11);
const IPAddress netmask(255, 255, 255, 0);
const IPAddress remote(11,11,11,110);


String received;


void setup()
{
  ///////////////////////////////////////////Wifi Configuration//////////////////////////////////////////////////////////////
  
    WiFi.softAPdisconnect(true);
    WiFi.disconnect(true);
    Serial.begin(115200);
    WiFi.mode(WIFI_AP); 
    WiFi.softAPConfig(local, dns, netmask);
    WiFi.softAP("XXX");                     //Name of the wifi you want to create
    WiFi.begin();

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


  ////////////////////////////////////////////////UDP////////////////////////////////////////////////////////////////////////////


    Udp.begin(UDPPort);                                                               //////listening to the port UDPPort///////
    Serial.printf("Now listening at IP %s, UDP port %d\n", WiFi.softAPIP().toString().c_str(), TestPort);


           int packetSize = Udp.parsePacket();                                        ////verify if a packet have been received//
 
           if (packetSize)
           
            {
              // receive incoming UDP packets
              Serial.printf("Received %d bytes from %s, port %d\n", packetSize, Udp.remoteIP().toString().c_str(), Udp.remotePort());
          
              int len = Udp.read(packetBuffer, 255);                                     //////read the buffer////////////////////
          
              
              
              if (len > 0)
              {
               packetBuffer[len] = 0;
              }
              received=String(packetBuffer);                                                 /////convert it to a string////////


              
                            Serial.printf("UDP packet contents: %s\n", testBuffer);
                        
           //////////////////////// send back a reply, to the IP address and port we got the packet from////////////////////////
           
                            Udp.beginPacket(Udp.remoteIP(), Udp.remotePort()); 
                            Udp.write(ReplyBuffer);
                            Udp.endPacket();

                

            }
      
      
}


void loop()
{
    

}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

