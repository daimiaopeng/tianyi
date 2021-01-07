package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

final class AI01AndOtherAIs extends AI01decoder
{
  private static final int HEADER_SIZE = 4;

  AI01AndOtherAIs(BitArray paramBitArray)
  {
    super(paramBitArray);
  }

  public String parseInformation()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("(01)");
    int i = localStringBuilder.length();
    localStringBuilder.append(getGeneralDecoder().extractNumericValueFromBitArray(4, 4));
    encodeCompressedGtinWithoutAI(localStringBuilder, 8, i);
    return getGeneralDecoder().decodeAllCodes(localStringBuilder, 48);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.expanded.decoders.AI01AndOtherAIs
 * JD-Core Version:    0.6.1
 */