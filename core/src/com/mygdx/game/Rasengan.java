package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

/**
 * Created by myolin13 on 1/26/2017.
 */

public class Rasengan {

    private EndlessRunner game;
    private Rectangle beamRec;
    private Array<Rectangle> beamArray;

    Enemy enemy;

    Animation animation;
    float timePassed = 0;

    public Rasengan(EndlessRunner game, Enemy enemy){
        this.game = game;
        this.enemy = enemy;
        beamArray = new Array<Rectangle>();
        animation = new Animation(1/8f, game.imageBank.getBeamAtlas().getRegions());
    }

    public void createBeam(Rectangle runner){
        Rectangle beamRec = new Rectangle();
        beamRec.x = runner.x+runner.width+10;
        beamRec.y = runner.y+15;
        beamRec.width = 50;
        beamRec.height = 50;
        beamArray.add(beamRec);
    }

    public void draw(){
        timePassed += Gdx.graphics.getDeltaTime();
        for(Rectangle beamRec: beamArray) {
            //game.batch.draw(game.imageBank.getBadlogicSprite(), beamRec.x, beamRec.y, beamRec.width, beamRec.height);
            game.batch.draw((TextureRegion)animation.getKeyFrame(timePassed,true), beamRec.x, beamRec.y, beamRec.width, beamRec.height);
        }

    }

    public Rectangle getBeamRec(){
        return beamRec;
    }

    public Array getBeamArray(){
        return beamArray;
    }

    public void iteration(){
        Iterator<Rectangle> iter = beamArray.iterator();
        while (iter.hasNext()) {
            Rectangle beamRec = iter.next();
            beamRec.x += 7;
            if (beamRec.x + 10 > 400){
                iter.remove();
            }
        }
    }




}
