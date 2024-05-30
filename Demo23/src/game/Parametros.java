package game;



public class Parametros {

 
 private static int anchoPantalla=800;
 private static int altoPantalla=600;
 
 public static boolean debug=false;

 public static float musicVolume=1;
 public static float soundVolume=1;
 
 
 public static float zoom=0.50f;
 public static int nivel=0;
 public static int gravedad= -300;
 public static float jugadorx=0;
 public static float jugadory=0;
 public static int vida=6;
 public static int mana=4;
 public static int puntuacion = 0;
 public static int aumentarVida= 0;
 public static int aumentarMana= 0;
 public static int jefe = 5;
 public static int matarSegundoJefe = 0;
 public static int ataque = 1;
 public static int hechizo = 0;
 
 public static void llenarVida() {
	 if(aumentarVida == 0) {
		 vida = 6;
	 }
	 if(aumentarVida == 1) {
		 vida = 8;
	 }
	 if(aumentarVida >= 2) {
		 vida = 10;
	 }
 }
 
 public static void llenarMana() {
	 if(aumentarMana == 0) {
		 mana = 4;
	 }
	 if(aumentarMana == 1) {
		 mana = 6;
	 }
	 if(aumentarMana >= 2) {
		 mana = 8;
	 }
 }


public static int getAnchoPantalla() {
	return anchoPantalla;
}

public static void setAnchoPantalla(int anchoPantalla) {
	Parametros.anchoPantalla = anchoPantalla;
}

public static int getAltoPantalla() {
	return altoPantalla;
}

public static void setAltoPantalla(int altoPantalla) {
	Parametros.altoPantalla = altoPantalla;
}




 
}
