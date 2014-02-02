package com.example.openglhoho;

import org.apache.commons.lang.ArrayUtils;

import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;

public class Donut implements Shape {

    private final FloatBuffer vertexBuffer;
    private final ByteBuffer indexBuffer;
    private final int length;
    private final FloatBuffer colorBuffer;

    public Donut(int x, int y) {
        float[] vertexes = createVertexes(x, y);
        vertexBuffer = Cube.initFloatBuffer(vertexes);
        float[] colors = createColors(vertexes);
        colorBuffer = Cube.initFloatBuffer(colors);
        byte[] indexes = createIndexes(x+1, y+1);
        length = indexes.length;
        indexBuffer = Cube.initByteBuffer(indexes);
    }

    private float[] createColors(float[] vertexes) {
        ArrayList<Float> al = new ArrayList<Float>();
        for (int i = 0; i < vertexes.length; i++) {
            al.add(vertexes[i]);
            if(i>0 && i%3==0) al.add(1.0f);
        }
        return ArrayUtils.toPrimitive(al.toArray(new Float[al.size()]));
    }

    private byte[] createIndexes(int x, int y) {
        ArrayList<Byte> al = new ArrayList<Byte>();
        for(int i=0; i<x-1; i++) {
            if(i>0)al.add((byte) (i*y));
            for(int j=0; j<y; j++) {
                al.add((byte) (i*y+j));
                al.add((byte) ((i+1)*y+j));
            }
            al.add((byte) ((i+1)*y+y-1));
        }
        return ArrayUtils.toPrimitive(al.toArray(new Byte[al.size()]));
    }

    private float[] createVertexes(int x, int y) {
        ArrayList<Float> al = new ArrayList<Float>();
        for(int i=0; i<=x; i++) {
            double alphaAngle = Math.PI*2*i/x;
            double ry = 2*Math.cos(alphaAngle);
            double rz = 2*Math.sin(alphaAngle);
            for(int j=0; j<=y; j++) {
                double betaAngle = Math.PI*2*j/y;
                al.add((float) (Math.sin(betaAngle)));
                al.add((float) (Math.cos(betaAngle)*Math.cos(alphaAngle) + ry));
                al.add((float) (Math.sin(alphaAngle)*Math.cos(betaAngle) + rz));
            }
        }
        return ArrayUtils.toPrimitive(al.toArray(new Float[al.size()]));
    }

    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CW);


        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);


        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);


        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, length, GL10.GL_UNSIGNED_BYTE, indexBuffer);



        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

    }
}
