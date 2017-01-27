package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by myolin13 on 1/24/2017.
 */

public class WinScreen implements Screen{

    private EndlessRunner game;
    private int score;

    public WinScreen(EndlessRunner game, int score){
        this.game = game;
        this.score = score;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.font.draw(game.batch,"TO MAIN MENU",350,240);
        game.font.draw(game.batch, "YOUR SCORE: " + Integer.toString(score), 350, 300);
        game.batch.end();

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            game.setScreen(new MainMenuScreen(game));
        }

        if(Gdx.input.isTouched()){
            game.setScreen(new MainMenuScreen(game));
        }


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
