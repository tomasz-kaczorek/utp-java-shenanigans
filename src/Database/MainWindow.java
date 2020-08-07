package Database;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainWindow extends JFrame
{
	final Locale[] locales = {
		new Locale("pl", "PL"),
		new Locale("en", "GB")};
	final String[] dateFormatPatterns = {
		"dd-MM-yyyy",
		"MM-dd-yyyy",
		"yyyy-MM-dd"};
	JLabel localeLabel = new JLabel();
	JComboBox<Locale> localeComboBox = new JComboBox<>(locales);
	JLabel dateFormatLabel = new JLabel();
	JComboBox<String> dateFormatComboBox = new JComboBox<>(dateFormatPatterns);
	OffersTableModel offersTableModel;
	JTable offersTable = new JTable();

	MainWindow(ArrayList<Offer> offers)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());

		offersTableModel = new OffersTableModel(getSelectedLocale(), getSelectedDateFormat(), offers);
		offersTable.setModel(offersTableModel);

		localeLabel.setText(ResourceBundle.getBundle("Database.HeadersBundle", getSelectedLocale()).getString("locale"));
		localeComboBox.addActionListener(e -> {
				offersTableModel.setLocale(getSelectedLocale());
				localeLabel.setText(ResourceBundle.getBundle("Database.HeadersBundle", getSelectedLocale()).getString("locale"));
				dateFormatLabel.setText(ResourceBundle.getBundle("Database.HeadersBundle", getSelectedLocale()).getString("date_format"));});
		dateFormatLabel.setText(ResourceBundle.getBundle("Database.HeadersBundle", getSelectedLocale()).getString("date_format"));
		dateFormatComboBox.addActionListener(e -> offersTableModel.setDateFormat(getSelectedDateFormat()));

		add(localeLabel, new GridBagConstraints(0, 0, 1, 1, 1., 1., GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
		add(localeComboBox, new GridBagConstraints(1, 0, 1, 1, 1., 1., GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
		add(dateFormatLabel, new GridBagConstraints(2, 0, 1, 1, 1., 1., GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
		add(dateFormatComboBox, new GridBagConstraints(3, 0, 1, 1, 1., 1., GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
		add(new JScrollPane(offersTable), new GridBagConstraints(0, 1, 4, 1, 1., 1., GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));

		pack();
		setVisible(true);
	}

	Locale getSelectedLocale()
	{
		return (Locale) localeComboBox.getSelectedItem();
	}

	SimpleDateFormat getSelectedDateFormat()
	{
		return new SimpleDateFormat((String) dateFormatComboBox.getSelectedItem());
	}
}
