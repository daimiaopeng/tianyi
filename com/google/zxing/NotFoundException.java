package com.google.zxing;

public final class NotFoundException extends ReaderException
{
  private static final NotFoundException instance = new NotFoundException();

  public static NotFoundException getNotFoundInstance()
  {
    return instance;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.NotFoundException
 * JD-Core Version:    0.6.1
 */