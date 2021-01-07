package android.support.v4.view;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.view.WindowInsets;

public class WindowInsetsCompat
{
  private final Object mInsets;

  public WindowInsetsCompat(WindowInsetsCompat paramWindowInsetsCompat)
  {
    if (Build.VERSION.SDK_INT >= 20)
    {
      WindowInsets localWindowInsets;
      if (paramWindowInsetsCompat == null)
        localWindowInsets = null;
      else
        localWindowInsets = new WindowInsets((WindowInsets)paramWindowInsetsCompat.mInsets);
      this.mInsets = localWindowInsets;
    }
    else
    {
      this.mInsets = null;
    }
  }

  private WindowInsetsCompat(Object paramObject)
  {
    this.mInsets = paramObject;
  }

  static Object unwrap(WindowInsetsCompat paramWindowInsetsCompat)
  {
    Object localObject;
    if (paramWindowInsetsCompat == null)
      localObject = null;
    else
      localObject = paramWindowInsetsCompat.mInsets;
    return localObject;
  }

  static WindowInsetsCompat wrap(Object paramObject)
  {
    WindowInsetsCompat localWindowInsetsCompat;
    if (paramObject == null)
      localWindowInsetsCompat = null;
    else
      localWindowInsetsCompat = new WindowInsetsCompat(paramObject);
    return localWindowInsetsCompat;
  }

  public WindowInsetsCompat consumeDisplayCutout()
  {
    if (Build.VERSION.SDK_INT >= 28)
      return new WindowInsetsCompat(((WindowInsets)this.mInsets).consumeDisplayCutout());
    return this;
  }

  public WindowInsetsCompat consumeStableInsets()
  {
    if (Build.VERSION.SDK_INT >= 21)
      return new WindowInsetsCompat(((WindowInsets)this.mInsets).consumeStableInsets());
    return null;
  }

  public WindowInsetsCompat consumeSystemWindowInsets()
  {
    if (Build.VERSION.SDK_INT >= 20)
      return new WindowInsetsCompat(((WindowInsets)this.mInsets).consumeSystemWindowInsets());
    return null;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject)
      return bool;
    if ((paramObject != null) && (getClass() == paramObject.getClass()))
    {
      WindowInsetsCompat localWindowInsetsCompat = (WindowInsetsCompat)paramObject;
      if (this.mInsets == null)
      {
        if (localWindowInsetsCompat.mInsets != null)
          bool = false;
      }
      else
        bool = this.mInsets.equals(localWindowInsetsCompat.mInsets);
      return bool;
    }
    return false;
  }

  @Nullable
  public DisplayCutoutCompat getDisplayCutout()
  {
    if (Build.VERSION.SDK_INT >= 28)
      return DisplayCutoutCompat.wrap(((WindowInsets)this.mInsets).getDisplayCutout());
    return null;
  }

  public int getStableInsetBottom()
  {
    if (Build.VERSION.SDK_INT >= 21)
      return ((WindowInsets)this.mInsets).getStableInsetBottom();
    return 0;
  }

  public int getStableInsetLeft()
  {
    if (Build.VERSION.SDK_INT >= 21)
      return ((WindowInsets)this.mInsets).getStableInsetLeft();
    return 0;
  }

  public int getStableInsetRight()
  {
    if (Build.VERSION.SDK_INT >= 21)
      return ((WindowInsets)this.mInsets).getStableInsetRight();
    return 0;
  }

  public int getStableInsetTop()
  {
    if (Build.VERSION.SDK_INT >= 21)
      return ((WindowInsets)this.mInsets).getStableInsetTop();
    return 0;
  }

  public int getSystemWindowInsetBottom()
  {
    if (Build.VERSION.SDK_INT >= 20)
      return ((WindowInsets)this.mInsets).getSystemWindowInsetBottom();
    return 0;
  }

  public int getSystemWindowInsetLeft()
  {
    if (Build.VERSION.SDK_INT >= 20)
      return ((WindowInsets)this.mInsets).getSystemWindowInsetLeft();
    return 0;
  }

  public int getSystemWindowInsetRight()
  {
    if (Build.VERSION.SDK_INT >= 20)
      return ((WindowInsets)this.mInsets).getSystemWindowInsetRight();
    return 0;
  }

  public int getSystemWindowInsetTop()
  {
    if (Build.VERSION.SDK_INT >= 20)
      return ((WindowInsets)this.mInsets).getSystemWindowInsetTop();
    return 0;
  }

  public boolean hasInsets()
  {
    if (Build.VERSION.SDK_INT >= 20)
      return ((WindowInsets)this.mInsets).hasInsets();
    return false;
  }

  public boolean hasStableInsets()
  {
    if (Build.VERSION.SDK_INT >= 21)
      return ((WindowInsets)this.mInsets).hasStableInsets();
    return false;
  }

  public boolean hasSystemWindowInsets()
  {
    if (Build.VERSION.SDK_INT >= 20)
      return ((WindowInsets)this.mInsets).hasSystemWindowInsets();
    return false;
  }

  public int hashCode()
  {
    int i;
    if (this.mInsets == null)
      i = 0;
    else
      i = this.mInsets.hashCode();
    return i;
  }

  public boolean isConsumed()
  {
    if (Build.VERSION.SDK_INT >= 21)
      return ((WindowInsets)this.mInsets).isConsumed();
    return false;
  }

  public boolean isRound()
  {
    if (Build.VERSION.SDK_INT >= 20)
      return ((WindowInsets)this.mInsets).isRound();
    return false;
  }

  public WindowInsetsCompat replaceSystemWindowInsets(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (Build.VERSION.SDK_INT >= 20)
      return new WindowInsetsCompat(((WindowInsets)this.mInsets).replaceSystemWindowInsets(paramInt1, paramInt2, paramInt3, paramInt4));
    return null;
  }

  public WindowInsetsCompat replaceSystemWindowInsets(Rect paramRect)
  {
    if (Build.VERSION.SDK_INT >= 21)
      return new WindowInsetsCompat(((WindowInsets)this.mInsets).replaceSystemWindowInsets(paramRect));
    return null;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.view.WindowInsetsCompat
 * JD-Core Version:    0.6.1
 */