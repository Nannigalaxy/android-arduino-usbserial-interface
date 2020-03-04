# android-arduino-usbserial-interface
USB Serial interface between arduino to android and python

>Note: No external power supply is needed for arduino. Phone will power the arduino via usb.<br>
Phone should have OTG support.

<br><br>
### HAQT app
<img src="https://i.imgur.com/ISrUXYS.jpg" width="200">

### Python script
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

## Refer
https://github.com/mik3y/usb-serial-for-android
