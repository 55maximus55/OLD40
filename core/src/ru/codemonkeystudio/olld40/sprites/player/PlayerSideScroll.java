package ru.codemonkeystudio.olld40.sprites.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import ru.codemonkeystudio.olld40.tools.ControlHandler;

public class PlayerSideScroll extends Sprite {
    public Body body;

    public PlayerSideScroll(Body body) {
        super(new Texture("characters/player.png"));
        this.body = body;
        setScale(0.25f, 2);
    }

    public void update() {
        body.setTransform(getPos().add(ControlHandler.playerMove().scl(1.5f, 0)), 0);
    }

    public Vector2 getPos() {
        return body.getPosition();
    }
}
