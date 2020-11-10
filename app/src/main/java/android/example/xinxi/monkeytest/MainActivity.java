package android.example.xinxi.monkeytest;

import android.content.ComponentName;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.PermissionListener;
import com.yhao.floatwindow.Screen;
import com.yhao.floatwindow.ViewStateListener;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {


    private Button startButton;
    private Button stopButton;
    private Context context;
    private static String TAG = "MonkeyTest";
    private static String packageName = "";
    private static String runClassName = "android.support.test.runner.AndroidJUnitRunner";
    public volatile boolean exit = false;
    public static String runtime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    ;
    }



}
