/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameStates;

import FirstMinigame.WorldGenerator.WorldLibrary;
import MainG.Window;
import Tilemaps.Animation;
import Tilemaps.Assets;
import UI.Answer;
import UI.ClickListener;
import UI.UIManager;
import UI.UIObject;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 *
 * @author German David
 */
public class QuizState extends GameState {

    UIManager manager;
    Answer a, b, c, d;
    Answer ans;
    boolean answered;
    boolean sw;
    int i, correctas;
    long wait = 5000, lastTime = 0, actual = 0;
    private Animation openning;

    public QuizState(GameStateManager gsm) {
        super(gsm);
        i = 1;
        correctas = 0;
        answered = false;
        manager = new UIManager(handler);
        sw = true;
        Window.mouse.setUIManager(manager);
        a = new Answer(true, "Correcta", 550, 240, 280, 50, new ClickListener() {
            @Override
            public void onClick() {
                if (a.isCorrect()) {
                    System.out.println("True");
                } else {
                    System.out.println("False");
                }
                answered = true;
                ans = a;
            }
        });
        manager.addUIObject(a);

        b = new Answer(false, "Falsa", 550, 340, 280, 50, new ClickListener() {
            @Override
            public void onClick() {
                if (b.isCorrect()) {
                    System.out.println("True");
                } else {
                    System.out.println("False");
                }
                answered = true;
                ans = b;
            }
        });
        manager.addUIObject(b);
        c = new Answer(false, "Falsa", 550, 440, 280, 50, new ClickListener() {
            @Override
            public void onClick() {
                if (c.isCorrect()) {
                    System.out.println("True");
                } else {
                    System.out.println("False");
                }
                ans = c;
                answered = true;
            }
        });
        manager.addUIObject(c);
        d = new Answer(false, "Falsa", 550, 540, 280, 50, new ClickListener() {
            @Override
            public void onClick() {
                if (d.isCorrect()) {
                    System.out.println("True");
                } else {
                    System.out.println("False");
                }
                ans = d;
                answered = true;
            }
        });
        manager.addUIObject(d);

        openning = new Animation(50, Assets.QuizBook);
        openning.setIndex(1);
    }

    @Override
    public void init() {
        timePassed = System.currentTimeMillis();
    }

    @Override
    public void update() {
        openning.update();
        if (!answered) {
            Window.mouse.setUIManager(manager);
            actual = System.currentTimeMillis();
            lastTime = 0;
            manager.tick();
            switch (i) {

                case 1:

                    break;
                case 2:
                    a.setNewInformation("Level 2");
                    a.setNewCorrect(false);
                    b.setNewCorrect(true);
                    b.setNewInformation("Correcta");
                    break;
                case 3:
                    a.setNewInformation("Level 3");
                    b.setNewCorrect(false);
                    b.setNewInformation("falsa");
                    c.setNewCorrect(true);
                    c.setNewInformation("Correcta");
                    break;
                case 4:
                    a.setNewInformation("Level 4");
                    c.setNewCorrect(false);
                    c.setNewInformation("Falsa");
                    b.setNewCorrect(true);
                    b.setNewInformation("Correcta");
                    break;
                case 5:
                    a.setNewInformation("Level 2");
                    b.setNewCorrect(false);
                    b.setNewInformation("Falsa");
                    d.setNewCorrect(true);
                    d.setNewInformation("Correcta");
                    break;
                default:
                    WorldLibrary auxW = (WorldLibrary) gsm.getGameStates()[2].getWorld();
                    gsm.getGameStates()[1].getLoadData();
                    MainLevel auxS = (MainLevel) gsm.getGameStates()[1];
                    auxS.getLevelManager().setFinishedMinigame();
                    auxW.setFinished();
                    gsm.reloadState(1);
                    break;
            }
            getInput();
        } else {
            lastTime += System.currentTimeMillis() - actual;
            actual = System.currentTimeMillis();
            Window.mouse.setUIManager(null);

            if (lastTime >= wait) {
                i++;
                openning.setIndex(1);
                answered = false;
            }

        }
        // Iniciar el menu de pausa
        if (Window.keyManager.pause) {
            pauseState();
        }
    }

    public void getInput() {
        if (Window.keyManager.a) {
            a.onClick();
        } else if (Window.keyManager.b) {
            b.onClick();
        } else if (Window.keyManager.c) {
            c.onClick();
        } else if (Window.keyManager.d) {
            d.onClick();
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(Assets.Table, 0, 0, null);
        if (i <= 6) {

            if (!answered) {
                //Diferentes preguntas
                if (getCurrentFrame().equals(Assets.QuizBook[0])) {
                    g.drawImage(Assets.Questions[i - 1], 130, 70, null);
                    manager.render(g);
                } else {
                    g.drawImage(getCurrentFrame(), 130, 70, 800, 580, null);
                }

            } else {
                g.drawImage(Assets.QuizAnswers[i - 1], 130, 70, null);
                g.setColor(Color.BLACK);
                if (ans.isCorrect()) {
                    g.drawString("Felicitaciones! Tu respuesta es correcta", 550, 240);
                    correctas++;
                } else {
                    g.drawString("Lo sentimos. Tu respuesta es incorrecta", 550, 240);
                }

                sw = true;
            }
        } else {
            g.drawString("Obtuviste" + correctas, i, i);
            
        }
    }

    @Override
    public void musicControl() {

    }

    @Override
    public World getWorld() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getInsertData() {

    }

    @Override
    public void getLoadData() {

    }

    private Image getCurrentFrame() {
        if (sw == false) {
            return Assets.QuizBook[0];
        } else {
            if (openning.getCurrentFrame().equals(Assets.QuizBook[17])) {
                sw = !sw;
            }
            return openning.getCurrentFrame();
        }
    }

}
