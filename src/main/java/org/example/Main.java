package org.example;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;


public class Main{
    private long window;

    public void run() throws InterruptedException {
        init();
        loop();
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    private void init() {
        // Инициализация GLFW
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Не удалось инициализировать GLFW");
        }

        // Создание окна
        window = GLFW.glfwCreateWindow(800, 800, "OpenGL в Java", 0, 0);
        if (window == 0) {
            throw new RuntimeException("Не удалось создать окно");
        }

        // Получаем видеорежим для установки окна по центру
        GLFWVidMode vidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(window, (vidMode.width() - 800) / 2, (vidMode.height() - 800) / 2);

        // Устанавливаем контекст OpenGL
        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwShowWindow(window);
        GL.createCapabilities(); // Инициализируем OpenGL


    }


    private void loop() throws InterruptedException {

        ArrayList<SolidObject> objects = new ArrayList<>();

        Player player = new Player(0.0f, 0.0f);

        float[] dir = {1.0f, 1.0f};

        float[] line1p1 = {-1.0f, 1.0f};
        float[] line1p2 = {1.0f, 1.0f};

        float[] line2p1 = {-0.5f, 0.0f};
        float[] line2p2 = {0.0f, 0.1f};

        Rectangle rect = new Rectangle(-1.0f, -1.0f, -0.5f, -0.5f);
        objects.add(rect);

        Rectangle rect1 = new Rectangle(0.5f, -0.5f, 1.0f, -1.0f);
        objects.add(rect1);

        SolidLine line = new SolidLine(-1.0f, 1.0f, 1.0f, 1.0f);
        objects.add(line);

        SolidLine line1 = new SolidLine(-1.0f, 1.0f, -1.0f, -1.0f);
        objects.add(line1);


        while (!GLFW.glfwWindowShouldClose(window)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); // Очистка экрана


            if (glfwGetKey(window, GLFW_KEY_A) == GLFW_PRESS) {
                player.rotate(1);
            } else if (glfwGetKey(window, GLFW_KEY_D) == GLFW_PRESS) {
                player.rotate(-1);
            }
            if (glfwGetKey(window, GLFW_KEY_W) == GLFW_PRESS) {
                player.move(0.01f);
            } else if (glfwGetKey(window, GLFW_KEY_S) == GLFW_PRESS) {
                player.move(-0.01f);
            }


            player.draw(objects);


            for (SolidObject object : objects){
                object.draw();
            }


            Thread.sleep(5);

            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Main().run();
    }
}
