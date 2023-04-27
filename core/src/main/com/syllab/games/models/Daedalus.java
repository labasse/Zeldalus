package com.syllab.games.models;

import com.syllab.games.utils.IntNumber;

import java.util.Random;

public class Daedalus {
    public final static int MAP_SIDE = 19;
    public final static int MAP_CENTER = MAP_SIDE/2;

    private final Random rand = new Random();
    private final boolean [][] map = new boolean[1+MAP_SIDE+1][1+MAP_SIDE+1];

    public Daedalus() {
        reset();
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

    public boolean isWall(int x, int y) {
        return this.map[x][y];
    }

    public boolean isOnBorder(int x, int y) {
        return (x==0)||(y==0)||(x==MAP_SIDE-1)||(y==MAP_SIDE-1);
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
}
