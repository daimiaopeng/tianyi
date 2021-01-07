package org.apache.commons.httpclient.util;

import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EncodingUtil
{
  private static final String DEFAULT_CHARSET = "ISO-8859-1";
  private static final Log LOG = LogFactory.getLog(localClass);
  static Class class$org$apache$commons$httpclient$util$EncodingUtil;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$util$EncodingUtil == null)
    {
      localClass = class$("org.apache.commons.httpclient.util.EncodingUtil");
      class$org$apache$commons$httpclient$util$EncodingUtil = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$util$EncodingUtil;
    }
  }

  static Class class$(String paramString)
  {
    try
    {
      Class localClass = Class.forName(paramString);
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
    }
  }

  private static String doFormUrlEncode(NameValuePair[] paramArrayOfNameValuePair, String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < paramArrayOfNameValuePair.length; i++)
    {
      URLCodec localURLCodec = new URLCodec();
      NameValuePair localNameValuePair = paramArrayOfNameValuePair[i];
      if (localNameValuePair.getName() != null)
      {
        if (i > 0)
          localStringBuffer.append("&");
        localStringBuffer.append(localURLCodec.encode(localNameValuePair.getName(), paramString));
        localStringBuffer.append("=");
        if (localNameValuePair.getValue() != null)
          localStringBuffer.append(localURLCodec.encode(localNameValuePair.getValue(), paramString));
      }
    }
    return localStringBuffer.toString();
  }

  // ERROR //
  public static String formUrlEncode(NameValuePair[] paramArrayOfNameValuePair, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokestatic 85	org/apache/commons/httpclient/util/EncodingUtil:doFormUrlEncode	([Lorg/apache/commons/httpclient/NameValuePair;Ljava/lang/String;)Ljava/lang/String;
    //   5: astore 7
    //   7: aload 7
    //   9: areturn
    //   10: getstatic 30	org/apache/commons/httpclient/util/EncodingUtil:LOG	Lorg/apache/commons/logging/Log;
    //   13: astore_2
    //   14: new 53	java/lang/StringBuffer
    //   17: dup
    //   18: invokespecial 54	java/lang/StringBuffer:<init>	()V
    //   21: astore_3
    //   22: aload_3
    //   23: ldc 87
    //   25: invokevirtual 68	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   28: pop
    //   29: aload_3
    //   30: aload_1
    //   31: invokevirtual 68	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   34: pop
    //   35: aload_2
    //   36: aload_3
    //   37: invokevirtual 80	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   40: invokeinterface 93 2 0
    //   45: aload_0
    //   46: ldc 8
    //   48: invokestatic 85	org/apache/commons/httpclient/util/EncodingUtil:doFormUrlEncode	([Lorg/apache/commons/httpclient/NameValuePair;Ljava/lang/String;)Ljava/lang/String;
    //   51: astore 6
    //   53: aload 6
    //   55: areturn
    //   56: new 95	org/apache/commons/httpclient/HttpClientError
    //   59: dup
    //   60: ldc 97
    //   62: invokespecial 98	org/apache/commons/httpclient/HttpClientError:<init>	(Ljava/lang/String;)V
    //   65: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   0	7	10	java/io/UnsupportedEncodingException
    //   45	53	56	java/io/UnsupportedEncodingException
  }

  // ERROR //
  public static byte[] getAsciiBytes(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +13 -> 14
    //   4: new 102	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 104
    //   10: invokespecial 105	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_0
    //   15: ldc 107
    //   17: invokevirtual 112	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   20: astore_1
    //   21: aload_1
    //   22: areturn
    //   23: new 95	org/apache/commons/httpclient/HttpClientError
    //   26: dup
    //   27: ldc 114
    //   29: invokespecial 98	org/apache/commons/httpclient/HttpClientError:<init>	(Ljava/lang/String;)V
    //   32: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   14	21	23	java/io/UnsupportedEncodingException
  }

  public static String getAsciiString(byte[] paramArrayOfByte)
  {
    return getAsciiString(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  // ERROR //
  public static String getAsciiString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +13 -> 14
    //   4: new 102	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 104
    //   10: invokespecial 105	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: new 109	java/lang/String
    //   17: dup
    //   18: aload_0
    //   19: iload_1
    //   20: iload_2
    //   21: ldc 107
    //   23: invokespecial 122	java/lang/String:<init>	([BIILjava/lang/String;)V
    //   26: astore_3
    //   27: aload_3
    //   28: areturn
    //   29: new 95	org/apache/commons/httpclient/HttpClientError
    //   32: dup
    //   33: ldc 114
    //   35: invokespecial 98	org/apache/commons/httpclient/HttpClientError:<init>	(Ljava/lang/String;)V
    //   38: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   14	27	29	java/io/UnsupportedEncodingException
  }

  // ERROR //
  public static byte[] getBytes(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +13 -> 14
    //   4: new 102	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 125
    //   10: invokespecial 105	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_1
    //   15: ifnull +81 -> 96
    //   18: aload_1
    //   19: invokevirtual 129	java/lang/String:length	()I
    //   22: ifne +6 -> 28
    //   25: goto +71 -> 96
    //   28: aload_0
    //   29: aload_1
    //   30: invokevirtual 112	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   33: astore 7
    //   35: aload 7
    //   37: areturn
    //   38: getstatic 30	org/apache/commons/httpclient/util/EncodingUtil:LOG	Lorg/apache/commons/logging/Log;
    //   41: invokeinterface 133 1 0
    //   46: ifeq +45 -> 91
    //   49: getstatic 30	org/apache/commons/httpclient/util/EncodingUtil:LOG	Lorg/apache/commons/logging/Log;
    //   52: astore_2
    //   53: new 53	java/lang/StringBuffer
    //   56: dup
    //   57: invokespecial 54	java/lang/StringBuffer:<init>	()V
    //   60: astore_3
    //   61: aload_3
    //   62: ldc 135
    //   64: invokevirtual 68	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   67: pop
    //   68: aload_3
    //   69: aload_1
    //   70: invokevirtual 68	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   73: pop
    //   74: aload_3
    //   75: ldc 137
    //   77: invokevirtual 68	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   80: pop
    //   81: aload_2
    //   82: aload_3
    //   83: invokevirtual 80	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   86: invokeinterface 140 2 0
    //   91: aload_0
    //   92: invokevirtual 143	java/lang/String:getBytes	()[B
    //   95: areturn
    //   96: new 102	java/lang/IllegalArgumentException
    //   99: dup
    //   100: ldc 145
    //   102: invokespecial 105	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   105: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   28	35	38	java/io/UnsupportedEncodingException
  }

  // ERROR //
  public static String getString(byte[] paramArrayOfByte, int paramInt1, int paramInt2, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +13 -> 14
    //   4: new 102	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 104
    //   10: invokespecial 105	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_3
    //   15: ifnull +100 -> 115
    //   18: aload_3
    //   19: invokevirtual 129	java/lang/String:length	()I
    //   22: ifne +6 -> 28
    //   25: goto +90 -> 115
    //   28: new 109	java/lang/String
    //   31: dup
    //   32: aload_0
    //   33: iload_1
    //   34: iload_2
    //   35: aload_3
    //   36: invokespecial 122	java/lang/String:<init>	([BIILjava/lang/String;)V
    //   39: astore 4
    //   41: aload 4
    //   43: areturn
    //   44: getstatic 30	org/apache/commons/httpclient/util/EncodingUtil:LOG	Lorg/apache/commons/logging/Log;
    //   47: invokeinterface 133 1 0
    //   52: ifeq +52 -> 104
    //   55: getstatic 30	org/apache/commons/httpclient/util/EncodingUtil:LOG	Lorg/apache/commons/logging/Log;
    //   58: astore 5
    //   60: new 53	java/lang/StringBuffer
    //   63: dup
    //   64: invokespecial 54	java/lang/StringBuffer:<init>	()V
    //   67: astore 6
    //   69: aload 6
    //   71: ldc 135
    //   73: invokevirtual 68	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   76: pop
    //   77: aload 6
    //   79: aload_3
    //   80: invokevirtual 68	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   83: pop
    //   84: aload 6
    //   86: ldc 149
    //   88: invokevirtual 68	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   91: pop
    //   92: aload 5
    //   94: aload 6
    //   96: invokevirtual 80	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   99: invokeinterface 140 2 0
    //   104: new 109	java/lang/String
    //   107: dup
    //   108: aload_0
    //   109: iload_1
    //   110: iload_2
    //   111: invokespecial 152	java/lang/String:<init>	([BII)V
    //   114: areturn
    //   115: new 102	java/lang/IllegalArgumentException
    //   118: dup
    //   119: ldc 145
    //   121: invokespecial 105	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   124: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   28	41	44	java/io/UnsupportedEncodingException
  }

  public static String getString(byte[] paramArrayOfByte, String paramString)
  {
    return getString(paramArrayOfByte, 0, paramArrayOfByte.length, paramString);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.util.EncodingUtil
 * JD-Core Version:    0.6.1
 */