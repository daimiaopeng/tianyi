package com.karics.library.zxing.view;

import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;

public final class a
  implements ResultPointCallback
{
  private final ViewfinderView a;

  public a(ViewfinderView paramViewfinderView)
  {
    this.a = paramViewfinderView;
  }

  public void foundPossibleResultPoint(ResultPoint paramResultPoint)
  {
    this.a.a(paramResultPoint);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.karics.library.zxing.view.a
 * JD-Core Version:    0.6.1
 */