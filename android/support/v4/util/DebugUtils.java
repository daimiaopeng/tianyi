package android.support.v4.util;

import android.support.annotation.RestrictTo;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class DebugUtils
{
  public static void buildShortClassTag(Object paramObject, StringBuilder paramStringBuilder)
  {
    if (paramObject == null)
    {
      paramStringBuilder.append("null");
    }
    else
    {
      String str = paramObject.getClass().getSimpleName();
      if ((str == null) || (str.length() <= 0))
      {
        str = paramObject.getClass().getName();
        int i = str.lastIndexOf('.');
        if (i > 0)
          str = str.substring(i + 1);
      }
      paramStringBuilder.append(str);
      paramStringBuilder.append('{');
      paramStringBuilder.append(Integer.toHexString(System.identityHashCode(paramObject)));
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.util.DebugUtils
 * JD-Core Version:    0.6.1
 */