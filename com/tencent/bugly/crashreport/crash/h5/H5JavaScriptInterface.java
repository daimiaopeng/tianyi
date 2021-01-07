package com.tencent.bugly.crashreport.crash.h5;

import android.webkit.JavascriptInterface;
import com.tencent.bugly.crashreport.CrashReport.WebViewInterface;
import com.tencent.bugly.crashreport.inner.InnerApi;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;

public class H5JavaScriptInterface
{
  private static HashSet<Integer> a = new HashSet();
  private String b = null;
  private Thread c = null;
  private String d = null;
  private Map<String, String> e = null;

  private static a a(String paramString)
  {
    if ((paramString != null) && (paramString.length() > 0))
      try
      {
        JSONObject localJSONObject = new JSONObject(paramString);
        a locala = new a();
        locala.a = localJSONObject.getString("projectRoot");
        if (locala.a == null)
          return null;
        locala.b = localJSONObject.getString("context");
        if (locala.b == null)
          return null;
        locala.c = localJSONObject.getString("url");
        if (locala.c == null)
          return null;
        locala.d = localJSONObject.getString("userAgent");
        if (locala.d == null)
          return null;
        locala.e = localJSONObject.getString("language");
        if (locala.e == null)
          return null;
        locala.f = localJSONObject.getString("name");
        if ((locala.f != null) && (!locala.f.equals("null")))
        {
          String str = localJSONObject.getString("stacktrace");
          if (str == null)
            return null;
          int i = str.indexOf("\n");
          if (i < 0)
          {
            x.d("H5 crash stack's format is wrong!", new Object[0]);
            return null;
          }
          locala.h = str.substring(i + 1);
          locala.g = str.substring(0, i);
          int j = locala.g.indexOf(":");
          if (j > 0)
            locala.g = locala.g.substring(j + 1);
          locala.i = localJSONObject.getString("file");
          if (locala.f == null)
            return null;
          locala.j = localJSONObject.getLong("lineNumber");
          if (locala.j < 0L)
            return null;
          locala.k = localJSONObject.getLong("columnNumber");
          if (locala.k < 0L)
            return null;
          x.a("H5 crash information is following: ", new Object[0]);
          StringBuilder localStringBuilder1 = new StringBuilder("[projectRoot]: ");
          localStringBuilder1.append(locala.a);
          x.a(localStringBuilder1.toString(), new Object[0]);
          StringBuilder localStringBuilder2 = new StringBuilder("[context]: ");
          localStringBuilder2.append(locala.b);
          x.a(localStringBuilder2.toString(), new Object[0]);
          StringBuilder localStringBuilder3 = new StringBuilder("[url]: ");
          localStringBuilder3.append(locala.c);
          x.a(localStringBuilder3.toString(), new Object[0]);
          StringBuilder localStringBuilder4 = new StringBuilder("[userAgent]: ");
          localStringBuilder4.append(locala.d);
          x.a(localStringBuilder4.toString(), new Object[0]);
          StringBuilder localStringBuilder5 = new StringBuilder("[language]: ");
          localStringBuilder5.append(locala.e);
          x.a(localStringBuilder5.toString(), new Object[0]);
          StringBuilder localStringBuilder6 = new StringBuilder("[name]: ");
          localStringBuilder6.append(locala.f);
          x.a(localStringBuilder6.toString(), new Object[0]);
          StringBuilder localStringBuilder7 = new StringBuilder("[message]: ");
          localStringBuilder7.append(locala.g);
          x.a(localStringBuilder7.toString(), new Object[0]);
          StringBuilder localStringBuilder8 = new StringBuilder("[stacktrace]: \n");
          localStringBuilder8.append(locala.h);
          x.a(localStringBuilder8.toString(), new Object[0]);
          StringBuilder localStringBuilder9 = new StringBuilder("[file]: ");
          localStringBuilder9.append(locala.i);
          x.a(localStringBuilder9.toString(), new Object[0]);
          StringBuilder localStringBuilder10 = new StringBuilder("[lineNumber]: ");
          localStringBuilder10.append(locala.j);
          x.a(localStringBuilder10.toString(), new Object[0]);
          StringBuilder localStringBuilder11 = new StringBuilder("[columnNumber]: ");
          localStringBuilder11.append(locala.k);
          x.a(localStringBuilder11.toString(), new Object[0]);
          return locala;
        }
        return null;
      }
      catch (Throwable localThrowable)
      {
        if (!x.a(localThrowable))
          localThrowable.printStackTrace();
        return null;
      }
    return null;
  }

