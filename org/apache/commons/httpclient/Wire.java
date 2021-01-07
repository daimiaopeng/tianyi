package org.apache.commons.httpclient;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

class Wire
{
  public static Wire CONTENT_WIRE = new Wire(LogFactory.getLog("httpclient.wire.content"));
  public static Wire HEADER_WIRE = new Wire(LogFactory.getLog("httpclient.wire.header"));
  private Log log;

  private Wire(Log paramLog)
  {
    this.log = paramLog;
  }

  private void wire(String paramString, InputStream paramInputStream)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    while (true)
    {
      int i = paramInputStream.read();
      if (i == -1)
        break;
      if (i == 13)
      {
        localStringBuffer.append("[\\r]");
      }
      else if (i == 10)
      {
        localStringBuffer.append("[\\n]\"");
        localStringBuffer.insert(0, "\"");
        localStringBuffer.insert(0, paramString);
        this.log.debug(localStringBuffer.toString());
        localStringBuffer.setLength(0);
      }
      else if ((i >= 32) && (i <= 127))
      {
        localStringBuffer.append((char)i);
      }
      else
      {
        localStringBuffer.append("[0x");
        localStringBuffer.append(Integer.toHexString(i));
        localStringBuffer.append("]");
      }
    }
    if (localStringBuffer.length() > 0)
    {
      localStringBuffer.append("\"");
      localStringBuffer.insert(0, "\"");
      localStringBuffer.insert(0, paramString);
      this.log.debug(localStringBuffer.toString());
    }
  }

  public boolean enabled()
  {
    return this.log.isDebugEnabled();
  }

  public void input(int paramInt)
  {
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = (byte)paramInt;
    input(arrayOfByte);
  }

  public void input(InputStream paramInputStream)
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException("Input may not be null");
    wire("<< ", paramInputStream);
  }

  public void input(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Input may not be null");
    input(paramString.getBytes());
  }

  public void input(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Input may not be null");
    wire("<< ", new ByteArrayInputStream(paramArrayOfByte));
  }

  public void input(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Input may not be null");
    wire("<< ", new ByteArrayInputStream(paramArrayOfByte, paramInt1, paramInt2));
  }

  public void output(int paramInt)
  {
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = (byte)paramInt;
    output(arrayOfByte);
  }

  public void output(InputStream paramInputStream)
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException("Output may not be null");
    wire(">> ", paramInputStream);
  }

  public void output(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Output may not be null");
    output(paramString.getBytes());
  }

  public void output(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Output may not be null");
    wire(">> ", new ByteArrayInputStream(paramArrayOfByte));
  }

  public void output(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Output may not be null");
    wire(">> ", new ByteArrayInputStream(paramArrayOfByte, paramInt1, paramInt2));
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.Wire
 * JD-Core Version:    0.6.1
 */