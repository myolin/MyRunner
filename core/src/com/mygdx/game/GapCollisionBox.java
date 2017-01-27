package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

/**
 * Created by myolin13 on 1/26/2017.
 */

public class GapCollisionBox {

    private EndlessRunner game;
    private Array<Rectangle> gapCollisionBoxArray;

    public GapCollisionBox(EndlessRunner game){
        this.game = game;
        gapCollisionBoxArray = new Array<Rectangle>();
    }

    public void createGapCollisionBox(int x, int y){
        Rectangle gapCollisionRec = new Rectangle();
        gapCollisionRec.x = x;
        gapCollisionRec.y = y;
        gapCollisionRec.width = 300;
        gapCollisionRec.height = 20;
        gapCollisionBoxArray.add(gapCollisionRec);
    }

    public void iteration(){
        Iterator<Rectangle> iter = gapCollisionBoxArray.iterator();
        while (iter.hasNext()) {
            Rectangle gapCollisionRec = iter.next();
            gapCollisionRec.x -= 5;
            if (gapCollisionRec.x + 300 < 0){
                iter.remove();
            }
        }
    }

    public void draw(){
        for(Rectangle gapCollisonbox: gapCollisionBoxArray){
            game.batch.draw(game.imageBank.getBadlogicSprite(), gapCollisonbox.x, gapCollisonbox.y, gapCollisonbox.width, gapCollisonbox.height);
        }
    }

    public Array getGapCollisonBoxArray(){
        return gapCollisionBoxArray;
    }

}
