package android.support.v4.util;

import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import java.util.Arrays;
import java.util.Objects;

public class ObjectsCompat
{
  public static boolean equals(@Nullable Object paramObject1, @Nullable Object paramObject2)
  {
    if (Build.VERSION.SDK_INT >= 19)
      return Objects.equals(paramObject1, paramObject2);
    boolean bool;
    if ((paramObject1 != paramObject2) && ((paramObject1 == null) || (!paramObject1.equals(paramObject2))))
      bool = false;
    else
      bool = true;
    return bool;
  }

  public static int hash(@Nullable Object[] paramArrayOfObject)
  {
    if (Build.VERSION.SDK_INT >= 19)
      return Objects.hash(paramArrayOfObject);
    return Arrays.hashCode(paramArrayOfObject);
  }

  public static int hashCode(@Nullable Object paramObject)
  {
    int i;
    if (paramObject != null)
      i = paramObject.hashCode();
    else
      i = 0;
    return i;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.util.ObjectsCompat
 * JD-Core Version:    0.6.1
 */