package com.google.zxing.aztec.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.MathUtils;

public final class Detector
{
  private boolean compact;
  private final BitMatrix image;
  private int nbCenterLayers;
  private int nbDataBlocks;
  private int nbLayers;
  private int shift;

  public Detector(BitMatrix paramBitMatrix)
  {
    this.image = paramBitMatrix;
  }

  // ERROR //
  private static void correctParameterData(boolean[] paramArrayOfBoolean, boolean paramBoolean)
  {
    // Byte code:
    //   0: iload_1
    //   1: ifeq +11 -> 12
    //   4: bipush 7
    //   6: istore_2
    //   7: iconst_2
    //   8: istore_3
    //   9: goto +8 -> 17
    //   12: bipush 10
    //   14: istore_2
    //   15: iconst_4
    //   16: istore_3
    //   17: iload_2
    //   18: iload_3
    //   19: isub
    //   20: istore 4
    //   22: iload_2
    //   23: newarray int
    //   25: astore 5
    //   27: iconst_0
    //   28: istore 6
    //   30: iconst_1
    //   31: istore 7
    //   33: iload 6
    //   35: iload_2
    //   36: if_icmpge +57 -> 93
    //   39: iconst_1
    //   40: istore 13
    //   42: iload 7
    //   44: iconst_4
    //   45: if_icmpgt +42 -> 87
    //   48: aload_0
    //   49: iconst_4
    //   50: iconst_4
    //   51: iload 6
    //   53: imul
    //   54: iadd
    //   55: iload 7
    //   57: isub
    //   58: baload
    //   59: ifeq +16 -> 75
    //   62: aload 5
    //   64: iload 6
    //   66: iload 13
    //   68: aload 5
    //   70: iload 6
    //   72: iaload
    //   73: iadd
    //   74: iastore
    //   75: iload 13
    //   77: iconst_1
    //   78: ishl
    //   79: istore 13
    //   81: iinc 7 1
    //   84: goto -42 -> 42
    //   87: iinc 6 1
    //   90: goto -60 -> 30
    //   93: new 26	com/google/zxing/common/reedsolomon/ReedSolomonDecoder
    //   96: dup
    //   97: getstatic 32	com/google/zxing/common/reedsolomon/GenericGF:AZTEC_PARAM	Lcom/google/zxing/common/reedsolomon/GenericGF;
    //   100: invokespecial 35	com/google/zxing/common/reedsolomon/ReedSolomonDecoder:<init>	(Lcom/google/zxing/common/reedsolomon/GenericGF;)V
    //   103: aload 5
    //   105: iload 4
    //   107: invokevirtual 39	com/google/zxing/common/reedsolomon/ReedSolomonDecoder:decode	([II)V
    //   110: iconst_0
    //   111: istore 8
    //   113: iload 8
    //   115: iload_3
    //   116: if_icmpge +72 -> 188
    //   119: iconst_1
    //   120: istore 9
    //   122: iconst_1
    //   123: istore 10
    //   125: iload 9
    //   127: iconst_4
    //   128: if_icmpgt +54 -> 182
    //   131: iconst_4
    //   132: iload 8
    //   134: iconst_4
    //   135: imul
    //   136: iadd
    //   137: iload 9
    //   139: isub
    //   140: istore 11
    //   142: iload 10
    //   144: aload 5
    //   146: iload 8
    //   148: iaload
    //   149: iand
    //   150: iload 10
    //   152: if_icmpne +9 -> 161
    //   155: iconst_1
    //   156: istore 12
    //   158: goto +6 -> 164
    //   161: iconst_0
    //   162: istore 12
    //   164: aload_0
    //   165: iload 11
    //   167: iload 12
    //   169: bastore
    //   170: iload 10
    //   172: iconst_1
    //   173: ishl
    //   174: istore 10
    //   176: iinc 9 1
    //   179: goto -54 -> 125
    //   182: iinc 8 1
    //   185: goto -72 -> 113
    //   188: return
    //   189: invokestatic 45	com/google/zxing/NotFoundException:getNotFoundInstance	()Lcom/google/zxing/NotFoundException;
    //   192: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   93	110	189	com/google/zxing/common/reedsolomon/ReedSolomonException
  }

