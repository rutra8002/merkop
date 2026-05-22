package com.rutradev.merkop.teavm;

import com.github.xpenatan.gdx.backends.teavm.TeaApplicationConfiguration;
import com.github.xpenatan.gdx.backends.teavm.TeaApplication;
import com.rutradev.merkop.Config;
import com.rutradev.merkop.Main;

/**
 * Launches the TeaVM/HTML application.
 */
public class TeaVMLauncher {
    public static void main(String[] args) {
        TeaApplicationConfiguration config = new TeaApplicationConfiguration("canvas");
        config.width = Config.DEFAULT_VIEWPORT_WIDTH;
        config.height = Config.DEFAULT_VIEWPORT_HEIGHT;
        new TeaApplication(new Main(), config);
    }
}
