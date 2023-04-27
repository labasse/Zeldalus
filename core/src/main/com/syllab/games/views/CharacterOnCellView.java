package com.syllab.games.views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.syllab.games.services.CharacterAssetsService;
import com.syllab.games.services.GameService;
import com.syllab.games.services.base.ServiceHost;
import com.syllab.games.models.Character;

public class CharacterOnCellView extends CharacterView {
    private final int direction;

    public CharacterOnCellView(int direction) {
        this.direction = direction;
    }
    @Override
    public void render(ServiceHost services, SpriteBatch batch) {
        Character ch = services.getInstance(GameService.class).getCharacter();
        TextureRegion tx = services.getInstance(CharacterAssetsService.class).getIdleTexture(this.direction);

        renderCharacter(batch, tx, ch.getX(), ch.getY());
    }
}
