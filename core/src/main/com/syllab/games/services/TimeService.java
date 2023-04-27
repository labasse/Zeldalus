package com.syllab.games.services;

import com.badlogic.gdx.Gdx;
import com.syllab.games.services.base.ServiceHost;
import com.syllab.games.services.base.UpdatableService;

public class TimeService implements UpdatableService {
    private float dt = 0f;
    private float time = 0f;

    @Override
    public void update(ServiceHost services) {
        dt = Gdx.graphics.getDeltaTime();
        time += dt;
    }
    public float getTime() {
        return time;
    }
    public float getDeltaTime() {
        return dt;
    }
}
