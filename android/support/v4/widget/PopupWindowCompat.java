package android.support.v4.widget;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class PopupWindowCompat
{
  private static final String TAG = "PopupWindowCompatApi21";
  private static Method sGetWindowLayoutTypeMethod;
  private static boolean sGetWindowLayoutTypeMethodAttempted;
  private static Field sOverlapAnchorField;
  private static boolean sOverlapAnchorFieldAttempted;
  private static Method sSetWindowLayoutTypeMethod;
  private static boolean sSetWindowLayoutTypeMethodAttempted;

  public static boolean getOverlapAnchor(@NonNull PopupWindow paramPopupWindow)
  {
    if (Build.VERSION.SDK_INT >= 23)
      return paramPopupWindow.getOverlapAnchor();
    if (Build.VERSION.SDK_INT >= 21)
    {
      if (!sOverlapAnchorFieldAttempted)
      {
        try
        {
          sOverlapAnchorField = PopupWindow.class.getDeclaredField("mOverlapAnchor");
          sOverlapAnchorField.setAccessible(true);
        }
        catch (NoSuchFieldException localNoSuchFieldException)
        {
          Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", localNoSuchFieldException);
        }
        sOverlapAnchorFieldAttempted = true;
      }
      if (sOverlapAnchorField != null)
        try
        {
          boolean bool = ((Boolean)sOverlapAnchorField.get(paramPopupWindow)).booleanValue();
          return bool;
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          Log.i("PopupWindowCompatApi21", "Could not get overlap anchor field in PopupWindow", localIllegalAccessException);
        }
    }
    return false;
  }

  // ERROR //
  public static int getWindowLayoutType(@NonNull PopupWindow paramPopupWindow)
  {
    // Byte code:
    //   0: getstatic 34	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 23
    //   5: if_icmplt +8 -> 13
    //   8: aload_0
    //   9: invokevirtual 83	android/widget/PopupWindow:getWindowLayoutType	()I
    //   12: ireturn
    //   13: getstatic 85	android/support/v4/widget/PopupWindowCompat:sGetWindowLayoutTypeMethodAttempted	Z
    //   16: ifne +32 -> 48
    //   19: ldc 36
    //   21: ldc 86
    //   23: iconst_0
    //   24: anewarray 45	java/lang/Class
    //   27: invokevirtual 90	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   30: putstatic 92	android/support/v4/widget/PopupWindowCompat:sGetWindowLayoutTypeMethod	Ljava/lang/reflect/Method;
    //   33: getstatic 92	android/support/v4/widget/PopupWindowCompat:sGetWindowLayoutTypeMethod	Ljava/lang/reflect/Method;
    //   36: iconst_1
    //   37: invokevirtual 95	java/lang/reflect/Method:setAccessible	(Z)V
    //   40: goto +4 -> 44
    //   43: pop
    //   44: iconst_1
    //   45: putstatic 85	android/support/v4/widget/PopupWindowCompat:sGetWindowLayoutTypeMethodAttempted	Z
    //   48: getstatic 92	android/support/v4/widget/PopupWindowCompat:sGetWindowLayoutTypeMethod	Ljava/lang/reflect/Method;
    //   51: ifnull +23 -> 74
    //   54: getstatic 92	android/support/v4/widget/PopupWindowCompat:sGetWindowLayoutTypeMethod	Ljava/lang/reflect/Method;
    //   57: aload_0
    //   58: iconst_0
    //   59: anewarray 4	java/lang/Object
    //   62: invokevirtual 99	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   65: checkcast 101	java/lang/Integer
    //   68: invokevirtual 104	java/lang/Integer:intValue	()I
    //   71: istore_1
    //   72: iload_1
    //   73: ireturn
    //   74: iconst_0
    //   75: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   19	43	43	java/lang/Exception
    //   54	72	74	java/lang/Exception
  }

  public static void setOverlapAnchor(@NonNull PopupWindow paramPopupWindow, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 23)
    {
      paramPopupWindow.setOverlapAnchor(paramBoolean);
    }
    else if (Build.VERSION.SDK_INT >= 21)
    {
      if (!sOverlapAnchorFieldAttempted)
      {
        try
        {
          sOverlapAnchorField = PopupWindow.class.getDeclaredField("mOverlapAnchor");
          sOverlapAnchorField.setAccessible(true);
        }
        catch (NoSuchFieldException localNoSuchFieldException)
        {
          Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", localNoSuchFieldException);
        }
        sOverlapAnchorFieldAttempted = true;
      }
      if (sOverlapAnchorField != null)
        try
        {
          sOverlapAnchorField.set(paramPopupWindow, Boolean.valueOf(paramBoolean));
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          Log.i("PopupWindowCompatApi21", "Could not set overlap anchor field in PopupWindow", localIllegalAccessException);
        }
    }
  }

  public static void setWindowLayoutType(@NonNull PopupWindow paramPopupWindow, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 23)
    {
      paramPopupWindow.setWindowLayoutType(paramInt);
      return;
    }
    if (!sSetWindowLayoutTypeMethodAttempted)
    {
      try
      {
        Class[] arrayOfClass = new Class[1];
        arrayOfClass[0] = Integer.TYPE;
        sSetWindowLayoutTypeMethod = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", arrayOfClass);
        sSetWindowLayoutTypeMethod.setAccessible(true);
      }
      catch (Exception localException1)
      {
      }
      sSetWindowLayoutTypeMethodAttempted = true;
    }
    if (sSetWindowLayoutTypeMethod != null);
    try
    {
      Method localMethod = sSetWindowLayoutTypeMethod;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      localMethod.invoke(paramPopupWindow, arrayOfObject);
    }
    catch (Exception localException2)
    {
    }
  }

  public static void showAsDropDown(@NonNull PopupWindow paramPopupWindow, @NonNull View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      paramPopupWindow.showAsDropDown(paramView, paramInt1, paramInt2, paramInt3);
    }
    else
    {
      if ((0x7 & GravityCompat.getAbsoluteGravity(paramInt3, ViewCompat.getLayoutDirection(paramView))) == 5)
        paramInt1 -= paramPopupWindow.getWidth() - paramView.getWidth();
      paramPopupWindow.showAsDropDown(paramView, paramInt1, paramInt2);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.widget.PopupWindowCompat
 * JD-Core Version:    0.6.1
 */