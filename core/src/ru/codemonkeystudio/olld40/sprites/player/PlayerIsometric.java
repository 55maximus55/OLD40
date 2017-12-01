package ru.codemonkeystudio.olld40.sprites.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import ru.codemonkeystudio.olld40.tools.ControlHandler;

public class PlayerIsometric extends Sprite {
    public Body body;

    public PlayerIsometric(Body body) {
        super(new Texture("characters/player.png"));
        this.body = body;
        setScale(0.125f, 1);
    }

    public void update() {
        body.setTransform(getPos().add(ControlHandler.playerMove().rotate(-45).scl(2)), ControlHandler.dir());
        float a = body.getAngle() * MathUtils.radiansToDegrees + 22.5f;
        if (a > 0f) {
            setRegion((int)(a / 45f) * 16, 0, 16, 48);
        }
        else {
            setRegion((7 + (int)(a / 45f)) * 16, 0, 16, 48);
        }
        float cx = getPos().x - 8;
        float cy = getPos().y / 2 + 24;

        float bx = cx - 2 * cy;
        float by = cy + cx / 2;

        setPosition(bx - 4, by - 4);
    }

    public Vector2 getPos() {
        return body.getPosition();
    }
}
