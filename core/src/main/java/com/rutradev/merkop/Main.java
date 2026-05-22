package com.rutradev.merkop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rutradev.merkop.screens.GameDisplay;

public class Main extends Game {
    private SpriteBatch batch;
    private OrthographicCamera uiCamera;

    @Override
    public void create() {
        batch = new SpriteBatch();
        uiCamera = new OrthographicCamera(640, 480);
        uiCamera.position.set(320, 240, 0);
        uiCamera.update();

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

    public OrthographicCamera getUICamera() {
        return uiCamera;
    }
}

