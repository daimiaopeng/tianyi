package com.stub.plugin;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Stub04 extends ContentProvider
{
  private Map<String, BusiItem> delegates = new HashMap();

  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    return 0;
  }

  public String getType(Uri paramUri)
  {
    try
    {
      Iterator localIterator = this.delegates.values().iterator();
      while (localIterator.hasNext())
      {
        BusiItem localBusiItem = (BusiItem)localIterator.next();
        Method localMethod = ReflectionUtil.getMethod(localBusiItem.getDelegateClz(), "getType", new Class[] { Uri.class });
        ReflectionUtil.invoke(localBusiItem.getDelegateImpl(), localMethod, new Object[] { paramUri });
      }
    }
    catch (Throwable localThrowable)
    {
    }
    return null;
  }

  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    try
    {
      Iterator localIterator = this.delegates.values().iterator();
      while (localIterator.hasNext())
      {
        BusiItem localBusiItem = (BusiItem)localIterator.next();
        Method localMethod = ReflectionUtil.getMethod(localBusiItem.getDelegateClz(), "insert", new Class[] { Uri.class, ContentValues.class });
        ReflectionUtil.invoke(localBusiItem.getDelegateImpl(), localMethod, new Object[] { paramUri, paramContentValues });
      }
    }
    catch (Throwable localThrowable)
    {
    }
    return null;
  }

  // ERROR //
  public boolean onCreate()
  {
    // Byte code:
    //   0: new 46	com/stub/plugin/BusiItem
    //   3: dup
    //   4: invokespecial 81	com/stub/plugin/BusiItem:<init>	()V
    //   7: astore_1
    //   8: aload_1
    //   9: ldc 83
    //   11: invokestatic 87	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   14: invokevirtual 91	com/stub/plugin/BusiItem:setDelegateClz	(Ljava/lang/Class;)V
    //   17: aload_1
    //   18: invokevirtual 50	com/stub/plugin/BusiItem:getDelegateClz	()Ljava/lang/Class;
    //   21: astore 4
    //   23: aload 4
    //   25: ifnull +101 -> 126
    //   28: aload_1
    //   29: aload_1
    //   30: invokevirtual 50	com/stub/plugin/BusiItem:getDelegateClz	()Ljava/lang/Class;
    //   33: invokevirtual 94	java/lang/Class:newInstance	()Ljava/lang/Object;
    //   36: invokevirtual 98	com/stub/plugin/BusiItem:setDelegateImpl	(Ljava/lang/Object;)V
    //   39: aload_0
    //   40: getfield 16	com/stub/plugin/Stub04:delegates	Ljava/util/Map;
    //   43: ldc 100
    //   45: aload_1
    //   46: invokeinterface 104 3 0
    //   51: pop
    //   52: aload_1
    //   53: invokevirtual 64	com/stub/plugin/BusiItem:getDelegateImpl	()Ljava/lang/Object;
    //   56: ifnull +70 -> 126
    //   59: aload_1
    //   60: invokevirtual 50	com/stub/plugin/BusiItem:getDelegateClz	()Ljava/lang/Class;
    //   63: ifnull +63 -> 126
    //   66: aload_1
    //   67: invokevirtual 50	com/stub/plugin/BusiItem:getDelegateClz	()Ljava/lang/Class;
    //   70: ldc 105
    //   72: iconst_1
    //   73: anewarray 53	java/lang/Class
    //   76: dup
    //   77: iconst_0
    //   78: ldc 4
    //   80: aastore
    //   81: invokestatic 61	com/stub/plugin/ReflectionUtil:getMethod	(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   84: astore 8
    //   86: aload_1
    //   87: invokevirtual 64	com/stub/plugin/BusiItem:getDelegateImpl	()Ljava/lang/Object;
    //   90: aload 8
    //   92: iconst_1
    //   93: anewarray 66	java/lang/Object
    //   96: dup
    //   97: iconst_0
    //   98: aload_0
    //   99: aastore
    //   100: invokestatic 70	com/stub/plugin/ReflectionUtil:invoke	(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;
    //   103: astore 9
    //   105: aload 9
    //   107: ifnull +19 -> 126
    //   110: aload 9
    //   112: checkcast 107	java/lang/Boolean
    //   115: invokevirtual 110	java/lang/Boolean:booleanValue	()Z
    //   118: istore 10
    //   120: iload 10
    //   122: istore_3
    //   123: iload_3
    //   124: ireturn
    //   125: astore_2
    //   126: iconst_0
    //   127: istore_3
    //   128: goto -5 -> 123
    //   131: astore 6
    //   133: goto -7 -> 126
    //   136: astore 5
    //   138: goto -12 -> 126
    //
    // Exception table:
    //   from	to	target	type
    //   0	23	125	java/lang/Throwable
    //   28	120	125	java/lang/Throwable
    //   28	120	131	java/lang/IllegalAccessException
    //   28	120	136	java/lang/InstantiationException
  }

  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    return null;
  }

  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    return 0;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.stub.plugin.Stub04
 * JD-Core Version:    0.6.1
 */