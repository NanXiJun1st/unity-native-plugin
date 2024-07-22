package com.gdyang.unityplugin;

import android.app.Activity;
import android.os.Build;
import android.widget.Toast;

public class PluginInstance {

    public String deviceManufacturer()
    {
        return Build.MANUFACTURER;
    }

    public int add(int a,int b)
    {
        return a+b;
    }

    private static Activity unityActivity;

    public static void receiveUnityActivity(Activity tActivity)
    {
        unityActivity = tActivity;
    }

    public void Toast(String msg)
    {
        Toast.makeText(unityActivity,msg,Toast.LENGTH_SHORT).show();
    }
}
