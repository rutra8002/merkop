package com.rutradev.merkop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rutradev.merkop.screens.GameDisplay;

public class Main extends Game {
    private SpriteBatch batch;
    private OrthographicCamera camera;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Config.DEFAULT_VIEWPORT_WIDTH, Config.DEFAULT_VIEWPORT_HEIGHT);
        camera.position.set(Config.DEFAULT_VIEWPORT_WIDTH * 0.5f, Config.DEFAULT_VIEWPORT_HEIGHT * 0.5f, 0);
        camera.update();

        setScreen(new GameDisplay(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}

