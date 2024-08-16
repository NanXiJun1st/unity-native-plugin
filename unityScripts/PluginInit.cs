using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class PluginInit : MonoBehaviour
{
    AndroidJavaClass unityClass;
    AndroidJavaObject unityActivity;
    AndroidJavaObject _pluginInstance;

    public Text init;
    public Text add;
    public Text man;
    public Text appListText;
    void Start()
    {
        InitializePlugin("com.gdyang.unityplugin.PluginInstance");
    }

    void InitializePlugin(string pluginName )
    {
        unityClass = new AndroidJavaClass( "com.unity3d.player.UnityPlayer");
        unityActivity = unityClass.GetStatic<AndroidJavaObject>("currentActivity");
        _pluginInstance = new AndroidJavaObject(pluginName);

        if (_pluginInstance == null)
        {
            Debug.Log("Plugin Instance Error");
        }
        _pluginInstance.CallStatic("receiveUnityActivity", unityActivity);
        init.text = "init success";
    }

    public void Add()
    {
        if (_pluginInstance != null)
        {
            var result = _pluginInstance.Call<int>("Add", 1, 1);
            Debug.Log($"result:{result}");    
            add.text = result.ToString();
        }
    }

    public void CurrentDeviceMan()
    {
        if (_pluginInstance != null)
        {
            var man = _pluginInstance.Call<string>("deviceManufacturer");
            Debug.Log(man);
            this.man.text = man;
        }
    }

    public void Toast()
    {
        if (_pluginInstance != null)
        {
            _pluginInstance.Call("Toast", "Hi! from Unity");
        }
    }

    public void AppList()
    {
        appListText.text = Application.version + "app list:";
        if (_pluginInstance != null)
        {
            //在 Java 中的 List（如 ArrayList）类型会作为 AndroidJavaObject 返回，需要在 Unity 中进行迭代处理
            var installApps = _pluginInstance.Call<AndroidJavaObject>("getInstalledApps", unityActivity);
            int size = installApps.Call<int>("size");

            for (int i = 0; i < size; i++)
            {
                string item = installApps.Call<string>("get", i);
                appListText.text += (item + ":");
            }
        }
    }
}
