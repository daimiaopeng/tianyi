package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class a
  implements NativeExceptionHandler
{
  private final Context a;
  private final com.tencent.bugly.crashreport.crash.b b;
  private final com.tencent.bugly.crashreport.common.info.a c;
  private final com.tencent.bugly.crashreport.common.strategy.a d;

  public a(Context paramContext, com.tencent.bugly.crashreport.common.info.a parama, com.tencent.bugly.crashreport.crash.b paramb, com.tencent.bugly.crashreport.common.strategy.a parama1)
  {
    this.a = paramContext;
    this.b = paramb;
    this.c = parama;
    this.d = parama1;
  }

  public final void handleNativeException(int paramInt1, int paramInt2, long paramLong1, long paramLong2, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt3, String paramString5, int paramInt4, int paramInt5, int paramInt6, String paramString6, String paramString7)
  {
    x.a("Native Crash Happen v1", new Object[0]);
    handleNativeException2(paramInt1, paramInt2, paramLong1, paramLong2, paramString1, paramString2, paramString3, paramString4, paramInt3, paramString5, paramInt4, paramInt5, paramInt6, paramString6, paramString7, null);
  }

  public final void handleNativeException2(int paramInt1, int paramInt2, long paramLong1, long paramLong2, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt3, String paramString5, int paramInt4, int paramInt5, int paramInt6, String paramString6, String paramString7, String[] paramArrayOfString)
  {
    x.a("Native Crash Happen v2", new Object[0]);
    while (true)
    {
      try
      {
        boolean bool1 = this.d.b();
        if (!bool1)
          try
          {
            x.e("waiting for remote sync", new Object[0]);
            int k = 0;
            if (!this.d.b())
            {
              z.b(500L);
              k += 500;
              if (k < 3000)
                continue;
            }
          }
          catch (Throwable localThrowable5)
          {
            localObject1 = localThrowable5;
            break label1047;
          }
        String str1 = b.a(paramString3);
        String str2 = "UNKNOWN";
        String str3;
        Object localObject2;
        String str4;
        if (paramInt3 > 0)
        {
          StringBuilder localStringBuilder6 = new StringBuilder();
          localStringBuilder6.append(paramString1);
          localStringBuilder6.append("(");
          localStringBuilder6.append(paramString5);
          localStringBuilder6.append(")");
          str3 = localStringBuilder6.toString();
          localObject2 = str2;
          str4 = "KERNEL";
        }
        else
        {
          if (paramInt4 > 0)
            str2 = AppInfo.a(paramInt4);
          boolean bool2 = str2.equals(String.valueOf(paramInt4));
          if (!bool2)
          {
            StringBuilder localStringBuilder5 = new StringBuilder();
            localStringBuilder5.append(str2);
            localStringBuilder5.append("(");
            localStringBuilder5.append(paramInt4);
            localStringBuilder5.append(")");
            String str16 = localStringBuilder5.toString();
            localObject2 = str16;
          }
          else
          {
            localObject2 = str2;
          }
          str3 = paramString1;
          str4 = paramString5;
        }
        boolean bool3 = this.d.b();
        if (!bool3)
          x.d("no remote but still store!", new Object[0]);
        boolean bool4 = this.d.c().g;
        if ((!bool4) && (this.d.b()))
        {
          x.e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
          String str14 = z.a();
          String str15 = this.c.d;
          Thread localThread4 = Thread.currentThread();
          StringBuilder localStringBuilder4 = new StringBuilder();
          localStringBuilder4.append(str3);
          localStringBuilder4.append("\n");
          localStringBuilder4.append(paramString2);
          localStringBuilder4.append("\n");
          localStringBuilder4.append(str1);
          com.tencent.bugly.crashreport.crash.b.a("NATIVE_CRASH", str14, str15, localThread4, localStringBuilder4.toString(), null);
          z.b(paramString4);
          return;
        }
        HashMap localHashMap = new HashMap();
        if (paramArrayOfString != null)
        {
          int i = paramArrayOfString.length;
          int j = 0;
          if (j < i)
          {
            String str13 = paramArrayOfString[j];
            String[] arrayOfString = str13.split("=");
            if (arrayOfString.length == 2)
              localHashMap.put(arrayOfString[0], arrayOfString[1]);
            else
              x.d("bad extraMsg %s", new Object[] { str13 });
            j++;
            continue;
          }
        }
        else
        {
          x.c("not found extraMsg", new Object[0]);
        }
        String str5 = (String)localHashMap.get("ExceptionProcessName");
        if ((str5 != null) && (str5.length() != 0))
          x.c("crash process name change to %s", new Object[] { str5 });
        else
          str5 = this.c.d;
        String str6 = (String)localHashMap.get("ExceptionThreadName");
        if ((str6 != null) && (str6.length() != 0))
        {
          x.c("crash thread name change to %s", new Object[] { str6 });
          Iterator localIterator = Thread.getAllStackTraces().keySet().iterator();
          if (localIterator.hasNext())
          {
            Thread localThread3 = (Thread)localIterator.next();
            if (!localThread3.getName().equals(str6))
              continue;
            StringBuilder localStringBuilder3 = new StringBuilder();
            localStringBuilder3.append(str6);
            localStringBuilder3.append("(");
            localStringBuilder3.append(localThread3.getId());
            localStringBuilder3.append(")");
            str6 = localStringBuilder3.toString();
          }
        }
        else
        {
          Thread localThread1 = Thread.currentThread();
          StringBuilder localStringBuilder1 = new StringBuilder();
          localStringBuilder1.append(localThread1.getName());
          localStringBuilder1.append("(");
          localStringBuilder1.append(localThread1.getId());
          localStringBuilder1.append(")");
          str6 = localStringBuilder1.toString();
        }
        long l = paramLong1 * 1000L + paramLong2 / 1000L;
        String str7 = (String)localHashMap.get("SysLogPath");
        String str8 = str3;
        String str9 = str3;
        Object localObject3 = localObject2;
        try
        {
          CrashDetailBean localCrashDetailBean = packageCrashDatas(str5, str6, l, str8, paramString2, str1, str4, localObject3, paramString4, str7, paramString7, null, null, true);
          if (localCrashDetailBean == null)
            try
            {
              x.e("pkg crash datas fail!", new Object[0]);
              return;
            }
            catch (Throwable localThrowable4)
            {
              localObject1 = localThrowable4;
            }
          String str10 = z.a();
          try
          {
            String str11 = this.c.d;
            Thread localThread2 = Thread.currentThread();
            StringBuilder localStringBuilder2 = new StringBuilder();
            localStringBuilder2.append(str9);
            localStringBuilder2.append("\n");
            localStringBuilder2.append(paramString2);
            localStringBuilder2.append("\n");
            localStringBuilder2.append(str1);
            com.tencent.bugly.crashreport.crash.b.a("NATIVE_CRASH", str10, str11, localThread2, localStringBuilder2.toString(), localCrashDetailBean);
            if (this.b.a(localCrashDetailBean, paramInt3))
              break label1061;
            com.tencent.bugly.crashreport.crash.b localb = this.b;
            bool5 = true;
            localb.a(localCrashDetailBean, 3000L, bool5);
            this.b.b(localCrashDetailBean);
            NativeCrashHandler localNativeCrashHandler = NativeCrashHandler.getInstance();
            String str12 = null;
            if (localNativeCrashHandler != null)
              str12 = localNativeCrashHandler.getDumpFilePath();
            b.a(bool5, str12);
            return;
          }
          catch (Throwable localThrowable1)
          {
          }
        }
        catch (Throwable localThrowable2)
        {
        }
      }
      catch (Throwable localThrowable3)
      {
      }
      Object localObject1 = localThrowable3;
      label1047: if (!x.a((Throwable)localObject1))
        ((Throwable)localObject1).printStackTrace();
      return;
      label1061: boolean bool5 = true;
    }
  }

  public final CrashDetailBean packageCrashDatas(String paramString1, String paramString2, long paramLong, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, byte[] paramArrayOfByte, Map<String, String> paramMap, boolean paramBoolean)
  {
    boolean bool = c.a().l();
    if (bool)
      x.e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
    CrashDetailBean localCrashDetailBean = new CrashDetailBean();
    localCrashDetailBean.b = 1;
    localCrashDetailBean.e = this.c.h();
    localCrashDetailBean.f = this.c.j;
    localCrashDetailBean.g = this.c.w();
    localCrashDetailBean.m = this.c.g();
    localCrashDetailBean.n = paramString3;
    String str1;
    if (bool)
      str1 = " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]";
    else
      str1 = "";
    localCrashDetailBean.o = str1;
    localCrashDetailBean.p = paramString4;
    String str2;
    if (paramString5 == null)
      str2 = "";
    else
      str2 = paramString5;
    localCrashDetailBean.q = str2;
    localCrashDetailBean.r = paramLong;
    localCrashDetailBean.u = z.b(localCrashDetailBean.q.getBytes());
    localCrashDetailBean.z = paramString1;
    localCrashDetailBean.A = paramString2;
    localCrashDetailBean.H = this.c.y();
    localCrashDetailBean.h = this.c.v();
    localCrashDetailBean.i = this.c.K();
    localCrashDetailBean.v = paramString8;
    NativeCrashHandler localNativeCrashHandler = NativeCrashHandler.getInstance();
    String str3;
    if (localNativeCrashHandler != null)
      str3 = localNativeCrashHandler.getDumpFilePath();
    else
      str3 = null;
    String str4 = b.a(str3, paramString8);
    if (!z.a(str4))
      localCrashDetailBean.T = str4;
    localCrashDetailBean.U = b.b(str3);
    localCrashDetailBean.w = b.a(paramString9, c.e, null);
    localCrashDetailBean.I = paramString7;
    localCrashDetailBean.J = paramString6;
    localCrashDetailBean.K = paramString10;
    localCrashDetailBean.E = this.c.p();
    localCrashDetailBean.F = this.c.o();
    localCrashDetailBean.G = this.c.q();
    if (paramBoolean)
    {
      localCrashDetailBean.B = com.tencent.bugly.crashreport.common.info.b.h();
      localCrashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.f();
      localCrashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.j();
      if (localCrashDetailBean.w == null)
        localCrashDetailBean.w = z.a(this.a, c.e, null);
      localCrashDetailBean.x = y.a();
      localCrashDetailBean.L = this.c.a;
      localCrashDetailBean.M = this.c.a();
      localCrashDetailBean.O = this.c.H();
      localCrashDetailBean.P = this.c.I();
      localCrashDetailBean.Q = this.c.B();
      localCrashDetailBean.R = this.c.G();
      localCrashDetailBean.y = z.a(c.f, false);
      int i = localCrashDetailBean.q.indexOf("java:\n");
      if (i > 0)
      {
        int j = i + "java:\n".length();
        if (j < localCrashDetailBean.q.length())
        {
          String str5 = localCrashDetailBean.q.substring(j, localCrashDetailBean.q.length() - 1);
          if ((str5.length() > 0) && (localCrashDetailBean.y.containsKey(localCrashDetailBean.A)))
          {
            String str6 = (String)localCrashDetailBean.y.get(localCrashDetailBean.A);
            int k = str6.indexOf(str5);
            if (k > 0)
            {
              String str7 = str6.substring(k);
              localCrashDetailBean.y.put(localCrashDetailBean.A, str7);
              localCrashDetailBean.q = localCrashDetailBean.q.substring(0, j);
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append(localCrashDetailBean.q);
              localStringBuilder.append(str7);
              localCrashDetailBean.q = localStringBuilder.toString();
            }
          }
        }
      }
      if (paramString1 == null)
        localCrashDetailBean.z = this.c.d;
      this.b.c(localCrashDetailBean);
    }
    else
    {
      localCrashDetailBean.B = -1L;
      localCrashDetailBean.C = -1L;
      localCrashDetailBean.D = -1L;
      if (localCrashDetailBean.w == null)
        localCrashDetailBean.w = "this crash is occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.";
      localCrashDetailBean.L = -1L;
      localCrashDetailBean.O = -1;
      localCrashDetailBean.P = -1;
      localCrashDetailBean.Q = paramMap;
      localCrashDetailBean.R = this.c.G();
      localCrashDetailBean.y = null;
      if (paramString1 == null)
        localCrashDetailBean.z = "unknown(record)";
      if (paramArrayOfByte != null)
        localCrashDetailBean.x = paramArrayOfByte;
    }
    return localCrashDetailBean;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.crashreport.crash.jni.a
 * JD-Core Version:    0.6.1
 */