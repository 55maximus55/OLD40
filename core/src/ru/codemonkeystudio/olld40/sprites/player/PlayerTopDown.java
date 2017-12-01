package ru.codemonkeystudio.olld40.sprites.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import ru.codemonkeystudio.olld40.tools.ControlHandler;

public class PlayerTopDown extends Sprite {
    public Body body;

    public PlayerTopDown(Body body) {
        super(new Texture("characters/player.png"));
        this.body = body;
        setScale(0.25f, 2);
    }

    public void update() {
        body.setTransform(getPos().add(ControlHandler.playerMove().scl(3.8f)), ControlHandler.dir());
        float a = body.getAngle() * MathUtils.radiansToDegrees + 22.5f;
        if (a > 0f) {
            setRegion((int)(a / 45f) * 16, 0, 16, 48);
        }
        else {
            setRegion((7 + (int) (a / 45f)) * 16, 0, 16, 48);
        }
        setPosition(getPos().x - getWidth() / 2, getPos().y + 16);
    }

    public Vector2 getPos() {
        return body.getPosition();
    }
}
