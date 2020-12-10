package com.lisi.videoTest;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public class AudioTest {
    public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File file = new File("in/a.wav");
        Clip clip = AudioSystem.getClip();
        AudioInputStream ais = AudioSystem.getAudioInputStream(file);
        clip.open(ais);
        //获取音频文件时长
        double secTemp = (double)Math.round((clip.getMicrosecondLength() / 1000000D)*100)/100;
        int min = (int)secTemp/60;
        int sec = (int)(secTemp-min*60);
        double hsec = (double)Math.round((secTemp -min*60 - sec)*100)/100;

        System.out.println( clip.getMicrosecondLength() / 1000000D + " s" );

        //
//        FileInputStream fileau=new FileInputStream(file);
//        AudioStream as=new AudioStream(fileau);
//        AudioPlayer.player.start(as);
//        System.out.println(file.getAbsolutePath());
    }
}
