package com.syllab.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.syllab.games.zeldalus.Character;
import com.syllab.games.zeldalus.Daedalus;

public class Zeldalus extends ApplicationAdapter {
	private final Daedalus map;
	private final Character character;

	private SpriteBatch batch;

	public Zeldalus() {
		this.map = new Daedalus();
		this.character = new Character(map);
	}

	@Override
	public void create () {
		this.map.create();
		this.character.create();
		this.batch = new SpriteBatch();
	}

	@Override
	public void render () {
		if(this.map.isOnBorder(character.getActualPos())) {
			this.map.reset();
			this.character.reset();
		}

		float dt = Gdx.graphics.getDeltaTime();

		this.batch.begin();
		{
			this.map.render(this.batch);
			this.character.render(this.batch, dt);
		}
		this.batch.end();
	}
	
	@Override
	public void dispose () {
		this.batch.dispose();
		this.character.dispose();
		this.map.dispose();
	}

}