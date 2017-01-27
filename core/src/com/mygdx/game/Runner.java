package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

/**
 * Created by myolin13 on 1/24/2017.
 */

public class Runner extends Sprite {
    private static float frames;
    private static float gravity;
    private Vector2 gravityVector = new Vector2(0,-1);
    private Vector2 momentum = new Vector2(0,0);
    boolean jumping = false;
    boolean overlap = false;

    Animation animation;
    float timePassed = 0;

    EndlessRunner game;

    Rectangle runnerRec;
    Rectangle buttonRec;
    Rectangle beamRec;

    Rasengan rasengan;
    boolean shoot = false;

    boolean keyWasPressed = false;
    boolean keyIsPressed;

    Array beamArray;
    Rectangle rec;

    Enemy enemy;

    long time;

    int score = 0;
    long scoreTime;

    LeftButton leftButton;

    public Runner(EndlessRunner game, float x, float y, Enemy enemy){
        this.game = game;
        this.enemy = enemy;
        rasengan = new Rasengan(game, enemy);

        leftButton = new LeftButton(game, rasengan, this
        );

        this.setPosition(x,y);
        animation = new Animation(1/10f, game.imageBank.getRunnerAtlas().getRegions());
        runnerRec = new Rectangle(x,y,70,62);
        buttonRec = new Rectangle(Gdx.graphics.getWidth()/2, 0, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
        //beamRec = new Rectangle(0,0,Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
        beamRec = leftButton.getLeftbutton();
        beamArray = rasengan.getBeamArray();
        time = TimeUtils.millis();
        scoreTime = TimeUtils.nanoTime();
        Gdx.input.setInputProcessor(leftButton);
    }

    public void update(int groundY, Array<Rectangle> spaceArray, OrthographicCamera camera){

        groundY = groundY+279;
        frames = Gdx.graphics.getFramesPerSecond();
        frames = (frames == 0) ? 60 : frames;
        gravity = 19.6f/frames;

        if (this.getY() <= groundY) {
            momentum.y = 0;
            landed();
        } else {
            momentum.y -= gravity;
        }

        if(runnerRec.y < groundY){
            runnerRec.y = groundY;
        }else {
            runnerRec.y += momentum.y;
        }


        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && jumping == false){
            momentum.y = gravity * 35;
            jumping = true;
        }

        keyIsPressed = Gdx.input.isKeyPressed(Input.Keys.Z);
        if(keyIsPressed && !keyWasPressed){
            rasengan.createBeam(runnerRec);
            score--;
            shoot = true;
        }
        keyWasPressed = keyIsPressed;

        /*if (Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            isBtnClicked(touchPos);
            isBeamClicked(touchPos);
        }*/

       if (Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            isBtnClicked(touchPos);
        }

        this.translateX(momentum.x);
        this.translateY(momentum.y);

        if(this.getBoundingRectangle().overlaps(spaceArray.get(0))){
            momentum.y -= gravity * 30;
            this.translateY(momentum.y);
        }else {
            this.setY(Math.max(this.getY(), groundY));
        }


        if(this.getBoundingRectangle().getY() <= groundY){
            landed();
        }

        if(TimeUtils.nanoTime() - scoreTime > 300000000 ){
            score++;
            scoreTime = TimeUtils.nanoTime();
        }
    }

    public void isBtnClicked(Vector3 touchPos){
        if(buttonRec.contains(touchPos.x, touchPos.y) && jumping == false){
            momentum.y = gravity * 35;
            jumping = true;
        }
    }

    public void isBeamClicked(Vector3 touchPos){
        if(beamRec.contains(touchPos.x, touchPos.y) && jumping == false){
            rasengan.createBeam(runnerRec);
            shoot = true;
        }
    }

    public Rectangle getRunnerRec(){
        return runnerRec;
    }

    public Rectangle getBeamRec(){
        boolean a = false;
       Iterator<Rectangle> iter = beamArray.iterator();
        while(iter.hasNext()){
            Rectangle rect = iter.next();
            //a = true;
            //if(a){
                rec = rect;
            //}
        }
        return rec;
    }

    public Array getBeamArray(){
        return rasengan.getBeamArray();
    }

    public Rasengan getRasengan(){
        return rasengan;
    }

    @Override
    public void draw(Batch batch) {
        timePassed += Gdx.graphics.getDeltaTime();
        game.batch.draw((TextureRegion)animation.getKeyFrame(timePassed, true), this.getX(), this.getY()-5);
        //game.batch.draw(game.imageBank.getBadlogicSprite(), runnerRec.x, runnerRec.y, runnerRec.width, runnerRec.height);
        //game.batch.draw(game.imageBank.getBadlogicSprite(), buttonRec.x, buttonRec.y, buttonRec.width, buttonRec.height);
        //game.batch.draw(game.imageBank.getBadlogicSprite(), beamRec.x, beamRec.y, beamRec.width, beamRec.height);
        game.font.draw(game.batch, "Score: "+Integer.toString(score), 720, 470);
        if(shoot) {
            rasengan.draw();
        }
        rasengan.iteration();

    }

    public void landed(){
        jumping = false;
    }

    public void gone(){
        shoot = false;
    }

    public int getScore(){
        return score;
    }

    public void setShoot(){
        shoot = true;
    }

    public void decreaseScore(){
        score -= 2;
    }
}
