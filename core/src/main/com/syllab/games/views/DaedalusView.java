package com.syllab.games.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.syllab.games.utils.Direction;
import com.syllab.games.zeldalus.Daedalus;

public class DaedalusView implements Drawable {

    public final static int BACKGROUND_TILE_WIDTH  = 16;
    public final static int BACKGROUND_TILE_HEIGHT = 16;

    private final static int MAP_CELL_TILE_COUNT_X = 4;
    private final static int MAP_CELL_TILE_COUNT_Y = 3;

    public final static int MAP_CELL_WIDTH  = MAP_CELL_TILE_COUNT_X * BACKGROUND_TILE_WIDTH;
    public final static int MAP_CELL_HEIGHT = MAP_CELL_TILE_COUNT_Y * BACKGROUND_TILE_HEIGHT;

    public final static int SCREEN_WIDTH  = Daedalus.MAP_CENTER * MAP_CELL_WIDTH + BACKGROUND_TILE_WIDTH;
    public final static int SCREEN_HEIGHT = Daedalus.MAP_CENTER * MAP_CELL_HEIGHT + BACKGROUND_TILE_HEIGHT;

    private static float fromMapX(int x) { return (float)x*(float)(MAP_CELL_WIDTH /2); }
    private static float fromMapY(int y) { return (float)y*(float)(MAP_CELL_HEIGHT /2); }

    private Texture txBackground;
    private TextureRegion[][] txrBgTiles;
    private TextureRegion txrBgHole, txrBgWall;

    private final Daedalus map;

    public DaedalusView(Daedalus daedalus) {
        this.map = daedalus;
    }
    @Override
    public void create() {
        txBackground = new Texture("background.png");
        txrBgTiles = TextureRegion.split(txBackground, BACKGROUND_TILE_WIDTH, BACKGROUND_TILE_HEIGHT);
        txrBgHole = txrBgTiles[0][1];
        txrBgWall = txrBgTiles[0][3];
    }
    @Override
    public void render(SpriteBatch batch, float dt) {
        for(int y=0; y<Daedalus.MAP_SIDE; y+=2)
        {
            for(int x=0; x<Daedalus.MAP_SIDE; x+=2)
            {
                float offsetX = fromMapX(x);
                float offsetY = fromMapY(y);

                assert(map.isWall(x, y)&&!map.isWall(x+1, y+1));
                batch.draw(txrBgWall, offsetX, offsetY);
                for(int i = 1; i< MAP_CELL_TILE_COUNT_X; i++)
                    batch.draw(map.isWall(x+1, y) ? txrBgWall : txrBgTiles[0][i-1], offsetX+BACKGROUND_TILE_WIDTH*i, offsetY);
                for(int i = 1; i< MAP_CELL_TILE_COUNT_Y; i++) {
                    batch.draw(map.isWall(x  , y+1) ? txrBgWall : txrBgTiles[4 - i][1], offsetX, offsetY + BACKGROUND_TILE_HEIGHT * i);
                    batch.draw(map.isWall(x+1, y+(i-1)*2) ? txrBgTiles[4 - i][1] : txrBgHole, offsetX+2*BACKGROUND_TILE_WIDTH, offsetY + BACKGROUND_TILE_HEIGHT * i);
                }
                for(int i = 0; i< Direction.DIRECTIONS; i++) {
                    int index = (map.isWall(x+1, y+2-(i&2)) ? 1:0)
                               |(map.isWall(x+(i&1)*2, y+1) ? 2:0);

                    batch.draw(
                            txrBgTiles[i+1][index],
                            offsetX+(1+(i&1)*2)*BACKGROUND_TILE_WIDTH,
                            offsetY+(2-(i&2)/2)*BACKGROUND_TILE_HEIGHT
                    );
                }
            }
        }
    }
    @Override
    public void dispose() {
        txBackground.dispose();
    }
}
