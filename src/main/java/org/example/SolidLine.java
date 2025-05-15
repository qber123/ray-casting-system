package org.example;

public class SolidLine extends SolidObject{
    SolidLine(float x1, float y1, float x2, float y2){
        this.lines = new Line[]{new Line(x1, y1, x2, y2)};
    }

    public void draw(){
        this.lines[0].draw();
    }
}
