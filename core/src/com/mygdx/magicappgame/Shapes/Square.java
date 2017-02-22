package com.mygdx.magicappgame.Shapes;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by ahayes on 2/22/17.
 */

public class Square extends Sprite{

    public World world;
    public Body bod;


    public Square(World world){
        this.world = world;
        //defineSquare(104, 300, 10, 10);

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

}
