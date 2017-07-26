package br.com.vpgdev.acadebirlwebservice.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {
	/**
	 * dateToString - Método que converte uma data para String. Retorna uma String no formato dd/mm/aaaa
	 * @param date
	 * @return String
	 */
	public String dateToString(Calendar date) {
		String stringDate = new SimpleDateFormat("dd/MM/yyyy").format(date.getTime());
		return stringDate;
	}
	
	/**
	 * stringToDate - Método que converte uma String para data. Aceita o format dd/mm/aaaa
	 * @param date
	 * @return Calendar
	 */
	public Calendar stringToDate(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(date));
			return cal;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
