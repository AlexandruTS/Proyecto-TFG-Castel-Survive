	package elements;
	
	import java.util.Random;
	
	import com.badlogic.gdx.Gdx;
	import com.badlogic.gdx.Input.Keys;
	import com.badlogic.gdx.graphics.g2d.Animation;
	import com.badlogic.gdx.graphics.g2d.TextureRegion;
	import com.badlogic.gdx.math.MathUtils;
	import com.badlogic.gdx.math.Vector2;
	import com.badlogic.gdx.scenes.scene2d.Stage;
	import com.badlogic.gdx.utils.Array;
	import com.badlogic.gdx.utils.TimeUtils;
	import com.badlogic.gdx.utils.Timer;
	
	import game.Parametros;
	import managers.AudioManager;
	import screens.GameScreen;
	
	public class Player extends Element{
	private final float dashCooldown = 2f;
	private Animation<TextureRegion> frente;
	private Animation<TextureRegion> espalda;
	private Animation<TextureRegion> drcha;
	private Animation<TextureRegion> izqda;
	private Animation<TextureRegion> muerte;
	private Animation<TextureRegion> impulsoAireIzquierda;
	private Animation<TextureRegion> impulsoAireDerecha;
	private Animation<TextureRegion> impulsoIzquierda;
	private Animation<TextureRegion> impulsoDerecha;
	private Animation<TextureRegion> ataqueDerecha;
	private Animation<TextureRegion> ataqueIzquierda;
	private Animation<TextureRegion> lanzaDerecha;
	private Animation<TextureRegion> lanzaIzquierda;
	private Animation<TextureRegion> dagasDerecha;
	private Animation<TextureRegion> dagasIzquierda;
	public Element pies;
	public float velocidad;
	private float velocidadSalto;
	public boolean tocoSuelo;
	public boolean enEscalera;
	public Array<Fuego> cargador;
	public Array<Hielo> cargadorHielo;
	public Array<RayoIzquierda> cargadorRayoIzquierda;
	public Array<RayoDerecha> cargadorRayoDerecha;
	public Array<RayoCentro> cargadorRayoCentro;
	private int numBalas=50;
	private int balaActual=0;
	private int balaActualHielo=0;
	private int balaActualRayoCentro=0;
	private int balaActualRayoIzquierda=0;
	private int balaActualRayoDerecha=0;
	private int direccion=1;
	public float cadencia=1;
	private float tiempoDisparo=0;
	private float tInvulnerabilidad=1;
	private float tInvulnerable=0;
	private GameScreen nivel;
	public  boolean isDashing = false;
	private float dashTimeLeft = 0;
	private float lastDashTime = -dashCooldown;


	
	
	
	
	
	
	
		public Player(float x, float y, Stage s, GameScreen nivel) {
		super(x, y, s);
		this.velocidad=150;
		this.velocidadSalto=140;
		this.nivel=nivel;
	
		// TODO Auto-generated constructor stub
		frente=loadFullAnimation("player/quieto.png",1,3,0.2f,true);
		espalda=loadFullAnimation("player/quieto.png",1,3,0.2f, true);
		drcha=loadFullAnimation("player/andarDerecha.png",2,1, 0.2f, true);
		izqda=loadFullAnimation("player/andarIzquierda.png",2,1,0.2f, true);
		muerte=loadFullAnimation("player/muerte.png", 1, 1, 4000f, true);
		impulsoAireIzquierda=loadFullAnimation("player/impulsoAireIzquierda.png",3,1,0.2f, true);
		impulsoAireDerecha=loadFullAnimation("player/impulsoAireDerecha.png",3,1,0.2f, true);
		ataqueDerecha=loadFullAnimation("player/atacarDerecha.png",4,1,0f, true);
		ataqueIzquierda=loadFullAnimation("player/atacarIzquierda.png",4,1,0f, true);
		lanzaIzquierda=loadFullAnimation("player/lanzaIzquierda.png",2,1,0f, true);
		lanzaDerecha=loadFullAnimation("player/lanzaDerecha.png",2,1,0f, true);
		dagasDerecha=loadFullAnimation("player/dagasDerecha.png",3,1,0f, true);
		dagasIzquierda=loadFullAnimation("player/dagasIzquierda.png",3,1,0f, true);
		this.setPolygon(4, 20, 20, 2, 2);
		
		pies=new Element(0,0,s,this.getWidth()/4,this.getHeight()/10);
		pies.setRectangle();
		
		cargador=new Array<Fuego>();
		for(int i=0;i<numBalas;i++) {
			this.cargador.add(new Fuego(0,0, s,nivel,true));
		}
		cargadorHielo=new Array<Hielo>();
		for(int i=0;i<numBalas;i++) {
			this.cargadorHielo.add(new Hielo(0,0, s,nivel,true));
		}
		cargadorRayoCentro=new Array<RayoCentro>();
		for(int i=0;i<numBalas;i++) {
			this.cargadorRayoCentro.add(new RayoCentro(0,0, s,nivel,true));
		}
		cargadorRayoDerecha=new Array<RayoDerecha>();
		for(int i=0;i<numBalas;i++) {
			this.cargadorRayoDerecha.add(new RayoDerecha(0,0, s,nivel,true));
		}
		cargadorRayoIzquierda=new Array<RayoIzquierda>();
		for(int i=0;i<numBalas;i++) {
			this.cargadorRayoIzquierda.add(new RayoIzquierda(0,0, s,nivel,true));
		}
		}
		@Override
		public void act(float delta) {
			// TODO Auto-generated method stub
			super.act(delta);
			if(tiempoDisparo>=0) {
				this.tiempoDisparo-=delta;
			}
			if(tInvulnerable>=0) {
				this.tInvulnerable-=delta;
			}
	
			if(!tocoSuelo && !enEscalera) {
			this.acceleration.y+=Parametros.gravedad;}
			if(tocoSuelo && this.velocity.y<0) {
				this.velocity.y=0;
			}
			if(enEscalera) {
				this.velocity.y=0;
			}
			controles();
			if(enEscalera) {
				this.setAnimation(espalda);
			}
			this.applyPhysics(delta);
			if (isDashing) {
				tInvulnerable = dashTimeLeft;
	            dashTimeLeft -= delta;
	
	            if (dashTimeLeft <= 0) {
	                isDashing = false;
	            } else {
	            	 float dashDirection = (direccion == 1) ? 1 : -1;
	                 float dashSpeed = 250;
	                 this.moveBy(dashDirection * dashSpeed * delta, 0);
	            }
	        }
		animaciones();
			
		}
		
		private void controles() {
			if(Gdx.input.isKeyPressed(Keys.X) && tiempoDisparo<=0 && Parametros.hechizo >=1) {
				if(Parametros.mana > 0){
					bolaFuego();
					Parametros.mana--;
				}
			}
			if(Gdx.input.isKeyPressed(Keys.C) && tiempoDisparo<=0 && Parametros.hechizo >=2) {
				if(Parametros.mana > 0){
					ondaHielo();
					Parametros.mana--;
				}
			}
			if(Gdx.input.isKeyPressed(Keys.V) && tiempoDisparo<=0 && Parametros.hechizo >=3) {
				if(Parametros.mana > 0){
					rayo();
					Parametros.mana--;
				}
			}
		if(Gdx.input.isKeyPressed(Keys.UP) && enEscalera){
			this.velocity.y=velocidad;
			
		}	else if(Gdx.input.isKeyPressed(Keys.DOWN)&& enEscalera){
			this.velocity.y=-velocidad;
			
		}
		
		 if (Gdx.input.isKeyJustPressed(Keys.SHIFT_LEFT) && !isDashing && !tocoSuelo && TimeUtils.nanoTime() - lastDashTime > dashCooldown * 1000000000L) {
	         isDashing = true;
	         dashTimeLeft = 0.2f;
	         lastDashTime = TimeUtils.nanoTime();
	     }
		
			if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			this.velocity.x=velocidad;
			
		}	else if(Gdx.input.isKeyPressed(Keys.LEFT)){
			this.velocity.x=-velocidad;
			
		}	else {this.velocity.x=0;}
			
			if(Gdx.input.isKeyJustPressed(Keys.SPACE) && tocoSuelo) {
				jump();
				
				
			}
			if(Parametros.vida <= 0) {
				morir();
			}else {
				this.setAnimation(frente);
			}
			
			 if(Gdx.input.isKeyJustPressed(Keys.Z) && Parametros.ataque == 1) {
			        atacarEspada(); 
			    } else if(Gdx.input.isKeyJustPressed(Keys.Z) && Parametros.ataque ==2) {
			    	atacarLanza();
			    } else if(Gdx.input.isKeyJustPressed(Keys.Z) && Parametros.ataque <= 3) {
			    	atacarDaga();
			    }
			
		}
		
		public void jump() {
			this.velocity.y=velocidadSalto;
			tocoSuelo=false;
		}
		
		public void colocarPies() {
			
			this.pies.setPosition(this.getX()+3*this.getWidth()/8,this.getY()-this.getHeight()/10);
			
		}
		
	public void animaciones() {
			
			if(this.velocity.x>0) {
				this.setAnimation(drcha);
				direccion=1;
			}
			else if(this.velocity.x<0){
				this.setAnimation(izqda);
				direccion=-1;
			}
			
			
			if(enEscalera) {
				this.setAnimation(espalda);
			}
			
			if(isDashing && this.velocity.x>0) {
				this.setAnimation(impulsoAireDerecha);
			}
			if(isDashing && this.velocity.x<0) {
				this.setAnimation(impulsoAireIzquierda);
			}
		}

		public void dano(int dano) {
			if(tInvulnerable<=0) {
			Parametros.vida-=dano;
			tInvulnerable=tInvulnerabilidad;
			}
		}
	
		public void morir() {
		    if (Parametros.vida <= 0) {
		        velocity.set(0, 0);
		        acceleration.set(0, 0);
		        setAnimation(muerte);
		        nivel.muerte();   
		        setAnimation(muerte);
		    }
		}
		
		public void bolaFuego() {
			tiempoDisparo=cadencia;
			this.cargador.get(balaActual).disparar(direccion,0,this.getX(),this.getY() +this.getHeight()/4);
			this.balaActual=(this.balaActual+1)%this.numBalas;
			AudioManager.playSound("audio/sounds/fuego.mp3");
		}
		
		public void ondaHielo() {
		    tiempoDisparo = cadencia;
	
		    for (int i = -1; i <= 1; i++) {
		        this.cargadorHielo.get(balaActualHielo).disparar(direccion, 0, this.getX(), this.getY() + this.getHeight() / 4 + i * 5); 
		        this.balaActualHielo = (this.balaActualHielo + 1) % this.numBalas;		        
		    }
		    AudioManager.playSound("audio/sounds/hielo.mp3");
		}
		public void rayo() {
		    tiempoDisparo = cadencia;
	

		    this.cargadorRayoCentro.get(balaActualRayoCentro).disparar(0, 1, this.getX(), this.getY() + this.getHeight() / 4);
		    this.balaActualRayoCentro = (this.balaActualRayoCentro + 1) % this.numBalas;
	

		    this.cargadorRayoIzquierda.get(balaActualRayoIzquierda).disparar(-1, 0, this.getX(), this.getY() + this.getHeight() / 4);
		    this.balaActualRayoIzquierda = (this.balaActualRayoIzquierda + 1) % this.numBalas;
	

		    this.cargadorRayoDerecha.get(balaActualRayoDerecha).disparar(1, 0, this.getX(), this.getY() + this.getHeight() / 4);
		    this.balaActualRayoDerecha = (this.balaActualRayoDerecha + 1) % this.numBalas;
		    AudioManager.playSound("audio/sounds/rayo.mp3");
		}
		
		public void atacarEspada() {
		    Array<Enemy> enemigos = nivel.enemigos;
		    if (direccion == 1) {  
		        for (Enemy enemigo : enemigos) {
		            if (enemigo.getX() > this.getX() && 
		                enemigo.getX() < this.getX() + this.getWidth() + 1 &&
		                Math.abs(enemigo.getY() - this.getY()) < this.getHeight() / 2) {
		                enemigo.dano(1);
		            }
		        }
		        setAnimation(ataqueDerecha);
		    } else {  
		        for (Enemy enemigo : enemigos) {
		            if (enemigo.getX() < this.getX() && 
		                enemigo.getX() > this.getX() - 1 &&
		                Math.abs(enemigo.getY() - this.getY()) < this.getHeight() / 2) {
		                enemigo.dano(1);
		            }
		        }
		        setAnimation(ataqueIzquierda);
		    }
		    AudioManager.playSound("audio/sounds/ataque.mp3");
		}

		public void atacarLanza() {
		    Array<Enemy> enemigos = nivel.enemigos;
		    if (direccion == 1) {  
		        for (Enemy enemigo : enemigos) {
		            if (enemigo.getX() > this.getX() && 
		                enemigo.getX() < this.getX() + this.getWidth() + 3 &&
		                Math.abs(enemigo.getY() - this.getY()) < this.getHeight() / 2) {
		                enemigo.dano(2);
		            }
		        }
		        setAnimation(lanzaDerecha);
		    } else {  
		        for (Enemy enemigo : enemigos) {
		            if (enemigo.getX() < this.getX() && 
		                enemigo.getX() > this.getX() - 3 &&
		                Math.abs(enemigo.getY() - this.getY()) < this.getHeight() / 2) {
		                enemigo.dano(2);
		            }
		        }
		        setAnimation(lanzaIzquierda);
		    }
		    AudioManager.playSound("audio/sounds/ataque.mp3");
		}

		public void atacarDaga() {
		    Array<Enemy> enemigos = nivel.enemigos;
		    if (direccion == 1) {  
		        for (Enemy enemigo : enemigos) {
		            if (enemigo.getX() > this.getX() && 
		                enemigo.getX() < this.getX() + this.getWidth() + 1 &&
		                Math.abs(enemigo.getY() - this.getY()) < this.getHeight() / 2) {
		                enemigo.dano(3);
		            }
		        }
		        setAnimation(dagasDerecha);
		    } else {  
		        for (Enemy enemigo : enemigos) {
		            if (enemigo.getX() < this.getX() && 
		                enemigo.getX() > this.getX() - 1 &&
		                Math.abs(enemigo.getY() - this.getY()) < this.getHeight() / 2) {
		                enemigo.dano(3);
		            }
		        }
		        setAnimation(dagasIzquierda);
		    }
		    AudioManager.playSound("audio/sounds/ataque.mp3");
		}
}		
	
	
