package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.ViewGroup.MarginLayoutParams;

public final class MarginLayoutParamsCompat
{
  public static int getLayoutDirection(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
  {
    int i;
    if (Build.VERSION.SDK_INT >= 17)
      i = paramMarginLayoutParams.getLayoutDirection();
    else
      i = 0;
    if ((i != 0) && (i != 1))
      i = 0;
    return i;
  }

  public static int getMarginEnd(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
  {
    if (Build.VERSION.SDK_INT >= 17)
      return paramMarginLayoutParams.getMarginEnd();
    return paramMarginLayoutParams.rightMargin;
  }

  public static int getMarginStart(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
  {
    if (Build.VERSION.SDK_INT >= 17)
      return paramMarginLayoutParams.getMarginStart();
    return paramMarginLayoutParams.leftMargin;
  }

  public static boolean isMarginRelative(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
  {
    if (Build.VERSION.SDK_INT >= 17)
      return paramMarginLayoutParams.isMarginRelative();
    return false;
  }

  public static void resolveLayoutDirection(ViewGroup.MarginLayoutParams paramMarginLayoutParams, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 17)
      paramMarginLayoutParams.resolveLayoutDirection(paramInt);
  }

  public static void setLayoutDirection(ViewGroup.MarginLayoutParams paramMarginLayoutParams, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 17)
      paramMarginLayoutParams.setLayoutDirection(paramInt);
  }

  public static void setMarginEnd(ViewGroup.MarginLayoutParams paramMarginLayoutParams, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 17)
      paramMarginLayoutParams.setMarginEnd(paramInt);
    else
      paramMarginLayoutParams.rightMargin = paramInt;
  }

  public static void setMarginStart(ViewGroup.MarginLayoutParams paramMarginLayoutParams, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 17)
      paramMarginLayoutParams.setMarginStart(paramInt);
    else
      paramMarginLayoutParams.leftMargin = paramInt;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.view.MarginLayoutParamsCompat
 * JD-Core Version:    0.6.1
 */