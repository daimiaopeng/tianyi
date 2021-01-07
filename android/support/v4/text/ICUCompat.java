package android.support.v4.text;

import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

public final class ICUCompat
{
  private static final String TAG = "ICUCompat";
  private static Method sAddLikelySubtagsMethod;
  private static Method sGetScriptMethod;

  static
  {
    if (Build.VERSION.SDK_INT >= 21)
      try
      {
        sAddLikelySubtagsMethod = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", new Class[] { Locale.class });
      }
      catch (Exception localException2)
      {
        throw new IllegalStateException(localException2);
      }
    else
      try
      {
        Class localClass = Class.forName("libcore.icu.ICU");
        if (localClass != null)
        {
          sGetScriptMethod = localClass.getMethod("getScript", new Class[] { String.class });
          sAddLikelySubtagsMethod = localClass.getMethod("addLikelySubtags", new Class[] { String.class });
        }
      }
      catch (Exception localException1)
      {
        sGetScriptMethod = null;
        sAddLikelySubtagsMethod = null;
        Log.w("ICUCompat", localException1);
      }
  }

  private static String addLikelySubtags(Locale paramLocale)
  {
    String str1 = paramLocale.toString();
    try
    {
      if (sAddLikelySubtagsMethod != null)
      {
        Object[] arrayOfObject = { str1 };
        String str2 = (String)sAddLikelySubtagsMethod.invoke(null, arrayOfObject);
        return str2;
      }
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      Log.w("ICUCompat", localInvocationTargetException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      Log.w("ICUCompat", localIllegalAccessException);
    }
    return str1;
  }

  private static String getScript(String paramString)
  {
    try
    {
      if (sGetScriptMethod != null)
      {
        Object[] arrayOfObject = { paramString };
        String str = (String)sGetScriptMethod.invoke(null, arrayOfObject);
        return str;
      }
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      Log.w("ICUCompat", localInvocationTargetException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      Log.w("ICUCompat", localIllegalAccessException);
    }
    return null;
  }

  @Nullable
  public static String maximizeAndGetScript(Locale paramLocale)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      try
      {
        Object[] arrayOfObject = { paramLocale };
        String str2 = ((Locale)sAddLikelySubtagsMethod.invoke(null, arrayOfObject)).getScript();
        return str2;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        Log.w("ICUCompat", localIllegalAccessException);
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        Log.w("ICUCompat", localInvocationTargetException);
      }
      return paramLocale.getScript();
    }
    String str1 = addLikelySubtags(paramLocale);
    if (str1 != null)
      return getScript(str1);
    return null;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.text.ICUCompat
 * JD-Core Version:    0.6.1
 */