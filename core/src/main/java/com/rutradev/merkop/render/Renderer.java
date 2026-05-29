package com.rutradev.merkop.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.rutradev.merkop.camera.Camera2D;

public class Renderer {
    public static final int KEY_W = com.badlogic.gdx.Input.Keys.W;
    public static final int KEY_A = com.badlogic.gdx.Input.Keys.A;
    public static final int KEY_S = com.badlogic.gdx.Input.Keys.S;
    public static final int KEY_D = com.badlogic.gdx.Input.Keys.D;
    public static final int KEY_ESCAPE = com.badlogic.gdx.Input.Keys.ESCAPE;

    private final SpriteBatch batch;
    private final ShapeRenderer shapeRenderer;
    private final BitmapFont font;
    private final OrthographicCamera screenCamera;
    private final OrthographicCamera worldCamera;

    public Renderer(float viewportWidth, float viewportHeight) {
        this.batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
        this.font = new BitmapFont();
        this.screenCamera = new OrthographicCamera(viewportWidth, viewportHeight);
        this.screenCamera.position.set(viewportWidth * 0.5f, viewportHeight * 0.5f, 0);
        this.screenCamera.update();
        this.worldCamera = new OrthographicCamera(viewportWidth, viewportHeight);
        this.worldCamera.position.set(viewportWidth * 0.5f, viewportHeight * 0.5f, 0);
        this.worldCamera.update();
    }

    public void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void beginWorld(Camera2D camera) {
        syncWorldCamera(camera);
        shapeRenderer.setProjectionMatrix(worldCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    }

    public void endWorld() {
        shapeRenderer.end();
    }

    public void drawPlayer(float x, float y, float width, float height) {
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(x, y, width, height);
    }

    public void beginHud() {
        batch.setProjectionMatrix(screenCamera.combined);
        batch.begin();
    }

    public void drawHudText(String text, float x, float y) {
        font.draw(batch, text, x, y);
    }

    public void endHud() {
        batch.end();
    }

    public int getFPS() {
        return Gdx.graphics.getFramesPerSecond();
    }

    public int getScreenHeight() {
        return Gdx.graphics.getHeight();
    }

    public int getScreenWidth() {
        return Gdx.graphics.getWidth();
    }

    public boolean isKeyPressed(int key) {
        return Gdx.input.isKeyPressed(key);
    }

    public boolean isKeyJustPressed(int key) {
        return Gdx.input.isKeyJustPressed(key);
    }

    public void exit() {
        Gdx.app.exit();
    }

    public void updateCamera(int width, int height) {
        screenCamera.viewportWidth = width;
        screenCamera.viewportHeight = height;
        screenCamera.position.set(width * 0.5f, height * 0.5f, 0f);
        screenCamera.update();
    }

    private void syncWorldCamera(Camera2D camera) {
        worldCamera.viewportWidth = camera.getViewportWidth();
        worldCamera.viewportHeight = camera.getViewportHeight();
        worldCamera.position.set(camera.getX(), camera.getY(), 0f);
        worldCamera.zoom = camera.getZoom();
        worldCamera.update();
    }

    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
    }
}
