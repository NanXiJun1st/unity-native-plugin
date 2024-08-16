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

    ///获取设备已安装应用列表
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

    // 判断是否安装了指定包名的应用
    public static boolean isAppInstalled(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        try {
            // 尝试获取包信息，如果存在则返回true
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            return packageInfo != null;
        } catch (PackageManager.NameNotFoundException e) {
            // 如果抛出异常，则说明未安装
            return false;
        }
    }

}
