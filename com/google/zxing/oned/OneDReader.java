package com.google.zxing.oned;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

public abstract class OneDReader
  implements Reader
{
  protected static final int INTEGER_MATH_SHIFT = 8;
  protected static final int PATTERN_MATCH_RESULT_SCALE_FACTOR = 256;

  // ERROR //
  private Result doDecode(BinaryBitmap paramBinaryBitmap, Map<DecodeHintType, ?> paramMap)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 27	com/google/zxing/BinaryBitmap:getWidth	()I
    //   4: istore_3
    //   5: aload_1
    //   6: invokevirtual 30	com/google/zxing/BinaryBitmap:getHeight	()I
    //   9: istore 4
    //   11: new 32	com/google/zxing/common/BitArray
    //   14: dup
    //   15: iload_3
    //   16: invokespecial 35	com/google/zxing/common/BitArray:<init>	(I)V
    //   19: astore 5
    //   21: iload 4
    //   23: iconst_1
    //   24: ishr
    //   25: istore 6
    //   27: iconst_1
    //   28: istore 7
    //   30: aload_2
    //   31: ifnull +21 -> 52
    //   34: aload_2
    //   35: getstatic 41	com/google/zxing/DecodeHintType:TRY_HARDER	Lcom/google/zxing/DecodeHintType;
    //   38: invokeinterface 47 2 0
    //   43: ifeq +9 -> 52
    //   46: iconst_1
    //   47: istore 8
    //   49: goto +6 -> 55
    //   52: iconst_0
    //   53: istore 8
    //   55: iload 8
    //   57: ifeq +10 -> 67
    //   60: bipush 8
    //   62: istore 9
    //   64: goto +6 -> 70
    //   67: iconst_5
    //   68: istore 9
    //   70: iload 7
    //   72: iload 4
    //   74: iload 9
    //   76: ishr
    //   77: invokestatic 53	java/lang/Math:max	(II)I
    //   80: istore 10
    //   82: iload 8
    //   84: ifeq +10 -> 94
    //   87: iload 4
    //   89: istore 11
    //   91: goto +7 -> 98
    //   94: bipush 15
    //   96: istore 11
    //   98: aload_2
    //   99: astore 12
    //   101: iconst_0
    //   102: istore 13
    //   104: iload 13
    //   106: iload 11
    //   108: if_icmpge +353 -> 461
    //   111: iload 13
    //   113: iconst_1
    //   114: iadd
    //   115: istore 14
    //   117: iload 14
    //   119: iconst_1
    //   120: ishr
    //   121: istore 15
    //   123: iload 13
    //   125: iconst_1
    //   126: iand
    //   127: ifne +9 -> 136
    //   130: iconst_1
    //   131: istore 16
    //   133: goto +6 -> 139
    //   136: iconst_0
    //   137: istore 16
    //   139: iload 16
    //   141: ifeq +6 -> 147
    //   144: goto +8 -> 152
    //   147: iload 15
    //   149: ineg
    //   150: istore 15
    //   152: iload 6
    //   154: iload 15
    //   156: iload 10
    //   158: imul
    //   159: iadd
    //   160: istore 17
    //   162: iload 17
    //   164: iflt +297 -> 461
    //   167: iload 17
    //   169: iload 4
    //   171: if_icmplt +6 -> 177
    //   174: goto +287 -> 461
    //   177: aload_1
    //   178: iload 17
    //   180: aload 5
    //   182: invokevirtual 57	com/google/zxing/BinaryBitmap:getBlackRow	(ILcom/google/zxing/common/BitArray;)Lcom/google/zxing/common/BitArray;
    //   185: astore 20
    //   187: iconst_0
    //   188: istore 21
    //   190: iload 21
    //   192: iconst_2
    //   193: if_icmpge +230 -> 423
    //   196: iload 21
    //   198: iload 7
    //   200: if_icmpne +61 -> 261
    //   203: aload 20
    //   205: invokevirtual 60	com/google/zxing/common/BitArray:reverse	()V
    //   208: aload 12
    //   210: ifnull +51 -> 261
    //   213: aload 12
    //   215: getstatic 63	com/google/zxing/DecodeHintType:NEED_RESULT_POINT_CALLBACK	Lcom/google/zxing/DecodeHintType;
    //   218: invokeinterface 47 2 0
    //   223: ifeq +38 -> 261
    //   226: new 65	java/util/EnumMap
    //   229: dup
    //   230: ldc 37
    //   232: invokespecial 68	java/util/EnumMap:<init>	(Ljava/lang/Class;)V
    //   235: astore 32
    //   237: aload 32
    //   239: aload 12
    //   241: invokeinterface 72 2 0
    //   246: aload 32
    //   248: getstatic 63	com/google/zxing/DecodeHintType:NEED_RESULT_POINT_CALLBACK	Lcom/google/zxing/DecodeHintType;
    //   251: invokeinterface 76 2 0
    //   256: pop
    //   257: aload 32
    //   259: astore 12
    //   261: aload_0
    //   262: iload 17
    //   264: aload 20
    //   266: aload 12
    //   268: invokevirtual 80	com/google/zxing/oned/OneDReader:decodeRow	(ILcom/google/zxing/common/BitArray;Ljava/util/Map;)Lcom/google/zxing/Result;
    //   271: astore 24
    //   273: iload 21
    //   275: iload 7
    //   277: if_icmpne +108 -> 385
    //   280: aload 24
    //   282: getstatic 86	com/google/zxing/ResultMetadataType:ORIENTATION	Lcom/google/zxing/ResultMetadataType;
    //   285: sipush 180
    //   288: invokestatic 92	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   291: invokevirtual 98	com/google/zxing/Result:putMetadata	(Lcom/google/zxing/ResultMetadataType;Ljava/lang/Object;)V
    //   294: aload 24
    //   296: invokevirtual 102	com/google/zxing/Result:getResultPoints	()[Lcom/google/zxing/ResultPoint;
    //   299: astore 25
    //   301: aload 25
    //   303: ifnull +82 -> 385
    //   306: iload_3
    //   307: i2f
    //   308: fstore 26
    //   310: iload_3
    //   311: istore 22
    //   313: aload 25
    //   315: iconst_0
    //   316: aaload
    //   317: invokevirtual 108	com/google/zxing/ResultPoint:getX	()F
    //   320: fstore 28
    //   322: fload 26
    //   324: fload 28
    //   326: fsub
    //   327: fconst_1
    //   328: fsub
    //   329: fstore 29
    //   331: iload 4
    //   333: istore 23
    //   335: aload 25
    //   337: iconst_0
    //   338: new 104	com/google/zxing/ResultPoint
    //   341: dup
    //   342: fload 29
    //   344: aload 25
    //   346: iconst_0
    //   347: aaload
    //   348: invokevirtual 111	com/google/zxing/ResultPoint:getY	()F
    //   351: invokespecial 114	com/google/zxing/ResultPoint:<init>	(FF)V
    //   354: aastore
    //   355: aload 25
    //   357: iconst_1
    //   358: new 104	com/google/zxing/ResultPoint
    //   361: dup
    //   362: fload 26
    //   364: aload 25
    //   366: iconst_1
    //   367: aaload
    //   368: invokevirtual 108	com/google/zxing/ResultPoint:getX	()F
    //   371: fsub
    //   372: fconst_1
    //   373: fsub
    //   374: aload 25
    //   376: iconst_1
    //   377: aaload
    //   378: invokevirtual 111	com/google/zxing/ResultPoint:getY	()F
    //   381: invokespecial 114	com/google/zxing/ResultPoint:<init>	(FF)V
    //   384: aastore
    //   385: aload 24
    //   387: areturn
    //   388: iload_3
    //   389: istore 22
    //   391: goto +4 -> 395
    //   394: pop
    //   395: iload 4
    //   397: istore 23
    //   399: goto +4 -> 403
    //   402: pop
    //   403: goto +4 -> 407
    //   406: pop
    //   407: iinc 21 1
    //   410: iload 22
    //   412: istore_3
    //   413: iload 23
    //   415: istore 4
    //   417: iconst_1
    //   418: istore 7
    //   420: goto -230 -> 190
    //   423: iload_3
    //   424: istore 18
    //   426: iload 4
    //   428: istore 19
    //   430: aload 20
    //   432: astore 5
    //   434: goto +10 -> 444
    //   437: iload_3
    //   438: istore 18
    //   440: iload 4
    //   442: istore 19
    //   444: iload 14
    //   446: istore 13
    //   448: iload 18
    //   450: istore_3
    //   451: iload 19
    //   453: istore 4
    //   455: iconst_1
    //   456: istore 7
    //   458: goto -354 -> 104
    //   461: invokestatic 118	com/google/zxing/NotFoundException:getNotFoundInstance	()Lcom/google/zxing/NotFoundException;
    //   464: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   261	306	388	com/google/zxing/ReaderException
    //   313	322	394	com/google/zxing/ReaderException
    //   335	355	402	com/google/zxing/ReaderException
    //   355	385	406	com/google/zxing/ReaderException
    //   177	187	437	com/google/zxing/NotFoundException
  }

  protected static int patternMatchVariance(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt)
  {
    int i = paramArrayOfInt1.length;
    int j = 0;
    int k = 0;
    int m = 0;
    int n = 0;
    while (k < i)
    {
      m += paramArrayOfInt1[k];
      n += paramArrayOfInt2[k];
      k++;
    }
    if (m < n)
      return 2147483647;
    int i1 = (m << 8) / n;
    int i2 = paramInt * i1 >> 8;
    int i3 = 0;
    while (j < i)
    {
      int i4 = paramArrayOfInt1[j] << 8;
      int i5 = i1 * paramArrayOfInt2[j];
      int i6;
      if (i4 > i5)
        i6 = i4 - i5;
      else
        i6 = i5 - i4;
      if (i6 > i2)
        return 2147483647;
      i3 += i6;
      j++;
    }
    return i3 / m;
  }

  protected static void recordPattern(BitArray paramBitArray, int paramInt, int[] paramArrayOfInt)
  {
    int i = paramArrayOfInt.length;
    Arrays.fill(paramArrayOfInt, 0, i, 0);
    int j = paramBitArray.getSize();
    if (paramInt >= j)
      throw NotFoundException.getNotFoundInstance();
    boolean bool = true ^ paramBitArray.get(paramInt);
    int k = 0;
    while (paramInt < j)
    {
      if ((bool ^ paramBitArray.get(paramInt)))
      {
        paramArrayOfInt[k] = (1 + paramArrayOfInt[k]);
      }
      else
      {
        k++;
        if (k == i)
          break;
        paramArrayOfInt[k] = 1;
        if (!bool)
          bool = true;
        else
          bool = false;
      }
      paramInt++;
    }
    if ((k != i) && ((k != i - 1) || (paramInt != j)))
      throw NotFoundException.getNotFoundInstance();
  }

  protected static void recordPatternInReverse(BitArray paramBitArray, int paramInt, int[] paramArrayOfInt)
  {
    int i = paramArrayOfInt.length;
    boolean bool = paramBitArray.get(paramInt);
    while ((paramInt > 0) && (i >= 0))
    {
      paramInt--;
      if (paramBitArray.get(paramInt) != bool)
      {
        i--;
        if (!bool)
          bool = true;
        else
          bool = false;
      }
    }
    if (i >= 0)
      throw NotFoundException.getNotFoundInstance();
    recordPattern(paramBitArray, paramInt + 1, paramArrayOfInt);
  }

  public Result decode(BinaryBitmap paramBinaryBitmap)
  {
    return decode(paramBinaryBitmap, null);
  }

  public Result decode(BinaryBitmap paramBinaryBitmap, Map<DecodeHintType, ?> paramMap)
  {
    try
    {
      Result localResult2 = doDecode(paramBinaryBitmap, paramMap);
      return localResult2;
    }
    catch (NotFoundException localNotFoundException)
    {
      int i = 0;
      int j;
      if ((paramMap != null) && (paramMap.containsKey(DecodeHintType.TRY_HARDER)))
        j = 1;
      else
        j = 0;
      if ((j != 0) && (paramBinaryBitmap.isRotateSupported()))
      {
        BinaryBitmap localBinaryBitmap = paramBinaryBitmap.rotateCounterClockwise();
        Result localResult1 = doDecode(localBinaryBitmap, paramMap);
        Map localMap = localResult1.getResultMetadata();
        int k = 270;
        if ((localMap != null) && (localMap.containsKey(ResultMetadataType.ORIENTATION)))
          k = (k + ((Integer)localMap.get(ResultMetadataType.ORIENTATION)).intValue()) % 360;
        localResult1.putMetadata(ResultMetadataType.ORIENTATION, Integer.valueOf(k));
        ResultPoint[] arrayOfResultPoint = localResult1.getResultPoints();
        if (arrayOfResultPoint != null)
        {
          int m = localBinaryBitmap.getHeight();
          while (i < arrayOfResultPoint.length)
          {
            arrayOfResultPoint[i] = new ResultPoint(m - arrayOfResultPoint[i].getY() - 1.0F, arrayOfResultPoint[i].getX());
            i++;
          }
        }
        return localResult1;
      }
      throw localNotFoundException;
    }
  }

  public abstract Result decodeRow(int paramInt, BitArray paramBitArray, Map<DecodeHintType, ?> paramMap);

  public void reset()
  {
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.OneDReader
 * JD-Core Version:    0.6.1
 */