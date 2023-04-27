package com.syllab.games.controllers;

import com.syllab.games.services.base.ServiceHost;
import com.syllab.games.views.View;

public interface Controller {
    boolean update(ServiceHost services);
    View getView(ServiceHost services);
}
