package FileVisitor1;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Futil
{
	public static void processDir(String dirName, String resultFileName)
	{
		try
		{
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resultFileName), "UTF-8"));
			Files.walkFileTree
			(
				Paths.get(dirName),
				new SimpleFileVisitor<Path>()
				{
					@Override
					public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
					{
						if(append(writer, file.toString(), "Cp1250"))
							return FileVisitResult.CONTINUE;
						else
							return FileVisitResult.TERMINATE;
					}
				}
			);
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
