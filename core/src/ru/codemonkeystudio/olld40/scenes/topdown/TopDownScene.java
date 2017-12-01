package ru.codemonkeystudio.olld40.scenes.topdown;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import ru.codemonkeystudio.olld40.scenes.Scene;
import ru.codemonkeystudio.olld40.screens.GameScreen;
import ru.codemonkeystudio.olld40.sprites.player.PlayerTopDown;
import ru.codemonkeystudio.olld40.tools.ContactListener;

public abstract class TopDownScene extends Scene {
    public World world;
    public PlayerTopDown player;

    public OrthogonalTiledMapRenderer mapRenderer;
    public Box2DDebugRenderer debugRenderer;

    public float playerRadius;

    public TopDownScene(GameScreen screen) {
        super(screen);
        world = new World(new Vector2(0, 0), false);
        world.setContactListener(new ContactListener());
        debugRenderer = new Box2DDebugRenderer();
    }

    @Override
    public void loadMap() {
        super.loadMap();
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        createWalls();
        createLocations();
        createPlayer();
    }

    @Override
    public void update() {
        camera.position.x = player.getPos().x;
        camera.position.y = player.getPos().y;
        camera.update();

        player.update();

        mapRenderer.setView(camera);
        screen.spriteBatch.setProjectionMatrix(camera.combined);

        world.step(Gdx.graphics.getDeltaTime(), 10, 10);
    }

    private void createWalls() {
        BodyDef bDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fDef = new FixtureDef();
        Body body;

        for (Object object : map.getLayers().get("walls").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bDef.type = BodyDef.BodyType.StaticBody;
            bDef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);

            body = world.createBody(bDef);

            shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
            fDef.shape = shape;
            body.createFixture(fDef);
            body.setUserData("wall");
        }
    }

    private void createLocations() {
        BodyDef bDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fDef = new FixtureDef();
        Body body;

        for (Object object : map.getLayers().get("location").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bDef.type = BodyDef.BodyType.StaticBody;
            bDef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);

            body = world.createBody(bDef);

            shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
            fDef.shape = shape;
            body.createFixture(fDef);
            body.setUserData(((RectangleMapObject)object).getName());
        }
    }

    private void createPlayer() {
        BodyDef bDef = new BodyDef();
        CircleShape shape = new CircleShape();
        FixtureDef fDef = new FixtureDef();
        Body body;

        Rectangle rect = ((RectangleMapObject) map.getLayers().get("player").getObjects().get(0)).getRectangle();

        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(screen.save.getFloat("Scene-" + name + "-playerX", rect.getX() + rect.getWidth() / 2),
                screen.save.getFloat("Scene-" + name + "-playerY", rect.getY() + rect.getHeight() / 2));

        body = world.createBody(bDef);

        shape.setRadius(playerRadius);
        fDef.shape = shape;
        body.createFixture(fDef);
        body.setUserData("player");

        player = new PlayerTopDown(body);
    }

    @Override
    public void dispose() {
        super.dispose();
        screen.save.putFloat("Scene-" + name + "-playerX", player.getPos().x);
        screen.save.putFloat("Scene-" + name + "-playerY", player.getPos().y);
        screen.save.flush();
    }

    @Override
    public void dispose(boolean location) {
        dispose();
    }
}
