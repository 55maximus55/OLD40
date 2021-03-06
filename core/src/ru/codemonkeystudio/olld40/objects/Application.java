package ru.codemonkeystudio.olld40.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import ru.codemonkeystudio.olld40.CMSGame;

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

        shape.setRadius(16 / CMSGame.SCALE);
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
            case 2:
                setTexture(new Texture("icons/chrome1.png"));
                break;
            case 3:
                setTexture(new Texture("icons/cleaner1.png"));
                break;
            case 4:
                setTexture(new Texture("icons/gallery1.png"));
                break;
            case 5:
                setTexture(new Texture("icons/google1.png"));
                break;
            case 6:
                setTexture(new Texture("icons/sms1.png"));
                break;
            case 7:
                setTexture(new Texture("icons/trash1.png"));
                break;
            case 8:
                setTexture(new Texture("icons/virus1.png"));
                break;
        }
    }

    @Override
    public void setCenter(float x, float y) {
        super.setCenter(x * CMSGame.SCALE, y * CMSGame.SCALE);
        if (a) {
            body.setTransform(x, y, 0);
            body.setLinearVelocity(goTo.cpy().sub(body.getPosition()).setLength(15));

            if (body.getPosition().dst(goTo) < 0.1f) {
                a = false;
                body.setLinearVelocity(0, 0);
                body.setUserData("AppI");
                if (appNum == 3 || appNum == 7 || appNum == 8)
                    Gdx.audio.newSound(Gdx.files.internal("sounds/virus.wav")).play(0.25f);
                else
                    Gdx.audio.newSound(Gdx.files.internal("sounds/pip.wav")).play(0.25f);
            }
        }
        if (body.getUserData().equals("AppD")) {
            body.setLinearVelocity(0, 100);
        }
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }
}
