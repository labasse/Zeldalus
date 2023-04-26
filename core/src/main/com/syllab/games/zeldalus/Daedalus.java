package com.syllab.games.zeldalus;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.syllab.games.utils.Direction;
import com.syllab.games.utils.IntNumber;

import java.util.Random;

public class Daedalus {
    public final static int MAP_SIDE = 19;
    private final static int MAP_CENTER = MAP_SIDE/2;

    public final static int BACKGROUND_TILE_WIDTH  = 16;
    public final static int BACKGROUND_TILE_HEIGHT = 16;

    private final static int MAP_CELL_TILE_COUNT_X = 4;
    private final static int MAP_CELL_TILE_COUNT_Y = 3;

    public final static int MAP_CELL_WIDTH  = MAP_CELL_TILE_COUNT_X * BACKGROUND_TILE_WIDTH;
    public final static int MAP_CELL_HEIGHT = MAP_CELL_TILE_COUNT_Y * BACKGROUND_TILE_HEIGHT;

    public final static int SCREEN_WIDTH  = MAP_CENTER * MAP_CELL_WIDTH + BACKGROUND_TILE_WIDTH;
    public final static int SCREEN_HEIGHT = MAP_CENTER * MAP_CELL_HEIGHT + BACKGROUND_TILE_HEIGHT;

    private static int toMapX(float x) { return (int)x/(MAP_CELL_WIDTH /2); }
    private static int toMapY(float y) { return (int)y/(MAP_CELL_HEIGHT/2); }

    private static float fromMapX(int x) { return (float)x*(float)(MAP_CELL_WIDTH /2); }
    private static float fromMapY(int y) { return (float)y*(float)(MAP_CELL_HEIGHT /2); }

    private final Random rand = new Random();
    private final boolean [][] map = new boolean[1+MAP_SIDE+1][1+MAP_SIDE+1];

    private Texture txBackground;
    private TextureRegion[][] txrBgTiles;
    private TextureRegion txrBgHole, txrBgWall;

    public Daedalus() {
        reset();
    }

    public void create() {
        txBackground = new Texture("background.png");
        txrBgTiles = TextureRegion.split(txBackground, BACKGROUND_TILE_WIDTH, BACKGROUND_TILE_HEIGHT);
        txrBgHole = txrBgTiles[0][1];
        txrBgWall = txrBgTiles[0][3];
    }

    public void reset() {
        for(int y=0; y<=MAP_CENTER; y++)
            for(int x=0; x<=MAP_CENTER; x++) {
                int rx = MAP_SIDE-1-x;
                int ry = MAP_SIDE-1-y;

                map[x][y] = map[rx][y] = map[x][ry] = map[rx][ry] = IntNumber.isEven(Math.min(x,y));
            }
        for(int i=1; i<=MAP_CENTER; i++)
            if(IntNumber.isOdd(i)) {
                addRandomHole(i);
                addRandomHole(i);
            }
            else
                addRandomWall(i);
    }

    public Vector2 newCenter() {
        return new Vector2(
            fromMapX(MAP_CENTER),
            fromMapY(MAP_CENTER)
        );
    }

    public void render(SpriteBatch batch) {
        for(int y=0; y<MAP_SIDE; y+=2)
        {
            for(int x=0; x<MAP_SIDE; x+=2)
            {
                float offsetX = fromMapX(x);
                float offsetY = fromMapY(y);

                assert(map[x][y]&&!map[x+1][y+1]);
                batch.draw(txrBgWall, offsetX, offsetY);
                for(int i = 1; i< MAP_CELL_TILE_COUNT_X; i++)
                    batch.draw(map[x+1][y] ? txrBgWall : txrBgTiles[0][i-1], offsetX+BACKGROUND_TILE_WIDTH*i, offsetY);
                for(int i = 1; i< MAP_CELL_TILE_COUNT_Y; i++) {
                    batch.draw(map[x  ][y+1] ? txrBgWall : txrBgTiles[4 - i][1], offsetX, offsetY + BACKGROUND_TILE_HEIGHT * i);
                    batch.draw(map[x+1][y+(i-1)*2] ? txrBgTiles[4 - i][1] : txrBgHole, offsetX+2*BACKGROUND_TILE_WIDTH, offsetY + BACKGROUND_TILE_HEIGHT * i);
                }
                for(int i = 0; i<Direction.DIRECTIONS; i++) {
                    int index = (map[x+1][y+2-(i&2)] ? 1:0)
                            |(map[x+(i&1)*2][y+1] ? 2:0);

                    batch.draw(
                            txrBgTiles[i+1][index],
                            offsetX+(1+(i&1)*2)*BACKGROUND_TILE_WIDTH,
                            offsetY+(2-(i&2)/2)*BACKGROUND_TILE_HEIGHT
                    );
                }
            }
        }
    }

    public boolean tryMove(Vector2 pos, int dx, int dy, Vector2 destOut) {
        int mapX = toMapX(pos.x);
        int mapY = toMapY(pos.y);

        if(map[mapX+dx][mapY+dy]) {
            return false;
        }
        destOut.set(fromMapX(mapX + dx), fromMapY(mapY + dy));
        return true;
    }

    public boolean isOnBorder(Vector2 pos) {
        int mapX = toMapX(pos.x);
        int mapY = toMapY(pos.y);
        return (mapX==0)||(mapY==0)||(mapX==MAP_SIDE-1)||(mapY==MAP_SIDE-1);
    }

    private void addRandomHole(int radius) { addRandom(radius, false); }
    private void addRandomWall(int radius) { addRandom(radius, true); }
    private void addRandom(int radius, boolean wall) {
        int direction=rand.nextInt(8);
        int pos=(radius+1)%2+2*(rand.nextInt(radius+1)/2);
        int [] offset = IntNumber.isOdd(direction)
                ? new int[]{ pos, radius }
                : new int[]{ radius, pos };
        int x = MAP_CENTER + IntNumber.opposite(offset[0], direction&2);
        int y = MAP_CENTER + IntNumber.opposite(offset[1], direction&4);

        map[x][y] = wall;
    }

    public void dispose() {
        txBackground.dispose();
    }
}