  private static float distance(Point paramPoint1, Point paramPoint2)
  {
    return MathUtils.distance(paramPoint1.x, paramPoint1.y, paramPoint2.x, paramPoint2.y);
  }

  private void extractParameters(Point[] paramArrayOfPoint)
  {
    int i = 2 * this.nbCenterLayers;
    int j = 0;
    Point localPoint1 = paramArrayOfPoint[0];
    Point localPoint2 = paramArrayOfPoint[1];
    int k = i + 1;
    boolean[] arrayOfBoolean1 = sampleLine(localPoint1, localPoint2, k);
    boolean[] arrayOfBoolean2 = sampleLine(paramArrayOfPoint[1], paramArrayOfPoint[2], k);
    boolean[] arrayOfBoolean3 = sampleLine(paramArrayOfPoint[2], paramArrayOfPoint[3], k);
    boolean[] arrayOfBoolean4 = sampleLine(paramArrayOfPoint[3], paramArrayOfPoint[0], k);
    if ((arrayOfBoolean1[0] != 0) && (arrayOfBoolean1[i] != 0))
    {
      this.shift = 0;
    }
    else if ((arrayOfBoolean2[0] != 0) && (arrayOfBoolean2[i] != 0))
    {
      this.shift = 1;
    }
    else if ((arrayOfBoolean3[0] != 0) && (arrayOfBoolean3[i] != 0))
    {
      this.shift = 2;
    }
    else
    {
      if ((arrayOfBoolean4[0] == 0) || (arrayOfBoolean4[i] == 0))
        break label487;
      this.shift = 3;
    }
    if (this.compact)
    {
      boolean[] arrayOfBoolean7 = new boolean[28];
      for (int i3 = 0; i3 < 7; i3++)
      {
        int i4 = i3 + 2;
        arrayOfBoolean7[i3] = arrayOfBoolean1[i4];
        arrayOfBoolean7[(i3 + 7)] = arrayOfBoolean2[i4];
        arrayOfBoolean7[(i3 + 14)] = arrayOfBoolean3[i4];
        arrayOfBoolean7[(i3 + 21)] = arrayOfBoolean4[i4];
      }
      arrayOfBoolean6 = new boolean[28];
      while (j < 28)
      {
        arrayOfBoolean6[j] = arrayOfBoolean7[((j + 7 * this.shift) % 28)];
        j++;
      }
    }
    boolean[] arrayOfBoolean5 = new boolean[40];
    for (int m = 0; m < 11; m++)
    {
      if (m < 5)
      {
        int i2 = m + 2;
        arrayOfBoolean5[m] = arrayOfBoolean1[i2];
        arrayOfBoolean5[(m + 10)] = arrayOfBoolean2[i2];
        arrayOfBoolean5[(m + 20)] = arrayOfBoolean3[i2];
        arrayOfBoolean5[(m + 30)] = arrayOfBoolean4[i2];
      }
      if (m > 5)
      {
        int n = m - 1;
        int i1 = m + 2;
        arrayOfBoolean5[n] = arrayOfBoolean1[i1];
        arrayOfBoolean5[(m + 9)] = arrayOfBoolean2[i1];
        arrayOfBoolean5[(m + 19)] = arrayOfBoolean3[i1];
        arrayOfBoolean5[(m + 29)] = arrayOfBoolean4[i1];
      }
    }
    boolean[] arrayOfBoolean6 = new boolean[40];
    while (j < 40)
    {
      arrayOfBoolean6[j] = arrayOfBoolean5[((j + 10 * this.shift) % 40)];
      j++;
    }
    correctParameterData(arrayOfBoolean6, this.compact);
    getParameters(arrayOfBoolean6);
    return;
    label487: throw NotFoundException.getNotFoundInstance();
  }

