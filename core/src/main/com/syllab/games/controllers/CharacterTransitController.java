package com.syllab.games.controllers;

import com.badlogic.gdx.math.Vector2;
import com.syllab.games.services.GameService;
import com.syllab.games.services.TimeService;
import com.syllab.games.services.gdx.GdxControllerViewService;
import com.syllab.games.services.base.ServiceHost;
import com.syllab.games.utils.Direction;
import com.syllab.games.views.CharacterTransitView;
import com.syllab.games.views.CompositeView;
import com.syllab.games.views.DaedalusView;
import com.syllab.games.views.View;

import java.util.Arrays;

public class CharacterTransitController implements Controller {
    private final View all;
    private final CharacterTransitView characterView;
    private final Vector2 actual, destination;
    private final int dx, dy;

    public CharacterTransitController(int x, int y, int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
        this.actual = new Vector2(x, y);
        this.destination = new Vector2(x+dx, y+dy);
        this.characterView = new CharacterTransitView();
        this.all = new CompositeView(Arrays.asList(
            DaedalusView.getInstance(),
            this.characterView
        ));
    }
    @Override
    public boolean update(ServiceHost services) {
        float step = services.getInstance(TimeService.class).getDeltaTime() * CharacterTransitView.SPEED;
        int direction = Direction.move(this.actual, this.destination, new Vector2(step, step));

        if(this.actual.equals(this.destination)) {
            services.getInstance(GameService.class).getCharacter().move(this.dx, this.dy);
            services.getInstance(GdxControllerViewService.class).change(
                new CharacterOnCellController(direction)
            );
            return true;
        }
        characterView.set(this.actual.x, this.actual.y, direction);
        return false;
    }
    @Override
    public View getView(ServiceHost services) {
        return this.all;
    }
}
