package com.tencent.bugly.proguard;

import java.util.ArrayList;

public final class al extends k
  implements Cloneable
{
  private static ArrayList<ak> b;
  public ArrayList<ak> a = null;

  public final void a(i parami)
  {
    if (b == null)
    {
      b = new ArrayList();
      ak localak = new ak();
      b.add(localak);
    }
    this.a = ((ArrayList)parami.a(b, 0, true));
  }

  public final void a(j paramj)
  {
    paramj.a(this.a, 0);
  }

  public final void a(StringBuilder paramStringBuilder, int paramInt)
  {
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.al
 * JD-Core Version:    0.6.1
 */