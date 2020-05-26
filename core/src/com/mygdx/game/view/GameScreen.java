package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.mygdx.game.models.Coin;
import com.mygdx.game.models.Man;
import com.mygdx.game.models.Map;
import com.mygdx.game.utils.SoundMuter;
import com.mygdx.game.utils.TextManager;

public class GameScreen implements Screen {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private TextureAtlas textureAtlas = new TextureAtlas("atlas1.atlas");
    private com.mygdx.game.utils.TextManager textManager;

    private Map map;

    private static Music mgMusic;
    private static Sound btSound;
    private static Sound cnSound;
    private static Sound psSound;

    private Sprite grassSprite;
    private Sprite treesLUSprite;
    private Sprite treesRUSprite;
    private Sprite treesLDSprite;
    private Sprite treesRDSprite;
    private Sprite treesUSprite;
    private Sprite treesDSprite;

    public static Sprite LButtonSprite;
    public static Sprite RButtonSprite;
    public static Sprite UButtonSprite;
    public static Sprite DButtonSprite;

    private Sprite pauseButtonSprite;
    private Sprite pauseBackgroundSprite;
    private Sprite continueButtonSprite;
    private Sprite exitButtonSprite;

    private Coin coin;
    private Man man;
    private int score;

    private static float BUTTON_RESIZE_FACTOR = 10000f;
    private static float PAUSEBUTTON_RESIZE_FACTOR = 50f;
    private static float MOVEBUTTON_RESIZE_FACTOR = 50f;
    private static float GRASS_RESIZE_FACTOR = 0.5f;
    private static float GRASS_PROPORTIONS_FACTOR = 0.6f;
    private static float TREES1_RESIZE_FACTOR = 0.1f;
    private static float TREES1_PROPORTIONS_FACTOR = 0.543f;
    private static float PAUSEBACKGROUND_RESIZE_FACTOR = 4f;
    private static float PAUSEBACKGROUND_PROPORTIONS_FACTOR = 0.562f;
    private static float MAN_RESIZE_FACTOR = 100f;
    private static float MAN_PROPORTIONS_FACTOR = 1.28f;
    private static float TREES2_RESIZE_FACTOR = 0.2f;
    private static float TREES2_PROPORTIONS_FACTOR = 1.44f;

    Main main;

    Vector3 GRU = new Vector3();

    public enum State {
        RUN,
        PAUSE
    }

    float width;
    float height;

