package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Main;
import com.mygdx.game.utils.SoundMuter;
import com.mygdx.game.utils.TextManager;

public class MenuScreen implements Screen {

    private Main main;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private TextureAtlas textureAtlas = new TextureAtlas("atlas1.atlas");
    private com.mygdx.game.utils.TextManager textManager;

    private Music mmMusic;
    private Sound btSound;

    private Sprite backgroundSprite;
    private Sprite optionsBackgroundSprite;
    private Sprite exitButtonSprite;
    private Sprite exitOpButtonSprite;
    private Sprite startButtonSprite;
    private Sprite optionsButtonSprite;
    private Sprite musicOnSprite;
    private Sprite musicOffSprite;
    private Sprite sfxOnSprite;
    private Sprite sfxOffSprite;
    private Sprite lncSprite;

    private static float BUTTON_RESIZE_FACTOR = 1800f;
    private static float START_VERT_POSITION_FACTOR = 4.5f;
    private static float EXIT_VERT_POSITION_FACTOR = 8f;
    private static float OPTIONS_VERT_POSITION_FACTOR = 19f;
    private static float SOUNDDBOTTONS_RESIZE_FACTOR = 0.1f;
    private static float OPTIONBUTTON_RESIZE_FACTOR = 4f;
    private static float OPTIONBUTTON_PROPORTION_FACTOR = 0.215f;
    private static float LNC_PROPORTION_FACTOR = 0.28f;
    private static float LNC_RESIZE_FACTOR = 800f;
    private static float BACKGROUND_RESIZE_FACTOR = 0.8f;
    private static float BACKGROUND_PROPORTION_FACTOR = 0.562f;
    private static float OPTIONSBACKGROUND_PROPORTION_FACTOR = 0.625f;

    public enum State{
        Menu,
        Options
    }

    Vector3 GRU = new Vector3();

    private float width;
    private float height;

