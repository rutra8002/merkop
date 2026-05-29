package com.rutradev.merkop.world;

import com.rutradev.merkop.render.Renderer;

public class Player {
    private float x;
    private float y;
    private float vx;
    private float vy;
    private final float speed;
    private final float width;
    private final float height;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        this.speed = 300;
        this.width = 20;
        this.height = 20;
    }

    public void update(float deltaTime) {
        x += vx * deltaTime;
        y += vy * deltaTime;
    }

    public void setInputDirection(float dirX, float dirY) {
        vx = dirX * speed;
        vy = dirY * speed;
    }

    public void render(Renderer renderer) {
        renderer.drawPlayer(x, y, width, height);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}

