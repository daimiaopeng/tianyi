package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

final class AnyAIDecoder extends AbstractExpandedDecoder
{
  private static final int HEADER_SIZE = 5;

  AnyAIDecoder(BitArray paramBitArray)
  {
    super(paramBitArray);
  }

  public String parseInformation()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    return getGeneralDecoder().decodeAllCodes(localStringBuilder, 5);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.expanded.decoders.AnyAIDecoder
 * JD-Core Version:    0.6.1
 */