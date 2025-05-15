package org.example;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import java.util.Objects;

public class MyFunctions {

    public static void drawTriangle(float x1, float y1, float x2, float y2, float x3, float y3){
        GL11.glBegin(GL11.GL_TRIANGLES);
        GL11.glVertex2f(x1, y1);
        GL11.glVertex2f(x2, y2);
        GL11.glVertex2f(x3, y3);
        GL11.glEnd();
    }

    public static void drawPoint(Float[] coordinates){
        if (coordinates != null){
            GL11.glPointSize(20.0f);
            GL11.glBegin(GL11.GL_POINTS);
            GL11.glColor3f(1.0f, 0.0f, 0.0f);
            GL11.glVertex2f(coordinates[0], coordinates[1]);
            GL11.glEnd();
        }
    }

    public static void drawLine(float[] firstPoint, float[] secondPoint){
        GL11.glLineWidth(20.0f); // Устанавливаем толщину линии (5 пикселей)
        GL11.glBegin(GL11.GL_LINES); // Начинаем отрисовку линий

        GL11.glColor3f(1.0f, 0.0f, 0.0f); // Красный цвет
        GL11.glVertex2f(firstPoint[0], firstPoint[1]); // Первая вершина линии

        GL11.glColor3f(0.0f, 0.0f, 1.0f); // Синий цвет
        GL11.glVertex2f(secondPoint[0], secondPoint[1]); // Вторая вершина линии

        GL11.glEnd(); // Завершаем отрисовку

    }

    public static void drawCircle(float x, float y, float radius) {
        GL30.glBegin(GL30.GL_TRIANGLE_FAN);
        GL30.glVertex2f(x, y); // Центр круга
        for (int i = 0; i <= 360; i += 10) {
            float angle = (float) Math.toRadians(i);
            float z = (float) Math.cos(angle) * radius + x;
            float c = (float) Math.sin(angle) * radius + y;
            GL30.glVertex2f(z, c);
        }
        GL30.glEnd();

    }


    public static float[] rotateVector(float[] direction, float degrees){
        float[] newDir = {0, 0};
        newDir[0] = (float) (Math.cos(Math.toRadians(degrees)) * direction[0] - Math.sin(Math.toRadians(degrees)) * direction[1]);
        newDir[1] = (float) (Math.sin(Math.toRadians(degrees)) * direction[0] + Math.cos(Math.toRadians(degrees)) * direction[1]);
        return newDir;
    }

    public static Float[] lineIntersection(Line line1, Line line2){
        float x1 = line1.x1;
        float y1 = line1.y1;
        float x2 = line1.x2;
        float y2 = line1.y2;
        float x3 = line2.x1;
        float y3 = line2.y1;
        float x4 = line2.x2;
        float y4 = line2.y2;

        float determinant =  (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        if (determinant == 0){
            return null;
        }

        float t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / determinant;
        float u = ((x1 - x3) * (y1 - y2) - (y1 - y3) * (x1 - x2)) / determinant;

        if (t >= 0 && t <= 1 && u >= 0 && u <= 1) {
            float ix = x1 + t * (x2 - x1);
            float iy = y1 + t * (y2 - y1);

            //return (float) Math.sqrt((ix - x1) * (ix - x1) + (iy - y1) * (iy - y1));

            return new Float[]{ix, iy};
        }

        return null;

    }


    public static Float[] lineIntersectionDist(Line ray, SolidObject object){
        float x1 = ray.x1;
        float y1 = ray.y1;
        float x2 = ray.x2;
        float y2 = ray.y2;

        float distance = Float.POSITIVE_INFINITY;

        Float[] retCoordinates = null;

        for (Line line : object.lines){

            float x3 = line.x1;
            float y3 = line.y1;
            float x4 = line.x2;
            float y4 = line.y2;

            float determinant =  (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

            if (determinant == 0){
                continue;
            }

            float t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / determinant;
            float u = ((x1 - x3) * (y1 - y2) - (y1 - y3) * (x1 - x2)) / determinant;

            if (t >= 0 && t <= 1 && u >= 0 && u <= 1) {
                float ix = x1 + t * (x2 - x1);
                float iy = y1 + t * (y2 - y1);

                float currentDistance = (float) Math.sqrt((ix - x1) * (ix - x1) + (iy - y1) * (iy - y1));

                if(currentDistance < distance){
                    distance = currentDistance;
                    retCoordinates = new Float[]{ix, iy};
                }

            }



        }

        return retCoordinates;

    }

    public static Float lineIntersectionDistance(Line ray, SolidObject object){
        float x1 = ray.x1;
        float y1 = ray.y1;
        float x2 = ray.x2;
        float y2 = ray.y2;

        float distance = Float.POSITIVE_INFINITY;

        Float[] retCoordinates = null;

        for (Line line : object.lines){

            float x3 = line.x1;
            float y3 = line.y1;
            float x4 = line.x2;
            float y4 = line.y2;

            float determinant =  (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

            if (determinant == 0){
                continue;
            }

            float t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / determinant;
            float u = ((x1 - x3) * (y1 - y2) - (y1 - y3) * (x1 - x2)) / determinant;

            if (t >= 0 && t <= 1 && u >= 0 && u <= 1) {
                float ix = x1 + t * (x2 - x1);
                float iy = y1 + t * (y2 - y1);

                float currentDistance = (float) Math.sqrt((ix - x1) * (ix - x1) + (iy - y1) * (iy - y1));

                if(currentDistance < distance){
                    distance = currentDistance;
                    retCoordinates = new Float[]{ix, iy};
                }

            }



        }

        return distance;

    }

}
