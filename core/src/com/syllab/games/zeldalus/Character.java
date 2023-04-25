package com.syllab.games.zeldalus;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.syllab.games.utils.Direction;
import com.syllab.games.zeldalus.states.CharacterIdle;
import com.syllab.games.zeldalus.states.CharacterMoving;
import com.syllab.games.zeldalus.states.CharacterState;

public class Character {
    public final static int STATE_IDLE = 0;
    public final static int STATE_MOVING = 1;

    public final static int WIDTH =48;
    public final static int HEIGHT =64;

    private final static int ANIM_LEFT  =3;
    private final static int ANIM_RIGHT =1;
    private final static int ANIM_UP    =0;
    private final static int ANIM_DOWN  =2;

    private final static float ANIM_SPEED = .1f;
    private final static float SPEED = 150f;

    private final float time = .0f;

    private Texture tx;
    private Vector2 actual, destination;
    private int idleDirection;
    private final Daedalus map;

    private final CharacterState[] states;
    private CharacterState currentState;

    public Character(Daedalus map) {
        this.map = map;
        this.states = new CharacterState[]{
            new CharacterIdle(),
            new CharacterMoving(
                new int[]{ ANIM_LEFT, ANIM_RIGHT, ANIM_DOWN, ANIM_UP },
                SPEED, ANIM_SPEED
            )
        };
        reset();
    }

    public Vector2 getActualPos() {
        return actual;
    }

    public void changeState(int newStateId) {
        this.currentState = this.states[newStateId];
    }

    public void reset() {
        this.idleDirection = ANIM_DOWN;
        this.actual      = map.newCenter();
        this.destination = map.newCenter();
        changeState(STATE_IDLE);
    }

    public void create() {
        this.tx = new Texture("character.png");
        TextureRegion[][] txrTiles = TextureRegion.split(tx, WIDTH, HEIGHT);
        for(CharacterState state : states) {
            state.create(txrTiles);
        }
    }

    public void render(SpriteBatch batch, float dt) {
        currentState.update       (this, dt);
        currentState.processInputs(this);
        currentState.rendrer      (this, batch);
    }

    public void dispose() {
        tx.dispose();
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

    public void draw(SpriteBatch batch, TextureRegion txr) {
        batch.draw(txr,actual.x+(float)((Daedalus.BACKGROUND_TILE_WIDTH - Character.WIDTH)/2),  actual.y);
    }

    public boolean isDestinationReached() {
        return this.actual.equals(this.destination);
    }

    public int walkToDestination(float step) {
        return Direction.move(this.actual, this.destination, new Vector2(step, step));
    }

    public void tryMoveToDestination(int dx, int dy) {
        assert(((dx==0)||(dy==0))&&(dx>=-1)&&(dx<=1)&&(dy>=-1)&&(dy<=1));
        if((dx!=0 || dy!=0) && map.tryMove(actual, dx, dy, this.destination)) {
            changeState(Character.STATE_MOVING);
        }
    }
}
