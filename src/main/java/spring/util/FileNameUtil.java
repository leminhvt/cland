package spring.util;

import org.apache.commons.io.FilenameUtils;

public class FileNameUtil {
	public static String rename(String filename) {
		return FilenameUtils.getBaseName(filename) + "-" + System.nanoTime() + "."
				+ FilenameUtils.getExtension(filename);
	}
}
