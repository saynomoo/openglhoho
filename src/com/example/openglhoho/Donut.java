package com.example.openglhoho;

import org.apache.commons.lang.ArrayUtils;

import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;

public class Donut {

    private final FloatBuffer vertexBuffer;
    private final ByteBuffer indexBuffer;
    private final int length;

    public Donut(int x, int y) {
        float[] vertexes = createVertexes(x, y);
        vertexBuffer = Cube.initFloatBuffer(vertexes);
        byte[] indexes = createIndexes(x, y);
        length = indexes.length;
        indexBuffer = Cube.initByteBuffer(indexes);
    }

    private byte[] createIndexes(int x, int y) {
        ArrayList<Byte> al = new ArrayList<Byte>();
        for(int i=0; i<x-1; i++) {
            al.add((byte) (i*y));
            for(int j=0; j<y; j++) {
                al.add((byte) (i*y+j));
                al.add((byte) ((i+1)*y+j));
            }
            al.add((byte) (2*y-1));
        }
        return ArrayUtils.toPrimitive(al.toArray(new Byte[al.size()]));
    }

    private float[] createVertexes(int x, int y) {
        ArrayList<Float> al = new ArrayList<Float>();
        for(int i=0; i<x; i++) {
            double alphaAngle = Math.PI*2*i/x;
            for(int j=0; j<y; j++) {
                double betaAngle = Math.PI*2*j/y;
                double r = Math.sin(betaAngle)/3+0.6;
                al.add((float) (r*Math.cos(betaAngle)));
                al.add((float) (r*Math.sin(alphaAngle)));
                al.add((float) (r*Math.cos(alphaAngle)));
            }
        }
        return ArrayUtils.toPrimitive(al.toArray(new Float[al.size()]));
    }

    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CW);


        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
//        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);


        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
  //      gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer);


        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, length, GL10.GL_UNSIGNED_BYTE, indexBuffer);



        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
//        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

    }
}
