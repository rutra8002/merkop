package com.rutradev.merkop.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.rutradev.merkop.Config;

public class Camera2D {
    private final OrthographicCamera camera;
    private final float smoothFactor;

    public Camera2D(float width, float height, float startX, float startY, float smoothFactor) {
        this.camera = new OrthographicCamera(width, height);
        this.camera.position.set(startX, startY, 0);
        this.smoothFactor = smoothFactor;

        this.camera.zoom = calculateTargetZoom(width, height);
        this.camera.update();
    }

    private float calculateTargetZoom(float currentWidth, float currentHeight) {
        float scaleX = currentWidth / Config.REFERENCE_WIDTH;
        float scaleY = currentHeight / Config.REFERENCE_HEIGHT;
        float scale = Math.min(scaleX, scaleY);
        return 1f / scale;
    }

    public void updateTarget(float targetX, float targetY, float deltaTime) {
        camera.position.x += (targetX - camera.position.x) * smoothFactor * deltaTime;
        camera.position.y += (targetY - camera.position.y) * smoothFactor * deltaTime;
        camera.update();
    }

    public void setTargetZoom(float zoom) {
        this.camera.zoom = zoom*calculateTargetZoom(camera.viewportWidth, camera.viewportHeight);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Vector2 getPosition() {
        return new Vector2(camera.position.x, camera.position.y);
    }

    public void onResize(int newWidth, int newHeight) {
        camera.viewportWidth = newWidth;
        camera.viewportHeight = newHeight;
        camera.zoom = calculateTargetZoom(newWidth, newHeight);
        camera.update();
    }
}

