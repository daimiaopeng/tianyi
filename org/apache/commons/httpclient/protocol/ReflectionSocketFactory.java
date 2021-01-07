package org.apache.commons.httpclient.protocol;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public final class ReflectionSocketFactory
{
  private static Constructor INETSOCKETADDRESS_CONSTRUCTOR;
  private static boolean REFLECTION_FAILED;
  private static Method SOCKETBIND_METHOD;
  private static Method SOCKETCONNECT_METHOD;
  private static Class SOCKETTIMEOUTEXCEPTION_CLASS;
  static Class class$java$net$InetAddress;
  static Class class$java$net$Socket;

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
  public static java.net.Socket createSocket(String paramString1, String paramString2, int paramInt1, java.net.InetAddress paramInetAddress, int paramInt2, int paramInt3)
  {
    // Byte code:
    //   0: getstatic 46	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:REFLECTION_FAILED	Z
    //   3: ifeq +5 -> 8
    //   6: aconst_null
    //   7: areturn
    //   8: aload_0
    //   9: invokestatic 29	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   12: astore 12
    //   14: aload 12
    //   16: ldc 48
    //   18: iconst_0
    //   19: anewarray 26	java/lang/Class
    //   22: invokevirtual 52	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   25: aconst_null
    //   26: iconst_0
    //   27: anewarray 4	java/lang/Object
    //   30: invokevirtual 58	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   33: astore 13
    //   35: aload 12
    //   37: ldc 59
    //   39: iconst_0
    //   40: anewarray 26	java/lang/Class
    //   43: invokevirtual 52	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   46: aload 13
    //   48: iconst_0
    //   49: anewarray 4	java/lang/Object
    //   52: invokevirtual 58	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   55: checkcast 61	java/net/Socket
    //   58: astore 14
    //   60: getstatic 63	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:INETSOCKETADDRESS_CONSTRUCTOR	Ljava/lang/reflect/Constructor;
    //   63: ifnonnull +65 -> 128
    //   66: ldc 65
    //   68: invokestatic 29	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   71: astore 29
    //   73: iconst_2
    //   74: anewarray 26	java/lang/Class
    //   77: astore 30
    //   79: getstatic 67	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:class$java$net$InetAddress	Ljava/lang/Class;
    //   82: ifnonnull +18 -> 100
    //   85: ldc 69
    //   87: invokestatic 71	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:class$	(Ljava/lang/String;)Ljava/lang/Class;
    //   90: astore 31
    //   92: aload 31
    //   94: putstatic 67	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:class$java$net$InetAddress	Ljava/lang/Class;
    //   97: goto +8 -> 105
    //   100: getstatic 67	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:class$java$net$InetAddress	Ljava/lang/Class;
    //   103: astore 31
    //   105: aload 30
    //   107: iconst_0
    //   108: aload 31
    //   110: aastore
    //   111: aload 30
    //   113: iconst_1
    //   114: getstatic 76	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   117: aastore
    //   118: aload 29
    //   120: aload 30
    //   122: invokevirtual 80	java/lang/Class:getConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   125: putstatic 63	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:INETSOCKETADDRESS_CONSTRUCTOR	Ljava/lang/reflect/Constructor;
    //   128: getstatic 63	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:INETSOCKETADDRESS_CONSTRUCTOR	Ljava/lang/reflect/Constructor;
    //   131: astore 15
    //   133: iconst_2
    //   134: anewarray 4	java/lang/Object
    //   137: astore 16
    //   139: aload 16
    //   141: iconst_0
    //   142: aload_1
    //   143: invokestatic 86	java/net/InetAddress:getByName	(Ljava/lang/String;)Ljava/net/InetAddress;
    //   146: aastore
    //   147: aload 16
    //   149: iconst_1
    //   150: new 73	java/lang/Integer
    //   153: dup
    //   154: iload_2
    //   155: invokespecial 89	java/lang/Integer:<init>	(I)V
    //   158: aastore
    //   159: aload 15
    //   161: aload 16
    //   163: invokevirtual 95	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   166: astore 17
    //   168: getstatic 63	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:INETSOCKETADDRESS_CONSTRUCTOR	Ljava/lang/reflect/Constructor;
    //   171: astore 18
    //   173: iconst_2
    //   174: anewarray 4	java/lang/Object
    //   177: astore 19
    //   179: aload 19
    //   181: iconst_0
    //   182: aload_3
    //   183: aastore
    //   184: aload 19
    //   186: iconst_1
    //   187: new 73	java/lang/Integer
    //   190: dup
    //   191: iload 4
    //   193: invokespecial 89	java/lang/Integer:<init>	(I)V
    //   196: aastore
    //   197: aload 18
    //   199: aload 19
    //   201: invokevirtual 95	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   204: astore 20
    //   206: getstatic 97	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:SOCKETCONNECT_METHOD	Ljava/lang/reflect/Method;
    //   209: ifnonnull +63 -> 272
    //   212: getstatic 99	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:class$java$net$Socket	Ljava/lang/Class;
    //   215: ifnonnull +18 -> 233
    //   218: ldc 101
    //   220: invokestatic 71	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:class$	(Ljava/lang/String;)Ljava/lang/Class;
    //   223: astore 27
    //   225: aload 27
    //   227: putstatic 99	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:class$java$net$Socket	Ljava/lang/Class;
    //   230: goto +8 -> 238
    //   233: getstatic 99	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:class$java$net$Socket	Ljava/lang/Class;
    //   236: astore 27
    //   238: iconst_2
    //   239: anewarray 26	java/lang/Class
    //   242: astore 28
    //   244: aload 28
    //   246: iconst_0
    //   247: ldc 103
    //   249: invokestatic 29	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   252: aastore
    //   253: aload 28
    //   255: iconst_1
    //   256: getstatic 76	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   259: aastore
    //   260: aload 27
    //   262: ldc 105
    //   264: aload 28
    //   266: invokevirtual 52	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   269: putstatic 97	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:SOCKETCONNECT_METHOD	Ljava/lang/reflect/Method;
    //   272: getstatic 107	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:SOCKETBIND_METHOD	Ljava/lang/reflect/Method;
    //   275: ifnonnull +56 -> 331
    //   278: getstatic 99	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:class$java$net$Socket	Ljava/lang/Class;
    //   281: ifnonnull +18 -> 299
    //   284: ldc 101
    //   286: invokestatic 71	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:class$	(Ljava/lang/String;)Ljava/lang/Class;
    //   289: astore 25
    //   291: aload 25
    //   293: putstatic 99	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:class$java$net$Socket	Ljava/lang/Class;
    //   296: goto +8 -> 304
    //   299: getstatic 99	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:class$java$net$Socket	Ljava/lang/Class;
    //   302: astore 25
    //   304: iconst_1
    //   305: anewarray 26	java/lang/Class
    //   308: astore 26
    //   310: aload 26
    //   312: iconst_0
    //   313: ldc 103
    //   315: invokestatic 29	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   318: aastore
    //   319: aload 25
    //   321: ldc 109
    //   323: aload 26
    //   325: invokevirtual 52	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   328: putstatic 107	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:SOCKETBIND_METHOD	Ljava/lang/reflect/Method;
    //   331: getstatic 107	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:SOCKETBIND_METHOD	Ljava/lang/reflect/Method;
    //   334: aload 14
    //   336: iconst_1
    //   337: anewarray 4	java/lang/Object
    //   340: dup
    //   341: iconst_0
    //   342: aload 20
    //   344: aastore
    //   345: invokevirtual 58	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   348: pop
    //   349: getstatic 97	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:SOCKETCONNECT_METHOD	Ljava/lang/reflect/Method;
    //   352: astore 22
    //   354: iconst_2
    //   355: anewarray 4	java/lang/Object
    //   358: astore 23
    //   360: aload 23
    //   362: iconst_0
    //   363: aload 17
    //   365: aastore
    //   366: aload 23
    //   368: iconst_1
    //   369: new 73	java/lang/Integer
    //   372: dup
    //   373: iload 5
    //   375: invokespecial 89	java/lang/Integer:<init>	(I)V
    //   378: aastore
    //   379: aload 22
    //   381: aload 14
    //   383: aload 23
    //   385: invokevirtual 58	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   388: pop
    //   389: aload 14
    //   391: areturn
    //   392: iconst_1
    //   393: putstatic 46	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:REFLECTION_FAILED	Z
    //   396: aconst_null
    //   397: areturn
    //   398: astore 6
    //   400: aload 6
    //   402: invokevirtual 113	java/lang/reflect/InvocationTargetException:getTargetException	()Ljava/lang/Throwable;
    //   405: astore 7
    //   407: getstatic 115	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:SOCKETTIMEOUTEXCEPTION_CLASS	Ljava/lang/Class;
    //   410: ifnonnull +20 -> 430
    //   413: ldc 117
    //   415: invokestatic 29	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   418: putstatic 115	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:SOCKETTIMEOUTEXCEPTION_CLASS	Ljava/lang/Class;
    //   421: goto +9 -> 430
    //   424: iconst_1
    //   425: putstatic 46	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:REFLECTION_FAILED	Z
    //   428: aconst_null
    //   429: areturn
    //   430: getstatic 115	org/apache/commons/httpclient/protocol/ReflectionSocketFactory:SOCKETTIMEOUTEXCEPTION_CLASS	Ljava/lang/Class;
    //   433: aload 7
    //   435: invokevirtual 121	java/lang/Class:isInstance	(Ljava/lang/Object;)Z
    //   438: ifeq +51 -> 489
    //   441: new 123	java/lang/StringBuffer
    //   444: dup
    //   445: invokespecial 124	java/lang/StringBuffer:<init>	()V
    //   448: astore 8
    //   450: aload 8
    //   452: ldc 126
    //   454: invokevirtual 130	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   457: pop
    //   458: aload 8
    //   460: iload 5
    //   462: invokevirtual 133	java/lang/StringBuffer:append	(I)Ljava/lang/StringBuffer;
    //   465: pop
    //   466: aload 8
    //   468: ldc 135
    //   470: invokevirtual 130	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   473: pop
    //   474: new 137	org/apache/commons/httpclient/ConnectTimeoutException
    //   477: dup
    //   478: aload 8
    //   480: invokevirtual 140	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   483: aload 7
    //   485: invokespecial 143	org/apache/commons/httpclient/ConnectTimeoutException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   488: athrow
    //   489: aload 7
    //   491: instanceof 145
    //   494: ifeq +9 -> 503
    //   497: aload 7
    //   499: checkcast 145	java/io/IOException
    //   502: athrow
    //   503: aconst_null
    //   504: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   8	389	392	java/lang/Exception
    //   8	389	398	java/lang/reflect/InvocationTargetException
    //   413	421	424	java/lang/ClassNotFoundException
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.protocol.ReflectionSocketFactory
 * JD-Core Version:    0.6.1
 */