package ru.codemonkeystudio.olld40.tools;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.SharedLibraryLoader;

public class Control {
    public static class Gamepad {
        public static float stickDeathZone;

        public static int lStickX;
        public static int lStickY;
        public static int rStickX;
        public static int rStickY;

        public static int up;
        public static int down;
        public static int left;
        public static int right;

        public static int pause;
        public static int use;

        static {
            stickDeathZone = 0.25f;
            if (SharedLibraryLoader.isWindows) {
                lStickX = 1;
                lStickY = 0;

                rStickX = 3;
                rStickY = 2;

                use = 0;
                pause = 7;
            }
            else if (SharedLibraryLoader.isLinux) {
                lStickX = 0;
                lStickY = 1;

                rStickX = 3;
                rStickY = 4;

                use = 0;
                pause = 7;
            }
        }
    }

    public static class Keyboard1 {
        public static int up;
        public static int down;
        public static int left;
        public static int right;

        public static int pause;
        public static int use;

        static {
            up = Input.Keys.W;
            down = Input.Keys.S;
            left = Input.Keys.A;
            right = Input.Keys.D;

            pause = Input.Keys.ESCAPE;
            use = Input.Keys.F;
        }
    }

    public static class Keyboard2 {
        public static int up;
        public static int down;
        public static int left;
        public static int right;

        public static int pause;
        public static int use;

        static {
            up = Input.Keys.UP;
            down = Input.Keys.DOWN;
            left = Input.Keys.LEFT;
            right = Input.Keys.RIGHT;

            pause = Input.Keys.ESCAPE;
            use = Input.Keys.ENTER;
        }
    }
}
