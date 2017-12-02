package ru.codemonkeystudio.olld40.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import ru.codemonkeystudio.olld40.CMSGame

fun main(arg: Array<String>) {
    val config = LwjglApplicationConfiguration()

    config.fullscreen = false
    config.width = 800
    config.height = 600

    LwjglApplication(CMSGame(), config)
}