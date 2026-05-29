package com.rutradev.merkop;

import com.badlogic.gdx.Game;
import com.rutradev.merkop.render.Renderer;
import com.rutradev.merkop.screens.GameDisplay;

public class Main extends Game {
    private Renderer renderer;

    @Override
    public void create() {
        renderer = new Renderer(Config.DEFAULT_VIEWPORT_WIDTH, Config.DEFAULT_VIEWPORT_HEIGHT);
        setScreen(new GameDisplay(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        renderer.dispose();
    }

    public Renderer getRenderer() {
        return renderer;
    }
}

