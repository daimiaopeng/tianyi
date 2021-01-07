package android.support.v4.graphics;

import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.v4.util.Preconditions;

public final class PathSegment
{
  private final PointF mEnd;
  private final float mEndFraction;
  private final PointF mStart;
  private final float mStartFraction;

  public PathSegment(@NonNull PointF paramPointF1, float paramFloat1, @NonNull PointF paramPointF2, float paramFloat2)
  {
    this.mStart = ((PointF)Preconditions.checkNotNull(paramPointF1, "start == null"));
    this.mStartFraction = paramFloat1;
    this.mEnd = ((PointF)Preconditions.checkNotNull(paramPointF2, "end == null"));
    this.mEndFraction = paramFloat2;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject)
      return bool;
    if (!(paramObject instanceof PathSegment))
      return false;
    PathSegment localPathSegment = (PathSegment)paramObject;
    if ((Float.compare(this.mStartFraction, localPathSegment.mStartFraction) != 0) || (Float.compare(this.mEndFraction, localPathSegment.mEndFraction) != 0) || (!this.mStart.equals(localPathSegment.mStart)) || (!this.mEnd.equals(localPathSegment.mEnd)))
      bool = false;
    return bool;
  }

  @NonNull
  public PointF getEnd()
  {
    return this.mEnd;
  }

  public float getEndFraction()
  {
    return this.mEndFraction;
  }

  @NonNull
  public PointF getStart()
  {
    return this.mStart;
  }

  public float getStartFraction()
  {
    return this.mStartFraction;
  }

  public int hashCode()
  {
    int i = 31 * this.mStart.hashCode();
    int j;
    if (this.mStartFraction != 0.0F)
      j = Float.floatToIntBits(this.mStartFraction);
    else
      j = 0;
    int k = 31 * (31 * (i + j) + this.mEnd.hashCode());
    boolean bool = this.mEndFraction < 0.0F;
    int m = 0;
    if (bool)
      m = Float.floatToIntBits(this.mEndFraction);
    return k + m;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("PathSegment{start=");
    localStringBuilder.append(this.mStart);
    localStringBuilder.append(", startFraction=");
    localStringBuilder.append(this.mStartFraction);
    localStringBuilder.append(", end=");
    localStringBuilder.append(this.mEnd);
    localStringBuilder.append(", endFraction=");
    localStringBuilder.append(this.mEndFraction);
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.graphics.PathSegment
 * JD-Core Version:    0.6.1
 */