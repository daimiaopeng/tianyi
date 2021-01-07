package com.google.zxing.oned.rss.expanded.decoders;

final class DecodedNumeric extends DecodedObject
{
  static final int FNC1 = 10;
  private final int firstDigit;
  private final int secondDigit;

  DecodedNumeric(int paramInt1, int paramInt2, int paramInt3)
  {
    super(paramInt1);
    this.firstDigit = paramInt2;
    this.secondDigit = paramInt3;
    if ((this.firstDigit >= 0) && (this.firstDigit <= 10))
    {
      if ((this.secondDigit >= 0) && (this.secondDigit <= 10))
        return;
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("Invalid secondDigit: ");
      localStringBuilder2.append(paramInt3);
      throw new IllegalArgumentException(localStringBuilder2.toString());
    }
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("Invalid firstDigit: ");
    localStringBuilder1.append(paramInt2);
    throw new IllegalArgumentException(localStringBuilder1.toString());
  }

  int getFirstDigit()
  {
    return this.firstDigit;
  }

  int getSecondDigit()
  {
    return this.secondDigit;
  }

  int getValue()
  {
    return 10 * this.firstDigit + this.secondDigit;
  }

  boolean isAnyFNC1()
  {
    boolean bool;
    if ((this.firstDigit != 10) && (this.secondDigit != 10))
      bool = false;
    else
      bool = true;
    return bool;
  }

  boolean isFirstDigitFNC1()
  {
    boolean bool;
    if (this.firstDigit == 10)
      bool = true;
    else
      bool = false;
    return bool;
  }

  boolean isSecondDigitFNC1()
  {
    boolean bool;
    if (this.secondDigit == 10)
      bool = true;
    else
      bool = false;
    return bool;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.expanded.decoders.DecodedNumeric
 * JD-Core Version:    0.6.1
 */