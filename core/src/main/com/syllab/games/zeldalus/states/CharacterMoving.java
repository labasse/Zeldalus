package com.syllab.games.zeldalus.states;

import com.syllab.games.zeldalus.Character;

public class CharacterMoving implements CharacterState {
    private final float speed;
    private float dt;

    public CharacterMoving(float speed) {
        this.speed = speed;
    }
    @Override
    public void update(Character c, float dt) {
        this.dt = dt;
        if(c.isDestinationReached()) {
            c.changeState(Character.STATE_IDLE);
        }
    }
    @Override
    public void processInputs(Character c) {
        // Nothing to do
    }
    @Override
    public Object getTexture(Character c, CharacterTextureMap ctm) {
        int dir = c.walkToDestination(this.speed*this.dt);

        c.setIdleDirection(dir);
        return ctm.getWalkTexture(dir);
    }
}
