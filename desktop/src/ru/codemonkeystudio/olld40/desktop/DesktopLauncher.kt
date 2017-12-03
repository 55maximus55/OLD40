package ru.codemonkeystudio.olld40.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import ru.codemonkeystudio.olld40.CMSGame

fun main(arg: Array<String>) {
    val config = LwjglApplicationConfiguration()

    config.fullscreen = true
    config.width = 1366
    config.height = 768

    LwjglApplication(CMSGame(), config)
}