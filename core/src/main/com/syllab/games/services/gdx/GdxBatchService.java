package com.syllab.games.services.gdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.syllab.games.services.base.DisposableService;
import com.syllab.games.services.base.ServiceHost;
import com.syllab.games.services.base.UpdatableService;

public class GdxBatchService implements UpdatableService, DisposableService {

    private final SpriteBatch batch;

    public GdxBatchService() {
        batch = new SpriteBatch();
    }
    public SpriteBatch getBatch() {
        return this.batch;
    }
    @Override
    public void update(ServiceHost services) {
        this.batch.begin();
        services.getServices(GdxRenderable.class).forEach(s -> s.render(services));
        this.batch.end();
    }
    @Override
    public void dispose() {
        batch.dispose();
    }

}
