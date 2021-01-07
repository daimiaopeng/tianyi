package com.cndatacom.e;

public class i
{
  // ERROR //
  public static java.lang.String a()
  {
    // Byte code:
    //   0: new 10	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 14	java/lang/StringBuilder:<init>	()V
    //   7: astore_0
    //   8: invokestatic 20	java/net/NetworkInterface:getNetworkInterfaces	()Ljava/util/Enumeration;
    //   11: astore_1
    //   12: aload_1
    //   13: invokeinterface 26 1 0
    //   18: ifeq +95 -> 113
    //   21: aload_1
    //   22: invokeinterface 30 1 0
    //   27: checkcast 16	java/net/NetworkInterface
    //   30: astore_3
    //   31: aload_3
    //   32: invokevirtual 33	java/net/NetworkInterface:getInetAddresses	()Ljava/util/Enumeration;
    //   35: astore 4
    //   37: aload 4
    //   39: invokeinterface 26 1 0
    //   44: ifeq -32 -> 12
    //   47: aload 4
    //   49: invokeinterface 30 1 0
    //   54: checkcast 35	java/net/InetAddress
    //   57: astore 5
    //   59: aload 5
    //   61: invokevirtual 38	java/net/InetAddress:isLoopbackAddress	()Z
    //   64: ifne -27 -> 37
    //   67: aload 5
    //   69: instanceof 40
    //   72: ifeq -35 -> 37
    //   75: aload_3
    //   76: invokevirtual 43	java/net/NetworkInterface:getDisplayName	()Ljava/lang/String;
    //   79: invokestatic 49	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   82: ifne -45 -> 37
    //   85: aload_3
    //   86: invokevirtual 43	java/net/NetworkInterface:getDisplayName	()Ljava/lang/String;
    //   89: ldc 51
    //   91: invokevirtual 57	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   94: ifne -57 -> 37
    //   97: aload_0
    //   98: aload 5
    //   100: invokevirtual 60	java/net/InetAddress:getHostAddress	()Ljava/lang/String;
    //   103: invokevirtual 63	java/lang/String:toString	()Ljava/lang/String;
    //   106: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: pop
    //   110: goto -73 -> 37
    //   113: aload_0
    //   114: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   117: astore_2
    //   118: aload_2
    //   119: areturn
    //   120: aconst_null
    //   121: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	118	120	java/lang/Exception
  }

  // ERROR //
  public static java.lang.String a(android.content.Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 71
    //   3: invokevirtual 77	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   6: checkcast 79	android/net/ConnectivityManager
    //   9: astore_1
    //   10: ldc 81
    //   12: invokestatic 87	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   15: astore_2
    //   16: ldc 89
    //   18: invokestatic 87	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   21: astore_3
    //   22: aload_2
    //   23: ldc 91
    //   25: iconst_0
    //   26: anewarray 83	java/lang/Class
    //   29: invokevirtual 95	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   32: aload_1
    //   33: iconst_0
    //   34: anewarray 4	java/lang/Object
    //   37: invokevirtual 101	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   40: astore 4
    //   42: aload_3
    //   43: ldc 103
    //   45: iconst_0
    //   46: anewarray 83	java/lang/Class
    //   49: invokevirtual 95	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   52: aload 4
    //   54: iconst_0
    //   55: anewarray 4	java/lang/Object
    //   58: invokevirtual 101	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   61: checkcast 105	java/util/Collection
    //   64: invokeinterface 109 1 0
    //   69: astore 5
    //   71: new 10	java/lang/StringBuilder
    //   74: dup
    //   75: invokespecial 14	java/lang/StringBuilder:<init>	()V
    //   78: astore 6
    //   80: aload 5
    //   82: invokeinterface 114 1 0
    //   87: ifeq +53 -> 140
    //   90: aload 5
    //   92: invokeinterface 117 1 0
    //   97: checkcast 35	java/net/InetAddress
    //   100: astore 8
    //   102: aload 8
    //   104: invokevirtual 38	java/net/InetAddress:isLoopbackAddress	()Z
    //   107: ifne -27 -> 80
    //   110: aload 8
    //   112: instanceof 40
    //   115: ifeq -35 -> 80
    //   118: aload 6
    //   120: aload 8
    //   122: invokevirtual 60	java/net/InetAddress:getHostAddress	()Ljava/lang/String;
    //   125: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   128: pop
    //   129: aload 6
    //   131: ldc 119
    //   133: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   136: pop
    //   137: goto -57 -> 80
    //   140: aload 6
    //   142: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   145: astore 7
    //   147: aload 7
    //   149: areturn
    //   150: aconst_null
    //   151: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   10	147	150	java/lang/Exception
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.e.i
 * JD-Core Version:    0.6.1
 */