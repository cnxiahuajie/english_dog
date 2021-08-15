package net.xiahuajie.english_dog.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

public final class JsonUtil {

	public static String readJsonFile(String filename) {
		StringBuilder sb = new StringBuilder();
		File jsonFile = new File(filename);
		try (FileReader fr = new FileReader(jsonFile);
				Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");) {
			int ch = 0;

			while ((ch = reader.read()) != -1) {
				sb.append((char) ch);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
