package com.google.zxing;

import com.google.zxing.aztec.AztecReader;
import com.google.zxing.datamatrix.DataMatrixReader;
import com.google.zxing.maxicode.MaxiCodeReader;
import com.google.zxing.oned.MultiFormatOneDReader;
import com.google.zxing.pdf417.PDF417Reader;
import com.google.zxing.qrcode.QRCodeReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class MultiFormatReader
  implements Reader
{
  private Map<DecodeHintType, ?> hints;
  private Reader[] readers;

  // ERROR //
  private Result decodeInternal(BinaryBitmap paramBinaryBitmap)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 21	com/google/zxing/MultiFormatReader:readers	[Lcom/google/zxing/Reader;
    //   4: ifnull +49 -> 53
    //   7: aload_0
    //   8: getfield 21	com/google/zxing/MultiFormatReader:readers	[Lcom/google/zxing/Reader;
    //   11: astore_2
    //   12: aload_2
    //   13: arraylength
    //   14: istore_3
    //   15: iconst_0
    //   16: istore 4
    //   18: iload 4
    //   20: iload_3
    //   21: if_icmpge +32 -> 53
    //   24: aload_2
    //   25: iload 4
    //   27: aaload
    //   28: astore 5
    //   30: aload 5
    //   32: aload_1
    //   33: aload_0
    //   34: getfield 23	com/google/zxing/MultiFormatReader:hints	Ljava/util/Map;
    //   37: invokeinterface 27 3 0
    //   42: astore 6
    //   44: aload 6
    //   46: areturn
    //   47: iinc 4 1
    //   50: goto -32 -> 18
    //   53: invokestatic 33	com/google/zxing/NotFoundException:getNotFoundInstance	()Lcom/google/zxing/NotFoundException;
    //   56: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   30	44	47	com/google/zxing/ReaderException
  }

  public Result decode(BinaryBitmap paramBinaryBitmap)
  {
    setHints(null);
    return decodeInternal(paramBinaryBitmap);
  }

  public Result decode(BinaryBitmap paramBinaryBitmap, Map<DecodeHintType, ?> paramMap)
  {
    setHints(paramMap);
    return decodeInternal(paramBinaryBitmap);
  }

  public Result decodeWithState(BinaryBitmap paramBinaryBitmap)
  {
    if (this.readers == null)
      setHints(null);
    return decodeInternal(paramBinaryBitmap);
  }

  public void reset()
  {
    if (this.readers != null)
    {
      Reader[] arrayOfReader = this.readers;
      int i = arrayOfReader.length;
      for (int j = 0; j < i; j++)
        arrayOfReader[j].reset();
    }
  }

  public void setHints(Map<DecodeHintType, ?> paramMap)
  {
    this.hints = paramMap;
    int i;
    if ((paramMap != null) && (paramMap.containsKey(DecodeHintType.TRY_HARDER)))
      i = 1;
    else
      i = 0;
    Collection localCollection;
    if (paramMap == null)
      localCollection = null;
    else
      localCollection = (Collection)paramMap.get(DecodeHintType.POSSIBLE_FORMATS);
    ArrayList localArrayList = new ArrayList();
    if (localCollection != null)
    {
      int j;
      if ((!localCollection.contains(BarcodeFormat.UPC_A)) && (!localCollection.contains(BarcodeFormat.UPC_E)) && (!localCollection.contains(BarcodeFormat.EAN_13)) && (!localCollection.contains(BarcodeFormat.EAN_8)) && (!localCollection.contains(BarcodeFormat.CODABAR)) && (!localCollection.contains(BarcodeFormat.CODE_39)) && (!localCollection.contains(BarcodeFormat.CODE_93)) && (!localCollection.contains(BarcodeFormat.CODE_128)) && (!localCollection.contains(BarcodeFormat.ITF)) && (!localCollection.contains(BarcodeFormat.RSS_14)))
      {
        boolean bool = localCollection.contains(BarcodeFormat.RSS_EXPANDED);
        j = 0;
        if (!bool);
      }
      else
      {
        j = 1;
      }
      if ((j != 0) && (i == 0))
        localArrayList.add(new MultiFormatOneDReader(paramMap));
      if (localCollection.contains(BarcodeFormat.QR_CODE))
        localArrayList.add(new QRCodeReader());
      if (localCollection.contains(BarcodeFormat.DATA_MATRIX))
        localArrayList.add(new DataMatrixReader());
      if (localCollection.contains(BarcodeFormat.AZTEC))
        localArrayList.add(new AztecReader());
      if (localCollection.contains(BarcodeFormat.PDF_417))
        localArrayList.add(new PDF417Reader());
      if (localCollection.contains(BarcodeFormat.MAXICODE))
        localArrayList.add(new MaxiCodeReader());
      if ((j != 0) && (i != 0))
        localArrayList.add(new MultiFormatOneDReader(paramMap));
    }
    if (localArrayList.isEmpty())
    {
      if (i == 0)
        localArrayList.add(new MultiFormatOneDReader(paramMap));
      localArrayList.add(new QRCodeReader());
      localArrayList.add(new DataMatrixReader());
      localArrayList.add(new AztecReader());
      localArrayList.add(new PDF417Reader());
      localArrayList.add(new MaxiCodeReader());
      if (i != 0)
        localArrayList.add(new MultiFormatOneDReader(paramMap));
    }
    this.readers = ((Reader[])localArrayList.toArray(new Reader[localArrayList.size()]));
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.MultiFormatReader
 * JD-Core Version:    0.6.1
 */