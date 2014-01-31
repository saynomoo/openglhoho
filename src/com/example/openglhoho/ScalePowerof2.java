package com.example.openglhoho;

import android.graphics.Bitmap;

public class ScalePowerof2 { // some devices require to scale texture bitmaps to power of 2 size 'ScalePower2.scalePowerof2(Bitmpap)' does the job
    private static boolean isPowerof2(int v) { // is v a 2^n ?
        for (int i=1; i!=0; i<<=1) if (v==i) return true;
        return false;
    }
    private static int closestPowerof2(int v) { // n | 2^n <= v
        for (int i=1; i!=0; i<<=1) if (i>=v) return i;
        return 0;
    }
    public static Bitmap scalePowerof2(Bitmap bmp) {
        Bitmap b=null;
        if (bmp!=null) {
            int w=bmp.getWidth(), h=bmp.getHeight();
            if (isPowerof2(w) & isPowerof2(h)) return bmp; // ok, both 2^n nothing to do
            w=closestPowerof2(w); h=closestPowerof2(h); // new 2^n values
            b=Bitmap.createScaledBitmap(bmp, w, h, false);
        }
        return b;
    }
}