package utils;

import java.util.UUID;

public class randomName {
	public static String generateValue () {
		UUID randomUUID = UUID.randomUUID();
		return randomUUID.toString();
	}

	public static String pathImg (String className) {
		String pathfileIMG = contains.folderImgReport + "Img_" + className + "_" + UUID.randomUUID().toString() + ".jpg";
		return pathfileIMG;
	}
	
	public static String pathVideoAvi (String className) {
		String pathfileVideo = contains.folderVideoReport + className + ".avi";
		return pathfileVideo;
	}
	
	public static String VideoAvi (String className) {
		String fileVideo = "Video_Avi_" + className + "_" + UUID.randomUUID().toString();
		return fileVideo;
	}
	
	public static String pathVideoMp4 (String className) {
		String pathfileVideo = contains.folderVideoReport + "Video_Mp4_" + className + "_" + UUID.randomUUID().toString() + ".mp4";
		return pathfileVideo;
	}
	
	public static String pathImgEvidence () {
		String pathfileIMG = contains.folderImgReport + UUID.randomUUID().toString() + ".jpg";
		return pathfileIMG;
	}
	public static String pathVideoEvidence () {
		String pathfileIMG = contains.folderVideoReport + UUID.randomUUID().toString() + ".avi";
		return pathfileIMG;
	}
}
