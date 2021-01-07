package org.apache.commons.httpclient.protocol;

import java.io.IOException;
import java.net.Socket;

public final class ControllerThreadSocketFactory
{
  // ERROR //
  public static Socket createSocket(SocketTask paramSocketTask, int paramInt)
  {
    // Byte code:
    //   0: iload_1
    //   1: i2l
    //   2: lstore_2
    //   3: aload_0
    //   4: lload_2
    //   5: invokestatic 18	org/apache/commons/httpclient/util/TimeoutController:execute	(Ljava/lang/Runnable;J)V
    //   8: aload_0
    //   9: invokevirtual 24	org/apache/commons/httpclient/protocol/ControllerThreadSocketFactory$SocketTask:getSocket	()Ljava/net/Socket;
    //   12: astore 8
    //   14: aload_0
    //   15: invokestatic 28	org/apache/commons/httpclient/protocol/ControllerThreadSocketFactory$SocketTask:access$000	(Lorg/apache/commons/httpclient/protocol/ControllerThreadSocketFactory$SocketTask;)Ljava/io/IOException;
    //   18: ifnull +8 -> 26
    //   21: aload_0
    //   22: invokestatic 28	org/apache/commons/httpclient/protocol/ControllerThreadSocketFactory$SocketTask:access$000	(Lorg/apache/commons/httpclient/protocol/ControllerThreadSocketFactory$SocketTask;)Ljava/io/IOException;
    //   25: athrow
    //   26: aload 8
    //   28: areturn
    //   29: new 30	java/lang/StringBuffer
    //   32: dup
    //   33: invokespecial 31	java/lang/StringBuffer:<init>	()V
    //   36: astore 4
    //   38: aload 4
    //   40: ldc 33
    //   42: invokevirtual 37	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   45: pop
    //   46: aload 4
    //   48: iload_1
    //   49: invokevirtual 40	java/lang/StringBuffer:append	(I)Ljava/lang/StringBuffer;
    //   52: pop
    //   53: aload 4
    //   55: ldc 42
    //   57: invokevirtual 37	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   60: pop
    //   61: new 44	org/apache/commons/httpclient/ConnectTimeoutException
    //   64: dup
    //   65: aload 4
    //   67: invokevirtual 48	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   70: invokespecial 51	org/apache/commons/httpclient/ConnectTimeoutException:<init>	(Ljava/lang/String;)V
    //   73: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   3	8	29	org/apache/commons/httpclient/util/TimeoutController$TimeoutException
  }

  // ERROR //
  public static Socket createSocket(ProtocolSocketFactory paramProtocolSocketFactory, java.lang.String paramString, int paramInt1, java.net.InetAddress paramInetAddress, int paramInt2, int paramInt3)
  {
    // Byte code:
    //   0: new 54	org/apache/commons/httpclient/protocol/ControllerThreadSocketFactory$1
    //   3: dup
    //   4: aload_0
    //   5: aload_1
    //   6: iload_2
    //   7: aload_3
    //   8: iload 4
    //   10: invokespecial 57	org/apache/commons/httpclient/protocol/ControllerThreadSocketFactory$1:<init>	(Lorg/apache/commons/httpclient/protocol/ProtocolSocketFactory;Ljava/lang/String;ILjava/net/InetAddress;I)V
    //   13: astore 6
    //   15: iload 5
    //   17: i2l
    //   18: lstore 7
    //   20: aload 6
    //   22: lload 7
    //   24: invokestatic 18	org/apache/commons/httpclient/util/TimeoutController:execute	(Ljava/lang/Runnable;J)V
    //   27: aload 6
    //   29: invokevirtual 24	org/apache/commons/httpclient/protocol/ControllerThreadSocketFactory$SocketTask:getSocket	()Ljava/net/Socket;
    //   32: astore 13
    //   34: aload 6
    //   36: invokestatic 28	org/apache/commons/httpclient/protocol/ControllerThreadSocketFactory$SocketTask:access$000	(Lorg/apache/commons/httpclient/protocol/ControllerThreadSocketFactory$SocketTask;)Ljava/io/IOException;
    //   39: ifnull +9 -> 48
    //   42: aload 6
    //   44: invokestatic 28	org/apache/commons/httpclient/protocol/ControllerThreadSocketFactory$SocketTask:access$000	(Lorg/apache/commons/httpclient/protocol/ControllerThreadSocketFactory$SocketTask;)Ljava/io/IOException;
    //   47: athrow
    //   48: aload 13
    //   50: areturn
    //   51: new 30	java/lang/StringBuffer
    //   54: dup
    //   55: invokespecial 31	java/lang/StringBuffer:<init>	()V
    //   58: astore 9
    //   60: aload 9
    //   62: ldc 33
    //   64: invokevirtual 37	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   67: pop
    //   68: aload 9
    //   70: iload 5
    //   72: invokevirtual 40	java/lang/StringBuffer:append	(I)Ljava/lang/StringBuffer;
    //   75: pop
    //   76: aload 9
    //   78: ldc 42
    //   80: invokevirtual 37	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   83: pop
    //   84: new 44	org/apache/commons/httpclient/ConnectTimeoutException
    //   87: dup
    //   88: aload 9
    //   90: invokevirtual 48	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   93: invokespecial 51	org/apache/commons/httpclient/ConnectTimeoutException:<init>	(Ljava/lang/String;)V
    //   96: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   20	27	51	org/apache/commons/httpclient/util/TimeoutController$TimeoutException
  }

  public static abstract class SocketTask
    implements Runnable
  {
    private IOException exception;
    private Socket socket;

    public abstract void doit();

    protected Socket getSocket()
    {
      return this.socket;
    }

    public void run()
    {
      try
      {
        doit();
      }
      catch (IOException localIOException)
      {
        this.exception = localIOException;
      }
    }

    protected void setSocket(Socket paramSocket)
    {
      this.socket = paramSocket;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.protocol.ControllerThreadSocketFactory
 * JD-Core Version:    0.6.1
 */