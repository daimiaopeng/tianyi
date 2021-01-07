package android.support.v4.graphics;

import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

public final class PaintCompat
{
  private static final String EM_STRING = "m";
  private static final String TOFU_STRING = "󟿽";
  private static final ThreadLocal<Pair<Rect, Rect>> sRectThreadLocal = new ThreadLocal();

  public static boolean hasGlyph(@NonNull Paint paramPaint, @NonNull String paramString)
  {
    if (Build.VERSION.SDK_INT >= 23)
      return paramPaint.hasGlyph(paramString);
    int i = paramString.length();
    if ((i == 1) && (Character.isWhitespace(paramString.charAt(0))))
      return true;
    float f1 = paramPaint.measureText("󟿽");
    float f2 = paramPaint.measureText("m");
    float f3 = paramPaint.measureText(paramString);
    float f4 = 0.0F;
    if (f3 == 0.0F)
      return false;
    if (paramString.codePointCount(0, paramString.length()) > 1)
    {
      if (f3 > f2 * 2.0F)
        return false;
      int k;
      for (int j = 0; j < i; j = k)
      {
        k = j + Character.charCount(paramString.codePointAt(j));
        f4 += paramPaint.measureText(paramString, j, k);
      }
      if (f3 >= f4)
        return false;
    }
    if (f3 != f1)
      return true;
    Pair localPair = obtainEmptyRects();
    paramPaint.getTextBounds("󟿽", 0, "󟿽".length(), (Rect)localPair.first);
    paramPaint.getTextBounds(paramString, 0, i, (Rect)localPair.second);
    return true ^ ((Rect)localPair.first).equals(localPair.second);
  }

  private static Pair<Rect, Rect> obtainEmptyRects()
  {
    Pair localPair = (Pair)sRectThreadLocal.get();
    if (localPair == null)
    {
      localPair = new Pair(new Rect(), new Rect());
      sRectThreadLocal.set(localPair);
    }
    else
    {
      ((Rect)localPair.first).setEmpty();
      ((Rect)localPair.second).setEmpty();
    }
    return localPair;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.graphics.PaintCompat
 * JD-Core Version:    0.6.1
 */