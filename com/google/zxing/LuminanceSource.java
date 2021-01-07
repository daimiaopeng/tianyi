package com.google.zxing;

public abstract class LuminanceSource
{
  private final int height;
  private final int width;

  protected LuminanceSource(int paramInt1, int paramInt2)
  {
    this.width = paramInt1;
    this.height = paramInt2;
  }

  public LuminanceSource crop(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    throw new UnsupportedOperationException("This luminance source does not support cropping.");
  }

  public final int getHeight()
  {
    return this.height;
  }

  public abstract byte[] getMatrix();

  public abstract byte[] getRow(int paramInt, byte[] paramArrayOfByte);

  public final int getWidth()
  {
    return this.width;
  }

  public LuminanceSource invert()
  {
    return new InvertedLuminanceSource(this);
  }

  public boolean isCropSupported()
  {
    return false;
  }

  public boolean isRotateSupported()
  {
    return false;
  }

  public LuminanceSource rotateCounterClockwise()
  {
    throw new UnsupportedOperationException("This luminance source does not support rotation by 90 degrees.");
  }

  public LuminanceSource rotateCounterClockwise45()
  {
    throw new UnsupportedOperationException("This luminance source does not support rotation by 45 degrees.");
  }

  public final String toString()
  {
    byte[] arrayOfByte1 = new byte[this.width];
    StringBuilder localStringBuilder = new StringBuilder(this.height * (1 + this.width));
    byte[] arrayOfByte2 = arrayOfByte1;
    for (int i = 0; i < this.height; i++)
    {
      arrayOfByte2 = getRow(i, arrayOfByte2);
      for (int j = 0; j < this.width; j++)
      {
        int k = 0xFF & arrayOfByte2[j];
        char c;
        if (k < 64)
          c = '#';
        else if (k < 128)
          c = '+';
        else if (k < 192)
          c = '.';
        else
          c = ' ';
        localStringBuilder.append(c);
      }
      localStringBuilder.append('\n');
    }
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.LuminanceSource
 * JD-Core Version:    0.6.1
 */