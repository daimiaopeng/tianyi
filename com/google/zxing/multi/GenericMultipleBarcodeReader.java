package com.google.zxing.multi;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class GenericMultipleBarcodeReader
  implements MultipleBarcodeReader
{
  private static final int MAX_DEPTH = 4;
  private static final int MIN_DIMENSION_TO_RECUR = 100;
  private final Reader delegate;

  public GenericMultipleBarcodeReader(Reader paramReader)
  {
    this.delegate = paramReader;
  }

  // ERROR //
  private void doDecodeMultiple(BinaryBitmap paramBinaryBitmap, Map<DecodeHintType, ?> paramMap, List<Result> paramList, int paramInt1, int paramInt2, int paramInt3)
  {
    // Byte code:
    //   0: iload 6
    //   2: iconst_4
    //   3: if_icmple +4 -> 7
    //   6: return
    //   7: aload_0
    //   8: getfield 20	com/google/zxing/multi/GenericMultipleBarcodeReader:delegate	Lcom/google/zxing/Reader;
    //   11: aload_1
    //   12: aload_2
    //   13: invokeinterface 30 3 0
    //   18: astore 7
    //   20: aload_3
    //   21: invokeinterface 36 1 0
    //   26: astore 8
    //   28: aload 8
    //   30: invokeinterface 42 1 0
    //   35: ifeq +33 -> 68
    //   38: aload 8
    //   40: invokeinterface 46 1 0
    //   45: checkcast 48	com/google/zxing/Result
    //   48: invokevirtual 52	com/google/zxing/Result:getText	()Ljava/lang/String;
    //   51: aload 7
    //   53: invokevirtual 52	com/google/zxing/Result:getText	()Ljava/lang/String;
    //   56: invokevirtual 58	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   59: ifeq -31 -> 28
    //   62: iconst_1
    //   63: istore 9
    //   65: goto +6 -> 71
    //   68: iconst_0
    //   69: istore 9
    //   71: iload 9
    //   73: ifne +30 -> 103
    //   76: aload 7
    //   78: iload 4
    //   80: iload 5
    //   82: invokestatic 62	com/google/zxing/multi/GenericMultipleBarcodeReader:translateResultPoints	(Lcom/google/zxing/Result;II)Lcom/google/zxing/Result;
    //   85: astore 46
    //   87: aload_3
    //   88: astore 10
    //   90: aload 10
    //   92: aload 46
    //   94: invokeinterface 65 2 0
    //   99: pop
    //   100: goto +6 -> 106
    //   103: aload_3
    //   104: astore 10
    //   106: aload 7
    //   108: invokevirtual 69	com/google/zxing/Result:getResultPoints	()[Lcom/google/zxing/ResultPoint;
    //   111: astore 11
    //   113: aload 11
    //   115: ifnull +415 -> 530
    //   118: aload 11
    //   120: arraylength
    //   121: ifne +6 -> 127
    //   124: goto +406 -> 530
    //   127: aload_1
    //   128: invokevirtual 75	com/google/zxing/BinaryBitmap:getWidth	()I
    //   131: istore 12
    //   133: aload_1
    //   134: invokevirtual 78	com/google/zxing/BinaryBitmap:getHeight	()I
    //   137: istore 13
    //   139: iload 12
    //   141: i2f
    //   142: fstore 14
    //   144: iload 13
    //   146: i2f
    //   147: fstore 15
    //   149: aload 11
    //   151: arraylength
    //   152: istore 16
    //   154: fload 15
    //   156: fstore 17
    //   158: fconst_0
    //   159: fstore 18
    //   161: fconst_0
    //   162: fstore 19
    //   164: fload 14
    //   166: fstore 20
    //   168: iconst_0
    //   169: istore 21
    //   171: iload 21
    //   173: iload 16
    //   175: if_icmpge +86 -> 261
    //   178: iload 16
    //   180: istore 42
    //   182: aload 11
    //   184: iload 21
    //   186: aaload
    //   187: astore 43
    //   189: aload 43
    //   191: invokevirtual 84	com/google/zxing/ResultPoint:getX	()F
    //   194: fstore 44
    //   196: aload 43
    //   198: invokevirtual 87	com/google/zxing/ResultPoint:getY	()F
    //   201: fstore 45
    //   203: fload 44
    //   205: fload 20
    //   207: fcmpg
    //   208: ifge +7 -> 215
    //   211: fload 44
    //   213: fstore 20
    //   215: fload 45
    //   217: fload 17
    //   219: fcmpg
    //   220: ifge +7 -> 227
    //   223: fload 45
    //   225: fstore 17
    //   227: fload 44
    //   229: fload 18
    //   231: fcmpl
    //   232: ifle +7 -> 239
    //   235: fload 44
    //   237: fstore 18
    //   239: fload 45
    //   241: fload 19
    //   243: fcmpl
    //   244: ifle +7 -> 251
    //   247: fload 45
    //   249: fstore 19
    //   251: iinc 21 1
    //   254: iload 42
    //   256: istore 16
    //   258: goto -87 -> 171
    //   261: fload 20
    //   263: ldc 88
    //   265: fcmpl
    //   266: ifle +64 -> 330
    //   269: aload_1
    //   270: iconst_0
    //   271: iconst_0
    //   272: fload 20
    //   274: f2i
    //   275: iload 13
    //   277: invokevirtual 92	com/google/zxing/BinaryBitmap:crop	(IIII)Lcom/google/zxing/BinaryBitmap;
    //   280: astore 39
    //   282: iload 6
    //   284: iconst_1
    //   285: iadd
    //   286: istore 40
    //   288: aload 10
    //   290: astore 41
    //   292: fload 19
    //   294: fstore 24
    //   296: fload 17
    //   298: fstore 25
    //   300: iload 13
    //   302: istore 22
    //   304: fload 18
    //   306: fstore 23
    //   308: iload 12
    //   310: istore 26
    //   312: aload_0
    //   313: aload 39
    //   315: aload_2
    //   316: aload 41
    //   318: iload 4
    //   320: iload 5
    //   322: iload 40
    //   324: invokespecial 94	com/google/zxing/multi/GenericMultipleBarcodeReader:doDecodeMultiple	(Lcom/google/zxing/BinaryBitmap;Ljava/util/Map;Ljava/util/List;III)V
    //   327: goto +23 -> 350
    //   330: iload 13
    //   332: istore 22
    //   334: fload 18
    //   336: fstore 23
    //   338: fload 19
    //   340: fstore 24
    //   342: fload 17
    //   344: fstore 25
    //   346: iload 12
    //   348: istore 26
    //   350: fload 25
    //   352: ldc 88
    //   354: fcmpl
    //   355: ifle +37 -> 392
    //   358: aload_1
    //   359: iconst_0
    //   360: iconst_0
    //   361: iload 26
    //   363: fload 25
    //   365: f2i
    //   366: invokevirtual 92	com/google/zxing/BinaryBitmap:crop	(IIII)Lcom/google/zxing/BinaryBitmap;
    //   369: astore 37
    //   371: iload 6
    //   373: iconst_1
    //   374: iadd
    //   375: istore 38
    //   377: aload_0
    //   378: aload 37
    //   380: aload_2
    //   381: aload 10
    //   383: iload 4
    //   385: iload 5
    //   387: iload 38
    //   389: invokespecial 94	com/google/zxing/multi/GenericMultipleBarcodeReader:doDecodeMultiple	(Lcom/google/zxing/BinaryBitmap;Ljava/util/Map;Ljava/util/List;III)V
    //   392: fload 23
    //   394: iload 26
    //   396: bipush 100
    //   398: isub
    //   399: i2f
    //   400: fcmpg
    //   401: ifge +63 -> 464
    //   404: fload 23
    //   406: f2i
    //   407: istore 32
    //   409: iload 26
    //   411: iload 32
    //   413: isub
    //   414: istore 33
    //   416: iload 22
    //   418: istore 27
    //   420: aload_1
    //   421: iload 32
    //   423: iconst_0
    //   424: iload 33
    //   426: iload 27
    //   428: invokevirtual 92	com/google/zxing/BinaryBitmap:crop	(IIII)Lcom/google/zxing/BinaryBitmap;
    //   431: astore 34
    //   433: iload 4
    //   435: iload 32
    //   437: iadd
    //   438: istore 35
    //   440: iload 6
    //   442: iconst_1
    //   443: iadd
    //   444: istore 36
    //   446: aload_0
    //   447: aload 34
    //   449: aload_2
    //   450: aload 10
    //   452: iload 35
    //   454: iload 5
    //   456: iload 36
    //   458: invokespecial 94	com/google/zxing/multi/GenericMultipleBarcodeReader:doDecodeMultiple	(Lcom/google/zxing/BinaryBitmap;Ljava/util/Map;Ljava/util/List;III)V
    //   461: goto +7 -> 468
    //   464: iload 22
    //   466: istore 27
    //   468: fload 24
    //   470: iload 27
    //   472: bipush 100
    //   474: isub
    //   475: i2f
    //   476: fcmpg
    //   477: ifge +52 -> 529
    //   480: fload 24
    //   482: f2i
    //   483: istore 28
    //   485: aload_1
    //   486: iconst_0
    //   487: iload 28
    //   489: iload 26
    //   491: iload 27
    //   493: iload 28
    //   495: isub
    //   496: invokevirtual 92	com/google/zxing/BinaryBitmap:crop	(IIII)Lcom/google/zxing/BinaryBitmap;
    //   499: astore 29
    //   501: iload 5
    //   503: iload 28
    //   505: iadd
    //   506: istore 30
    //   508: iload 6
    //   510: iconst_1
    //   511: iadd
    //   512: istore 31
    //   514: aload_0
    //   515: aload 29
    //   517: aload_2
    //   518: aload 10
    //   520: iload 4
    //   522: iload 30
    //   524: iload 31
    //   526: invokespecial 94	com/google/zxing/multi/GenericMultipleBarcodeReader:doDecodeMultiple	(Lcom/google/zxing/BinaryBitmap;Ljava/util/Map;Ljava/util/List;III)V
    //   529: return
    //   530: return
    //   531: return
    //
    // Exception table:
    //   from	to	target	type
    //   7	20	531	com/google/zxing/ReaderException
  }

  private static Result translateResultPoints(Result paramResult, int paramInt1, int paramInt2)
  {
    ResultPoint[] arrayOfResultPoint1 = paramResult.getResultPoints();
    if (arrayOfResultPoint1 == null)
      return paramResult;
    ResultPoint[] arrayOfResultPoint2 = new ResultPoint[arrayOfResultPoint1.length];
    for (int i = 0; i < arrayOfResultPoint1.length; i++)
    {
      ResultPoint localResultPoint = arrayOfResultPoint1[i];
      arrayOfResultPoint2[i] = new ResultPoint(localResultPoint.getX() + paramInt1, localResultPoint.getY() + paramInt2);
    }
    return new Result(paramResult.getText(), paramResult.getRawBytes(), arrayOfResultPoint2, paramResult.getBarcodeFormat());
  }

  public Result[] decodeMultiple(BinaryBitmap paramBinaryBitmap)
  {
    return decodeMultiple(paramBinaryBitmap, null);
  }

  public Result[] decodeMultiple(BinaryBitmap paramBinaryBitmap, Map<DecodeHintType, ?> paramMap)
  {
    ArrayList localArrayList = new ArrayList();
    doDecodeMultiple(paramBinaryBitmap, paramMap, localArrayList, 0, 0, 0);
    if (localArrayList.isEmpty())
      throw NotFoundException.getNotFoundInstance();
    return (Result[])localArrayList.toArray(new Result[localArrayList.size()]);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.multi.GenericMultipleBarcodeReader
 * JD-Core Version:    0.6.1
 */