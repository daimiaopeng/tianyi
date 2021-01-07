package android.support.v4.view;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.DisplayCutout;
import java.util.List;

public final class DisplayCutoutCompat
{
  private final Object mDisplayCutout;

  public DisplayCutoutCompat(Rect paramRect, List<Rect> paramList)
  {
    this(localDisplayCutout);
  }

  private DisplayCutoutCompat(Object paramObject)
  {
    this.mDisplayCutout = paramObject;
  }

  static DisplayCutoutCompat wrap(Object paramObject)
  {
    DisplayCutoutCompat localDisplayCutoutCompat;
    if (paramObject == null)
      localDisplayCutoutCompat = null;
    else
      localDisplayCutoutCompat = new DisplayCutoutCompat(paramObject);
    return localDisplayCutoutCompat;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject)
      return bool;
    if ((paramObject != null) && (getClass() == paramObject.getClass()))
    {
      DisplayCutoutCompat localDisplayCutoutCompat = (DisplayCutoutCompat)paramObject;
      if (this.mDisplayCutout == null)
      {
        if (localDisplayCutoutCompat.mDisplayCutout != null)
          bool = false;
      }
      else
        bool = this.mDisplayCutout.equals(localDisplayCutoutCompat.mDisplayCutout);
      return bool;
    }
    return false;
  }

  public List<Rect> getBoundingRects()
  {
    if (Build.VERSION.SDK_INT >= 28)
      return ((DisplayCutout)this.mDisplayCutout).getBoundingRects();
    return null;
  }

  public int getSafeInsetBottom()
  {
    if (Build.VERSION.SDK_INT >= 28)
      return ((DisplayCutout)this.mDisplayCutout).getSafeInsetBottom();
    return 0;
  }

  public int getSafeInsetLeft()
  {
    if (Build.VERSION.SDK_INT >= 28)
      return ((DisplayCutout)this.mDisplayCutout).getSafeInsetLeft();
    return 0;
  }

  public int getSafeInsetRight()
  {
    if (Build.VERSION.SDK_INT >= 28)
      return ((DisplayCutout)this.mDisplayCutout).getSafeInsetRight();
    return 0;
  }

  public int getSafeInsetTop()
  {
    if (Build.VERSION.SDK_INT >= 28)
      return ((DisplayCutout)this.mDisplayCutout).getSafeInsetTop();
    return 0;
  }

  public int hashCode()
  {
    int i;
    if (this.mDisplayCutout == null)
      i = 0;
    else
      i = this.mDisplayCutout.hashCode();
    return i;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("DisplayCutoutCompat{");
    localStringBuilder.append(this.mDisplayCutout);
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.view.DisplayCutoutCompat
 * JD-Core Version:    0.6.1
 */