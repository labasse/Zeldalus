package com.syllab.games.services;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.syllab.games.services.base.DisposableService;

public class DaedalusAssetsService implements DisposableService {
    public final static int BACKGROUND_TILE_WIDTH  = 16;
    public final static int BACKGROUND_TILE_HEIGHT = 16;

    private final Texture txBackground;
    private final TextureRegion[][] txrBgTiles;
    private final TextureRegion txrBgHole, txrBgWall;

    public DaedalusAssetsService() {
        txBackground = new Texture("background.png");
        txrBgTiles = TextureRegion.split(txBackground, BACKGROUND_TILE_WIDTH, BACKGROUND_TILE_HEIGHT);
        txrBgHole = txrBgTiles[0][1];
        txrBgWall = txrBgTiles[0][3];
    }
    public TextureRegion getHole() {
        return txrBgHole;
    }
    public TextureRegion getWall() {
        return txrBgWall;
    }
    public TextureRegion getTile(int x, int y) {
        return txrBgTiles[x][y];
    }
    @Override
    public void dispose() {
        txBackground.dispose();
    }
}
