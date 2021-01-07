package com.tencent.bugly.proguard;

import java.io.Serializable;

public abstract class k
  implements Serializable
{
  public abstract void a(i parami);

  public abstract void a(j paramj);

  public abstract void a(StringBuilder paramStringBuilder, int paramInt);

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    a(localStringBuilder, 0);
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.k
 * JD-Core Version:    0.6.1
 */