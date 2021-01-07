package org.apache.commons.httpclient;

public class HttpVersion
  implements Comparable
{
  public static final HttpVersion HTTP_0_9 = new HttpVersion(0, 9);
  public static final HttpVersion HTTP_1_0 = new HttpVersion(1, 0);
  public static final HttpVersion HTTP_1_1 = new HttpVersion(1, 1);
  private int major = 0;
  private int minor = 0;

  public HttpVersion(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0)
      throw new IllegalArgumentException("HTTP major version number may not be negative");
    this.major = paramInt1;
    if (paramInt2 < 0)
      throw new IllegalArgumentException("HTTP minor version number may not be negative");
    this.minor = paramInt2;
  }

  // ERROR //
  public static HttpVersion parse(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +13 -> 14
    //   4: new 33	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 46
    //   10: invokespecial 38	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_0
    //   15: ldc 48
    //   17: invokevirtual 54	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   20: ifne +36 -> 56
    //   23: new 56	java/lang/StringBuffer
    //   26: dup
    //   27: invokespecial 57	java/lang/StringBuffer:<init>	()V
    //   30: astore_1
    //   31: aload_1
    //   32: ldc 59
    //   34: invokevirtual 63	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   37: pop
    //   38: aload_1
    //   39: aload_0
    //   40: invokevirtual 63	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   43: pop
    //   44: new 65	org/apache/commons/httpclient/ProtocolException
    //   47: dup
    //   48: aload_1
    //   49: invokevirtual 69	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   52: invokespecial 70	org/apache/commons/httpclient/ProtocolException:<init>	(Ljava/lang/String;)V
    //   55: athrow
    //   56: ldc 48
    //   58: invokevirtual 74	java/lang/String:length	()I
    //   61: istore 4
    //   63: aload_0
    //   64: ldc 76
    //   66: iload 4
    //   68: invokevirtual 80	java/lang/String:indexOf	(Ljava/lang/String;I)I
    //   71: istore 5
    //   73: iload 5
    //   75: iconst_m1
    //   76: if_icmpne +40 -> 116
    //   79: new 56	java/lang/StringBuffer
    //   82: dup
    //   83: invokespecial 57	java/lang/StringBuffer:<init>	()V
    //   86: astore 6
    //   88: aload 6
    //   90: ldc 82
    //   92: invokevirtual 63	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   95: pop
    //   96: aload 6
    //   98: aload_0
    //   99: invokevirtual 63	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   102: pop
    //   103: new 65	org/apache/commons/httpclient/ProtocolException
    //   106: dup
    //   107: aload 6
    //   109: invokevirtual 69	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   112: invokespecial 70	org/apache/commons/httpclient/ProtocolException:<init>	(Ljava/lang/String;)V
    //   115: athrow
    //   116: aload_0
    //   117: iload 4
    //   119: iload 5
    //   121: invokevirtual 86	java/lang/String:substring	(II)Ljava/lang/String;
    //   124: invokestatic 92	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   127: istore 12
    //   129: iload 5
    //   131: iconst_1
    //   132: iadd
    //   133: istore 13
    //   135: aload_0
    //   136: invokevirtual 74	java/lang/String:length	()I
    //   139: istore 14
    //   141: aload_0
    //   142: iload 13
    //   144: iload 14
    //   146: invokevirtual 86	java/lang/String:substring	(II)Ljava/lang/String;
    //   149: invokestatic 92	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   152: istore 18
    //   154: new 2	org/apache/commons/httpclient/HttpVersion
    //   157: dup
    //   158: iload 12
    //   160: iload 18
    //   162: invokespecial 19	org/apache/commons/httpclient/HttpVersion:<init>	(II)V
    //   165: areturn
    //   166: new 56	java/lang/StringBuffer
    //   169: dup
    //   170: invokespecial 57	java/lang/StringBuffer:<init>	()V
    //   173: astore 15
    //   175: aload 15
    //   177: ldc 94
    //   179: invokevirtual 63	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   182: pop
    //   183: aload 15
    //   185: aload_0
    //   186: invokevirtual 63	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   189: pop
    //   190: new 65	org/apache/commons/httpclient/ProtocolException
    //   193: dup
    //   194: aload 15
    //   196: invokevirtual 69	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   199: invokespecial 70	org/apache/commons/httpclient/ProtocolException:<init>	(Ljava/lang/String;)V
    //   202: athrow
    //   203: new 56	java/lang/StringBuffer
    //   206: dup
    //   207: invokespecial 57	java/lang/StringBuffer:<init>	()V
    //   210: astore 9
    //   212: aload 9
    //   214: ldc 96
    //   216: invokevirtual 63	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   219: pop
    //   220: aload 9
    //   222: aload_0
    //   223: invokevirtual 63	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   226: pop
    //   227: new 65	org/apache/commons/httpclient/ProtocolException
    //   230: dup
    //   231: aload 9
    //   233: invokevirtual 69	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   236: invokespecial 70	org/apache/commons/httpclient/ProtocolException:<init>	(Ljava/lang/String;)V
    //   239: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   141	154	166	java/lang/NumberFormatException
    //   116	129	203	java/lang/NumberFormatException
  }

  public int compareTo(Object paramObject)
  {
    return compareTo((HttpVersion)paramObject);
  }

  public int compareTo(HttpVersion paramHttpVersion)
  {
    if (paramHttpVersion == null)
      throw new IllegalArgumentException("Version parameter may not be null");
    int i = getMajor() - paramHttpVersion.getMajor();
    if (i == 0)
      i = getMinor() - paramHttpVersion.getMinor();
    return i;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if (!(paramObject instanceof HttpVersion))
      return false;
    return equals((HttpVersion)paramObject);
  }

  public boolean equals(HttpVersion paramHttpVersion)
  {
    boolean bool;
    if (compareTo(paramHttpVersion) == 0)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public int getMajor()
  {
    return this.major;
  }

  public int getMinor()
  {
    return this.minor;
  }

  public boolean greaterEquals(HttpVersion paramHttpVersion)
  {
    boolean bool;
    if (compareTo(paramHttpVersion) >= 0)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public int hashCode()
  {
    return 100000 * this.major + this.minor;
  }

  public boolean lessEquals(HttpVersion paramHttpVersion)
  {
    boolean bool;
    if (compareTo(paramHttpVersion) <= 0)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("HTTP/");
    localStringBuffer.append(this.major);
    localStringBuffer.append('.');
    localStringBuffer.append(this.minor);
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpVersion
 * JD-Core Version:    0.6.1
 */