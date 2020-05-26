package com.mygdx.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.view.GameScreen;

public class ManController {

    private Polygon manBounds;

    public ManController(Polygon manBounds) {
        this.manBounds = manBounds;
    }

    private Vector3 GRU = new Vector3();

    public String handle(float delta, Camera camera){

        //кнопки на экране
        if (Gdx.input.isTouched()) {
            GRU.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(GRU);
            float touchX = GRU.x;
            float touchY = GRU.y;
            if (touchX >= GameScreen.LButtonSprite.getX() && touchX <= GameScreen.LButtonSprite.getX() + GameScreen.LButtonSprite.getWidth() &&
                    touchY >= GameScreen.LButtonSprite.getY() && touchY <= GameScreen.LButtonSprite.getY() + GameScreen.LButtonSprite.getHeight() &&
                    manBounds.getX() + 19.5f / 4f > -70){
                manBounds.setPosition(manBounds.getX() - 10f * delta, manBounds.getY());
                return "A";
            } else if (touchX >= GameScreen.RButtonSprite.getX() && touchX <= GameScreen.RButtonSprite.getX() + GameScreen.RButtonSprite.getWidth() &&
                    touchY >= GameScreen.RButtonSprite.getY() && touchY <= GameScreen.RButtonSprite.getY() + GameScreen.RButtonSprite.getHeight() &&
                    manBounds.getX() + 19.5f / 4f < 70f){
                manBounds.setPosition(manBounds.getX() + 8f * delta, manBounds.getY());
                return "D";
            } else if (touchX >= GameScreen.UButtonSprite.getX() && touchX <= GameScreen.UButtonSprite.getX() + GameScreen.UButtonSprite.getWidth() &&
                    touchY >= GameScreen.UButtonSprite.getY() && touchY <= GameScreen.UButtonSprite.getY() + GameScreen.UButtonSprite.getHeight() &&
                    manBounds.getY() + 25f / 4f < 70f){
                manBounds.setPosition(manBounds.getX(), manBounds.getY() + 6.5f * delta);
                return "W";
            } else if (touchX >= GameScreen.DButtonSprite.getX() && touchX <= GameScreen.DButtonSprite.getX() + GameScreen.DButtonSprite.getWidth() &&
                    touchY >= GameScreen.DButtonSprite.getY() && touchY <= GameScreen.DButtonSprite.getY() + GameScreen.DButtonSprite.getHeight() &&
                    manBounds.getY() + 25f / 4f > -70){
                manBounds.setPosition(manBounds.getX(), manBounds.getY() - 11.5f * delta);
                return "S";
            }
        }

        //клавиатура
        if ((Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) && manBounds.getX() + 19.5f / 4f < 70f) {
            manBounds.setPosition(manBounds.getX() + 8f * delta, manBounds.getY());
            return "D";
        } else if ((Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) && manBounds.getX() + 19.5f / 4f > -70) {
            manBounds.setPosition(manBounds.getX() - 10f * delta, manBounds.getY());
            return "A";
        } else if ((Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) && manBounds.getY() + 25f / 4f > -70) {
            manBounds.setPosition(manBounds.getX(), manBounds.getY() - 11.5f * delta);
            return "S";
        } else if ((Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) && manBounds.getY() + 25f / 4f < 70f) {
            manBounds.setPosition(manBounds.getX(), manBounds.getY() + 6.5f * delta);
            return "W";
        } else {
            return null;
        }
    }
}
