package ru.codemonkeystudio.olld40.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Application extends Sprite {
    public Body body;
    public int appNum;
    public Vector2 goTo;
    public World world;
    public boolean a;

    public Application(World world, int appNum, Vector2 goTo) {
        super(new Texture("icons/antivirus1.png"));
        this.appNum = appNum;
        this.goTo = goTo;
        this.world = world;

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

        a = true;

        switch (appNum) {
            case 0:
                setTexture(new Texture("icons/antivirus1.png"));
                world.destroyBody(body);
                break;
            case 1:
                setTexture(new Texture("icons/caller1.png"));
                break;
        }
    }

    @Override
    public void setCenter(float x, float y) {
        super.setCenter(x, y);
        if (a) {
            body.setTransform(x, y, 0);
            body.setLinearVelocity(goTo.cpy().sub(body.getPosition()).setLength(100));
        }
        if (body.getPosition().dst(goTo) < 2) {
            a = false;
            body.setLinearVelocity(0, 0);
        }
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }
}
