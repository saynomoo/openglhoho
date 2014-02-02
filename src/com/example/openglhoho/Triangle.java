package com.example.openglhoho;

import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static com.example.openglhoho.Cube.initByteBuffer;
import static com.example.openglhoho.Cube.initFloatBuffer;

public class Triangle implements Shape {

    private FloatBuffer mVertexBuffer = initFloatBuffer(new float[]{
              0f, 1f, 0f,
              -1, 0f, 1f,
              -1, 0f, -1f
    });
    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CW);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}