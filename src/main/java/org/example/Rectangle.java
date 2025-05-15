package org.example;

import static org.lwjgl.opengl.GL11.*;

public class Rectangle extends SolidObject{

    Rectangle(float x1, float y1, float x2, float y2){
        this.lines = new Line[]{new Line(x1, y1, x1, y2), new Line(x1, y1, x2, y1), new Line(x1, y2, x2, y2), new Line(x2, y1, x2, y2)};

        this.vertexes = new float[][]{{x1, y2}, {x2, y2}, {x2, y1}, {x1, y1}};
    }

    public void draw(){
        glColor3f(0.0f, 1.0f, 0.0f);

        glBegin(GL_QUADS);
        glVertex2f(vertexes[0][0], vertexes[0][1]);
        glVertex2f(vertexes[1][0], vertexes[1][1]);
        glVertex2f(vertexes[2][0], vertexes[2][1]);
        glVertex2f(vertexes[3][0], vertexes[3][1]);
        glEnd();
    }

}
