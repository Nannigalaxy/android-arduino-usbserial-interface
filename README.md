# android-arduino-usbserial-interface
USB Serial interface between arduino to android

>Note: No external power supply is needed for arduino. Phone will power the arduino via usb.<br>
Phone should have OTG support.

<br><br>
### Air Quality monitor app
<img src="https://i.imgur.com/NTvAdMl.jpg" width="200">


Arduino:
```Serial.write()```

Android:
<br>To read serial data which is in bytes<br>
```byte[] response = new bytes[3];``` <br>
port.read(response, READ_WAIT_MILLIS);```

## Refer
https://github.com/mik3y/usb-serial-for-android
