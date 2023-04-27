package com.syllab.games.views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.syllab.games.services.gdx.GdxBatchService;
import com.syllab.games.services.base.ServiceHost;

public abstract class View {
    public void render(ServiceHost services) {
        render(services, services.getInstance(GdxBatchService.class).getBatch());
    }
    public abstract void render(ServiceHost services, SpriteBatch batch);
}
