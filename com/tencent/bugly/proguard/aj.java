package com.tencent.bugly.proguard;

public final class aj extends k
  implements Cloneable
{
  private static byte[] d;
  private byte a = 0;
  private String b = "";
  private byte[] c = null;

  public aj()
  {
  }

  public aj(byte paramByte, String paramString, byte[] paramArrayOfByte)
  {
    this.a = paramByte;
    this.b = paramString;
    this.c = paramArrayOfByte;
  }

  public final void a(i parami)
  {
    this.a = parami.a(this.a, 0, true);
    this.b = parami.b(1, true);
    if (d == null)
    {
      byte[] arrayOfByte = (byte[])new byte[1];
      d = arrayOfByte;
      ((byte[])arrayOfByte)[0] = 0;
    }
    this.c = ((byte[])parami.c(2, false));
  }

  public final void a(j paramj)
  {
    paramj.a(this.a, 0);
    paramj.a(this.b, 1);
    if (this.c != null)
      paramj.a(this.c, 2);
  }

  public final void a(StringBuilder paramStringBuilder, int paramInt)
  {
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.aj
 * JD-Core Version:    0.6.1
 */