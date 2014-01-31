package com.example.openglhoho;

import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Cube {

    private FloatBuffer mVertexBuffer = initFloatBuffer(new float[]{
            -1.0f, -1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,
            1.0f, 1.0f, -1.0f,
            -1.0f, 1.0f, -1.0f,
            -1.0f, -1.0f, 1.0f,
            1.0f, -1.0f, 1.0f,
            1.0f, 1.0f, 1.0f,
            -1.0f, 1.0f, 1.0f
    });
    private FloatBuffer mColorBuffer = initFloatBuffer(new float[]{
            0.0f, 1.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 1.0f,
            1.0f, 0.5f, 0.0f, 1.0f,
            1.0f, 0.5f, 0.0f, 1.0f,
            1.0f, 0.0f, 0.0f, 1.0f,
            1.0f, 0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f, 1.0f,
            1.0f, 0.0f, 1.0f, 1.0f
    });
    private ByteBuffer  mIndexBuffer = initByteBuffer(new byte[]{
            0, 4, 5, 0, 5, 1,
            1, 5, 6, 1, 6, 2,
            2, 6, 7, 2, 7, 3,
            3, 7, 4, 3, 4, 0,
            4, 7, 6, 4, 6, 5,
            3, 0, 1, 3, 1, 2
    });
    private ByteBuffer  mTextureIndexBuffer = initByteBuffer(new byte[]{
            4, 7, 6, 4, 6, 5
    });
    private FloatBuffer mTextureBuffer = initFloatBuffer(new float[]{
            0.0f, 1.0f,     // top left     (V2)
            0.0f, 0.0f,     // bottom left  (V1)
            1.0f, 1.0f,     // top right    (V4)
            1.0f, 0.0f      // bottom right (V3)
    });
    private int[] textures;

    public Cube(int[] textures) {
        this.textures = textures;
    }

    public static FloatBuffer initFloatBuffer(float[] vertices) {
        ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        FloatBuffer buffer = byteBuf.asFloatBuffer();
        buffer.put(vertices);
        buffer.position(0);
        return buffer;
    }

    public static ByteBuffer initByteBuffer(byte[] vertices) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length);
        buffer.put(vertices);
        buffer.position(0);
        return buffer;
    }

    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CW);


        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
//        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);


        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
//        gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer);


//        gl.glDrawElements(GL10.GL_TRIANGLES, 36, GL10.GL_UNSIGNED_BYTE, mIndexBuffer);


        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, 6, GL10.GL_UNSIGNED_BYTE, mTextureIndexBuffer);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

    }
}