package com.mygdx.magicappgame.Shapes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.magicappgame.MyGdxGame;
import com.mygdx.magicappgame.States.PlayScreen;

/**
 * Created by ahayes on 2/22/17.
 */

public class GenericShape_NOTUSED extends Sprite {
    private World world;
    public Body bod;
    public Fixture fixture;
    private Texture tex;
    private Sprite sprite = new Sprite (new Texture("StoneSquare.png"));


    public GenericShape_NOTUSED(World world, int identifier, float sideLen, Vector2 screenPos, PlayScreen screen){
        this.world = world;

        if(identifier == 0){
            drawSquare(screenPos, sideLen, sideLen);
        }
        else if (identifier == 1){
            drawCircle(screenPos, sideLen);

        }
        else if (identifier == 2){

        }
        else if (identifier == 3){

        }
    }

    public void render(SpriteBatch batch){
        float posX = bod.getPosition().x - getWidth()/2;
        float posY = bod.getPosition().y - getHeight()/2;
        //sprite.setPosition(posX,posY);

        //sprite.draw(batch);
    }

    public Body drawSquare(Vector2 screenPos, float width, float height){
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(screenPos);

        bod = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 1f;
        bod.createFixture(fdef);

        return bod;
    }

    public void drawCircle(Vector2 screenpos, float radius){
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(screenpos);

        bod = world.createBody(def);

        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 1f;
        fixture = bod.createFixture(fdef);


    }

}
