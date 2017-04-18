package com.mygdx.magicappgame.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.magicappgame.MyGdxGame;
import com.mygdx.magicappgame.Scenes.Hud;
import com.mygdx.magicappgame.Shapes.BalancePlatform;
import com.mygdx.magicappgame.Tools.MyInputProcessor;
import com.mygdx.magicappgame.Tools.MyTextInputListener;
import com.mygdx.magicappgame.levels.Level;
import com.mygdx.magicappgame.levels.Level1;
import com.mygdx.magicappgame.levels.Level2;
import com.mygdx.magicappgame.levels.Level3;
import com.mygdx.magicappgame.levels.Level4;
import com.mygdx.magicappgame.levels.Level5;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jiayin Qu on 2017/2/11.
 * This PlayScreen class is passed the Gdx game and is in control when the game is in "play."
 */
public class PlayScreen implements Screen{
    private MyGdxGame game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;
    private Stage stage;

    // Backgrounds
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private ShapeRenderer shapeRenderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    private Long lifeTime;
    private Long delay = 2000L;

    private BalancePlatform plat;
    private Vector2 screenPos;
    private Body currentBod;
    private HashMap<Body, Sprite> bodyMap;

    // Level setups
    private ArrayList<Level> levels;

    private Level currentLevel;
    private int levelCount;

    private Boolean somethingOnScreen;

    private Image pauseImage;
    private boolean pauseTouched;
    private boolean moveAllowed;
    private boolean firstDraw;

    private Sprite platformSprite;
    private Sprite pivotSprite;

    private Integer levelTime;

    private Sound startSound, losingSound, winningSound;
    MyTextInputListener listener;

    private MyInputProcessor newInputProcessor;

    final float PIXELS_TO_METERS = 100f;


    /**
     * This constructor simply initializes everything.
     * @param game the main game
     */

    public PlayScreen(MyGdxGame game) {
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, gamecam);
        hud = new Hud(game.batch);

