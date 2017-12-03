package ru.codemonkeystudio.olld40.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class ControlHandler implements ControllerListener {
    public static Control ctrl;

    private static Vector2 lStick = new Vector2();
    private static Vector2 rStick = new Vector2();

    private static int x = Gdx.input.getX();
    private static int y = Gdx.input.getY();
    private static float angle = 0f;

    private static boolean gamepad;

    private static boolean gamepadUseKeyJustPressed = false;
    private static boolean gamepadPauseKeyJustPressed = false;

    private static boolean gamepadUpKeyJustPressed = false;
    private static boolean gamepadDownKeyJustPressed = false;
    private static boolean gamepadLeftKeyJustPressed = false;
    private static boolean gamepadRightKeyJustPressed = false;

    public ControlHandler() {
        ctrl = new Control();
    }

    public static void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY) || x != Gdx.input.getX() || y != Gdx.input.getY())
            gamepad = false;
        x = Gdx.input.getX();
        y = Gdx.input.getY();

        gamepadUseKeyJustPressed = false;
        gamepadPauseKeyJustPressed = false;

        gamepadUpKeyJustPressed = false;
        gamepadDownKeyJustPressed = false;
        gamepadLeftKeyJustPressed = false;
        gamepadRightKeyJustPressed = false;

        dir();
    }

    @Override
    public void connected(Controller controller) {

    }

    @Override
    public void disconnected(Controller controller) {

    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        gamepad = true;
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        gamepad = true;
        if (buttonCode == Control.Gamepad.use)
            gamepadUseKeyJustPressed = true;
        if (buttonCode == Control.Gamepad.pause)
            gamepadPauseKeyJustPressed = true;
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        gamepad = true;
        if (axisCode == Control.Gamepad.lStickX)
            lStick.x = value;
        if (axisCode == Control.Gamepad.lStickY)
            lStick.y = -value;

        if (axisCode == Control.Gamepad.rStickX)
            rStick.x = value;
        if (axisCode == Control.Gamepad.rStickY)
            rStick.y = -value;
        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        gamepad = true;
        if (value == PovDirection.north)
            gamepadUpKeyJustPressed = true;
        if (value == PovDirection.south)
            gamepadDownKeyJustPressed = true;
        if (value == PovDirection.west)
            gamepadLeftKeyJustPressed = true;
        if (value == PovDirection.east)
            gamepadRightKeyJustPressed = true;
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return false;
    }

    public static float dir() {
        if (gamepad) {
            if (lStick.len() > Control.Gamepad.stickDeathZone)
                angle = lStick.angleRad();
        }
        else {
            angle = new Vector2(Gdx.input.getX() - Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 - Gdx.input.getY()).angleRad();
        }
        return angle;
    }

    public static Vector2 playerMove() {
        Vector2 c = new Vector2();
        if (gamepad) {
            if (lStick.len() > Control.Gamepad.stickDeathZone)
                c.add(lStick);
        }
        else {
            if (Gdx.input.isKeyPressed(Control.Keyboard1.up) || Gdx.input.isKeyPressed(Control.Keyboard2.up))
                c.add(0, 1);
            if (Gdx.input.isKeyPressed(Control.Keyboard1.down) || Gdx.input.isKeyPressed(Control.Keyboard2.down))
                c.add(0, -1);
            if (Gdx.input.isKeyPressed(Control.Keyboard1.left) || Gdx.input.isKeyPressed(Control.Keyboard2.left))
                c.add(-1, 0);
            if (Gdx.input.isKeyPressed(Control.Keyboard1.right) || Gdx.input.isKeyPressed(Control.Keyboard2.right))
                c.add(1, 0);
        }
        c.clamp(0, 1);
        return c;
    }



    public static boolean useKeyJustPressed() {
        if (gamepad) {
            if (gamepadUseKeyJustPressed) {
                gamepadUseKeyJustPressed = false;
                return true;
            }
            else
                return false;
        }
        else {
            return Gdx.input.isKeyJustPressed(Control.Keyboard1.use) || Gdx.input.isKeyJustPressed(Control.Keyboard2.use)|| Gdx.input.justTouched();
        }
    }

    public static boolean pauseKeyJustPressed() {
        if (gamepad) {
            if (gamepadPauseKeyJustPressed) {
                gamepadPauseKeyJustPressed = false;
                return true;
            }
            else
                return false;
        }
        else {
            return Gdx.input.isKeyJustPressed(Control.Keyboard1.pause) || Gdx.input.isKeyJustPressed(Control.Keyboard2.pause);
        }
    }



    public static boolean upKeyJustPressed() {
        if (gamepad) {
            if (gamepadUpKeyJustPressed) {
                gamepadUpKeyJustPressed = false;
                return true;
            }
            else
                return false;
        }
        else {
            return Gdx.input.isKeyJustPressed(Control.Keyboard1.up) || Gdx.input.isKeyJustPressed(Control.Keyboard2.up);
        }
    }

    public static boolean downKeyJustPressed() {
        if (gamepad) {
            if (gamepadDownKeyJustPressed) {
                gamepadDownKeyJustPressed = false;
                return true;
            }
            else
                return false;
        }
        else {
            return Gdx.input.isKeyJustPressed(Control.Keyboard1.down) || Gdx.input.isKeyJustPressed(Control.Keyboard2.down);
        }
    }

    public static boolean leftKeyJustPressed() {
        if (gamepad) {
            if (gamepadLeftKeyJustPressed) {
                gamepadLeftKeyJustPressed = false;
                return true;
            }
            else
                return false;
        }
        else {
            return Gdx.input.isKeyJustPressed(Control.Keyboard1.left) || Gdx.input.isKeyJustPressed(Control.Keyboard2.left);
        }
    }

    public static boolean rightKeyJustPressed() {
        if (gamepad) {
            if (gamepadRightKeyJustPressed) {
                gamepadRightKeyJustPressed = false;
                return true;
            }
            else
                return false;
        }
        else {
            return Gdx.input.isKeyJustPressed(Control.Keyboard1.left) || Gdx.input.isKeyJustPressed(Control.Keyboard2.left);
        }
    }
}
