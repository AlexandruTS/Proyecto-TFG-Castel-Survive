package screens;
import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;

import elements.Arquero;
import elements.Caballero;
import elements.CaballeroGrande;
import elements.Corazon;
import elements.CuartoJefe;
import elements.Element;
import elements.End;
import elements.Enemy;
import elements.Esqueleto;
import elements.Fin;
import elements.Gargola;
import elements.Hechicero;
import elements.Herrero;
import elements.Mago;
import elements.Mana;
import elements.Npc;
import elements.Player;
import elements.PrimerJefe;
import elements.QuintoJefe;
import elements.SegundoJefe;
import elements.Solid;
import elements.TercerJefe;
import elements.Tienda;
import elements.Totem;
import game.Demo;
import game.Parametros;
import managers.AudioManager;
import managers.ResourceManager;



public class GameScreen extends BScreen{
	
Stage mainStage;


public Array<Solid> suelo;
public Array<Solid> escaleras;
public Array<Enemy> enemigos;
public Array<Solid> checkPoints;
public Array<Solid> muertes;
public Array<Npc> npcs;
Solid end;


public OrthographicCamera camara;
private TiledMap map;
private OrthogonalTiledMapRenderer renderer;
private int tileWidth, tileHeight, mapWidthInTiles, mapHeightInTiles,
mapWidthInPixels, mapHeightInPixels;
private int[] pPlano=new int[] {3};

private Label etiquetaVida;
private Label etiquetaMonedas;
private Label etiquetaMana;

float inicioX;
float inicioY;




	   
public Player player;

