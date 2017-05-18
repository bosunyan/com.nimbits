/**
 * Connects an arduino to a nimbits server running on a linux server (not a cloud) using websockets so it can recieve push notifications
 * this watches a nimbits data point called switch1 and turns someone on or off based on the value. 
 * see the switch tutorial on nimbits.com 
 **/
 

#include "Arduino.h"
#include <Ethernet.h>
#include <SPI.h>
#include <Nimbits.h>
#include <ArduinoJson.h>


#define POINT_COUNT 1

byte mac[] = { 
  0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
char server[] ="192.168.1.21"; 

char email[] = "x@x.xom";

//this can be your password, a user token, server token or session token.
//see http://nimbits.com/howto_security.jsp
char password[] ="x";
 
int port = 8080;

String switchPoint = "switch1";
String points[POINT_COUNT] = { 
  switchPoint };
 

const int ledPin =  8;      // the number of the LED pin
int ledState = HIGH;
unsigned long previousMillis = 0; 
const long interval = 5000; 

Nimbits client(server, port);

void setup() {
  pinMode(ledPin, OUTPUT);    
  Serial.begin(9600);

  Ethernet.begin(mac);

  Serial.println("Logging in...");
  //after using the password for the first time, switch to using the session token which is safer.
  String authToken = client.login(email, password);
  Serial.println("Got Auth Token: " + authToken);
  double currentSwitch = client.getValue(switchPoint);
  if (currentSwitch == 1.0) {
    digitalWrite(ledPin, HIGH);

  }
  if (currentSwitch == 0.0) {
    digitalWrite(ledPin, LOW);

  }
  Serial.println(currentSwitch);

  client.setDataArrivedDelegate(incoming);
  client.connectSocket(points, POINT_COUNT);
  Serial.println("Ready");
}

void loop() {
  String name1 = "a";
  String name2 = "b";
  client.monitorSocket();
  unsigned long currentMillis = millis();

  if(currentMillis - previousMillis >= interval) {

    previousMillis = currentMillis;   

    client.recordValue(random(300), name1); 
    client.recordValue(random(300), name2); 
    client.sendSocketMessage("Keep Alive");
    double currentSwitch = client.getValue(switchPoint);

    if (currentSwitch == 1.0) {
      client.recordValue(0.0, switchPoint); 

    }
    if (currentSwitch == 0.0) {
      client.recordValue(1.0, switchPoint); 

    }
  }





}

void incoming(String point, double value) {
  if (point == switchPoint) {
    if (value == 1.0) {

      digitalWrite(ledPin, HIGH);

    }
    else {

      digitalWrite(ledPin, LOW);

    }
  }

}





