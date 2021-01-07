package com.google.a.b;

import java.lang.reflect.Method;

public abstract class k
{
  // ERROR //
  public static k a()
  {
    // Byte code:
    //   0: ldc 14
    //   2: invokestatic 20	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   5: astore 7
    //   7: aload 7
    //   9: ldc 22
    //   11: invokevirtual 26	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   14: astore 8
    //   16: aload 8
    //   18: iconst_1
    //   19: invokevirtual 32	java/lang/reflect/Field:setAccessible	(Z)V
    //   22: aload 8
    //   24: aconst_null
    //   25: invokevirtual 36	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   28: astore 9
    //   30: new 38	com/google/a/b/k$1
    //   33: dup
    //   34: aload 7
    //   36: ldc 40
    //   38: iconst_1
    //   39: anewarray 16	java/lang/Class
    //   42: dup
    //   43: iconst_0
    //   44: ldc 16
    //   46: aastore
    //   47: invokevirtual 44	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   50: aload 9
    //   52: invokespecial 47	com/google/a/b/k$1:<init>	(Ljava/lang/reflect/Method;Ljava/lang/Object;)V
    //   55: astore 10
    //   57: aload 10
    //   59: areturn
    //   60: ldc 49
    //   62: ldc 51
    //   64: iconst_2
    //   65: anewarray 16	java/lang/Class
    //   68: dup
    //   69: iconst_0
    //   70: ldc 16
    //   72: aastore
    //   73: dup
    //   74: iconst_1
    //   75: ldc 16
    //   77: aastore
    //   78: invokevirtual 54	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   81: astore 5
    //   83: aload 5
    //   85: iconst_1
    //   86: invokevirtual 57	java/lang/reflect/Method:setAccessible	(Z)V
    //   89: new 59	com/google/a/b/k$2
    //   92: dup
    //   93: aload 5
    //   95: invokespecial 62	com/google/a/b/k$2:<init>	(Ljava/lang/reflect/Method;)V
    //   98: astore 6
    //   100: aload 6
    //   102: areturn
    //   103: ldc 64
    //   105: ldc 66
    //   107: iconst_1
    //   108: anewarray 16	java/lang/Class
    //   111: dup
    //   112: iconst_0
    //   113: ldc 16
    //   115: aastore
    //   116: invokevirtual 54	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   119: astore_0
    //   120: aload_0
    //   121: iconst_1
    //   122: invokevirtual 57	java/lang/reflect/Method:setAccessible	(Z)V
    //   125: aload_0
    //   126: aconst_null
    //   127: iconst_1
    //   128: anewarray 4	java/lang/Object
    //   131: dup
    //   132: iconst_0
    //   133: ldc 4
    //   135: aastore
    //   136: invokevirtual 70	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   139: checkcast 72	java/lang/Integer
    //   142: invokevirtual 76	java/lang/Integer:intValue	()I
    //   145: istore_1
    //   146: iconst_2
    //   147: anewarray 16	java/lang/Class
    //   150: astore_2
    //   151: aload_2
    //   152: iconst_0
    //   153: ldc 16
    //   155: aastore
    //   156: aload_2
    //   157: iconst_1
    //   158: getstatic 80	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   161: aastore
    //   162: ldc 64
    //   164: ldc 51
    //   166: aload_2
    //   167: invokevirtual 54	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   170: astore_3
    //   171: aload_3
    //   172: iconst_1
    //   173: invokevirtual 57	java/lang/reflect/Method:setAccessible	(Z)V
    //   176: new 82	com/google/a/b/k$3
    //   179: dup
    //   180: aload_3
    //   181: iload_1
    //   182: invokespecial 85	com/google/a/b/k$3:<init>	(Ljava/lang/reflect/Method;I)V
    //   185: astore 4
    //   187: aload 4
    //   189: areturn
    //   190: new 87	com/google/a/b/k$4
    //   193: dup
    //   194: invokespecial 88	com/google/a/b/k$4:<init>	()V
    //   197: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	57	60	java/lang/Exception
    //   60	100	103	java/lang/Exception
    //   103	187	190	java/lang/Exception
  }

  public abstract <T> T a(Class<T> paramClass);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.b.k
 * JD-Core Version:    0.6.1
 */