	public GameScreen(Demo game) {
	
		super(game);
		
	
		mainStage=new Stage();

		switch(Parametros.nivel) {
		case 0: 
			map=ResourceManager.getMap("maps/Entrada.tmx");
			break;
		case 1:
			map=ResourceManager.getMap("maps/Castillo.tmx");
			break;
		case 2:
			map=ResourceManager.getMap("maps/Mazmorra.tmx");
			break;
		case 3:
			map=ResourceManager.getMap("maps/Techo.tmx");
			break;
		
		}
		renderer=new OrthogonalTiledMapRenderer(map,mainStage.getBatch());
		
		
		camara=(OrthographicCamera) mainStage.getCamera();
		camara.setToOrtho(false, Parametros.getAnchoPantalla()*Parametros.zoom, Parametros.getAltoPantalla()*Parametros.zoom);
		renderer.setView(camara);
		
		ArrayList<MapObject> elementos;
		elementos=getRectangleList("Solid");
		
		MapProperties props;
		Solid solido;
		suelo=new Array<Solid>();
		
		for(MapObject solid:elementos) {
			props=solid.getProperties();
			solido=new Solid((float)props.get("x"),(float)props.get("y"),mainStage,(float)props.get("width"),(float)props.get("height"));
			suelo.add(solido);
		}
		
		elementos=getRectangleList("check");
		
	
		
		checkPoints=new Array<Solid>();
		for(MapObject solid:elementos) {
			props=solid.getProperties();
			solido=new Solid((float)props.get("x"),(float)props.get("y"),mainStage,(float)props.get("width"),(float)props.get("height"));
			checkPoints.add(solido);
		}
		elementos=getRectangleList("death");
	
		muertes=new Array<Solid>();
		for(MapObject solid:elementos) {
			props=solid.getProperties();
			solido=new Solid((float)props.get("x"),(float)props.get("y"),mainStage,(float)props.get("width"),(float)props.get("height"));
			muertes.add(solido);
		}
		
		elementos=getRectangleList("Stair");
		Solid escalera;
		escaleras=new Array<Solid>();
		for(MapObject solid:elementos) {
			props=solid.getProperties();
			escalera=new Solid((float)props.get("x"),(float)props.get("y"),mainStage,(float)props.get("width"),(float)props.get("height"));
			escaleras.add(escalera);
		}
		
		
		elementos=getRectangleList("Start");
		props=elementos.get(0).getProperties();
		inicioX=(float)props.get("x");
		inicioY=(float)props.get("y");
		
		elementos=getRectangleList("End");
		props=elementos.get(0).getProperties();
		end=new Solid((float)props.get("x"),(float)props.get("y"),mainStage,(float)props.get("width"),(float)props.get("height"));
		
		
		enemigos=new Array<Enemy>();
		for(MapObject ene:getEnemyList()) {
			props=ene.getProperties();
			switch(props.get("enemigo").toString()) {
			case "esqueleto":
				Esqueleto esqueleto=new Esqueleto((float)props.get("x"), (float)props.get("y"), mainStage, this);
				
				
				enemigos.add(esqueleto);
				break;
			case "gargola":
				Gargola gargola=new Gargola((float)props.get("x"), (float)props.get("y"), mainStage, this);
				
				
				enemigos.add(gargola);
				break;
			case "caballero":
				Caballero caballero= new Caballero((float)props.get("x"), (float)props.get("y"), mainStage, this);
				
				enemigos.add(caballero);
				break;
			case "mago":
				Mago mago = new Mago((float)props.get("x"), (float)props.get("y"), mainStage, this);
				
				enemigos.add(mago);
				break;
			case "caballeroGrande":
				CaballeroGrande caballeroGrande = new CaballeroGrande((float)props.get("x"), (float)props.get("y"), mainStage, this);
				
				enemigos.add(caballeroGrande);
				break;
			case "arquero":
				Arquero arquero = new Arquero((float)props.get("x"), (float)props.get("y"), mainStage, this);
				
				enemigos.add(arquero);
				break;
			case "primerJefe":
				PrimerJefe primerJefe = new PrimerJefe((float)props.get("x"), (float)props.get("y"), mainStage, this);
				
				enemigos.add(primerJefe);
				break;
			case "segundoJefe":
				SegundoJefe segundoJefe = new SegundoJefe((float)props.get("x"), (float)props.get("y"), mainStage, this);
				
				enemigos.add(segundoJefe);
				break;
			case "terceroJefe":
				TercerJefe terceroJefe = new TercerJefe((float)props.get("x"), (float)props.get("y"), mainStage, this);
				
				enemigos.add(terceroJefe);
				break;
			case "cuartoJefe":
				CuartoJefe cuartoJefe = new CuartoJefe((float)props.get("x"), (float)props.get("y"), mainStage, this);
				
				enemigos.add(cuartoJefe);
				break;
			case "quintoJefe":
				QuintoJefe quintoJefe = new QuintoJefe((float)props.get("x"), (float)props.get("y"), mainStage, this);
				
				enemigos.add(quintoJefe);
				break;
			case "totem":
				Totem totem = new Totem((float)props.get("x"), (float)props.get("y"), mainStage, this);
				enemigos.add(totem);
				break;
			case "fin":
				Fin fin = new Fin((float)props.get("x"), (float)props.get("y"), mainStage, this);
				enemigos.add(fin);
				break;
			case "corazon":
				Corazon corazon = new Corazon((float)props.get("x"), (float)props.get("y"), mainStage, this);
				enemigos.add(corazon);
				break;
			case "mana":
				Mana mana = new Mana((float)props.get("x"), (float)props.get("y"), mainStage, this);
				enemigos.add(mana);
				break;
			}
		}
		npcs = new Array<Npc>();
        for (MapObject npc : getNpcList()) {
            props = npc.getProperties();
            switch (props.get("npc").toString()) {
                case "herrero":
                	Herrero herrero=new Herrero((float)props.get("x"), (float)props.get("y"), mainStage, this);
                    npcs.add(herrero);
                	break;
                case "hechicero":
                	Hechicero hechicero=new Hechicero((float)props.get("x"), (float)props.get("y"), mainStage, this);
                    npcs.add(hechicero);
                    break;
                case "tienda":
                	Tienda tienda=new Tienda((float)props.get("x"), (float)props.get("y"), mainStage, this);
                    npcs.add(tienda);
                    break;

            }
        }
		
		
		

		player=new Player(inicioX,inicioY,mainStage,this);

		
		uiStage=new Stage();
		
		etiquetaVida=new Label("Vida: "+ Parametros.vida,uiStyle);
		etiquetaVida.setPosition(12,(float) (Parametros.getAltoPantalla()-Parametros.getAltoPantalla()/6.5));
		etiquetaMana = new Label("Mana: "+ Parametros.mana,uiStyle);
		etiquetaMana.setPosition(8, Parametros.getAltoPantalla()-Parametros.getAltoPantalla()/5);
		etiquetaMonedas = new Label("Monedas: " + Parametros.puntuacion,uiStyle);
		etiquetaMonedas.setPosition(6, Parametros.getAltoPantalla()-Parametros.getAltoPantalla()/4);
		uiStage.addActor(etiquetaVida);
		uiStage.addActor(etiquetaMana);
		uiStage.addActor(etiquetaMonedas);
		switch(Parametros.nivel) {
		case 0:
			AudioManager.playMusic("audio/music/entrada.mp3");
		break;
		case 1:
			AudioManager.playMusic("audio/music/castillo.mp3");
			break;
		case 2:
			AudioManager.playMusic("audio/music/mazmorra.mp3");
			break;
		case 3:
			AudioManager.playMusic("audio/music/techo.mp3");
			break;
		}


	}
	
