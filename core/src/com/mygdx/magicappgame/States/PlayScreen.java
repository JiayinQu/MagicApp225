package com.mygdx.magicappgame.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.BatchTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.magicappgame.MyGdxGame;
import com.mygdx.magicappgame.Scenes.Hud;
import com.mygdx.magicappgame.Shapes.BalancePlatform;
import com.mygdx.magicappgame.levels.Level1;


import java.util.ArrayList;

/**
 * Created by Jiayin Qu on 2017/2/11.
 */
public class PlayScreen implements Screen{
    private MyGdxGame game;
    public OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private ShapeRenderer shapeRenderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    private Body plat;
    private Vector2 screenPos;
    private ArrayList<Body> bodyList;
    private ArrayList<Sprite> squareTexList;
    private Stage stage;
    private Level1 level1;

    public Boolean levelComplete;

    public PlayScreen(MyGdxGame game) {
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, gamecam);
        hud = new Hud(game.batch);

        maploader = new TmxMapLoader();
        //map = maploader.load("SkyBackG.tmx");
        //renderer = new OrthogonalTiledMapRenderer(map);
        shapeRenderer = new ShapeRenderer();
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -40f), true);
        b2dr = new Box2DDebugRenderer();

        BalancePlatform balancePlatform = new BalancePlatform(world);
        plat = balancePlatform.bod1;

        screenPos = new Vector2(104, 300);
        bodyList = new ArrayList<Body>();
        squareTexList = new ArrayList<Sprite>();

        level1 = new Level1(this);
        levelComplete = false;

    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            screenPos.add(0, 1.75f);
            gamecam.position.y += 100 * dt;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            gamecam.position.y -= 100 * dt;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)){
            //squareTexList.add(drawSquareTex());
            if (!levelComplete) {
                bodyList.add(level1.getNextBod());
            } else {
                endGame();
            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)){
            //bodyList.add(drawCircle(screenPos, 30);
        }

        // Moves the current falling shape to the left
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            Body moveBod = bodyList.get(bodyList.size()-1);
            moveBod.applyForce(new Vector2(-300000f, 0), moveBod.getWorldCenter(), true);
        }
        // Moves the current falling shape to the right
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            Body moveBod = bodyList.get(bodyList.size()-1);
            moveBod.applyForce(new Vector2(300000f, 0), moveBod.getWorldCenter(), true);
        }
    }

    private void update(float dt){
        handleInput(dt);
        world.step(1/60f, 6, 2);

        checkEndGame();

        gamecam.update();
        //renderer.setView(gamecam);
        b2dr.render(world, gamecam.combined);

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //renderer.render();
        b2dr.render(world,gamecam.combined);
        if(bodyList.size()!=0){
            draw(game.batch);
        }

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    private void checkEndGame() {
        for (Body aBodyList : bodyList) {
            if (aBodyList.getWorldCenter().y < plat.getWorldCenter().y - 60) {
                endGame();

                     /*if(exitButton.isPressed())
                        game.setScreen(new MenuState(game));*/


                //Gdx.app.exit();

            }

        }
    }

    private void endGame() {

        gamePort = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(gamePort, game.batch);

        Label gameOverLabel = new Label("Game Over", MyGdxGame.gameSkin);
        gameOverLabel.setFontScale(1.5f);

        gameOverLabel.setColor(Color.RED);

        gameOverLabel.setWidth(Gdx.graphics.getWidth() / 16);
        gameOverLabel.setPosition(35, 250);

        TextButton exitButton = new TextButton("Exit", MyGdxGame.gameSkin);
        exitButton.getLabel().setFontScale(1f);
        exitButton.setColor(Color.RED);

        exitButton.setWidth(Gdx.graphics.getWidth() / 16);
        exitButton.setPosition(70, 150);

        hud.stage.addActor(gameOverLabel);
        hud.stage.addActor(exitButton);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY))
            game.setScreen(new MenuState(game));
            //bodyList.clear(); //We can remove all the objects from the screen
            //Gdx.app.exit(); // TODO: Replace this with a GAMEOVER and then remove all objects
        }



    public Body drawSquare(float width, float height) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(screenPos);

        Body bod = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 1f;
        bod.createFixture(fdef);

        /*
        if(squareTexList.size()!=0){
            squareTexList.get(squareTexList.size()-1).setBounds(0,0,drawSquareTex().getWidth(),drawSquareTex().getHeight());
            squareTexList.get(squareTexList.size()-1).setSize(width * 2,height * 2);
            squareTexList.get(squareTexList.size()-1).setOrigin(drawSquareTex().getWidth()/2, drawSquareTex().getHeight()/2);
        }
        */
        shape.dispose();

        return bod;
    }

    public Sprite drawSquareTex(){
        Texture squareTex = new Texture("StoneSquare.png");
        Sprite squareSprite = new Sprite(squareTex);

        return squareSprite;
    }

    public void draw(Batch batch){
        game.batch.begin();
        for(int i = 0; i< squareTexList.size();i++){
            squareTexList.get(i).setPosition(bodyList.get(i).getPosition().x - 30,bodyList.get(i).getPosition().y - 30);
            squareTexList.get(i).setRotation(bodyList.get(i).getAngle() * MathUtils.radiansToDegrees);
            squareTexList.get(i).draw(batch);
        }
        game.batch.end();

    }


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
