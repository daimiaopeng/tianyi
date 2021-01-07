package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Process;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

public final class a
{
  private static a ad;
  public HashMap<String, String> A = new HashMap();
  public boolean B = true;
  public List<String> C = new ArrayList();
  public com.tencent.bugly.crashreport.a D = null;
  public SharedPreferences E;
  private final Context F;
  private String G;
  private String H;
  private String I = "unknown";
  private String J = "unknown";
  private String K = "";
  private String L = null;
  private String M = null;
  private String N = null;
  private String O = null;
  private long P = -1L;
  private long Q = -1L;
  private long R = -1L;
  private String S = null;
  private String T = null;
  private Map<String, PlugInBean> U = null;
  private boolean V = true;
  private String W = null;
  private String X = null;
  private Boolean Y = null;
  private String Z = null;
  public final long a = System.currentTimeMillis();
  private String aa = null;
  private String ab = null;
  private Map<String, PlugInBean> ac = null;
  private int ae = -1;
  private int af = -1;
  private Map<String, String> ag = new HashMap();
  private Map<String, String> ah = new HashMap();
  private Map<String, String> ai = new HashMap();
  private boolean aj;
  private String ak = null;
  private String al = null;
  private String am = null;
  private String an = null;
  private String ao = null;
  private final Object ap = new Object();
  private final Object aq = new Object();
  private final Object ar = new Object();
  private final Object as = new Object();
  private final Object at = new Object();
  private final Object au = new Object();
  private final Object av = new Object();
  public final byte b;
  public String c;
  public final String d;
  public boolean e = true;
  public final String f;
  public final String g;
  public final String h;
  public long i;
  public String j = null;
  public String k = null;
  public String l = null;
  public String m = null;
  public String n = null;
  public List<String> o = null;
  public String p = "unknown";
  public long q = 0L;
  public long r = 0L;
  public long s = 0L;
  public long t = 0L;
  public boolean u = false;
  public String v = null;
  public String w = null;
  public String x = null;
  public boolean y = false;
  public boolean z = false;

  private a(Context paramContext)
  {
    this.F = z.a(paramContext);
    this.b = 1;
    PackageInfo localPackageInfo = AppInfo.b(paramContext);
    if (localPackageInfo != null)
      try
      {
        this.j = localPackageInfo.versionName;
        this.v = this.j;
        this.w = Integer.toString(localPackageInfo.versionCode);
      }
      catch (Throwable localThrowable3)
      {
        if (!x.a(localThrowable3))
          localThrowable3.printStackTrace();
      }
    this.c = AppInfo.a(paramContext);
    this.d = AppInfo.a(Process.myPid());
    this.f = b.l();
    this.g = b.a();
    this.k = AppInfo.c(paramContext);
    StringBuilder localStringBuilder1 = new StringBuilder("Android ");
    localStringBuilder1.append(b.b());
    localStringBuilder1.append(",level ");
    localStringBuilder1.append(b.c());
    this.h = localStringBuilder1.toString();
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(this.g);
    localStringBuilder2.append(";");
    localStringBuilder2.append(this.h);
    localStringBuilder2.toString();
    Map localMap = AppInfo.d(paramContext);
    if (localMap != null)
      try
      {
        this.o = AppInfo.a(localMap);
        String str1 = (String)localMap.get("BUGLY_APPID");
        if (str1 != null)
          this.X = str1;
        String str2 = (String)localMap.get("BUGLY_APP_VERSION");
        if (str2 != null)
          this.j = str2;
        String str3 = (String)localMap.get("BUGLY_APP_CHANNEL");
        if (str3 != null)
          this.l = str3;
        String str4 = (String)localMap.get("BUGLY_ENABLE_DEBUG");
        if (str4 != null)
          this.u = str4.equalsIgnoreCase("true");
        String str5 = (String)localMap.get("com.tencent.rdm.uuid");
        if (str5 != null)
          this.x = str5;
      }
      catch (Throwable localThrowable2)
      {
        if (!x.a(localThrowable2))
          localThrowable2.printStackTrace();
      }
    try
    {
      if (!paramContext.getDatabasePath("bugly_db_").exists())
      {
        this.z = true;
        x.c("App is first time to be installed on the device.", new Object[0]);
      }
    }
    catch (Throwable localThrowable1)
    {
      if (com.tencent.bugly.b.c)
        localThrowable1.printStackTrace();
    }
    this.E = z.a("BUGLY_COMMON_VALUES", paramContext);
    x.c("com info create end", new Object[0]);
  }

  public static int L()
  {
    return b.c();
  }

