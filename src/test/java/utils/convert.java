package utils;

import ws.schild.jave.*;

import java.io.File;
public class convert {
	public static void AviToMp4(String oldPath,String newPath) {
				File source = new File(contains.folderReprotLocation+ oldPath);
			    File target = new File(contains.folderReprotLocation+newPath);
			    AudioAttributes audio = new AudioAttributes(); 
				audio.setCodec("libmp3lame");//Audio encoding format
				audio.setBitRate(800000);
				audio.setChannels(1);
				//audio.setSamplingRate(new Integer(22050)); 
				VideoAttributes video = new VideoAttributes(); 
				video.setCodec("libx264");//Video encoding format
				video.setBitRate(3200000);
				video.setFrameRate(15);//If the number setting is small, the video will freeze
				EncodingAttributes attrs = new EncodingAttributes(); 
				attrs.setFormat("mp4");
				attrs.setAudioAttributes(audio); 
				attrs.setVideoAttributes(video); 
				Encoder encoder = new Encoder();  
				MultimediaObject multimediaObject = new MultimediaObject(source);
				try {
					encoder.encode(multimediaObject, target, attrs);
				} catch (IllegalArgumentException | EncoderException  e) {
					//TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

	/*
	 * input: 0h 0m 35s+860ms 
	 * output: 3000ms
	 */
	public static int convertTimeToInteger(String timmer) {
		int time = 0;
		try {
			String[] timeList = timmer.split(" ");
			int hours = Integer.parseInt(timeList[0].replace("h", ""));
			int minues = Integer.parseInt(timeList[1].replace("m", ""));
			String[] secondAndMilisecond = timeList[2].split("\\+");
			int second = Integer.parseInt(secondAndMilisecond[0].replace("s", ""));
			int milisecond = Integer.parseInt(secondAndMilisecond[1].replace("ms", ""));
			time = milisecond + (second * 1000) + (minues * 60000) + (hours * 3600000);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return time;
	}
}
