package android.support.v4.widget;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class FocusStrategy
{
  private static boolean beamBeats(int paramInt, @NonNull Rect paramRect1, @NonNull Rect paramRect2, @NonNull Rect paramRect3)
  {
    boolean bool1 = beamsOverlap(paramInt, paramRect1, paramRect2);
    if ((!beamsOverlap(paramInt, paramRect1, paramRect3)) && (bool1))
    {
      boolean bool2 = isToDirectionOf(paramInt, paramRect1, paramRect3);
      boolean bool3 = true;
      if (!bool2)
        return bool3;
      if ((paramInt != 17) && (paramInt != 66))
      {
        if (majorAxisDistance(paramInt, paramRect1, paramRect2) >= majorAxisDistanceToFarEdge(paramInt, paramRect1, paramRect3))
          bool3 = false;
        return bool3;
      }
      return bool3;
    }
    return false;
  }

  private static boolean beamsOverlap(int paramInt, @NonNull Rect paramRect1, @NonNull Rect paramRect2)
  {
    if (paramInt != 17)
    {
      if (paramInt != 33)
      {
        if (paramInt == 66)
          break label85;
        if (paramInt != 130)
          throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
      }
      int n = paramRect2.right;
      int i1 = paramRect1.left;
      boolean bool2 = false;
      if (n >= i1)
      {
        int i2 = paramRect2.left;
        int i3 = paramRect1.right;
        bool2 = false;
        if (i2 <= i3)
          bool2 = true;
      }
      return bool2;
    }
    label85: int i = paramRect2.bottom;
    int j = paramRect1.top;
    boolean bool1 = false;
    if (i >= j)
    {
      int k = paramRect2.top;
      int m = paramRect1.bottom;
      bool1 = false;
      if (k <= m)
        bool1 = true;
    }
    return bool1;
  }

  public static <L, T> T findNextFocusInAbsoluteDirection(@NonNull L paramL, @NonNull CollectionAdapter<L, T> paramCollectionAdapter, @NonNull BoundsAdapter<T> paramBoundsAdapter, @Nullable T paramT, @NonNull Rect paramRect, int paramInt)
  {
    Rect localRect1 = new Rect(paramRect);
    int i = 0;
    if (paramInt != 17)
    {
      if (paramInt != 33)
      {
        if (paramInt != 66)
        {
          if (paramInt != 130)
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
          localRect1.offset(0, -(1 + paramRect.height()));
        }
        else
        {
          localRect1.offset(-(1 + paramRect.width()), 0);
        }
      }
      else
        localRect1.offset(0, 1 + paramRect.height());
    }
    else
      localRect1.offset(1 + paramRect.width(), 0);
    Object localObject1 = null;
    int j = paramCollectionAdapter.size(paramL);
    Rect localRect2 = new Rect();
    while (i < j)
    {
      Object localObject2 = paramCollectionAdapter.get(paramL, i);
      if (localObject2 != paramT)
      {
        paramBoundsAdapter.obtainBounds(localObject2, localRect2);
        if (isBetterCandidate(paramInt, paramRect, localRect2, localRect1))
        {
          localRect1.set(localRect2);
          localObject1 = localObject2;
        }
      }
      i++;
    }
    return localObject1;
  }

  public static <L, T> T findNextFocusInRelativeDirection(@NonNull L paramL, @NonNull CollectionAdapter<L, T> paramCollectionAdapter, @NonNull BoundsAdapter<T> paramBoundsAdapter, @Nullable T paramT, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i = paramCollectionAdapter.size(paramL);
    ArrayList localArrayList = new ArrayList(i);
    for (int j = 0; j < i; j++)
      localArrayList.add(paramCollectionAdapter.get(paramL, j));
    Collections.sort(localArrayList, new SequentialComparator(paramBoolean1, paramBoundsAdapter));
    switch (paramInt)
    {
    default:
      throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD}.");
    case 2:
      return getNextFocusable(paramT, localArrayList, paramBoolean2);
    case 1:
    }
    return getPreviousFocusable(paramT, localArrayList, paramBoolean2);
  }

  private static <T> T getNextFocusable(T paramT, ArrayList<T> paramArrayList, boolean paramBoolean)
  {
    int i = paramArrayList.size();
    int j;
    if (paramT == null)
      j = -1;
    else
      j = paramArrayList.lastIndexOf(paramT);
    int k = j + 1;
    if (k < i)
      return paramArrayList.get(k);
    if ((paramBoolean) && (i > 0))
      return paramArrayList.get(0);
    return null;
  }

  private static <T> T getPreviousFocusable(T paramT, ArrayList<T> paramArrayList, boolean paramBoolean)
  {
    int i = paramArrayList.size();
    int j;
    if (paramT == null)
      j = i;
    else
      j = paramArrayList.indexOf(paramT);
    int k = j - 1;
    if (k >= 0)
      return paramArrayList.get(k);
    if ((paramBoolean) && (i > 0))
      return paramArrayList.get(i - 1);
    return null;
  }

  private static int getWeightedDistanceFor(int paramInt1, int paramInt2)
  {
    return paramInt1 * (paramInt1 * 13) + paramInt2 * paramInt2;
  }

  private static boolean isBetterCandidate(int paramInt, @NonNull Rect paramRect1, @NonNull Rect paramRect2, @NonNull Rect paramRect3)
  {
    if (!isCandidate(paramRect1, paramRect2, paramInt))
      return false;
    if (!isCandidate(paramRect1, paramRect3, paramInt))
      return true;
    if (beamBeats(paramInt, paramRect1, paramRect2, paramRect3))
      return true;
    if (beamBeats(paramInt, paramRect1, paramRect3, paramRect2))
      return false;
    int i = getWeightedDistanceFor(majorAxisDistance(paramInt, paramRect1, paramRect2), minorAxisDistance(paramInt, paramRect1, paramRect2));
    int j = getWeightedDistanceFor(majorAxisDistance(paramInt, paramRect1, paramRect3), minorAxisDistance(paramInt, paramRect1, paramRect3));
    boolean bool = false;
    if (i < j)
      bool = true;
    return bool;
  }

  private static boolean isCandidate(@NonNull Rect paramRect1, @NonNull Rect paramRect2, int paramInt)
  {
    if (paramInt != 17)
    {
      if (paramInt != 33)
      {
        if (paramInt != 66)
        {
          if (paramInt != 130)
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
          boolean bool4;
          if (paramRect1.top >= paramRect2.top)
          {
            int i10 = paramRect1.bottom;
            int i11 = paramRect2.top;
            bool4 = false;
            if (i10 > i11);
          }
          else
          {
            int i8 = paramRect1.bottom;
            int i9 = paramRect2.bottom;
            bool4 = false;
            if (i8 < i9)
              bool4 = true;
          }
          return bool4;
        }
        boolean bool3;
        if (paramRect1.left >= paramRect2.left)
        {
          int i6 = paramRect1.right;
          int i7 = paramRect2.left;
          bool3 = false;
          if (i6 > i7);
        }
        else
        {
          int i4 = paramRect1.right;
          int i5 = paramRect2.right;
          bool3 = false;
          if (i4 < i5)
            bool3 = true;
        }
        return bool3;
      }
      boolean bool2;
      if (paramRect1.bottom <= paramRect2.bottom)
      {
        int i2 = paramRect1.top;
        int i3 = paramRect2.bottom;
        bool2 = false;
        if (i2 < i3);
      }
      else
      {
        int n = paramRect1.top;
        int i1 = paramRect2.top;
        bool2 = false;
        if (n > i1)
          bool2 = true;
      }
      return bool2;
    }
    boolean bool1;
    if (paramRect1.right <= paramRect2.right)
    {
      int k = paramRect1.left;
      int m = paramRect2.right;
      bool1 = false;
      if (k < m);
    }
    else
    {
      int i = paramRect1.left;
      int j = paramRect2.left;
      bool1 = false;
      if (i > j)
        bool1 = true;
    }
    return bool1;
  }

  private static boolean isToDirectionOf(int paramInt, @NonNull Rect paramRect1, @NonNull Rect paramRect2)
  {
    if (paramInt != 17)
    {
      if (paramInt != 33)
      {
        if (paramInt != 66)
        {
          if (paramInt != 130)
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
          int i2 = paramRect1.bottom;
          int i3 = paramRect2.top;
          boolean bool4 = false;
          if (i2 <= i3)
            bool4 = true;
          return bool4;
        }
        int n = paramRect1.right;
        int i1 = paramRect2.left;
        boolean bool3 = false;
        if (n <= i1)
          bool3 = true;
        return bool3;
      }
      int k = paramRect1.top;
      int m = paramRect2.bottom;
      boolean bool2 = false;
      if (k >= m)
        bool2 = true;
      return bool2;
    }
    int i = paramRect1.left;
    int j = paramRect2.right;
    boolean bool1 = false;
    if (i >= j)
      bool1 = true;
    return bool1;
  }

  private static int majorAxisDistance(int paramInt, @NonNull Rect paramRect1, @NonNull Rect paramRect2)
  {
    return Math.max(0, majorAxisDistanceRaw(paramInt, paramRect1, paramRect2));
  }

  private static int majorAxisDistanceRaw(int paramInt, @NonNull Rect paramRect1, @NonNull Rect paramRect2)
  {
    if (paramInt != 17)
    {
      if (paramInt != 33)
      {
        if (paramInt != 66)
        {
          if (paramInt != 130)
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
          return paramRect2.top - paramRect1.bottom;
        }
        return paramRect2.left - paramRect1.right;
      }
      return paramRect1.top - paramRect2.bottom;
    }
    return paramRect1.left - paramRect2.right;
  }

  private static int majorAxisDistanceToFarEdge(int paramInt, @NonNull Rect paramRect1, @NonNull Rect paramRect2)
  {
    return Math.max(1, majorAxisDistanceToFarEdgeRaw(paramInt, paramRect1, paramRect2));
  }

  private static int majorAxisDistanceToFarEdgeRaw(int paramInt, @NonNull Rect paramRect1, @NonNull Rect paramRect2)
  {
    if (paramInt != 17)
    {
      if (paramInt != 33)
      {
        if (paramInt != 66)
        {
          if (paramInt != 130)
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
          return paramRect2.bottom - paramRect1.bottom;
        }
        return paramRect2.right - paramRect1.right;
      }
      return paramRect1.top - paramRect2.top;
    }
    return paramRect1.left - paramRect2.left;
  }

  private static int minorAxisDistance(int paramInt, @NonNull Rect paramRect1, @NonNull Rect paramRect2)
  {
    if (paramInt != 17)
    {
      if (paramInt != 33)
      {
        if (paramInt == 66)
          break label62;
        if (paramInt != 130)
          throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
      }
      return Math.abs(paramRect1.left + paramRect1.width() / 2 - (paramRect2.left + paramRect2.width() / 2));
    }
    label62: return Math.abs(paramRect1.top + paramRect1.height() / 2 - (paramRect2.top + paramRect2.height() / 2));
  }

  public static abstract interface BoundsAdapter<T>
  {
    public abstract void obtainBounds(T paramT, Rect paramRect);
  }

  public static abstract interface CollectionAdapter<T, V>
  {
    public abstract V get(T paramT, int paramInt);

    public abstract int size(T paramT);
  }

  private static class SequentialComparator<T>
    implements Comparator<T>
  {
    private final FocusStrategy.BoundsAdapter<T> mAdapter;
    private final boolean mIsLayoutRtl;
    private final Rect mTemp1 = new Rect();
    private final Rect mTemp2 = new Rect();

    SequentialComparator(boolean paramBoolean, FocusStrategy.BoundsAdapter<T> paramBoundsAdapter)
    {
      this.mIsLayoutRtl = paramBoolean;
      this.mAdapter = paramBoundsAdapter;
    }

    public int compare(T paramT1, T paramT2)
    {
      Rect localRect1 = this.mTemp1;
      Rect localRect2 = this.mTemp2;
      this.mAdapter.obtainBounds(paramT1, localRect1);
      this.mAdapter.obtainBounds(paramT2, localRect2);
      int i = localRect1.top;
      int j = localRect2.top;
      int k = -1;
      if (i < j)
        return k;
      if (localRect1.top > localRect2.top)
        return 1;
      if (localRect1.left < localRect2.left)
      {
        if (this.mIsLayoutRtl)
          k = 1;
        return k;
      }
      if (localRect1.left > localRect2.left)
      {
        if (!this.mIsLayoutRtl)
          k = 1;
        return k;
      }
      if (localRect1.bottom < localRect2.bottom)
        return k;
      if (localRect1.bottom > localRect2.bottom)
        return 1;
      if (localRect1.right < localRect2.right)
      {
        if (this.mIsLayoutRtl)
          k = 1;
        return k;
      }
      if (localRect1.right > localRect2.right)
      {
        if (!this.mIsLayoutRtl)
          k = 1;
        return k;
      }
      return 0;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.widget.FocusStrategy
 * JD-Core Version:    0.6.1
 */