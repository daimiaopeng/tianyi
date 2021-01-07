package com.karics.library.zxing.a;

import android.graphics.Point;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public final class c
{
  private static final Pattern a = Pattern.compile(";");

  public static Point a(Camera.Parameters paramParameters, Point paramPoint)
  {
    List localList = paramParameters.getSupportedPreviewSizes();
    if (localList == null)
    {
      Log.w("CameraConfiguration", "Device returned no supported preview sizes; using default");
      Camera.Size localSize5 = paramParameters.getPreviewSize();
      if (localSize5 == null)
        throw new IllegalStateException("Parameters contained no preview size!");
      return new Point(localSize5.width, localSize5.height);
    }
    ArrayList localArrayList = new ArrayList(localList);
    Collections.sort(localArrayList, new Comparator()
    {
      public int a(Camera.Size paramAnonymousSize1, Camera.Size paramAnonymousSize2)
      {
        int i = paramAnonymousSize1.height * paramAnonymousSize1.width;
        int j = paramAnonymousSize2.height * paramAnonymousSize2.width;
        if (j < i)
          return -1;
        if (j > i)
          return 1;
        return 0;
      }
    });
    if (Log.isLoggable("CameraConfiguration", 4))
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      Iterator localIterator1 = localArrayList.iterator();
      while (localIterator1.hasNext())
      {
        Camera.Size localSize4 = (Camera.Size)localIterator1.next();
        localStringBuilder1.append(localSize4.width);
        localStringBuilder1.append('x');
        localStringBuilder1.append(localSize4.height);
        localStringBuilder1.append(' ');
      }
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("Supported preview sizes: ");
      localStringBuilder2.append(localStringBuilder1);
      Log.i("CameraConfiguration", localStringBuilder2.toString());
    }
    double d = paramPoint.x / paramPoint.y;
    Iterator localIterator2 = localArrayList.iterator();
    while (localIterator2.hasNext())
    {
      Camera.Size localSize3 = (Camera.Size)localIterator2.next();
      int i = localSize3.width;
      int j = localSize3.height;
      if (i * j < 153600)
      {
        localIterator2.remove();
      }
      else
      {
        int k = 0;
        if (i < j)
          k = 1;
        int m;
        if (k != 0)
          m = j;
        else
          m = i;
        int n;
        if (k != 0)
          n = i;
        else
          n = j;
        if (Math.abs(m / n - d) > 0.15D)
        {
          localIterator2.remove();
        }
        else if ((m == paramPoint.x) && (n == paramPoint.y))
        {
          Point localPoint3 = new Point(i, j);
          StringBuilder localStringBuilder5 = new StringBuilder();
          localStringBuilder5.append("Found preview size exactly matching screen size: ");
          localStringBuilder5.append(localPoint3);
          Log.i("CameraConfiguration", localStringBuilder5.toString());
          return localPoint3;
        }
      }
    }
    if (!localArrayList.isEmpty())
    {
      Camera.Size localSize2 = (Camera.Size)localArrayList.get(0);
      Point localPoint2 = new Point(localSize2.width, localSize2.height);
      StringBuilder localStringBuilder4 = new StringBuilder();
      localStringBuilder4.append("Using largest suitable preview size: ");
      localStringBuilder4.append(localPoint2);
      Log.i("CameraConfiguration", localStringBuilder4.toString());
      return localPoint2;
    }
    Camera.Size localSize1 = paramParameters.getPreviewSize();
    if (localSize1 == null)
      throw new IllegalStateException("Parameters contained no preview size!");
    Point localPoint1 = new Point(localSize1.width, localSize1.height);
    StringBuilder localStringBuilder3 = new StringBuilder();
    localStringBuilder3.append("No suitable preview sizes, using default: ");
    localStringBuilder3.append(localPoint1);
    Log.i("CameraConfiguration", localStringBuilder3.toString());
    return localPoint1;
  }

  private static String a(String paramString, Collection<String> paramCollection, String[] paramArrayOfString)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("Requesting ");
    localStringBuilder1.append(paramString);
    localStringBuilder1.append(" value from among: ");
    localStringBuilder1.append(Arrays.toString(paramArrayOfString));
    Log.i("CameraConfiguration", localStringBuilder1.toString());
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("Supported ");
    localStringBuilder2.append(paramString);
    localStringBuilder2.append(" values: ");
    localStringBuilder2.append(paramCollection);
    Log.i("CameraConfiguration", localStringBuilder2.toString());
    if (paramCollection != null)
    {
      int i = paramArrayOfString.length;
      for (int j = 0; j < i; j++)
      {
        String str = paramArrayOfString[j];
        if (paramCollection.contains(str))
        {
          StringBuilder localStringBuilder3 = new StringBuilder();
          localStringBuilder3.append("Can set ");
          localStringBuilder3.append(paramString);
          localStringBuilder3.append(" to: ");
          localStringBuilder3.append(str);
          Log.i("CameraConfiguration", localStringBuilder3.toString());
          return str;
        }
      }
    }
    Log.i("CameraConfiguration", "No supported values match");
    return null;
  }

  public static void a(Camera.Parameters paramParameters, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    List localList = paramParameters.getSupportedFocusModes();
    String str;
    if (paramBoolean1)
    {
      if ((!paramBoolean3) && (!paramBoolean2))
        str = a("focus mode", localList, new String[] { "continuous-video", "auto" });
      else
        str = a("focus mode", localList, new String[] { "auto" });
    }
    else
      str = null;
    if ((!paramBoolean3) && (str == null))
      str = a("focus mode", localList, new String[] { "macro", "edof" });
    if (str != null)
      if (str.equals(paramParameters.getFocusMode()))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Focus mode already set to ");
        localStringBuilder.append(str);
        Log.i("CameraConfiguration", localStringBuilder.toString());
      }
      else
      {
        paramParameters.setFocusMode(str);
      }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.karics.library.zxing.a.c
 * JD-Core Version:    0.6.1
 */