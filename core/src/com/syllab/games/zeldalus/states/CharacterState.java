package com.syllab.games.zeldalus.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.syllab.games.zeldalus.Character;

public interface CharacterState {
    void create(TextureRegion[][] txrTiles);
    void update(Character c, float dt);
    void processInputs(Character c);
    void rendrer(Character c, SpriteBatch batch);
}
