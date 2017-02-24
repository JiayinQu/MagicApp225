package com.mygdx.magicappgame.Shapes;

import com.badlogic.gdx.graphics.g2d.Sprite;
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

/**
 * Created by ahayes on 2/22/17.
 */

public class GenericShape extends Sprite {
    public World world;
    public Body bod;
    public Fixture fixture;


    public GenericShape(World world, int identifier, float sideLen, Vector2 screenPos){
        this.world = world;
        if(identifier == 0){
            defineSquare(screenPos, sideLen, sideLen);
        }
        else if (identifier == 1){
            defineCircle(screenPos, sideLen);

        }
        else if (identifier == 2){

        }
        else if (identifier == 3){

        }


    }
    public void defineSquare(Vector2 screenPos, float width, float height){
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(screenPos);

        bod = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 1f;
        fixture = bod.createFixture(fdef);

    }

    public void defineCircle(Vector2 screenpos, float radius){
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
