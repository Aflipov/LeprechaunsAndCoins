package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextManager {

    static BitmapFont font;
    static float width,height;

    public  void initialize(float width,float height){

        font = new BitmapFont();
        TextManager.width = width;
        TextManager.height= height;
        font.setColor(Color.GOLD);
        font.getData().setScale(width/1000f);
    }

    public  void displayMessage(SpriteBatch batch, String string, float x, float y, float scale){

        font.getData().setScale(width/scale);

        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, string);

        font.draw(batch, glyphLayout, x, y);
    }
}