package com.syllab.games.models;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Character {

    private final ArrayList<Consumer<Character>> borderListeners = new ArrayList<>();
    private final Daedalus map;
    private int x, y;

    public Character(Daedalus map) {
        this.map = map;
        reset();
    }
    public void reset() {
        this.x = Daedalus.MAP_CENTER;
        this.y = Daedalus.MAP_CENTER;
    }
    public void addBorderListener(Consumer<Character> onBorderReached) {
        this.borderListeners.add(onBorderReached);
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public boolean canMove(int dx, int dy) {
        return !map.isWall(this.x + dx, this.y + dy);
    }
    public void move(int dx, int dy) {
        if(!canMove(dx, dy)) {
            throw new IllegalStateException("Cannot reach this cell");
        }
        if(map.isOnBorder(this.x += dx, this.y += dy)) {
            borderListeners.forEach(l -> l.accept(this));
        }
    }
}
