package com.example.openglhoho;

import javax.microedition.khronos.opengles.GL10;
import java.util.ArrayList;
import java.util.Random;

public class Particles implements Shape{
    private final Triangle triangle;
    private final Random rnd;
    private final Particle[] particles;
    int amount;
    private float distance;
    int maxAge = 100;

    public Particles(int amount, float distance) {
        this.amount = amount;
        this.distance = distance;
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
