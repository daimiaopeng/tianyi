package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.view.ViewConfiguration;
import java.lang.reflect.Method;

public final class ViewConfigurationCompat
{
  private static final String TAG = "ViewConfigCompat";
  private static Method sGetScaledScrollFactorMethod;

  // ERROR //
  static
  {
    // Byte code:
    //   0: getstatic 20	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 25
    //   5: if_icmpne +28 -> 33
    //   8: ldc 22
    //   10: ldc 24
    //   12: iconst_0
    //   13: anewarray 26	java/lang/Class
    //   16: invokevirtual 30	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   19: putstatic 32	android/support/v4/view/ViewConfigurationCompat:sGetScaledScrollFactorMethod	Ljava/lang/reflect/Method;
    //   22: goto +11 -> 33
    //   25: ldc 8
    //   27: ldc 34
    //   29: invokestatic 40	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   32: pop
    //   33: return
    //
    // Exception table:
    //   from	to	target	type
    //   8	22	25	java/lang/Exception
  }

  // ERROR //
  private static float getLegacyScrollFactor(ViewConfiguration paramViewConfiguration, Context paramContext)
  {
    // Byte code:
    //   0: getstatic 20	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 25
    //   5: if_icmplt +40 -> 45
    //   8: getstatic 32	android/support/v4/view/ViewConfigurationCompat:sGetScaledScrollFactorMethod	Ljava/lang/reflect/Method;
    //   11: ifnull +34 -> 45
    //   14: getstatic 32	android/support/v4/view/ViewConfigurationCompat:sGetScaledScrollFactorMethod	Ljava/lang/reflect/Method;
    //   17: aload_0
    //   18: iconst_0
    //   19: anewarray 4	java/lang/Object
    //   22: invokevirtual 51	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   25: checkcast 53	java/lang/Integer
    //   28: invokevirtual 57	java/lang/Integer:intValue	()I
    //   31: istore 4
    //   33: iload 4
    //   35: i2f
    //   36: freturn
    //   37: ldc 8
    //   39: ldc 34
    //   41: invokestatic 40	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   44: pop
    //   45: new 59	android/util/TypedValue
    //   48: dup
    //   49: invokespecial 60	android/util/TypedValue:<init>	()V
    //   52: astore_2
    //   53: aload_1
    //   54: invokevirtual 66	android/content/Context:getTheme	()Landroid/content/res/Resources$Theme;
    //   57: ldc 67
    //   59: aload_2
    //   60: iconst_1
    //   61: invokevirtual 73	android/content/res/Resources$Theme:resolveAttribute	(ILandroid/util/TypedValue;Z)Z
    //   64: ifeq +15 -> 79
    //   67: aload_2
    //   68: aload_1
    //   69: invokevirtual 77	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   72: invokevirtual 83	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   75: invokevirtual 87	android/util/TypedValue:getDimension	(Landroid/util/DisplayMetrics;)F
    //   78: freturn
    //   79: fconst_0
    //   80: freturn
    //
    // Exception table:
    //   from	to	target	type
    //   14	33	37	java/lang/Exception
  }

  public static float getScaledHorizontalScrollFactor(@NonNull ViewConfiguration paramViewConfiguration, @NonNull Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 26)
      return paramViewConfiguration.getScaledHorizontalScrollFactor();
    return getLegacyScrollFactor(paramViewConfiguration, paramContext);
  }

  public static int getScaledHoverSlop(ViewConfiguration paramViewConfiguration)
  {
    if (Build.VERSION.SDK_INT >= 28)
      return paramViewConfiguration.getScaledHoverSlop();
    return paramViewConfiguration.getScaledTouchSlop() / 2;
  }

  @Deprecated
  public static int getScaledPagingTouchSlop(ViewConfiguration paramViewConfiguration)
  {
    return paramViewConfiguration.getScaledPagingTouchSlop();
  }

  public static float getScaledVerticalScrollFactor(@NonNull ViewConfiguration paramViewConfiguration, @NonNull Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 26)
      return paramViewConfiguration.getScaledVerticalScrollFactor();
    return getLegacyScrollFactor(paramViewConfiguration, paramContext);
  }

  @Deprecated
  public static boolean hasPermanentMenuKey(ViewConfiguration paramViewConfiguration)
  {
    return paramViewConfiguration.hasPermanentMenuKey();
  }

  public static boolean shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration paramViewConfiguration, @NonNull Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 28)
      return paramViewConfiguration.shouldShowMenuShortcutsWhenKeyboardPresent();
    Resources localResources = paramContext.getResources();
    int i = localResources.getIdentifier("config_showMenuShortcutsWhenKeyboardPresent", "bool", "android");
    boolean bool;
    if ((i != 0) && (localResources.getBoolean(i)))
      bool = true;
    else
      bool = false;
    return bool;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.view.ViewConfigurationCompat
 * JD-Core Version:    0.6.1
 */