package com.mygdx.game.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.utils.Assets;

public class Map {

    private Assets assets;

    private TextureAtlas textureAtlas = new TextureAtlas("atlas1.atlas");

    float width;

    private Sprite grassSprite;
    private Sprite treesLUSprite;
    private Sprite treesRUSprite;
    private Sprite treesLDSprite;
    private Sprite treesRDSprite;
    private Sprite treesUSprite;
    private Sprite treesDSprite;

    private static float GRASS_RESIZE_FACTOR = 0.5f;
    private static float GRASS_PROPORTIONS_FACTOR = 0.6f;
    private static float TREES1_RESIZE_FACTOR = 0.1f;
    private static float TREES2_RESIZE_FACTOR = 0.2f;
    private static float TREES1_PROPORTIONS_FACTOR = 0.543f;
    private static float TREES2_PROPORTIONS_FACTOR = 1.44f;

    public Map(float wEigth) {

        assets = new Assets();

        this.width = 800;

        grassSprite = new Sprite(textureAtlas.findRegion("grass"));
        treesLUSprite = new Sprite(textureAtlas.findRegion("trees2"));
        treesRUSprite = new Sprite(textureAtlas.findRegion("trees2"));
        treesLDSprite = new Sprite(textureAtlas.findRegion("trees2"));
        treesRDSprite = new Sprite(textureAtlas.findRegion("trees2"));
        treesUSprite = new Sprite(textureAtlas.findRegion("trees1"));
        treesDSprite = new Sprite(textureAtlas.findRegion("trees1"));

        grassSprite.setSize(width * GRASS_RESIZE_FACTOR, width * GRASS_RESIZE_FACTOR * GRASS_PROPORTIONS_FACTOR);
        treesLUSprite.setSize(width * TREES1_RESIZE_FACTOR, width * TREES1_RESIZE_FACTOR * TREES2_PROPORTIONS_FACTOR);
        treesRUSprite.setSize(width * TREES1_RESIZE_FACTOR, width * TREES1_RESIZE_FACTOR * TREES2_PROPORTIONS_FACTOR);
        treesLDSprite.setSize(width * TREES1_RESIZE_FACTOR, width * TREES1_RESIZE_FACTOR * TREES2_PROPORTIONS_FACTOR);
        treesRDSprite.setSize(width * TREES1_RESIZE_FACTOR, width * TREES1_RESIZE_FACTOR * TREES2_PROPORTIONS_FACTOR);
        treesUSprite.setSize(width * TREES2_RESIZE_FACTOR, width * TREES2_RESIZE_FACTOR * TREES1_PROPORTIONS_FACTOR);
        treesDSprite.setSize(width * TREES2_RESIZE_FACTOR, width * TREES2_RESIZE_FACTOR * TREES1_PROPORTIONS_FACTOR);

        grassSprite.setPosition(-grassSprite.getWidth()/2, -grassSprite.getHeight()/2);
        treesUSprite.setPosition(- treesUSprite.getWidth()/2, 70);
        treesDSprite.setPosition(- treesDSprite.getWidth()/2, -76 - treesDSprite.getHeight());
        treesLUSprite.setPosition(-72 - treesLUSprite.getWidth(), -2);
        treesRUSprite.setPosition(72, -2);
        treesLDSprite.setPosition(-72 - treesLUSprite.getWidth(), -treesLUSprite.getHeight() + 2);
        treesRDSprite.setPosition(72, -treesLDSprite.getHeight() + 2);
    }

    public void draw(SpriteBatch batch){
        grassSprite.draw(batch);
        treesUSprite.draw(batch);
        treesLUSprite.draw(batch);
        treesRUSprite.draw(batch);
        treesLDSprite.draw(batch);
        treesRDSprite.draw(batch);
        treesDSprite.draw(batch);
    }
}
