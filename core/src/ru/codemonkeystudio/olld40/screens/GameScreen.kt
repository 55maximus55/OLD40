package ru.codemonkeystudio.olld40.screens

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import ru.codemonkeystudio.olld40.CMSGame
import ru.codemonkeystudio.olld40.objects.Grid
import ru.codemonkeystudio.olld40.tools.ContactListener
import ru.codemonkeystudio.olld40.tools.ControlHandler

class GameScreen(game: CMSGame) : Screen {
    lateinit var batch : SpriteBatch
    lateinit var camera : OrthographicCamera

    lateinit var world : World
    lateinit var debugRenderer : Box2DDebugRenderer

    lateinit var shape : CircleShape
    lateinit var bDef : BodyDef
    lateinit var fDef : FixtureDef

    lateinit var grid : Grid

    override fun show() {
        batch = SpriteBatch()

        camera = OrthographicCamera()
        camera.setToOrtho(false)

        world = World(Vector2(0f, 0f), false)
        world.setContactListener(ContactListener())

        debugRenderer = Box2DDebugRenderer()


        bDef = BodyDef()
        bDef.type = BodyDef.BodyType.KinematicBody
        bDef.position.set(0f, 0f)

        shape = CircleShape()
        shape.radius = 4f
        fDef = FixtureDef()
        fDef.shape = shape

        grid = Grid()
    }

    override fun render(delta: Float) {
        camera.position.x = 0f
        camera.position.y = 0f
        camera.update()

        world.step(delta, 10, 10)

        debugRenderer.render(world, camera.combined)

        if (ControlHandler.useKeyJustPressed()) {
            val body = world.createBody(bDef)
            body.createFixture(fDef)
            body.userData = "bullet"
            body.linearVelocity = Vector2(100f, 0f).setAngleRad(ControlHandler.dir())
        }


    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun hide() {
    }

    override fun dispose() {
    }
}
