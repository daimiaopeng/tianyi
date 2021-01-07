package com.google.zxing.oned.rss.expanded.decoders;

abstract class DecodedObject
{
  private final int newPosition;

  DecodedObject(int paramInt)
  {
    this.newPosition = paramInt;
  }

  final int getNewPosition()
  {
    return this.newPosition;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.expanded.decoders.DecodedObject
 * JD-Core Version:    0.6.1
 */