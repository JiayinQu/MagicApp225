package com.mygdx.magicappgame.Shapes;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

/**
 * Created by Ansel Colby on 2/22/17.
 * This Class contains all of the parts that make up the moving platform
 */

public class BalancePlatform extends Sprite {

    private World world;
    public Body bod1; // The platform
    public Body bod2; // The Body the platform rotates around
    private Vector2[] vectorList;

    /**
     * Initializes the world, creates the triangle's points, and calls
     * the other functions to set up the platform, triangle, and joint.
     * @param world is the world passed from PlayScreen
     */
    public BalancePlatform(World world){
        this.world = world;
        vectorList = new Vector2[3];
        vectorList[0] = new Vector2(0, 10);
        vectorList[1] = new Vector2(-5, -10);
        vectorList[2] = new Vector2(5, -10);
        definePlatform(104, 120, 80, 10);
        definePivot(104, 70);
        defineJoint();
    }

    /**
     * Creates the platform
     * @param x coordinate for the platform's position on screen
     * @param y coordinate for the platform's position on screen
     * @param width of the platform
     * @param height of the platform
     * @return the Body of the platform
     */
    private Body definePlatform(float x, float y, float width, float height){
        BodyDef def1 = new BodyDef();
        def1.type = BodyDef.BodyType.DynamicBody;
        def1.position.set(x, y);
        bod1 = world.createBody(def1);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);
        FixtureDef fdef = new FixtureDef();
        fdef.friction = .2f;
        fdef.shape = shape;
        fdef.density = 1.0f;
        bod1.createFixture(fdef);

        return bod1;
    }

    /**
     * Creates the triangle that the platform pivots around
     * @param x coordinate of the pivot's position on screen
     * @param y coordinate of the pivot's position on screen
     */
    private void definePivot(float x, float y) {
        BodyDef def2 = new BodyDef();
        def2.type = BodyDef.BodyType.StaticBody;
        def2.position.set(x, y);
        bod2 = world.createBody(def2);
        //CircleShape shape = new CircleShape();
        PolygonShape shape = new PolygonShape();
        shape.set(vectorList);

        bod2.createFixture(shape, 1.0f);

    }

    /**
     * Creates the joint between the platform and pivot
     */
    private void defineJoint() {
        RevoluteJointDef rjdef = new RevoluteJointDef();
        rjdef.bodyA = bod1;
        rjdef.bodyB = bod2;
        rjdef.collideConnected = false;

        rjdef.lowerAngle = -0.05f * MathUtils.PI; // how far it can tilt to the left
        rjdef.upperAngle = 0.05f * MathUtils.PI;  // how far it can tilt to the right

        // These lines simulate the friction of the tilting
        rjdef.enableLimit = true;
        rjdef.maxMotorTorque = 1.0f;
        rjdef.motorSpeed = 0f;
        rjdef.enableMotor = true;

        world.createJoint(rjdef);

    }

}