package helpers;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.Properties;
import java.util.stream.Stream;

public class InputProvider {

	private final int year;
	private final int day;
	private InputProvider(int year, int day) {
		this.year = year;
		this.day = day;
	}

	public static Stream<String> getInputFor(int year, int day) throws IOException {
		if(day == 0) {
			throw new RuntimeException("change day from 0 to actual day");
		}
		return new InputProvider(year, day).getInput();
	}

	private Stream<String> getInput() throws IOException {
		File file = new File(getFilename());
		if(!file.exists()) {
			getAndSaveFile();
		}
		return Files.lines(file.toPath());
	}

	private void getAndSaveFile() {
		try {
			// set up connection
			URL url = new URL("https://adventofcode.com/"+ year + "/day/" + day+ "/input");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Cookie", "session=" + getSession() );
			con.setRequestMethod("GET");
			String responseMessage = con.getResponseMessage();
			// read response
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
				content.append("\n");
			}
			in.close();
			con.disconnect();
			writeToFile(content);
		} catch (Exception e) {
			System.out.println("Check if the session header value is still correct. This might change after a new login.");
			e.printStackTrace();
		}
	}

	private void writeToFile(StringBuffer content) {
		File file = createFile();
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(content.toString());
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private File createFile() {
		try {
			File myObj = new File(getFilename());
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
			return myObj;
		} catch (IOException e) {
			System.out.println("An error occurred creating the file.");
			e.printStackTrace();
		}
		return null;
	}

	private String getSession() {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = rootPath + "application.properties";

		Properties appProps = new Properties();
		try (FileInputStream inputStream = new FileInputStream(appConfigPath)) {
			appProps.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return appProps.getProperty("session");
	}

	private String getFilename() {
		return "src/main/resources/"+ year+ "-"+ day +".txt";
	}
}