package utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

public class EstraiDataOrario {
	
	public String estraiData() {
		LocalDateTime now = LocalDateTime.now();
		String anno = String.valueOf(now.getYear());
		String mese = String.valueOf(now.getMonthValue());
		String giorno = String.valueOf(now.getDayOfMonth());
		String ore = String.valueOf(now.getHour());
		String minuti = String.valueOf(now.getMinute());
		String secondi = String.valueOf(now.getSecond());
		String millisecondi = String.valueOf(now.get(ChronoField.MILLI_OF_SECOND));
		String data = giorno + "-" + mese + "-" + anno + "_" + ore + "h" + minuti + "m" + secondi + "s" + millisecondi + "ms";
		return data;
	}
	
}
