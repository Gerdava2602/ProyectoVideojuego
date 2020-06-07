package MainLevel;

import Audio.AudioLoader;
import Entities.Creatures.MainPlayer;
import Entities.EntityManager;
import GameStates.LevelUpManager;
import GameStates.MainLevel;
import GameStates.World;
import MainG.Window;
import MainLevel.WorldGenerator.WorldPlat;
import UtilLoader.MusicPlayer;
import UtilLoader.SaveGame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import tinysound.Music;

/**
 *
 * @author Omen
 */
public class MainLevelUpManager extends LevelUpManager implements SaveGame {

    private Music[] musicPlaylist;
    private int minigames = 0;
    private boolean endMinigame;
    private int currentWorld = 1;
    private boolean levelSwitched = false;
    private MainPlayer player;
    private int currentButtonsPressed;
    private boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false, flag5 = false, flag6 = false, flag7 = false, flag8 = false, flag9 = false, flag10 = false;
    
    private MusicPlayer musicPlayer;
    private Thread hiloMusica;
    
    private MainLevel state;
    int[] position = new int[2];

    public MainLevelUpManager(World world, EntityManager entityManager, MainLevel state) {
        super(world, entityManager);
        player = this.entityManager.getMainPlayer();
        endMinigame = false;
        this.state = state;
        init();
    }

    
    public void init(){
        musicPlaylist = AudioLoader.musicPlayListMainLevel;
        musicPlayer = new MusicPlayer(musicPlaylist);
        hiloMusica = new Thread(musicPlayer,"auxiliarMusica");
        //hiloMusica.start();
    }
    
    @Override
    public void levelUpManager() {
        if(Window.keyManager.debug){
            player.setGravity(500f);
        }
        
        changeMusic();
        WorldPlat worldAux = (WorldPlat) world.cast(this);
        if (!endMinigame) {
            switch (currentWorld) {
                case 1:
                    if (!flag1) {
                        player.setMaxReturns(1);
                        player.setAmountOfReturns(0);
                        flag1 = true;
                        insertData();
                    }
                    switchLevels(1, worldAux);
                    resetLevel(1, worldAux);
                    break;
                case 2:
                    if (!flag2) {
                        player.setMaxReturns(1);
                        player.setAmountOfReturns(0);
                        flag2 = true;
                        insertData();
                    }
                    switchLevels(4, worldAux);
                    resetLevel(2, worldAux);
                    break;
                case 3:
                    if (!flag3) {
                        player.setMaxReturns(1);
                        player.setAmountOfReturns(0);
                        flag2 = true;
                        insertData();
                    }
                    switchLevels(4, worldAux);
                    resetLevel(3, worldAux);
                    break;
                case 4:
                    if (!flag4) {
                        player.setMaxReturns(1);
                        player.setAmountOfReturns(0);
                        flag4 = true;
                        insertData();
                    }
                    switchLevels(1, worldAux);
                    resetLevel(4, worldAux);
                    break;
                case 5:
                    if (!flag5) {
                        worldAux.switchAllTheTiles();
                        worldAux.changeAnimation();
                        player.setGravity(500);
                        player.setMaxReturns(1);
                        player.setAmountOfReturns(0);
                        flag5 = true;
                        insertData();
                    }
                    switchLevels(1, worldAux);
                    resetLevel(5, worldAux);
                    break;
                case 6:
                    if (!flag6) {
                        worldAux.switchAllTheTiles();
                        player.setMaxReturns(1);
                        player.setAmountOfReturns(0);
                        flag6 = true;
                        insertData();
                    }
                    switchLevels(1, worldAux);
                    resetLevel(6, worldAux);
                    break;
                case 7:
                    if (!flag7) {
                        worldAux.switchAllTheTiles();
                        player.setMaxReturns(1);
                        player.setAmountOfReturns(0);
                        flag7 = true;
                        insertData();
                    }
                    switchLevels(1, worldAux);
                    resetLevel(7, worldAux);
                    break;
                case 8:
                    if (!flag8) {
                        player.setMaxReturns(1);
                        player.setAmountOfReturns(0);
                        flag8 = true;
                        insertData();
                    }
                    switchLevels(1, worldAux);
                    resetLevel(8, worldAux);
                    break;
                case 9:
                    if (!flag9) {
                        player.setMaxReturns(1);
                        player.setAmountOfReturns(0);
                        flag8 = true;
                        insertData();
                    }
                    switchLevels(1, worldAux);
                    resetLevel(9, worldAux);
                    break;
                case 10:
                    if (!flag10) {
                        player.setMaxReturns(1);
                        player.setAmountOfReturns(0);
                        flag10 = true;
                        insertData();
                    }
                    switchLevels(1, worldAux);
                    resetLevel(10, worldAux);
                    break;
                default:
                    break;
            }
        } else {
            finishLevel();
        }
    }

