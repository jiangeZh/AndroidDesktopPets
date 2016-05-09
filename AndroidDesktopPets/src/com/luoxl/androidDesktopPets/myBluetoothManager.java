package com.luoxl.androidDesktopPets;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnLongClickListener;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhuangqf on 4/29/16.
 */
public class myBluetoothManager {

    public static BluetoothSPP bt = new BluetoothSPP();
    private static String Myaddress = null;
    private static int t = 0;
    private static boolean LorS;


    public static boolean isAvailable(){ return  bt.isBluetoothAvailable();}

    public static boolean isEnable(){ return  bt.isBluetoothEnabled();}

    public static void enable(){ bt.enable(); }

    public static boolean isScanMode() {
        return bt.getBluetoothAdapter().getScanMode()
                == BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE;
    }

    public static void stop(Context context){
        bt.stopService();
        bt.disconnect();
        bt.getBluetoothAdapter().disable();
        Intent i = new Intent(context,myBluetoothService.class);
        context.stopService(i);
    }

    public static void Listen(){bt.startService(true); LorS = true;}

    public static void Search(String address){ bt.connect(address); LorS = false;}

    public static void setupService(){
        bt.setupService();
    }

    public static void setContext(Context context) {
        bt.setContext(context);
    }

    private static void openBluetooth(Context context) {
		MyWindowManager.createBluetoothMessageWindow(context);
	}
    
    private static void openBluetooth(Context context, String message) {
		MyWindowManager.createBluetoothMessageWindow(context, message);
	}
    
    public static void setMyaddress(String myaddress){ Myaddress = new String(myaddress);}

    public static void setReceivedListener(final Context context) {
        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() {
            public void onDataReceived(byte[] data, String message) {
                //Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            	openBluetooth(context, message);
            }
        });
    }

    public static void setConnectionListener(final Context context) {
        //Listener for bluetooth connection status
        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {

            //定时发送消息
            public void onDeviceConnected(String name, String address) {
            	/*
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                Thread.sleep(10000);

                                SimpleDateFormat formatter= new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ");
                                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                                String str = formatter.format(curDate);
                                bt.send(str,true); //发送消息（消息，是否加/r/n）
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                thread.start();
                */
                FloatWindowPetView.petView.setClickable(true);
            	FloatWindowPetView.petView.setOnLongClickListener(new OnLongClickListener(){

					@Override
					public boolean onLongClick(View v) {
						// TODO Auto-generated method stub
						// TODO Auto-generated method stub
						openBluetooth(context);
						return false;
					}
            		
            	});
            }
            public void onDeviceDisconnected() {
            		FloatWindowPetView.petView.setClickable(false);
                    Toast.makeText(context, "连接中断", Toast.LENGTH_SHORT).show();
                    stop(bt.getmContext());
            }

            public void onDeviceConnectionFailed() {
                    Toast.makeText(context, "连接失败", Toast.LENGTH_SHORT).show();
                stop(bt.getmContext());
            }
        });
    }

}
