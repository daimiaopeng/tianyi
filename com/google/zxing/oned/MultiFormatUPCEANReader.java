package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class MultiFormatUPCEANReader extends OneDReader
{
  private final UPCEANReader[] readers;

  public MultiFormatUPCEANReader(Map<DecodeHintType, ?> paramMap)
  {
    Collection localCollection;
    if (paramMap == null)
      localCollection = null;
    else
      localCollection = (Collection)paramMap.get(DecodeHintType.POSSIBLE_FORMATS);
    ArrayList localArrayList = new ArrayList();
    if (localCollection != null)
    {
      if (localCollection.contains(BarcodeFormat.EAN_13))
        localArrayList.add(new EAN13Reader());
      else if (localCollection.contains(BarcodeFormat.UPC_A))
        localArrayList.add(new UPCAReader());
      if (localCollection.contains(BarcodeFormat.EAN_8))
        localArrayList.add(new EAN8Reader());
      if (localCollection.contains(BarcodeFormat.UPC_E))
        localArrayList.add(new UPCEReader());
    }
    if (localArrayList.isEmpty())
    {
      localArrayList.add(new EAN13Reader());
      localArrayList.add(new EAN8Reader());
      localArrayList.add(new UPCEReader());
    }
    this.readers = ((UPCEANReader[])localArrayList.toArray(new UPCEANReader[localArrayList.size()]));
  }

  // ERROR //
  public com.google.zxing.Result decodeRow(int paramInt, com.google.zxing.common.BitArray paramBitArray, Map<DecodeHintType, ?> paramMap)
  {
    // Byte code:
    //   0: aload_2
    //   1: invokestatic 87	com/google/zxing/oned/UPCEANReader:findStartGuardPattern	(Lcom/google/zxing/common/BitArray;)[I
    //   4: astore 4
    //   6: aload_0
    //   7: getfield 79	com/google/zxing/oned/MultiFormatUPCEANReader:readers	[Lcom/google/zxing/oned/UPCEANReader;
    //   10: astore 5
    //   12: aload 5
    //   14: arraylength
    //   15: istore 6
    //   17: iconst_0
    //   18: istore 7
    //   20: iload 7
    //   22: iload 6
    //   24: if_icmpge +171 -> 195
    //   27: aload 5
    //   29: iload 7
    //   31: aaload
    //   32: astore 8
    //   34: aload 8
    //   36: iload_1
    //   37: aload_2
    //   38: aload 4
    //   40: aload_3
    //   41: invokevirtual 90	com/google/zxing/oned/UPCEANReader:decodeRow	(ILcom/google/zxing/common/BitArray;[ILjava/util/Map;)Lcom/google/zxing/Result;
    //   44: astore 9
    //   46: aload 9
    //   48: invokevirtual 96	com/google/zxing/Result:getBarcodeFormat	()Lcom/google/zxing/BarcodeFormat;
    //   51: getstatic 34	com/google/zxing/BarcodeFormat:EAN_13	Lcom/google/zxing/BarcodeFormat;
    //   54: if_acmpne +23 -> 77
    //   57: aload 9
    //   59: invokevirtual 100	com/google/zxing/Result:getText	()Ljava/lang/String;
    //   62: iconst_0
    //   63: invokevirtual 106	java/lang/String:charAt	(I)C
    //   66: bipush 48
    //   68: if_icmpne +9 -> 77
    //   71: iconst_1
    //   72: istore 10
    //   74: goto +6 -> 80
    //   77: iconst_0
    //   78: istore 10
    //   80: aload_3
    //   81: ifnonnull +9 -> 90
    //   84: aconst_null
    //   85: astore 11
    //   87: goto +17 -> 104
    //   90: aload_3
    //   91: getstatic 17	com/google/zxing/DecodeHintType:POSSIBLE_FORMATS	Lcom/google/zxing/DecodeHintType;
    //   94: invokeinterface 23 2 0
    //   99: checkcast 25	java/util/Collection
    //   102: astore 11
    //   104: aload 11
    //   106: ifnull +23 -> 129
    //   109: aload 11
    //   111: getstatic 47	com/google/zxing/BarcodeFormat:UPC_A	Lcom/google/zxing/BarcodeFormat;
    //   114: invokeinterface 38 2 0
    //   119: istore 14
    //   121: iconst_0
    //   122: istore 12
    //   124: iload 14
    //   126: ifeq +6 -> 132
    //   129: iconst_1
    //   130: istore 12
    //   132: iload 10
    //   134: ifeq +52 -> 186
    //   137: iload 12
    //   139: ifeq +47 -> 186
    //   142: new 92	com/google/zxing/Result
    //   145: dup
    //   146: aload 9
    //   148: invokevirtual 100	com/google/zxing/Result:getText	()Ljava/lang/String;
    //   151: iconst_1
    //   152: invokevirtual 110	java/lang/String:substring	(I)Ljava/lang/String;
    //   155: aload 9
    //   157: invokevirtual 114	com/google/zxing/Result:getRawBytes	()[B
    //   160: aload 9
    //   162: invokevirtual 118	com/google/zxing/Result:getResultPoints	()[Lcom/google/zxing/ResultPoint;
    //   165: getstatic 47	com/google/zxing/BarcodeFormat:UPC_A	Lcom/google/zxing/BarcodeFormat;
    //   168: invokespecial 121	com/google/zxing/Result:<init>	(Ljava/lang/String;[B[Lcom/google/zxing/ResultPoint;Lcom/google/zxing/BarcodeFormat;)V
    //   171: astore 13
    //   173: aload 13
    //   175: aload 9
    //   177: invokevirtual 125	com/google/zxing/Result:getResultMetadata	()Ljava/util/Map;
    //   180: invokevirtual 128	com/google/zxing/Result:putAllMetadata	(Ljava/util/Map;)V
    //   183: aload 13
    //   185: areturn
    //   186: aload 9
    //   188: areturn
    //   189: iinc 7 1
    //   192: goto -172 -> 20
    //   195: invokestatic 134	com/google/zxing/NotFoundException:getNotFoundInstance	()Lcom/google/zxing/NotFoundException;
    //   198: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   34	46	189	com/google/zxing/ReaderException
  }

  public void reset()
  {
    UPCEANReader[] arrayOfUPCEANReader = this.readers;
    int i = arrayOfUPCEANReader.length;
    for (int j = 0; j < i; j++)
      arrayOfUPCEANReader[j].reset();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.MultiFormatUPCEANReader
 * JD-Core Version:    0.6.1
 */