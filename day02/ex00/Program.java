package ex00;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Program {
    public static void main(String[] args) throws IOException
	{
		Map<String, String> signaturesMap = new HashMap<>();
		FileInputStream fileInputStream = new FileInputStream("ex00/signatures.txt");
		String key = "";
		String value = "";
		int	c;
		boolean keyOrValue = false;

		while ((c = fileInputStream.read()) != -1) {
			if (!keyOrValue) {
				if (c != '\n' && c != ',') {
					value += (char)c;
				}
			}
			else {
				if (c != '\n' && c != ',')
					key += (char)c;
			}
			if (c == ',') {
				keyOrValue = true;
				continue;
			}
			if (c == '\n') {
				signaturesMap.put(key, value);
				key = "";
				value = "";
				keyOrValue = false;
			}
		}
		if (keyOrValue)
			signaturesMap.put(key, value);
		fileInputStream.close();

		List<String> list = new LinkedList<>();
		Scanner scanner = new Scanner(System.in);
		String input = "";
		String output;

		while (!(input = scanner.nextLine()).equals("42")) {
			try {
				output = spotSignature(input, signaturesMap);
				if (output == null) {
					System.out.println("UNDEFINED");
				}
				else {
					System.out.println("PROCESSED");
					list.add(output);
				}
			}
			catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}
		}
		scanner.close();

		FileOutputStream fileOutputStream = new FileOutputStream("result.txt", false);
		byte[] bytes;

		for (String s : list) {
			bytes = s.getBytes(StandardCharsets.UTF_8);
			fileOutputStream.write(bytes);
			fileOutputStream.write('\n');
		}
		fileOutputStream.close();
    }

	private static String spotSignature(String input, Map<String, String> signaturesMap) throws IOException {
		try (FileInputStream fileInputStream = new FileInputStream(input)) {
			int i = 0;
			int	symbols = 0;
			String result = "";
			String find = null;

			while ((i = fileInputStream.read()) == -1 || symbols <= 12) {
				result += String.format(" %02X", i);
				find = signaturesMap.get(result);
				if (find != null) {
					break;
				}
				symbols++;
			}
			return find;
		}
		catch (FileNotFoundException ex) {
			System.out.println("File not found.");
			return null;
		}
		catch (IOException e) {
			System.out.println("I/O error.");
			return null;
		}
	}
}