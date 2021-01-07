package android.support.v4.media;

import android.os.Bundle;
import android.support.annotation.RestrictTo;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class MediaBrowserCompatUtils
{
  public static boolean areSameOptions(Bundle paramBundle1, Bundle paramBundle2)
  {
    boolean bool = true;
    if (paramBundle1 == paramBundle2)
      return bool;
    if (paramBundle1 == null)
    {
      if ((paramBundle2.getInt("android.media.browse.extra.PAGE", -1) != -1) || (paramBundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1) != -1))
        bool = false;
      return bool;
    }
    if (paramBundle2 == null)
    {
      if ((paramBundle1.getInt("android.media.browse.extra.PAGE", -1) != -1) || (paramBundle1.getInt("android.media.browse.extra.PAGE_SIZE", -1) != -1))
        bool = false;
      return bool;
    }
    if ((paramBundle1.getInt("android.media.browse.extra.PAGE", -1) != paramBundle2.getInt("android.media.browse.extra.PAGE", -1)) || (paramBundle1.getInt("android.media.browse.extra.PAGE_SIZE", -1) != paramBundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1)))
      bool = false;
    return bool;
  }

  public static boolean hasDuplicatedItems(Bundle paramBundle1, Bundle paramBundle2)
  {
    int i;
    if (paramBundle1 == null)
      i = -1;
    else
      i = paramBundle1.getInt("android.media.browse.extra.PAGE", -1);
    int j;
    if (paramBundle2 == null)
      j = -1;
    else
      j = paramBundle2.getInt("android.media.browse.extra.PAGE", -1);
    int k;
    if (paramBundle1 == null)
      k = -1;
    else
      k = paramBundle1.getInt("android.media.browse.extra.PAGE_SIZE", -1);
    int m;
    if (paramBundle2 == null)
      m = -1;
    else
      m = paramBundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1);
    int n = 2147483647;
    int i2;
    int i1;
    if ((i != -1) && (k != -1))
    {
      i2 = i * k;
      i1 = k + i2 - 1;
    }
    else
    {
      i1 = 2147483647;
      i2 = 0;
    }
    int i3;
    if ((j != -1) && (m != -1))
    {
      i3 = m * j;
      n = -1 + (m + i3);
    }
    else
    {
      i3 = 0;
    }
    boolean bool = false;
    if (i1 >= i3)
    {
      bool = false;
      if (n >= i2)
        bool = true;
    }
    return bool;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.media.MediaBrowserCompatUtils
 * JD-Core Version:    0.6.1
 */