package ru.codemonkeystudio.olld40.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ContactListener implements com.badlogic.gdx.physics.box2d.ContactListener {
    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        if (contact.getFixtureA().getBody().getUserData().equals("App") && contact.getFixtureB().getBody().getUserData().equals("App"))
            contact.setEnabled(false);
        if (contact.getFixtureA().getBody().getUserData().equals("AppI") || contact.getFixtureB().getBody().getUserData().equals("AppI"))
            contact.setEnabled(false);

        if ((contact.getFixtureA().getBody().getUserData().equals("App") || contact.getFixtureB().getBody().getUserData().equals("App")) && (contact.getFixtureA().getBody().getUserData().equals("bullet") || contact.getFixtureB().getBody().getUserData().equals("bullet"))) {
            if (contact.getFixtureA().getBody().getUserData().equals("App"))
                contact.getFixtureA().getBody().setUserData("AppD");
            else
                contact.getFixtureB().getBody().setUserData("AppD");
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
