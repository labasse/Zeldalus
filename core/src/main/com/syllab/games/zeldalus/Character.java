package com.syllab.games.zeldalus;

import com.badlogic.gdx.math.Vector2;
import com.syllab.games.utils.Direction;
import com.syllab.games.zeldalus.states.CharacterIdle;
import com.syllab.games.zeldalus.states.CharacterMoving;
import com.syllab.games.zeldalus.states.CharacterTextureMap;
import com.syllab.games.zeldalus.states.CharacterState;

public class Character {
    public final static int STATE_IDLE   = 0;
    public final static int STATE_MOVING = 1;

    private final static float SPEED = 4f;

    private Vector2 actual, destination;
    private int idleDirection;
    private final Daedalus map;

    private final CharacterState[] states;
    private CharacterState currentState;

    public Character(Daedalus map) {
        this.map = map;
        this.states = new CharacterState[]{
            new CharacterIdle(),
            new CharacterMoving(SPEED)
        };
        reset();
    }

    public Vector2 getActualPos() {
        return actual;
    }

    public boolean isOnBorder() {
        return map.isOnBorder((int)actual.x, (int)actual.y);
    }

    public void changeState(int newStateId) {
        this.currentState = this.states[newStateId];
    }

    public void reset() {
        this.idleDirection = Direction.DOWN;
        this.actual      = new Vector2(Daedalus.MAP_CENTER, Daedalus.MAP_CENTER);
        this.destination = this.actual.cpy();
        changeState(STATE_IDLE);
    }

    public void updateAndProcessInputs(float dt) {
        currentState.update       (this, dt);
        currentState.processInputs(this);
    }

    public void setIdleDirection(int dir) {
        if(dir < 0 || Direction.DIRECTIONS <= dir ) {
            throw new IllegalArgumentException("Valid directions are 0, 1, 2, 3");
        }
        this.idleDirection = dir;
    }

    public int getIdleDirection() {
        return this.idleDirection;
    }

    public Object getTexture(CharacterTextureMap ctm) {
        return currentState.getTexture(this, ctm);
    }

    public boolean isDestinationReached() {
        return this.actual.equals(this.destination);
    }

    public int walkToDestination(float step) {
        return Direction.move(this.actual, this.destination, new Vector2(step, step));
    }

    public void tryToMoveToDestination(int dx, int dy) {
        assert(((dx==0)||(dy==0))&&(dx>=-1)&&(dx<=1)&&(dy>=-1)&&(dy<=1));
        if(dx!=0 || dy!=0) {
            int destX = (int)actual.x + dx;
            int destY = (int)actual.y + dy;

            if(!map.isWall(destX, destY)) {
                this.destination.set(destX, destY);
                changeState(Character.STATE_MOVING);
            }
        }
    }
}