  private Point[] getBullEyeCornerPoints(Point paramPoint)
  {
    this.nbCenterLayers = 1;
    Object localObject1 = paramPoint;
    Object localObject2 = localObject1;
    Object localObject3 = localObject2;
    Object localObject4 = localObject3;
    boolean bool1 = true;
    while (this.nbCenterLayers < 9)
    {
      Point localPoint1 = getFirstDifferent((Point)localObject1, bool1, 1, -1);
      Point localPoint2 = getFirstDifferent(localObject2, bool1, 1, 1);
      Point localPoint3 = getFirstDifferent(localObject3, bool1, -1, 1);
      Point localPoint4 = getFirstDifferent(localObject4, bool1, -1, -1);
      if (this.nbCenterLayers > 2)
      {
        double d = distance(localPoint4, localPoint1) * this.nbCenterLayers / (distance(localObject4, (Point)localObject1) * 2 + this.nbCenterLayers);
        if ((d < 0.75D) || (d > 1.25D) || (!isWhiteOrBlackRectangle(localPoint1, localPoint2, localPoint3, localPoint4)))
          break;
      }
      bool1 ^= true;
      this.nbCenterLayers = (1 + this.nbCenterLayers);
      localObject4 = localPoint4;
      localObject1 = localPoint1;
      localObject2 = localPoint2;
      localObject3 = localPoint3;
    }
    if ((this.nbCenterLayers != 5) && (this.nbCenterLayers != 7))
      throw NotFoundException.getNotFoundInstance();
    boolean bool2;
    if (this.nbCenterLayers == 5)
      bool2 = true;
    else
      bool2 = false;
    this.compact = bool2;
    float f1 = 1.5F / 2 * this.nbCenterLayers - 3;
    int i = ((Point)localObject1).x - localObject3.x;
    int j = ((Point)localObject1).y - localObject3.y;
    float f2 = localObject3.x;
    float f3 = f1 * i;
    int k = MathUtils.round(f2 - f3);
    float f4 = localObject3.y;
    float f5 = f1 * j;
    int m = MathUtils.round(f4 - f5);
    int n = MathUtils.round(f3 + ((Point)localObject1).x);
    int i1 = MathUtils.round(f5 + ((Point)localObject1).y);
    int i2 = localObject2.x - localObject4.x;
    int i3 = localObject2.y - localObject4.y;
    float f6 = localObject4.x;
    float f7 = f1 * i2;
    int i4 = MathUtils.round(f6 - f7);
    float f8 = localObject4.y;
    float f9 = f1 * i3;
    int i5 = MathUtils.round(f8 - f9);
    int i6 = MathUtils.round(f7 + localObject2.x);
    int i7 = MathUtils.round(f9 + localObject2.y);
    if ((isValid(n, i1)) && (isValid(i6, i7)) && (isValid(k, m)) && (isValid(i4, i5)))
    {
      Point[] arrayOfPoint = new Point[4];
      arrayOfPoint[0] = new Point(n, i1);
      arrayOfPoint[1] = new Point(i6, i7);
      arrayOfPoint[2] = new Point(k, m);
      arrayOfPoint[3] = new Point(i4, i5);
      return arrayOfPoint;
    }
    throw NotFoundException.getNotFoundInstance();
  }

  private int getColor(Point paramPoint1, Point paramPoint2)
  {
    float f1 = distance(paramPoint1, paramPoint2);
    float f2 = paramPoint2.x - paramPoint1.x / f1;
    float f3 = paramPoint2.y - paramPoint1.y / f1;
    float f4 = paramPoint1.x;
    float f5 = paramPoint1.y;
    boolean bool1 = this.image.get(paramPoint1.x, paramPoint1.y);
    float f6 = f5;
    int i = 0;
    float f7 = f4;
    for (int j = 0; j < f1; j++)
    {
      f7 += f2;
      f6 += f3;
      if (this.image.get(MathUtils.round(f7), MathUtils.round(f6)) != bool1)
        i++;
    }
    float f8 = i / f1;
    if ((f8 > 0.1F) && (f8 < 0.9F))
      return 0;
    boolean bool2 = f8 < 0.1F;
    int k = 1;
    boolean bool3 = false;
    if (!bool2)
      bool3 = true;
    if (bool3 != bool1)
      k = -1;
    return k;
  }

  private Point getFirstDifferent(Point paramPoint, boolean paramBoolean, int paramInt1, int paramInt2)
  {
    int i = paramInt1 + paramPoint.x;
    int j = paramInt2 + paramPoint.y;
    while ((isValid(i, j)) && (this.image.get(i, j) == paramBoolean))
    {
      i += paramInt1;
      j += paramInt2;
    }
    int k = i - paramInt1;
    int m = j - paramInt2;
    while ((isValid(k, m)) && (this.image.get(k, m) == paramBoolean))
      k += paramInt1;
    int n = k - paramInt1;
    while ((isValid(n, m)) && (this.image.get(n, m) == paramBoolean))
      m += paramInt2;
    return new Point(n, m - paramInt2);
  }

