package br.com.thing.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class FileManager {

	private static String filesPath;

	static {

		try {

			filesPath = System.getProperty("wf.files.path");
			if (filesPath == null || filesPath.isEmpty()) {

				InputStream is = FileManager.fileInputStreamFromClasspath("generic_internal.properties");
				if (is != null) {
					System.getProperties().load(is);
					filesPath = System.getProperty("wf.files.path", "");
				}
			}

		} catch (IOException e) {
			// WSLog.ERROR("core", "Queries Can not read file properties | IOException >>
			// Error :" + e.getMessage(), e);
		}
	}

	public static Properties propertiesFromClasspath(String filename) throws IOException {
		InputStream file = classloader().getResourceAsStream(filename);
		Properties props = new Properties();
		props.load(file);
		return props;
	}

	public static ClassLoader classloader() {
		return Thread.currentThread().getContextClassLoader();
	}

	public static File fileFromClasspath(String fileName) {
		URL resource = classloader().getResource(fileName);
		return resource == null ? null : new File(resource.getFile());
	}

	public static Properties readPropertiesFile(String fileName) {
		Properties prop = null;
		try {
			prop = new Properties();
			prop.load(fileInputStream(filesPath + fileName));
		} catch (IOException e) {
			// WSLog.ERROR("core",
			// "FileManager[readPropertiesFile] Can not read file properties | IOException
			// >> Error :" + e, e);
		}
		return prop;
	}

	public static Properties readPropertiesFile(FileInputStream fileInputStream) {
		Properties prop = null;
		try {
			prop = new Properties();
			prop.load(fileInputStream);

			fileInputStream.close();
		} catch (IOException e) {
			// WSLog.ERROR("core",
			// "FileManager[readPropertiesFile] Can not read file properties | IOException
			// >> Error :" + e, e);
		}
		return prop;
	}

	public static InputStream fileInputStreamFromClasspath(String fileName) {
		URL resource = classloader().getResource(fileName);
		InputStream inputStream = null;
		if (resource != null) {
			try {
				inputStream = resource.openStream();
			} catch (IOException e) {
				// WSLog.ERROR("core",
				// "FileManager[fileInputStreamFromClasspath] Can not read file properties |
				// IOException >> Error :"
				// + e.getMessage(),
				// e);
			}
		}
		return inputStream;
	}

	public static InputStream fileInputStream(String fileName) {
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			// WSLog.ERROR("core",
			// "FileManager[fileInputStream] Can not read file properties |
			// FileNotFoundException >> Error :"
			// + e.getMessage(),
			// e);
		}
		return inputStream;
	}

	public static File file(String fileName) {
		File file = new File(filesPath + fileName);
		return file;
	}

}