    @Override
    public void changeMusic() {
        if(!levelSwitched){
            musicPlayer.resume();
        }
    }

    @Override
    public void finishLevel() {
        
    }

    @Override
    public void loadData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("saveGeneralFile.txt"));
            br.readLine(); // Salta el GameState
            br.readLine(); // Salta el minijuego
            player.setX(Integer.parseInt(br.readLine()));
            player.setY(Integer.parseInt(br.readLine()));
            player.setyMove(Integer.parseInt(br.readLine()));
            player.setxMove(Integer.parseInt(br.readLine()));
            currentButtonsPressed = Integer.parseInt(br.readLine());
            br.close();
        } catch (Exception e) {

        }
    }

    @Override
    public void insertData() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("saveGeneralFile.txt"));
            bw.write("" + 1);
            bw.newLine();
            bw.write("" + minigames);
            bw.newLine();
            bw.write("" + player.getX());
            bw.newLine();
            bw.write("" + player.getY());
            bw.newLine();
            bw.write("" + player.getYmove());
            bw.newLine();
            bw.write("" + player.getXmove());
            bw.newLine();
            bw.write("" + player.getAmountOfReturns());
            bw.newLine();
            bw.write("" + currentButtonsPressed);
            bw.newLine();
            bw.close();
        } catch (Exception e) {

        }
    }

    public void setMusic(Music[] musicPlayList) {
        this.musicPlaylist = musicPlayList;
    }

    public void setFinishedMinigame() {
        player.setInMinigame(false);
        levelSwitched = false;
    }

    public void switchLevels(int maxButtons, WorldPlat world) {
        int[] position = new int[2];
        if (player.isButtonPressed()) {
            if (player.isInMinigame()) {
                musicPlayer.kill();
                minigames++;
                levelSwitched = true;
                loadData();
                state.teleporterReached(minigames);
            } else {
                currentButtonsPressed++;
                world.switchElevators(currentButtonsPressed);
                player.setButtonPressed();
            }
        }
        if (!levelSwitched) {
            if (player.isTouchingLap()){
                if (currentWorld == 10) {
                    endMinigame = true;
                } else {
                    currentWorld++;
                    world.changeMap(state.pathString(currentWorld));
                    world.restoreElevators();
                    position = world.getPositionMap();
                    player.setPosition(position);
                    player.setReturnPoint(null);
                    currentButtonsPressed = 0;
                }
            }
        }
    }

    public void resetLevel(int flagStatus, WorldPlat world) {
        if (player.isReset()) {
            currentButtonsPressed = 0;
            world.restoreElevators();
            setFlagFalse(flagStatus);
            position = world.getPositionMap();
            player.setPosition(position);
            player.setReset(false);
        }
    }

    public void setFlagFalse(int flagMark) {
        switch (flagMark) {
            case 1:
                flag1 = false;
                break;
            case 2:
                flag2 = false;
                break;
            case 3:
                flag3 = false;
                break;
            case 4:
                flag4 = false;
                break;
            case 5:
                flag5 = false;
                break;
            case 6:
                flag6 = false;
                break;
            case 7:
                flag7 = false;
                break;
            case 8:
                flag8 = false;
                break;
            case 9:
                flag9 = false;
                break;
            default:
                break;
        }
    }

    public boolean getFinishedGame() {
        return endMinigame;
    }
    
    public void setLevelSwitched(boolean value){
       levelSwitched = value; 
    }

    public MusicPlayer getMusicPlayer() {
        return musicPlayer;
    }
    
    
}
