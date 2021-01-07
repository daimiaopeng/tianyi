package com.google.zxing.client.result;

import java.util.List;

public final class VEventResultParser extends ResultParser
{
  private static String matchSingleVCardPrefixedField(CharSequence paramCharSequence, String paramString, boolean paramBoolean)
  {
    List localList = VCardResultParser.matchSingleVCardPrefixedField(paramCharSequence, paramString, paramBoolean, false);
    String str;
    if ((localList != null) && (!localList.isEmpty()))
      str = (String)localList.get(0);
    else
      str = null;
    return str;
  }

  private static String[] matchVCardPrefixedField(CharSequence paramCharSequence, String paramString, boolean paramBoolean)
  {
    List localList = VCardResultParser.matchVCardPrefixedField(paramCharSequence, paramString, paramBoolean, false);
    if ((localList != null) && (!localList.isEmpty()))
    {
      int i = localList.size();
      String[] arrayOfString = new String[i];
      for (int j = 0; j < i; j++)
        arrayOfString[j] = ((String)((List)localList.get(j)).get(0));
      return arrayOfString;
    }
    return null;
  }

  private static String stripMailto(String paramString)
  {
    if ((paramString != null) && ((paramString.startsWith("mailto:")) || (paramString.startsWith("MAILTO:"))))
      paramString = paramString.substring(7);
    return paramString;
  }

  // ERROR //
  public CalendarParsedResult parse(com.google.zxing.Result paramResult)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 59	com/google/zxing/client/result/VEventResultParser:getMassagedText	(Lcom/google/zxing/Result;)Ljava/lang/String;
    //   4: astore_2
    //   5: aload_2
    //   6: ldc 61
    //   8: invokevirtual 65	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   11: ifge +5 -> 16
    //   14: aconst_null
    //   15: areturn
    //   16: ldc 67
    //   18: aload_2
    //   19: iconst_1
    //   20: invokestatic 69	com/google/zxing/client/result/VEventResultParser:matchSingleVCardPrefixedField	(Ljava/lang/CharSequence;Ljava/lang/String;Z)Ljava/lang/String;
    //   23: astore_3
    //   24: ldc 71
    //   26: aload_2
    //   27: iconst_1
    //   28: invokestatic 69	com/google/zxing/client/result/VEventResultParser:matchSingleVCardPrefixedField	(Ljava/lang/CharSequence;Ljava/lang/String;Z)Ljava/lang/String;
    //   31: astore 4
    //   33: aload 4
    //   35: ifnonnull +5 -> 40
    //   38: aconst_null
    //   39: areturn
    //   40: ldc 73
    //   42: aload_2
    //   43: iconst_1
    //   44: invokestatic 69	com/google/zxing/client/result/VEventResultParser:matchSingleVCardPrefixedField	(Ljava/lang/CharSequence;Ljava/lang/String;Z)Ljava/lang/String;
    //   47: astore 5
    //   49: ldc 75
    //   51: aload_2
    //   52: iconst_1
    //   53: invokestatic 69	com/google/zxing/client/result/VEventResultParser:matchSingleVCardPrefixedField	(Ljava/lang/CharSequence;Ljava/lang/String;Z)Ljava/lang/String;
    //   56: astore 6
    //   58: ldc 77
    //   60: aload_2
    //   61: iconst_1
    //   62: invokestatic 69	com/google/zxing/client/result/VEventResultParser:matchSingleVCardPrefixedField	(Ljava/lang/CharSequence;Ljava/lang/String;Z)Ljava/lang/String;
    //   65: astore 7
    //   67: ldc 79
    //   69: aload_2
    //   70: iconst_1
    //   71: invokestatic 69	com/google/zxing/client/result/VEventResultParser:matchSingleVCardPrefixedField	(Ljava/lang/CharSequence;Ljava/lang/String;Z)Ljava/lang/String;
    //   74: invokestatic 81	com/google/zxing/client/result/VEventResultParser:stripMailto	(Ljava/lang/String;)Ljava/lang/String;
    //   77: astore 8
    //   79: ldc 83
    //   81: aload_2
    //   82: iconst_1
    //   83: invokestatic 85	com/google/zxing/client/result/VEventResultParser:matchVCardPrefixedField	(Ljava/lang/CharSequence;Ljava/lang/String;Z)[Ljava/lang/String;
    //   86: astore 9
    //   88: aload 9
    //   90: ifnull +33 -> 123
    //   93: iconst_0
    //   94: istore 20
    //   96: iload 20
    //   98: aload 9
    //   100: arraylength
    //   101: if_icmpge +22 -> 123
    //   104: aload 9
    //   106: iload 20
    //   108: aload 9
    //   110: iload 20
    //   112: aaload
    //   113: invokestatic 81	com/google/zxing/client/result/VEventResultParser:stripMailto	(Ljava/lang/String;)Ljava/lang/String;
    //   116: aastore
    //   117: iinc 20 1
    //   120: goto -24 -> 96
    //   123: ldc 87
    //   125: aload_2
    //   126: iconst_1
    //   127: invokestatic 69	com/google/zxing/client/result/VEventResultParser:matchSingleVCardPrefixedField	(Ljava/lang/CharSequence;Ljava/lang/String;Z)Ljava/lang/String;
    //   130: astore 10
    //   132: ldc 89
    //   134: aload_2
    //   135: iconst_1
    //   136: invokestatic 69	com/google/zxing/client/result/VEventResultParser:matchSingleVCardPrefixedField	(Ljava/lang/CharSequence;Ljava/lang/String;Z)Ljava/lang/String;
    //   139: astore 11
    //   141: ldc2_w 90
    //   144: dstore 12
    //   146: aload 11
    //   148: ifnonnull +10 -> 158
    //   151: dload 12
    //   153: dstore 17
    //   155: goto +43 -> 198
    //   158: aload 11
    //   160: bipush 59
    //   162: invokevirtual 94	java/lang/String:indexOf	(I)I
    //   165: istore 14
    //   167: aload 11
    //   169: iconst_0
    //   170: iload 14
    //   172: invokevirtual 97	java/lang/String:substring	(II)Ljava/lang/String;
    //   175: invokestatic 103	java/lang/Double:parseDouble	(Ljava/lang/String;)D
    //   178: dstore 12
    //   180: aload 11
    //   182: iload 14
    //   184: iconst_1
    //   185: iadd
    //   186: invokevirtual 49	java/lang/String:substring	(I)Ljava/lang/String;
    //   189: invokestatic 103	java/lang/Double:parseDouble	(Ljava/lang/String;)D
    //   192: dstore 15
    //   194: dload 15
    //   196: dstore 17
    //   198: new 105	com/google/zxing/client/result/CalendarParsedResult
    //   201: dup
    //   202: aload_3
    //   203: aload 4
    //   205: aload 5
    //   207: aload 6
    //   209: aload 7
    //   211: aload 8
    //   213: aload 9
    //   215: aload 10
    //   217: dload 12
    //   219: dload 17
    //   221: invokespecial 108	com/google/zxing/client/result/CalendarParsedResult:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;DD)V
    //   224: astore 19
    //   226: aload 19
    //   228: areturn
    //   229: aconst_null
    //   230: areturn
    //   231: aconst_null
    //   232: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   198	226	229	java/lang/IllegalArgumentException
    //   167	194	231	java/lang/NumberFormatException
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.VEventResultParser
 * JD-Core Version:    0.6.1
 */