    public MenuScreen(Main main) {

        this.main = main;

        textManager = new TextManager();
        textManager.initialize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(width,height);
        camera.setToOrtho(false);
        batch = new SpriteBatch();

        mmMusic = Gdx.audio.newMusic(Gdx.files.internal("mmMusic.mp3"));
        mmMusic.setLooping(true);
        if (SoundMuter.music){
            mmMusic.play();
        }
        btSound = Gdx.audio.newSound(Gdx.files.internal("btSound.wav"));

        backgroundSprite = new Sprite(textureAtlas.findRegion("mm0"));
        optionsBackgroundSprite = new Sprite(textureAtlas.findRegion("opbg0"));
        exitButtonSprite = new Sprite(textureAtlas.findRegion("eb0"));
        exitOpButtonSprite = new Sprite(textureAtlas.findRegion("eb0"));
        startButtonSprite = new Sprite(textureAtlas.findRegion("sb0"));
        optionsButtonSprite = new Sprite(textureAtlas.findRegion("ob0"));
        lncSprite = new Sprite(textureAtlas.findRegion("LnC"));
        musicOnSprite = new Sprite(textureAtlas.findRegion("sonb0"));
        musicOffSprite = new Sprite(textureAtlas.findRegion("soffb0"));
        sfxOnSprite = new Sprite(textureAtlas.findRegion("sonb0"));
        sfxOffSprite = new Sprite(textureAtlas.findRegion("soffb0"));


        backgroundSprite.setSize(width / BACKGROUND_RESIZE_FACTOR, width / BACKGROUND_RESIZE_FACTOR * BACKGROUND_PROPORTION_FACTOR);
        optionsBackgroundSprite.setSize(width, width * OPTIONSBACKGROUND_PROPORTION_FACTOR);
        startButtonSprite.setSize(startButtonSprite.getWidth() *(width/BUTTON_RESIZE_FACTOR), startButtonSprite.getHeight()*(width/BUTTON_RESIZE_FACTOR));
        exitButtonSprite.setSize(exitButtonSprite.getWidth() *(width/BUTTON_RESIZE_FACTOR), exitButtonSprite.getHeight()*(width/BUTTON_RESIZE_FACTOR));
        exitOpButtonSprite.setSize(exitOpButtonSprite.getWidth() *(width/BUTTON_RESIZE_FACTOR), exitOpButtonSprite.getHeight()*(width/BUTTON_RESIZE_FACTOR));
        optionsButtonSprite.setSize(width / OPTIONBUTTON_RESIZE_FACTOR, width / OPTIONBUTTON_RESIZE_FACTOR * OPTIONBUTTON_PROPORTION_FACTOR);
        lncSprite.setSize(lncSprite.getWidth() * width/LNC_RESIZE_FACTOR, lncSprite.getWidth() * width/LNC_RESIZE_FACTOR * LNC_PROPORTION_FACTOR);
        musicOnSprite.setSize(width* SOUNDDBOTTONS_RESIZE_FACTOR, width * SOUNDDBOTTONS_RESIZE_FACTOR);
        musicOffSprite.setSize(width * SOUNDDBOTTONS_RESIZE_FACTOR, width * SOUNDDBOTTONS_RESIZE_FACTOR);
        sfxOnSprite.setSize(width * SOUNDDBOTTONS_RESIZE_FACTOR, width * SOUNDDBOTTONS_RESIZE_FACTOR);
        sfxOffSprite.setSize(width * SOUNDDBOTTONS_RESIZE_FACTOR, width * SOUNDDBOTTONS_RESIZE_FACTOR);


        startButtonSprite.setPosition((width/2f -startButtonSprite.getWidth()/2) , width/START_VERT_POSITION_FACTOR);
        exitButtonSprite.setPosition((width/2f -exitButtonSprite.getWidth()/2) , width/EXIT_VERT_POSITION_FACTOR);
        exitOpButtonSprite.setPosition((width/2f -exitOpButtonSprite.getWidth()/2) , width/19);
        optionsButtonSprite.setPosition((width/2f -optionsButtonSprite.getWidth()/2) , width/OPTIONS_VERT_POSITION_FACTOR);
        lncSprite.setPosition((width/2f - lncSprite.getWidth()/2.5f) , width/3f);
        musicOnSprite.setPosition(width/2f, width/3);
        musicOffSprite.setPosition(width/2f, width/3);
        sfxOnSprite.setPosition(width/2f, width/6);
        sfxOffSprite.setPosition(width/2f, width/6);
    }

    private State state = State.Menu;

    private void handleTouchMenu(){
        if (Gdx.input.isTouched()) {
            GRU.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(GRU);
            float touchX = GRU.x;
            float touchY = GRU.y;
            if (touchX >= startButtonSprite.getX() && touchX <= startButtonSprite.getX() + startButtonSprite.getWidth() &&
                    touchY >= startButtonSprite.getY() && touchY <= startButtonSprite.getY() + startButtonSprite.getHeight()){
                mmMusic.stop();
                main.setScreen(new GameScreen(main));
                if (SoundMuter.sfx){
                    btSound.play();
                }
                Main.lastClick = 0;
            } else if (touchX >= exitButtonSprite.getX() && touchX <= exitButtonSprite.getX() + exitButtonSprite.getWidth() &&
                    touchY >= exitButtonSprite.getY() && touchY <= exitButtonSprite.getY() + exitButtonSprite.getHeight()){
                Gdx.app.exit();
            } else if (touchX >= optionsButtonSprite.getX() && touchX <= optionsButtonSprite.getX() + optionsButtonSprite.getWidth() &&
                    touchY >= optionsButtonSprite.getY() && touchY <= optionsButtonSprite.getY() + optionsButtonSprite.getHeight()){
                state = State.Options;
                Main.lastClick = 0;
                if (SoundMuter.sfx){
                    btSound.play();
                }
            }
        }
    }

