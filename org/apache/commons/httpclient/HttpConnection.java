package org.apache.commons.httpclient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.httpclient.util.ExceptionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpConnection
{
  private static final byte[] CRLF = { 13, 10 };
  private static final Log LOG = LogFactory.getLog(localClass);
  static Class class$org$apache$commons$httpclient$HttpConnection;
  private String hostName = null;
  private HttpConnectionManager httpConnectionManager;
  private InputStream inputStream = null;
  protected boolean isOpen = false;
  private InputStream lastResponseInputStream = null;
  private InetAddress localAddress;
  private boolean locked = false;
  private OutputStream outputStream = null;
  private HttpConnectionParams params = new HttpConnectionParams();
  private int portNumber = -1;
  private Protocol protocolInUse;
  private String proxyHostName = null;
  private int proxyPortNumber = -1;
  private Socket socket = null;
  private boolean tunnelEstablished = false;
  private boolean usingSecureSocket = false;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$HttpConnection == null)
    {
      localClass = class$("org.apache.commons.httpclient.HttpConnection");
      class$org$apache$commons$httpclient$HttpConnection = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$HttpConnection;
    }
  }

  public HttpConnection(String paramString, int paramInt)
  {
    this(null, -1, paramString, null, paramInt, Protocol.getProtocol("http"));
  }

  public HttpConnection(String paramString1, int paramInt1, String paramString2, int paramInt2)
  {
    this(paramString1, paramInt1, paramString2, null, paramInt2, Protocol.getProtocol("http"));
  }

  public HttpConnection(String paramString1, int paramInt1, String paramString2, int paramInt2, Protocol paramProtocol)
  {
    if (paramString2 == null)
      throw new IllegalArgumentException("host parameter is null");
    if (paramProtocol == null)
      throw new IllegalArgumentException("protocol is null");
    this.proxyHostName = paramString1;
    this.proxyPortNumber = paramInt1;
    this.hostName = paramString2;
    this.portNumber = paramProtocol.resolvePort(paramInt2);
    this.protocolInUse = paramProtocol;
  }

  public HttpConnection(String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2, Protocol paramProtocol)
  {
    this(paramString1, paramInt1, paramString2, paramInt2, paramProtocol);
  }

  public HttpConnection(String paramString, int paramInt, Protocol paramProtocol)
  {
    this(null, -1, paramString, null, paramInt, paramProtocol);
  }

  public HttpConnection(String paramString1, String paramString2, int paramInt, Protocol paramProtocol)
  {
    this(null, -1, paramString1, paramString2, paramInt, paramProtocol);
  }

  public HttpConnection(HostConfiguration paramHostConfiguration)
  {
    this(paramHostConfiguration.getProxyHost(), paramHostConfiguration.getProxyPort(), paramHostConfiguration.getHost(), paramHostConfiguration.getPort(), paramHostConfiguration.getProtocol());
    this.localAddress = paramHostConfiguration.getLocalAddress();
  }

  static Class class$(String paramString)
  {
    try
    {
      Class localClass = Class.forName(paramString);
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
    }
  }

  protected void assertNotOpen()
  {
    if (this.isOpen)
      throw new IllegalStateException("Connection is open");
  }

  protected void assertOpen()
  {
    if (!this.isOpen)
      throw new IllegalStateException("Connection is not open");
  }

  public void close()
  {
    LOG.trace("enter HttpConnection.close()");
    closeSocketAndStreams();
  }

  public boolean closeIfStale()
  {
    if ((this.isOpen) && (isStale()))
    {
      LOG.debug("Connection is stale, closing...");
      close();
      return true;
    }
    return false;
  }

  protected void closeSocketAndStreams()
  {
    LOG.trace("enter HttpConnection.closeSockedAndStreams()");
    this.isOpen = false;
    this.lastResponseInputStream = null;
    if (this.outputStream != null)
    {
      OutputStream localOutputStream = this.outputStream;
      this.outputStream = null;
      try
      {
        localOutputStream.close();
      }
      catch (Exception localException3)
      {
        LOG.debug("Exception caught when closing output", localException3);
      }
    }
    if (this.inputStream != null)
    {
      InputStream localInputStream = this.inputStream;
      this.inputStream = null;
      try
      {
        localInputStream.close();
      }
      catch (Exception localException2)
      {
        LOG.debug("Exception caught when closing input", localException2);
      }
    }
    if (this.socket != null)
    {
      Socket localSocket = this.socket;
      this.socket = null;
      try
      {
        localSocket.close();
      }
      catch (Exception localException1)
      {
        LOG.debug("Exception caught when closing socket", localException1);
      }
    }
    this.tunnelEstablished = false;
    this.usingSecureSocket = false;
  }

  public void flushRequestOutputStream()
  {
    LOG.trace("enter HttpConnection.flushRequestOutputStream()");
    assertOpen();
    this.outputStream.flush();
  }

  public String getHost()
  {
    return this.hostName;
  }

  public HttpConnectionManager getHttpConnectionManager()
  {
    return this.httpConnectionManager;
  }

  public InputStream getLastResponseInputStream()
  {
    return this.lastResponseInputStream;
  }

  public InetAddress getLocalAddress()
  {
    return this.localAddress;
  }

  public HttpConnectionParams getParams()
  {
    return this.params;
  }

  public int getPort()
  {
    if (this.portNumber < 0)
    {
      int i;
      if (isSecure())
        i = 443;
      else
        i = 80;
      return i;
    }
    return this.portNumber;
  }

  public Protocol getProtocol()
  {
    return this.protocolInUse;
  }

  public String getProxyHost()
  {
    return this.proxyHostName;
  }

  public int getProxyPort()
  {
    return this.proxyPortNumber;
  }

  public OutputStream getRequestOutputStream()
  {
    LOG.trace("enter HttpConnection.getRequestOutputStream()");
    assertOpen();
    Object localObject = this.outputStream;
    if (Wire.CONTENT_WIRE.enabled())
      localObject = new WireLogOutputStream((OutputStream)localObject, Wire.CONTENT_WIRE);
    return localObject;
  }

  public InputStream getResponseInputStream()
  {
    LOG.trace("enter HttpConnection.getResponseInputStream()");
    assertOpen();
    return this.inputStream;
  }

  public int getSendBufferSize()
  {
    if (this.socket == null)
      return -1;
    return this.socket.getSendBufferSize();
  }

  public int getSoTimeout()
  {
    return this.params.getSoTimeout();
  }

  protected Socket getSocket()
  {
    return this.socket;
  }

  public String getVirtualHost()
  {
    return this.hostName;
  }

  protected boolean isLocked()
  {
    return this.locked;
  }

  public boolean isOpen()
  {
    return this.isOpen;
  }

  public boolean isProxied()
  {
    boolean bool;
    if ((this.proxyHostName != null) && (this.proxyPortNumber > 0))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public boolean isResponseAvailable()
  {
    LOG.trace("enter HttpConnection.isResponseAvailable()");
    if (this.isOpen)
    {
      int i = this.inputStream.available();
      boolean bool = false;
      if (i > 0)
        bool = true;
      return bool;
    }
    return false;
  }

  // ERROR //
  public boolean isResponseAvailable(int paramInt)
  {
    // Byte code:
    //   0: getstatic 58	org/apache/commons/httpclient/HttpConnection:LOG	Lorg/apache/commons/logging/Log;
    //   3: ldc_w 281
    //   6: invokeinterface 180 2 0
    //   11: aload_0
    //   12: invokevirtual 222	org/apache/commons/httpclient/HttpConnection:assertOpen	()V
    //   15: aload_0
    //   16: getfield 87	org/apache/commons/httpclient/HttpConnection:inputStream	Ljava/io/InputStream;
    //   19: invokevirtual 274	java/io/InputStream:available	()I
    //   22: istore_2
    //   23: iconst_1
    //   24: istore_3
    //   25: iconst_0
    //   26: istore 4
    //   28: iload_2
    //   29: ifle +9 -> 38
    //   32: iconst_1
    //   33: istore 4
    //   35: goto +199 -> 234
    //   38: aload_0
    //   39: getfield 85	org/apache/commons/httpclient/HttpConnection:socket	Ljava/net/Socket;
    //   42: iload_1
    //   43: invokevirtual 285	java/net/Socket:setSoTimeout	(I)V
    //   46: aload_0
    //   47: getfield 87	org/apache/commons/httpclient/HttpConnection:inputStream	Ljava/io/InputStream;
    //   50: iload_3
    //   51: invokevirtual 288	java/io/InputStream:mark	(I)V
    //   54: aload_0
    //   55: getfield 87	org/apache/commons/httpclient/HttpConnection:inputStream	Ljava/io/InputStream;
    //   58: invokevirtual 291	java/io/InputStream:read	()I
    //   61: iconst_m1
    //   62: if_icmpeq +24 -> 86
    //   65: aload_0
    //   66: getfield 87	org/apache/commons/httpclient/HttpConnection:inputStream	Ljava/io/InputStream;
    //   69: invokevirtual 294	java/io/InputStream:reset	()V
    //   72: getstatic 58	org/apache/commons/httpclient/HttpConnection:LOG	Lorg/apache/commons/logging/Log;
    //   75: ldc_w 296
    //   78: invokeinterface 193 2 0
    //   83: goto +16 -> 99
    //   86: getstatic 58	org/apache/commons/httpclient/HttpConnection:LOG	Lorg/apache/commons/logging/Log;
    //   89: ldc_w 298
    //   92: invokeinterface 193 2 0
    //   97: iconst_0
    //   98: istore_3
    //   99: aload_0
    //   100: getfield 85	org/apache/commons/httpclient/HttpConnection:socket	Ljava/net/Socket;
    //   103: aload_0
    //   104: getfield 98	org/apache/commons/httpclient/HttpConnection:params	Lorg/apache/commons/httpclient/params/HttpConnectionParams;
    //   107: invokevirtual 263	org/apache/commons/httpclient/params/HttpConnectionParams:getSoTimeout	()I
    //   110: invokevirtual 285	java/net/Socket:setSoTimeout	(I)V
    //   113: iload_3
    //   114: istore 4
    //   116: goto +118 -> 234
    //   119: astore 12
    //   121: goto +116 -> 237
    //   124: astore 5
    //   126: aload 5
    //   128: invokestatic 304	org/apache/commons/httpclient/util/ExceptionUtil:isSocketTimeoutException	(Ljava/io/InterruptedIOException;)Z
    //   131: ifne +6 -> 137
    //   134: aload 5
    //   136: athrow
    //   137: getstatic 58	org/apache/commons/httpclient/HttpConnection:LOG	Lorg/apache/commons/logging/Log;
    //   140: invokeinterface 307 1 0
    //   145: ifeq +54 -> 199
    //   148: getstatic 58	org/apache/commons/httpclient/HttpConnection:LOG	Lorg/apache/commons/logging/Log;
    //   151: astore 7
    //   153: new 309	java/lang/StringBuffer
    //   156: dup
    //   157: invokespecial 310	java/lang/StringBuffer:<init>	()V
    //   160: astore 8
    //   162: aload 8
    //   164: ldc_w 312
    //   167: invokevirtual 316	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   170: pop
    //   171: aload 8
    //   173: iload_1
    //   174: invokevirtual 319	java/lang/StringBuffer:append	(I)Ljava/lang/StringBuffer;
    //   177: pop
    //   178: aload 8
    //   180: ldc_w 321
    //   183: invokevirtual 316	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   186: pop
    //   187: aload 7
    //   189: aload 8
    //   191: invokevirtual 324	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   194: invokeinterface 193 2 0
    //   199: aload_0
    //   200: getfield 85	org/apache/commons/httpclient/HttpConnection:socket	Ljava/net/Socket;
    //   203: aload_0
    //   204: getfield 98	org/apache/commons/httpclient/HttpConnection:params	Lorg/apache/commons/httpclient/params/HttpConnectionParams;
    //   207: invokevirtual 263	org/apache/commons/httpclient/params/HttpConnectionParams:getSoTimeout	()I
    //   210: invokevirtual 285	java/net/Socket:setSoTimeout	(I)V
    //   213: iconst_0
    //   214: istore 4
    //   216: goto +18 -> 234
    //   219: astore 6
    //   221: getstatic 58	org/apache/commons/httpclient/HttpConnection:LOG	Lorg/apache/commons/logging/Log;
    //   224: ldc_w 326
    //   227: aload 6
    //   229: invokeinterface 207 3 0
    //   234: iload 4
    //   236: ireturn
    //   237: aload_0
    //   238: getfield 85	org/apache/commons/httpclient/HttpConnection:socket	Ljava/net/Socket;
    //   241: aload_0
    //   242: getfield 98	org/apache/commons/httpclient/HttpConnection:params	Lorg/apache/commons/httpclient/params/HttpConnectionParams;
    //   245: invokevirtual 263	org/apache/commons/httpclient/params/HttpConnectionParams:getSoTimeout	()I
    //   248: invokevirtual 285	java/net/Socket:setSoTimeout	(I)V
    //   251: goto +18 -> 269
    //   254: astore 13
    //   256: getstatic 58	org/apache/commons/httpclient/HttpConnection:LOG	Lorg/apache/commons/logging/Log;
    //   259: ldc_w 326
    //   262: aload 13
    //   264: invokeinterface 207 3 0
    //   269: aload 12
    //   271: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   38	97	119	finally
    //   126	199	119	finally
    //   38	97	124	java/io/InterruptedIOException
    //   99	113	219	java/io/IOException
    //   199	213	219	java/io/IOException
    //   237	251	254	java/io/IOException
  }

  public boolean isSecure()
  {
    return this.protocolInUse.isSecure();
  }

  protected boolean isStale()
  {
    boolean bool = this.isOpen;
    int i = 1;
    if (bool)
    {
      int j = 0;
      try
      {
        int k = this.inputStream.available();
        if (k <= 0);
        try
        {
          this.socket.setSoTimeout(i);
          this.inputStream.mark(i);
          int m = this.inputStream.read();
          j = 0;
          if (m == -1)
            j = 1;
          else
            this.inputStream.reset();
          this.socket.setSoTimeout(this.params.getSoTimeout());
        }
        finally
        {
          this.socket.setSoTimeout(this.params.getSoTimeout());
        }
      }
      catch (IOException localIOException)
      {
        LOG.debug("An error occurred while reading from the socket, is appears to be stale", localIOException);
      }
      catch (InterruptedIOException localInterruptedIOException)
      {
        if (!ExceptionUtil.isSocketTimeoutException(localInterruptedIOException))
          throw localInterruptedIOException;
        i = j;
      }
    }
    return i;
  }

  public boolean isStaleCheckingEnabled()
  {
    return this.params.isStaleCheckingEnabled();
  }

  public boolean isTransparent()
  {
    boolean bool;
    if ((isProxied()) && (!this.tunnelEstablished))
      bool = false;
    else
      bool = true;
    return bool;
  }

  public void open()
  {
    LOG.trace("enter HttpConnection.open()");
    if (this.proxyHostName == null);
    String str2;
    for (String str1 = this.hostName; ; str1 = this.proxyHostName)
    {
      str2 = str1;
      break;
    }
    if (this.proxyHostName == null);
    int j;
    for (int i = this.portNumber; ; i = this.proxyPortNumber)
    {
      j = i;
      break;
    }
    assertNotOpen();
    if (LOG.isDebugEnabled())
    {
      Log localLog = LOG;
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("Open connection to ");
      localStringBuffer.append(str2);
      localStringBuffer.append(":");
      localStringBuffer.append(j);
      localLog.debug(localStringBuffer.toString());
    }
    while (true)
    {
      int i3;
      try
      {
        if (this.socket == null)
        {
          if ((isSecure()) && (!isProxied()))
          {
            bool2 = true;
            this.usingSecureSocket = bool2;
            ProtocolSocketFactory localProtocolSocketFactory;
            if ((isSecure()) && (isProxied()))
              localProtocolSocketFactory = Protocol.getProtocol("http").getSocketFactory();
            else
              localProtocolSocketFactory = this.protocolInUse.getSocketFactory();
            this.socket = localProtocolSocketFactory.createSocket(str2, j, this.localAddress, 0, this.params);
          }
        }
        else
        {
          this.socket.setTcpNoDelay(this.params.getTcpNoDelay());
          this.socket.setSoTimeout(this.params.getSoTimeout());
          int k = this.params.getLinger();
          if (k >= 0)
          {
            Socket localSocket = this.socket;
            boolean bool1 = false;
            if (k > 0)
              bool1 = true;
            localSocket.setSoLinger(bool1, k);
          }
          int m = this.params.getSendBufferSize();
          if (m >= 0)
            this.socket.setSendBufferSize(m);
          int n = this.params.getReceiveBufferSize();
          if (n >= 0)
            this.socket.setReceiveBufferSize(n);
          i1 = this.socket.getSendBufferSize();
          i2 = 2048;
          if ((i1 > i2) || (i1 <= 0))
            break label456;
          i3 = this.socket.getReceiveBufferSize();
          if (i3 <= i2)
            if (i3 > 0)
              break label464;
          this.inputStream = new BufferedInputStream(this.socket.getInputStream(), i2);
          this.outputStream = new BufferedOutputStream(this.socket.getOutputStream(), i1);
          this.isOpen = true;
          return;
        }
      }
      catch (IOException localIOException)
      {
        closeSocketAndStreams();
        throw localIOException;
      }
      boolean bool2 = false;
      continue;
      label456: int i1 = 2048;
      continue;
      label464: int i2 = i3;
    }
  }

  public void print(String paramString)
  {
    LOG.trace("enter HttpConnection.print(String)");
    write(EncodingUtil.getBytes(paramString, "ISO-8859-1"));
  }

  public void print(String paramString1, String paramString2)
  {
    LOG.trace("enter HttpConnection.print(String)");
    write(EncodingUtil.getBytes(paramString1, paramString2));
  }

  public void printLine()
  {
    LOG.trace("enter HttpConnection.printLine()");
    writeLine();
  }

  public void printLine(String paramString)
  {
    LOG.trace("enter HttpConnection.printLine(String)");
    writeLine(EncodingUtil.getBytes(paramString, "ISO-8859-1"));
  }

  public void printLine(String paramString1, String paramString2)
  {
    LOG.trace("enter HttpConnection.printLine(String)");
    writeLine(EncodingUtil.getBytes(paramString1, paramString2));
  }

  public String readLine()
  {
    LOG.trace("enter HttpConnection.readLine()");
    assertOpen();
    return HttpParser.readLine(this.inputStream);
  }

  public String readLine(String paramString)
  {
    LOG.trace("enter HttpConnection.readLine()");
    assertOpen();
    return HttpParser.readLine(this.inputStream, paramString);
  }

  public void releaseConnection()
  {
    LOG.trace("enter HttpConnection.releaseConnection()");
    if (this.locked)
    {
      LOG.debug("Connection is locked.  Call to releaseConnection() ignored.");
    }
    else if (this.httpConnectionManager != null)
    {
      LOG.debug("Releasing connection back to connection manager.");
      this.httpConnectionManager.releaseConnection(this);
    }
    else
    {
      LOG.warn("HttpConnectionManager is null.  Connection cannot be released.");
    }
  }

  public void setConnectionTimeout(int paramInt)
  {
    this.params.setConnectionTimeout(paramInt);
  }

  public void setHost(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("host parameter is null");
    assertNotOpen();
    this.hostName = paramString;
  }

  public void setHttpConnectionManager(HttpConnectionManager paramHttpConnectionManager)
  {
    this.httpConnectionManager = paramHttpConnectionManager;
  }

  public void setLastResponseInputStream(InputStream paramInputStream)
  {
    this.lastResponseInputStream = paramInputStream;
  }

  public void setLocalAddress(InetAddress paramInetAddress)
  {
    assertNotOpen();
    this.localAddress = paramInetAddress;
  }

  protected void setLocked(boolean paramBoolean)
  {
    this.locked = paramBoolean;
  }

  public void setParams(HttpConnectionParams paramHttpConnectionParams)
  {
    if (paramHttpConnectionParams == null)
      throw new IllegalArgumentException("Parameters may not be null");
    this.params = paramHttpConnectionParams;
  }

  public void setPort(int paramInt)
  {
    assertNotOpen();
    this.portNumber = paramInt;
  }

  public void setProtocol(Protocol paramProtocol)
  {
    assertNotOpen();
    if (paramProtocol == null)
      throw new IllegalArgumentException("protocol is null");
    this.protocolInUse = paramProtocol;
  }

  public void setProxyHost(String paramString)
  {
    assertNotOpen();
    this.proxyHostName = paramString;
  }

  public void setProxyPort(int paramInt)
  {
    assertNotOpen();
    this.proxyPortNumber = paramInt;
  }

  public void setSendBufferSize(int paramInt)
  {
    this.params.setSendBufferSize(paramInt);
  }

  public void setSoTimeout(int paramInt)
  {
    this.params.setSoTimeout(paramInt);
    if (this.socket != null)
      this.socket.setSoTimeout(paramInt);
  }

  public void setSocketTimeout(int paramInt)
  {
    assertOpen();
    if (this.socket != null)
      this.socket.setSoTimeout(paramInt);
  }

  public void setStaleCheckingEnabled(boolean paramBoolean)
  {
    this.params.setStaleCheckingEnabled(paramBoolean);
  }

  public void setVirtualHost(String paramString)
  {
    assertNotOpen();
  }

  public void shutdownOutput()
  {
    LOG.trace("enter HttpConnection.shutdownOutput()");
    try
    {
      Class[] arrayOfClass = new Class[0];
      Method localMethod = this.socket.getClass().getMethod("shutdownOutput", arrayOfClass);
      Object[] arrayOfObject = new Object[0];
      localMethod.invoke(this.socket, arrayOfObject);
    }
    catch (Exception localException)
    {
      LOG.debug("Unexpected Exception caught", localException);
    }
  }

  public void tunnelCreated()
  {
    LOG.trace("enter HttpConnection.tunnelCreated()");
    if ((isSecure()) && (isProxied()))
    {
      if (this.usingSecureSocket)
        throw new IllegalStateException("Already using a secure socket");
      if (LOG.isDebugEnabled())
      {
        Log localLog = LOG;
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append("Secure tunnel to ");
        localStringBuffer.append(this.hostName);
        localStringBuffer.append(":");
        localStringBuffer.append(this.portNumber);
        localLog.debug(localStringBuffer.toString());
      }
      this.socket = ((SecureProtocolSocketFactory)this.protocolInUse.getSocketFactory()).createSocket(this.socket, this.hostName, this.portNumber, true);
      int i = this.params.getSendBufferSize();
      if (i >= 0)
        this.socket.setSendBufferSize(i);
      int j = this.params.getReceiveBufferSize();
      if (j >= 0)
        this.socket.setReceiveBufferSize(j);
      int k = this.socket.getSendBufferSize();
      int m = 2048;
      if (k > m)
        k = 2048;
      int n = this.socket.getReceiveBufferSize();
      if (n <= m)
        m = n;
      this.inputStream = new BufferedInputStream(this.socket.getInputStream(), m);
      this.outputStream = new BufferedOutputStream(this.socket.getOutputStream(), k);
      this.usingSecureSocket = true;
      this.tunnelEstablished = true;
      return;
    }
    throw new IllegalStateException("Connection must be secure and proxied to use this feature");
  }

  public void write(byte[] paramArrayOfByte)
  {
    LOG.trace("enter HttpConnection.write(byte[])");
    write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    LOG.trace("enter HttpConnection.write(byte[], int, int)");
    if (paramInt1 < 0)
      throw new IllegalArgumentException("Array offset may not be negative");
    if (paramInt2 < 0)
      throw new IllegalArgumentException("Array length may not be negative");
    if (paramInt1 + paramInt2 > paramArrayOfByte.length)
      throw new IllegalArgumentException("Given offset and length exceed the array length");
    assertOpen();
    this.outputStream.write(paramArrayOfByte, paramInt1, paramInt2);
  }

  public void writeLine()
  {
    LOG.trace("enter HttpConnection.writeLine()");
    write(CRLF);
  }

  public void writeLine(byte[] paramArrayOfByte)
  {
    LOG.trace("enter HttpConnection.writeLine(byte[])");
    write(paramArrayOfByte);
    writeLine();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpConnection
 * JD-Core Version:    0.6.1
 */