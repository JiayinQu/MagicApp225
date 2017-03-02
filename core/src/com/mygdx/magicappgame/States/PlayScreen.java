package com.mygdx.magicappgame.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.magicappgame.MyGdxGame;
import com.mygdx.magicappgame.Scenes.Hud;
import com.mygdx.magicappgame.Shapes.BalancePlatform;

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

        world = new World(new Vector2(0, -50f), true);
        b2dr = new Box2DDebugRenderer();

        BalancePlatform balancePlatform = new BalancePlatform(world);
        plat = balancePlatform.bod1;

        screenPos = new Vector2(104, 300);
        bodyList = new ArrayList<Body>();


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
            bodyList.add(drawSquare(screenPos, 30));
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

    public void update(float dt){
        handleInput(dt);
        world.step(1/60f, 6, 2);

        endGame();

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

        game.batch.begin();
        //currentShape.render(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    private void endGame() {
        for (Body aBodyList : bodyList) {
            if (aBodyList.getWorldCenter().y < plat.getWorldCenter().y - 60) {
                Gdx.app.exit(); // TODO: Replace this with a GAMEOVER and then remove all objects
            }
        }
    }

    public Body drawSquare(Vector2 screenPos, float sideLen) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(screenPos);

        Body bod = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sideLen, sideLen);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 1f;
        bod.createFixture(fdef);

        return bod;
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
