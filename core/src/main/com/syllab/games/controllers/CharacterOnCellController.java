package com.syllab.games.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.syllab.games.services.GameService;
import com.syllab.games.services.base.ServiceHost;
import com.syllab.games.services.gdx.GdxControllerViewService;
import com.syllab.games.views.CharacterOnCellView;
import com.syllab.games.views.CompositeView;
import com.syllab.games.views.DaedalusView;
import com.syllab.games.views.View;
import com.syllab.games.models.Character;

import java.util.Arrays;

public class CharacterOnCellController implements Controller {
    private final View view;

    public CharacterOnCellController(int direction) {
        this.view = new CompositeView(Arrays.asList(
            DaedalusView.getInstance(),
            new CharacterOnCellView(direction)
        ));
    }
    @Override
    public boolean update(ServiceHost services) {
        Character character = services.getInstance(GameService.class).getCharacter();
        int dx=0, dy=0;

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)||Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            dx=Gdx.input.isKeyPressed(Input.Keys.LEFT) ? -1:1;
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)||Gdx.input.isKeyPressed(Input.Keys.UP))
            dy=Gdx.input.isKeyPressed(Input.Keys.DOWN) ? -1:1;

        if((dx!=0 || dy!=0) && character.canMove(dx, dy)) {
            services.getInstance(GdxControllerViewService.class).change(
                new CharacterTransitController(character.getX(), character.getY(), dx, dy)
            );
            return true;
        }
        return false;
    }
    @Override
    public View getView(ServiceHost services) {
        return this.view;
    }
}
