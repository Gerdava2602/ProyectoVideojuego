/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Static;

import Entities.Entity;
import Entities.EntityManager;
import Entities.Static.BookPile;
import FirstMinigame.WorldGenerator.WorldLibrary;
import MainG.Handler;
import MainG.Window;
import Tilemaps.Animation;
import java.awt.Color;
import Tilemaps.Assets;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author German David
 */
public class BookInfo extends StaticEntity{

    
    private final EntityManager manager;
    private final BufferedImage img;
    private final int id;
    private Graphics2D g;
    private static int contIni;
    private boolean sw=true;
    Font textFont = new Font("pixelart", Font.PLAIN, 20);
    private Animation openning;

    public BookInfo(EntityManager manager, BufferedImage img, Handler handler, float x, float y, int width, int height, int id) {
        super(handler, manager, x, y, width, height);
        this.manager = manager;
        this.img = img;
        this.id = id;
        
        openning = new Animation(100,Assets.BookOpenning);
    }

    
    
    
    
    @Override
    public void update(){
        getInput();
        openning.update();
    }
    
    void getInput(){
        if (Window.keyManager.enter) {
            manager.removeEntity(this);//Quitar despues, cambiar por moverse a otra posicion
            this.manager.getJoan().setCanMove(true);
        }
    }
    
    @Override
    public void render(Graphics2D g) {
        this.manager.getJoan().setCanMove(false);
        
        /*
        g.setFont(textFont);
        g.drawImage(Assets.astronautTalker, 25, 470, 995, 189, null);
        g.setColor(Color.WHITE);
        g.drawString("Presiona ENTER para continuar", 700, 505);
        */
        g.drawImage(getCurrentFrame(),200,200,null);
        /*
        switch (this.id) {
            case 0:
                g.setColor(Color.BLACK);
                g.drawString("Bienvenida viajera!", 250, 545);
                g.drawString("Ahora mismo eres Joan Clarke, pero debes salir de aqui y continuar tu camino.", 250, 575);
                g.drawString("La clave está en ir recogiendo libros que poseen la información de tu historia.", 250, 605);
                g.drawString("Al final deberas pasar una prueba final y es importante que memorices esta información", 250, 635);
                break;
            case 1:
                g.setColor(Color.BLACK);
                g.drawString("Gracias a una beca, obtuviste una doble titulacion en matemáticas,", 250, 545);
                g.drawString("pero no un título completo ya que eras mujer.", 250, 575);
                g.drawString("Fuiste reclutada en la institucion dedicada a romper el código enigma,", 250, 605);
                g.drawString("pero solo te pusieron a hacer trabajo rutinario de oficina a pesar de tus habilidades.", 250, 635);
                break;
            case 2:
                g.setColor(Color.BLACK);
                g.drawString("Bienvenida Astronauta", 280, 545);
                g.drawString("Ahora mismo eres Katherine Johnson,estas a bordo del Apolo ", 280, 575);
                g.drawString("Tu mision, es estar al mando del manejo de la nave y cambiar", 280, 605);
                g.drawString("la historia", 280, 635);
                break;
            case 3:
                g.setColor(Color.BLACK);
                g.drawString("Bienvenida Astronauta", 280, 545);
                g.drawString("Ahora mismo eres Katherine Johnson,estas a bordo del Apolo ", 280, 575);
                g.drawString("Tu mision, es estar al mando del manejo de la nave y cambiar", 280, 605);
                g.drawString("la historia", 280, 635);
                break;
            case 4:
                g.setColor(Color.BLACK);
                g.drawString("Bienvenida viajera!", 280, 545);
                g.drawString("Ahora mismo eres Joan Clarke, pero debes salir de aqui y continuar tu camino.", 280, 575);
                g.drawString("La clave está en ir recogiendo libros que poseen la información de tu historia.", 280, 605);
                g.drawString("Al final deberas pasar una prueba final y es importante que memorices esta información", 280, 635);
                break;
            case 5:
                g.setColor(Color.BLACK);
                g.drawString("Bienvenida Astronauta", 280, 545);
                g.drawString("Ahora mismo eres Katherine Johnson,estas a bordo del Apolo ", 280, 575);
                g.drawString("Tu mision, es estar al mando del manejo de la nave y cambiar", 280, 605);
                g.drawString("la historia", 280, 635);
                break;
            case 6:
                g.setColor(Color.BLACK);
                g.drawString("Bienvenida Astronauta", 280, 545);
                g.drawString("Ahora mismo eres Katherine Johnson,estas a bordo del Apolo ", 280, 575);
                g.drawString("Tu mision, es estar al mando del manejo de la nave y cambiar", 280, 605);
                g.drawString("la historia", 280, 635);
                break;
            case 7:
                g.setColor(Color.BLACK);
                g.drawString("Bienvenida Astronauta", 280, 545);
                g.drawString("Ahora mismo eres Katherine Johnson,estas a bordo del Apolo ", 280, 575);
                g.drawString("Tu mision, es estar al mando del manejo de la nave y cambiar", 280, 605);
                g.drawString("la historia", 280, 635);
                break;

            default:
                throw new AssertionError();
        }
        */
    }

    @Override
    public void die() {
        
    }
    
    public int getId() {
        return id;
    }

    private Image getCurrentFrame() {
        
        
        if(sw==false){
            if(WorldLibrary.bookcount-1<6)
                return Assets.Books[WorldLibrary.bookcount-1];
            else
                return Assets.Books[WorldLibrary.bookcount-7];
            
        }else{
            if(openning.getCurrentFrame().equals(Assets.BookOpenning[5])){
                sw=!sw;
            }
            return openning.getCurrentFrame();
        }
        
    }
    
}
