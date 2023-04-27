package com.syllab.games.views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.syllab.games.services.base.ServiceHost;

public class CompositeView extends View {
    private final Iterable<View> views;

    public CompositeView(Iterable<View> views) {
        this.views = views;
    }
    @Override
    public void render(ServiceHost services, SpriteBatch batch) {
        views.forEach(v -> v.render(services));
    }
}
