package com.google.zxing;

public final class Dimension
{
  private final int height;
  private final int width;

  public Dimension(int paramInt1, int paramInt2)
  {
    if ((paramInt1 >= 0) && (paramInt2 >= 0))
    {
      this.width = paramInt1;
      this.height = paramInt2;
      return;
    }
    throw new IllegalArgumentException();
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Dimension))
    {
      Dimension localDimension = (Dimension)paramObject;
      int i = this.width;
      int j = localDimension.width;
      boolean bool = false;
      if (i == j)
      {
        int k = this.height;
        int m = localDimension.height;
        bool = false;
        if (k == m)
          bool = true;
      }
      return bool;
    }
    return false;
  }

  public int getHeight()
  {
    return this.height;
  }

  public int getWidth()
  {
    return this.width;
  }

  public int hashCode()
  {
    return 32713 * this.width + this.height;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.width);
    localStringBuilder.append("x");
    localStringBuilder.append(this.height);
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.Dimension
 * JD-Core Version:    0.6.1
 */