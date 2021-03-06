package com.google.zxing.qrcode.encoder;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;

public final class QRCode
{
  public static final int NUM_MASK_PATTERNS = 8;
  private ErrorCorrectionLevel ecLevel;
  private int maskPattern = -1;
  private ByteMatrix matrix;
  private Mode mode;
  private Version version;

  public static boolean isValidMaskPattern(int paramInt)
  {
    boolean bool;
    if ((paramInt >= 0) && (paramInt < 8))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public ErrorCorrectionLevel getECLevel()
  {
    return this.ecLevel;
  }

  public int getMaskPattern()
  {
    return this.maskPattern;
  }

  public ByteMatrix getMatrix()
  {
    return this.matrix;
  }

  public Mode getMode()
  {
    return this.mode;
  }

  public Version getVersion()
  {
    return this.version;
  }

  public void setECLevel(ErrorCorrectionLevel paramErrorCorrectionLevel)
  {
    this.ecLevel = paramErrorCorrectionLevel;
  }

  public void setMaskPattern(int paramInt)
  {
    this.maskPattern = paramInt;
  }

  public void setMatrix(ByteMatrix paramByteMatrix)
  {
    this.matrix = paramByteMatrix;
  }

  public void setMode(Mode paramMode)
  {
    this.mode = paramMode;
  }

  public void setVersion(Version paramVersion)
  {
    this.version = paramVersion;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(200);
    localStringBuilder.append("<<\n");
    localStringBuilder.append(" mode: ");
    localStringBuilder.append(this.mode);
    localStringBuilder.append("\n ecLevel: ");
    localStringBuilder.append(this.ecLevel);
    localStringBuilder.append("\n version: ");
    localStringBuilder.append(this.version);
    localStringBuilder.append("\n maskPattern: ");
    localStringBuilder.append(this.maskPattern);
    if (this.matrix == null)
    {
      localStringBuilder.append("\n matrix: null\n");
    }
    else
    {
      localStringBuilder.append("\n matrix:\n");
      localStringBuilder.append(this.matrix.toString());
    }
    localStringBuilder.append(">>\n");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.encoder.QRCode
 * JD-Core Version:    0.6.1
 */