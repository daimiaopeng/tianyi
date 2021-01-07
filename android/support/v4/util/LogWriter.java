package android.support.v4.util;

import android.support.annotation.RestrictTo;
import android.util.Log;
import java.io.Writer;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class LogWriter extends Writer
{
  private StringBuilder mBuilder = new StringBuilder(128);
  private final String mTag;

  public LogWriter(String paramString)
  {
    this.mTag = paramString;
  }

  private void flushBuilder()
  {
    if (this.mBuilder.length() > 0)
    {
      Log.d(this.mTag, this.mBuilder.toString());
      this.mBuilder.delete(0, this.mBuilder.length());
    }
  }

  public void close()
  {
    flushBuilder();
  }

  public void flush()
  {
    flushBuilder();
  }

  public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    for (int i = 0; i < paramInt2; i++)
    {
      char c = paramArrayOfChar[(paramInt1 + i)];
      if (c == '\n')
        flushBuilder();
      else
        this.mBuilder.append(c);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.util.LogWriter
 * JD-Core Version:    0.6.1
 */