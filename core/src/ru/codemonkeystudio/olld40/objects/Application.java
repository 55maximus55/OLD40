package ru.codemonkeystudio.olld40.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Application extends Sprite {
    public Body body;
    public int appNum;
    public Vector2 goTo;

    public Application(World world, int appNum, Vector2 goTo) {
        super(new Texture("icons/antivirus.png"));
        this.appNum = appNum;
        this.goTo = goTo;

        BodyDef bDef = new BodyDef();
        CircleShape shape = new CircleShape();
        FixtureDef fDef = new FixtureDef();

        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(0, 0);

        body = world.createBody(bDef);

        shape.setRadius(16);
        fDef.shape = shape;
        body.createFixture(fDef);
        body.setUserData("App");

        switch (appNum) {
            case 0:
                setTexture(new Texture("icons/antivirus.png"));
                world.destroyBody(body);
                break;
            case 1:
                setTexture(new Texture("icons/caller.png"));
                break;
        }
        setScale(32 / 1088f);
    }

    @Override
    public void setCenter(float x, float y) {
        body.setTransform(x, y, 0);
        body.setLinearVelocity(goTo.sub(body.getPosition()).setLength(100));
        super.setCenter(x, y);
    }
}
