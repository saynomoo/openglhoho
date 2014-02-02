package com.example.openglhoho;


import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class GLES20Activity extends Activity {


    public static final String EXAMPLE = "INTENT_EXTRA_EXAMPLE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Go fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final OpenGLRenderer renderer = new OpenGLRenderer(this, createShape(shapeIndex()));
        GLSurfaceView view = new GLSurfaceView(this){
            @Override
            public boolean onTouchEvent(MotionEvent e) {
                float x = e.getX();
                float y = e.getY();
                float dx = (2*x - getWidth())/getWidth();
                float dy = (2*y - getHeight())/getHeight();
                renderer.setRotationX(dy);
                renderer.setRotationY(dx);
                renderer.setAngle((float) Math.sqrt(dx*dx+dy*dy));
                return true;
            }
        };
        view.setRenderer(renderer);
        setContentView(view);
    }

    private int shapeIndex() {
        return getIntent().getIntExtra(EXAMPLE, 0);
    }
    private Shape createShape(int position) {
        switch (position) {
            case 0: return new Cube(new int[1]);
            case 1: return new Donut(15,15);
        }
        return null;
    }
}