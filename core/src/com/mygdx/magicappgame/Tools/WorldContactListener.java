package com.mygdx.magicappgame.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.magicappgame.Shapes.BalancePlatform;
import com.mygdx.magicappgame.levels.Level;

/**
 * Created by Jiayin Qu on 2017/3/20.
 *
 */

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA == null || fixB == null) return;
        if(fixA.getUserData() == null || fixB.getUserData() == null) return;

        if(isContacted(fixA, fixB)){
            if(fixA.getUserData() instanceof Level){
                Level fA = (Level) fixA.getUserData();
                fA.hit();
            }
            else{
                Level fB = (Level) fixB.getUserData();
                fB.hit();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private boolean isContacted(Fixture A, Fixture B) {
        return (A.getUserData() instanceof Level || A.getUserData() instanceof BalancePlatform && B.getUserData() instanceof Level || B.getUserData() instanceof BalancePlatform);
    }
}
