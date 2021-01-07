package org.apache.commons.httpclient.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil
{
  private static final Collection DEFAULT_PATTERNS = Arrays.asList(new String[] { "EEE MMM d HH:mm:ss yyyy", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE, dd MMM yyyy HH:mm:ss zzz" });
  private static final Date DEFAULT_TWO_DIGIT_YEAR_START = localCalendar.getTime();
  private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
  public static final String PATTERN_ASCTIME = "EEE MMM d HH:mm:ss yyyy";
  public static final String PATTERN_RFC1036 = "EEEE, dd-MMM-yy HH:mm:ss zzz";
  public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";

  static
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(2000, 0, 1, 0, 0);
  }

  public static String formatDate(Date paramDate)
  {
    return formatDate(paramDate, "EEE, dd MMM yyyy HH:mm:ss zzz");
  }

  public static String formatDate(Date paramDate, String paramString)
  {
    if (paramDate == null)
      throw new IllegalArgumentException("date is null");
    if (paramString == null)
      throw new IllegalArgumentException("pattern is null");
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString, Locale.US);
    localSimpleDateFormat.setTimeZone(GMT);
    return localSimpleDateFormat.format(paramDate);
  }

  public static Date parseDate(String paramString)
  {
    return parseDate(paramString, null, null);
  }

  public static Date parseDate(String paramString, Collection paramCollection)
  {
    return parseDate(paramString, paramCollection, null);
  }

  // ERROR //
  public static Date parseDate(String paramString, Collection paramCollection, Date paramDate)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +13 -> 14
    //   4: new 67	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 102
    //   10: invokespecial 72	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_1
    //   15: ifnonnull +7 -> 22
    //   18: getstatic 32	org/apache/commons/httpclient/util/DateUtil:DEFAULT_PATTERNS	Ljava/util/Collection;
    //   21: astore_1
    //   22: aload_2
    //   23: ifnonnull +7 -> 30
    //   26: getstatic 48	org/apache/commons/httpclient/util/DateUtil:DEFAULT_TWO_DIGIT_YEAR_START	Ljava/util/Date;
    //   29: astore_2
    //   30: aload_0
    //   31: invokevirtual 106	java/lang/String:length	()I
    //   34: iconst_1
    //   35: if_icmple +33 -> 68
    //   38: aload_0
    //   39: ldc 108
    //   41: invokevirtual 112	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   44: ifeq +24 -> 68
    //   47: aload_0
    //   48: ldc 108
    //   50: invokevirtual 115	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   53: ifeq +15 -> 68
    //   56: aload_0
    //   57: iconst_1
    //   58: aload_0
    //   59: invokevirtual 106	java/lang/String:length	()I
    //   62: iconst_1
    //   63: isub
    //   64: invokevirtual 119	java/lang/String:substring	(II)Ljava/lang/String;
    //   67: astore_0
    //   68: aload_1
    //   69: invokeinterface 125 1 0
    //   74: astore_3
    //   75: aconst_null
    //   76: astore 4
    //   78: goto +4 -> 82
    //   81: pop
    //   82: aload_3
    //   83: invokeinterface 131 1 0
    //   88: ifeq +70 -> 158
    //   91: aload_3
    //   92: invokeinterface 135 1 0
    //   97: checkcast 24	java/lang/String
    //   100: astore 8
    //   102: aload 4
    //   104: ifnonnull +36 -> 140
    //   107: new 76	java/text/SimpleDateFormat
    //   110: dup
    //   111: aload 8
    //   113: getstatic 82	java/util/Locale:US	Ljava/util/Locale;
    //   116: invokespecial 85	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   119: astore 4
    //   121: aload 4
    //   123: ldc 49
    //   125: invokestatic 55	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   128: invokevirtual 89	java/text/SimpleDateFormat:setTimeZone	(Ljava/util/TimeZone;)V
    //   131: aload 4
    //   133: aload_2
    //   134: invokevirtual 139	java/text/SimpleDateFormat:set2DigitYearStart	(Ljava/util/Date;)V
    //   137: goto +10 -> 147
    //   140: aload 4
    //   142: aload 8
    //   144: invokevirtual 142	java/text/SimpleDateFormat:applyPattern	(Ljava/lang/String;)V
    //   147: aload 4
    //   149: aload_0
    //   150: invokevirtual 145	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   153: astore 9
    //   155: aload 9
    //   157: areturn
    //   158: new 147	java/lang/StringBuffer
    //   161: dup
    //   162: invokespecial 148	java/lang/StringBuffer:<init>	()V
    //   165: astore 5
    //   167: aload 5
    //   169: ldc 150
    //   171: invokevirtual 154	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   174: pop
    //   175: aload 5
    //   177: aload_0
    //   178: invokevirtual 154	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   181: pop
    //   182: new 156	org/apache/commons/httpclient/util/DateParseException
    //   185: dup
    //   186: aload 5
    //   188: invokevirtual 160	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   191: invokespecial 161	org/apache/commons/httpclient/util/DateParseException:<init>	(Ljava/lang/String;)V
    //   194: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   147	155	81	java/text/ParseException
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.util.DateUtil
 * JD-Core Version:    0.6.1
 */