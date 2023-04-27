package com.syllab.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.syllab.games.views.CharacterView;
import com.syllab.games.views.DaedalusView;
import com.syllab.games.views.Drawable;
import com.syllab.games.zeldalus.Character;
import com.syllab.games.zeldalus.Daedalus;

import java.util.ArrayList;
import java.util.List;

public class Zeldalus extends ApplicationAdapter {
	private final Daedalus map;
	private final Character character;
	private final List<Drawable> drawables;

	private SpriteBatch batch;

	public Zeldalus() {
		this.map = new Daedalus();
		this.character = new Character(map);
		this.drawables = new ArrayList<>();
		this.drawables.add(new DaedalusView(this.map));
		this.drawables.add(new CharacterView(this.character));
	}
	@Override
	public void create () {
		this.drawables.forEach(Drawable::create);
		this.batch = new SpriteBatch();
	}
	@Override
	public void render () {
		if(this.character.isOnBorder()) {
			this.character.reset();
			this.map.reset();
		}
		float dt = Gdx.graphics.getDeltaTime();

		this.batch.begin();
		this.drawables.forEach(d -> d.render(batch, dt));
		this.batch.end();
	}
	@Override
	public void dispose () {
		this.batch.dispose();
		this.drawables.forEach(Drawable::dispose);
	}
}