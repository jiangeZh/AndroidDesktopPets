package com.luoxl.androidDesktopPets;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by zhuangqf on 4/24/16.
 */
public class myBluetoothService extends Service {
    @Override
    public IBinder onBind(Intent i){return null;}
    private boolean ListenOrSearch = false;
    private String address=null;


    @Override
    public int onStartCommand(Intent i,int flags,int id){
        myBluetoothManager.setContext(this);
        myBluetoothManager.setConnectionListener(this);
        myBluetoothManager.setReceivedListener(this);

        ListenOrSearch = i.getBooleanExtra("ListenOrSearch",true);
        if(!ListenOrSearch) address = i.getStringExtra(BluetoothState.DEVICE_ADDRESS);
        myBluetoothManager.bt.setupService();
        myBluetoothManager.bt.setContext(this);


        if(ListenOrSearch) Listen();
        else Search();
        return 0;
    }

    private void Listen(){
        myBluetoothManager.bt.startService(true);
    }
    private void Search(){
        myBluetoothManager.bt.connect(address);
    }


    @Override
    public void onDestroy(){
        Toast.makeText(this,"Service end",Toast.LENGTH_LONG).show();
        myBluetoothManager.stop(this);
    }
}
