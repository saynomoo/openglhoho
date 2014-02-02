package com.example.openglhoho;


import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLES20Activity extends Activity {


    public static final String EXAMPLE = "INTENT_EXTRA_EXAMPLE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Go fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final OpenGLRenderer renderer = createRenderer(shapeIndex());
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

    private OpenGLRenderer createRenderer(int position) {
        switch (position) {
            case 0: return new OpenGLRenderer(this, null) {
                @Override
                public void onSurfaceCreated(GL10 gl, EGLConfig config) {
                    super.onSurfaceCreated(gl, config);
                    translationZ = -10f;
                    shape = new Cube(loadGLTexture(gl, getBaseContext(), R.drawable.lemur));
                }
            };
            case 1: return new OpenGLRenderer(this, new Donut(15,15));
            case 2: return new OpenGLRenderer(this, null) {
                @Override
                public void onSurfaceCreated(GL10 gl, EGLConfig config) {
                    super.onSurfaceCreated(gl, config);
                    shape = new Particles(100);
                }
            };

        }
        return null;
    }

    private int shapeIndex() {
        return getIntent().getIntExtra(EXAMPLE, 0);
    }
}
