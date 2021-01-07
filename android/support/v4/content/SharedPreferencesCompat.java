package android.support.v4.content;

import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;

@Deprecated
public final class SharedPreferencesCompat
{
  @Deprecated
  public static final class EditorCompat
  {
    private static EditorCompat sInstance;
    private final Helper mHelper = new Helper();

    @Deprecated
    public static EditorCompat getInstance()
    {
      if (sInstance == null)
        sInstance = new EditorCompat();
      return sInstance;
    }

    @Deprecated
    public void apply(@NonNull SharedPreferences.Editor paramEditor)
    {
      this.mHelper.apply(paramEditor);
    }

    private static class Helper
    {
      // ERROR //
      public void apply(@NonNull SharedPreferences.Editor paramEditor)
      {
        // Byte code:
        //   0: aload_1
        //   1: invokeinterface 17 1 0
        //   6: goto +10 -> 16
        //   9: aload_1
        //   10: invokeinterface 21 1 0
        //   15: pop
        //   16: return
        //
        // Exception table:
        //   from	to	target	type
        //   0	6	9	java/lang/AbstractMethodError
      }
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.content.SharedPreferencesCompat
 * JD-Core Version:    0.6.1
 */