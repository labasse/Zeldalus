package com.syllab.games.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.syllab.games.utils.Direction;
import com.syllab.games.zeldalus.Character;
import com.syllab.games.zeldalus.states.CharacterTextureMap;

public class CharacterView implements Drawable, CharacterTextureMap {

    public final static int WIDTH =48;
    public final static int HEIGHT =64;

    private final static int ANIM_LEFT  =3;
    private final static int ANIM_RIGHT =1;
    private final static int ANIM_UP    =0;
    private final static int ANIM_DOWN  =2;

    private final static float ANIM_SPEED = .07f;

    private final static int[] ANIMS = { ANIM_LEFT, ANIM_RIGHT, ANIM_DOWN, ANIM_UP };

    private float time = .0f;

    private Texture tx;
    private TextureRegion [][] txrTiles;
    private Animation<TextureRegion>[] animations;

    private final Character character;

    public CharacterView(Character character) {
        this.character = character;
    }
    @Override
    public void create() {
        this.tx = new Texture("character.png");
        this.txrTiles = TextureRegion.split(tx, WIDTH, HEIGHT);
        this.animations = new Animation[Direction.DIRECTIONS];
        for(int i = 0; i< animations.length; i++) {
            animations[i] = new Animation<>(
                    ANIM_SPEED,
                    txrTiles[i]
            );
        }
    }
    @Override
    public void render(SpriteBatch batch, float dt) {
        this.time += dt;
        this.character.updateAndProcessInputs(dt);

        Vector2 pos = this.character.getActualPos();

        batch.draw(
                (TextureRegion)this.character.getTexture(this),
                DaedalusView.MAP_CELL_WIDTH  * .5f * (pos.x+.25f) - CharacterView.WIDTH / 2,
                DaedalusView.MAP_CELL_HEIGHT * .5f * pos.y
        );
    }
    @Override
    public void dispose() {
        this.tx.dispose();
    }
    @Override
    public Object getWalkTexture(int direction) {
        return this.animations[ANIMS[direction]].getKeyFrame(this.time, true);
    }
    @Override
    public Object getIdleTexture(int direction) {
        return this.txrTiles[ANIMS[direction]][1];
    }
}
