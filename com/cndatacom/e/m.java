package com.cndatacom.e;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class m
{
  public static String a = "TrineaAndroidCommon";

  public static String a(Context paramContext, String paramString)
  {
    return b(paramContext, paramString, null);
  }

  public static boolean a(Context paramContext, String paramString, int paramInt)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(a, 0).edit();
    localEditor.putInt(paramString, paramInt);
    return localEditor.commit();
  }

  public static boolean a(Context paramContext, String paramString, long paramLong)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(a, 0).edit();
    localEditor.putLong(paramString, paramLong);
    return localEditor.commit();
  }

  public static boolean a(Context paramContext, String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(a, 0).edit();
    localEditor.putString(paramString1, paramString2);
    return localEditor.commit();
  }

  public static boolean a(Context paramContext, String paramString, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(a, 0).edit();
    localEditor.putBoolean(paramString, paramBoolean);
    return localEditor.commit();
  }

  public static int b(Context paramContext, String paramString, int paramInt)
  {
    return paramContext.getSharedPreferences(a, 0).getInt(paramString, paramInt);
  }

  public static long b(Context paramContext, String paramString, long paramLong)
  {
    return paramContext.getSharedPreferences(a, 0).getLong(paramString, paramLong);
  }

  public static String b(Context paramContext, String paramString1, String paramString2)
  {
    return paramContext.getSharedPreferences(a, 0).getString(paramString1, paramString2);
  }

  public static boolean b(Context paramContext, String paramString, boolean paramBoolean)
  {
    return paramContext.getSharedPreferences(a, 0).getBoolean(paramString, paramBoolean);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.e.m
 * JD-Core Version:    0.6.1
 */