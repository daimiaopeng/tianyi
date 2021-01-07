package com.google.zxing.qrcode.detector;

public final class FinderPatternInfo
{
  private final FinderPattern bottomLeft = paramArrayOfFinderPattern[0];
  private final FinderPattern topLeft = paramArrayOfFinderPattern[1];
  private final FinderPattern topRight = paramArrayOfFinderPattern[2];

  public FinderPatternInfo(FinderPattern[] paramArrayOfFinderPattern)
  {
  }

  public FinderPattern getBottomLeft()
  {
    return this.bottomLeft;
  }

  public FinderPattern getTopLeft()
  {
    return this.topLeft;
  }

  public FinderPattern getTopRight()
  {
    return this.topRight;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.detector.FinderPatternInfo
 * JD-Core Version:    0.6.1
 */