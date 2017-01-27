package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.sun.org.apache.regexp.internal.RE;

import org.w3c.dom.css.Rect;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by myolin13 on 1/24/2017.
 */

public class GameScreen implements Screen {

    private EndlessRunner game;
    private OrthographicCamera camera;

    private Runner runner;
    private Ground ground;
    private Enemy enemy;
    private GroundGap groundGap;
    private GapCollisionBox gapCollisionBox;

    private Array<Rectangle> gapCollisonBoxArray;
    private Array<Rectangle> enemyArray;
    private Random rand;

    private int groundX;
    private int groundY;
    private int spaceX;
    private int srcX = 0;
    //private int spaceFixed;

    private long time;

    Rectangle runnerRec;

    public GameScreen(EndlessRunner game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        ground = new Ground(game);
        enemy = new Enemy(game);
        groundGap = new GroundGap(game);
        gapCollisionBox = new GapCollisionBox(game);

        rand = new Random();

        groundX = 0;
        groundY = (rand.nextInt(141)+79) * -1;

        //spaceFixed = groundX + 800;
        spaceX = groundX + 800;

        runner = new Runner(game, groundX, groundY+279, enemy);

        gapCollisonBoxArray = gapCollisionBox.getGapCollisonBoxArray();
        enemyArray = enemy.getEnemyArray();

        ground.createGround(groundX, groundY);
        groundGap.createGroundGap(spaceX, groundY);
        gapCollisionBox.createGapCollisionBox(spaceX, groundY+200);
        enemy.createEnemy(groundX+600, groundY+279);

        time = TimeUtils.millis();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(game.imageBank.getBackground(),0,0,srcX,0,(int)camera.viewportWidth, (int)camera.viewportHeight);
        ground.draw();
        //groundGap.draw();
        //gapCollisionBox.draw();
        enemy.draw();
        runner.draw(game.batch);
        game.batch.end();

        srcX += 125 * delta;

        if(TimeUtils.millis() - time > 0){
            groundX += 1100;
            spaceX += 1100; // spaceFixed + 300
            ground.createGround(groundX,groundY);
            groundGap.createGroundGap(spaceX, groundY);
            gapCollisionBox.createGapCollisionBox(spaceX, groundY+200);
            enemy.createEnemy(groundX+600,groundY+279);
            enemy.createEnemy(groundX+800, groundY+279);
            time = TimeUtils.millis();
        }

        runner.update(groundY, groundGap.getGroundGapArray(), camera);

        ground.iteration();
        groundGap.iteration();
        gapCollisionBox.iteration();
        enemy.iteration(runner);

        if(runner.getBoundingRectangle().overlaps(gapCollisonBoxArray.get(0))){
            game.setScreen(new WinScreen(game, runner.getScore()));
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
        game.imageBank.dispose();
    }
}
