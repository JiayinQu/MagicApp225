package com.mygdx.magicappgame.Shapes;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by ahayes on 2/22/17.
 */

public class Platform extends Sprite {

    public World world;
    public Body bod;

    public Platform(World world){
        this.world = world;
        definePlatform(104, 70, 80, 10);

    }

    public void definePlatform(float x, float y, float width, float height){
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(x, y);
        bod = world.createBody(def);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);

        bod.createFixture(shape, 1.0f);

    }

}