  public static a a(Context paramContext)
  {
    try
    {
      if (ad == null)
        ad = new a(paramContext);
      a locala = ad;
      return locala;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static a b()
  {
    try
    {
      a locala = ad;
      return locala;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static String c()
  {
    return "2.6.6";
  }

  public final String A()
  {
    if (this.ab == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(b.d());
      this.ab = localStringBuilder.toString();
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.ab;
      x.a("Hardware serial number: %s", arrayOfObject);
    }
    return this.ab;
  }

  public final Map<String, String> B()
  {
    synchronized (this.ar)
    {
      if (this.ag.size() <= 0)
        return null;
      HashMap localHashMap = new HashMap(this.ag);
      return localHashMap;
    }
  }

  public final void C()
  {
    synchronized (this.ar)
    {
      this.ag.clear();
      return;
    }
  }

  public final int D()
  {
    synchronized (this.ar)
    {
      int i1 = this.ag.size();
      return i1;
    }
  }

  public final Set<String> E()
  {
    synchronized (this.ar)
    {
      Set localSet = this.ag.keySet();
      return localSet;
    }
  }

  public final Map<String, String> F()
  {
    synchronized (this.av)
    {
      if (this.ah.size() <= 0)
        return null;
      HashMap localHashMap = new HashMap(this.ah);
      return localHashMap;
    }
  }

  public final Map<String, String> G()
  {
    synchronized (this.as)
    {
      if (this.ai.size() <= 0)
        return null;
      HashMap localHashMap = new HashMap(this.ai);
      return localHashMap;
    }
  }

  public final int H()
  {
    synchronized (this.at)
    {
      int i1 = this.ae;
      return i1;
    }
  }

  public final int I()
  {
    return this.af;
  }

  public final boolean J()
  {
    return AppInfo.f(this.F);
  }

  public final Map<String, PlugInBean> K()
  {
    return null;
  }

  public final String M()
  {
    if (this.ak == null)
      this.ak = b.m();
    return this.ak;
  }

  public final String N()
  {
    if (this.al == null)
      this.al = b.j(this.F);
    return this.al;
  }

  public final String O()
  {
    if (this.am == null)
      this.am = b.k(this.F);
    return this.am;
  }

  public final String P()
  {
    return b.n();
  }

  public final String Q()
  {
    if (this.an == null)
      this.an = b.l(this.F);
    return this.an;
  }

  public final long R()
  {
    return b.o();
  }

  public final void a(int paramInt)
  {
    synchronized (this.at)
    {
      int i1 = this.ae;
      if (i1 != paramInt)
      {
        this.ae = paramInt;
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(i1);
        arrayOfObject[1] = Integer.valueOf(this.ae);
        x.a("user scene tag %d changed to tag %d", arrayOfObject);
      }
      return;
    }
  }

  public final void a(String paramString)
  {
    this.X = paramString;
  }

  public final void a(String paramString1, String paramString2)
  {
    if ((paramString1 != null) && (paramString2 != null))
      synchronized (this.aq)
      {
        this.A.put(paramString1, paramString2);
        return;
      }
  }

  public final void a(boolean paramBoolean)
  {
    this.aj = paramBoolean;
    if (this.D != null)
      this.D.setNativeIsAppForeground(paramBoolean);
  }

  public final boolean a()
  {
    return this.aj;
  }

  public final void b(int paramInt)
  {
    int i1 = this.af;
    if (i1 != 24096)
    {
      this.af = 24096;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(i1);
      arrayOfObject[1] = Integer.valueOf(this.af);
      x.a("server scene tag %d changed to tag %d", arrayOfObject);
    }
  }

  public final void b(String paramString)
  {
    Object localObject1 = this.au;
    if (paramString == null);
    try
    {
      paramString = "10000";
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      this.I = localStringBuilder.toString();
      return;
      Object localObject2;
      throw localObject2;
    }
    finally
    {
    }
  }

  public final void b(String paramString1, String paramString2)
  {
    if ((!z.a(paramString1)) && (!z.a(paramString2)))
      synchronized (this.ar)
      {
        this.ag.put(paramString1, paramString2);
        return;
      }
    Object[] arrayOfObject = new Object[2];
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append(paramString1);
    arrayOfObject[0] = localStringBuilder1.toString();
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(paramString2);
    arrayOfObject[1] = localStringBuilder2.toString();
    x.d("key&value should not be empty %s %s", arrayOfObject);
  }

  public final void c(String paramString)
  {
    this.H = paramString;
    synchronized (this.av)
    {
      this.ah.put("E8", paramString);
      return;
    }
  }

  public final void c(String paramString1, String paramString2)
  {
    if ((!z.a(paramString1)) && (!z.a(paramString2)))
      synchronized (this.as)
      {
        this.ai.put(paramString1, paramString2);
        return;
      }
    Object[] arrayOfObject = new Object[2];
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append(paramString1);
    arrayOfObject[0] = localStringBuilder1.toString();
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(paramString2);
    arrayOfObject[1] = localStringBuilder2.toString();
    x.d("server key&value should not be empty %s %s", arrayOfObject);
  }

  public final void d()
  {
    synchronized (this.ap)
    {
      this.G = UUID.randomUUID().toString();
      return;
    }
  }

  public final void d(String paramString)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      this.J = localStringBuilder.toString();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final String e()
  {
    if (this.G == null)
      synchronized (this.ap)
      {
        if (this.G == null)
          this.G = UUID.randomUUID().toString();
      }
    return this.G;
  }

  public final void e(String paramString)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      this.K = localStringBuilder.toString();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final String f()
  {
    if (!z.a(null))
      return null;
    return this.X;
  }

  public final String f(String paramString)
  {
    if (z.a(paramString))
    {
      Object[] arrayOfObject = new Object[1];
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      arrayOfObject[0] = localStringBuilder.toString();
      x.d("key should not be empty %s", arrayOfObject);
      return null;
    }
    synchronized (this.ar)
    {
      String str = (String)this.ag.remove(paramString);
      return str;
    }
  }

  public final String g()
  {
    synchronized (this.au)
    {
      String str = this.I;
      return str;
    }
  }

  public final String g(String paramString)
  {
    if (z.a(paramString))
    {
      Object[] arrayOfObject = new Object[1];
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      arrayOfObject[0] = localStringBuilder.toString();
      x.d("key should not be empty %s", arrayOfObject);
      return null;
    }
    synchronized (this.ar)
    {
      String str = (String)this.ag.get(paramString);
      return str;
    }
  }

  public final String h()
  {
    if (this.H != null)
      return this.H;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(k());
    localStringBuilder.append("|");
    localStringBuilder.append(m());
    localStringBuilder.append("|");
    localStringBuilder.append(n());
    this.H = localStringBuilder.toString();
    return this.H;
  }

  public final String i()
  {
    try
    {
      String str = this.J;
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final String j()
  {
    try
    {
      String str = this.K;
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final String k()
  {
    if (!this.V)
      return "";
    if (this.L == null)
      this.L = b.a(this.F);
    return this.L;
  }

  public final String l()
  {
    if (!this.V)
      return "";
    if ((this.M == null) || (!this.M.contains(":")))
      this.M = b.d(this.F);
    return this.M;
  }

  public final String m()
  {
    if (!this.V)
      return "";
    if (this.N == null)
      this.N = b.b(this.F);
    return this.N;
  }

  public final String n()
  {
    if (!this.V)
      return "";
    if (this.O == null)
      this.O = b.c(this.F);
    return this.O;
  }

  public final long o()
  {
    if (this.P <= 0L)
      this.P = b.e();
    return this.P;
  }

  public final long p()
  {
    if (this.Q <= 0L)
      this.Q = b.g();
    return this.Q;
  }

  public final long q()
  {
    if (this.R <= 0L)
      this.R = b.i();
    return this.R;
  }

  public final String r()
  {
    if (this.S == null)
      this.S = b.a(true);
    return this.S;
  }

  public final String s()
  {
    if (this.T == null)
      this.T = b.h(this.F);
    return this.T;
  }

  public final String t()
  {
    try
    {
      Map localMap = this.F.getSharedPreferences("BuglySdkInfos", 0).getAll();
      if (!localMap.isEmpty())
        synchronized (this.aq)
        {
          Iterator localIterator2 = localMap.entrySet().iterator();
          while (localIterator2.hasNext())
          {
            Map.Entry localEntry2 = (Map.Entry)localIterator2.next();
            try
            {
              this.A.put(localEntry2.getKey(), localEntry2.getValue().toString());
            }
            catch (Throwable localThrowable2)
            {
              x.a(localThrowable2);
            }
          }
        }
    }
    catch (Throwable localThrowable1)
    {
      x.a(localThrowable1);
      if (!this.A.isEmpty())
      {
        StringBuilder localStringBuilder = new StringBuilder();
        Iterator localIterator1 = this.A.entrySet().iterator();
        while (localIterator1.hasNext())
        {
          Map.Entry localEntry1 = (Map.Entry)localIterator1.next();
          localStringBuilder.append("[");
          localStringBuilder.append((String)localEntry1.getKey());
          localStringBuilder.append(",");
          localStringBuilder.append((String)localEntry1.getValue());
          localStringBuilder.append("] ");
        }
        c("SDK_INFO", localStringBuilder.toString());
        return localStringBuilder.toString();
      }
    }
    return null;
  }

  public final String u()
  {
    if (this.ao == null)
      this.ao = AppInfo.e(this.F);
    return this.ao;
  }

  public final Map<String, PlugInBean> v()
  {
    return null;
  }

  public final String w()
  {
    if (this.W == null)
      this.W = b.k();
    return this.W;
  }

  public final Boolean x()
  {
    if (this.Y == null)
      this.Y = Boolean.valueOf(b.i(this.F));
    return this.Y;
  }

  public final String y()
  {
    if (this.Z == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(b.g(this.F));
      this.Z = localStringBuilder.toString();
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.Z;
      x.a("ROM ID: %s", arrayOfObject);
    }
    return this.Z;
  }

  public final String z()
  {
    if (this.aa == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(b.e(this.F));
      this.aa = localStringBuilder.toString();
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.aa;
      x.a("SIM serial number: %s", arrayOfObject);
    }
    return this.aa;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.crashreport.common.info.a
 * JD-Core Version:    0.6.1
 */