package com.mygdx.magicappgame.Shapes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.magicappgame.States.PlayScreen;

/**
 * Created by Jiayin Qu on 2017/2/27.
 */

public class Square extends Sprite {

    private World world;
    private Body bod;


    private Texture tex;
    private Sprite sprite;

    public Square(World world) {
        this.world = world;
        sprite = new Sprite (new Texture("StoneSquare.png"));
    }

    public Body drawSquare(Vector2 screenPos, float width, float height) {
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

    public Body getBod(){
        return bod;
    }

    /*
    public void render(SpriteBatch batch){
        float posX = bod.getPosition().x - getWidth()/2;
        float posY = bod.getPosition().y - getHeight()/2;
        sprite.setPosition(posX,posY);

        sprite.draw(batch);
    }
    */


}
