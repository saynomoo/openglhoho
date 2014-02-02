package com.example.openglhoho;

import javax.microedition.khronos.opengles.GL10;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Random;

import static com.example.openglhoho.Cube.initFloatBuffer;

public class Particles implements Shape{
    private final Triangle triangle;
    private final Random rnd;
    private final Particle[] particles;
    int amount;
    private float distance;
    int maxAge = 100;
    private int[] textures;
    private FloatBuffer mTextureBuffer = initFloatBuffer(new float[]{
            0.0f, 1.0f,     // top left     (V2)
            0.0f, 0.0f,     // bottom left  (V1)
            1.0f, 1.0f,     // top right    (V4)
    });

    public Particles(int amount, float distance, int[] textures) {
        this.amount = amount;
        this.distance = distance;
        this.textures = textures;
        triangle = new Triangle();
        rnd = new Random();
        ArrayList<Particle> al = new ArrayList<Particle>();
        for(int i=0; i<amount; i++) {
            al.add(refreshParticle(new Particle()));
        }
        this.particles = al.toArray(new Particle[al.size()]);
    }

    @Override
    public void draw(GL10 gl) {
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);

        for (int i = 0; i < particles.length; i++) {
            Particle particle = particles[i];
            if(particle.age==maxAge)refreshParticle(particle); else particle.age++;
            if(particle.age>=0) {
                int age = particle.age;
                gl.glPushMatrix();
                float relativeAge = (float) age / maxAge;
                gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f - relativeAge);
                gl.glRotatef(particle.angle, particle.rotX, particle.rotY, particle.rotZ);
                float dist = relativeAge*distance;
                gl.glTranslatef(dist, dist, dist);
                triangle.draw(gl);
                gl.glPopMatrix();
            }
        }
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    }

    private Particle refreshParticle(Particle particle) {
        particle.age = -rnd.nextInt(maxAge);
        particle.angle = 360f * rnd.nextFloat();
        particle.rotX = 2*rnd.nextFloat()-1;
        particle.rotY = 2*rnd.nextFloat()-1;
        particle.rotZ = 2*rnd.nextFloat()-1;
        return particle;
    }
}
class Particle {
    float angle;
    float rotX;
    float rotY;
    float rotZ;
    int age = 0;
}
