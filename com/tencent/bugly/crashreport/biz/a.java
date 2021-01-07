package com.tencent.bugly.crashreport.biz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.am;
import com.tencent.bugly.proguard.ar;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.t;
import com.tencent.bugly.proguard.u;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class a
{
  private Context a;
  private long b;
  private int c;
  private boolean d = true;

  public a(Context paramContext, boolean paramBoolean)
  {
    this.a = paramContext;
    this.d = paramBoolean;
  }

  private static ContentValues a(UserInfoBean paramUserInfoBean)
  {
    if (paramUserInfoBean == null)
      return null;
    try
    {
      ContentValues localContentValues = new ContentValues();
      if (paramUserInfoBean.a > 0L)
        localContentValues.put("_id", Long.valueOf(paramUserInfoBean.a));
      localContentValues.put("_tm", Long.valueOf(paramUserInfoBean.e));
      localContentValues.put("_ut", Long.valueOf(paramUserInfoBean.f));
      localContentValues.put("_tp", Integer.valueOf(paramUserInfoBean.b));
      localContentValues.put("_pc", paramUserInfoBean.c);
      localContentValues.put("_dt", z.a(paramUserInfoBean));
      return localContentValues;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return null;
  }

  private static UserInfoBean a(Cursor paramCursor)
  {
    if (paramCursor == null)
      return null;
    try
    {
      byte[] arrayOfByte = paramCursor.getBlob(paramCursor.getColumnIndex("_dt"));
      if (arrayOfByte == null)
        return null;
      long l = paramCursor.getLong(paramCursor.getColumnIndex("_id"));
      UserInfoBean localUserInfoBean = (UserInfoBean)z.a(arrayOfByte, UserInfoBean.CREATOR);
      if (localUserInfoBean != null)
        localUserInfoBean.a = l;
      return localUserInfoBean;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return null;
  }

  private static void a(List<UserInfoBean> paramList)
  {
    if ((paramList != null) && (paramList.size() != 0))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      for (int i = 0; (i < paramList.size()) && (i < 50); i++)
      {
        UserInfoBean localUserInfoBean = (UserInfoBean)paramList.get(i);
        localStringBuilder.append(" or _id");
        localStringBuilder.append(" = ");
        localStringBuilder.append(localUserInfoBean.a);
      }
      String str1 = localStringBuilder.toString();
      if (str1.length() > 0)
        str1 = str1.substring(4);
      String str2 = str1;
      localStringBuilder.setLength(0);
      try
      {
        int j = p.a().a("t_ui", str2, null, null, true);
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = "t_ui";
        arrayOfObject[1] = Integer.valueOf(j);
        x.c("[Database] deleted %s data %d", arrayOfObject);
        return;
      }
      catch (Throwable localThrowable)
      {
        if (!x.a(localThrowable))
          localThrowable.printStackTrace();
        return;
      }
    }
  }

  private void c()
  {
    while (true)
    {
      int i3;
      int i4;
      int n;
      try
      {
        boolean bool1 = this.d;
        if (!bool1)
          return;
        u localu1 = u.a();
        if (localu1 == null)
          return;
        com.tencent.bugly.crashreport.common.strategy.a locala = com.tencent.bugly.crashreport.common.strategy.a.a();
        if (locala == null)
          return;
        if (locala.b())
        {
          boolean bool3 = localu1.b(1001);
          if (!bool3)
            return;
        }
        String str1 = com.tencent.bugly.crashreport.common.info.a.a(this.a).d;
        ArrayList localArrayList = new ArrayList();
        Object localObject2 = a(str1);
        if (localObject2 != null)
        {
          int m = -20 + ((List)localObject2).size();
          if (m > 0)
          {
            i1 = 0;
            if (i1 >= ((List)localObject2).size() - 1)
              break label755;
            i3 = i1 + 1;
            i4 = i3;
            if (i4 >= ((List)localObject2).size())
              break label748;
            if (((UserInfoBean)((List)localObject2).get(i1)).e <= ((UserInfoBean)((List)localObject2).get(i4)).e)
              break label742;
            UserInfoBean localUserInfoBean2 = (UserInfoBean)((List)localObject2).get(i1);
            ((List)localObject2).set(i1, ((List)localObject2).get(i4));
            ((List)localObject2).set(i4, localUserInfoBean2);
            break label742;
            if (i2 < m)
            {
              localArrayList.add(((List)localObject2).get(i2));
              i2++;
              continue;
            }
          }
          Iterator localIterator = ((List)localObject2).iterator();
          n = 0;
          if (localIterator.hasNext())
          {
            UserInfoBean localUserInfoBean1 = (UserInfoBean)localIterator.next();
            if (localUserInfoBean1.f != -1L)
            {
              localIterator.remove();
              if (localUserInfoBean1.e < z.b())
                localArrayList.add(localUserInfoBean1);
            }
            if (localUserInfoBean1.e <= System.currentTimeMillis() - 600000L)
              continue;
            if ((localUserInfoBean1.b == 1) || (localUserInfoBean1.b == 4))
              break label761;
            if (localUserInfoBean1.b != 3)
              continue;
            break label761;
          }
          if (n <= 15)
            break label767;
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = Integer.valueOf(n);
          x.d("[UserInfo] Upload user info too many times in 10 min: %d", arrayOfObject2);
          i = 0;
        }
        else
        {
          localObject2 = new ArrayList();
          break label767;
        }
        if (localArrayList.size() > 0)
          a(localArrayList);
        if ((i != 0) && (((List)localObject2).size() != 0))
        {
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = Integer.valueOf(((List)localObject2).size());
          x.c("[UserInfo] Upload user info(size: %d)", arrayOfObject1);
          if (this.c != 1)
            break label773;
          j = 1;
          ar localar = com.tencent.bugly.proguard.a.a((List)localObject2, j);
          if (localar == null)
          {
            x.d("[UserInfo] Failed to create UserInfoPackage.", new Object[0]);
            return;
          }
          byte[] arrayOfByte = com.tencent.bugly.proguard.a.a(localar);
          if (arrayOfByte == null)
          {
            x.d("[UserInfo] Failed to encode data.", new Object[0]);
            return;
          }
          if (!localu1.a)
            break label779;
          k = 840;
          am localam = com.tencent.bugly.proguard.a.a(this.a, k, arrayOfByte);
          if (localam == null)
          {
            x.d("[UserInfo] Request package is null.", new Object[0]);
            return;
          }
          t local1 = new t()
          {
            public final void a(boolean paramAnonymousBoolean)
            {
              if (paramAnonymousBoolean)
              {
                x.c("[UserInfo] Successfully uploaded user info.", new Object[0]);
                long l = System.currentTimeMillis();
                Iterator localIterator = this.a.iterator();
                while (localIterator.hasNext())
                {
                  UserInfoBean localUserInfoBean = (UserInfoBean)localIterator.next();
                  localUserInfoBean.f = l;
                  a.a(a.this, localUserInfoBean, true);
                }
              }
            }
          };
          StrategyBean localStrategyBean = com.tencent.bugly.crashreport.common.strategy.a.a().c();
          String str2;
          if (localu1.a)
            str2 = localStrategyBean.r;
          else
            str2 = localStrategyBean.t;
          String str3 = str2;
          String str4;
          if (localu1.a)
            str4 = StrategyBean.b;
          else
            str4 = StrategyBean.a;
          String str5 = str4;
          u localu2 = u.a();
          if (this.c != 1)
            break label787;
          bool2 = true;
          localu2.a(1001, localam, str3, str5, local1, bool2);
          return;
        }
        x.c("[UserInfo] There is no user info in local database.", new Object[0]);
        return;
      }
      finally
      {
      }
      label742: i4++;
      continue;
      label748: int i1 = i3;
      continue;
      label755: int i2 = 0;
      continue;
      label761: n++;
      continue;
      label767: int i = 1;
      continue;
      label773: int j = 2;
      continue;
      label779: int k = 640;
      continue;
      label787: boolean bool2 = false;
    }
  }

  // ERROR //
  public final List<UserInfoBean> a(String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 310	com/tencent/bugly/proguard/z:a	(Ljava/lang/String;)Z
    //   4: ifeq +9 -> 13
    //   7: aconst_null
    //   8: astore 6
    //   10: goto +38 -> 48
    //   13: new 157	java/lang/StringBuilder
    //   16: dup
    //   17: ldc_w 312
    //   20: invokespecial 315	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   23: astore 22
    //   25: aload 22
    //   27: aload_1
    //   28: invokevirtual 168	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: pop
    //   32: aload 22
    //   34: ldc_w 317
    //   37: invokevirtual 168	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: pop
    //   41: aload 22
    //   43: invokevirtual 177	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   46: astore 6
    //   48: invokestatic 145	com/tencent/bugly/proguard/p:a	()Lcom/tencent/bugly/proguard/p;
    //   51: ldc 147
    //   53: aconst_null
    //   54: aload 6
    //   56: aconst_null
    //   57: aconst_null
    //   58: iconst_1
    //   59: invokevirtual 320	com/tencent/bugly/proguard/p:a	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Lcom/tencent/bugly/proguard/o;Z)Landroid/database/Cursor;
    //   62: astore_3
    //   63: aload_3
    //   64: ifnonnull +15 -> 79
    //   67: aload_3
    //   68: ifnull +9 -> 77
    //   71: aload_3
    //   72: invokeinterface 323 1 0
    //   77: aconst_null
    //   78: areturn
    //   79: new 157	java/lang/StringBuilder
    //   82: dup
    //   83: invokespecial 158	java/lang/StringBuilder:<init>	()V
    //   86: astore 7
    //   88: new 216	java/util/ArrayList
    //   91: dup
    //   92: invokespecial 217	java/util/ArrayList:<init>	()V
    //   95: astore 8
    //   97: aload_3
    //   98: invokeinterface 326 1 0
    //   103: ifeq +84 -> 187
    //   106: aload_3
    //   107: invokestatic 328	com/tencent/bugly/crashreport/biz/a:a	(Landroid/database/Cursor;)Lcom/tencent/bugly/crashreport/biz/UserInfoBean;
    //   110: astore 14
    //   112: aload 14
    //   114: ifnull +16 -> 130
    //   117: aload 8
    //   119: aload 14
    //   121: invokeinterface 225 2 0
    //   126: pop
    //   127: goto -30 -> 97
    //   130: aload_3
    //   131: aload_3
    //   132: ldc 33
    //   134: invokeinterface 98 2 0
    //   139: invokeinterface 106 2 0
    //   144: lstore 16
    //   146: aload 7
    //   148: ldc 164
    //   150: invokevirtual 168	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   153: pop
    //   154: aload 7
    //   156: ldc 170
    //   158: invokevirtual 168	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   161: pop
    //   162: aload 7
    //   164: lload 16
    //   166: invokevirtual 173	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   169: pop
    //   170: goto -73 -> 97
    //   173: ldc_w 330
    //   176: iconst_0
    //   177: anewarray 4	java/lang/Object
    //   180: invokestatic 257	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   183: pop
    //   184: goto -87 -> 97
    //   187: aload 7
    //   189: invokevirtual 177	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   192: astore 9
    //   194: aload 9
    //   196: invokevirtual 182	java/lang/String:length	()I
    //   199: ifle +56 -> 255
    //   202: aload 9
    //   204: iconst_4
    //   205: invokevirtual 186	java/lang/String:substring	(I)Ljava/lang/String;
    //   208: astore 10
    //   210: invokestatic 145	com/tencent/bugly/proguard/p:a	()Lcom/tencent/bugly/proguard/p;
    //   213: ldc 147
    //   215: aload 10
    //   217: aconst_null
    //   218: aconst_null
    //   219: iconst_1
    //   220: invokevirtual 193	com/tencent/bugly/proguard/p:a	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Lcom/tencent/bugly/proguard/o;Z)I
    //   223: istore 11
    //   225: iconst_2
    //   226: anewarray 4	java/lang/Object
    //   229: astore 12
    //   231: aload 12
    //   233: iconst_0
    //   234: ldc 147
    //   236: aastore
    //   237: aload 12
    //   239: iconst_1
    //   240: iload 11
    //   242: invokestatic 62	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   245: aastore
    //   246: ldc_w 332
    //   249: aload 12
    //   251: invokestatic 257	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   254: pop
    //   255: aload_3
    //   256: ifnull +9 -> 265
    //   259: aload_3
    //   260: invokeinterface 323 1 0
    //   265: aload 8
    //   267: areturn
    //   268: astore_2
    //   269: goto +17 -> 286
    //   272: astore 5
    //   274: aload 5
    //   276: astore 4
    //   278: aconst_null
    //   279: astore_3
    //   280: goto +31 -> 311
    //   283: astore_2
    //   284: aconst_null
    //   285: astore_3
    //   286: aload_2
    //   287: invokestatic 88	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   290: ifne +7 -> 297
    //   293: aload_2
    //   294: invokevirtual 91	java/lang/Throwable:printStackTrace	()V
    //   297: aload_3
    //   298: ifnull +9 -> 307
    //   301: aload_3
    //   302: invokeinterface 323 1 0
    //   307: aconst_null
    //   308: areturn
    //   309: astore 4
    //   311: aload_3
    //   312: ifnull +9 -> 321
    //   315: aload_3
    //   316: invokeinterface 323 1 0
    //   321: aload 4
    //   323: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   130	170	173	java/lang/Throwable
    //   79	127	268	java/lang/Throwable
    //   173	255	268	java/lang/Throwable
    //   0	63	272	finally
    //   0	63	283	java/lang/Throwable
    //   79	127	309	finally
    //   130	170	309	finally
    //   173	255	309	finally
    //   286	297	309	finally
  }

  public final void a()
  {
    this.b = (86400000L + z.b());
    w.a().a(new b(), 5000L + (this.b - System.currentTimeMillis()));
  }

  public final void a(int paramInt, boolean paramBoolean, long paramLong)
  {
    com.tencent.bugly.crashreport.common.strategy.a locala = com.tencent.bugly.crashreport.common.strategy.a.a();
    if ((locala != null) && (!locala.c().h) && (paramInt != 1) && (paramInt != 3))
    {
      x.e("UserInfo is disable", new Object[0]);
      return;
    }
    if ((paramInt == 1) || (paramInt == 3))
      this.c = (1 + this.c);
    com.tencent.bugly.crashreport.common.info.a locala1 = com.tencent.bugly.crashreport.common.info.a.a(this.a);
    UserInfoBean localUserInfoBean = new UserInfoBean();
    localUserInfoBean.b = paramInt;
    localUserInfoBean.c = locala1.d;
    localUserInfoBean.d = locala1.g();
    localUserInfoBean.e = System.currentTimeMillis();
    localUserInfoBean.f = -1L;
    localUserInfoBean.n = locala1.j;
    int i = 0;
    if (paramInt == 1)
      i = 1;
    localUserInfoBean.o = i;
    localUserInfoBean.l = locala1.a();
    localUserInfoBean.m = locala1.p;
    localUserInfoBean.g = locala1.q;
    localUserInfoBean.h = locala1.r;
    localUserInfoBean.i = locala1.s;
    localUserInfoBean.k = locala1.t;
    localUserInfoBean.r = locala1.B();
    localUserInfoBean.s = locala1.G();
    localUserInfoBean.p = locala1.H();
    localUserInfoBean.q = locala1.I();
    w.a().a(new a(localUserInfoBean, paramBoolean), 0L);
  }

  public final void b()
  {
    w localw = w.a();
    if (localw != null)
      localw.a(new Runnable()
      {
        public final void run()
        {
          try
          {
            a.a(a.this);
            return;
          }
          catch (Throwable localThrowable)
          {
            x.a(localThrowable);
          }
        }
      });
  }

  final class a
    implements Runnable
  {
    private boolean a;
    private UserInfoBean b;

    public a(UserInfoBean paramBoolean, boolean arg3)
    {
      this.b = paramBoolean;
      boolean bool;
      this.a = bool;
    }

    public final void run()
    {
      try
      {
        if (this.b != null)
        {
          UserInfoBean localUserInfoBean = this.b;
          if (localUserInfoBean != null)
          {
            com.tencent.bugly.crashreport.common.info.a locala1 = com.tencent.bugly.crashreport.common.info.a.b();
            if (locala1 != null)
              localUserInfoBean.j = locala1.e();
          }
          x.c("[UserInfo] Record user info.", new Object[0]);
          a.a(a.this, this.b, false);
        }
        if (this.a)
        {
          a locala = a.this;
          w localw = w.a();
          if (localw != null)
            localw.a(new a.2(locala));
        }
        return;
      }
      catch (Throwable localThrowable)
      {
        if (!x.a(localThrowable))
          localThrowable.printStackTrace();
      }
    }
  }

  final class b
    implements Runnable
  {
    b()
    {
    }

    public final void run()
    {
      long l = System.currentTimeMillis();
      if (l < a.b(a.this))
      {
        w.a().a(new b(a.this), 5000L + (a.b(a.this) - l));
        return;
      }
      a.this.a(3, false, 0L);
      a.this.a();
    }
  }

  final class c
    implements Runnable
  {
    private long a = 21600000L;

    public c(long arg2)
    {
      Object localObject;
      this.a = localObject;
    }

    public final void run()
    {
      a locala1 = a.this;
      w localw = w.a();
      if (localw != null)
        localw.a(new a.2(locala1));
      a locala2 = a.this;
      long l = this.a;
      w.a().a(new c(locala2, l), l);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.crashreport.biz.a
 * JD-Core Version:    0.6.1
 */