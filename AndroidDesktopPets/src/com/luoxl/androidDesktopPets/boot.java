package com.luoxl.androidDesktopPets;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class boot extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED"));
        Intent i = new Intent(context,MainActivity.class);
        i.putExtra("boot", true);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

}
