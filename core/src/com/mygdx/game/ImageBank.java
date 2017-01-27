package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by myolin13 on 1/24/2017.
 */

public class ImageBank {


    private Texture background;
    private Texture runner;
    private Texture enemy;
    private Texture ground;
    private Texture groundGap;
    private Texture badlogic;

    private Sprite enemySprite;
    private Sprite groundSprite;
    private Sprite groundGapSprite;
    private Sprite badlogicSprite;

    private TextureAtlas runnerAtlas;
    private TextureAtlas beamAtlas;

    public ImageBank(){
        background = new Texture(Gdx.files.internal("background.jpg"));
        runner = new Texture(Gdx.files.internal("runner.png"));
        enemy = new Texture(Gdx.files.internal("enemy.png"));
        ground = new Texture(Gdx.files.internal("ground.jpg"));
        groundGap = new Texture(Gdx.files.internal("groundgap.png"));
        badlogic = new Texture(Gdx.files.internal("badlogic.jpg"));

        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        enemySprite = createScaledSprite(enemy);
        groundSprite = createScaledSprite(ground);
        groundGapSprite = createScaledSprite(groundGap);
        badlogicSprite = createScaledSprite(badlogic);

        runnerAtlas = new TextureAtlas(Gdx.files.internal("ninjaRun.atlas"));
        beamAtlas = new TextureAtlas(Gdx.files.internal("Rasengan.atlas"));
    }

    public Texture getBackground(){
        return background;
    }

    public Texture getRunner(){
        return runner;
    }

    public Sprite getEnemySprite(){
        return enemySprite;
    }

    public Sprite getGroundSprite(){
        return groundSprite;
    }

    public Sprite getGroundGapSprite(){
       return groundGapSprite;
    }

    public Sprite getBadlogicSprite(){
        return badlogicSprite;
    }

    public Texture getGround(){
        return ground;
    }

    public TextureAtlas getRunnerAtlas(){
        return runnerAtlas;
    }

    public TextureAtlas getBeamAtlas(){
        return beamAtlas;
    }

    public static Sprite createScaledSprite(Texture texture) {
        Sprite sprite = new Sprite(texture);
        sprite.getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        sprite.setSize(sprite.getWidth() / ((texture.getWidth()) / Gdx.graphics.getWidth()),
                sprite.getHeight() / ((texture.getHeight()) / Gdx.graphics.getHeight()));
        return sprite;
    }

    public void dispose(){
        background.dispose();
        runner.dispose();
        enemy.dispose();
        ground.dispose();
        groundGap.dispose();
        badlogic.dispose();
        runnerAtlas.dispose();
        //beamAtlas.dispose();
    }

}
