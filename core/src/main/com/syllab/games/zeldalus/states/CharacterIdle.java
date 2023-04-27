package com.syllab.games.zeldalus.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.syllab.games.zeldalus.Character;

public class CharacterIdle implements CharacterState {

    public CharacterIdle() {  }

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
        c.tryToMoveToDestination(dx, dy);
    }
    @Override
    public Object getTexture(Character c, CharacterTextureMap ctm) {
        return ctm.getIdleTexture(c.getIdleDirection());
    }
}
