package com.google.zxing;

public final class ChecksumException extends ReaderException
{
  private static final ChecksumException instance = new ChecksumException();

  public static ChecksumException getChecksumInstance()
  {
    return instance;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.ChecksumException
 * JD-Core Version:    0.6.1
 */