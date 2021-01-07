package com.google.zxing.oned.rss.expanded;

import com.google.zxing.common.BitArray;
import com.google.zxing.oned.rss.DataCharacter;
import java.util.List;

final class BitArrayBuilder
{
  static BitArray buildBitArray(List<ExpandedPair> paramList)
  {
    int i = (paramList.size() << 1) - 1;
    if (((ExpandedPair)paramList.get(paramList.size() - 1)).getRightChar() == null)
      i--;
    BitArray localBitArray = new BitArray(i * 12);
    int j = ((ExpandedPair)paramList.get(0)).getRightChar().getValue();
    int k = 11;
    int m = 0;
    while (k >= 0)
    {
      if ((j & 1 << k) != 0)
        localBitArray.set(m);
      m++;
      k--;
    }
    for (int n = 1; n < paramList.size(); n++)
    {
      ExpandedPair localExpandedPair = (ExpandedPair)paramList.get(n);
      int i1 = localExpandedPair.getLeftChar().getValue();
      int i2 = m;
      for (int i3 = 11; i3 >= 0; i3--)
      {
        if ((i1 & 1 << i3) != 0)
          localBitArray.set(i2);
        i2++;
      }
      if (localExpandedPair.getRightChar() != null)
      {
        int i4 = localExpandedPair.getRightChar().getValue();
        for (int i5 = 11; i5 >= 0; i5--)
        {
          if ((i4 & 1 << i5) != 0)
            localBitArray.set(i2);
          i2++;
        }
      }
      m = i2;
    }
    return localBitArray;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.expanded.BitArrayBuilder
 * JD-Core Version:    0.6.1
 */