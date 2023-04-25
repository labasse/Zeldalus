package com.syllab.games.zeldalus.states;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.syllab.games.utils.Direction;
import com.syllab.games.zeldalus.Character;

public class CharacterMoving implements CharacterState {

    private final int[] animationIds;
    private final float speed;
    private final float animSpeed;
    private float dt, time = 0f;

    private Animation<TextureRegion>[] animations;

    public CharacterMoving(int[] animationIds, float speed, float animSpeed) {
        this.animationIds = animationIds;
        this.speed = speed;
        this.animSpeed = animSpeed;
    }

    @Override
    public void create(TextureRegion[][] txrTiles) {
        this.animations = new Animation[Direction.DIRECTIONS];
        for(int i = 0; i< this.animations.length; i++) {
            this.animations[i] = new Animation<>(
                this.animSpeed,
                txrTiles[i]
            );
        }
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
    public void rendrer(Character c, SpriteBatch batch) {
        int anim = this.animationIds[c.walkToDestination(this.speed*this.dt)];

        this.time += this.dt;
        c.draw(batch, this.animations[anim].getKeyFrame(this.time, true));
        c.setIdleDirection(anim);
    }
}
