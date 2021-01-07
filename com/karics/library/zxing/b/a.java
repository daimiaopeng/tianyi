package com.karics.library.zxing.b;

import com.google.zxing.BarcodeFormat;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public final class a
{
  public static final Set<BarcodeFormat> a;
  static final Set<BarcodeFormat> b;
  static final Set<BarcodeFormat> c;
  static final Set<BarcodeFormat> d;
  static final Set<BarcodeFormat> e;
  static final Set<BarcodeFormat> f;
  private static final Pattern g = Pattern.compile(",");
  private static final Set<BarcodeFormat> h;
  private static final Map<String, Set<BarcodeFormat>> i;

  static
  {
    c = EnumSet.of(BarcodeFormat.QR_CODE);
    d = EnumSet.of(BarcodeFormat.DATA_MATRIX);
    e = EnumSet.of(BarcodeFormat.AZTEC);
    f = EnumSet.of(BarcodeFormat.PDF_417);
    BarcodeFormat localBarcodeFormat = BarcodeFormat.UPC_A;
    BarcodeFormat[] arrayOfBarcodeFormat = new BarcodeFormat[5];
    arrayOfBarcodeFormat[0] = BarcodeFormat.UPC_E;
    arrayOfBarcodeFormat[1] = BarcodeFormat.EAN_13;
    arrayOfBarcodeFormat[2] = BarcodeFormat.EAN_8;
    arrayOfBarcodeFormat[3] = BarcodeFormat.RSS_14;
    arrayOfBarcodeFormat[4] = BarcodeFormat.RSS_EXPANDED;
    a = EnumSet.of(localBarcodeFormat, arrayOfBarcodeFormat);
    b = EnumSet.of(BarcodeFormat.CODE_39, BarcodeFormat.CODE_93, BarcodeFormat.CODE_128, BarcodeFormat.ITF, BarcodeFormat.CODABAR);
    h = EnumSet.copyOf(a);
    h.addAll(b);
    i = new HashMap();
    i.put("ONE_D_MODE", h);
    i.put("PRODUCT_MODE", a);
    i.put("QR_CODE_MODE", c);
    i.put("DATA_MATRIX_MODE", d);
    i.put("AZTEC_MODE", e);
    i.put("PDF417_MODE", f);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.karics.library.zxing.b.a
 * JD-Core Version:    0.6.1
 */