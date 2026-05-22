package com.rutradev.merkop.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.rutradev.merkop.Main;
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
        this.camera = new Camera2D(640, 480, 320, 240, 3f);
        this.shapeRenderer = new ShapeRenderer();
        this.font = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        handleInput();
        player.update(delta);
        camera.updateTarget(player.getPosition().x + player.getWidth() / 2,
                           player.getPosition().y + player.getHeight() / 2, delta);
        camera.zoomIntro(delta);

        shapeRenderer.setProjectionMatrix(camera.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        player.render(shapeRenderer);

        shapeRenderer.end();

        game.getBatch().setProjectionMatrix(game.getUICamera().combined);
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

