package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import org.w3c.dom.css.Rect;

import java.util.Iterator;

/**
 * Created by myolin13 on 1/24/2017.
 */

public class Enemy{

    private EndlessRunner game;
    private Array<Rectangle> enemyArray;

    Rectangle firstEnemy;

    public Enemy(EndlessRunner game){
        this.game = game;
        enemyArray = new Array<Rectangle>();
    }

    public void createEnemy(float x, float y){
        Rectangle enemyRec = new Rectangle();
        enemyRec.x = x;
        enemyRec.y = y;
        enemyRec.width = 45;
        enemyRec.height = 55;
        enemyArray.add(enemyRec);
    }

    public void iteration(Runner runner){
        Iterator<Rectangle> iter = enemyArray.iterator();
        while (iter.hasNext()) {
            Rectangle enemyRec = iter.next();
            enemyRec.x -= 6;
            if (enemyRec.x + 45 < 0){
                iter.remove();
            }
            if(enemyRec.overlaps(runner.getRunnerRec())){
                game.setScreen(new WinScreen(game, runner.getScore()));
            }
            Iterator<Rectangle> iter2 = runner.getBeamArray().iterator();
            while(iter2.hasNext()){
                Rectangle beamRec = iter2.next();
                if(beamRec.overlaps(enemyRec)){
                    iter2.remove();
                    iter.remove();
                }
            }
        }
    }

    public void draw(){
        for(Rectangle enemy: enemyArray){
            game.batch.draw(game.imageBank.getEnemySprite(), enemy.x, enemy.y, enemy.width, enemy.height);
        }
    }

    public Array getEnemyArray(){
        return enemyArray;
    }

    public Rectangle getFirstEnemy(){
        return firstEnemy;
    }
}
