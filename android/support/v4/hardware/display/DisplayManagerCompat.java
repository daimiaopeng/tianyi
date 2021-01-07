package android.support.v4.hardware.display;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.WindowManager;
import java.util.WeakHashMap;

public final class DisplayManagerCompat
{
  public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";
  private static final WeakHashMap<Context, DisplayManagerCompat> sInstances = new WeakHashMap();
  private final Context mContext;

  private DisplayManagerCompat(Context paramContext)
  {
    this.mContext = paramContext;
  }

  @NonNull
  public static DisplayManagerCompat getInstance(@NonNull Context paramContext)
  {
    synchronized (sInstances)
    {
      DisplayManagerCompat localDisplayManagerCompat = (DisplayManagerCompat)sInstances.get(paramContext);
      if (localDisplayManagerCompat == null)
      {
        localDisplayManagerCompat = new DisplayManagerCompat(paramContext);
        sInstances.put(paramContext, localDisplayManagerCompat);
      }
      return localDisplayManagerCompat;
    }
  }

  @Nullable
  public Display getDisplay(int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 17)
      return ((DisplayManager)this.mContext.getSystemService("display")).getDisplay(paramInt);
    Display localDisplay = ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay();
    if (localDisplay.getDisplayId() == paramInt)
      return localDisplay;
    return null;
  }

  @NonNull
  public Display[] getDisplays()
  {
    if (Build.VERSION.SDK_INT >= 17)
      return ((DisplayManager)this.mContext.getSystemService("display")).getDisplays();
    return new Display[] { ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay() };
  }

  @NonNull
  public Display[] getDisplays(@Nullable String paramString)
  {
    if (Build.VERSION.SDK_INT >= 17)
      return ((DisplayManager)this.mContext.getSystemService("display")).getDisplays(paramString);
    if (paramString == null)
      return new Display[0];
    return new Display[] { ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay() };
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.hardware.display.DisplayManagerCompat
 * JD-Core Version:    0.6.1
 */