  // ERROR //
  private Point getMatrixCenter()
  {
    // Byte code:
    //   0: new 119	com/google/zxing/common/detector/WhiteRectangleDetector
    //   3: dup
    //   4: aload_0
    //   5: getfield 20	com/google/zxing/aztec/detector/Detector:image	Lcom/google/zxing/common/BitMatrix;
    //   8: invokespecial 121	com/google/zxing/common/detector/WhiteRectangleDetector:<init>	(Lcom/google/zxing/common/BitMatrix;)V
    //   11: invokevirtual 125	com/google/zxing/common/detector/WhiteRectangleDetector:detect	()[Lcom/google/zxing/ResultPoint;
    //   14: astore 25
    //   16: aload 25
    //   18: iconst_0
    //   19: aaload
    //   20: astore 12
    //   22: aload 25
    //   24: iconst_1
    //   25: aaload
    //   26: astore 13
    //   28: aload 25
    //   30: iconst_2
    //   31: aaload
    //   32: astore 11
    //   34: aload 25
    //   36: iconst_3
    //   37: aaload
    //   38: astore 10
    //   40: goto +148 -> 188
    //   43: aload_0
    //   44: getfield 20	com/google/zxing/aztec/detector/Detector:image	Lcom/google/zxing/common/BitMatrix;
    //   47: invokevirtual 129	com/google/zxing/common/BitMatrix:getWidth	()I
    //   50: iconst_2
    //   51: idiv
    //   52: istore_1
    //   53: aload_0
    //   54: getfield 20	com/google/zxing/aztec/detector/Detector:image	Lcom/google/zxing/common/BitMatrix;
    //   57: invokevirtual 132	com/google/zxing/common/BitMatrix:getHeight	()I
    //   60: iconst_2
    //   61: idiv
    //   62: istore_2
    //   63: iload_1
    //   64: bipush 7
    //   66: iadd
    //   67: istore_3
    //   68: iload_2
    //   69: bipush 7
    //   71: isub
    //   72: istore 4
    //   74: aload_0
    //   75: new 49	com/google/zxing/aztec/detector/Detector$Point
    //   78: dup
    //   79: iload_3
    //   80: iload 4
    //   82: invokespecial 106	com/google/zxing/aztec/detector/Detector$Point:<init>	(II)V
    //   85: iconst_0
    //   86: iconst_1
    //   87: iconst_m1
    //   88: invokespecial 84	com/google/zxing/aztec/detector/Detector:getFirstDifferent	(Lcom/google/zxing/aztec/detector/Detector$Point;ZII)Lcom/google/zxing/aztec/detector/Detector$Point;
    //   91: invokevirtual 136	com/google/zxing/aztec/detector/Detector$Point:toResultPoint	()Lcom/google/zxing/ResultPoint;
    //   94: astore 5
    //   96: iload_2
    //   97: bipush 7
    //   99: iadd
    //   100: istore 6
    //   102: aload_0
    //   103: new 49	com/google/zxing/aztec/detector/Detector$Point
    //   106: dup
    //   107: iload_3
    //   108: iload 6
    //   110: invokespecial 106	com/google/zxing/aztec/detector/Detector$Point:<init>	(II)V
    //   113: iconst_0
    //   114: iconst_1
    //   115: iconst_1
    //   116: invokespecial 84	com/google/zxing/aztec/detector/Detector:getFirstDifferent	(Lcom/google/zxing/aztec/detector/Detector$Point;ZII)Lcom/google/zxing/aztec/detector/Detector$Point;
    //   119: invokevirtual 136	com/google/zxing/aztec/detector/Detector$Point:toResultPoint	()Lcom/google/zxing/ResultPoint;
    //   122: astore 7
    //   124: iload_1
    //   125: bipush 7
    //   127: isub
    //   128: istore 8
    //   130: aload_0
    //   131: new 49	com/google/zxing/aztec/detector/Detector$Point
    //   134: dup
    //   135: iload 8
    //   137: iload 6
    //   139: invokespecial 106	com/google/zxing/aztec/detector/Detector$Point:<init>	(II)V
    //   142: iconst_0
    //   143: iconst_m1
    //   144: iconst_1
    //   145: invokespecial 84	com/google/zxing/aztec/detector/Detector:getFirstDifferent	(Lcom/google/zxing/aztec/detector/Detector$Point;ZII)Lcom/google/zxing/aztec/detector/Detector$Point;
    //   148: invokevirtual 136	com/google/zxing/aztec/detector/Detector$Point:toResultPoint	()Lcom/google/zxing/ResultPoint;
    //   151: astore 9
    //   153: aload_0
    //   154: new 49	com/google/zxing/aztec/detector/Detector$Point
    //   157: dup
    //   158: iload 8
    //   160: iload 4
    //   162: invokespecial 106	com/google/zxing/aztec/detector/Detector$Point:<init>	(II)V
    //   165: iconst_0
    //   166: iconst_m1
    //   167: iconst_m1
    //   168: invokespecial 84	com/google/zxing/aztec/detector/Detector:getFirstDifferent	(Lcom/google/zxing/aztec/detector/Detector$Point;ZII)Lcom/google/zxing/aztec/detector/Detector$Point;
    //   171: invokevirtual 136	com/google/zxing/aztec/detector/Detector$Point:toResultPoint	()Lcom/google/zxing/ResultPoint;
    //   174: astore 10
    //   176: aload 9
    //   178: astore 11
    //   180: aload 5
    //   182: astore 12
    //   184: aload 7
    //   186: astore 13
    //   188: aload 12
    //   190: invokevirtual 142	com/google/zxing/ResultPoint:getX	()F
    //   193: aload 10
    //   195: invokevirtual 142	com/google/zxing/ResultPoint:getX	()F
    //   198: fadd
    //   199: aload 13
    //   201: invokevirtual 142	com/google/zxing/ResultPoint:getX	()F
    //   204: fadd
    //   205: aload 11
    //   207: invokevirtual 142	com/google/zxing/ResultPoint:getX	()F
    //   210: fadd
    //   211: ldc 143
    //   213: fdiv
    //   214: invokestatic 99	com/google/zxing/common/detector/MathUtils:round	(F)I
    //   217: istore 14
    //   219: aload 12
    //   221: invokevirtual 146	com/google/zxing/ResultPoint:getY	()F
    //   224: aload 10
    //   226: invokevirtual 146	com/google/zxing/ResultPoint:getY	()F
    //   229: fadd
    //   230: aload 13
    //   232: invokevirtual 146	com/google/zxing/ResultPoint:getY	()F
    //   235: fadd
    //   236: aload 11
    //   238: invokevirtual 146	com/google/zxing/ResultPoint:getY	()F
    //   241: fadd
    //   242: ldc 143
    //   244: fdiv
    //   245: invokestatic 99	com/google/zxing/common/detector/MathUtils:round	(F)I
    //   248: istore 15
    //   250: new 119	com/google/zxing/common/detector/WhiteRectangleDetector
    //   253: dup
    //   254: aload_0
    //   255: getfield 20	com/google/zxing/aztec/detector/Detector:image	Lcom/google/zxing/common/BitMatrix;
    //   258: bipush 15
    //   260: iload 14
    //   262: iload 15
    //   264: invokespecial 149	com/google/zxing/common/detector/WhiteRectangleDetector:<init>	(Lcom/google/zxing/common/BitMatrix;III)V
    //   267: invokevirtual 125	com/google/zxing/common/detector/WhiteRectangleDetector:detect	()[Lcom/google/zxing/ResultPoint;
    //   270: astore 24
    //   272: aload 24
    //   274: iconst_0
    //   275: aaload
    //   276: astore 18
    //   278: aload 24
    //   280: iconst_1
    //   281: aaload
    //   282: astore 20
    //   284: aload 24
    //   286: iconst_2
    //   287: aaload
    //   288: astore 22
    //   290: aload 24
    //   292: iconst_3
    //   293: aaload
    //   294: astore 23
    //   296: goto +123 -> 419
    //   299: iload 14
    //   301: bipush 7
    //   303: iadd
    //   304: istore 16
    //   306: iload 15
    //   308: bipush 7
    //   310: isub
    //   311: istore 17
    //   313: aload_0
    //   314: new 49	com/google/zxing/aztec/detector/Detector$Point
    //   317: dup
    //   318: iload 16
    //   320: iload 17
    //   322: invokespecial 106	com/google/zxing/aztec/detector/Detector$Point:<init>	(II)V
    //   325: iconst_0
    //   326: iconst_1
    //   327: iconst_m1
    //   328: invokespecial 84	com/google/zxing/aztec/detector/Detector:getFirstDifferent	(Lcom/google/zxing/aztec/detector/Detector$Point;ZII)Lcom/google/zxing/aztec/detector/Detector$Point;
    //   331: invokevirtual 136	com/google/zxing/aztec/detector/Detector$Point:toResultPoint	()Lcom/google/zxing/ResultPoint;
    //   334: astore 18
    //   336: iload 15
    //   338: bipush 7
    //   340: iadd
    //   341: istore 19
    //   343: aload_0
    //   344: new 49	com/google/zxing/aztec/detector/Detector$Point
    //   347: dup
    //   348: iload 16
    //   350: iload 19
    //   352: invokespecial 106	com/google/zxing/aztec/detector/Detector$Point:<init>	(II)V
    //   355: iconst_0
    //   356: iconst_1
    //   357: iconst_1
    //   358: invokespecial 84	com/google/zxing/aztec/detector/Detector:getFirstDifferent	(Lcom/google/zxing/aztec/detector/Detector$Point;ZII)Lcom/google/zxing/aztec/detector/Detector$Point;
    //   361: invokevirtual 136	com/google/zxing/aztec/detector/Detector$Point:toResultPoint	()Lcom/google/zxing/ResultPoint;
    //   364: astore 20
    //   366: iload 14
    //   368: bipush 7
    //   370: isub
    //   371: istore 21
    //   373: aload_0
    //   374: new 49	com/google/zxing/aztec/detector/Detector$Point
    //   377: dup
    //   378: iload 21
    //   380: iload 19
    //   382: invokespecial 106	com/google/zxing/aztec/detector/Detector$Point:<init>	(II)V
    //   385: iconst_0
    //   386: iconst_m1
    //   387: iconst_1
    //   388: invokespecial 84	com/google/zxing/aztec/detector/Detector:getFirstDifferent	(Lcom/google/zxing/aztec/detector/Detector$Point;ZII)Lcom/google/zxing/aztec/detector/Detector$Point;
    //   391: invokevirtual 136	com/google/zxing/aztec/detector/Detector$Point:toResultPoint	()Lcom/google/zxing/ResultPoint;
    //   394: astore 22
    //   396: aload_0
    //   397: new 49	com/google/zxing/aztec/detector/Detector$Point
    //   400: dup
    //   401: iload 21
    //   403: iload 17
    //   405: invokespecial 106	com/google/zxing/aztec/detector/Detector$Point:<init>	(II)V
    //   408: iconst_0
    //   409: iconst_m1
    //   410: iconst_m1
    //   411: invokespecial 84	com/google/zxing/aztec/detector/Detector:getFirstDifferent	(Lcom/google/zxing/aztec/detector/Detector$Point;ZII)Lcom/google/zxing/aztec/detector/Detector$Point;
    //   414: invokevirtual 136	com/google/zxing/aztec/detector/Detector$Point:toResultPoint	()Lcom/google/zxing/ResultPoint;
    //   417: astore 23
    //   419: new 49	com/google/zxing/aztec/detector/Detector$Point
    //   422: dup
    //   423: aload 18
    //   425: invokevirtual 142	com/google/zxing/ResultPoint:getX	()F
    //   428: aload 23
    //   430: invokevirtual 142	com/google/zxing/ResultPoint:getX	()F
    //   433: fadd
    //   434: aload 20
    //   436: invokevirtual 142	com/google/zxing/ResultPoint:getX	()F
    //   439: fadd
    //   440: aload 22
    //   442: invokevirtual 142	com/google/zxing/ResultPoint:getX	()F
    //   445: fadd
    //   446: ldc 143
    //   448: fdiv
    //   449: invokestatic 99	com/google/zxing/common/detector/MathUtils:round	(F)I
    //   452: aload 18
    //   454: invokevirtual 146	com/google/zxing/ResultPoint:getY	()F
    //   457: aload 23
    //   459: invokevirtual 146	com/google/zxing/ResultPoint:getY	()F
    //   462: fadd
    //   463: aload 20
    //   465: invokevirtual 146	com/google/zxing/ResultPoint:getY	()F
    //   468: fadd
    //   469: aload 22
    //   471: invokevirtual 146	com/google/zxing/ResultPoint:getY	()F
    //   474: fadd
    //   475: ldc 143
    //   477: fdiv
    //   478: invokestatic 99	com/google/zxing/common/detector/MathUtils:round	(F)I
    //   481: invokespecial 106	com/google/zxing/aztec/detector/Detector$Point:<init>	(II)V
    //   484: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	40	43	com/google/zxing/NotFoundException
    //   250	296	299	com/google/zxing/NotFoundException
  }

