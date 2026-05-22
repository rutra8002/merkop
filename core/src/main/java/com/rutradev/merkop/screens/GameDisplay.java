package com.rutradev.merkop.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.rutradev.merkop.Main;
import com.rutradev.merkop.Config;
import com.rutradev.merkop.camera.Camera2D;
import com.rutradev.merkop.world.Player;

public class GameDisplay extends BaseDisplay {
    private final Player player;
    private final Camera2D camera;
    private final ShapeRenderer shapeRenderer;
    private final BitmapFont font;

    public GameDisplay(Main game) {
        super(game);
        this.player = new Player(320, 240);
        this.camera = new Camera2D(Config.DEFAULT_VIEWPORT_WIDTH, Config.DEFAULT_VIEWPORT_HEIGHT,
            320, 240,
                                   Config.DEFAULT_SMOOTH_FACTOR);
        this.shapeRenderer = new ShapeRenderer();
        this.font = new BitmapFont();
    }

    @Override
    public void resize(int width, int height) {
        camera.onResize(width, height);

        game.getCamera().viewportWidth = width;
        game.getCamera().viewportHeight = height;
        game.getCamera().position.set(width * 0.5f, height * 0.5f, 0f);
        game.getCamera().update();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        handleInput();
        player.update(delta);
        camera.updateTarget(player.getPosition().x + player.getWidth() / 2,
                           player.getPosition().y + player.getHeight() / 2, delta);

        shapeRenderer.setProjectionMatrix(camera.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        player.render(shapeRenderer);

        shapeRenderer.end();

        game.getBatch().setProjectionMatrix(game.getCamera().combined);
        game.getBatch().begin();
        font.draw(game.getBatch(), "FPS: " + Gdx.graphics.getFramesPerSecond(), 10,
                 Gdx.graphics.getHeight() - 10);
        font.draw(game.getBatch(), "Position: " + (int)player.getPosition().x + ", " +
                 (int)player.getPosition().y, 10, Gdx.graphics.getHeight() - 30);
        font.draw(game.getBatch(), "Camera: " + (int)camera.getPosition().x + ", " +
                 (int)camera.getPosition().y + " Zoom: " + String.format("%.2f", camera.getCamera().zoom),
                 10, Gdx.graphics.getHeight() - 50);
        game.getBatch().end();
    }

    private void handleInput() {
        float dirX = 0;
        float dirY = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            dirY += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            dirY -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            dirX -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            dirX += 1;
        }

        player.setInputDirection(dirX, dirY);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        font.dispose();
    }
}

