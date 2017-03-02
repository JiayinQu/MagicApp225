package com.mygdx.magicappgame.Shapes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.magicappgame.States.PlayScreen;

/**
 * Created by Jiayin Qu on 2017/2/27.
 */

public class Circle extends GenericShape {
    private Texture tex;
    private Sprite sprite = new Sprite (new Texture("WoodCircle.png"));

    public Circle(World world, int identifier, float sideLen, Vector2 screenPos, PlayScreen screen) {
        super(world, identifier, sideLen, screenPos, screen);
        tex = new Texture("WoodCircle.png");
        setTexture(tex);

    }

    public void render(SpriteBatch batch){
        float posX = bod.getPosition().x - getWidth()/2;
        float posY = bod.getPosition().y - getHeight()/2;
        sprite.setPosition(posX,posY);

        sprite.draw(batch);
    }
}
