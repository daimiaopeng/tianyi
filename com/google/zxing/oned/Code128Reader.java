package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Code128Reader extends OneDReader
{
  private static final int CODE_CODE_A = 101;
  private static final int CODE_CODE_B = 100;
  private static final int CODE_CODE_C = 99;
  private static final int CODE_FNC_1 = 102;
  private static final int CODE_FNC_2 = 97;
  private static final int CODE_FNC_3 = 96;
  private static final int CODE_FNC_4_A = 101;
  private static final int CODE_FNC_4_B = 100;
  static final int[][] CODE_PATTERNS = { { 2, 1, 2, 2, 2, 2 }, { 2, 2, 2, 1, 2, 2 }, { 2, 2, 2, 2, 2, 1 }, { 1, 2, 1, 2, 2, 3 }, { 1, 2, 1, 3, 2, 2 }, { 1, 3, 1, 2, 2, 2 }, { 1, 2, 2, 2, 1, 3 }, { 1, 2, 2, 3, 1, 2 }, { 1, 3, 2, 2, 1, 2 }, { 2, 2, 1, 2, 1, 3 }, { 2, 2, 1, 3, 1, 2 }, { 2, 3, 1, 2, 1, 2 }, { 1, 1, 2, 2, 3, 2 }, { 1, 2, 2, 1, 3, 2 }, { 1, 2, 2, 2, 3, 1 }, { 1, 1, 3, 2, 2, 2 }, { 1, 2, 3, 1, 2, 2 }, { 1, 2, 3, 2, 2, 1 }, { 2, 2, 3, 2, 1, 1 }, { 2, 2, 1, 1, 3, 2 }, { 2, 2, 1, 2, 3, 1 }, { 2, 1, 3, 2, 1, 2 }, { 2, 2, 3, 1, 1, 2 }, { 3, 1, 2, 1, 3, 1 }, { 3, 1, 1, 2, 2, 2 }, { 3, 2, 1, 1, 2, 2 }, { 3, 2, 1, 2, 2, 1 }, { 3, 1, 2, 2, 1, 2 }, { 3, 2, 2, 1, 1, 2 }, { 3, 2, 2, 2, 1, 1 }, { 2, 1, 2, 1, 2, 3 }, { 2, 1, 2, 3, 2, 1 }, { 2, 3, 2, 1, 2, 1 }, { 1, 1, 1, 3, 2, 3 }, { 1, 3, 1, 1, 2, 3 }, { 1, 3, 1, 3, 2, 1 }, { 1, 1, 2, 3, 1, 3 }, { 1, 3, 2, 1, 1, 3 }, { 1, 3, 2, 3, 1, 1 }, { 2, 1, 1, 3, 1, 3 }, { 2, 3, 1, 1, 1, 3 }, { 2, 3, 1, 3, 1, 1 }, { 1, 1, 2, 1, 3, 3 }, { 1, 1, 2, 3, 3, 1 }, { 1, 3, 2, 1, 3, 1 }, { 1, 1, 3, 1, 2, 3 }, { 1, 1, 3, 3, 2, 1 }, { 1, 3, 3, 1, 2, 1 }, { 3, 1, 3, 1, 2, 1 }, { 2, 1, 1, 3, 3, 1 }, { 2, 3, 1, 1, 3, 1 }, { 2, 1, 3, 1, 1, 3 }, { 2, 1, 3, 3, 1, 1 }, { 2, 1, 3, 1, 3, 1 }, { 3, 1, 1, 1, 2, 3 }, { 3, 1, 1, 3, 2, 1 }, { 3, 3, 1, 1, 2, 1 }, { 3, 1, 2, 1, 1, 3 }, { 3, 1, 2, 3, 1, 1 }, { 3, 3, 2, 1, 1, 1 }, { 3, 1, 4, 1, 1, 1 }, { 2, 2, 1, 4, 1, 1 }, { 4, 3, 1, 1, 1, 1 }, { 1, 1, 1, 2, 2, 4 }, { 1, 1, 1, 4, 2, 2 }, { 1, 2, 1, 1, 2, 4 }, { 1, 2, 1, 4, 2, 1 }, { 1, 4, 1, 1, 2, 2 }, { 1, 4, 1, 2, 2, 1 }, { 1, 1, 2, 2, 1, 4 }, { 1, 1, 2, 4, 1, 2 }, { 1, 2, 2, 1, 1, 4 }, { 1, 2, 2, 4, 1, 1 }, { 1, 4, 2, 1, 1, 2 }, { 1, 4, 2, 2, 1, 1 }, { 2, 4, 1, 2, 1, 1 }, { 2, 2, 1, 1, 1, 4 }, { 4, 1, 3, 1, 1, 1 }, { 2, 4, 1, 1, 1, 2 }, { 1, 3, 4, 1, 1, 1 }, { 1, 1, 1, 2, 4, 2 }, { 1, 2, 1, 1, 4, 2 }, { 1, 2, 1, 2, 4, 1 }, { 1, 1, 4, 2, 1, 2 }, { 1, 2, 4, 1, 1, 2 }, { 1, 2, 4, 2, 1, 1 }, { 4, 1, 1, 2, 1, 2 }, { 4, 2, 1, 1, 1, 2 }, { 4, 2, 1, 2, 1, 1 }, { 2, 1, 2, 1, 4, 1 }, { 2, 1, 4, 1, 2, 1 }, { 4, 1, 2, 1, 2, 1 }, { 1, 1, 1, 1, 4, 3 }, { 1, 1, 1, 3, 4, 1 }, { 1, 3, 1, 1, 4, 1 }, { 1, 1, 4, 1, 1, 3 }, { 1, 1, 4, 3, 1, 1 }, { 4, 1, 1, 1, 1, 3 }, { 4, 1, 1, 3, 1, 1 }, { 1, 1, 3, 1, 4, 1 }, { 1, 1, 4, 1, 3, 1 }, { 3, 1, 1, 1, 4, 1 }, { 4, 1, 1, 1, 3, 1 }, { 2, 1, 1, 4, 1, 2 }, { 2, 1, 1, 2, 1, 4 }, { 2, 1, 1, 2, 3, 2 }, { 2, 3, 3, 1, 1, 1, 2 } };
  private static final int CODE_SHIFT = 98;
  private static final int CODE_START_A = 103;
  private static final int CODE_START_B = 104;
  private static final int CODE_START_C = 105;
  private static final int CODE_STOP = 106;
  private static final int MAX_AVG_VARIANCE = 64;
  private static final int MAX_INDIVIDUAL_VARIANCE = 179;

  private static int decodeCode(BitArray paramBitArray, int[] paramArrayOfInt, int paramInt)
  {
    recordPattern(paramBitArray, paramInt, paramArrayOfInt);
    int i = 64;
    int j = -1;
    for (int k = 0; k < CODE_PATTERNS.length; k++)
    {
      int m = patternMatchVariance(paramArrayOfInt, CODE_PATTERNS[k], 179);
      if (m < i)
      {
        j = k;
        i = m;
      }
    }
    if (j >= 0)
      return j;
    throw NotFoundException.getNotFoundInstance();
  }

  private static int[] findStartPattern(BitArray paramBitArray)
  {
    int i = paramBitArray.getSize();
    int j = paramBitArray.getNextSet(0);
    int[] arrayOfInt = new int[6];
    int k = arrayOfInt.length;
    int m = j;
    int n = 0;
    int i1 = 0;
    while (j < i)
    {
      if ((n ^ paramBitArray.get(j)) != 0)
      {
        arrayOfInt[i1] = (1 + arrayOfInt[i1]);
      }
      else
      {
        int i2 = k - 1;
        if (i1 == i2)
        {
          int i3 = 64;
          int i4 = 103;
          int i5 = -1;
          while (i4 <= 105)
          {
            int i7 = patternMatchVariance(arrayOfInt, CODE_PATTERNS[i4], 179);
            if (i7 < i3)
            {
              i5 = i4;
              i3 = i7;
            }
            i4++;
          }
          if ((i5 >= 0) && (paramBitArray.isRange(Math.max(0, m - (j - m) / 2), m, false)))
            return new int[] { m, j, i5 };
          m += arrayOfInt[0] + arrayOfInt[1];
          int i6 = k - 2;
          System.arraycopy(arrayOfInt, 2, arrayOfInt, 0, i6);
          arrayOfInt[i6] = 0;
          arrayOfInt[i2] = 0;
          i1--;
        }
        else
        {
          i1++;
        }
        arrayOfInt[i1] = 1;
        n ^= 1;
      }
      j++;
    }
    throw NotFoundException.getNotFoundInstance();
  }

  public Result decodeRow(int paramInt, BitArray paramBitArray, Map<DecodeHintType, ?> paramMap)
  {
    int i;
    if ((paramMap != null) && (paramMap.containsKey(DecodeHintType.ASSUME_GS1)))
      i = 1;
    else
      i = 0;
    int[] arrayOfInt1 = findStartPattern(paramBitArray);
    int j = arrayOfInt1[2];
    int k;
    switch (j)
    {
    default:
      throw FormatException.getFormatInstance();
    case 105:
      k = 99;
      break;
    case 104:
      k = 100;
      break;
    case 103:
      k = 101;
    }
    StringBuilder localStringBuilder = new StringBuilder(20);
    ArrayList localArrayList = new ArrayList(20);
    int m = arrayOfInt1[0];
    int n = arrayOfInt1[1];
    int[] arrayOfInt2 = new int[6];
    int i1 = j;
    int i2 = k;
    int i3 = m;
    int i4 = 0;
    int i5 = 0;
    int i6 = 0;
    int i7 = 0;
    int i8 = 0;
    int i9 = 1;
    while (i5 == 0)
    {
      int i14 = decodeCode(paramBitArray, arrayOfInt2, n);
      localArrayList.add(Byte.valueOf((byte)i14));
      if (i14 != 106)
        i9 = 1;
      if (i14 != 106)
      {
        i8++;
        i1 += i8 * i14;
      }
      int i15 = arrayOfInt2.length;
      int i16 = n;
      for (int i17 = 0; i17 < i15; i17++)
        i16 += arrayOfInt2[i17];
      switch (i14)
      {
      default:
        switch (i2)
        {
        default:
        case 101:
        case 100:
        case 99:
        }
        break;
      case 103:
      case 104:
      case 105:
      }
      while (true)
      {
        break;
        throw FormatException.getFormatInstance();
        if (i14 < 64)
        {
          localStringBuilder.append((char)(i14 + 32));
        }
        else if (i14 < 96)
        {
          localStringBuilder.append((char)(i14 - 64));
        }
        else
        {
          if (i14 != 106)
            i9 = 0;
          int i21;
          if (i14 != 106)
          {
            switch (i14)
            {
            default:
              break;
            case 102:
              if (i != 0)
                if (localStringBuilder.length() == 0)
                  localStringBuilder.append("]C1");
                else
                  localStringBuilder.append('\035');
              break;
            case 100:
              i18 = 0;
              break;
            case 98:
              i18 = 1;
              i21 = 100;
              break label640;
              if (i14 < 96)
              {
                localStringBuilder.append((char)(i14 + 32));
                continue;
              }
              if (i14 != 106)
                i9 = 0;
              if (i14 != 106)
                switch (i14)
                {
                default:
                  break;
                case 102:
                  if (i != 0)
                    if (localStringBuilder.length() == 0)
                      localStringBuilder.append("]C1");
                    else
                      localStringBuilder.append('\035');
                  break;
                case 101:
                  i18 = 0;
                case 99:
                case 98:
                case 96:
                case 97:
                case 100:
                }
              break;
            case 99:
              i21 = 99;
              i18 = 0;
              break label640;
              i18 = 1;
              i21 = 101;
              break;
            case 96:
            case 97:
            case 101:
              i21 = i2;
              i18 = 0;
              break;
            }
          }
          else
          {
            i21 = i2;
            i18 = 0;
            i5 = 1;
            label640: i2 = i21;
            break label794;
            if (i14 < 100)
            {
              if (i14 < 10)
                localStringBuilder.append('0');
              localStringBuilder.append(i14);
            }
            else
            {
              if (i14 != 106)
                i9 = 0;
              if (i14 != 106)
              {
                switch (i14)
                {
                default:
                  break;
                case 102:
                  if (i == 0)
                    break;
                  if (localStringBuilder.length() == 0)
                    localStringBuilder.append("]C1");
                  else
                    localStringBuilder.append('\035');
                  break;
                case 101:
                  i2 = 101;
                  i18 = 0;
                  break;
                case 100:
                  i2 = 100;
                  i18 = 0;
                  break;
                }
              }
              else
              {
                i5 = 1;
                i18 = 0;
                break label794;
              }
            }
          }
        }
      }
      int i18 = 0;
      label794: if (i6 != 0)
        if (i2 == 101)
          i2 = 100;
        else
          i2 = 101;
      i6 = i18;
      int i19 = i7;
      i7 = i14;
      i4 = i19;
      int i20 = i16;
      i3 = n;
      n = i20;
    }
    int i10 = paramBitArray.getNextUnset(n);
    if (!paramBitArray.isRange(i10, Math.min(paramBitArray.getSize(), i10 + (i10 - i3) / 2), false))
      throw NotFoundException.getNotFoundInstance();
    if ((i1 - i8 * i4) % 103 != i4)
      throw ChecksumException.getChecksumInstance();
    int i11 = localStringBuilder.length();
    if (i11 == 0)
      throw NotFoundException.getNotFoundInstance();
    if ((i11 > 0) && (i9 != 0))
      if (i2 == 99)
        localStringBuilder.delete(i11 - 2, i11);
      else
        localStringBuilder.delete(i11 - 1, i11);
    float f1 = arrayOfInt1[1] + arrayOfInt1[0] / 2.0F;
    float f2 = i10 + i3 / 2.0F;
    int i12 = localArrayList.size();
    byte[] arrayOfByte = new byte[i12];
    for (int i13 = 0; i13 < i12; i13++)
      arrayOfByte[i13] = ((Byte)localArrayList.get(i13)).byteValue();
    String str = localStringBuilder.toString();
    ResultPoint[] arrayOfResultPoint = new ResultPoint[2];
    float f3 = paramInt;
    arrayOfResultPoint[0] = new ResultPoint(f1, f3);
    arrayOfResultPoint[1] = new ResultPoint(f2, f3);
    return new Result(str, arrayOfByte, arrayOfResultPoint, BarcodeFormat.CODE_128);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.Code128Reader
 * JD-Core Version:    0.6.1
 */