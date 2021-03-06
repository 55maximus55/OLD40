package ru.codemonkeystudio.olld40.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align.center
import com.badlogic.gdx.utils.viewport.FitViewport
import ru.codemonkeystudio.olld40.CMSGame
import ru.codemonkeystudio.olld40.tools.ControlHandler
import java.util.*

class MainMenuScreen(private val game: CMSGame) : Screen {

    private lateinit var stage: Stage
    private lateinit var camera: OrthographicCamera
    private lateinit var font1: BitmapFont


    private lateinit var buttons: ArrayList<Button>

    private lateinit var sound: Sound

    lateinit var background : TextureRegionDrawable

    private var cursor = -1
    private var x = 0
    private var y = 0

    override fun show() {
        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/select.wav"))

        background = TextureRegionDrawable(TextureRegion(Texture("icons/back_menu.png")))

        font1 = BitmapFont(Gdx.files.internal("font/roboto.fnt"), Gdx.files.internal("font/roboto.png"), false)

        camera = OrthographicCamera()
        stage = Stage(FitViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat(), camera))
        Gdx.input.inputProcessor = stage

        val table = Table().apply {
            center()
            setFillParent(true)
        }

        val table1 = Table().apply {
            center()
            setFillParent(true)
        }

        val textButtonStyle = TextButton.TextButtonStyle().apply {
            this.font = font1
            up = game.skin.getDrawable("play_butt")
            down = game.skin.getDrawable("play_butt")
            pressedOffsetX = 1f
            pressedOffsetY = -1f
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
        var back = com.badlogic.gdx.scenes.scene2d.ui.Image(background)
//        back.setPosition()

        table.apply {
            add(startGameButton)
        }
        table1.apply {
            add(back)
        }


        stage.addActor(table1)
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