  private ResultPoint[] getMatrixCornerPoints(Point[] paramArrayOfPoint)
  {
    int i = 2 * this.nbLayers;
    int j;
    if (this.nbLayers > 4)
      j = 1;
    else
      j = 0;
    float f1 = i + j + (this.nbLayers - 4) / 8 / (2.0F * this.nbCenterLayers);
    int k = paramArrayOfPoint[0].x - paramArrayOfPoint[2].x;
    int m = -1;
    int n;
    if (k > 0)
      n = 1;
    else
      n = -1;
    int i1 = k + n;
    int i2 = paramArrayOfPoint[0].y - paramArrayOfPoint[2].y;
    int i3;
    if (i2 > 0)
      i3 = 1;
    else
      i3 = -1;
    int i4 = i2 + i3;
    float f2 = paramArrayOfPoint[2].x;
    float f3 = f1 * i1;
    int i5 = MathUtils.round(f2 - f3);
    float f4 = paramArrayOfPoint[2].y;
    float f5 = f1 * i4;
    int i6 = MathUtils.round(f4 - f5);
    int i7 = MathUtils.round(f3 + paramArrayOfPoint[0].x);
    int i8 = MathUtils.round(f5 + paramArrayOfPoint[0].y);
    int i9 = paramArrayOfPoint[1].x - paramArrayOfPoint[3].x;
    int i10;
    if (i9 > 0)
      i10 = 1;
    else
      i10 = -1;
    int i11 = i9 + i10;
    int i12 = paramArrayOfPoint[1].y - paramArrayOfPoint[3].y;
    if (i12 > 0)
      m = 1;
    int i13 = i12 + m;
    float f6 = paramArrayOfPoint[3].x;
    float f7 = f1 * i11;
    int i14 = MathUtils.round(f6 - f7);
    float f8 = paramArrayOfPoint[3].y;
    float f9 = f1 * i13;
    int i15 = MathUtils.round(f8 - f9);
    int i16 = MathUtils.round(f7 + paramArrayOfPoint[1].x);
    int i17 = MathUtils.round(f9 + paramArrayOfPoint[1].y);
    if ((isValid(i7, i8)) && (isValid(i16, i17)) && (isValid(i5, i6)) && (isValid(i14, i15)))
    {
      ResultPoint[] arrayOfResultPoint = new ResultPoint[4];
      arrayOfResultPoint[0] = new ResultPoint(i7, i8);
      arrayOfResultPoint[1] = new ResultPoint(i16, i17);
      arrayOfResultPoint[2] = new ResultPoint(i5, i6);
      arrayOfResultPoint[3] = new ResultPoint(i14, i15);
      return arrayOfResultPoint;
    }
    throw NotFoundException.getNotFoundInstance();
  }

