package com.mango.getcaller;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;

public class TargetActivity extends AppCompatActivity {
    String TAG = "MYDEMO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri caller1 = getReferrer();
        if (caller1 != null)
            Log.e(TAG, "getReferrer() : " + caller1.toString());

        String caller2 = getCallingPackage();
        String caller3 = String.valueOf(getCallingActivity());
        Log.e(TAG, "getCallingPackage : " + caller2);
        Log.e(TAG, "getCallingActivity : " + caller3);

        try {
            Field reflectfield = Class.forName("android.app.Activity").getDeclaredField("mReferrer");
            reflectfield.setAccessible(true);
            String caller4 = (String)reflectfield.get(this);
            Log.e(TAG, "mReferrer : " + caller4);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int caller5 = this.getLaunchedFromUid();
        String caller6 = this.getLaunchedFromPackage();
        Log.e(TAG, "getLaunchedFromUid : " + caller5);
        Log.e(TAG, "getLaunchedFromPackage : " + caller6);

    }
}
