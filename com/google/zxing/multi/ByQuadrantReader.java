package com.google.zxing.multi;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.Reader;
import com.google.zxing.Result;

public final class ByQuadrantReader
  implements Reader
{
  private final Reader delegate;

  public ByQuadrantReader(Reader paramReader)
  {
    this.delegate = paramReader;
  }

  public Result decode(BinaryBitmap paramBinaryBitmap)
  {
    return decode(paramBinaryBitmap, null);
  }

  // ERROR //
  public Result decode(BinaryBitmap paramBinaryBitmap, java.util.Map<com.google.zxing.DecodeHintType, ?> paramMap)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 28	com/google/zxing/BinaryBitmap:getWidth	()I
    //   4: istore_3
    //   5: aload_1
    //   6: invokevirtual 31	com/google/zxing/BinaryBitmap:getHeight	()I
    //   9: istore 4
    //   11: iload_3
    //   12: iconst_2
    //   13: idiv
    //   14: istore 5
    //   16: iload 4
    //   18: iconst_2
    //   19: idiv
    //   20: istore 6
    //   22: aload_1
    //   23: iconst_0
    //   24: iconst_0
    //   25: iload 5
    //   27: iload 6
    //   29: invokevirtual 35	com/google/zxing/BinaryBitmap:crop	(IIII)Lcom/google/zxing/BinaryBitmap;
    //   32: astore 7
    //   34: aload_0
    //   35: getfield 15	com/google/zxing/multi/ByQuadrantReader:delegate	Lcom/google/zxing/Reader;
    //   38: aload 7
    //   40: aload_2
    //   41: invokeinterface 36 3 0
    //   46: astore 15
    //   48: aload 15
    //   50: areturn
    //   51: aload_1
    //   52: iload 5
    //   54: iconst_0
    //   55: iload 5
    //   57: iload 6
    //   59: invokevirtual 35	com/google/zxing/BinaryBitmap:crop	(IIII)Lcom/google/zxing/BinaryBitmap;
    //   62: astore 8
    //   64: aload_0
    //   65: getfield 15	com/google/zxing/multi/ByQuadrantReader:delegate	Lcom/google/zxing/Reader;
    //   68: aload 8
    //   70: aload_2
    //   71: invokeinterface 36 3 0
    //   76: astore 14
    //   78: aload 14
    //   80: areturn
    //   81: aload_1
    //   82: iconst_0
    //   83: iload 6
    //   85: iload 5
    //   87: iload 6
    //   89: invokevirtual 35	com/google/zxing/BinaryBitmap:crop	(IIII)Lcom/google/zxing/BinaryBitmap;
    //   92: astore 9
    //   94: aload_0
    //   95: getfield 15	com/google/zxing/multi/ByQuadrantReader:delegate	Lcom/google/zxing/Reader;
    //   98: aload 9
    //   100: aload_2
    //   101: invokeinterface 36 3 0
    //   106: astore 13
    //   108: aload 13
    //   110: areturn
    //   111: aload_1
    //   112: iload 5
    //   114: iload 6
    //   116: iload 5
    //   118: iload 6
    //   120: invokevirtual 35	com/google/zxing/BinaryBitmap:crop	(IIII)Lcom/google/zxing/BinaryBitmap;
    //   123: astore 10
    //   125: aload_0
    //   126: getfield 15	com/google/zxing/multi/ByQuadrantReader:delegate	Lcom/google/zxing/Reader;
    //   129: aload 10
    //   131: aload_2
    //   132: invokeinterface 36 3 0
    //   137: astore 12
    //   139: aload 12
    //   141: areturn
    //   142: aload_1
    //   143: iload 5
    //   145: iconst_2
    //   146: idiv
    //   147: iload 6
    //   149: iconst_2
    //   150: idiv
    //   151: iload 5
    //   153: iload 6
    //   155: invokevirtual 35	com/google/zxing/BinaryBitmap:crop	(IIII)Lcom/google/zxing/BinaryBitmap;
    //   158: astore 11
    //   160: aload_0
    //   161: getfield 15	com/google/zxing/multi/ByQuadrantReader:delegate	Lcom/google/zxing/Reader;
    //   164: aload 11
    //   166: aload_2
    //   167: invokeinterface 36 3 0
    //   172: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   34	48	51	com/google/zxing/NotFoundException
    //   64	78	81	com/google/zxing/NotFoundException
    //   94	108	111	com/google/zxing/NotFoundException
    //   125	139	142	com/google/zxing/NotFoundException
  }

  public void reset()
  {
    this.delegate.reset();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.multi.ByQuadrantReader
 * JD-Core Version:    0.6.1
 */