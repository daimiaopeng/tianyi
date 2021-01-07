package com.google.zxing.oned.rss.expanded.decoders;

final class DecodedChar extends DecodedObject
{
  static final char FNC1 = '$';
  private final char value;

  DecodedChar(int paramInt, char paramChar)
  {
    super(paramInt);
    this.value = paramChar;
  }

  char getValue()
  {
    return this.value;
  }

  boolean isFNC1()
  {
    boolean bool;
    if (this.value == '$')
      bool = true;
    else
      bool = false;
    return bool;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.expanded.decoders.DecodedChar
 * JD-Core Version:    0.6.1
 */