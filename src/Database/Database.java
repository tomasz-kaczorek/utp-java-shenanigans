package Database;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

public class Database
{
	private Connection connection;
	private TravelData travelData;
	private static String driverName = "org.apache.derby.jdbc.EmbeddedDriver";
	private String url;
	private Integer id = 1;

	public Database(String url, TravelData travelData)
	{
		this.url = url;
		this.travelData = travelData;
	}

	public void create() {
		try
		{
			Class.forName(driverName);
			connection = DriverManager.getConnection(url);

			DatabaseMetaData databaseMetadata = connection.getMetaData();
			ResultSet resultSet = databaseMetadata.getTables(null, null, "OFFERS", null);
			if (resultSet.next())
			{
				connection.createStatement().execute("DROP TABLE Offers");
			}
			connection.createStatement().execute("CREATE TABLE Offers("
				+ "id int PRIMARY KEY, "
				+ "locale varchar(5), "
				+ "country varchar(40), "
				+ "beginning Date, "
				+ "ending Date, "
				+ "location varchar(20), "
				+ "price varchar(20), "
				+ "currency varchar(3))"
			);

			PreparedStatement statement = connection.prepareStatement("INSERT INTO Offers VALUES(?,?,?,?,?,?,?,?)");
			for (String record : travelData.records)
			{
				String[] tokens = record.split("\\t");

				statement.setInt(1, id++);
				statement.setString(2, tokens[0]);
				statement.setString(3, tokens[1]);
				statement.setString(4, tokens[2]);
				statement.setString(5, tokens[3]);
				statement.setString(6, tokens[4]);
				statement.setString(7, tokens[5]);
				statement.setString(8, tokens[6]);
				statement.executeUpdate();
			}
		}
		catch (SQLException e) {e.printStackTrace();}
		catch (ClassNotFoundException e) {e.printStackTrace();}
	}

	public void showGui()
	{
		ArrayList<Offer> offers = new ArrayList<>();
		try
		{
			ResultSet table = connection.createStatement().executeQuery("SELECT * FROM Offers");
			while(table.next())
			{
				String[] tokens = new String[7];
				for(int i = 0; i < 7; ++i)
				{
					tokens[i] = table.getString(i + 2);
				}
				offers.add(new Offer(tokens));
			}
		}
		catch (SQLException e) {e.printStackTrace();}
		catch (ParseException e) {e.printStackTrace();}
		new MainWindow(offers);
	}
}