    private void handleTouchOptions() {
        if (Gdx.input.isTouched()) {
            GRU.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(GRU);
            float touchX = GRU.x;
            float touchY = GRU.y;
            if (touchX >= exitOpButtonSprite.getX() && touchX <= exitOpButtonSprite.getX() + exitOpButtonSprite.getWidth() &&
                    touchY >= exitOpButtonSprite.getY() && touchY <= exitOpButtonSprite.getY() + exitOpButtonSprite.getHeight()) {
                state = State.Menu;
                Main.lastClick = 0;
                if (SoundMuter.sfx){
                    btSound.play();
                }
            } else if (touchX >= musicOnSprite.getX() && touchX <= musicOnSprite.getX() + musicOnSprite.getWidth() &&
                    touchY >= musicOnSprite.getY() && touchY <= musicOnSprite.getY() + musicOnSprite.getHeight() && SoundMuter.music) {
                mmMusic.stop();
                SoundMuter.music = false;
                if (SoundMuter.sfx){
                    btSound.play();
                }
            } else if (touchX >= sfxOnSprite.getX() && touchX <= sfxOnSprite.getX() + sfxOnSprite.getWidth() &&
                    touchY >= sfxOnSprite.getY() && touchY <= sfxOnSprite.getY() + sfxOnSprite.getHeight() && SoundMuter.sfx) {
                SoundMuter.sfx = false;
                if (SoundMuter.sfx){
                    btSound.play();
                }
            } else if (touchX >= musicOffSprite.getX() && touchX <= musicOffSprite.getX() + musicOffSprite.getWidth() &&
                    touchY >= musicOffSprite.getY() && touchY <= musicOffSprite.getY() + musicOffSprite.getHeight() && !SoundMuter.music) {
                mmMusic.play();
                SoundMuter.music = true;
                if (SoundMuter.sfx){
                    btSound.play();
                }
            } else if (touchX >= sfxOffSprite.getX() && touchX <= sfxOffSprite.getX() + sfxOffSprite.getWidth() &&
                    touchY >= sfxOffSprite.getY() && touchY <= sfxOffSprite.getY() + sfxOffSprite.getHeight() && !SoundMuter.sfx) {
                SoundMuter.sfx = true;
                if (SoundMuter.sfx){
                    btSound.play();
                }
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        switch (state){
            case Menu:{

                Main.lastClick += delta;

                Gdx.gl.glClearColor(1, 1, 1, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                batch.setProjectionMatrix(camera.combined);

                batch.begin();
                backgroundSprite.draw(batch);
                startButtonSprite.draw(batch);
                exitButtonSprite.draw(batch);
                optionsButtonSprite.draw(batch);
                lncSprite.draw(batch);
                batch.end();

                if (Main.lastClick >= 0.2) {
                    handleTouchMenu();
                }

                break;

            } case Options: {

                Main.lastClick += delta;

                Gdx.gl.glClearColor(1, 1, 1, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                batch.setProjectionMatrix(camera.combined);

                batch.begin();
                optionsBackgroundSprite.draw(batch);
                textManager.displayMessage(batch,"Sound",musicOnSprite.getX() - 420, musicOnSprite.getY() + 200, 300);
                textManager.displayMessage(batch,"Sfx",sfxOnSprite.getX() - 280, sfxOnSprite.getY() + 200, 300);
                if (SoundMuter.music){
                    musicOnSprite.draw(batch);
                }
                if (SoundMuter.sfx){
                    sfxOnSprite.draw(batch);
                }
                if (!SoundMuter.music){
                    musicOffSprite.draw(batch);
                }
                if (!SoundMuter.sfx){
                    sfxOffSprite.draw(batch);
                }
                exitOpButtonSprite.draw(batch);
                batch.end();

                if (Main.lastClick >= 0.2) {
                    handleTouchOptions();
                    Main.lastClick = 0;
                }

                break;
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        textureAtlas.dispose();
        batch.dispose();
        mmMusic.dispose();
    }
}
