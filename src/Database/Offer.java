package Database;

import org.w3c.dom.CDATASection;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class Offer
{
	Locale locale;
	Locale country;
	Date beginning;
	Date ending;
	String location;
	Double price;
	Currency currency;

	Offer(String[] tokens) throws ParseException
	{
		locale = localeFromString(tokens[0]);
		country = countryFromString(tokens[1]);
		beginning = new SimpleDateFormat("yyyy-MM-dd").parse(tokens[2]);
		ending = new SimpleDateFormat("yyyy-MM-dd").parse(tokens[3]);
		location = locationFromString(tokens[4]);
		price = NumberFormat.getInstance(locale).parse(tokens[5]).doubleValue();
		currency = Currency.getInstance(tokens[6]);
	}

	String getCountry(Locale locale)
	{
		return country.getDisplayCountry(locale);
	}

	String getBeginning(SimpleDateFormat dateFormat)
	{
		return dateFormat.format(beginning);
	}

	String getEnding(SimpleDateFormat dateFormat)
	{
		return dateFormat.format(ending);
	}

	String getLocation(Locale locale)
	{
		return ResourceBundle.getBundle("Database.LocationsBundle", locale).getString(location);
	}

	String getPrice(Locale locale)
	{
		return NumberFormat.getInstance(locale).format(price);
	}

	String getCurrency()
	{
		return currency.toString();
	}

	Locale localeFromString(String locale)
	{
		return new Locale(locale.substring(0, 2), locale.substring(locale.length() - 2));
	}

	Locale countryFromString(String country)
	{
		for(Locale locale : Locale.getAvailableLocales())
		{
			if(locale.getDisplayCountry(this.locale).equals(country)) return locale;
		}
		return null;
	}

	String locationFromString(String location)
	{
		ResourceBundle locations = ResourceBundle.getBundle("Database.LocationsBundle", locale);
		Enumeration<String> keys = locations.getKeys();
		while(keys.hasMoreElements())
		{
			String key = keys.nextElement();
			if(locations.getString(key).equals(location)) return key;
		}
		return null;
	}

	public String toLocalizedString(String locale, String dateFormat)
	{
		Locale outputLocale = localeFromString(locale);
		SimpleDateFormat outputDateFormat = new SimpleDateFormat(dateFormat);
		return getCountry(outputLocale) + " " +
			getBeginning(outputDateFormat) + " " +
			getEnding(outputDateFormat) + " " +
			getLocation(outputLocale) + " " +
			getPrice(outputLocale) + " " +
			getCurrency();
	}

}