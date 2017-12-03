package ru.codemonkeystudio.olld40;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import ru.codemonkeystudio.olld40.screens.MainMenuScreen;
import ru.codemonkeystudio.olld40.tools.ControlHandler;

public class CMSGame extends Game {
	public static final String APP_NAME = "NOT A VIRUS";
	public SpriteBatch batch;
	private TextureAtlas atlas;
	public Skin skin;
	public static final float SCALE = 8f;

	@Override
	public void create () {
		Gdx.app.log(APP_NAME, "Application started");
		Controllers.addListener(new ControlHandler());

		batch = new SpriteBatch();
		skin = new Skin();
		atlas = new TextureAtlas(Gdx.files.internal("icons/texture.pack"));
		skin.addRegions(atlas);

		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
		ControlHandler.update();
	}

	@Override
	public void dispose () {
		super.dispose();
		Gdx.app.log(APP_NAME, "Application closed");
	}
}