    GameScreen(Main main) {

        this.main = main;

        map = new Map(width);

        width = 800;
        camera = new OrthographicCamera(width,height);
        camera.setToOrtho(false);
        batch = new SpriteBatch();

        mgMusic = Gdx.audio.newMusic(Gdx.files.internal("mgMusic.mp3"));
        mgMusic.setLooping(true);
        if (SoundMuter.music) {
            mgMusic.play();
        }
        btSound = Gdx.audio.newSound(Gdx.files.internal("btSound.wav"));
        cnSound = Gdx.audio.newSound(Gdx.files.internal("cnSound.wav"));
        psSound = Gdx.audio.newSound(Gdx.files.internal("psSound.wav"));

        grassSprite = new Sprite(textureAtlas.findRegion("grass"));
        treesLUSprite = new Sprite(textureAtlas.findRegion("trees2"));
        treesRUSprite = new Sprite(textureAtlas.findRegion("trees2"));
        treesLDSprite = new Sprite(textureAtlas.findRegion("trees2"));
        treesRDSprite = new Sprite(textureAtlas.findRegion("trees2"));
        treesUSprite = new Sprite(textureAtlas.findRegion("trees1"));
        treesDSprite = new Sprite(textureAtlas.findRegion("trees1"));

        LButtonSprite = new Sprite(textureAtlas.findRegion("lBottob"));
        RButtonSprite = new Sprite(textureAtlas.findRegion("rButton"));
        UButtonSprite = new Sprite(textureAtlas.findRegion("uButton"));
        DButtonSprite = new Sprite(textureAtlas.findRegion("dButton"));

        pauseButtonSprite = new Sprite(textureAtlas.findRegion("pb0"));
        pauseBackgroundSprite = new Sprite(textureAtlas.findRegion("ps0"));
        continueButtonSprite = new Sprite(textureAtlas.findRegion("cb0-2"));
        exitButtonSprite = new Sprite(textureAtlas.findRegion("eb0"));
        coin = new Coin();
        man = new Man(0, 0, width/MAN_RESIZE_FACTOR, width/MAN_RESIZE_FACTOR * MAN_PROPORTIONS_FACTOR);
        score = 0;

        textManager = new TextManager();
        textManager.initialize(width, height);

        grassSprite.setSize(width * GRASS_RESIZE_FACTOR, width * GRASS_RESIZE_FACTOR * GRASS_PROPORTIONS_FACTOR);
        treesLUSprite.setSize(width * TREES1_RESIZE_FACTOR, width * TREES1_RESIZE_FACTOR * TREES2_PROPORTIONS_FACTOR);
        treesRUSprite.setSize(width * TREES1_RESIZE_FACTOR, width * TREES1_RESIZE_FACTOR * TREES2_PROPORTIONS_FACTOR);
        treesLDSprite.setSize(width * TREES1_RESIZE_FACTOR, width * TREES1_RESIZE_FACTOR * TREES2_PROPORTIONS_FACTOR);
        treesRDSprite.setSize(width * TREES1_RESIZE_FACTOR, width * TREES1_RESIZE_FACTOR * TREES2_PROPORTIONS_FACTOR);
        treesUSprite.setSize(width * TREES2_RESIZE_FACTOR, width * TREES2_RESIZE_FACTOR * TREES1_PROPORTIONS_FACTOR);
        treesDSprite.setSize(width * TREES2_RESIZE_FACTOR, width * TREES2_RESIZE_FACTOR * TREES1_PROPORTIONS_FACTOR);

        RButtonSprite.setSize(width / MOVEBUTTON_RESIZE_FACTOR, width / MOVEBUTTON_RESIZE_FACTOR);
        LButtonSprite.setSize(width / MOVEBUTTON_RESIZE_FACTOR, width / MOVEBUTTON_RESIZE_FACTOR);
        UButtonSprite.setSize(width / MOVEBUTTON_RESIZE_FACTOR, width / MOVEBUTTON_RESIZE_FACTOR);
        DButtonSprite.setSize(width / MOVEBUTTON_RESIZE_FACTOR, width / MOVEBUTTON_RESIZE_FACTOR);

        pauseBackgroundSprite.setSize(width/ PAUSEBACKGROUND_RESIZE_FACTOR, width/ PAUSEBACKGROUND_RESIZE_FACTOR * PAUSEBACKGROUND_PROPORTIONS_FACTOR);
        pauseButtonSprite.setSize(width/PAUSEBUTTON_RESIZE_FACTOR, width/PAUSEBUTTON_RESIZE_FACTOR);
        continueButtonSprite.setSize(continueButtonSprite.getWidth() *(width/BUTTON_RESIZE_FACTOR), continueButtonSprite.getHeight()*(width/BUTTON_RESIZE_FACTOR));
        exitButtonSprite.setSize(exitButtonSprite.getWidth() *(width/BUTTON_RESIZE_FACTOR), exitButtonSprite.getHeight()*(width/BUTTON_RESIZE_FACTOR));

        grassSprite.setPosition(-grassSprite.getWidth()/2, -grassSprite.getHeight()/2);
        treesUSprite.setPosition(- treesUSprite.getWidth()/2, 70);
        treesDSprite.setPosition(- treesDSprite.getWidth()/2, -76 - treesDSprite.getHeight());
        treesLUSprite.setPosition(-72 - treesLUSprite.getWidth(), -2);
        treesRUSprite.setPosition(72, -2);
        treesLDSprite.setPosition(-72 - treesLUSprite.getWidth(), -treesLUSprite.getHeight() + 2);
        treesRDSprite.setPosition(72, -treesLDSprite.getHeight() + 2);
    }

