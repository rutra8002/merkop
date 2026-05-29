package com.rutradev.merkop.camera;

import com.rutradev.merkop.Config;

public class Camera2D {
    private float x;
    private float y;
    private float zoom;
    private float viewportWidth;
    private float viewportHeight;
    private final float smoothFactor;

    public Camera2D(float width, float height, float startX, float startY, float smoothFactor) {
        this.viewportWidth = width;
        this.viewportHeight = height;
        this.x = startX;
        this.y = startY;
        this.smoothFactor = smoothFactor;
        this.zoom = calculateTargetZoom(width, height);
    }

    private float calculateTargetZoom(float currentWidth, float currentHeight) {
        float scaleX = currentWidth / Config.REFERENCE_WIDTH;
        float scaleY = currentHeight / Config.REFERENCE_HEIGHT;
        float scale = Math.min(scaleX, scaleY);
        return 1f / scale;
    }

    public void updateTarget(float targetX, float targetY, float deltaTime) {
        x += (targetX - x) * smoothFactor * deltaTime;
        y += (targetY - y) * smoothFactor * deltaTime;
    }

    public void setTargetZoom(float zoom) {
        this.zoom = zoom * calculateTargetZoom(viewportWidth, viewportHeight);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZoom() {
        return zoom;
    }

    public float getViewportWidth() {
        return viewportWidth;
    }

    public float getViewportHeight() {
        return viewportHeight;
    }

    public void onResize(int newWidth, int newHeight) {
        viewportWidth = newWidth;
        viewportHeight = newHeight;
        zoom = calculateTargetZoom(newWidth, newHeight);
    }
}

