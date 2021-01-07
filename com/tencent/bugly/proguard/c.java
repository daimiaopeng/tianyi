package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class c extends a
{
  protected HashMap<String, byte[]> d = null;
  private HashMap<String, Object> e = new HashMap();
  private i f = new i();

  public <T> void a(String paramString, T paramT)
  {
    if (this.d != null)
    {
      if (paramString == null)
        throw new IllegalArgumentException("put key can not is null");
      if (paramT == null)
        throw new IllegalArgumentException("put value can not is null");
      if ((paramT instanceof Set))
        throw new IllegalArgumentException("can not support Set");
      j localj = new j();
      localj.a(this.b);
      localj.a(paramT, 0);
      byte[] arrayOfByte = l.a(localj.a());
      this.d.put(paramString, arrayOfByte);
      return;
    }
    super.a(paramString, paramT);
  }

  // ERROR //
  public void a(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial 76	com/tencent/bugly/proguard/a:a	([B)V
    //   5: return
    //   6: aload_0
    //   7: getfield 27	com/tencent/bugly/proguard/c:f	Lcom/tencent/bugly/proguard/i;
    //   10: aload_1
    //   11: invokevirtual 77	com/tencent/bugly/proguard/i:a	([B)V
    //   14: aload_0
    //   15: getfield 27	com/tencent/bugly/proguard/c:f	Lcom/tencent/bugly/proguard/i;
    //   18: aload_0
    //   19: getfield 51	com/tencent/bugly/proguard/c:b	Ljava/lang/String;
    //   22: invokevirtual 78	com/tencent/bugly/proguard/i:a	(Ljava/lang/String;)I
    //   25: pop
    //   26: new 19	java/util/HashMap
    //   29: dup
    //   30: iconst_1
    //   31: invokespecial 81	java/util/HashMap:<init>	(I)V
    //   34: astore_3
    //   35: aload_3
    //   36: ldc 83
    //   38: iconst_0
    //   39: newarray byte
    //   41: invokevirtual 69	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   44: pop
    //   45: aload_0
    //   46: aload_0
    //   47: getfield 27	com/tencent/bugly/proguard/c:f	Lcom/tencent/bugly/proguard/i;
    //   50: aload_3
    //   51: iconst_0
    //   52: iconst_0
    //   53: invokevirtual 86	com/tencent/bugly/proguard/i:a	(Ljava/util/Map;IZ)Ljava/util/HashMap;
    //   56: putfield 17	com/tencent/bugly/proguard/c:d	Ljava/util/HashMap;
    //   59: return
    //
    // Exception table:
    //   from	to	target	type
    //   0	5	6	java/lang/Exception
  }

  public byte[] a()
  {
    if (this.d != null)
    {
      j localj = new j(0);
      localj.a(this.b);
      localj.a(this.d, 0);
      return l.a(localj.a());
    }
    return super.a();
  }

  public final <T> T b(String paramString, T paramT)
  {
    if (this.d != null)
    {
      if (!this.d.containsKey(paramString))
        return null;
      if (this.e.containsKey(paramString))
        return this.e.get(paramString);
      byte[] arrayOfByte2 = (byte[])this.d.get(paramString);
      try
      {
        this.f.a(arrayOfByte2);
        this.f.a(this.b);
        Object localObject2 = this.f.a(paramT, 0, true);
        if (localObject2 != null)
          this.e.put(paramString, localObject2);
        return localObject2;
      }
      catch (Exception localException2)
      {
        throw new b(localException2);
      }
    }
    if (!this.a.containsKey(paramString))
      return null;
    if (this.e.containsKey(paramString))
      return this.e.get(paramString);
    HashMap localHashMap = (HashMap)this.a.get(paramString);
    byte[] arrayOfByte1 = new byte[0];
    Iterator localIterator = localHashMap.entrySet().iterator();
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localEntry.getKey();
      arrayOfByte1 = (byte[])localEntry.getValue();
    }
    try
    {
      this.f.a(arrayOfByte1);
      this.f.a(this.b);
      Object localObject1 = this.f.a(paramT, 0, true);
      this.e.put(paramString, localObject1);
      return localObject1;
    }
    catch (Exception localException1)
    {
      throw new b(localException1);
    }
  }

  public void b()
  {
    this.d = new HashMap();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.c
 * JD-Core Version:    0.6.1
 */