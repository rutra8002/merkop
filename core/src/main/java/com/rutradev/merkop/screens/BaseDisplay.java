package com.rutradev.merkop.screens;

import com.badlogic.gdx.Screen;
import com.rutradev.merkop.Main;
import com.rutradev.merkop.render.Renderer;

public abstract class BaseDisplay implements Screen {
    protected Main game;
    protected Renderer renderer;

    public BaseDisplay(Main game) {
        this.game = game;
        this.renderer = game.getRenderer();
    }

    @Override
    public void render(float delta) {
        renderer.clearScreen();
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {}
}

