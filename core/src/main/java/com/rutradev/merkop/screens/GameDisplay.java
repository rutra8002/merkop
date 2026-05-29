package com.rutradev.merkop.screens;

import com.rutradev.merkop.Config;
import com.rutradev.merkop.Main;
import com.rutradev.merkop.camera.Camera2D;
import com.rutradev.merkop.render.Renderer;
import com.rutradev.merkop.world.Player;

public class GameDisplay extends BaseDisplay {
    private final Player player;
    private final Camera2D camera;

    public GameDisplay(Main game) {
        super(game);
        this.player = new Player(320, 240);
        this.camera = new Camera2D(Config.DEFAULT_VIEWPORT_WIDTH, Config.DEFAULT_VIEWPORT_HEIGHT,
            320, 240,
                                   Config.DEFAULT_SMOOTH_FACTOR);
    }

    @Override
    public void resize(int width, int height) {
        camera.onResize(width, height);
        renderer.updateCamera(width, height);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        handleInput();
        player.update(delta);
        camera.updateTarget(player.getX() + player.getWidth() / 2,
                            player.getY() + player.getHeight() / 2, delta);

        renderer.beginWorld(camera);
        player.render(renderer);
        renderer.endWorld();

        renderer.beginHud();
        renderer.drawHudText("FPS: " + renderer.getFPS(), 10, renderer.getScreenHeight() - 10);
        renderer.drawHudText("Position: " + (int) player.getX() + ", " + (int) player.getY(),
                10, renderer.getScreenHeight() - 30);
        renderer.drawHudText("Camera: " + (int) camera.getX() + ", " + (int) camera.getY() +
                        " Zoom: " + String.format("%.2f", camera.getZoom()),
                10, renderer.getScreenHeight() - 50);
        renderer.endHud();
    }

    private void handleInput() {
        float dirX = 0;
        float dirY = 0;

        if (renderer.isKeyPressed(Renderer.KEY_W)) {
            dirY += 1;
        }
        if (renderer.isKeyPressed(Renderer.KEY_S)) {
            dirY -= 1;
        }
        if (renderer.isKeyPressed(Renderer.KEY_A)) {
            dirX -= 1;
        }
        if (renderer.isKeyPressed(Renderer.KEY_D)) {
            dirX += 1;
        }

        player.setInputDirection(dirX, dirY);

        if (renderer.isKeyJustPressed(Renderer.KEY_ESCAPE)) {
            renderer.exit();
        }
    }

}

