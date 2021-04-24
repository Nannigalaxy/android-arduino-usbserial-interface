#include <dht11.h>

#define dht_pin A1
#define mq_pin A0

dht11 DHT11;
int mq_val;

void setup(){  
  
Serial.begin(9600); 
 }
 
 
void loop(){
//  if(Serial.available()){
int chk = DHT11.read(dht_pin);
mq_val = analogRead(0);
uint8_t b = mq_val;

Serial.write(DHT11.humidity);
Serial.write(DHT11.temperature);
Serial.write(b);       
//}
delay(1000);                                  
}
