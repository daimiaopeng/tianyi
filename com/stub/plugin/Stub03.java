package com.stub.plugin;

import android.content.BroadcastReceiver;
import java.util.HashMap;
import java.util.Map;

public class Stub03 extends BroadcastReceiver
{
  private Map<String, BusiItem> delegates = new HashMap();

  // ERROR //
  public void onReceive(android.content.Context paramContext, android.content.Intent paramIntent)
  {
    // Byte code:
    //   0: aload_2
    //   1: ifnull +184 -> 185
    //   4: aload_2
    //   5: ldc 28
    //   7: invokevirtual 34	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   10: astore 4
    //   12: new 36	com/stub/plugin/BusiItem
    //   15: dup
    //   16: invokespecial 37	com/stub/plugin/BusiItem:<init>	()V
    //   19: astore 5
    //   21: aload 4
    //   23: ifnull +162 -> 185
    //   26: aload_0
    //   27: getfield 16	com/stub/plugin/Stub03:delegates	Ljava/util/Map;
    //   30: aload 4
    //   32: invokeinterface 43 2 0
    //   37: istore 6
    //   39: iload 6
    //   41: ifne +106 -> 147
    //   44: aload 5
    //   46: aload 4
    //   48: invokestatic 49	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   51: invokevirtual 53	com/stub/plugin/BusiItem:setDelegateClz	(Ljava/lang/Class;)V
    //   54: aload 5
    //   56: invokevirtual 57	com/stub/plugin/BusiItem:getDelegateClz	()Ljava/lang/Class;
    //   59: astore 10
    //   61: aload 10
    //   63: ifnull +16 -> 79
    //   66: aload 5
    //   68: aload 5
    //   70: invokevirtual 57	com/stub/plugin/BusiItem:getDelegateClz	()Ljava/lang/Class;
    //   73: invokevirtual 61	java/lang/Class:newInstance	()Ljava/lang/Object;
    //   76: invokevirtual 65	com/stub/plugin/BusiItem:setDelegateImpl	(Ljava/lang/Object;)V
    //   79: aload 5
    //   81: invokevirtual 68	com/stub/plugin/BusiItem:getDelegateImpl	()Ljava/lang/Object;
    //   84: ifnull +101 -> 185
    //   87: aload 5
    //   89: invokevirtual 57	com/stub/plugin/BusiItem:getDelegateClz	()Ljava/lang/Class;
    //   92: ifnull +93 -> 185
    //   95: aload 5
    //   97: invokevirtual 57	com/stub/plugin/BusiItem:getDelegateClz	()Ljava/lang/Class;
    //   100: ldc 69
    //   102: iconst_2
    //   103: anewarray 45	java/lang/Class
    //   106: dup
    //   107: iconst_0
    //   108: ldc 71
    //   110: aastore
    //   111: dup
    //   112: iconst_1
    //   113: ldc 30
    //   115: aastore
    //   116: invokestatic 77	com/stub/plugin/ReflectionUtil:getMethod	(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   119: astore 7
    //   121: aload 5
    //   123: invokevirtual 68	com/stub/plugin/BusiItem:getDelegateImpl	()Ljava/lang/Object;
    //   126: aload 7
    //   128: iconst_2
    //   129: anewarray 79	java/lang/Object
    //   132: dup
    //   133: iconst_0
    //   134: aload_1
    //   135: aastore
    //   136: dup
    //   137: iconst_1
    //   138: aload_2
    //   139: aastore
    //   140: invokestatic 83	com/stub/plugin/ReflectionUtil:invoke	(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;
    //   143: pop
    //   144: goto +41 -> 185
    //   147: aload_0
    //   148: getfield 16	com/stub/plugin/Stub03:delegates	Ljava/util/Map;
    //   151: aload 4
    //   153: invokeinterface 87 2 0
    //   158: checkcast 36	com/stub/plugin/BusiItem
    //   161: astore 5
    //   163: goto -84 -> 79
    //   166: astore_3
    //   167: goto +18 -> 185
    //   170: astore 12
    //   172: goto -93 -> 79
    //   175: astore 11
    //   177: goto -98 -> 79
    //   180: astore 9
    //   182: goto -128 -> 54
    //   185: return
    //
    // Exception table:
    //   from	to	target	type
    //   4	39	166	java/lang/Throwable
    //   44	54	166	java/lang/Throwable
    //   54	61	166	java/lang/Throwable
    //   66	79	166	java/lang/Throwable
    //   79	163	166	java/lang/Throwable
    //   66	79	170	java/lang/IllegalAccessException
    //   66	79	175	java/lang/InstantiationException
    //   44	54	180	java/lang/ClassNotFoundException
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.stub.plugin.Stub03
 * JD-Core Version:    0.6.1
 */