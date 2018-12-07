package uet.oop.bomberman.Sound;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class play {
    public static void playMusic(String path){
        InputStream music;
        try{
            music=new FileInputStream(new File(path));
            AudioStream au=new AudioStream(music);
            AudioPlayer.player.start(au);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
