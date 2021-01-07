package android.support.v4.graphics;

import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class PathUtils
{
  @NonNull
  @RequiresApi(26)
  public static Collection<PathSegment> flatten(@NonNull Path paramPath)
  {
    return flatten(paramPath, 0.5F);
  }

  @NonNull
  @RequiresApi(26)
  public static Collection<PathSegment> flatten(@NonNull Path paramPath, @FloatRange(from=0.0D) float paramFloat)
  {
    float[] arrayOfFloat = paramPath.approximate(paramFloat);
    int i = arrayOfFloat.length / 3;
    ArrayList localArrayList = new ArrayList(i);
    for (int j = 1; j < i; j++)
    {
      int k = j * 3;
      int m = 3 * (j - 1);
      float f1 = arrayOfFloat[k];
      float f2 = arrayOfFloat[(k + 1)];
      float f3 = arrayOfFloat[(k + 2)];
      float f4 = arrayOfFloat[m];
      float f5 = arrayOfFloat[(m + 1)];
      float f6 = arrayOfFloat[(m + 2)];
      if ((f1 != f4) && ((f2 != f5) || (f3 != f6)))
        localArrayList.add(new PathSegment(new PointF(f5, f6), f4, new PointF(f2, f3), f1));
    }
    return localArrayList;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.graphics.PathUtils
 * JD-Core Version:    0.6.1
 */