package org.example;

import org.lwjgl.opengl.GL11;

public class Line{

    public float x1, y1, x2, y2;

    public float[][] vertexes;

    Line(float x1, float y1, float x2, float y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        this.vertexes = new float[][]{{x1, y1}, {x2, y2}};
    }

    public void draw(){
        GL11.glLineWidth(5.0f);
        GL11.glBegin(GL11.GL_LINES);

        GL11.glColor3f(0.0f, 0.0f, 1.0f);
        GL11.glVertex2f(x1, y1);

        GL11.glVertex2f(x2, y2);

        GL11.glEnd();
    }
}
