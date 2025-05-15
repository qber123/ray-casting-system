package org.example;

import java.util.ArrayList;

public class Player {

    private float[] direction;

    private float x;
    private float y;

    Player(float x, float y){
        this.x = x;
        this.y = y;
        this.direction = new float[]{1.0f, 0.0f};
    }

    void draw(ArrayList<SolidObject> objects){
        MyFunctions.drawCircle(this.x, this.y, 0.1f);

        for (int i = -100; i <= 100; i++){
            float[] rayDirection = MyFunctions.rotateVector(direction,  -0.3f * i );
            Line ray = new Line(x, y, rayDirection[0] + x, rayDirection[1] + y);
            ray.draw();
            for(SolidObject object : objects){

                MyFunctions.drawPoint(MyFunctions.lineIntersectionDist(ray, object));
                MyFunctions.drawLine(new float[]{0.0f + 0.01f * i, 1 - MyFunctions.lineIntersectionDistance(ray, object)}, new float[]{0.0f + 0.01f * i, - (1 - MyFunctions.lineIntersectionDistance(ray, object))});

//                for(Line line : object.lines){
//                    MyFunctions.drawPoint(MyFunctions.lineIntersection(ray, line));
//                }

            }

            //MyFunctions.drawLine(new float[]{x, y}, new float[]{MyFunctions.rotateVector(direction, 10 * i)[0] + x, MyFunctions.rotateVector(direction, 10 * i)[1] + y});
        }
    }

    float[] getDirection(){
        return direction;
    }

    void rotate(float degrees){
        direction = MyFunctions.rotateVector(direction, degrees);
    }

    void move(float r){
        float c = (float) (r / Math.sqrt(direction[0] * direction[0] + direction[1] * direction[1]));
        this.x += c * direction[0];
        this.y += c * direction[1];
    }

}
