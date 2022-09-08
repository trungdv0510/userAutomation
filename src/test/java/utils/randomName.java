package utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class randomName {
	public static String generateValue () {
		String randomUUID = RandomStringUtils.randomAlphabetic(20);
		return randomUUID;
	}

	public static String pathImg (String className) {
		return contains.folderImgReport + "Img_" + className + "_" + UUID.randomUUID() + ".jpg";
	}
	
	public static String pathVideoAvi (String className) {
		return contains.folderVideoReport + className + ".avi";
	}
	
	public static String VideoAvi (String className) {
		return "Video_Avi_" + className + "_" + UUID.randomUUID();
	}
	
	public static String pathVideoMp4 (String className) {
		return contains.folderVideoReport + "Video_Mp4_" + className + "_" + UUID.randomUUID() + ".mp4";
	}
	
	public static String pathImgEvidence () {
		return contains.folderImgReport + UUID.randomUUID() + ".jpg";

	}
	public static String pathVideoEvidence () {
		return contains.folderVideoReport + UUID.randomUUID() + ".avi";
	}
}
