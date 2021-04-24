# arduino-usbserial-interface
USB Serial interface between arduino to android and python

>Note: No external power supply is needed for arduino. Phone will power the arduino via usb.<br>
Phone should have OTG support.

<br><br>
### HAQT app <a href="https://drive.google.com/open?id=1QkfwZ5lyCaG8pm2cIbEduOcPqybLoP3Q" target="_blank">(link)</a><br>

<img src="https://i.imgur.com/ISrUXYS.jpg" width="200">

### Python
<img src="https://i.imgur.com/fgwAiWX.png" width="700">

Arduino:
```Serial.write()```

Python:<br>
library: pyserial<br>
```arduino = serial.Serial('COM4', 9600, timeout=0)```<br>
```data = arduino.readline() ```

Android:
<br>To read serial data which is in bytes<br>
```byte[] response = new bytes[3];``` <br>
```port.read(response, READ_WAIT_MILLIS);```

Ubuntu:  
`sudo apt-get install python-serial`  
Change this line to  
`arduino = serial.Serial('/dev/ttyACM0', 9600, timeout=0)`  
## Refer
https://github.com/mik3y/usb-serial-for-android
