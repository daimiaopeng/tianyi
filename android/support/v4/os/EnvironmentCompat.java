package android.support.v4.os;

import android.os.Build.VERSION;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.IOException;

public final class EnvironmentCompat
{
  public static final String MEDIA_UNKNOWN = "unknown";
  private static final String TAG = "EnvironmentCompat";

  public static String getStorageState(File paramFile)
  {
    if (Build.VERSION.SDK_INT >= 19)
      return Environment.getStorageState(paramFile);
    try
    {
      if (paramFile.getCanonicalPath().startsWith(Environment.getExternalStorageDirectory().getCanonicalPath()))
      {
        String str = Environment.getExternalStorageState();
        return str;
      }
    }
    catch (IOException localIOException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Failed to resolve canonical path: ");
      localStringBuilder.append(localIOException);
      Log.w("EnvironmentCompat", localStringBuilder.toString());
    }
    return "unknown";
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.os.EnvironmentCompat
 * JD-Core Version:    0.6.1
 */