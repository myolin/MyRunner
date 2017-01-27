package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class EndlessRunner extends Game {
	SpriteBatch batch;
	BitmapFont font;
	ImageBank imageBank;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		imageBank = new ImageBank();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
