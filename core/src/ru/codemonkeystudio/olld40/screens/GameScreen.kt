package ru.codemonkeystudio.olld40.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ru.codemonkeystudio.olld40.CMSGame
import ru.codemonkeystudio.olld40.scenes.Scene
import ru.codemonkeystudio.olld40.scenes.Scenes
import ru.codemonkeystudio.olld40.tools.ContactListener
import ru.codemonkeystudio.olld40.tools.ControlHandler

class GameScreen(private val game: CMSGame, public val saveNum: Int) : Screen {
    lateinit var spriteBatch : SpriteBatch
    lateinit var scene : Scene

    lateinit var save: Preferences

    override fun show() {
        spriteBatch = SpriteBatch()

        save = Gdx.app.getPreferences(CMSGame.APP_NAME + "-" + "Save" + saveNum)
        scene = Scenes.getScene(save.getString("location"), this)
    }

    override fun render(delta: Float) {
        scene.update()
        scene.render()

        if (ContactListener.location != "" && ControlHandler.useKeyJustPressed()) {
            scene.dispose(true)
            scene = Scenes.getScene(save.getString("location"), this)
        }
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun hide() {
        dispose()
    }

    override fun dispose() {
        spriteBatch.dispose()
        scene.dispose()
        save.flush()
    }
}
