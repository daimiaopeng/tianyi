package com.google.zxing.oned;

final class UPCEANExtensionSupport
{
  private static final int[] EXTENSION_START_PATTERN = { 1, 1, 2 };
  private final UPCEANExtension5Support fiveSupport = new UPCEANExtension5Support();
  private final UPCEANExtension2Support twoSupport = new UPCEANExtension2Support();

  // ERROR //
  com.google.zxing.Result decodeRow(int paramInt1, com.google.zxing.common.BitArray paramBitArray, int paramInt2)
  {
    // Byte code:
    //   0: aload_2
    //   1: iload_3
    //   2: iconst_0
    //   3: getstatic 14	com/google/zxing/oned/UPCEANExtensionSupport:EXTENSION_START_PATTERN	[I
    //   6: invokestatic 37	com/google/zxing/oned/UPCEANReader:findGuardPattern	(Lcom/google/zxing/common/BitArray;IZ[I)[I
    //   9: astore 4
    //   11: aload_0
    //   12: getfield 27	com/google/zxing/oned/UPCEANExtensionSupport:fiveSupport	Lcom/google/zxing/oned/UPCEANExtension5Support;
    //   15: iload_1
    //   16: aload_2
    //   17: aload 4
    //   19: invokevirtual 40	com/google/zxing/oned/UPCEANExtension5Support:decodeRow	(ILcom/google/zxing/common/BitArray;[I)Lcom/google/zxing/Result;
    //   22: astore 5
    //   24: aload 5
    //   26: areturn
    //   27: aload_0
    //   28: getfield 22	com/google/zxing/oned/UPCEANExtensionSupport:twoSupport	Lcom/google/zxing/oned/UPCEANExtension2Support;
    //   31: iload_1
    //   32: aload_2
    //   33: aload 4
    //   35: invokevirtual 41	com/google/zxing/oned/UPCEANExtension2Support:decodeRow	(ILcom/google/zxing/common/BitArray;[I)Lcom/google/zxing/Result;
    //   38: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   11	24	27	com/google/zxing/ReaderException
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.UPCEANExtensionSupport
 * JD-Core Version:    0.6.1
 */