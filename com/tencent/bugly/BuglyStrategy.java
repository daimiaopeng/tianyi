package com.tencent.bugly;

import com.tencent.bugly.crashreport.common.info.a;
import java.util.Map;

public class BuglyStrategy
{
  private String a;
  private String b;
  private String c;
  private long d;
  private String e;
  private String f;
  private boolean g = true;
  private boolean h = true;
  private boolean i = true;
  private Class<?> j = null;
  private boolean k = true;
  private boolean l = true;
  private boolean m = true;
  private boolean n = false;
  private a o;

  public String getAppChannel()
  {
    try
    {
      if (this.b == null)
      {
        String str2 = a.b().l;
        return str2;
      }
      String str1 = this.b;
      return str1;
    }
    finally
    {
    }
  }

  public String getAppPackageName()
  {
    try
    {
      if (this.c == null)
      {
        String str2 = a.b().c;
        return str2;
      }
      String str1 = this.c;
      return str1;
    }
    finally
    {
    }
  }

  public long getAppReportDelay()
  {
    try
    {
      long l1 = this.d;
      return l1;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String getAppVersion()
  {
    try
    {
      if (this.a == null)
      {
        String str2 = a.b().j;
        return str2;
      }
      String str1 = this.a;
      return str1;
    }
    finally
    {
    }
  }

  public a getCrashHandleCallback()
  {
    try
    {
      a locala = this.o;
      return locala;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String getDeviceID()
  {
    try
    {
      String str = this.f;
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String getLibBuglySOFilePath()
  {
    try
    {
      String str = this.e;
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public Class<?> getUserInfoActivity()
  {
    try
    {
      Class localClass = this.j;
      return localClass;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean isBuglyLogUpload()
  {
    try
    {
      boolean bool = this.k;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean isEnableANRCrashMonitor()
  {
    try
    {
      boolean bool = this.h;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean isEnableNativeCrashMonitor()
  {
    try
    {
      boolean bool = this.g;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean isEnableUserInfo()
  {
    try
    {
      boolean bool = this.i;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean isReplaceOldChannel()
  {
    return this.l;
  }

  public boolean isUploadProcess()
  {
    try
    {
      boolean bool = this.m;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean recordUserInfoOnceADay()
  {
    try
    {
      boolean bool = this.n;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public BuglyStrategy setAppChannel(String paramString)
  {
    try
    {
      this.b = paramString;
      return this;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public BuglyStrategy setAppPackageName(String paramString)
  {
    try
    {
      this.c = paramString;
      return this;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public BuglyStrategy setAppReportDelay(long paramLong)
  {
    try
    {
      this.d = paramLong;
      return this;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public BuglyStrategy setAppVersion(String paramString)
  {
    try
    {
      this.a = paramString;
      return this;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public BuglyStrategy setBuglyLogUpload(boolean paramBoolean)
  {
    try
    {
      this.k = paramBoolean;
      return this;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public BuglyStrategy setCrashHandleCallback(a parama)
  {
    try
    {
      this.o = parama;
      return this;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public BuglyStrategy setDeviceID(String paramString)
  {
    try
    {
      this.f = paramString;
      return this;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public BuglyStrategy setEnableANRCrashMonitor(boolean paramBoolean)
  {
    try
    {
      this.h = paramBoolean;
      return this;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public BuglyStrategy setEnableNativeCrashMonitor(boolean paramBoolean)
  {
    try
    {
      this.g = paramBoolean;
      return this;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public BuglyStrategy setEnableUserInfo(boolean paramBoolean)
  {
    try
    {
      this.i = paramBoolean;
      return this;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public BuglyStrategy setLibBuglySOFilePath(String paramString)
  {
    try
    {
      this.e = paramString;
      return this;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public BuglyStrategy setRecordUserInfoOnceADay(boolean paramBoolean)
  {
    try
    {
      this.n = paramBoolean;
      return this;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setReplaceOldChannel(boolean paramBoolean)
  {
    this.l = paramBoolean;
  }

  public BuglyStrategy setUploadProcess(boolean paramBoolean)
  {
    try
    {
      this.m = paramBoolean;
      return this;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public BuglyStrategy setUserInfoActivity(Class<?> paramClass)
  {
    try
    {
      this.j = paramClass;
      return this;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static class a
  {
    public static final int CRASHTYPE_ANR = 4;
    public static final int CRASHTYPE_BLOCK = 7;
    public static final int CRASHTYPE_COCOS2DX_JS = 5;
    public static final int CRASHTYPE_COCOS2DX_LUA = 6;
    public static final int CRASHTYPE_JAVA_CATCH = 1;
    public static final int CRASHTYPE_JAVA_CRASH = 0;
    public static final int CRASHTYPE_NATIVE = 2;
    public static final int CRASHTYPE_U3D = 3;
    public static final int MAX_USERDATA_KEY_LENGTH = 100;
    public static final int MAX_USERDATA_VALUE_LENGTH = 30000;

    public Map<String, String> onCrashHandleStart(int paramInt, String paramString1, String paramString2, String paramString3)
    {
      return null;
    }

    public byte[] onCrashHandleStart2GetExtraDatas(int paramInt, String paramString1, String paramString2, String paramString3)
    {
      return null;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.BuglyStrategy
 * JD-Core Version:    0.6.1
 */