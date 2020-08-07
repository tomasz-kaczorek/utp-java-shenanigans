package FileVisitor2;

import java.io.*;
import java.nio.file.*;

public class Futil
{
	public static void processDir(String dirName, String resultFileName)
	{
		try
		{
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resultFileName), "UTF-8"));
			Files.walk(Paths.get(dirName), FileVisitOption.FOLLOW_LINKS)
				.filter(e -> Files.isRegularFile(e))
				.forEach(e -> Futil.append(writer, e.toString(), "Cp1250"));
			writer.close();
		}
		catch (IOException e) {e.printStackTrace();}
	}

	static boolean append(BufferedWriter writer, String fileName, String charsetName)
	{
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), charsetName)))
		{
			for(int c = reader.read(); c != -1; c = reader.read()) writer.write(c);
			return true;
		}
		catch (UnsupportedEncodingException e) {e.printStackTrace();}
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		return false;
	}
}
