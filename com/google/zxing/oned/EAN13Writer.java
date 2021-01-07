package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class EAN13Writer extends UPCEANWriter
{
  private static final int CODE_WIDTH = 95;

  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Map<EncodeHintType, ?> paramMap)
  {
    if (paramBarcodeFormat != BarcodeFormat.EAN_13)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Can only encode EAN_13, but got ");
      localStringBuilder.append(paramBarcodeFormat);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    return super.encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, paramMap);
  }

  // ERROR //
  public boolean[] encode(String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 51	java/lang/String:length	()I
    //   4: bipush 13
    //   6: if_icmpeq +39 -> 45
    //   9: new 21	java/lang/StringBuilder
    //   12: dup
    //   13: invokespecial 22	java/lang/StringBuilder:<init>	()V
    //   16: astore_2
    //   17: aload_2
    //   18: ldc 53
    //   20: invokevirtual 28	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: pop
    //   24: aload_2
    //   25: aload_1
    //   26: invokevirtual 51	java/lang/String:length	()I
    //   29: invokevirtual 56	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   32: pop
    //   33: new 33	java/lang/IllegalArgumentException
    //   36: dup
    //   37: aload_2
    //   38: invokevirtual 37	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   41: invokespecial 40	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   44: athrow
    //   45: aload_1
    //   46: invokestatic 62	com/google/zxing/oned/UPCEANReader:checkStandardUPCEANChecksum	(Ljava/lang/CharSequence;)Z
    //   49: ifne +13 -> 62
    //   52: new 33	java/lang/IllegalArgumentException
    //   55: dup
    //   56: ldc 64
    //   58: invokespecial 40	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   61: athrow
    //   62: aload_1
    //   63: iconst_0
    //   64: iconst_1
    //   65: invokevirtual 68	java/lang/String:substring	(II)Ljava/lang/String;
    //   68: invokestatic 74	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   71: istore 5
    //   73: getstatic 80	com/google/zxing/oned/EAN13Reader:FIRST_DIGIT_ENCODINGS	[I
    //   76: iload 5
    //   78: iaload
    //   79: istore 6
    //   81: bipush 95
    //   83: newarray boolean
    //   85: astore 7
    //   87: iconst_0
    //   88: aload 7
    //   90: iconst_0
    //   91: getstatic 83	com/google/zxing/oned/UPCEANReader:START_END_PATTERN	[I
    //   94: iconst_1
    //   95: invokestatic 87	com/google/zxing/oned/EAN13Writer:appendPattern	([ZI[IZ)I
    //   98: iadd
    //   99: istore 8
    //   101: iconst_1
    //   102: istore 9
    //   104: iload 9
    //   106: bipush 6
    //   108: if_icmpgt +65 -> 173
    //   111: iload 9
    //   113: iconst_1
    //   114: iadd
    //   115: istore 15
    //   117: aload_1
    //   118: iload 9
    //   120: iload 15
    //   122: invokevirtual 68	java/lang/String:substring	(II)Ljava/lang/String;
    //   125: invokestatic 74	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   128: istore 16
    //   130: iconst_1
    //   131: iload 6
    //   133: bipush 6
    //   135: iload 9
    //   137: isub
    //   138: ishr
    //   139: iand
    //   140: iconst_1
    //   141: if_icmpne +6 -> 147
    //   144: iinc 16 10
    //   147: iload 8
    //   149: aload 7
    //   151: iload 8
    //   153: getstatic 91	com/google/zxing/oned/UPCEANReader:L_AND_G_PATTERNS	[[I
    //   156: iload 16
    //   158: aaload
    //   159: iconst_0
    //   160: invokestatic 87	com/google/zxing/oned/EAN13Writer:appendPattern	([ZI[IZ)I
    //   163: iadd
    //   164: istore 8
    //   166: iload 15
    //   168: istore 9
    //   170: goto -66 -> 104
    //   173: iload 8
    //   175: aload 7
    //   177: iload 8
    //   179: getstatic 94	com/google/zxing/oned/UPCEANReader:MIDDLE_PATTERN	[I
    //   182: iconst_0
    //   183: invokestatic 87	com/google/zxing/oned/EAN13Writer:appendPattern	([ZI[IZ)I
    //   186: iadd
    //   187: istore 10
    //   189: bipush 7
    //   191: istore 11
    //   193: iload 11
    //   195: bipush 12
    //   197: if_icmpgt +48 -> 245
    //   200: iload 11
    //   202: iconst_1
    //   203: iadd
    //   204: istore 13
    //   206: aload_1
    //   207: iload 11
    //   209: iload 13
    //   211: invokevirtual 68	java/lang/String:substring	(II)Ljava/lang/String;
    //   214: invokestatic 74	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   217: istore 14
    //   219: iload 10
    //   221: aload 7
    //   223: iload 10
    //   225: getstatic 97	com/google/zxing/oned/UPCEANReader:L_PATTERNS	[[I
    //   228: iload 14
    //   230: aaload
    //   231: iconst_1
    //   232: invokestatic 87	com/google/zxing/oned/EAN13Writer:appendPattern	([ZI[IZ)I
    //   235: iadd
    //   236: istore 10
    //   238: iload 13
    //   240: istore 11
    //   242: goto -49 -> 193
    //   245: aload 7
    //   247: iload 10
    //   249: getstatic 83	com/google/zxing/oned/UPCEANReader:START_END_PATTERN	[I
    //   252: iconst_1
    //   253: invokestatic 87	com/google/zxing/oned/EAN13Writer:appendPattern	([ZI[IZ)I
    //   256: pop
    //   257: aload 7
    //   259: areturn
    //   260: new 33	java/lang/IllegalArgumentException
    //   263: dup
    //   264: ldc 99
    //   266: invokespecial 40	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   269: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   45	62	260	com/google/zxing/FormatException
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.EAN13Writer
 * JD-Core Version:    0.6.1
 */