  private void getParameters(boolean[] paramArrayOfBoolean)
  {
    int i;
    int j;
    if (this.compact)
    {
      i = 2;
      j = 6;
    }
    else
    {
      i = 5;
      j = 11;
    }
    for (int k = 0; k < i; k++)
    {
      this.nbLayers <<= 1;
      if (paramArrayOfBoolean[k] != 0)
        this.nbLayers = (1 + this.nbLayers);
    }
    for (int m = i; m < i + j; m++)
    {
      this.nbDataBlocks <<= 1;
      if (paramArrayOfBoolean[m] != 0)
        this.nbDataBlocks = (1 + this.nbDataBlocks);
    }
    this.nbLayers = (1 + this.nbLayers);
    this.nbDataBlocks = (1 + this.nbDataBlocks);
  }

  private boolean isValid(int paramInt1, int paramInt2)
  {
    boolean bool;
    if ((paramInt1 >= 0) && (paramInt1 < this.image.getWidth()) && (paramInt2 > 0) && (paramInt2 < this.image.getHeight()))
      bool = true;
    else
      bool = false;
    return bool;
  }

  private boolean isWhiteOrBlackRectangle(Point paramPoint1, Point paramPoint2, Point paramPoint3, Point paramPoint4)
  {
    Point localPoint1 = new Point(-3 + paramPoint1.x, 3 + paramPoint1.y);
    Point localPoint2 = new Point(-3 + paramPoint2.x, -3 + paramPoint2.y);
    Point localPoint3 = new Point(3 + paramPoint3.x, -3 + paramPoint3.y);
    Point localPoint4 = new Point(3 + paramPoint4.x, 3 + paramPoint4.y);
    int i = getColor(localPoint4, localPoint1);
    if (i == 0)
      return false;
    if (getColor(localPoint1, localPoint2) != i)
      return false;
    if (getColor(localPoint2, localPoint3) != i)
      return false;
    int j = getColor(localPoint3, localPoint4);
    boolean bool = false;
    if (j == i)
      bool = true;
    return bool;
  }