	@Override
	public void render(float delta) {

		super.render(delta);
	     mainStage.act();
	     uiStage.act();
	   colide();
	    
	    Parametros.jugadorx=player.getX();
	    Parametros.jugadory=player.getY();
	  
	    
	  
	    centrarCamara();
	    renderer.setView(camara);
	    actualizarInterfaz();
	    renderer.render();
	        mainStage.draw();
	        uiStage.draw();
	    

	}
	
	public void colide() {
		player.tocoSuelo=false;
		player.enEscalera=false;

	for(Solid solid:suelo) {
		if(player.overlaps(solid)) {
			player.preventOverlap(solid);
			
		}
		if(player.pies.overlaps(solid)) {
			player.tocoSuelo=true;
			
		}	
		
	}
	
	for(Solid solid:checkPoints) {
		
		 if (solid.getEnabled() && player.overlaps(solid)) {
		        solid.setEnabled(false);
		        inicioX = solid.getX();
		        inicioY = solid.getY();
		    }
	}
	
	for(Solid solid:muertes) {
		if(player.overlaps(solid)) {
			muerte();
			
		}
	}
	
	for(Solid escalera:escaleras) {
		if(player.overlaps(escalera)) {
			player.enEscalera=true;
		}
	}
	if(player.overlaps(end)) {
		cambioNivel();
	}
	
	player.colocarPies();	
	
	for(Enemy enemigo : enemigos) {
		if(player.overlaps(enemigo)) {
				if(enemigo instanceof Totem) {
					Totem totem = (Totem) enemigo;
					Parametros.matarSegundoJefe++;
					System.out.println("");
					totem.remove();
					totem.setPolygon(3, 0, 0, 0,0);
					}
				if(enemigo instanceof Corazon) {
					Corazon corazon = (Corazon) enemigo;
					corazon.aumentarParametroVida();
					corazon.remove();
					corazon.setPolygon(3,0,0,0,0);
				}
				if(enemigo instanceof Mana) {
					Mana mana = (Mana) enemigo;
					mana.aumentarParametroMana();
					mana.remove();
					mana.setPolygon(3,0,0,0,0);
				}
				if (enemigo instanceof Fin) {
				    System.out.println("Jugador ha colisionado con Fin");
				    System.out.println(Parametros.jefe);
				    game.setScreen(new GoverScreen(game));
				    return;
				}
			}
		}
	System.out.println(Parametros.jefe);
	}
	
	public void centrarCamara() {
		this.camara.position.x=player.getX();
		this.camara.position.y=player.getY();
		camara.update();
		
	}
	
	public void muerte() {
		Parametros.nivel = 0;
		game.setScreen(new GameScreen(game));
        player.setPosition(inicioX, inicioY);
        Parametros.vida = 1;
        Parametros.mana = 0;
        Parametros.jefe = 5;
	        	
	}
	
