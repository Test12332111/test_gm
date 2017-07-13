package com.example.mac_204.test.util;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;


public class ScreenUtil {


    public static int convertDIPToPixels(Context context, int dip) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, displayMetrics);
    }

    public static int convertDIPToPixels(Context context, float dip) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, displayMetrics);
    }

    public static float convertPixelsToDIP(Context context, int pixels) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return pixels / (displayMetrics.densityDpi / 160f);
    }

    public static Point getScreenDimensionsInDIP(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Configuration configuration = context.getResources().getConfiguration();
            return new Point(configuration.screenWidthDp, configuration.screenHeightDp);

        } else {

            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);

            int screenWidthInDIP = (int) convertPixelsToDIP(context, displayMetrics.widthPixels);
            int screenHeightInDIP = (int) convertPixelsToDIP(context, displayMetrics.heightPixels);
            return new Point(screenWidthInDIP, screenHeightInDIP);
        }
    }

    public static boolean isInLandscapeOrientation(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        return configuration.orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    public static boolean hasSmallScreen(Context context) {
        return getScreenSize(context) == Configuration.SCREENLAYOUT_SIZE_SMALL;
    }

    public static boolean hasNormalScreen(Context context) {
        return getScreenSize(context) == Configuration.SCREENLAYOUT_SIZE_NORMAL;
    }

    public static boolean hasLargeScreen(Context context) {
        return getScreenSize(context) == Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static boolean hasXLargeScreen(Context context) {
        return getScreenSize(context) == Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    public static int getScreenSize(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        return configuration.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
    }

    public static int getDisplayWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int getDisplayHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    public static int getMinDisplaySize(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        int result = height < width ? height : width;
        return result;
    }

    public static int getMaxDisplaySize(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        int result = height < width ? width : height;
        return result;
    }

}


