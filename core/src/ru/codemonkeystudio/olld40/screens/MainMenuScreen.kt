package ru.codemonkeystudio.olld40.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.FitViewport
import ru.codemonkeystudio.olld40.CMSGame
import ru.codemonkeystudio.olld40.tools.ControlHandler
import java.util.*

class MainMenuScreen(private val game: CMSGame) : Screen {

    private lateinit var stage: Stage
    private lateinit var camera: OrthographicCamera

    private lateinit var buttons: ArrayList<Button>

    private var cursor = -1
    private var x = 0
    private var y = 0

    override fun show() {
        camera = OrthographicCamera()
        stage = Stage(FitViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat(), camera))
        Gdx.input.inputProcessor = stage

        val table = Table().apply {
            center()
            setFillParent(true)
        }

        val textButtonStyle = TextButton.TextButtonStyle().apply {
            font = BitmapFont()
            pressedOffsetX = 7f
            checkedOffsetX = 5f
        }

        val startGameButton = TextButton("Start game", textButtonStyle).apply {
            addListener(object : ClickListener() {
                override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                    super.touchUp(event, x, y, pointer, button)
                    game.screen = GameScreen(game)
                }
            })
        }

        buttons = ArrayList()
        buttons.apply {
            add(startGameButton)
        }

        table.apply {
            add(startGameButton).row()
        }

        stage.addActor(table)
    }

    override fun render(delta: Float) {
        stage.isDebugAll = true
        stage.act()
        stage.draw()

        if (ControlHandler.upKeyJustPressed()) {
            if (cursor != -1)
                buttons[cursor].isChecked = false
            cursor--
            if (cursor < 0)
                cursor = 2
        }
        if (ControlHandler.downKeyJustPressed()) {
            if (cursor != -1)
                buttons[cursor].isChecked = false
            cursor++
            if (cursor >= 3)
                cursor = 0
        }

        if (x != Gdx.input.x || y != Gdx.input.y) {
            if (cursor != -1) {
                buttons[cursor].isChecked = false
                cursor = -1
            }
        }
        x = Gdx.input.x
        y = Gdx.input.y

        if (cursor != -1)
            buttons[cursor].isChecked = true

        if (cursor != -1 && ControlHandler.useKeyJustPressed()) {
            (buttons[cursor].listeners[1] as ClickListener).touchUp(null, 0f, 0f, 0, 0)
        }
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun hide() {
        dispose()
    }

    override fun dispose() {
        stage.dispose()
    }
}
