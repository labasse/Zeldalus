package com.syllab.games.zeldalus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.syllab.games.utils.Direction;

public class Character {
    private final static int WIDTH =48;
    private final static int HEIGHT =64;

    private final static int ANIM_NONE  =-1;
    private final static int ANIM_LEFT  =3;
    private final static int ANIM_RIGHT =1;
    private final static int ANIM_UP    =0;
    private final static int ANIM_DOWN  =2;

    private final static float ANIM_SPEED = .1f;
    private final static float SPEED = 150f;
    private final static Vector2 SPEED2D = new Vector2(SPEED, SPEED);

    private final static int[] ANIMS = { ANIM_NONE, ANIM_LEFT, ANIM_RIGHT, ANIM_DOWN, ANIM_UP };

    private float time = .0f;

    private Vector2 gotoPos, actual;
    private int stop;
    private final Daedalus map;

    private Texture tx;
    private TextureRegion [][] txrTiles;
    private Animation<TextureRegion>[] animations;

    public Character(Daedalus map) {
        this.map = map;
        reset();
    }

    public Vector2 getActualPos() {
        return actual;
    }
    public void create() {
        this.tx = new Texture("character.png");
        this.txrTiles = TextureRegion.split(tx, WIDTH, HEIGHT);
        animations = new Animation[Direction.DIRECTIONS];
        for(int i = 0; i< animations.length; i++) {
            animations[i] = new Animation<>(
                    ANIM_SPEED,
                    txrTiles[i]
            );
        }
    }
    public void reset() {
        this.stop = ANIM_DOWN;
        this.gotoPos = map.newCenter();
        this.actual  = map.newCenter();
    }
    public void render(SpriteBatch batch, float dt) {
        time += dt;

        if( gotoPos.equals(actual) ) {
            int dx=0, dy=0;

            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)||Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                dx=Gdx.input.isKeyPressed(Input.Keys.LEFT) ? -1:1;
            else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)||Gdx.input.isKeyPressed(Input.Keys.UP))
                dy=Gdx.input.isKeyPressed(Input.Keys.DOWN) ? -1:1;

            assert(((dx==0)||(dy==0))&&(dx>=-1)&&(dx<=1)&&(dy>=-1)&&(dy<=1));
            map.tryMove(actual, dx, dy, gotoPos);
        }

        assert((gotoPos.x==actual.x)||(gotoPos.y==actual.y));
        int anim = ANIMS[Direction.move(actual, gotoPos, new Vector2(SPEED*dt, SPEED*dt))+1];

        TextureRegion txrCharacter = anim<0
                ? txrTiles[stop][0]
                : this.animations[stop = anim].getKeyFrame(time, true);

        batch.draw(
                txrCharacter,
                actual.x+(float)((Daedalus.BACKGROUND_TILE_WIDTH- WIDTH)/2),
                actual.y
        );
    }

    public void dispose() {
        tx.dispose();
    }
}
