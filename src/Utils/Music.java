/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
 *
 * @author laoui
 */
public class Music {
        public static void music() 
{       
   AudioPlayer MGP = AudioPlayer.player;
   AudioStream BGM;
   AudioData MD;

   ContinuousAudioDataStream loop = null;

   try
   {
       InputStream test = new FileInputStream("C:\\a.wav");
       BGM = new AudioStream(test);
       AudioPlayer.player.start(BGM);
   }
   catch(FileNotFoundException e){
       System.out.print(e.toString());
   }
   catch(IOException error)
   {
       System.out.print(error.toString());
   }
   MGP.start(loop);
}
}
