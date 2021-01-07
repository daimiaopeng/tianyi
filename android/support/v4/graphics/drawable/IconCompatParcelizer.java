package android.support.v4.graphics.drawable;

import android.support.annotation.RestrictTo;
import androidx.versionedparcelable.a;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
public final class IconCompatParcelizer extends androidx.core.graphics.drawable.IconCompatParcelizer
{
  public static IconCompat read(a parama)
  {
    return androidx.core.graphics.drawable.IconCompatParcelizer.read(parama);
  }

  public static void write(IconCompat paramIconCompat, a parama)
  {
    androidx.core.graphics.drawable.IconCompatParcelizer.write(paramIconCompat, parama);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.graphics.drawable.IconCompatParcelizer
 * JD-Core Version:    0.6.1
 */