  public static H5JavaScriptInterface getInstance(CrashReport.WebViewInterface paramWebViewInterface)
  {
    if ((paramWebViewInterface != null) && (!a.contains(Integer.valueOf(paramWebViewInterface.hashCode()))))
    {
      H5JavaScriptInterface localH5JavaScriptInterface = new H5JavaScriptInterface();
      a.add(Integer.valueOf(paramWebViewInterface.hashCode()));
      localH5JavaScriptInterface.c = Thread.currentThread();
      Thread localThread = localH5JavaScriptInterface.c;
      String str;
      if (localThread == null)
      {
        str = null;
      }
      else
      {
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("\n");
        for (int i = 2; i < localThread.getStackTrace().length; i++)
        {
          StackTraceElement localStackTraceElement = localThread.getStackTrace()[i];
          if (!localStackTraceElement.toString().contains("crashreport"))
          {
            localStringBuilder2.append(localStackTraceElement.toString());
            localStringBuilder2.append("\n");
          }
        }
        str = localStringBuilder2.toString();
      }
      localH5JavaScriptInterface.d = str;
      HashMap localHashMap = new HashMap();
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append(paramWebViewInterface.getContentDescription());
      localHashMap.put("[WebView] ContentDescription", localStringBuilder1.toString());
      localH5JavaScriptInterface.e = localHashMap;
      return localH5JavaScriptInterface;
    }
    return null;
  }

  @JavascriptInterface
  public void printLog(String paramString)
  {
    x.d("Log from js: %s", new Object[] { paramString });
  }

  @JavascriptInterface
  public void reportJSException(String paramString)
  {
    if (paramString == null)
    {
      x.d("Payload from JS is null.", new Object[0]);
      return;
    }
    String str = z.b(paramString.getBytes());
    if ((this.b != null) && (this.b.equals(str)))
    {
      x.d("Same payload from js. Please check whether you've injected bugly.js more than one times.", new Object[0]);
      return;
    }
    this.b = str;
    x.d("Handling JS exception ...", new Object[0]);
    a locala = a(paramString);
    if (locala == null)
    {
      x.d("Failed to parse payload.", new Object[0]);
      return;
    }
    LinkedHashMap localLinkedHashMap1 = new LinkedHashMap();
    LinkedHashMap localLinkedHashMap2 = new LinkedHashMap();
    if (locala.a != null)
      localLinkedHashMap2.put("[JS] projectRoot", locala.a);
    if (locala.b != null)
      localLinkedHashMap2.put("[JS] context", locala.b);
    if (locala.c != null)
      localLinkedHashMap2.put("[JS] url", locala.c);
    if (locala.d != null)
      localLinkedHashMap2.put("[JS] userAgent", locala.d);
    if (locala.i != null)
      localLinkedHashMap2.put("[JS] file", locala.i);
    if (locala.j != 0L)
      localLinkedHashMap2.put("[JS] lineNumber", Long.toString(locala.j));
    localLinkedHashMap1.putAll(localLinkedHashMap2);
    localLinkedHashMap1.putAll(this.e);
    localLinkedHashMap1.put("Java Stack", this.d);
    Thread localThread = this.c;
    if (locala != null)
      InnerApi.postH5CrashAsync(localThread, locala.f, locala.g, locala.h, localLinkedHashMap1);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.crashreport.crash.h5.H5JavaScriptInterface
 * JD-Core Version:    0.6.1
 */