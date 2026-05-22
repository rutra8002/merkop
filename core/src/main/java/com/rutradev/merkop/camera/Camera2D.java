package com.rutradev.merkop.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class Camera2D {
    private final OrthographicCamera camera;
    private final Vector2 targetPosition;
    private final float smoothFactor;
    private float targetZoom;

    public Camera2D(float width, float height, float startX, float startY, float smoothFactor) {
        this(width, height, startX, startY, smoothFactor, 1f, 0.33f);
    }

    public Camera2D(float width, float height, float startX, float startY, float smoothFactor, float initialZoom, float targetZoom) {
        this.camera = new OrthographicCamera(width, height);
        this.camera.position.set(startX, startY, 0);
        this.targetPosition = new Vector2(startX, startY);
        this.smoothFactor = smoothFactor;
        this.targetZoom = targetZoom;
        this.camera.zoom = initialZoom;
        this.camera.update();
    }

    public void updateTarget(float targetX, float targetY, float deltaTime) {
        this.targetPosition.x = targetX;
        this.targetPosition.y = targetY;

        camera.position.x += (targetX - camera.position.x) * smoothFactor * deltaTime;
        camera.position.y += (targetY - camera.position.y) * smoothFactor * deltaTime;
        camera.update();
    }

    public void adjustZoom(float deltaTime) {
        camera.zoom += (targetZoom - camera.zoom) * smoothFactor * deltaTime;
        camera.update();
    }

    public void zoomIntro(float deltaTime) {
        adjustZoom(deltaTime);
    }

    public void setTargetZoom(float zoom) {
        this.targetZoom = zoom;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Vector2 getPosition() {
        return new Vector2(camera.position.x, camera.position.y);
    }
}

