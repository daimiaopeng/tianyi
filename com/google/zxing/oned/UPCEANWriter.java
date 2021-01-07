package com.google.zxing.oned;

public abstract class UPCEANWriter extends OneDimensionalCodeWriter
{
  public int getDefaultMargin()
  {
    return UPCEANReader.START_END_PATTERN.length;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.UPCEANWriter
 * JD-Core Version:    0.6.1
 */