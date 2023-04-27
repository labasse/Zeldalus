package com.syllab.games.services.gdx;

import com.syllab.games.controllers.Controller;
import com.syllab.games.services.base.ServiceHost;
import com.syllab.games.services.base.UpdatableService;

public class GdxControllerViewService implements GdxRenderable, UpdatableService {
    private Controller current;

    public GdxControllerViewService(Controller initial) {
        this.change(initial);
    }
    public void change(Controller current) {
        this.current = current;
    }
    public void update(ServiceHost services) {
        while(this.current.update(services))
            ;
    }
    @Override
    public void render(ServiceHost services) {
        this.current.getView(services).render(services);
    }
}
