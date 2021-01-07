package com.cndatacom.d;

import com.cndatacom.campus.jscportal.MyApplication;
import com.cndatacom.dykeylib.PortalCipher;
import com.cndatacom.e.m;
import com.cndatacom.httppap.PortalShared;
import com.cndatacom.xjhui.MainUiActivity;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class a
{
  public int a;
  public short b;
  public short c;
  public ByteBuffer d = null;
  private final int e = 4;

  public int a(byte[] paramArrayOfByte, int paramInt)
  {
    if (paramInt < 4)
      return 0;
    ByteBuffer localByteBuffer = ByteBuffer.allocate(paramInt);
    localByteBuffer.put(paramArrayOfByte);
    localByteBuffer.position(0);
    localByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    this.a = localByteBuffer.getShort();
    if ((this.a <= 1024) && (this.a <= paramInt))
    {
      localByteBuffer.order(ByteOrder.BIG_ENDIAN);
      int i = localByteBuffer.getShort();
      this.b = (short)(0xFF & i >>> 8);
      this.c = (short)(i & 0xFF);
      byte[] arrayOfByte = new byte[this.a];
      localByteBuffer.get(arrayOfByte);
      this.d = ByteBuffer.allocate(this.a);
      this.d.put(arrayOfByte);
      return 4 + this.a;
    }
    return 0;
  }

  void a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return;
    this.d = ByteBuffer.allocate(paramArrayOfByte.length);
    this.d.put(paramArrayOfByte);
  }

  public byte[] a()
  {
    this.a = 0;
    byte[] arrayOfByte = this.d.array();
    if (arrayOfByte != null)
    {
      if (1 == this.b)
      {
        PortalShared localPortalShared = PortalShared.getInstance();
        PortalCipher localPortalCipher = localPortalShared.getPortalCipher();
        if (localPortalCipher == null)
        {
          localPortalCipher = new PortalCipher();
          localPortalShared.setPortalCipher(localPortalCipher);
        }
        localPortalCipher.setContext(MyApplication.a());
        if (!localPortalCipher.isInitial())
          localPortalCipher.zsmInitial();
        localPortalCipher.setUser(m.b(MainUiActivity.a, "UID", ""));
        localPortalCipher.getZsmInfo();
        arrayOfByte = localPortalCipher.zsmEncrypt(this.d.array());
      }
      this.a += arrayOfByte.length;
    }
    ByteBuffer localByteBuffer = ByteBuffer.allocate(4 + this.a);
    localByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    localByteBuffer.putShort((short)this.a);
    localByteBuffer.put((byte)(0xFF & this.b));
    localByteBuffer.put((byte)(0xFF & this.c));
    localByteBuffer.order(ByteOrder.BIG_ENDIAN);
    localByteBuffer.put(arrayOfByte);
    return localByteBuffer.array();
  }

  public short b()
  {
    if ((this.d != null) && (this.d.array().length >= 2))
    {
      this.d.position(0);
      this.d.order(ByteOrder.LITTLE_ENDIAN);
      short s = this.d.getShort();
      this.d.order(ByteOrder.BIG_ENDIAN);
      return s;
    }
    return 0;
  }

  public short c()
  {
    if ((this.d != null) && (this.d.array().length >= 4))
    {
      this.d.position(2);
      this.d.order(ByteOrder.LITTLE_ENDIAN);
      short s = this.d.getShort();
      this.d.order(ByteOrder.BIG_ENDIAN);
      return s;
    }
    return 0;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.d.a
 * JD-Core Version:    0.6.1
 */