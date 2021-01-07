package com.google.zxing.client.result;

import java.util.regex.Pattern;

public final class GeoResultParser extends ResultParser
{
  private static final Pattern GEO_URL_PATTERN = Pattern.compile("geo:([\\-0-9.]+),([\\-0-9.]+)(?:,([\\-0-9.]+))?(?:\\?(.*))?", 2);

  // ERROR //
  public GeoParsedResult parse(com.google.zxing.Result paramResult)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 29	com/google/zxing/client/result/GeoResultParser:getMassagedText	(Lcom/google/zxing/Result;)Ljava/lang/String;
    //   4: astore_2
    //   5: getstatic 18	com/google/zxing/client/result/GeoResultParser:GEO_URL_PATTERN	Ljava/util/regex/Pattern;
    //   8: aload_2
    //   9: invokevirtual 33	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   12: astore_3
    //   13: aload_3
    //   14: invokevirtual 39	java/util/regex/Matcher:matches	()Z
    //   17: ifne +5 -> 22
    //   20: aconst_null
    //   21: areturn
    //   22: aload_3
    //   23: iconst_4
    //   24: invokevirtual 43	java/util/regex/Matcher:group	(I)Ljava/lang/String;
    //   27: astore 4
    //   29: aload_3
    //   30: iconst_1
    //   31: invokevirtual 43	java/util/regex/Matcher:group	(I)Ljava/lang/String;
    //   34: invokestatic 49	java/lang/Double:parseDouble	(Ljava/lang/String;)D
    //   37: dstore 5
    //   39: dload 5
    //   41: ldc2_w 50
    //   44: dcmpl
    //   45: ifgt +110 -> 155
    //   48: dload 5
    //   50: ldc2_w 52
    //   53: dcmpg
    //   54: ifge +6 -> 60
    //   57: goto +98 -> 155
    //   60: aload_3
    //   61: iconst_2
    //   62: invokevirtual 43	java/util/regex/Matcher:group	(I)Ljava/lang/String;
    //   65: invokestatic 49	java/lang/Double:parseDouble	(Ljava/lang/String;)D
    //   68: dstore 7
    //   70: dload 7
    //   72: ldc2_w 54
    //   75: dcmpl
    //   76: ifgt +77 -> 153
    //   79: dload 7
    //   81: ldc2_w 56
    //   84: dcmpg
    //   85: ifge +6 -> 91
    //   88: goto +65 -> 153
    //   91: aload_3
    //   92: iconst_3
    //   93: invokevirtual 43	java/util/regex/Matcher:group	(I)Ljava/lang/String;
    //   96: astore 9
    //   98: dconst_0
    //   99: dstore 10
    //   101: aload 9
    //   103: ifnonnull +6 -> 109
    //   106: goto +27 -> 133
    //   109: aload_3
    //   110: iconst_3
    //   111: invokevirtual 43	java/util/regex/Matcher:group	(I)Ljava/lang/String;
    //   114: invokestatic 49	java/lang/Double:parseDouble	(Ljava/lang/String;)D
    //   117: dstore 12
    //   119: dload 12
    //   121: dload 10
    //   123: dcmpg
    //   124: ifge +5 -> 129
    //   127: aconst_null
    //   128: areturn
    //   129: dload 12
    //   131: dstore 10
    //   133: new 59	com/google/zxing/client/result/GeoParsedResult
    //   136: dup
    //   137: dload 5
    //   139: dload 7
    //   141: dload 10
    //   143: aload 4
    //   145: invokespecial 62	com/google/zxing/client/result/GeoParsedResult:<init>	(DDDLjava/lang/String;)V
    //   148: astore 14
    //   150: aload 14
    //   152: areturn
    //   153: aconst_null
    //   154: areturn
    //   155: aconst_null
    //   156: areturn
    //   157: aconst_null
    //   158: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   29	119	157	java/lang/NumberFormatException
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.GeoResultParser
 * JD-Core Version:    0.6.1
 */