package br.com.thing.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	private static final String KEY_ORDER_FI_FOLDER = "order.fi.folder";
	public static final String ORDER_FI_FOLDER = System.getProperty(KEY_ORDER_FI_FOLDER, "orders/fi");

	private static final String EXT = ".json";

	static {
		File fiFolder1 = new File(ORDER_FI_FOLDER);
		fiFolder1.mkdirs();

	}

	public FileUtils() {

	}

	public static boolean createOrUpdateFile(String filename, String json, String folder, boolean isUpdateFile) {

		try {

			File file = new File(folder + "/" + filename + EXT);

			if (file.exists() && isUpdateFile) {

//				WSLog.INFO("core", "Update file: " + file.getPath() + " json: " + json);

				StringBuilder builder = new StringBuilder();
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				String line = null;
				while ((line = br.readLine()) != null) {
					builder.append(line);
					if (br.ready()) {
						builder.append("\n");
					}
				}
				br.close();
				builder.append("\n");
				builder.append(json);
				String jsonUpdate = builder.toString();

				FileOutputStream fos = new FileOutputStream(file);
				fos.write(jsonUpdate.getBytes("UTF-8"));
				fos.flush();

				fos.close();

			} else {
	//			WSLog.INFO("core", "Create file: " + file.getPath() + " json: " + json);

				FileOutputStream fos = new FileOutputStream(file);
				fos.write(json.getBytes("UTF-8"));
				fos.flush();

				fos.close();
			}

			return file.exists();

		} catch (Throwable t) {
		//	WSLog.ERROR("core", "createOrUpdateFile " + t.getLocalizedMessage(), t);
		}

		return false;
	}

	public static boolean removeFile(String filename, String folder) {

		try {

			File file = new File(folder + "/" + filename + EXT);

			if (file.exists()) {

			//	WSLog.INFO("core", "Remove file: " + file.getPath());

				return file.delete();
			}
		} catch (Throwable t) {
			//WSLog.ERROR("core", "removeFile " + t.getLocalizedMessage(), t);
		}

		return false;
	}

	public static List<String> readFiles(String folder) {

		List<String> list = new ArrayList<String>();
		try {

			File fFolder = new File(folder);

			File[] files = fFolder.listFiles();

			if (files != null && files.length > 0) {
				for (File file : files) {
				//	WSLog.INFO("core", "Read file: " + file.getPath());
					StringBuilder builder = new StringBuilder();
					BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
					String line = null;
					while ((line = br.readLine()) != null) {
						builder.append(line);
						list.add(line);
					}
					String json = builder.toString();
					//WSLog.INFO("core", "Read file: " + file.getPath() + " json: " + json);

					br.close();
				}
			}
		} catch (Throwable t) {
			//WSLog.ERROR("core", "readFiles " + t.getLocalizedMessage(), t);
		}
		return list;
	}

//	public static Properties propertiesFromClasspath(String filename) throws IOException {
//		InputStream file = classloader().getResourceAsStream(filename);
//		Properties props = new Properties();
//		props.load(file);
//		return props;
//	}
//
//	private static ClassLoader classloader() {
//		return Thread.currentThread().getContextClassLoader();
//	}
//
//	public static File file(String fileName) {
//		File file = new File(fileName);	
//		return (file == null || !file.exists() ? null : file);
//	}
//	
//	public static String fileFromClasspath(String fileName) {
//		String file = classloader().getResource(fileName).getFile();
////		File file = new File(classloader().getResource(fileName).getFile());
//		return (file == null ? null : fileName);
//	}
//	
//	public static Properties readPropertiesFile(String fileName) {
//		Properties prop = null;
//		try {
//			prop = new Properties();
//			prop.load(classloader().getResourceAsStream(fileName));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return prop;
//	}

	public static void main(String[] args) {
		FileUtils.createOrUpdateFile("teste_arquivo",
				"inserindo escrita no arquivo gerado",
				FileUtils.ORDER_FI_FOLDER, false);

		FileUtils.readFiles(FileUtils.ORDER_FI_FOLDER);
	}
}
