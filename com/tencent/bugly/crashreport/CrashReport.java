package com.tencent.bugly.crashreport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.BuglyStrategy.a;
import com.tencent.bugly.CrashModule;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastRecevier;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.crashreport.crash.h5.H5JavaScriptInterface;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CrashReport
{
  private static Context a;

  public static void closeBugly()
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not close bugly because bugly is disable.");
      return;
    }
    if (!CrashModule.hasInitialized())
    {
      Log.w(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      return;
    }
    if (a == null)
      return;
    BuglyBroadcastRecevier localBuglyBroadcastRecevier = BuglyBroadcastRecevier.getInstance();
    if (localBuglyBroadcastRecevier != null)
      localBuglyBroadcastRecevier.unregister(a);
    closeCrashReport();
    com.tencent.bugly.crashreport.biz.b.a(a);
    w localw = w.a();
    if (localw != null)
      localw.b();
  }

  public static void closeCrashReport()
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not close crash report because bugly is disable.");
      return;
    }
    if (!CrashModule.hasInitialized())
    {
      Log.w(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      return;
    }
    c.a().d();
  }

  public static void closeNativeReport()
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not close native report because bugly is disable.");
      return;
    }
    if (!CrashModule.hasInitialized())
    {
      Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      return;
    }
    c.a().f();
  }

  public static void enableBugly(boolean paramBoolean)
  {
    com.tencent.bugly.b.a = paramBoolean;
  }

  public static Set<String> getAllUserDataKeys(Context paramContext)
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not get all keys of user data because bugly is disable.");
      return new HashSet();
    }
    if (paramContext == null)
    {
      Log.e(x.a, "getAllUserDataKeys args context should not be null");
      return new HashSet();
    }
    return a.a(paramContext).E();
  }

  public static String getAppChannel()
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not get App channel because bugly is disable.");
      return "unknown";
    }
    if (!CrashModule.hasInitialized())
    {
      Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      return "unknown";
    }
    return a.a(a).l;
  }

  public static String getAppID()
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not get App ID because bugly is disable.");
      return "unknown";
    }
    if (!CrashModule.hasInitialized())
    {
      Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      return "unknown";
    }
    return a.a(a).f();
  }

  public static String getAppVer()
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not get app version because bugly is disable.");
      return "unknown";
    }
    if (!CrashModule.hasInitialized())
    {
      Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      return "unknown";
    }
    return a.a(a).j;
  }

  public static String getBuglyVersion(Context paramContext)
  {
    if (paramContext == null)
    {
      x.d("Please call with context.", new Object[0]);
      return "unknown";
    }
    a.a(paramContext);
    return a.c();
  }

  public static Map<String, String> getSdkExtraData()
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not get SDK extra data because bugly is disable.");
      return new HashMap();
    }
    if (!CrashModule.hasInitialized())
    {
      Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      return null;
    }
    return a.a(a).A;
  }

  public static Map<String, String> getSdkExtraData(Context paramContext)
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not get SDK extra data because bugly is disable.");
      return new HashMap();
    }
    if (paramContext == null)
    {
      x.d("Context should not be null.", new Object[0]);
      return null;
    }
    return a.a(paramContext).A;
  }

  public static String getUserData(Context paramContext, String paramString)
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not get user data because bugly is disable.");
      return "unknown";
    }
    if (paramContext == null)
    {
      Log.e(x.a, "getUserDataValue args context should not be null");
      return "unknown";
    }
    if (z.a(paramString))
      return null;
    return a.a(paramContext).g(paramString);
  }

  public static int getUserDatasSize(Context paramContext)
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not get size of user data because bugly is disable.");
      return -1;
    }
    if (paramContext == null)
    {
      Log.e(x.a, "getUserDatasSize args context should not be null");
      return -1;
    }
    return a.a(paramContext).D();
  }

  public static String getUserId()
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not get user ID because bugly is disable.");
      return "unknown";
    }
    if (!CrashModule.hasInitialized())
    {
      Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      return "unknown";
    }
    return a.a(a).g();
  }

  public static int getUserSceneTagId(Context paramContext)
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not get user scene tag because bugly is disable.");
      return -1;
    }
    if (paramContext == null)
    {
      Log.e(x.a, "getUserSceneTagId args context should not be null");
      return -1;
    }
    return a.a(paramContext).H();
  }

  public static void initCrashReport(Context paramContext)
  {
    a = paramContext;
    com.tencent.bugly.b.a(CrashModule.getInstance());
    com.tencent.bugly.b.a(paramContext);
  }

  public static void initCrashReport(Context paramContext, UserStrategy paramUserStrategy)
  {
    a = paramContext;
    com.tencent.bugly.b.a(CrashModule.getInstance());
    com.tencent.bugly.b.a(paramContext, paramUserStrategy);
  }

  public static void initCrashReport(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (paramContext != null)
    {
      a = paramContext;
      com.tencent.bugly.b.a(CrashModule.getInstance());
      com.tencent.bugly.b.a(paramContext, paramString, paramBoolean, null);
    }
  }

  public static void initCrashReport(Context paramContext, String paramString, boolean paramBoolean, UserStrategy paramUserStrategy)
  {
    if (paramContext == null)
      return;
    a = paramContext;
    com.tencent.bugly.b.a(CrashModule.getInstance());
    com.tencent.bugly.b.a(paramContext, paramString, paramBoolean, paramUserStrategy);
  }

  public static boolean isLastSessionCrash()
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "The info 'isLastSessionCrash' is not accurate because bugly is disable.");
      return false;
    }
    if (!CrashModule.hasInitialized())
    {
      Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      return false;
    }
    return c.a().b();
  }

  public static void postCatchedException(Throwable paramThrowable)
  {
    postCatchedException(paramThrowable, Thread.currentThread(), false);
  }

  public static void postCatchedException(Throwable paramThrowable, Thread paramThread)
  {
    postCatchedException(paramThrowable, paramThread, false);
  }

  public static void postCatchedException(Throwable paramThrowable, Thread paramThread, boolean paramBoolean)
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not post crash caught because bugly is disable.");
      return;
    }
    if (!CrashModule.hasInitialized())
    {
      Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      return;
    }
    if (paramThrowable == null)
    {
      x.d("throwable is null, just return", new Object[0]);
      return;
    }
    if (paramThread == null)
      paramThread = Thread.currentThread();
    Thread localThread = paramThread;
    c.a().a(localThread, paramThrowable, false, null, null, paramBoolean);
  }

  private static void putSdkData(Context paramContext, String paramString1, String paramString2)
  {
    if ((paramContext != null) && (!z.a(paramString1)) && (!z.a(paramString2)))
    {
      String str1 = paramString1.replace("[a-zA-Z[0-9]]+", "");
      if (str1.length() > 100)
      {
        String str3 = x.a;
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Integer.valueOf(50);
        Log.w(str3, String.format("putSdkData key length over limit %d, will be cutted.", arrayOfObject2));
        str1 = str1.substring(0, 50);
      }
      if (paramString2.length() > 500)
      {
        String str2 = x.a;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Integer.valueOf(200);
        Log.w(str2, String.format("putSdkData value length over limit %d, will be cutted!", arrayOfObject1));
        paramString2 = paramString2.substring(0, 200);
      }
      a.a(paramContext).c(str1, paramString2);
      x.b(String.format("[param] putSdkData data: %s - %s", new Object[] { str1, paramString2 }), new Object[0]);
      return;
    }
  }

  public static void putUserData(Context paramContext, String paramString1, String paramString2)
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not put user data because bugly is disable.");
      return;
    }
    if (paramContext == null)
    {
      Log.w(x.a, "putUserData args context should not be null");
      return;
    }
    if (paramString1 == null)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append(paramString1);
      localStringBuilder1.toString();
      x.d("putUserData args key should not be null or empty", new Object[0]);
      return;
    }
    if (paramString2 == null)
    {
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append(paramString2);
      localStringBuilder2.toString();
      x.d("putUserData args value should not be null", new Object[0]);
      return;
    }
    if (!paramString1.matches("[a-zA-Z[0-9]]+"))
    {
      StringBuilder localStringBuilder3 = new StringBuilder("putUserData args key should match [a-zA-Z[0-9]]+  {");
      localStringBuilder3.append(paramString1);
      localStringBuilder3.append("}");
      x.d(localStringBuilder3.toString(), new Object[0]);
      return;
    }
    if (paramString2.length() > 200)
    {
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = Integer.valueOf(200);
      x.d("user data value length over limit %d, it will be cutted!", arrayOfObject3);
      paramString2 = paramString2.substring(0, 200);
    }
    a locala = a.a(paramContext);
    if (locala.E().contains(paramString1))
    {
      NativeCrashHandler localNativeCrashHandler2 = NativeCrashHandler.getInstance();
      if (localNativeCrashHandler2 != null)
        localNativeCrashHandler2.putKeyValueToNative(paramString1, paramString2);
      a.a(paramContext).b(paramString1, paramString2);
      x.c("replace KV %s %s", new Object[] { paramString1, paramString2 });
      return;
    }
    if (locala.D() >= 10)
    {
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(10);
      x.d("user data size is over limit %d, it will be cutted!", arrayOfObject2);
      return;
    }
    if (paramString1.length() > 50)
    {
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = Integer.valueOf(50);
      arrayOfObject1[1] = paramString1;
      x.d("user data key length over limit %d , will drop this new key %s", arrayOfObject1);
      paramString1 = paramString1.substring(0, 50);
    }
    NativeCrashHandler localNativeCrashHandler1 = NativeCrashHandler.getInstance();
    if (localNativeCrashHandler1 != null)
      localNativeCrashHandler1.putKeyValueToNative(paramString1, paramString2);
    a.a(paramContext).b(paramString1, paramString2);
    x.b("[param] set user data: %s - %s", new Object[] { paramString1, paramString2 });
  }

  public static String removeUserData(Context paramContext, String paramString)
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not remove user data because bugly is disable.");
      return "unknown";
    }
    if (paramContext == null)
    {
      Log.e(x.a, "removeUserData args context should not be null");
      return "unknown";
    }
    if (z.a(paramString))
      return null;
    x.b("[param] remove user data: %s", new Object[] { paramString });
    return a.a(paramContext).f(paramString);
  }

  public static void setAppChannel(Context paramContext, String paramString)
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not set App channel because Bugly is disable.");
      return;
    }
    if (paramContext == null)
    {
      Log.w(x.a, "setAppChannel args context should not be null");
      return;
    }
    if (paramString == null)
    {
      Log.w(x.a, "App channel is null, will not set");
      return;
    }
    a.a(paramContext).l = paramString;
    NativeCrashHandler localNativeCrashHandler = NativeCrashHandler.getInstance();
    if (localNativeCrashHandler != null)
      localNativeCrashHandler.setNativeAppChannel(paramString);
  }

  public static void setAppPackage(Context paramContext, String paramString)
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not set App package because bugly is disable.");
      return;
    }
    if (paramContext == null)
    {
      Log.w(x.a, "setAppPackage args context should not be null");
      return;
    }
    if (paramString == null)
    {
      Log.w(x.a, "App package is null, will not set");
      return;
    }
    a.a(paramContext).c = paramString;
    NativeCrashHandler localNativeCrashHandler = NativeCrashHandler.getInstance();
    if (localNativeCrashHandler != null)
      localNativeCrashHandler.setNativeAppPackage(paramString);
  }

  public static void setAppVersion(Context paramContext, String paramString)
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not set App version because bugly is disable.");
      return;
    }
    if (paramContext == null)
    {
      Log.w(x.a, "setAppVersion args context should not be null");
      return;
    }
    if (paramString == null)
    {
      Log.w(x.a, "App version is null, will not set");
      return;
    }
    a.a(paramContext).j = paramString;
    NativeCrashHandler localNativeCrashHandler = NativeCrashHandler.getInstance();
    if (localNativeCrashHandler != null)
      localNativeCrashHandler.setNativeAppVersion(paramString);
  }

  public static void setAuditEnable(Context paramContext, boolean paramBoolean)
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not set App package because bugly is disable.");
      return;
    }
    if (paramContext == null)
    {
      Log.w(x.a, "setAppPackage args context should not be null");
      return;
    }
    String str = x.a;
    StringBuilder localStringBuilder = new StringBuilder("Set audit enable: ");
    localStringBuilder.append(paramBoolean);
    Log.i(str, localStringBuilder.toString());
    a.a(paramContext).B = paramBoolean;
  }

  public static void setBuglyDbName(String paramString)
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not set DB name because bugly is disable.");
      return;
    }
    String str = x.a;
    StringBuilder localStringBuilder = new StringBuilder("Set Bugly DB name: ");
    localStringBuilder.append(paramString);
    Log.i(str, localStringBuilder.toString());
    com.tencent.bugly.proguard.q.a = paramString;
  }

  public static void setContext(Context paramContext)
  {
    a = paramContext;
  }

  public static void setCrashFilter(String paramString)
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not set App package because bugly is disable.");
      return;
    }
    String str = x.a;
    StringBuilder localStringBuilder = new StringBuilder("Set crash stack filter: ");
    localStringBuilder.append(paramString);
    Log.w(str, localStringBuilder.toString());
    c.m = paramString;
  }

  public static void setCrashRegularFilter(String paramString)
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not set App package because bugly is disable.");
      return;
    }
    String str = x.a;
    StringBuilder localStringBuilder = new StringBuilder("Set crash stack filter: ");
    localStringBuilder.append(paramString);
    Log.w(str, localStringBuilder.toString());
    c.n = paramString;
  }

  public static void setIsAppForeground(Context paramContext, boolean paramBoolean)
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not set 'isAppForeground' because bugly is disable.");
      return;
    }
    if (paramContext == null)
    {
      x.d("Context should not be null.", new Object[0]);
      return;
    }
    if (paramBoolean)
      x.c("App is in foreground.", new Object[0]);
    else
      x.c("App is in background.", new Object[0]);
    a.a(paramContext).a(paramBoolean);
  }

  public static void setIsDevelopmentDevice(Context paramContext, boolean paramBoolean)
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not set 'isDevelopmentDevice' because bugly is disable.");
      return;
    }
    if (paramContext == null)
    {
      x.d("Context should not be null.", new Object[0]);
      return;
    }
    if (paramBoolean)
      x.c("This is a development device.", new Object[0]);
    else
      x.c("This is not a development device.", new Object[0]);
    a.a(paramContext).y = paramBoolean;
  }

  public static boolean setJavascriptMonitor(WebView paramWebView, boolean paramBoolean)
  {
    return setJavascriptMonitor(paramWebView, paramBoolean, false);
  }

  @SuppressLint({"SetJavaScriptEnabled"})
  public static boolean setJavascriptMonitor(WebView paramWebView, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramWebView == null)
    {
      Log.w(x.a, "WebView is null.");
      return false;
    }
    return setJavascriptMonitor(new WebViewInterface()
    {
      public final void addJavascriptInterface(H5JavaScriptInterface paramAnonymousH5JavaScriptInterface, String paramAnonymousString)
      {
        this.a.addJavascriptInterface(paramAnonymousH5JavaScriptInterface, paramAnonymousString);
      }

      public final CharSequence getContentDescription()
      {
        return this.a.getContentDescription();
      }

      public final String getUrl()
      {
        return this.a.getUrl();
      }

      public final void loadUrl(String paramAnonymousString)
      {
        this.a.loadUrl(paramAnonymousString);
      }

      public final void setJavaScriptEnabled(boolean paramAnonymousBoolean)
      {
        WebSettings localWebSettings = this.a.getSettings();
        if (!localWebSettings.getJavaScriptEnabled())
          localWebSettings.setJavaScriptEnabled(true);
      }
    }
    , paramBoolean1, paramBoolean2);
  }

  public static boolean setJavascriptMonitor(WebViewInterface paramWebViewInterface, boolean paramBoolean)
  {
    return setJavascriptMonitor(paramWebViewInterface, paramBoolean, false);
  }

  @SuppressLint({"SetJavaScriptEnabled"})
  public static boolean setJavascriptMonitor(WebViewInterface paramWebViewInterface, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramWebViewInterface == null)
    {
      Log.w(x.a, "WebViewInterface is null.");
      return false;
    }
    if (!CrashModule.hasInitialized())
    {
      x.e("CrashReport has not been initialed! please to call method 'initCrashReport' first!", new Object[0]);
      return false;
    }
    x.a("Set Javascript exception monitor of webview.", new Object[0]);
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not set JavaScript monitor because bugly is disable.");
      return false;
    }
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = paramWebViewInterface.getUrl();
    x.c("URL of webview is %s", arrayOfObject1);
    if ((!paramBoolean2) && (Build.VERSION.SDK_INT < 19))
    {
      x.e("This interface is only available for Android 4.4 or later.", new Object[0]);
      return false;
    }
    x.a("Enable the javascript needed by webview monitor.", new Object[0]);
    paramWebViewInterface.setJavaScriptEnabled(true);
    H5JavaScriptInterface localH5JavaScriptInterface = H5JavaScriptInterface.getInstance(paramWebViewInterface);
    if (localH5JavaScriptInterface != null)
    {
      x.a("Add a secure javascript interface to the webview.", new Object[0]);
      paramWebViewInterface.addJavascriptInterface(localH5JavaScriptInterface, "exceptionUploader");
    }
    if (paramBoolean1)
    {
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = com.tencent.bugly.crashreport.crash.h5.b.b();
      x.a("Inject bugly.js(v%s) to the webview.", arrayOfObject2);
      String str = com.tencent.bugly.crashreport.crash.h5.b.a();
      if (str == null)
      {
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = com.tencent.bugly.crashreport.crash.h5.b.b();
        x.e("Failed to inject Bugly.js.", arrayOfObject3);
        return false;
      }
      StringBuilder localStringBuilder = new StringBuilder("javascript:");
      localStringBuilder.append(str);
      paramWebViewInterface.loadUrl(localStringBuilder.toString());
    }
    return true;
  }

  public static void setSdkExtraData(Context paramContext, String paramString1, String paramString2)
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not put SDK extra data because bugly is disable.");
      return;
    }
    if ((paramContext != null) && (!z.a(paramString1)) && (!z.a(paramString2)))
    {
      a.a(paramContext).a(paramString1, paramString2);
      return;
    }
  }

  public static void setSessionIntervalMills(long paramLong)
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not set 'SessionIntervalMills' because bugly is disable.");
      return;
    }
    com.tencent.bugly.crashreport.biz.b.a(paramLong);
  }

  public static void setUserId(Context paramContext, String paramString)
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not set user ID because bugly is disable.");
      return;
    }
    if (paramContext == null)
    {
      Log.e(x.a, "Context should not be null when bugly has not been initialed!");
      return;
    }
    if (paramString == null)
    {
      x.d("userId should not be null", new Object[0]);
      return;
    }
    if (paramString.length() > 100)
    {
      String str = paramString.substring(0, 100);
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = paramString;
      arrayOfObject[1] = Integer.valueOf(100);
      arrayOfObject[2] = str;
      x.d("userId %s length is over limit %d substring to %s", arrayOfObject);
      paramString = str;
    }
    if (paramString.equals(a.a(paramContext).g()))
      return;
    a.a(paramContext).b(paramString);
    x.b("[user] set userId : %s", new Object[] { paramString });
    NativeCrashHandler localNativeCrashHandler = NativeCrashHandler.getInstance();
    if (localNativeCrashHandler != null)
      localNativeCrashHandler.setNativeUserId(paramString);
    if (CrashModule.hasInitialized())
      com.tencent.bugly.crashreport.biz.b.a();
  }

  public static void setUserId(String paramString)
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not set user ID because bugly is disable.");
      return;
    }
    if (!CrashModule.hasInitialized())
    {
      Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      return;
    }
    setUserId(a, paramString);
  }

  public static void setUserSceneTag(Context paramContext, int paramInt)
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not set tag caught because bugly is disable.");
      return;
    }
    if (paramContext == null)
    {
      Log.e(x.a, "setTag args context should not be null");
      return;
    }
    if (paramInt <= 0)
      x.d("setTag args tagId should > 0", new Object[0]);
    a.a(paramContext).a(paramInt);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    x.b("[param] set user scene tag: %d", arrayOfObject);
  }

  public static void startCrashReport()
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not start crash report because bugly is disable.");
      return;
    }
    if (!CrashModule.hasInitialized())
    {
      Log.w(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      return;
    }
    c.a().c();
  }

  public static void testANRCrash()
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not test ANR crash because bugly is disable.");
      return;
    }
    if (!CrashModule.hasInitialized())
    {
      Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      return;
    }
    x.a("start to create a anr crash for test!", new Object[0]);
    c.a().k();
  }

  public static void testJavaCrash()
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not test Java crash because bugly is disable.");
      return;
    }
    if (!CrashModule.hasInitialized())
    {
      Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      return;
    }
    a locala = a.b();
    if (locala != null)
      locala.b(24096);
    throw new RuntimeException("This Crash create for Test! You can go to Bugly see more detail!");
  }

  public static void testNativeCrash()
  {
    if (!com.tencent.bugly.b.a)
    {
      Log.w(x.a, "Can not test native crash because bugly is disable.");
      return;
    }
    if (!CrashModule.hasInitialized())
    {
      Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      return;
    }
    x.a("start to create a native crash for test!", new Object[0]);
    c.a().j();
  }

  public static class CrashHandleCallback extends BuglyStrategy.a
  {
  }

  public static class UserStrategy extends BuglyStrategy
  {
    private CrashReport.CrashHandleCallback a;

    public UserStrategy(Context paramContext)
    {
    }

    public CrashReport.CrashHandleCallback getCrashHandleCallback()
    {
      try
      {
        CrashReport.CrashHandleCallback localCrashHandleCallback = this.a;
        return localCrashHandleCallback;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    public void setCrashHandleCallback(CrashReport.CrashHandleCallback paramCrashHandleCallback)
    {
      try
      {
        this.a = paramCrashHandleCallback;
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }
  }

  public static abstract interface WebViewInterface
  {
    public abstract void addJavascriptInterface(H5JavaScriptInterface paramH5JavaScriptInterface, String paramString);

    public abstract CharSequence getContentDescription();

    public abstract String getUrl();

    public abstract void loadUrl(String paramString);

    public abstract void setJavaScriptEnabled(boolean paramBoolean);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.crashreport.CrashReport
 * JD-Core Version:    0.6.1
 */