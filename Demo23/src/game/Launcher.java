package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Launcher
{
    public static void main (String[] args)
    {
        Game myGame = new Demo(); 
        LwjglApplication launcher = new LwjglApplication( myGame, "Castel Survive", Parametros.getAnchoPantalla(), Parametros.getAltoPantalla());
    }
}