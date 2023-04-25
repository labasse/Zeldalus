package com.syllab.games.zeldalus.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.syllab.games.utils.Direction;
import com.syllab.games.zeldalus.Character;

public class CharacterIdle implements CharacterState {
    private final TextureRegion[] txrIdle = new TextureRegion[Direction.DIRECTIONS];

    public CharacterIdle() {  }

    @Override
    public void create(TextureRegion[][] txrTiles) {
        for(int i=0; i<txrIdle.length; i++) {
            txrIdle[i] = txrTiles[i][0];
        }
    }

    @Override
    public void update(Character c, float dt) {
        // Nothing to do
    }

    @Override
    public void processInputs(Character c) {
        int dx=0, dy=0;

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)||Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            dx=Gdx.input.isKeyPressed(Input.Keys.LEFT) ? -1:1;
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)||Gdx.input.isKeyPressed(Input.Keys.UP))
            dy=Gdx.input.isKeyPressed(Input.Keys.DOWN) ? -1:1;
        c.tryMoveToDestination(dx, dy);
    }

    @Override
    public void rendrer(Character c, SpriteBatch batch) {
        c.draw(batch, txrIdle[c.getIdleDirection()]);
    }
}
