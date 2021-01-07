package com.tencent.bugly.proguard;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class h
{
  private StringBuilder a;
  private int b = 0;

  public h(StringBuilder paramStringBuilder, int paramInt)
  {
    this.a = paramStringBuilder;
    this.b = paramInt;
  }

  private <T> h a(T paramT, String paramString)
  {
    if (paramT == null)
    {
      this.a.append("null\n");
    }
    else if ((paramT instanceof Byte))
    {
      int i6 = ((Byte)paramT).byteValue();
      a(paramString);
      StringBuilder localStringBuilder27 = this.a;
      localStringBuilder27.append(i6);
      localStringBuilder27.append('\n');
    }
    else if ((paramT instanceof Boolean))
    {
      boolean bool2 = ((Boolean)paramT).booleanValue();
      a(paramString);
      StringBuilder localStringBuilder26 = this.a;
      char c;
      if (bool2)
        c = 'T';
      else
        c = 'F';
      localStringBuilder26.append(c);
      localStringBuilder26.append('\n');
    }
    else if ((paramT instanceof Short))
    {
      int i5 = ((Short)paramT).shortValue();
      a(paramString);
      StringBuilder localStringBuilder25 = this.a;
      localStringBuilder25.append(i5);
      localStringBuilder25.append('\n');
    }
    else if ((paramT instanceof Integer))
    {
      int i4 = ((Integer)paramT).intValue();
      a(paramString);
      StringBuilder localStringBuilder24 = this.a;
      localStringBuilder24.append(i4);
      localStringBuilder24.append('\n');
    }
    else if ((paramT instanceof Long))
    {
      long l2 = ((Long)paramT).longValue();
      a(paramString);
      StringBuilder localStringBuilder23 = this.a;
      localStringBuilder23.append(l2);
      localStringBuilder23.append('\n');
    }
    else if ((paramT instanceof Float))
    {
      float f2 = ((Float)paramT).floatValue();
      a(paramString);
      StringBuilder localStringBuilder22 = this.a;
      localStringBuilder22.append(f2);
      localStringBuilder22.append('\n');
    }
    else if ((paramT instanceof Double))
    {
      double d2 = ((Double)paramT).doubleValue();
      a(paramString);
      StringBuilder localStringBuilder21 = this.a;
      localStringBuilder21.append(d2);
      localStringBuilder21.append('\n');
    }
    else if ((paramT instanceof String))
    {
      a((String)paramT, paramString);
    }
    else if ((paramT instanceof Map))
    {
      a((Map)paramT, paramString);
    }
    else if ((paramT instanceof List))
    {
      List localList = (List)paramT;
      if (localList == null)
      {
        a(paramString);
        this.a.append("null\t");
      }
      else
      {
        a(localList.toArray(), paramString);
      }
    }
    else if ((paramT instanceof k))
    {
      a((k)paramT, paramString);
    }
    else if ((paramT instanceof byte[]))
    {
      a((byte[])paramT, paramString);
    }
    else if ((paramT instanceof boolean[]))
    {
      a((boolean[])paramT, paramString);
    }
    else
    {
      boolean bool1 = paramT instanceof short[];
      int i = 0;
      if (bool1)
      {
        short[] arrayOfShort = (short[])paramT;
        a(paramString);
        if (arrayOfShort == null)
        {
          this.a.append("null\n");
        }
        else if (arrayOfShort.length == 0)
        {
          StringBuilder localStringBuilder20 = this.a;
          localStringBuilder20.append(arrayOfShort.length);
          localStringBuilder20.append(", []\n");
        }
        else
        {
          StringBuilder localStringBuilder17 = this.a;
          localStringBuilder17.append(arrayOfShort.length);
          localStringBuilder17.append(", [\n");
          h localh5 = new h(this.a, 1 + this.b);
          int i2 = arrayOfShort.length;
          while (i < i2)
          {
            int i3 = arrayOfShort[i];
            localh5.a(null);
            StringBuilder localStringBuilder19 = localh5.a;
            localStringBuilder19.append(i3);
            localStringBuilder19.append('\n');
            i++;
          }
          a(null);
          StringBuilder localStringBuilder18 = this.a;
          localStringBuilder18.append(']');
          localStringBuilder18.append('\n');
        }
      }
      else if ((paramT instanceof int[]))
      {
        int[] arrayOfInt = (int[])paramT;
        a(paramString);
        if (arrayOfInt == null)
        {
          this.a.append("null\n");
        }
        else if (arrayOfInt.length == 0)
        {
          StringBuilder localStringBuilder16 = this.a;
          localStringBuilder16.append(arrayOfInt.length);
          localStringBuilder16.append(", []\n");
        }
        else
        {
          StringBuilder localStringBuilder13 = this.a;
          localStringBuilder13.append(arrayOfInt.length);
          localStringBuilder13.append(", [\n");
          h localh4 = new h(this.a, 1 + this.b);
          int n = arrayOfInt.length;
          while (i < n)
          {
            int i1 = arrayOfInt[i];
            localh4.a(null);
            StringBuilder localStringBuilder15 = localh4.a;
            localStringBuilder15.append(i1);
            localStringBuilder15.append('\n');
            i++;
          }
          a(null);
          StringBuilder localStringBuilder14 = this.a;
          localStringBuilder14.append(']');
          localStringBuilder14.append('\n');
        }
      }
      else if ((paramT instanceof long[]))
      {
        long[] arrayOfLong = (long[])paramT;
        a(paramString);
        if (arrayOfLong == null)
        {
          this.a.append("null\n");
        }
        else if (arrayOfLong.length == 0)
        {
          StringBuilder localStringBuilder12 = this.a;
          localStringBuilder12.append(arrayOfLong.length);
          localStringBuilder12.append(", []\n");
        }
        else
        {
          StringBuilder localStringBuilder9 = this.a;
          localStringBuilder9.append(arrayOfLong.length);
          localStringBuilder9.append(", [\n");
          h localh3 = new h(this.a, 1 + this.b);
          int m = arrayOfLong.length;
          while (i < m)
          {
            long l1 = arrayOfLong[i];
            localh3.a(null);
            StringBuilder localStringBuilder11 = localh3.a;
            localStringBuilder11.append(l1);
            localStringBuilder11.append('\n');
            i++;
          }
          a(null);
          StringBuilder localStringBuilder10 = this.a;
          localStringBuilder10.append(']');
          localStringBuilder10.append('\n');
        }
      }
      else if ((paramT instanceof float[]))
      {
        float[] arrayOfFloat = (float[])paramT;
        a(paramString);
        if (arrayOfFloat == null)
        {
          this.a.append("null\n");
        }
        else if (arrayOfFloat.length == 0)
        {
          StringBuilder localStringBuilder8 = this.a;
          localStringBuilder8.append(arrayOfFloat.length);
          localStringBuilder8.append(", []\n");
        }
        else
        {
          StringBuilder localStringBuilder5 = this.a;
          localStringBuilder5.append(arrayOfFloat.length);
          localStringBuilder5.append(", [\n");
          h localh2 = new h(this.a, 1 + this.b);
          int k = arrayOfFloat.length;
          while (i < k)
          {
            float f1 = arrayOfFloat[i];
            localh2.a(null);
            StringBuilder localStringBuilder7 = localh2.a;
            localStringBuilder7.append(f1);
            localStringBuilder7.append('\n');
            i++;
          }
          a(null);
          StringBuilder localStringBuilder6 = this.a;
          localStringBuilder6.append(']');
          localStringBuilder6.append('\n');
        }
      }
      else if ((paramT instanceof double[]))
      {
        double[] arrayOfDouble = (double[])paramT;
        a(paramString);
        if (arrayOfDouble == null)
        {
          this.a.append("null\n");
        }
        else if (arrayOfDouble.length == 0)
        {
          StringBuilder localStringBuilder4 = this.a;
          localStringBuilder4.append(arrayOfDouble.length);
          localStringBuilder4.append(", []\n");
        }
        else
        {
          StringBuilder localStringBuilder1 = this.a;
          localStringBuilder1.append(arrayOfDouble.length);
          localStringBuilder1.append(", [\n");
          h localh1 = new h(this.a, 1 + this.b);
          int j = arrayOfDouble.length;
          while (i < j)
          {
            double d1 = arrayOfDouble[i];
            localh1.a(null);
            StringBuilder localStringBuilder3 = localh1.a;
            localStringBuilder3.append(d1);
            localStringBuilder3.append('\n');
            i++;
          }
          a(null);
          StringBuilder localStringBuilder2 = this.a;
          localStringBuilder2.append(']');
          localStringBuilder2.append('\n');
        }
      }
      else
      {
        if (!paramT.getClass().isArray())
          break label1504;
        a((Object[])paramT, paramString);
      }
    }
    return this;
    label1504: throw new b("write object error: unsupport type.");
  }

  private <T> h a(T[] paramArrayOfT, String paramString)
  {
    a(paramString);
    if (paramArrayOfT == null)
    {
      this.a.append("null\n");
      return this;
    }
    if (paramArrayOfT.length == 0)
    {
      StringBuilder localStringBuilder3 = this.a;
      localStringBuilder3.append(paramArrayOfT.length);
      localStringBuilder3.append(", []\n");
      return this;
    }
    StringBuilder localStringBuilder1 = this.a;
    localStringBuilder1.append(paramArrayOfT.length);
    localStringBuilder1.append(", [\n");
    h localh = new h(this.a, 1 + this.b);
    int i = paramArrayOfT.length;
    for (int j = 0; j < i; j++)
      localh.a(paramArrayOfT[j], null);
    a(null);
    StringBuilder localStringBuilder2 = this.a;
    localStringBuilder2.append(']');
    localStringBuilder2.append('\n');
    return this;
  }

  private void a(String paramString)
  {
    for (int i = 0; i < this.b; i++)
      this.a.append('\t');
    if (paramString != null)
    {
      StringBuilder localStringBuilder = this.a;
      localStringBuilder.append(paramString);
      localStringBuilder.append(": ");
    }
  }

  public final h a(byte paramByte, String paramString)
  {
    a(paramString);
    StringBuilder localStringBuilder = this.a;
    localStringBuilder.append(paramByte);
    localStringBuilder.append('\n');
    return this;
  }

  public final h a(int paramInt, String paramString)
  {
    a(paramString);
    StringBuilder localStringBuilder = this.a;
    localStringBuilder.append(paramInt);
    localStringBuilder.append('\n');
    return this;
  }

  public final h a(long paramLong, String paramString)
  {
    a(paramString);
    StringBuilder localStringBuilder = this.a;
    localStringBuilder.append(paramLong);
    localStringBuilder.append('\n');
    return this;
  }

  public final h a(k paramk, String paramString)
  {
    a(paramString);
    StringBuilder localStringBuilder1 = this.a;
    localStringBuilder1.append('{');
    localStringBuilder1.append('\n');
    if (paramk == null)
    {
      StringBuilder localStringBuilder3 = this.a;
      localStringBuilder3.append('\t');
      localStringBuilder3.append("null");
    }
    else
    {
      paramk.a(this.a, 1 + this.b);
    }
    a(null);
    StringBuilder localStringBuilder2 = this.a;
    localStringBuilder2.append('}');
    localStringBuilder2.append('\n');
    return this;
  }

  public final h a(String paramString1, String paramString2)
  {
    a(paramString2);
    if (paramString1 == null)
    {
      this.a.append("null\n");
    }
    else
    {
      StringBuilder localStringBuilder = this.a;
      localStringBuilder.append(paramString1);
      localStringBuilder.append('\n');
    }
    return this;
  }

  public final <K, V> h a(Map<K, V> paramMap, String paramString)
  {
    a(paramString);
    if (paramMap == null)
    {
      this.a.append("null\n");
      return this;
    }
    if (paramMap.isEmpty())
    {
      StringBuilder localStringBuilder5 = this.a;
      localStringBuilder5.append(paramMap.size());
      localStringBuilder5.append(", {}\n");
      return this;
    }
    StringBuilder localStringBuilder1 = this.a;
    localStringBuilder1.append(paramMap.size());
    localStringBuilder1.append(", {\n");
    h localh1 = new h(this.a, 1 + this.b);
    h localh2 = new h(this.a, 2 + this.b);
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localh1.a(null);
      StringBuilder localStringBuilder3 = localh1.a;
      localStringBuilder3.append('(');
      localStringBuilder3.append('\n');
      localh2.a(localEntry.getKey(), null);
      localh2.a(localEntry.getValue(), null);
      localh1.a(null);
      StringBuilder localStringBuilder4 = localh1.a;
      localStringBuilder4.append(')');
      localStringBuilder4.append('\n');
    }
    a(null);
    StringBuilder localStringBuilder2 = this.a;
    localStringBuilder2.append('}');
    localStringBuilder2.append('\n');
    return this;
  }

  public final h a(short paramShort, String paramString)
  {
    a(paramString);
    StringBuilder localStringBuilder = this.a;
    localStringBuilder.append(paramShort);
    localStringBuilder.append('\n');
    return this;
  }

  public final h a(boolean paramBoolean, String paramString)
  {
    a(paramString);
    StringBuilder localStringBuilder = this.a;
    char c;
    if (paramBoolean)
      c = 'T';
    else
      c = 'F';
    localStringBuilder.append(c);
    localStringBuilder.append('\n');
    return this;
  }

  public final h a(byte[] paramArrayOfByte, String paramString)
  {
    a(paramString);
    if (paramArrayOfByte == null)
    {
      this.a.append("null\n");
      return this;
    }
    if (paramArrayOfByte.length == 0)
    {
      StringBuilder localStringBuilder4 = this.a;
      localStringBuilder4.append(paramArrayOfByte.length);
      localStringBuilder4.append(", []\n");
      return this;
    }
    StringBuilder localStringBuilder1 = this.a;
    localStringBuilder1.append(paramArrayOfByte.length);
    localStringBuilder1.append(", [\n");
    h localh = new h(this.a, 1 + this.b);
    int i = paramArrayOfByte.length;
    for (int j = 0; j < i; j++)
    {
      int k = paramArrayOfByte[j];
      localh.a(null);
      StringBuilder localStringBuilder3 = localh.a;
      localStringBuilder3.append(k);
      localStringBuilder3.append('\n');
    }
    a(null);
    StringBuilder localStringBuilder2 = this.a;
    localStringBuilder2.append(']');
    localStringBuilder2.append('\n');
    return this;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.h
 * JD-Core Version:    0.6.1
 */