	public void cambioNivel() {
		Parametros.nivel++;
		game.setScreen(new GameScreen(game));
		
	}
	
	public ArrayList<MapObject> getRectangleList(String propertyName){
		ArrayList<MapObject> list =new ArrayList<MapObject>();
		for(MapLayer layer: map.getLayers()) {
			for(MapObject obj: layer.getObjects()) {
				if(!(obj instanceof RectangleMapObject))
					continue;
				MapProperties props= obj.getProperties();
				if(props.containsKey("name") &&  props.get("name").equals(propertyName))
				{
					list.add(obj);
				}
				
			}
			
		}
		
		return list;
	}
	
	public ArrayList<Polygon> getPolygonList(String propertyName){
		
		Polygon poly;
		ArrayList<Polygon> list =new ArrayList<Polygon>();
		for(MapLayer layer: map.getLayers()) {
			for(MapObject obj: layer.getObjects()) {
				
				
				if(!(obj instanceof PolygonMapObject))
					continue;
				MapProperties props= obj.getProperties();
				if(props.containsKey("name") &&  props.get("name").equals(propertyName))
				{
					
					poly=((PolygonMapObject)obj).getPolygon();
					list.add(poly);
				}
				
			}
			
		}
		
		return list;
	}
	
	
	
	
	
	public ArrayList<MapObject> getEnemyList(){
		ArrayList<MapObject> list =new ArrayList<MapObject>();
		for(MapLayer layer: map.getLayers()) {
			for(MapObject obj: layer.getObjects()) {
				if(!(obj instanceof TiledMapTileMapObject))
					continue;
				MapProperties props= obj.getProperties();
				
				
				TiledMapTileMapObject tmtmo=(TiledMapTileMapObject) obj;
				TiledMapTile t=tmtmo.getTile();
				MapProperties defaultProps=t.getProperties();
				if(defaultProps.containsKey("enemigo")) {
					list.add(obj);
					
					
				}
				
				
				Iterator<String> propertyKeys=defaultProps.getKeys();
				while(propertyKeys.hasNext()) {
					String key =propertyKeys.next();
					
					if(props.containsKey(key))
						continue;
					else {
						Object value=defaultProps.get(key);
						props.put(key, value);
					}
						
				}
				
			}
			
		}
		
		return list;
	}
	
	public ArrayList<MapObject> getNpcList() {
        ArrayList<MapObject> list = new ArrayList<MapObject>();
        for (MapLayer layer : map.getLayers()) {
            for (MapObject obj : layer.getObjects()) {
                if (!(obj instanceof TiledMapTileMapObject))
                    continue;
                MapProperties props = obj.getProperties();


                TiledMapTileMapObject tmtmo = (TiledMapTileMapObject) obj;
                TiledMapTile t = tmtmo.getTile();
                MapProperties defaultProps = t.getProperties();
                if (defaultProps.containsKey("npc")) {
                    list.add(obj);


                }


                Iterator<String> propertyKeys = defaultProps.getKeys();
                while (propertyKeys.hasNext()) {
                    String key = propertyKeys.next();

                    if (props.containsKey(key))
                        continue;
                    else {
                        Object value = defaultProps.get(key);
                        props.put(key, value);
                    }

                }

            }

        }

        return list;
    }
	
	
	public void actualizarInterfaz() {
		etiquetaVida.setText("Vida: "+ Parametros.vida);
		etiquetaMana.setText("Mana: "+ Parametros.mana);
		etiquetaMonedas.setText("monedas: " + Parametros.puntuacion);
		
	}
	
	
	public void aumentarVida() {
		if(Parametros.aumentarVida == 1) {
			Parametros.vida = 8;
		} else if(Parametros.aumentarVida == 2) {
			Parametros.vida = 10;
		}
	}
	 
	public void aumentarMana() {
		if(Parametros.aumentarMana == 1) {
			Parametros.mana = 6;
		}else if(Parametros.aumentarMana <= 2) {
			Parametros.mana = 8;
		}
	}
	
	
}
