package Sample;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Testsample {

	public static void main(String[] args) {
		LocalDate a = LocalDate.of(2010,8,19);
		LocalDate b = LocalDate.of(2010,8,20);
		LocalDate c = b.with(DayOfWeek.MONDAY);
System.out.println(a.equals(c) + "," + a.isBefore(c));

	}

}
