package com.mygdx.game.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {

    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;
    private TextureRegion texture;

    public Animation(TextureRegion textureRegion, int frameCount, float cycleTime) {
        texture = textureRegion;
        frames = new Array<TextureRegion>();
        int frameWigth = texture.getRegionWidth() / frameCount;
        for (int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(texture, i * frameWigth, 0, frameWigth, texture.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }

    public void update(float delta) {
        currentFrameTime += delta;
        if (currentFrameTime >= maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }
        if (frame >= frameCount) {
            frame = 0;
        }

    }

    public TextureRegion getFrame() {
        return frames.get(frame);
    }
}
