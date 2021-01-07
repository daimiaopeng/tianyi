package org.apache.commons.httpclient;

public class StatusLine
{
  private final String httpVersion;
  private final String reasonPhrase;
  private final int statusCode;
  private final String statusLine;

  // ERROR //
  public StatusLine(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 19	java/lang/Object:<init>	()V
    //   4: aload_1
    //   5: invokevirtual 25	java/lang/String:length	()I
    //   8: istore_2
    //   9: iconst_0
    //   10: istore_3
    //   11: iconst_0
    //   12: istore 4
    //   14: aload_1
    //   15: iload_3
    //   16: invokevirtual 29	java/lang/String:charAt	(I)C
    //   19: invokestatic 35	java/lang/Character:isWhitespace	(C)Z
    //   22: ifeq +12 -> 34
    //   25: iinc 3 1
    //   28: iinc 4 1
    //   31: goto -17 -> 14
    //   34: iload_3
    //   35: iconst_4
    //   36: iadd
    //   37: istore 9
    //   39: ldc 37
    //   41: aload_1
    //   42: iload_3
    //   43: iload 9
    //   45: invokevirtual 41	java/lang/String:substring	(II)Ljava/lang/String;
    //   48: invokevirtual 45	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   51: ifne +48 -> 99
    //   54: new 47	java/lang/StringBuffer
    //   57: dup
    //   58: invokespecial 48	java/lang/StringBuffer:<init>	()V
    //   61: astore 10
    //   63: aload 10
    //   65: ldc 50
    //   67: invokevirtual 54	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   70: pop
    //   71: aload 10
    //   73: aload_1
    //   74: invokevirtual 54	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   77: pop
    //   78: aload 10
    //   80: ldc 56
    //   82: invokevirtual 54	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   85: pop
    //   86: new 58	org/apache/commons/httpclient/HttpException
    //   89: dup
    //   90: aload 10
    //   92: invokevirtual 62	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   95: invokespecial 64	org/apache/commons/httpclient/HttpException:<init>	(Ljava/lang/String;)V
    //   98: athrow
    //   99: aload_1
    //   100: ldc 66
    //   102: iload 9
    //   104: invokevirtual 70	java/lang/String:indexOf	(Ljava/lang/String;I)I
    //   107: istore 14
    //   109: iload 14
    //   111: ifgt +48 -> 159
    //   114: new 47	java/lang/StringBuffer
    //   117: dup
    //   118: invokespecial 48	java/lang/StringBuffer:<init>	()V
    //   121: astore 15
    //   123: aload 15
    //   125: ldc 72
    //   127: invokevirtual 54	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   130: pop
    //   131: aload 15
    //   133: aload_1
    //   134: invokevirtual 54	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   137: pop
    //   138: aload 15
    //   140: ldc 74
    //   142: invokevirtual 54	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   145: pop
    //   146: new 76	org/apache/commons/httpclient/ProtocolException
    //   149: dup
    //   150: aload 15
    //   152: invokevirtual 62	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   155: invokespecial 77	org/apache/commons/httpclient/ProtocolException:<init>	(Ljava/lang/String;)V
    //   158: athrow
    //   159: aload_0
    //   160: aload_1
    //   161: iload 4
    //   163: iload 14
    //   165: invokevirtual 41	java/lang/String:substring	(II)Ljava/lang/String;
    //   168: invokevirtual 80	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   171: putfield 82	org/apache/commons/httpclient/StatusLine:httpVersion	Ljava/lang/String;
    //   174: aload_1
    //   175: iload 14
    //   177: invokevirtual 29	java/lang/String:charAt	(I)C
    //   180: bipush 32
    //   182: if_icmpne +9 -> 191
    //   185: iinc 14 1
    //   188: goto -14 -> 174
    //   191: aload_1
    //   192: ldc 66
    //   194: iload 14
    //   196: invokevirtual 70	java/lang/String:indexOf	(Ljava/lang/String;I)I
    //   199: istore 19
    //   201: iload 19
    //   203: ifge +6 -> 209
    //   206: iload_2
    //   207: istore 19
    //   209: aload_0
    //   210: aload_1
    //   211: iload 14
    //   213: iload 19
    //   215: invokevirtual 41	java/lang/String:substring	(II)Ljava/lang/String;
    //   218: invokestatic 88	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   221: putfield 90	org/apache/commons/httpclient/StatusLine:statusCode	I
    //   224: iload 19
    //   226: iconst_1
    //   227: iadd
    //   228: istore 24
    //   230: iload 24
    //   232: iload_2
    //   233: if_icmpge +19 -> 252
    //   236: aload_0
    //   237: aload_1
    //   238: iload 24
    //   240: invokevirtual 93	java/lang/String:substring	(I)Ljava/lang/String;
    //   243: invokevirtual 96	java/lang/String:trim	()Ljava/lang/String;
    //   246: putfield 98	org/apache/commons/httpclient/StatusLine:reasonPhrase	Ljava/lang/String;
    //   249: goto +9 -> 258
    //   252: aload_0
    //   253: ldc 100
    //   255: putfield 98	org/apache/commons/httpclient/StatusLine:reasonPhrase	Ljava/lang/String;
    //   258: aload_0
    //   259: aload_1
    //   260: putfield 102	org/apache/commons/httpclient/StatusLine:statusLine	Ljava/lang/String;
    //   263: return
    //   264: new 47	java/lang/StringBuffer
    //   267: dup
    //   268: invokespecial 48	java/lang/StringBuffer:<init>	()V
    //   271: astore 20
    //   273: aload 20
    //   275: ldc 104
    //   277: invokevirtual 54	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   280: pop
    //   281: aload 20
    //   283: aload_1
    //   284: invokevirtual 54	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   287: pop
    //   288: aload 20
    //   290: ldc 74
    //   292: invokevirtual 54	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   295: pop
    //   296: new 76	org/apache/commons/httpclient/ProtocolException
    //   299: dup
    //   300: aload 20
    //   302: invokevirtual 62	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   305: invokespecial 77	org/apache/commons/httpclient/ProtocolException:<init>	(Ljava/lang/String;)V
    //   308: athrow
    //   309: new 47	java/lang/StringBuffer
    //   312: dup
    //   313: invokespecial 48	java/lang/StringBuffer:<init>	()V
    //   316: astore 5
    //   318: aload 5
    //   320: ldc 50
    //   322: invokevirtual 54	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   325: pop
    //   326: aload 5
    //   328: aload_1
    //   329: invokevirtual 54	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   332: pop
    //   333: aload 5
    //   335: ldc 106
    //   337: invokevirtual 54	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   340: pop
    //   341: new 58	org/apache/commons/httpclient/HttpException
    //   344: dup
    //   345: aload 5
    //   347: invokevirtual 62	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   350: invokespecial 64	org/apache/commons/httpclient/HttpException:<init>	(Ljava/lang/String;)V
    //   353: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   209	224	264	java/lang/NumberFormatException
    //   14	201	309	java/lang/StringIndexOutOfBoundsException
    //   209	224	309	java/lang/StringIndexOutOfBoundsException
    //   236	258	309	java/lang/StringIndexOutOfBoundsException
    //   264	309	309	java/lang/StringIndexOutOfBoundsException
  }

  // ERROR //
  public static boolean startsWithHTTP(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: iload_1
    //   4: invokevirtual 29	java/lang/String:charAt	(I)C
    //   7: invokestatic 35	java/lang/Character:isWhitespace	(C)Z
    //   10: ifeq +9 -> 19
    //   13: iinc 1 1
    //   16: goto -14 -> 2
    //   19: ldc 37
    //   21: aload_0
    //   22: iload_1
    //   23: iload_1
    //   24: iconst_4
    //   25: iadd
    //   26: invokevirtual 41	java/lang/String:substring	(II)Ljava/lang/String;
    //   29: invokevirtual 45	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   32: istore_2
    //   33: iload_2
    //   34: ireturn
    //   35: iconst_0
    //   36: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   2	33	35	java/lang/StringIndexOutOfBoundsException
  }

  public final String getHttpVersion()
  {
    return this.httpVersion;
  }

  public final String getReasonPhrase()
  {
    return this.reasonPhrase;
  }

  public final int getStatusCode()
  {
    return this.statusCode;
  }

  public final String toString()
  {
    return this.statusLine;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.StatusLine
 * JD-Core Version:    0.6.1
 */