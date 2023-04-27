package com.syllab.games.views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.syllab.games.services.CharacterAssetsService;
import com.syllab.games.services.TimeService;
import com.syllab.games.services.base.ServiceHost;

public class CharacterTransitView extends CharacterView {
    public final static float SPEED = 4f;

    private float x, y;
    private int direction;

    public void set(float x, float y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    @Override
    public void render(ServiceHost services, SpriteBatch batch) {
        float time = services.getInstance(TimeService.class).getTime();
        TextureRegion tx = services.getInstance(CharacterAssetsService.class).getWalkTexture(this.direction, time);

        this.renderCharacter(batch, tx, x, y);
    }
}
