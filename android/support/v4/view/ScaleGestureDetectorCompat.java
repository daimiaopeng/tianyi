package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.ScaleGestureDetector;

public final class ScaleGestureDetectorCompat
{
  public static boolean isQuickScaleEnabled(ScaleGestureDetector paramScaleGestureDetector)
  {
    if (Build.VERSION.SDK_INT >= 19)
      return paramScaleGestureDetector.isQuickScaleEnabled();
    return false;
  }

  @Deprecated
  public static boolean isQuickScaleEnabled(Object paramObject)
  {
    return isQuickScaleEnabled((ScaleGestureDetector)paramObject);
  }

  public static void setQuickScaleEnabled(ScaleGestureDetector paramScaleGestureDetector, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 19)
      paramScaleGestureDetector.setQuickScaleEnabled(paramBoolean);
  }

  @Deprecated
  public static void setQuickScaleEnabled(Object paramObject, boolean paramBoolean)
  {
    setQuickScaleEnabled((ScaleGestureDetector)paramObject, paramBoolean);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.view.ScaleGestureDetectorCompat
 * JD-Core Version:    0.6.1
 */