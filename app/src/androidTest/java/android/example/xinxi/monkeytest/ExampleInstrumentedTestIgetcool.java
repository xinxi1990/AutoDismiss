package android.example.xinxi.monkeytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTestIgetcool {

    String TAG = "ExampleInstrumentedTestIgetcool";
    private String mPackageName="android.example.autodismiss";
    public UiDevice mDevice;
    public boolean Flag = true;
    public int MaxRetryCout = 30;
    ArrayList<String> TextList = new ArrayList<String>(Arrays.asList("继续安装","安装","继续安装旧版本", "下一步", "完成", "停止"));
    ArrayList<String> IDList = new ArrayList<String>(Arrays.asList("com.android.packageinstaller:id/btn_continue_install_old"));
    private  ByteArrayOutputStream baos = new ByteArrayOutputStream();

    @Test
    public void useAppContext() throws InterruptedException, IOException {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        while (Flag){

            for (String str:TextList) {
                Log.e(TAG,"循环遍历TEXT列表");
                Log.e(TAG,(String.format("遍历TEXT : %s", str)));
                UiObject2 uiObject2 = mDevice.findObject(By.text(str));
                if (uiObject2 != null ){
                    uiObject2.click();
                    Log.e(TAG,(String.format("点击:%s", uiObject2.getText())));
                }
            }

            for (String str:IDList) {
                Log.e(TAG,"************ 获取页面元素 *************");
                mDevice.dumpWindowHierarchy(baos);
                baos.flush();
                String[] lines = baos.toString().split(System.lineSeparator());
                for (String line : lines) {
                    Log.e(TAG, line.trim());
                }
                Log.e(TAG,"************ 获取页面元素 *************");

                Log.e(TAG,(String.format("遍历ID : %s", str)));
                UiObject2 uiObject2 = mDevice.findObject(By.res(str));
                if (uiObject2 != null ){
                    uiObject2.click();
                    Log.e(TAG,(String.format("点击:%s", uiObject2.getResourceName())));
                }
            }

            SendInstallPassWord();

            MaxRetryCout --;
            Thread.sleep(5000);
            if (MaxRetryCout < 0){
                Log.e(TAG, "退出任务...");
                Flag = false;

            }

        }

    }

    public  void SendInstallPassWord(){
        UiObject2 uiObject2 =  mDevice.findObject(By.res("com.coloros.safecenter:id/et_login_passwd_edit"));
        // oppo手机输入密码框
        //UiObject2 uiObject2 =  mDevice.findObject(By.res("com.bbk.account:id/dialog_pwd"));
        // vivo手机输入密码框
        if (uiObject2 != null ){
            uiObject2.setText("qa123456");
            Log.e(TAG,(String.format("点击: %s ", uiObject2.getText())));
        }

    }


    @Before
    public void beforeCase() throws UiObjectNotFoundException, IOException {



        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        Bundle bundle = InstrumentationRegistry.getArguments();
        try {
            if(!mDevice.isScreenOn()){
                mDevice.wakeUp();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        startAPP(mPackageName);
        mDevice.waitForWindowUpdate(mPackageName, 5 * 1000); // 等待app

    }

    /**
     * 通过Intent启动app
     * @param sPackageName
     */
    private void startAPP(String sPackageName){
        Context mContext = InstrumentationRegistry.getContext();
        Intent myIntent = mContext.getPackageManager().getLaunchIntentForPackage(sPackageName);
        mContext.startActivity(myIntent);
        Log.e(TAG, "app启动中...");
    }

    @After
    public void atferCase(){
        closeAPP(mDevice,mPackageName);
    }

    /**
     * 通过命令行关闭app
     * @param sPackageName
     */
    private void closeAPP(UiDevice uiDevice,String sPackageName){
        Log.e(TAG, "关闭app...");
        try {
            uiDevice.executeShellCommand("am force-stop "+sPackageName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
