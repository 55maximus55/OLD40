package ru.codemonkeystudio.olld40.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

public class Bullet extends Sprite {
    public Body body;

    public Bullet(Texture texture, Body body) {
        super(texture);
        this.body = body;
    }
}
