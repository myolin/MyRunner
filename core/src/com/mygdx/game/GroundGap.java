package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

/**
 * Created by myolin13 on 1/26/2017.
 */

public class GroundGap {

    private EndlessRunner game;
    private Array<Rectangle> groundGapArray;

    public GroundGap(EndlessRunner game){
        this.game = game;
        groundGapArray = new Array<Rectangle>();
    }

    public void createGroundGap(int x, int y){
        Rectangle groundGapRec = new Rectangle();
        groundGapRec.x = x;
        groundGapRec.y = y;
        groundGapRec.width = 300;
        groundGapRec.height = 300;
        groundGapArray.add(groundGapRec);
    }

    public void iteration(){
        Iterator<Rectangle> iter = groundGapArray.iterator();
        while (iter.hasNext()) {
            Rectangle groundGapRec = iter.next();
            groundGapRec.x -= 5;
            if (groundGapRec.x + 300 < 0){
                iter.remove();
            }
        }
    }

    public void draw(){
        for(Rectangle groundGap: groundGapArray){
            game.batch.draw(game.imageBank.getGroundGapSprite(), groundGap.x, groundGap.y, groundGap.width, groundGap.height);
        }
    }

    public Array getGroundGapArray(){
        return groundGapArray;
    }
}
