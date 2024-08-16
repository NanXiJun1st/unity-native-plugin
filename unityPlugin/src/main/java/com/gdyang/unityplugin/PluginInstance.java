package com.gdyang.unityplugin;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> getInstalledApps(Context context) {
        List<String> installedApps = new ArrayList<>();
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packages = packageManager.getInstalledPackages(0);

        for (PackageInfo packageInfo : packages) {
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                String packageName = packageInfo.packageName;
                String versionName = packageInfo.versionName;

                installedApps.add(packageName + " - " + versionName);
            }
        }
        return installedApps;
    }

}