  private BitMatrix sampleGrid(BitMatrix paramBitMatrix, ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, ResultPoint paramResultPoint4)
  {
    int i;
    if (this.compact)
      i = 11 + 4 * this.nbLayers;
    int j;
    while (true)
    {
      j = i;
      break;
      if (this.nbLayers <= 4)
        i = 15 + 4 * this.nbLayers;
      else
        i = 15 + (4 * this.nbLayers + 2 * (1 + (this.nbLayers - 4) / 8));
    }
    GridSampler localGridSampler = GridSampler.getInstance();
    float f = j - 0.5F;
    return localGridSampler.sampleGrid(paramBitMatrix, j, j, 0.5F, 0.5F, f, 0.5F, f, f, 0.5F, f, paramResultPoint1.getX(), paramResultPoint1.getY(), paramResultPoint4.getX(), paramResultPoint4.getY(), paramResultPoint3.getX(), paramResultPoint3.getY(), paramResultPoint2.getX(), paramResultPoint2.getY());
  }

  private boolean[] sampleLine(Point paramPoint1, Point paramPoint2, int paramInt)
  {
    boolean[] arrayOfBoolean = new boolean[paramInt];
    float f1 = distance(paramPoint1, paramPoint2);
    float f2 = f1 / paramInt - 1;
    float f3 = f2 * paramPoint2.x - paramPoint1.x / f1;
    float f4 = f2 * paramPoint2.y - paramPoint1.y / f1;
    float f5 = paramPoint1.x;
    float f6 = paramPoint1.y;
    for (int i = 0; i < paramInt; i++)
    {
      arrayOfBoolean[i] = this.image.get(MathUtils.round(f5), MathUtils.round(f6));
      f5 += f3;
      f6 += f4;
    }
    return arrayOfBoolean;
  }

  public AztecDetectorResult detect()
  {
    Point[] arrayOfPoint = getBullEyeCornerPoints(getMatrixCenter());
    extractParameters(arrayOfPoint);
    ResultPoint[] arrayOfResultPoint = getMatrixCornerPoints(arrayOfPoint);
    BitMatrix localBitMatrix = sampleGrid(this.image, arrayOfResultPoint[(this.shift % 4)], arrayOfResultPoint[((3 + this.shift) % 4)], arrayOfResultPoint[((2 + this.shift) % 4)], arrayOfResultPoint[((1 + this.shift) % 4)]);
    AztecDetectorResult localAztecDetectorResult = new AztecDetectorResult(localBitMatrix, arrayOfResultPoint, this.compact, this.nbDataBlocks, this.nbLayers);
    return localAztecDetectorResult;
  }

  static final class Point
  {
    final int x;
    final int y;

    Point(int paramInt1, int paramInt2)
    {
      this.x = paramInt1;
      this.y = paramInt2;
    }

    ResultPoint toResultPoint()
    {
      return new ResultPoint(this.x, this.y);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.aztec.detector.Detector
 * JD-Core Version:    0.6.1
 */