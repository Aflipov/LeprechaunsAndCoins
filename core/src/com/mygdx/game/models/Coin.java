package com.mygdx.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.Random;

public class Coin {

    private TextureAtlas textureAtlas = new TextureAtlas("atlas1.atlas");
    private Sprite coinSprite;
    private Animation coinAnimation;
    private float coinX;
    private float coinY;
    static Random random = new Random();

    public Coin() {
        coinX = 68 - random.nextInt(136);
        coinY = 68 - random.nextInt(136);

        coinAnimation = new Animation(textureAtlas.findRegion("coin"), 8, 1);
        coinSprite = new Sprite(coinAnimation.getFrame());
        coinSprite.setSize(Gdx.graphics.getWidth() / 100f, Gdx.graphics.getWidth() / 100f);
        coinSprite.setPosition(coinX, coinY);
    }

    public boolean checkCollisions(float weigth, float heigth, Camera camera) {
        if(camera.position.x + weigth/2 >= coinSprite.getX() && camera.position.y + heigth/2 >= coinSprite.getY() &&
                camera.position.x - weigth/2 <= coinSprite.getX() + coinSprite.getWidth() && camera.position.y - heigth/2 <= coinSprite.getY() + coinSprite.getHeight()) {
            coinX = 68 - random.nextInt(136);
            coinY = 68 - random.nextInt(136);
            return true;
        } else {
            return false;
        }
    }

    public void draw(SpriteBatch batch, float delta, float weigth) {
        coinAnimation.update(delta);
        coinSprite = new Sprite(coinAnimation.getFrame());
        coinSprite.setSize(weigth / 100f, weigth / 100f);
        coinSprite.setPosition(coinX, coinY);
        coinSprite.draw(batch);
    }

    public void dispose() {
        textureAtlas.dispose();
    }
}
