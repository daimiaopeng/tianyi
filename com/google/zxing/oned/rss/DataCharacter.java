package com.google.zxing.oned.rss;

public class DataCharacter
{
  private final int checksumPortion;
  private final int value;

  public DataCharacter(int paramInt1, int paramInt2)
  {
    this.value = paramInt1;
    this.checksumPortion = paramInt2;
  }

  public final boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof DataCharacter))
      return false;
    DataCharacter localDataCharacter = (DataCharacter)paramObject;
    int i = this.value;
    int j = localDataCharacter.value;
    boolean bool = false;
    if (i == j)
    {
      int k = this.checksumPortion;
      int m = localDataCharacter.checksumPortion;
      bool = false;
      if (k == m)
        bool = true;
    }
    return bool;
  }

  public final int getChecksumPortion()
  {
    return this.checksumPortion;
  }

  public final int getValue()
  {
    return this.value;
  }

  public final int hashCode()
  {
    return this.value ^ this.checksumPortion;
  }

  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.value);
    localStringBuilder.append("(");
    localStringBuilder.append(this.checksumPortion);
    localStringBuilder.append(')');
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.DataCharacter
 * JD-Core Version:    0.6.1
 */