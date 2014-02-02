package com.example.openglhoho;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.opengl.GLUtils;

public class OpenGLRenderer implements Renderer {

    private Donut mCube;
    private float mCubeRotation = 1f;
    private Context context;

    public OpenGLRenderer(Context context) {
        this.context = context;
    }

    public void setRotationX(float rotationX) {
        this.rotationX = rotationX;
    }

    public void setRotationY(float rotationY) {
        this.rotationY = rotationY;
    }

    private float rotationX = 0.1f;
    private float rotationY = 0.1f;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);

        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glEnable(GL10.GL_TEXTURE_2D);
//        gl.glEnable(GL10.GL_BLEND);
  //      gl.glBlendFunc(GL10.GL_ONE, GL10.GL_SRC_COLOR);
        gl.glDepthFunc(GL10.GL_LEQUAL);

        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
                GL10.GL_NICEST);
        loadGLTexture(gl, context);
//        mCube = new Cube(textures);
        mCube = new Donut(15,15);

    }



    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glTranslatef(0.0f, 0.0f, -20.0f);
        gl.glRotatef(mCubeRotation, rotationX, rotationY, 0f);

        mCube.draw(gl);

        gl.glLoadIdentity();

        //mCubeRotation -= 0.15f;
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);
        gl.glViewport(0, 0, width, height);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void setAngle(float angle) {
        mCubeRotation = 180*angle;
    }

    /** The texture pointer */
    private int[] textures = new int[1];

    public void loadGLTexture(GL10 gl, Context context) {
        Bitmap b = BitmapFactory.decodeResource(context.getResources(), R.drawable.lemur);
        Bitmap bitmap = ScalePowerof2.scalePowerof2(b);
        gl.glGenTextures(1, textures, 0);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
        bitmap.recycle();
    }

}

