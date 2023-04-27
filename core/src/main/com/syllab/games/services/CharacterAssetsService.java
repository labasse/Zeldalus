package com.syllab.games.services;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.syllab.games.services.base.DisposableService;
import com.syllab.games.utils.Direction;

public class CharacterAssetsService implements DisposableService {
    public final static int WIDTH =48;
    public final static int HEIGHT =64;

    private final static int ANIM_LEFT  =3;
    private final static int ANIM_RIGHT =1;
    private final static int ANIM_UP    =0;
    private final static int ANIM_DOWN  =2;

    private final static float ANIM_SPEED = .07f;

    private final static int[] ANIMS = { ANIM_LEFT, ANIM_RIGHT, ANIM_DOWN, ANIM_UP };

    private final Texture tx;
    private final TextureRegion[][] txrTiles;
    private final Animation<TextureRegion>[] animations;

    public CharacterAssetsService() {
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
    public void dispose() {
        this.tx.dispose();
    }
    public TextureRegion getWalkTexture(int direction, float time) {
        return this.animations[ANIMS[direction]].getKeyFrame(time, true);
    }
    public TextureRegion getIdleTexture(int direction) {
        return this.txrTiles[ANIMS[direction]][1];
    }
}
