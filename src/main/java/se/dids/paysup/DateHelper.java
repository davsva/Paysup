/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.dids.paysup;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author david
 */
public class DateHelper {

  public static String toIso8601(Date d) {
    Calendar c = GregorianCalendar.getInstance();
    c.setTime(d);
    return DatatypeConverter.printDateTime(c);
  }

  public static Date toDate(String s) {
    return DatatypeConverter.parseDateTime(s).getTime();
  }

}
