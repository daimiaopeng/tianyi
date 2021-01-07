package com.google.zxing.datamatrix.encoder;

public enum SymbolShapeHint
{
  static
  {
    FORCE_RECTANGLE = new SymbolShapeHint("FORCE_RECTANGLE", 2);
    SymbolShapeHint[] arrayOfSymbolShapeHint = new SymbolShapeHint[3];
    arrayOfSymbolShapeHint[0] = FORCE_NONE;
    arrayOfSymbolShapeHint[1] = FORCE_SQUARE;
    arrayOfSymbolShapeHint[2] = FORCE_RECTANGLE;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.datamatrix.encoder.SymbolShapeHint
 * JD-Core Version:    0.6.1
 */