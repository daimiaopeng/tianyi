package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public final class Code128Writer extends OneDimensionalCodeWriter
{
  private static final int CODE_CODE_B = 100;
  private static final int CODE_CODE_C = 99;
  private static final int CODE_FNC_1 = 102;
  private static final int CODE_FNC_2 = 97;
  private static final int CODE_FNC_3 = 96;
  private static final int CODE_FNC_4_B = 100;
  private static final int CODE_START_B = 104;
  private static final int CODE_START_C = 105;
  private static final int CODE_STOP = 106;
  private static final char ESCAPE_FNC_1 = 'ñ';
  private static final char ESCAPE_FNC_2 = 'ò';
  private static final char ESCAPE_FNC_3 = 'ó';
  private static final char ESCAPE_FNC_4 = 'ô';

  private static boolean isDigits(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    int i = paramInt2 + paramInt1;
    int j = paramCharSequence.length();
    while ((paramInt1 < i) && (paramInt1 < j))
    {
      int k = paramCharSequence.charAt(paramInt1);
      if ((k < 48) || (k > 57))
      {
        if (k != 241)
          return false;
        i++;
      }
      paramInt1++;
    }
    boolean bool = false;
    if (i <= j)
      bool = true;
    return bool;
  }

  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Map<EncodeHintType, ?> paramMap)
  {
    if (paramBarcodeFormat != BarcodeFormat.CODE_128)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Can only encode CODE_128, but got ");
      localStringBuilder.append(paramBarcodeFormat);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    return super.encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, paramMap);
  }

  public boolean[] encode(String paramString)
  {
    int i = paramString.length();
    if ((i >= 1) && (i <= 80))
    {
      int j = 0;
      for (int k = 0; k < i; k++)
      {
        char c = paramString.charAt(k);
        if ((c < ' ') || (c > '~'))
          switch (c)
          {
          default:
            StringBuilder localStringBuilder2 = new StringBuilder();
            localStringBuilder2.append("Bad character in input: ");
            localStringBuilder2.append(c);
            throw new IllegalArgumentException(localStringBuilder2.toString());
          case 'ñ':
          case 'ò':
          case 'ó':
          case 'ô':
          }
      }
      ArrayList localArrayList = new ArrayList();
      int m = 0;
      int n = 0;
      int i1 = 0;
      int i2 = 1;
      while (m < i)
      {
        int i8 = 99;
        int i9;
        if (n == i8)
          i9 = 2;
        else
          i9 = 4;
        boolean bool = isDigits(paramString, m, i9);
        int i10 = 100;
        if (!bool)
          i8 = 100;
        if (i8 == n)
        {
          if (n == i10)
          {
            i10 = '￠' + paramString.charAt(m);
            m++;
          }
          else
          {
            switch (paramString.charAt(m))
            {
            default:
              int i11 = m + 2;
              i10 = Integer.parseInt(paramString.substring(m, i11));
              m = i11;
              break;
            case 'ô':
              m++;
              break;
            case 'ó':
              i10 = 96;
              m++;
              break;
            case 'ò':
              i10 = 97;
              m++;
              break;
            case 'ñ':
              i10 = 102;
              m++;
              break;
            }
          }
        }
        else
        {
          if (n == 0)
          {
            if (i8 == i10)
              i10 = 104;
            else
              i10 = 105;
          }
          else
            i10 = i8;
          n = i8;
        }
        localArrayList.add(Code128Reader.CODE_PATTERNS[i10]);
        i1 += i10 * i2;
        if (m != 0)
          i2++;
      }
      int i3 = i1 % 103;
      localArrayList.add(Code128Reader.CODE_PATTERNS[i3]);
      localArrayList.add(Code128Reader.CODE_PATTERNS[106]);
      Iterator localIterator1 = localArrayList.iterator();
      int i6;
      for (int i4 = 0; localIterator1.hasNext(); i4 = i6)
      {
        int[] arrayOfInt = (int[])localIterator1.next();
        int i5 = arrayOfInt.length;
        i6 = i4;
        for (int i7 = 0; i7 < i5; i7++)
          i6 += arrayOfInt[i7];
      }
      boolean[] arrayOfBoolean = new boolean[i4];
      Iterator localIterator2 = localArrayList.iterator();
      while (localIterator2.hasNext())
        j += appendPattern(arrayOfBoolean, j, (int[])localIterator2.next(), true);
      return arrayOfBoolean;
    }
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("Contents length should be between 1 and 80 characters, but got ");
    localStringBuilder1.append(i);
    throw new IllegalArgumentException(localStringBuilder1.toString());
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.Code128Writer
 * JD-Core Version:    0.6.1
 */