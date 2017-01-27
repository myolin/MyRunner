package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

/**
 * Created by myolin13 on 1/26/2017.
 */

public class Ground{

    private EndlessRunner game;
    private Array<Rectangle> groundArray;

    public Ground(EndlessRunner game){
        this.game = game;
        groundArray = new Array<Rectangle>();
    }

    public void createGround(int x, int y){
        Rectangle groundRec = new Rectangle();
        groundRec.x = x;
        groundRec.y = y;
        groundRec.width = 800;
        groundRec.height = 279;
        groundArray.add(groundRec);
    }

    public void iteration(){
        Iterator<Rectangle> iter = groundArray.iterator();
        while (iter.hasNext()) {
            Rectangle groundRec = iter.next();
            groundRec.x -= 5;
            if (groundRec.x + 800 < 0){
                iter.remove();
            }
        }
    }

    public void draw(){
        for(Rectangle ground:groundArray){
            game.batch.draw(game.imageBank.getGroundSprite(), ground.x, ground.y, ground.width, ground.height);
        }
    }

    public Array getGroundArray(){
        return groundArray;
    }

}
