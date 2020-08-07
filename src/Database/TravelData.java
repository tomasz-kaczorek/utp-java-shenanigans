package Database;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.*;

public class TravelData
{
	LinkedList<String> records = new LinkedList<>();

	public TravelData(File dataDir) {
		try
		{
			for(File file : dataDir.listFiles())
			{
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
				reader.mark(1);
				if (reader.read() != 0xFEFF) reader.reset();
				for(String line = reader.readLine(); line != null; line = reader.readLine())
				{
					records.add(line);
				}
			}
		}
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
	}

	public List<String> getOffersDescriptionsList(String loc, String dateFormat)
	{
		LinkedList<String> returned = new LinkedList<>();
		for(String record : records)
		{
			try
			{
				returned.add(new Offer(record.split("\t")).toLocalizedString(loc, dateFormat));
			}
			catch (ParseException e) {e.printStackTrace();}
		}
		return returned;
	}
}
