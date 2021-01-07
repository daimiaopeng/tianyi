package com.cndatacom.d;

import com.cndatacom.e.j;
import com.cndatacom.e.k;
import java.io.ByteArrayOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class b
{
  ByteArrayOutputStream a = new ByteArrayOutputStream();
  private String b;
  private int c;
  private DatagramSocket d;
  private c e;
  private boolean f = true;
  private int g = 0;

  public b(String paramString, int paramInt)
  {
    this.b = paramString;
    this.c = paramInt;
    try
    {
      this.d = new DatagramSocket();
    }
    catch (Exception localException)
    {
      j.a("TrineaAndroidCommon", localException, "CDCMessager Exception");
    }
  }

  private void a(byte[] paramArrayOfByte, int paramInt)
  {
    this.a.write(paramArrayOfByte, this.g, paramInt);
    this.g = (paramInt + this.g);
    do
    {
      a locala = new a();
      int i = locala.a(this.a.toByteArray(), this.g);
      if (i > 0)
        this.e.a(locala);
      this.g -= i;
      if (this.g == 0)
      {
        this.a.reset();
        break;
      }
      byte[] arrayOfByte = (byte[])this.a.toByteArray().clone();
      this.a.reset();
      this.a.write(arrayOfByte, i, this.g);
    }
    while (this.g > 0);
  }

  public void a()
  {
    this.f = false;
    if (this.d != null)
    {
      this.d.close();
      this.d = null;
    }
  }

  public void a(c paramc)
  {
    this.e = paramc;
    byte[] arrayOfByte = new byte[1024];
    DatagramPacket localDatagramPacket = new DatagramPacket(arrayOfByte, arrayOfByte.length);
    while (this.f)
      try
      {
        j.b("TrineaAndroidCommon", "CDCMessager receive start");
        this.d.receive(localDatagramPacket);
        if (localDatagramPacket.getLength() == 0)
          break;
        a(localDatagramPacket.getData(), localDatagramPacket.getLength());
        j.b("TrineaAndroidCommon", "CDCMessager receive continue");
      }
      catch (Exception localException)
      {
        j.a("TrineaAndroidCommon", localException, "CDCMessager sendMessage Exception");
      }
  }

  public void a(short paramShort1, short paramShort2, String paramString)
  {
    a locala = new a();
    locala.b = paramShort1;
    locala.c = paramShort2;
    locala.a(k.a(paramString));
    byte[] arrayOfByte = locala.a();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("CDCMessager sendMessage encrypt : ");
    localStringBuilder.append(paramShort1);
    localStringBuilder.append(" cmd : ");
    localStringBuilder.append(paramShort2);
    j.b("TrineaAndroidCommon", localStringBuilder.toString());
    try
    {
      InetSocketAddress localInetSocketAddress = new InetSocketAddress(InetAddress.getByName(this.b), this.c);
      DatagramPacket localDatagramPacket = new DatagramPacket(arrayOfByte, arrayOfByte.length, localInetSocketAddress);
      this.d.send(localDatagramPacket);
    }
    catch (Exception localException)
    {
      j.a("TrineaAndroidCommon", localException, "CDCMessager sendMessage Exception");
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.d.b
 * JD-Core Version:    0.6.1
 */