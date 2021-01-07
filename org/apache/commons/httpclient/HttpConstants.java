package org.apache.commons.httpclient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpConstants
{
  public static final String DEFAULT_CONTENT_CHARSET = "ISO-8859-1";
  public static final String HTTP_ELEMENT_CHARSET = "US-ASCII";
  private static final Log LOG = LogFactory.getLog(localClass);
  static Class class$org$apache$commons$httpclient$HttpConstants;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$HttpConstants == null)
    {
      localClass = class$("org.apache.commons.httpclient.HttpConstants");
      class$org$apache$commons$httpclient$HttpConstants = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$HttpConstants;
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

  // ERROR //
  public static byte[] getAsciiBytes(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +13 -> 14
    //   4: new 58	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 60
    //   10: invokespecial 61	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_0
    //   15: ldc 11
    //   17: invokevirtual 66	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   20: astore_1
    //   21: aload_1
    //   22: areturn
    //   23: new 68	java/lang/RuntimeException
    //   26: dup
    //   27: ldc 70
    //   29: invokespecial 71	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
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
    //   4: new 58	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 60
    //   10: invokespecial 61	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: new 63	java/lang/String
    //   17: dup
    //   18: aload_0
    //   19: iload_1
    //   20: iload_2
    //   21: ldc 11
    //   23: invokespecial 79	java/lang/String:<init>	([BIILjava/lang/String;)V
    //   26: astore_3
    //   27: aload_3
    //   28: areturn
    //   29: new 68	java/lang/RuntimeException
    //   32: dup
    //   33: ldc 70
    //   35: invokespecial 71	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   38: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   14	27	29	java/io/UnsupportedEncodingException
  }

  // ERROR //
  public static byte[] getBytes(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +13 -> 14
    //   4: new 58	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 60
    //   10: invokespecial 61	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_0
    //   15: ldc 11
    //   17: invokevirtual 66	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   20: astore_1
    //   21: aload_1
    //   22: areturn
    //   23: getstatic 33	org/apache/commons/httpclient/HttpConstants:LOG	Lorg/apache/commons/logging/Log;
    //   26: invokeinterface 85 1 0
    //   31: ifeq +13 -> 44
    //   34: getstatic 33	org/apache/commons/httpclient/HttpConstants:LOG	Lorg/apache/commons/logging/Log;
    //   37: ldc 87
    //   39: invokeinterface 91 2 0
    //   44: aload_0
    //   45: invokevirtual 94	java/lang/String:getBytes	()[B
    //   48: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   14	21	23	java/io/UnsupportedEncodingException
  }

  public static byte[] getContentBytes(String paramString)
  {
    return getContentBytes(paramString, null);
  }

  // ERROR //
  public static byte[] getContentBytes(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +13 -> 14
    //   4: new 58	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 60
    //   10: invokespecial 61	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_1
    //   15: ifnull +12 -> 27
    //   18: aload_1
    //   19: ldc 100
    //   21: invokevirtual 104	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   24: ifeq +6 -> 30
    //   27: ldc 8
    //   29: astore_1
    //   30: aload_0
    //   31: aload_1
    //   32: invokevirtual 66	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   35: astore 8
    //   37: aload 8
    //   39: areturn
    //   40: getstatic 33	org/apache/commons/httpclient/HttpConstants:LOG	Lorg/apache/commons/logging/Log;
    //   43: invokeinterface 85 1 0
    //   48: ifeq +50 -> 98
    //   51: getstatic 33	org/apache/commons/httpclient/HttpConstants:LOG	Lorg/apache/commons/logging/Log;
    //   54: astore_3
    //   55: new 106	java/lang/StringBuffer
    //   58: dup
    //   59: invokespecial 107	java/lang/StringBuffer:<init>	()V
    //   62: astore 4
    //   64: aload 4
    //   66: ldc 109
    //   68: invokevirtual 113	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   71: pop
    //   72: aload 4
    //   74: aload_1
    //   75: invokevirtual 113	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   78: pop
    //   79: aload 4
    //   81: ldc 115
    //   83: invokevirtual 113	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   86: pop
    //   87: aload_3
    //   88: aload 4
    //   90: invokevirtual 118	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   93: invokeinterface 91 2 0
    //   98: aload_0
    //   99: ldc 8
    //   101: invokevirtual 66	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   104: astore_2
    //   105: aload_2
    //   106: areturn
    //   107: getstatic 33	org/apache/commons/httpclient/HttpConstants:LOG	Lorg/apache/commons/logging/Log;
    //   110: invokeinterface 85 1 0
    //   115: ifeq +13 -> 128
    //   118: getstatic 33	org/apache/commons/httpclient/HttpConstants:LOG	Lorg/apache/commons/logging/Log;
    //   121: ldc 120
    //   123: invokeinterface 91 2 0
    //   128: aload_0
    //   129: invokevirtual 94	java/lang/String:getBytes	()[B
    //   132: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   30	37	40	java/io/UnsupportedEncodingException
    //   98	105	107	java/io/UnsupportedEncodingException
  }

  public static String getContentString(byte[] paramArrayOfByte)
  {
    return getContentString(paramArrayOfByte, null);
  }

  public static String getContentString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return getContentString(paramArrayOfByte, paramInt1, paramInt2, null);
  }

  // ERROR //
  public static String getContentString(byte[] paramArrayOfByte, int paramInt1, int paramInt2, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +13 -> 14
    //   4: new 58	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 60
    //   10: invokespecial 61	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_3
    //   15: ifnull +12 -> 27
    //   18: aload_3
    //   19: ldc 100
    //   21: invokevirtual 104	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   24: ifeq +6 -> 30
    //   27: ldc 8
    //   29: astore_3
    //   30: new 63	java/lang/String
    //   33: dup
    //   34: aload_0
    //   35: iload_1
    //   36: iload_2
    //   37: aload_3
    //   38: invokespecial 79	java/lang/String:<init>	([BIILjava/lang/String;)V
    //   41: astore 4
    //   43: aload 4
    //   45: areturn
    //   46: getstatic 33	org/apache/commons/httpclient/HttpConstants:LOG	Lorg/apache/commons/logging/Log;
    //   49: invokeinterface 85 1 0
    //   54: ifeq +52 -> 106
    //   57: getstatic 33	org/apache/commons/httpclient/HttpConstants:LOG	Lorg/apache/commons/logging/Log;
    //   60: astore 6
    //   62: new 106	java/lang/StringBuffer
    //   65: dup
    //   66: invokespecial 107	java/lang/StringBuffer:<init>	()V
    //   69: astore 7
    //   71: aload 7
    //   73: ldc 109
    //   75: invokevirtual 113	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   78: pop
    //   79: aload 7
    //   81: aload_3
    //   82: invokevirtual 113	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   85: pop
    //   86: aload 7
    //   88: ldc 129
    //   90: invokevirtual 113	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   93: pop
    //   94: aload 6
    //   96: aload 7
    //   98: invokevirtual 118	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   101: invokeinterface 91 2 0
    //   106: new 63	java/lang/String
    //   109: dup
    //   110: aload_0
    //   111: iload_1
    //   112: iload_2
    //   113: ldc 8
    //   115: invokespecial 79	java/lang/String:<init>	([BIILjava/lang/String;)V
    //   118: astore 5
    //   120: aload 5
    //   122: areturn
    //   123: getstatic 33	org/apache/commons/httpclient/HttpConstants:LOG	Lorg/apache/commons/logging/Log;
    //   126: invokeinterface 85 1 0
    //   131: ifeq +13 -> 144
    //   134: getstatic 33	org/apache/commons/httpclient/HttpConstants:LOG	Lorg/apache/commons/logging/Log;
    //   137: ldc 120
    //   139: invokeinterface 91 2 0
    //   144: new 63	java/lang/String
    //   147: dup
    //   148: aload_0
    //   149: iload_1
    //   150: iload_2
    //   151: invokespecial 132	java/lang/String:<init>	([BII)V
    //   154: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   30	43	46	java/io/UnsupportedEncodingException
    //   106	120	123	java/io/UnsupportedEncodingException
  }

  public static String getContentString(byte[] paramArrayOfByte, String paramString)
  {
    return getContentString(paramArrayOfByte, 0, paramArrayOfByte.length, paramString);
  }

  public static String getString(byte[] paramArrayOfByte)
  {
    return getString(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  // ERROR //
  public static String getString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +13 -> 14
    //   4: new 58	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 60
    //   10: invokespecial 61	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: new 63	java/lang/String
    //   17: dup
    //   18: aload_0
    //   19: iload_1
    //   20: iload_2
    //   21: ldc 11
    //   23: invokespecial 79	java/lang/String:<init>	([BIILjava/lang/String;)V
    //   26: astore_3
    //   27: aload_3
    //   28: areturn
    //   29: getstatic 33	org/apache/commons/httpclient/HttpConstants:LOG	Lorg/apache/commons/logging/Log;
    //   32: invokeinterface 85 1 0
    //   37: ifeq +13 -> 50
    //   40: getstatic 33	org/apache/commons/httpclient/HttpConstants:LOG	Lorg/apache/commons/logging/Log;
    //   43: ldc 87
    //   45: invokeinterface 91 2 0
    //   50: new 63	java/lang/String
    //   53: dup
    //   54: aload_0
    //   55: iload_1
    //   56: iload_2
    //   57: invokespecial 132	java/lang/String:<init>	([BII)V
    //   60: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   14	27	29	java/io/UnsupportedEncodingException
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpConstants
 * JD-Core Version:    0.6.1
 */