package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by myolin13 on 1/27/2017.
 */

public class LeftButton implements InputProcessor {

    Rectangle leftbutton;
    EndlessRunner game;
    OrthographicCamera camera;
    Rasengan rasengan;
    Runner runner;

    public LeftButton(EndlessRunner game, Rasengan rasengan, Runner runner){
        this.game = game;
        this.runner = runner;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        this.rasengan = rasengan;
        leftbutton = new Rectangle();
        leftbutton.x = 0;
        leftbutton.y = 0;
        leftbutton.width = Gdx.graphics.getWidth()/2;
        leftbutton.height = Gdx.graphics.getHeight();
    }

    public Rectangle getLeftbutton(){
        return leftbutton;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode== Input.Keys.A){
            game.setScreen(new WinScreen(game, 1000));
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos);
        if(leftbutton.contains(touchPos.x, touchPos.y)){
            rasengan.createBeam(runner.getRunnerRec());
            runner.decreaseScore();
            runner.setShoot();
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
