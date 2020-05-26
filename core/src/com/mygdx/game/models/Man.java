package com.mygdx.game.models;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Polygon;
import com.mygdx.game.control.ManController;

public class Man {

    private TextureAtlas textureAtlas = new TextureAtlas("atlas1.atlas");
    private Sprite manSprite;
    private Polygon bounds;
    private Animation manAnimationUR;
    private Animation manAnimationDR;
    private Animation manAnimationLR;
    private Animation manAnimationRR;
    private Animation manAnimationU;
    private Animation manAnimationD;
    private Animation manAnimationL;
    private Animation manAnimationR;


    public float weigth;
    public float heigth;

    public enum State{
        RUN,
        STOP
    }

    private ManController manController;

    public Man(float x, float y, float weidth, float heigth) {
        this.weigth = weidth;
        this.heigth = heigth;
        bounds = new Polygon(new float[]{x, y, weidth, y, weidth, heigth, x, heigth});
        bounds.setPosition(x, y);
        manAnimationUR = new com.mygdx.game.models.Animation(textureAtlas.findRegion("usl"), 10, 0.7f);
        manAnimationDR = new com.mygdx.game.models.Animation(textureAtlas.findRegion("dsl"), 10, 0.7f);
        manAnimationLR = new com.mygdx.game.models.Animation(textureAtlas.findRegion("lsl"), 10, 0.7f);
        manAnimationRR = new com.mygdx.game.models.Animation(textureAtlas.findRegion("rsl"), 10, 0.7f);
        manAnimationU = new com.mygdx.game.models.Animation(textureAtlas.findRegion("us"), 1, 0.7f);
        manAnimationD = new com.mygdx.game.models.Animation(textureAtlas.findRegion("ds-2"), 3, 0.7f);
        manAnimationL = new com.mygdx.game.models.Animation(textureAtlas.findRegion("ls"), 3, 0.7f);
        manAnimationR = new com.mygdx.game.models.Animation(textureAtlas.findRegion("rs"), 3, 0.7f);
        manController = new ManController(bounds);
    }

    public float getManX() {
        return bounds.getX();
    }

    public float getManY() {
        return bounds.getY();
    }

    private State state = State.STOP;

    private  String direction = "S";

    private void manMove(float delta, Camera camera) {

        if (manController.handle(delta, camera) =="S") {
            manAnimationDR.update(delta);
            manSprite = new Sprite(manAnimationDR.getFrame());
            direction = manController.handle(delta, camera);
            state = State.RUN;
        } else if (manController.handle(delta, camera) == "A") {
            manAnimationLR.update(delta);
            manSprite = new Sprite(manAnimationLR.getFrame());
            direction = manController.handle(delta, camera);
            state = State.RUN;
        } else if (manController.handle(delta, camera) == "D") {
            manAnimationRR.update(delta);
            manSprite = new Sprite(manAnimationRR.getFrame());
            direction = manController.handle(delta, camera);
            state = State.RUN;
        } else if (manController.handle(delta, camera) == "W") {
            manAnimationUR.update(delta);
            manSprite = new Sprite(manAnimationUR.getFrame());
            direction = manController.handle(delta, camera);
            state = State.RUN;
        } else if (direction == "W") {
            manAnimationU.update(delta);
            manSprite = new Sprite(manAnimationU.getFrame());
            state = State.STOP;
        } else if (direction == "S") {
            manAnimationD.update(delta);
            manSprite = new Sprite(manAnimationD.getFrame());
            state = State.STOP;
        } else if (direction == "A") {
            manAnimationL.update(delta);
            manSprite = new Sprite(manAnimationL.getFrame());
            state = State.STOP;
        } else if (direction == "D") {
            manAnimationR.update(delta);
            manSprite = new Sprite(manAnimationR.getFrame());
            state = State.STOP;
        }
    }

    public void draw(SpriteBatch batch, float delta, float weigth, Camera camera) {

        switch (state) {
            case RUN: {
                manMove(delta, camera);
                manSprite.setSize(weigth / 80f, weigth / 80f * 1.28f);
                manSprite.setPosition(bounds.getX(), bounds.getY());
                manSprite.draw(batch);
            } case STOP: {
                manMove(delta, camera);
                manSprite.setSize(weigth / 80f, weigth / 80f * 1.28f);
                manSprite.setPosition(bounds.getX(), bounds.getY());
                manSprite.draw(batch);
            }
        }
    }
}
