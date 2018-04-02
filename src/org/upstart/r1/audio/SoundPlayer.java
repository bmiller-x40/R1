package org.upstart.r1.audio;

import javax.sound.sampled.*;

public class SoundPlayer  {

    public void play(Sound aSound) {
        int EXTERNAL_BUFFER_SIZE = 524288;
        SourceDataLine audioLine = null;

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(aSound.soundFile);
            AudioFormat format = audioInputStream.getFormat();


            //Describe a desired line
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            audioLine = (SourceDataLine) AudioSystem.getLine(info);

            //Opens the line with the specified format,
            //causing the line to acquire any required
            //system resources and become operational.
            audioLine.open(format);

            //Allows a line to engage in data I/O
            audioLine.start();

            int nBytesRead = 0;
            byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0) {
                    //Writes audio data to the mixer via this source data line
                    //NOTE : A mixer is an audio device with one or more lines
                    audioLine.write(abData, 0, nBytesRead);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {
            //Drains queued data from the line
            //by continuing data I/O until the
            //data line's internal buffer has been emptied
            audioLine.drain();

            //Closes the line, indicating that any system
            //resources in use by the line can be released
            audioLine.close();
        }
    }

}
