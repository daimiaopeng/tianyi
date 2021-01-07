package android.support.v4.os;

import android.os.Build.VERSION;
import android.os.Trace;

public final class TraceCompat
{
  public static void beginSection(String paramString)
  {
    if (Build.VERSION.SDK_INT >= 18)
      Trace.beginSection(paramString);
  }

  public static void endSection()
  {
    if (Build.VERSION.SDK_INT >= 18)
      Trace.endSection();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.os.TraceCompat
 * JD-Core Version:    0.6.1
 */