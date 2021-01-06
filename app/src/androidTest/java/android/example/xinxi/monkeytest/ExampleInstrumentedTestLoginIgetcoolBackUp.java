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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 * adb shell am instrument -w -r -e phone 12345000000 -e debug false -e class 'android.example.xinxi.monkeytest.ExampleInstrumentedTestLoginIgetcool' android.example.autodismiss.test/android.support.test.runner.AndroidJUnitRunner

 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTestLoginIgetcoolBackUp {

    String TAG = "ExampleInstrumentedTestLoginIgetcool";
    //private String mPackageName="android.example.autodismiss";
    private String mPackageName = "com.dedao.juvenile";
    private String mActivityName = "com.dedao.juvenile.business.splash.SplashActivity";
    private String btnAgree = "com.dedao.juvenile:id/btnAgree";
    private String btnSkip ="com.dedao.juvenile:id/tvSkip";
    private String notificationBtn = "com.dedao.juvenile:id/notificationBtn";
    private String etMobile = "com.dedao.juvenile:id/etMobile";
    private String sendSmsBtn = "com.dedao.juvenile:id/btnSend";
    //private String phone;
    private String phone = "12300000001";
    public UiDevice mDevice;
    public boolean Flag = true;
    public int MaxRetryCout = 30;
    public int WaitTime = 2000;
    public int LongWaitTime = 5000;
    ArrayList<String> permissionTextList = new ArrayList<String>(Arrays.asList("始终允许","允许","总是允许"));
    ArrayList<String> IDList = new ArrayList<String>(Arrays.asList("com.android.packageinstaller:id/btn_continue_install_old"));
    private  ByteArrayOutputStream baos = new ByteArrayOutputStream();

    @Test
    public void useAppContext() throws InterruptedException, IOException {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Log.i(TAG, "Start Lanuch App");

        this.InitPermission();
        this.LoginApp();

    }


    public void InitPermission() throws InterruptedException {

        UiObject2 btnAgreeObject2 = mDevice.findObject(By.res(btnAgree));
        if (btnAgreeObject2 != null){
            mDevice.findObject(By.res(btnAgree)).click();
            Log.e(TAG,"点击启动同意权限按钮");
        }

        Thread.sleep(WaitTime);
        for (String str:permissionTextList) {
            Log.e(TAG,"循环遍历Text列表");
            Log.e(TAG,(String.format("遍历Text : %s", str)));
            UiObject2 uiObject2 = mDevice.findObject(By.text(str));
            if (uiObject2 != null ){
                uiObject2.click();
                Log.e(TAG,(String.format("点击:%s", uiObject2.getText())));
            }
        }

        Thread.sleep(LongWaitTime);
        for (int i = 0; i < 2; i++) {
            UiObject2 btnSkipObject2 = mDevice.findObject(By.res(btnSkip));
            if (btnSkipObject2 != null){
                btnSkipObject2.click();
                Log.e(TAG,"点击跳过按钮");
            }
        }

        Thread.sleep(LongWaitTime);


    }


    /**
     * 登录用例
     */
    public void LoginApp() throws InterruptedException, IOException {
        Log.e(TAG, String.format("测试手机号:%s",phone));
        mDevice.findObject(By.res(notificationBtn)).click();
        Thread.sleep(WaitTime);
        mDevice.findObject(By.res(etMobile)).click();
        Thread.sleep(WaitTime);
        mDevice.findObject(By.res(etMobile)).setText(phone);
        Thread.sleep(WaitTime);
        mDevice.findObject(By.res(sendSmsBtn)).click();
        Thread.sleep(WaitTime);
        mDevice.executeShellCommand("input text 8888");
        Thread.sleep(WaitTime);
    }





    @Before
    public void beforeCase() throws UiObjectNotFoundException, IOException {

        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        Bundle bundle = InstrumentationRegistry.getArguments();
        phone  = String.valueOf(bundle.getString("phone"));
        Log.e(TAG, String.format("测试手机号:%s",phone));
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
    private void startAPP(String sPackageName) throws IOException {
        Context mContext = InstrumentationRegistry.getContext();
        Intent myIntent = mContext.getPackageManager().getLaunchIntentForPackage(sPackageName);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mContext.startActivity(myIntent);
        Log.e(TAG, "App启动中...");
        mDevice.executeShellCommand(String.format("am start -n %s/%s", mPackageName,mActivityName));
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
        Log.e(TAG, "关闭App...");
        try {
            uiDevice.executeShellCommand("am force-stop " + sPackageName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
