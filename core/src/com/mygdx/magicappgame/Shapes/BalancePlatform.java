package com.mygdx.magicappgame.Shapes;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

/**
 * Created by ahayes on 2/22/17.
 */

public class BalancePlatform extends Sprite {

    public World world;
    public Body bod1;
    public Body bod2;
    private Vector2[] vectorList;
    private BodyDef def1;
    private BodyDef def2;

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

    private Body definePlatform(float x, float y, float width, float height){
        def1 = new BodyDef();
        def1.type = BodyDef.BodyType.DynamicBody;
        def1.position.set(x, y);
        bod1 = world.createBody(def1);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);

        bod1.createFixture(shape, 1f);

        return bod1;
    }

    private void definePivot(float x, float y) {
        def2 = new BodyDef();
        def2.type = BodyDef.BodyType.StaticBody;
        def2.position.set(x, y);
        bod2 = world.createBody(def2);
        //CircleShape shape = new CircleShape();
        PolygonShape shape = new PolygonShape();
        shape.set(vectorList);

        bod2.createFixture(shape, 1.0f);

    }

    private void defineJoint() {
        RevoluteJointDef rjdef = new RevoluteJointDef();
        rjdef.bodyA = bod1;
        rjdef.bodyB = bod2;
        rjdef.collideConnected = false;

        rjdef.lowerAngle = -0.05f * MathUtils.PI; // how far it can tilt to the left
        rjdef.upperAngle = 0.05f * MathUtils.PI;  // how far it can tilt to the right

        rjdef.enableLimit = true;
        rjdef.maxMotorTorque = 1.0f;
        rjdef.motorSpeed = 0f;
        rjdef.enableMotor = true;

        world.createJoint(rjdef);

    }

}