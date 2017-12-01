package ru.codemonkeystudio.olld40.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ContactListener implements com.badlogic.gdx.physics.box2d.ContactListener {
    public static String location = "";

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {
        location = "";
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        if (contact.getFixtureA().getBody().getUserData().toString().contains("location") || contact.getFixtureB().getBody().getUserData().toString().contains("location")) {
            contact.setEnabled(false);
            location = contact.getFixtureA().getBody().getUserData().toString().contains("location") ?
                    contact.getFixtureA().getBody().getUserData().toString() :
                    contact.getFixtureB().getBody().getUserData().toString();
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
