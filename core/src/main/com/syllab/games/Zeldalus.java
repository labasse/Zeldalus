package com.syllab.games;

import com.badlogic.gdx.ApplicationAdapter;
import com.syllab.games.controllers.CharacterOnCellController;
import com.syllab.games.services.base.DisposableService;
import com.syllab.games.services.base.ServiceHost;
import com.syllab.games.services.base.UpdatableService;
import com.syllab.games.services.GameService;
import com.syllab.games.services.TimeService;
import com.syllab.games.services.CharacterAssetsService;
import com.syllab.games.services.DaedalusAssetsService;
import com.syllab.games.services.gdx.GdxBatchService;
import com.syllab.games.services.gdx.GdxControllerViewService;
import com.syllab.games.utils.Direction;

import java.util.Arrays;

public class Zeldalus extends ApplicationAdapter {
	private final ServiceHost host;

	public Zeldalus() {
		this.host = new ServiceHost();
	}
	@Override
	public void create () {
		Arrays.stream(new Object[]{
			new CharacterAssetsService(),
			new DaedalusAssetsService(),
			new TimeService(),
			new GameService(),
			new GdxControllerViewService(new CharacterOnCellController(Direction.DOWN)),
			new GdxBatchService()
		}).forEach(this.host::register);
	}
	@Override
	public void render () {
		this.host.getServices(UpdatableService.class).forEach(s -> s.update(this.host));
	}
	@Override
	public void dispose () {
		this.host.getServices(DisposableService.class).forEach(DisposableService::dispose);
	}
}
