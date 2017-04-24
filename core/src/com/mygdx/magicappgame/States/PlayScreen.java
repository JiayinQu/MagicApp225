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

    private World world;
    private Box2DDebugRenderer b2dr;

    private BalancePlatform plat;
    private Vector2 screenPos;
    private Body currentBod;
    private HashMap<Body, Sprite> bodyMap;

    private Level currentLevel;

    private Boolean somethingOnScreen;

    private Image pauseImage, upperLineImage, congratsImage, nextLevelImage;

    private boolean pauseTouched, moveAllowed, firstDraw;

    private Sprite platformSprite, pivotSprite;

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

        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -40f), true); //The game's gravity
        b2dr = new Box2DDebugRenderer();
        stage = new Stage(gamePort);

        plat = new BalancePlatform(world);
        platformSprite = drawPlatformTex();
        pivotSprite = drawPivot();
        screenPos = new Vector2(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight());
        bodyMap = new HashMap<Body, Sprite>();

        firstDraw = true;
        somethingOnScreen = false;
        pauseTouched = false;
        moveAllowed = true;

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

        final Texture upperLine = new Texture("lightening.jpg");
        Drawable upperLineDrawable = new TextureRegionDrawable(new TextureRegion(upperLine));
        upperLineImage = new Image(upperLineDrawable);
        upperLineImage.setWidth(gamePort.getWorldWidth());
        upperLineImage.setWidth(gamePort.getWorldHeight());
        upperLineImage.setPosition(gamePort.getWorldWidth()/2 - upperLineImage.getWidth()/2, 310);

    }

    /**
     * Handles all of the input for the game
     * @param dt delta time
     */
    private void handleInput(float dt){

        if(Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            hud.instructionDisappear();
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
            currentBod.applyForce(new Vector2(currentBod.getMass() * -125, 0), currentBod.getWorldCenter(), true);
        }
        // Moves the current falling shape to the right
        if(moveAllowed && Gdx.input.isKeyPressed(Input.Keys.RIGHT)&& somethingOnScreen){
            currentBod.applyForce(new Vector2(currentBod.getMass() * 125, 0), currentBod.getWorldCenter(), true);
        }

    }

    /**
     * Draws the label on the screen that tells the user that
     * they beat the level and the key to press to advance to the next level
     */
    private void nextLevelLabel() {
        final Texture congrats =  new Texture("congrats.png");
        Drawable congratsDrawable = new TextureRegionDrawable(new TextureRegion(congrats));
        congratsImage = new Image(congratsDrawable);
        congratsImage.setSize(congratsImage.getWidth()/1.7f, congratsImage.getHeight()/1.7f);
        congratsImage.setPosition((gamePort.getWorldWidth() / 2) - congratsImage.getWidth() / 2, gamePort.getWorldHeight()/2 - congratsImage.getHeight()/2 + 50);

        final Texture nextLevelButton = new Texture("nextLevel.png");
        Drawable nextLevelDrawable = new TextureRegionDrawable(new TextureRegion(nextLevelButton));
        nextLevelImage = new Image(nextLevelDrawable);
        nextLevelImage.setSize(nextLevelImage.getWidth()/1.3f, nextLevelImage.getHeight()/1.3f);
        nextLevelImage.setPosition((gamePort.getWorldWidth() / 2) - nextLevelImage.getWidth() / 2, gamePort.getWorldHeight()/2 - nextLevelImage.getHeight());

        nextLevelImage.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("next Level", "Pressed");
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                currentLevel.clearLevel();
                nextLevelImage.remove();
                congratsImage.remove();
                Hud.addLevel();
                hud.resetTime();
                hud.resetBox();
                currentLevel = currentLevel.getNextLevel();
                currentLevel.increaseLevelNum();
                moveAllowed = true;
                refresh();
            }
        });

        stage.addActor(congratsImage);
        stage.addActor(nextLevelImage);
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
        stage.addActor(pauseImage);
        stage.addActor(upperLineImage);
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
            //Gdx.input.getTextInput(listener, "Player's Name", "", "Enter your name");
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
            else if (aBody.getWorldCenter().y > 300 && aBody.getWorldCenter().y < 350 && aBody.getLinearVelocity().y > - .5){
                return true;
            }
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
        pivotSprite.setSize(12 *2f,5 *2f);
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
        currentLevel = new Level1(game);
    }



    /**
     * The pop up screen used when users press "pause button")
     */
    private void pausePopUp(){
        final Texture resume = new Texture ("resumeButton.jpg");
        Drawable resumeDrawable = new TextureRegionDrawable(new TextureRegion(resume));
        final Image resumeImage = new Image(resumeDrawable);
        resumeImage.setWidth(100);
        resumeImage.setHeight(30);
        resumeImage.setPosition(gamePort.getWorldWidth()/2 - resumeImage.getWidth()/2, gamePort.getWorldHeight()/2 - resumeImage.getHeight()/2 + 50);

        final Texture BackToMenu = new Texture("backToMenu.jpg");
        Drawable BackToMenuDrawable = new TextureRegionDrawable(new TextureRegion(BackToMenu));
        final Image BackToMenuImage = new Image(BackToMenuDrawable);
        BackToMenuImage.setWidth(170);
        BackToMenuImage.setHeight(30);
        BackToMenuImage.setPosition(gamePort.getWorldWidth()/2 - BackToMenuImage.getWidth()/2, gamePort.getWorldHeight()/2 - BackToMenuImage.getHeight()/2);


        resumeImage.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("resume", "Pressed");
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                pauseTouched = false;
                resumeImage.remove();
                BackToMenuImage.remove();
            }
        });
        BackToMenuImage.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("backtomenu", "Pressed");
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                pauseTouched = false;
                refresh();
                hud.resetBox();
                hud.resetLevel();
                hud.resetTime();
                resumeImage.remove();
                BackToMenuImage.remove();
                game.setScreen(game.newMainMenu);
            }
        });

        stage.addActor(resumeImage);
        stage.addActor(BackToMenuImage);
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

    public Level getCurrentLevel() {
        return currentLevel;
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
