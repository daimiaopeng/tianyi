package com.google.zxing.datamatrix.encoder;

public final class ErrorCorrection
{
  private static final int[] ALOG;
  private static final int[][] FACTORS;
  private static final int[] FACTOR_SETS = { 5, 7, 10, 11, 12, 14, 18, 20, 24, 28, 36, 42, 48, 56, 62, 68 };
  private static final int[] LOG;
  private static final int MODULO_VALUE = 301;

  static
  {
    int[][] arrayOfInt = new int[16][];
    int[] arrayOfInt1 = { 228, 48, 15, 111, 62 };
    int i = 0;
    arrayOfInt[0] = arrayOfInt1;
    arrayOfInt[1] = { 23, 68, 144, 134, 240, 92, 254 };
    arrayOfInt[2] = { 28, 24, 185, 166, 223, 248, 116, 255, 110, 61 };
    arrayOfInt[3] = { 175, 138, 205, 12, 194, 168, 39, 245, 60, 97, 120 };
    arrayOfInt[4] = { 41, 153, 158, 91, 61, 42, 142, 213, 97, 178, 100, 242 };
    arrayOfInt[5] = { 156, 97, 192, 252, 95, 9, 157, 119, 138, 45, 18, 186, 83, 185 };
    arrayOfInt[6] = { 83, 195, 100, 39, 188, 75, 66, 61, 241, 213, 109, 129, 94, 254, 225, 48, 90, 188 };
    arrayOfInt[7] = { 15, 195, 244, 9, 233, 71, 168, 2, 188, 160, 153, 145, 253, 79, 108, 82, 27, 174, 186, 172 };
    arrayOfInt[8] = { 52, 190, 88, 205, 109, 39, 176, 21, 155, 197, 251, 223, 155, 21, 5, 172, 254, 124, 12, 181, 184, 96, 50, 193 };
    arrayOfInt[9] = { 211, 231, 43, 97, 71, 96, 103, 174, 37, 151, 170, 53, 75, 34, 249, 121, 17, 138, 110, 213, 141, 136, 120, 151, 233, 168, 93, 255 };
    arrayOfInt[10] = { 245, 127, 242, 218, 130, 250, 162, 181, 102, 120, 84, 179, 220, 251, 80, 182, 229, 18, 2, 4, 68, 33, 101, 137, 95, 119, 115, 44, 175, 184, 59, 25, 225, 98, 81, 112 };
    arrayOfInt[11] = { 77, 193, 137, 31, 19, 38, 22, 153, 247, 105, 122, 2, 245, 133, 242, 8, 175, 95, 100, 9, 167, 105, 214, 111, 57, 121, 21, 1, 253, 57, 54, 101, 248, 202, 69, 50, 150, 177, 226, 5, 9, 5 };
    arrayOfInt[12] = { 245, 132, 172, 223, 96, 32, 117, 22, 238, 133, 238, 231, 205, 188, 237, 87, 191, 106, 16, 147, 118, 23, 37, 90, 170, 205, 131, 88, 120, 100, 66, 138, 186, 240, 82, 44, 176, 87, 187, 147, 160, 175, 69, 213, 92, 253, 225, 19 };
    arrayOfInt[13] = { 175, 9, 223, 238, 12, 17, 220, 208, 100, 29, 175, 170, 230, 192, 215, 235, 150, 159, 36, 223, 38, 200, 132, 54, 228, 146, 218, 234, 117, 203, 29, 232, 144, 238, 22, 150, 201, 117, 62, 207, 164, 13, 137, 245, 127, 67, 247, 28, 155, 43, 203, 107, 233, 53, 143, 46 };
    arrayOfInt[14] = { 242, 93, 169, 50, 144, 210, 39, 118, 202, 188, 201, 189, 143, 108, 196, 37, 185, 112, 134, 230, 245, 63, 197, 190, 250, 106, 185, 221, 175, 64, 114, 71, 161, 44, 147, 6, 27, 218, 51, 63, 87, 10, 40, 130, 188, 17, 163, 31, 176, 170, 4, 107, 232, 7, 94, 166, 224, 124, 86, 47, 11, 204 };
    arrayOfInt[15] = { 220, 228, 173, 89, 251, 149, 159, 56, 89, 33, 147, 244, 154, 36, 73, 127, 213, 136, 248, 180, 234, 197, 158, 177, 68, 122, 93, 213, 15, 160, 227, 236, 66, 139, 153, 185, 202, 167, 179, 25, 220, 232, 96, 210, 231, 136, 223, 239, 181, 241, 59, 52, 172, 25, 49, 232, 211, 189, 64, 54, 108, 153, 132, 63, 96, 103, 82, 186 };
    FACTORS = arrayOfInt;
    LOG = new int[256];
    ALOG = new int['ÿ'];
    int j = 1;
    while (i < 255)
    {
      ALOG[i] = j;
      LOG[j] = i;
      j <<= 1;
      if (j >= 256)
        j ^= 301;
      i++;
    }
  }

