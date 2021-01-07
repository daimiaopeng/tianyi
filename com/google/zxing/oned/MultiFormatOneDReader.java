package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Reader;
import com.google.zxing.oned.rss.RSS14Reader;
import com.google.zxing.oned.rss.expanded.RSSExpandedReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class MultiFormatOneDReader extends OneDReader
{
  private final OneDReader[] readers;

  public MultiFormatOneDReader(Map<DecodeHintType, ?> paramMap)
  {
    Collection localCollection;
    if (paramMap == null)
      localCollection = null;
    else
      localCollection = (Collection)paramMap.get(DecodeHintType.POSSIBLE_FORMATS);
    boolean bool;
    if ((paramMap != null) && (paramMap.get(DecodeHintType.ASSUME_CODE_39_CHECK_DIGIT) != null))
      bool = true;
    else
      bool = false;
    ArrayList localArrayList = new ArrayList();
    if (localCollection != null)
    {
      if ((localCollection.contains(BarcodeFormat.EAN_13)) || (localCollection.contains(BarcodeFormat.UPC_A)) || (localCollection.contains(BarcodeFormat.EAN_8)) || (localCollection.contains(BarcodeFormat.UPC_E)))
        localArrayList.add(new MultiFormatUPCEANReader(paramMap));
      if (localCollection.contains(BarcodeFormat.CODE_39))
        localArrayList.add(new Code39Reader(bool));
      if (localCollection.contains(BarcodeFormat.CODE_93))
        localArrayList.add(new Code93Reader());
      if (localCollection.contains(BarcodeFormat.CODE_128))
        localArrayList.add(new Code128Reader());
      if (localCollection.contains(BarcodeFormat.ITF))
        localArrayList.add(new ITFReader());
      if (localCollection.contains(BarcodeFormat.CODABAR))
        localArrayList.add(new CodaBarReader());
      if (localCollection.contains(BarcodeFormat.RSS_14))
        localArrayList.add(new RSS14Reader());
      if (localCollection.contains(BarcodeFormat.RSS_EXPANDED))
        localArrayList.add(new RSSExpandedReader());
    }
    if (localArrayList.isEmpty())
    {
      localArrayList.add(new MultiFormatUPCEANReader(paramMap));
      localArrayList.add(new Code39Reader());
      localArrayList.add(new CodaBarReader());
      localArrayList.add(new Code93Reader());
      localArrayList.add(new Code128Reader());
      localArrayList.add(new ITFReader());
      localArrayList.add(new RSS14Reader());
      localArrayList.add(new RSSExpandedReader());
    }
    this.readers = ((OneDReader[])localArrayList.toArray(new OneDReader[localArrayList.size()]));
  }

  // ERROR //
  public com.google.zxing.Result decodeRow(int paramInt, com.google.zxing.common.BitArray paramBitArray, Map<DecodeHintType, ?> paramMap)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 117	com/google/zxing/oned/MultiFormatOneDReader:readers	[Lcom/google/zxing/oned/OneDReader;
    //   4: astore 4
    //   6: aload 4
    //   8: arraylength
    //   9: istore 5
    //   11: iconst_0
    //   12: istore 6
    //   14: iload 6
    //   16: iload 5
    //   18: if_icmpge +29 -> 47
    //   21: aload 4
    //   23: iload 6
    //   25: aaload
    //   26: astore 7
    //   28: aload 7
    //   30: iload_1
    //   31: aload_2
    //   32: aload_3
    //   33: invokevirtual 123	com/google/zxing/oned/OneDReader:decodeRow	(ILcom/google/zxing/common/BitArray;Ljava/util/Map;)Lcom/google/zxing/Result;
    //   36: astore 8
    //   38: aload 8
    //   40: areturn
    //   41: iinc 6 1
    //   44: goto -30 -> 14
    //   47: invokestatic 129	com/google/zxing/NotFoundException:getNotFoundInstance	()Lcom/google/zxing/NotFoundException;
    //   50: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   28	38	41	com/google/zxing/ReaderException
  }

  public void reset()
  {
    OneDReader[] arrayOfOneDReader = this.readers;
    int i = arrayOfOneDReader.length;
    for (int j = 0; j < i; j++)
      arrayOfOneDReader[j].reset();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.MultiFormatOneDReader
 * JD-Core Version:    0.6.1
 */