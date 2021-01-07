package com.google.zxing;

public final class FormatException extends ReaderException
{
  private static final FormatException instance = new FormatException();

  public static FormatException getFormatInstance()
  {
    return instance;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.FormatException
 * JD-Core Version:    0.6.1
 */