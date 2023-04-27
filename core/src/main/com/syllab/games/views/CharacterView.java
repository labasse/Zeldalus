package com.syllab.games.views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.syllab.games.services.CharacterAssetsService;
import com.syllab.games.services.base.ServiceHost;

public abstract class CharacterView extends View {
    protected void renderCharacter(SpriteBatch batch, TextureRegion tx, float x, float y) {
        batch.draw(
            tx,
            DaedalusView.MAP_CELL_WIDTH  * .5f * (x+.25f) - CharacterAssetsService.WIDTH / 2,
            DaedalusView.MAP_CELL_HEIGHT * .5f * y
        );
    }
    @Override
    public abstract void render(ServiceHost services, SpriteBatch batch);
}