    private void handleTouchGame() {
        if (Gdx.input.isTouched()) {
            GRU.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(GRU);
            float touchX = GRU.x;
            float touchY = GRU.y;
            if (touchX >= pauseButtonSprite.getX() && touchX <= pauseButtonSprite.getX() + pauseButtonSprite.getWidth() &&
                    touchY >= pauseButtonSprite.getY() && touchY <= pauseButtonSprite.getY() + pauseButtonSprite.getHeight()){
                mgMusic.setVolume(0.4f);
                if (SoundMuter.sfx){
                    psSound.play();
                }
                state = State.PAUSE;
                Main.lastClick = 0;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            mgMusic.setVolume(0.2f);
            if (SoundMuter.sfx){
                psSound.play();
            }
            state = State.PAUSE;
            Main.lastClick = 0;
        }
    }

    private void handleTouchPause() {
        if (Gdx.input.isTouched()) {
            GRU.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(GRU);
            float touchX = GRU.x;
            float touchY = GRU.y;
            if (touchX >= continueButtonSprite.getX() && touchX <= continueButtonSprite.getX() + continueButtonSprite.getWidth() &&
                    touchY >= continueButtonSprite.getY() && touchY <= continueButtonSprite.getY() + continueButtonSprite.getHeight()){
                mgMusic.setVolume(1f);
                if (SoundMuter.sfx){
                    btSound.play();
                }
                state = State.RUN;
                Main.lastClick = 0;
            } else if (touchX >= exitButtonSprite.getX() && touchX <= exitButtonSprite.getX() + exitButtonSprite.getWidth() &&
                    touchY >= exitButtonSprite.getY() && touchY <= exitButtonSprite.getY() + exitButtonSprite.getHeight()){
                mgMusic.pause();
                if (SoundMuter.sfx){
                    btSound.play();
                }
                main.setScreen(new MenuScreen(main));
                Main.lastClick = 0;
            }
        }
    }

    @Override
    public void show() {
    }

    private State state = State.RUN;

    private float masagePposition = 33;

    @Override
    public void render(float delta) {

        switch (state) {
            case RUN: {

                Main.lastClick += delta;

                camera.position.x = man.getManX() + 19.5f / 4f;
                camera.position.y = man.getManY() + 25f / 4f;
                camera.update();

                pauseButtonSprite.setPosition(man.getManX() - 75, man.getManY() + 35);
                RButtonSprite.setPosition(man.getManX() - 40, man.getManY() - 18);
                LButtonSprite.setPosition(man.getManX() - 40 - 2 * LButtonSprite.getWidth(), man.getManY() - 18);
                UButtonSprite.setPosition(man.getManX() - 40 - UButtonSprite.getWidth(), man.getManY() - 18 + UButtonSprite.getHeight());
                DButtonSprite.setPosition(man.getManX() - 40 - UButtonSprite.getWidth(), man.getManY() - 18 - UButtonSprite.getHeight());

                if (coin.checkCollisions(man.weigth, man.heigth, camera)) {
                    if (SoundMuter.sfx){
                        cnSound.play();
                    }
                    score++;
                    if (score == 100){
                        masagePposition = 28;
                    }
                }

                Gdx.gl.glClearColor(0, 0, 0, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

                batch.setProjectionMatrix(camera.combined);
                batch.begin();
                //map.draw(batch);
                grassSprite.draw(batch);
                treesUSprite.draw(batch);
                treesLUSprite.draw(batch);
                treesRUSprite.draw(batch);
                treesLDSprite.draw(batch);
                treesRDSprite.draw(batch);
                treesDSprite.draw(batch);
                coin.draw(batch, delta, width);
                man.draw(batch, delta, width, camera);
                pauseButtonSprite.draw(batch);
                RButtonSprite.draw(batch);
                LButtonSprite.draw(batch);
                UButtonSprite.draw(batch);
                DButtonSprite.draw(batch);
                textManager.displayMessage(batch, "Coins: " + score, man.getManX() + masagePposition, man.getManY() + 49, 1000);
                batch.end();

                if (Main.lastClick >= 0.2   ) {
                    handleTouchGame();
                }

                break;

            } case PAUSE: {

                Main.lastClick += delta;

                pauseBackgroundSprite.setPosition(camera.position.x - pauseBackgroundSprite.getWidth() / 2f,
                        camera.position.y - pauseBackgroundSprite.getHeight() / 2f);
                continueButtonSprite.setPosition((camera.position.x -continueButtonSprite.getWidth()/2) ,
                        camera.position.y);
                exitButtonSprite.setPosition((camera.position.x -exitButtonSprite.getWidth()/2) ,
                        camera.position.y - 20);

                Gdx.gl.glClearColor(1, 1, 1, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

                batch.setProjectionMatrix(camera.combined);
                batch.begin();
                pauseBackgroundSprite.draw(batch);
                continueButtonSprite.draw(batch);
                exitButtonSprite.draw(batch);
                textManager.displayMessage(batch, "Coins: " + score, man.getManX() - 19, man.getManY() + 33, 1000);
                batch.end();

                if (Main.lastClick >= 0.2) {
                    handleTouchPause();
                    Main.lastClick = 0;
                }

                break;
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) height / width;
        camera = new OrthographicCamera(40f, 40f * aspectRatio);
        camera.zoom = 4f;
        camera.update();
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
        mgMusic.dispose();
        btSound.dispose();
        cnSound.dispose();
        psSound.dispose();
    }
}