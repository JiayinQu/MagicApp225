package com.mygdx.magicappgame.Shapes;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by ahayes on 2/22/17.
 */

public class GenericShape extends Sprite {
    public World world;
    public Body bod;

    public GenericShape(World world, int identifier, float sideLen){
        this.world = world;
        if(identifier == 0){
            defineSquare(104, 300, sideLen, sideLen);
        }
        else if (identifier == 1){
            defineCircle(104, 300, sideLen);

        }
        else if (identifier == 2){

        }
        else if (identifier == 3){

        }


    }
    public void defineSquare(float x, float y, float width, float height){
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(x, y);
        bod = world.createBody(def);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);

        bod.createFixture(shape, 1.0f);

    }

    public void defineCircle(float x, float y, float radius){
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(x, y);
        bod = world.createBody(def);
        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

        bod.createFixture(shape, 1.0f);

    }

}
