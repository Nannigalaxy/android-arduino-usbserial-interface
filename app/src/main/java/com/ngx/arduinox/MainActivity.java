package com.ngx.arduinox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;

import java.io.IOException;
import android.os.Handler;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler();

    TextView textView1,textView2,textView3,textView4, textppm;
    CardView l1,l2,l3;
    private static final String ACTION_USB_PERMISSION =
            "com.android.example.USB_PERMISSION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler.removeCallbacks(updateTimeTask);
        handler.postDelayed(updateTimeTask, 1000);
    }

    private Runnable updateTimeTask = new Runnable() {
        public void run() {
            refresh();
            handler.postDelayed(this, 3000);
        }
    };
    void refresh(){

        UsbSerialPort port;
        byte[] response=new byte[4];
        l1= findViewById(R.id.humid_);
        l2= findViewById(R.id.temp_);
        l3= findViewById(R.id.aq_);
        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        textView3 = findViewById(R.id.text3);
        textView4 = findViewById(R.id.text4);
        textppm = findViewById(R.id.ppm);

        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        if (availableDrivers.isEmpty()) {
//            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
            return;
        }


        // Open a connection to the first available driver.

        UsbSerialDriver driver = availableDrivers.get(0);
        UsbDevice device=driver.getDevice();
        PendingIntent permissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
        manager.requestPermission(device, permissionIntent);
        UsbDeviceConnection connection = manager.openDevice(driver.getDevice());

//
//        HashMap<String, UsbDevice> usbDevices = manager.getDeviceList();
//        Collection<UsbDevice> ite = usbDevices.values();
//        UsbDevice[] usbs = ite.toArray(new UsbDevice[]{});
//        for (UsbDevice usb : usbs) {
//            Toast.makeText(this, usb.getVendorId() +" p: "+usb.getProductId(), Toast.LENGTH_SHORT).show();
//        }

        if (connection == null) {
//            Toast.makeText(this, "connection error (null)", Toast.LENGTH_SHORT).show();
            return;
        }

        port = driver.getPorts().get(0); // Most devices have just one port (port 0)
        try {
            textView1.setText("Loading");
            port.open(connection);
            port.setParameters(9600, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);
            port.read(response, 0);
            textView1.setVisibility(View.GONE);
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.VISIBLE);
            l3.setVisibility(View.VISIBLE);
            String humid = (response[0])+"";
            textView2.setText(humid);
            String temp = (response[1])+"";
            textView3.setText(temp);
            int mq = (int) response[2] & 0xff;
            String quality = "";

            if(mq>0 && mq<101){
                quality="Good";
                textView4.setTextColor(getColor(R.color.good_a));
            }
            if(mq>100 && mq<150){
                quality="Moderate";
                textView4.setTextColor(getColor(R.color.mod_a));
            }
            if(mq>149){
                quality="Unhealthy";
                textView4.setTextColor(getColor(R.color.unhealthy_a));
            }
            String airq =  mq+" PPM";
            textView4.setText(quality);
            textppm.setText(airq);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(updateTimeTask);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.removeCallbacks(updateTimeTask);
        handler.postDelayed(updateTimeTask, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ( handler != null )
            handler.removeCallbacks(updateTimeTask);
        handler = null;
    }



}
