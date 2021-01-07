package com.tencent.bugly.crashreport.inner;

import com.tencent.bugly.crashreport.crash.d;
import com.tencent.bugly.proguard.x;
import java.util.Map;

public class InnerApi
{
  public static void postCocos2dxCrashAsync(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    if ((paramString1 != null) && (paramString2 != null) && (paramString3 != null))
    {
      if ((paramInt != 5) && (paramInt != 6))
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(paramInt);
        x.e("post cocos2d-x fail category illeagle: %d", arrayOfObject);
        return;
      }
      x.a("post cocos2d-x crash %s %s", new Object[] { paramString1, paramString2 });
      d.a(Thread.currentThread(), paramInt, paramString1, paramString2, paramString3, null);
      return;
    }
    x.e("post cocos2d-x fail args null", new Object[0]);
  }

  public static void postH5CrashAsync(Thread paramThread, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap)
  {
    if ((paramString1 != null) && (paramString2 != null) && (paramString3 != null))
    {
      x.a("post h5 crash %s %s", new Object[] { paramString1, paramString2 });
      d.a(paramThread, 8, paramString1, paramString2, paramString3, paramMap);
      return;
    }
    x.e("post h5 fail args null", new Object[0]);
  }

  public static void postU3dCrashAsync(String paramString1, String paramString2, String paramString3)
  {
    if ((paramString1 == null) || (paramString2 == null) || (paramString3 == null))
      x.e("post u3d fail args null", new Object[0]);
    x.a("post u3d crash %s %s", new Object[] { paramString1, paramString2 });
    d.a(Thread.currentThread(), 4, paramString1, paramString2, paramString3, null);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.crashreport.inner.InnerApi
 * JD-Core Version:    0.6.1
 */