        shapeRenderer = new ShapeRenderer();
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -40f), true); //The game's gravity
        b2dr = new Box2DDebugRenderer();
        stage = new Stage(gamePort);

        //world.setContactListener(new WorldContactListener());
        //Gdx.input.setInputProcessor(newInputProcessor);


        plat = new BalancePlatform(world);
        platformSprite = drawPlatformTex();
        pivotSprite = drawPivot();
        screenPos = new Vector2(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight());
        bodyMap = new HashMap<Body, Sprite>();

        firstDraw = true;
        somethingOnScreen = false;
        pauseTouched = false;
        moveAllowed = true;

        lifeTime = System.currentTimeMillis();

        listener = new MyTextInputListener();

        startSound = Gdx.audio.newSound(Gdx.files.internal("sound/TheStart.mp3"));
        startSound.play(1.0f);
        losingSound = Gdx.audio.newSound(Gdx.files.internal("sound/FailSoundMix.mp3"));
        winningSound = Gdx.audio.newSound(Gdx.files.internal("sound/ShortTriumphal.mp3"));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        final Texture pauseButton =  new Texture("PauseButton.png");
        Drawable pauseDrawable = new TextureRegionDrawable(new TextureRegion(pauseButton));
        pauseImage = new Image(pauseDrawable);
        pauseImage.setHeight(25);
        pauseImage.setWidth(25);
        pauseImage.setPosition((gamePort.getWorldWidth() / 2) - pauseImage.getWidth() / 2, 350);

        pauseImage.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("pause", "Pressed");
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                pauseTouched = true;
                pausePopUp();
            }
        });

        stage.addActor(pauseImage);

    }

    /**
     * Handles all of the input for the game
     * @param dt delta time
     */
    private void handleInput(float dt){


        if(Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            if(!currentLevel.levelComplete && ((bodyMap.size() == 0) || (somethingOnScreen && (currentBod.getLinearVelocity().y > -.5)))){
                somethingOnScreen = true;
                Hud.minusBox();
                if (!firstDraw)
                    bodyMap.get(currentBod).setColor(Color.GRAY);
                else
                    firstDraw = false;

                currentBod = currentLevel.getNextBod();

                Sprite squareSprite = drawSquareTex();
                squareSprite.setSize(currentLevel.getWidth() * 2f, currentLevel.getHeight() * 2f);
                bodyMap.put(currentBod, squareSprite);
                bodyMap.get(currentBod).setColor(Color.WHITE);

            }
            else if (currentLevel.levelComplete && (currentBod.getLinearVelocity().y > -.5)) {
                moveAllowed = false;
                levelTime = hud.getTime();
                winningSound.play(1.0f);
                nextLevelLabel();

            }
        }

        // Moves the current falling shape to the left
        if(moveAllowed && Gdx.input.isKeyPressed(Input.Keys.LEFT)&& somethingOnScreen){
            currentBod.applyForce(new Vector2(-250000f, 0), currentBod.getWorldCenter(), true);
        }
        // Moves the current falling shape to the right
        if(moveAllowed && Gdx.input.isKeyPressed(Input.Keys.RIGHT)&& somethingOnScreen){
            currentBod.applyForce(new Vector2(250000f, 0), currentBod.getWorldCenter(), true);
        }

        if (currentLevel.levelComplete && Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            currentLevel.clearLevel();
            levelCount++;
            Hud.addLevel();
            hud.resetTime();
            hud.resetBox();
            currentLevel = levels.get(levelCount - 1);
            moveAllowed = true;
            refresh();
        }


    }

    /**
     * Draws the label on the screen that tells the user that
     * they beat the level and the key to press to advance to the next level
     */
    private void nextLevelLabel() {
        Label nextLevel = new Label("CONGRATULATIONS!\n\n\n" +
                "You beat the level!\n" +
                "in " + levelTime + " seconds\n"+
                "Press ENTER to move on \n" +
                "to the next level", MyGdxGame.gameSkin);
        nextLevel.setFontScale(1);
        nextLevel.setColor(Color.GREEN);
        nextLevel.setWidth(Gdx.graphics.getWidth() / 10);
        nextLevel.setPosition(80, 300);
        nextLevel.setAlignment(1);
        stage.addActor(nextLevel);
    }


    /**
     * Removes the bodies from the map from the World,
     * clears bodyMap,
     * updates the current level,
     * removes the bodies created by the BalancePlatform,
     * and makes a new BalancePlatform
     */
    private void refresh() {
        for (Body key :bodyMap.keySet()) {
            world.destroyBody(key);
        }
        bodyMap.clear();
        stage.clear();
        currentLevel.count = 0;
        firstDraw = true;
        if (currentLevel.levelComplete) {
            currentLevel = levels.get(levelCount - 1);
        }
        world.destroyBody(plat.bod1);
        world.destroyBody(plat.bod2);
        plat = new BalancePlatform(world);

        startSound.dispose();
        hud.resetTime();
        hud.resetLevel();
        hud.resetBox();
    }

    /**
     * Updating the game and "stepping"
     * @param dt delta time
     */
    private void update(float dt){
        if(!pauseTouched){
            handleInput(dt);
            world.step(1/60f, 6, 2);

            hud.update(dt);

            gamecam.update();
            //renderer.setView(gamecam); // used for the background
        }
    }

    /**
     * Clears the screen and then draws everything again
     * @param delta delta time
     */
    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        drawPlatform(game.batch);
        drawPivot(game.batch);

        if(bodyMap.size()!=0){
            draw(game.batch);
        }

        //b2dr.render(world, gamecam.combined);
        stage.draw();

        if(gameOver()){
            refresh();
            losingSound.play(1.0f);
            Gdx.input.getTextInput(listener, "Player's Name", "", "Enter your name");
            game.setScreen(game.gameOverScreen);
        }
    }


    /**
     * The function that ends the current game and brings up the exit screen
     */
    private boolean gameOver(){
        for(Body aBody : bodyMap.keySet()) {
            if (aBody.getWorldCenter().y < plat.bod2.getWorldCenter().y - 60)
                return true;

        }
        return false;
    }


    /**
     * Puts a Texture with a Sprite
     * @return the Sprite
     */
    private Sprite drawSquareTex(){
        return new Sprite(new Texture("BlueSquare.png"));

    }

    private Sprite drawPlatformTex(){
        return new Sprite(new Texture("Platform.jpg"));
    }

    private Sprite drawPivot(){
        return new Sprite(new Texture("Pivot.jpg"));
    }


    private void draw(Batch batch){
        batch.begin();
        Sprite sprite;
        Body body;

        for (HashMap.Entry<Body, Sprite> entry: bodyMap.entrySet()) {
            body = entry.getKey();
            sprite = entry.getValue();

            sprite.setRotation((float)Math.toDegrees(body.getAngle()));
            sprite.setPosition(body.getPosition().x - (sprite.getWidth()/2),
                    body.getPosition().y - (sprite.getHeight()/2));
            sprite.setOriginCenter();

            sprite.draw(batch);
        }

        batch.end();

    }

    private void drawPlatform(Batch batch){
        batch.begin();
        platformSprite.setSize(plat.getWidth() *2f,plat.getHeight()*2f);
        platformSprite.setRotation((float)Math.toDegrees(plat.bod1.getAngle()));
        platformSprite.setPosition(plat.bod1.getPosition().x-(platformSprite.getWidth()/2),plat.bod1.getPosition().y - (platformSprite.getHeight()/2));
        platformSprite.setOriginCenter();
        platformSprite.draw(batch);
        batch.end();
    }

    private void drawPivot(Batch batch){
        batch.begin();
        pivotSprite.setSize(15 *2f,5 *2f);
        pivotSprite.setRotation((float)Math.toDegrees(plat.bod2.getAngle()));
        pivotSprite.setPosition(plat.bod2.getPosition().x-(pivotSprite.getWidth()/2),plat.bod2.getPosition().y - (pivotSprite.getHeight()/2));
        pivotSprite.setOriginCenter();
        pivotSprite.draw(batch);
        batch.end();
    }


    /**
     * Change Levels based on levelCount
     */
    public void setUpLevels() {
        levelCount = 1;

        levels = new ArrayList<Level>();

        Level1 level1 = new Level1(game);
        Level2 level2 = new Level2(game);
        Level3 level3 = new Level3(game);
        Level4 level4 = new Level4(game);
        Level5 level5 = new Level5(game);

        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
        levels.add(level4);
        levels.add(level5);

        currentLevel = level1;
    }


    /**
     * The pop up screen used when users press "pause button")
     */
    private void pausePopUp(){
        final Label resume = new Label("RESUME", MyGdxGame.gameSkin);
        resume.setFontScale(1);
        resume.setColor(Color.GREEN);
        resume.setWidth(Gdx.graphics.getWidth()/10);
        resume.setPosition(80,200);
        resume.setAlignment(1);


        final Label BackToMenu = new Label("Back to Menu", MyGdxGame.gameSkin);
        BackToMenu.setFontScale(1);
        BackToMenu.setColor(Color.GREEN);
        BackToMenu.setWidth(Gdx.graphics.getWidth()/10);
        BackToMenu.setPosition(80,160);
        BackToMenu.setAlignment(1);

        resume.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("resume", "Pressed");
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                pauseTouched = false;
                resume.remove();
                BackToMenu.remove();
            }
        });
        BackToMenu.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("backtomenu", "Pressed");
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                pauseTouched = false;
                game.setScreen(game.newMainMenu);
                refresh();
                hud.resetBox();
                hud.resetLevel();
                hud.resetTime();
                resume.remove();
                BackToMenu.remove();
            }
        });

        stage.addActor(resume);
        stage.addActor(BackToMenu);
    }

    void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Vector2 getScreenPos() {
        return screenPos;
    }

    public World getWorld() {
        return world;
    }

    /**
     * Resizes the window
     * @param width of the screen
     * @param height of the screen
     */
    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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

    }
}
