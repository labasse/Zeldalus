package com.syllab.games.views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.syllab.games.services.DaedalusAssetsService;
import com.syllab.games.services.GameService;
import com.syllab.games.services.base.ServiceHost;
import com.syllab.games.utils.Direction;
import com.syllab.games.models.Daedalus;

public class DaedalusView extends View {

    private final static int MAP_CELL_TILE_COUNT_X = 4;
    private final static int MAP_CELL_TILE_COUNT_Y = 3;

    public final static int MAP_CELL_WIDTH  = MAP_CELL_TILE_COUNT_X * DaedalusAssetsService.BACKGROUND_TILE_WIDTH;
    public final static int MAP_CELL_HEIGHT = MAP_CELL_TILE_COUNT_Y * DaedalusAssetsService.BACKGROUND_TILE_HEIGHT;

    public final static int SCREEN_WIDTH  = Daedalus.MAP_CENTER * MAP_CELL_WIDTH  + DaedalusAssetsService.BACKGROUND_TILE_WIDTH;
    public final static int SCREEN_HEIGHT = Daedalus.MAP_CENTER * MAP_CELL_HEIGHT + DaedalusAssetsService.BACKGROUND_TILE_HEIGHT;

    private static float fromMapX(int x) { return (float)x*(float)(MAP_CELL_WIDTH /2); }
    private static float fromMapY(int y) { return (float)y*(float)(MAP_CELL_HEIGHT /2); }

    private final static DaedalusView theInstance = new DaedalusView();

    private DaedalusView() {

    }
    public static DaedalusView getInstance() {
        return theInstance;
    }
    @Override
    public void render(ServiceHost services, SpriteBatch batch) {
        Daedalus map = services.getInstance(GameService.class).getMap();
        DaedalusAssetsService assets = services.getInstance(DaedalusAssetsService.class);

        for(int y=0; y<Daedalus.MAP_SIDE; y+=2)
        {
            for(int x=0; x<Daedalus.MAP_SIDE; x+=2)
            {
                float offsetX = fromMapX(x);
                float offsetY = fromMapY(y);

                assert(map.isWall(x, y)&&!map.isWall(x+1, y+1));
                batch.draw(assets.getWall(), offsetX, offsetY);
                for(int i = 1; i< MAP_CELL_TILE_COUNT_X; i++) {
                    batch.draw(
                        map.isWall(x + 1, y) ? assets.getWall() : assets.getTile(0, i - 1),
                        offsetX + DaedalusAssetsService.BACKGROUND_TILE_WIDTH * i,
                        offsetY
                    );
                }
                for(int i = 1; i< MAP_CELL_TILE_COUNT_Y; i++) {
                    batch.draw(
                        map.isWall(x  , y+1) ? assets.getWall() : assets.getTile(4 - i, 1),
                        offsetX,
                        offsetY + DaedalusAssetsService.BACKGROUND_TILE_HEIGHT * i
                    );
                    batch.draw(
                        map.isWall(x+1, y+(i-1)*2) ? assets.getTile(4 - i, 1) : assets.getHole(),
                        offsetX+2*DaedalusAssetsService.BACKGROUND_TILE_WIDTH,
                        offsetY + DaedalusAssetsService.BACKGROUND_TILE_HEIGHT * i
                    );
                }
                for(int i = 0; i< Direction.DIRECTIONS; i++) {
                    int index = (map.isWall(x+1, y+2-(i&2)) ? 1:0)
                            |(map.isWall(x+(i&1)*2, y+1) ? 2:0);

                    batch.draw(
                            assets.getTile(i+1, index),
                            offsetX+(1+(i&1)*2)*DaedalusAssetsService.BACKGROUND_TILE_WIDTH,
                            offsetY+(2-(i&2)/2)*DaedalusAssetsService.BACKGROUND_TILE_HEIGHT
                    );
                }
            }
        }
    }
}
