package com.google.zxing.client.result;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CalendarParsedResult extends ParsedResult
{
  private static final DateFormat DATE_FORMAT;
  private static final Pattern DATE_TIME;
  private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyyMMdd'T'HHmmss", Locale.ENGLISH);
  private static final Pattern RFC2445_DURATION = Pattern.compile("P(?:(\\d+)W)?(?:(\\d+)D)?(?:T(?:(\\d+)H)?(?:(\\d+)M)?(?:(\\d+)S)?)?");
  private static final long[] RFC2445_DURATION_FIELD_UNITS = { 604800000L, 86400000L, 3600000L, 60000L, 1000L };
  private final String[] attendees;
  private final String description;
  private final Date end;
  private final boolean endAllDay;
  private final double latitude;
  private final String location;
  private final double longitude;
  private final String organizer;
  private final Date start;
  private final boolean startAllDay;
  private final String summary;

  static
  {
    DATE_TIME = Pattern.compile("[0-9]{8}(T[0-9]{6}Z?)?");
    DATE_FORMAT = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
    DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
  }

  public CalendarParsedResult(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String[] paramArrayOfString, String paramString7, double paramDouble1, double paramDouble2)
  {
    super(ParsedResultType.CALENDAR);
    this.summary = paramString1;
    try
    {
      this.start = parseDate(paramString2);
      if (paramString3 == null)
      {
        long l = parseDurationMS(paramString4);
        Date localDate;
        if (l < 0L)
          localDate = null;
        else
          localDate = new Date(l + this.start.getTime());
        this.end = localDate;
      }
      try
      {
        this.end = parseDate(paramString3);
        boolean bool1;
        if (paramString2.length() == 8)
          bool1 = true;
        else
          bool1 = false;
        this.startAllDay = bool1;
        boolean bool2 = false;
        if (paramString3 != null)
        {
          int i = paramString3.length();
          bool2 = false;
          if (i == 8)
            bool2 = true;
        }
        this.endAllDay = bool2;
        this.location = paramString5;
        this.organizer = paramString6;
        this.attendees = paramArrayOfString;
        this.description = paramString7;
        this.latitude = paramDouble1;
        this.longitude = paramDouble2;
        return;
      }
      catch (ParseException localParseException2)
      {
        throw new IllegalArgumentException(localParseException2.toString());
      }
    }
    catch (ParseException localParseException1)
    {
      throw new IllegalArgumentException(localParseException1.toString());
    }
  }

  private static String format(boolean paramBoolean, Date paramDate)
  {
    if (paramDate == null)
      return null;
    DateFormat localDateFormat;
    if (paramBoolean)
      localDateFormat = DateFormat.getDateInstance(2);
    else
      localDateFormat = DateFormat.getDateTimeInstance(2, 2);
    return localDateFormat.format(paramDate);
  }

  private static Date parseDate(String paramString)
  {
    if (!DATE_TIME.matcher(paramString).matches())
      throw new ParseException(paramString, 0);
    if (paramString.length() == 8)
      return DATE_FORMAT.parse(paramString);
    Date localDate1;
    if ((paramString.length() == 16) && (paramString.charAt(15) == 'Z'))
    {
      Date localDate2 = DATE_TIME_FORMAT.parse(paramString.substring(0, 15));
      GregorianCalendar localGregorianCalendar = new GregorianCalendar();
      long l = localDate2.getTime() + localGregorianCalendar.get(15);
      localGregorianCalendar.setTime(new Date(l));
      localDate1 = new Date(l + localGregorianCalendar.get(16));
    }
    else
    {
      localDate1 = DATE_TIME_FORMAT.parse(paramString);
    }
    return localDate1;
  }

  private static long parseDurationMS(CharSequence paramCharSequence)
  {
    if (paramCharSequence == null)
      return -1L;
    Matcher localMatcher = RFC2445_DURATION.matcher(paramCharSequence);
    if (!localMatcher.matches())
      return -1L;
    long l = 0L;
    int j;
    for (int i = 0; i < RFC2445_DURATION_FIELD_UNITS.length; i = j)
    {
      j = i + 1;
      String str = localMatcher.group(j);
      if (str != null)
        l += RFC2445_DURATION_FIELD_UNITS[i] * Integer.parseInt(str);
    }
    return l;
  }

  public String[] getAttendees()
  {
    return this.attendees;
  }

  public String getDescription()
  {
    return this.description;
  }

  public String getDisplayResult()
  {
    StringBuilder localStringBuilder = new StringBuilder(100);
    maybeAppend(this.summary, localStringBuilder);
    maybeAppend(format(this.startAllDay, this.start), localStringBuilder);
    maybeAppend(format(this.endAllDay, this.end), localStringBuilder);
    maybeAppend(this.location, localStringBuilder);
    maybeAppend(this.organizer, localStringBuilder);
    maybeAppend(this.attendees, localStringBuilder);
    maybeAppend(this.description, localStringBuilder);
    return localStringBuilder.toString();
  }

  public Date getEnd()
  {
    return this.end;
  }

  public double getLatitude()
  {
    return this.latitude;
  }

  public String getLocation()
  {
    return this.location;
  }

  public double getLongitude()
  {
    return this.longitude;
  }

  public String getOrganizer()
  {
    return this.organizer;
  }

  public Date getStart()
  {
    return this.start;
  }

  public String getSummary()
  {
    return this.summary;
  }

  public boolean isEndAllDay()
  {
    return this.endAllDay;
  }

  public boolean isStartAllDay()
  {
    return this.startAllDay;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.CalendarParsedResult
 * JD-Core Version:    0.6.1
 */