package org.apache.commons.httpclient.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

public class DateParser
{
  private static final Collection DEFAULT_PATTERNS = Arrays.asList(new String[] { "EEE MMM d HH:mm:ss yyyy", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE, dd MMM yyyy HH:mm:ss zzz" });
  public static final String PATTERN_ASCTIME = "EEE MMM d HH:mm:ss yyyy";
  public static final String PATTERN_RFC1036 = "EEEE, dd-MMM-yy HH:mm:ss zzz";
  public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";

  public static Date parseDate(String paramString)
  {
    return parseDate(paramString, null);
  }

  // ERROR //
  public static Date parseDate(String paramString, Collection paramCollection)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +13 -> 14
    //   4: new 40	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 42
    //   10: invokespecial 45	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_1
    //   15: ifnonnull +7 -> 22
    //   18: getstatic 28	org/apache/commons/httpclient/util/DateParser:DEFAULT_PATTERNS	Ljava/util/Collection;
    //   21: astore_1
    //   22: aload_0
    //   23: invokevirtual 49	java/lang/String:length	()I
    //   26: iconst_1
    //   27: if_icmple +33 -> 60
    //   30: aload_0
    //   31: ldc 51
    //   33: invokevirtual 55	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   36: ifeq +24 -> 60
    //   39: aload_0
    //   40: ldc 51
    //   42: invokevirtual 58	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   45: ifeq +15 -> 60
    //   48: aload_0
    //   49: iconst_1
    //   50: aload_0
    //   51: invokevirtual 49	java/lang/String:length	()I
    //   54: iconst_1
    //   55: isub
    //   56: invokevirtual 62	java/lang/String:substring	(II)Ljava/lang/String;
    //   59: astore_0
    //   60: aload_1
    //   61: invokeinterface 68 1 0
    //   66: astore_2
    //   67: aconst_null
    //   68: astore_3
    //   69: goto +4 -> 73
    //   72: pop
    //   73: aload_2
    //   74: invokeinterface 74 1 0
    //   79: ifeq +59 -> 138
    //   82: aload_2
    //   83: invokeinterface 78 1 0
    //   88: checkcast 20	java/lang/String
    //   91: astore 7
    //   93: aload_3
    //   94: ifnonnull +28 -> 122
    //   97: new 80	java/text/SimpleDateFormat
    //   100: dup
    //   101: aload 7
    //   103: getstatic 86	java/util/Locale:US	Ljava/util/Locale;
    //   106: invokespecial 89	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   109: astore_3
    //   110: aload_3
    //   111: ldc 91
    //   113: invokestatic 97	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   116: invokevirtual 101	java/text/SimpleDateFormat:setTimeZone	(Ljava/util/TimeZone;)V
    //   119: goto +9 -> 128
    //   122: aload_3
    //   123: aload 7
    //   125: invokevirtual 104	java/text/SimpleDateFormat:applyPattern	(Ljava/lang/String;)V
    //   128: aload_3
    //   129: aload_0
    //   130: invokevirtual 107	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   133: astore 8
    //   135: aload 8
    //   137: areturn
    //   138: new 109	java/lang/StringBuffer
    //   141: dup
    //   142: invokespecial 110	java/lang/StringBuffer:<init>	()V
    //   145: astore 4
    //   147: aload 4
    //   149: ldc 112
    //   151: invokevirtual 116	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   154: pop
    //   155: aload 4
    //   157: aload_0
    //   158: invokevirtual 116	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   161: pop
    //   162: new 118	org/apache/commons/httpclient/util/DateParseException
    //   165: dup
    //   166: aload 4
    //   168: invokevirtual 122	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   171: invokespecial 123	org/apache/commons/httpclient/util/DateParseException:<init>	(Ljava/lang/String;)V
    //   174: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   128	135	72	java/text/ParseException
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.util.DateParser
 * JD-Core Version:    0.6.1
 */