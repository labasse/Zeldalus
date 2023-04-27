package com.syllab.games.services;

import com.syllab.games.models.Character;
import com.syllab.games.models.Daedalus;

public class GameService {
    private final Daedalus map;
    private final Character character;

    public GameService() {
        this.map = new Daedalus();
        this.character = new Character(map);
        this.character.addBorderListener(c -> reset());
    }
    public Daedalus getMap() {
        return this.map;
    }
    public Character getCharacter() {
        return this.character;
    }
    public void reset() {
        this.character.reset();
        this.map.reset();
    }
}