  private static String createECCBlock(CharSequence paramCharSequence, int paramInt)
  {
    return createECCBlock(paramCharSequence, 0, paramCharSequence.length(), paramInt);
  }

  private static String createECCBlock(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = 0;
    for (int j = 0; j < FACTOR_SETS.length; j++)
      if (FACTOR_SETS[j] == paramInt3)
        break label37;
    j = -1;
    label37: if (j < 0)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Illegal number of error correction codewords specified: ");
      localStringBuilder.append(paramInt3);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    int[] arrayOfInt = FACTORS[j];
    char[] arrayOfChar1 = new char[paramInt3];
    for (int k = 0; k < paramInt3; k++)
      arrayOfChar1[k] = '\000';
    for (int m = paramInt1; m < paramInt1 + paramInt2; m++)
    {
      int n = paramInt3 - 1;
      int i1 = arrayOfChar1[n] ^ paramCharSequence.charAt(m);
      while (n > 0)
      {
        if ((i1 != 0) && (arrayOfInt[n] != 0))
          arrayOfChar1[n] = (char)(arrayOfChar1[(n - 1)] ^ ALOG[((LOG[i1] + LOG[arrayOfInt[n]]) % 255)]);
        else
          arrayOfChar1[n] = arrayOfChar1[(n - 1)];
        n--;
      }
      if ((i1 != 0) && (arrayOfInt[0] != 0))
        arrayOfChar1[0] = (char)ALOG[((LOG[i1] + LOG[arrayOfInt[0]]) % 255)];
      else
        arrayOfChar1[0] = '\000';
    }
    char[] arrayOfChar2 = new char[paramInt3];
    while (i < paramInt3)
    {
      arrayOfChar2[i] = arrayOfChar1[(-1 + (paramInt3 - i))];
      i++;
    }
    return String.valueOf(arrayOfChar2);
  }

  public static String encodeECC200(String paramString, SymbolInfo paramSymbolInfo)
  {
    if (paramString.length() != paramSymbolInfo.dataCapacity)
      throw new IllegalArgumentException("The number of codewords does not match the selected symbol");
    StringBuilder localStringBuilder1 = new StringBuilder(paramSymbolInfo.dataCapacity + paramSymbolInfo.errorCodewords);
    localStringBuilder1.append(paramString);
    int i = paramSymbolInfo.getInterleavedBlockCount();
    if (i == 1)
    {
      localStringBuilder1.append(createECCBlock(paramString, paramSymbolInfo.errorCodewords));
    }
    else
    {
      localStringBuilder1.setLength(localStringBuilder1.capacity());
      int[] arrayOfInt1 = new int[i];
      int[] arrayOfInt2 = new int[i];
      int[] arrayOfInt3 = new int[i];
      int i4;
      for (int j = 0; j < i; j = i4)
      {
        i4 = j + 1;
        arrayOfInt1[j] = paramSymbolInfo.getDataLengthForInterleavedBlock(i4);
        arrayOfInt2[j] = paramSymbolInfo.getErrorLengthForInterleavedBlock(i4);
        arrayOfInt3[j] = 0;
        if (j > 0)
          arrayOfInt3[j] = (arrayOfInt3[(j - 1)] + arrayOfInt1[j]);
      }
      for (int k = 0; k < i; k++)
      {
        StringBuilder localStringBuilder2 = new StringBuilder(arrayOfInt1[k]);
        int m = k;
        while (m < paramSymbolInfo.dataCapacity)
        {
          localStringBuilder2.append(paramString.charAt(m));
          m += i;
        }
        String str = createECCBlock(localStringBuilder2.toString(), arrayOfInt2[k]);
        int n = k;
        int i3;
        for (int i1 = 0; n < i * arrayOfInt2[k]; i1 = i3)
        {
          int i2 = n + paramSymbolInfo.dataCapacity;
          i3 = i1 + 1;
          localStringBuilder1.setCharAt(i2, str.charAt(i1));
          n += i;
        }
      }
    }
    return localStringBuilder1.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.datamatrix.encoder.ErrorCorrection
 * JD-Core Version:    0.6.1
 */