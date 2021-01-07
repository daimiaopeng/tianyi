package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.ExceptionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ChunkedInputStream extends InputStream
{
  private static final Log LOG = LogFactory.getLog(localClass);
  static Class class$org$apache$commons$httpclient$ChunkedInputStream;
  private boolean bof = true;
  private int chunkSize;
  private boolean closed = false;
  private boolean eof = false;
  private InputStream in;
  private HttpMethod method = null;
  private int pos;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$ChunkedInputStream == null)
    {
      localClass = class$("org.apache.commons.httpclient.ChunkedInputStream");
      class$org$apache$commons$httpclient$ChunkedInputStream = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$ChunkedInputStream;
    }
  }

  public ChunkedInputStream(InputStream paramInputStream)
  {
    this(paramInputStream, null);
  }

  public ChunkedInputStream(InputStream paramInputStream, HttpMethod paramHttpMethod)
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException("InputStream parameter may not be null");
    this.in = paramInputStream;
    this.method = paramHttpMethod;
    this.pos = 0;
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

  static void exhaustInputStream(InputStream paramInputStream)
  {
    byte[] arrayOfByte = new byte[1024];
    while (paramInputStream.read(arrayOfByte) >= 0);
  }

  // ERROR //
  private static int getChunkSizeFromInputStream(InputStream paramInputStream)
  {
    // Byte code:
    //   0: new 88	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: invokespecial 89	java/io/ByteArrayOutputStream:<init>	()V
    //   7: astore_1
    //   8: iconst_0
    //   9: istore_2
    //   10: iload_2
    //   11: iconst_m1
    //   12: if_icmpeq +156 -> 168
    //   15: aload_0
    //   16: invokevirtual 92	java/io/InputStream:read	()I
    //   19: istore 10
    //   21: iload 10
    //   23: iconst_m1
    //   24: if_icmpne +13 -> 37
    //   27: new 94	java/io/IOException
    //   30: dup
    //   31: ldc 96
    //   33: invokespecial 97	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   36: athrow
    //   37: iload_2
    //   38: tableswitch	default:+26 -> 64, 0:+97->135, 1:+75->113, 2:+36->74
    //   65: nop
    //   66: dadd
    //   67: dup
    //   68: ldc 101
    //   70: invokespecial 102	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   73: athrow
    //   74: iload 10
    //   76: bipush 34
    //   78: if_icmpeq +24 -> 102
    //   81: iload 10
    //   83: bipush 92
    //   85: if_icmpeq +6 -> 91
    //   88: goto +16 -> 104
    //   91: aload_1
    //   92: aload_0
    //   93: invokevirtual 92	java/io/InputStream:read	()I
    //   96: invokevirtual 106	java/io/ByteArrayOutputStream:write	(I)V
    //   99: goto -89 -> 10
    //   102: iconst_0
    //   103: istore_2
    //   104: aload_1
    //   105: iload 10
    //   107: invokevirtual 106	java/io/ByteArrayOutputStream:write	(I)V
    //   110: goto -100 -> 10
    //   113: iload 10
    //   115: bipush 10
    //   117: if_icmpne +8 -> 125
    //   120: iconst_m1
    //   121: istore_2
    //   122: goto -112 -> 10
    //   125: new 94	java/io/IOException
    //   128: dup
    //   129: ldc 108
    //   131: invokespecial 97	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   134: athrow
    //   135: iload 10
    //   137: bipush 13
    //   139: if_icmpeq +24 -> 163
    //   142: iload 10
    //   144: bipush 34
    //   146: if_icmpeq +6 -> 152
    //   149: goto +5 -> 154
    //   152: iconst_2
    //   153: istore_2
    //   154: aload_1
    //   155: iload 10
    //   157: invokevirtual 106	java/io/ByteArrayOutputStream:write	(I)V
    //   160: goto -150 -> 10
    //   163: iconst_1
    //   164: istore_2
    //   165: goto -155 -> 10
    //   168: aload_1
    //   169: invokevirtual 112	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   172: invokestatic 118	org/apache/commons/httpclient/util/EncodingUtil:getAsciiString	([B)Ljava/lang/String;
    //   175: astore_3
    //   176: aload_3
    //   177: bipush 59
    //   179: invokevirtual 124	java/lang/String:indexOf	(I)I
    //   182: istore 4
    //   184: iload 4
    //   186: ifle +11 -> 197
    //   189: aload_3
    //   190: iconst_0
    //   191: iload 4
    //   193: invokevirtual 128	java/lang/String:substring	(II)Ljava/lang/String;
    //   196: astore_3
    //   197: aload_3
    //   198: invokevirtual 131	java/lang/String:trim	()Ljava/lang/String;
    //   201: astore 5
    //   203: aload 5
    //   205: invokevirtual 131	java/lang/String:trim	()Ljava/lang/String;
    //   208: bipush 16
    //   210: invokestatic 137	java/lang/Integer:parseInt	(Ljava/lang/String;I)I
    //   213: istore 9
    //   215: iload 9
    //   217: ireturn
    //   218: new 139	java/lang/StringBuffer
    //   221: dup
    //   222: invokespecial 140	java/lang/StringBuffer:<init>	()V
    //   225: astore 6
    //   227: aload 6
    //   229: ldc 142
    //   231: invokevirtual 146	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   234: pop
    //   235: aload 6
    //   237: aload 5
    //   239: invokevirtual 146	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   242: pop
    //   243: new 94	java/io/IOException
    //   246: dup
    //   247: aload 6
    //   249: invokevirtual 149	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   252: invokespecial 97	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   255: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   203	215	218	java/lang/NumberFormatException
  }

  private void nextChunk()
  {
    if (!this.bof)
      readCRLF();
    this.chunkSize = getChunkSizeFromInputStream(this.in);
    this.bof = false;
    this.pos = 0;
    if (this.chunkSize == 0)
    {
      this.eof = true;
      parseTrailerHeaders();
    }
  }

  private void parseTrailerHeaders()
  {
    try
    {
      String str = "US-ASCII";
      if (this.method != null)
        str = this.method.getParams().getHttpElementCharset();
      Header[] arrayOfHeader = HttpParser.parseHeaders(this.in, str);
      if (this.method != null)
        for (int i = 0; i < arrayOfHeader.length; i++)
          this.method.addResponseFooter(arrayOfHeader[i]);
      return;
    }
    catch (HttpException localHttpException)
    {
      LOG.error("Error parsing trailer headers", localHttpException);
      IOException localIOException = new IOException(localHttpException.getMessage());
      ExceptionUtil.initCause(localIOException, localHttpException);
      throw localIOException;
    }
  }

  private void readCRLF()
  {
    int i = this.in.read();
    int j = this.in.read();
    if ((i == 13) && (j == 10))
      return;
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("CRLF expected at end of chunk: ");
    localStringBuffer.append(i);
    localStringBuffer.append("/");
    localStringBuffer.append(j);
    throw new IOException(localStringBuffer.toString());
  }

  public void close()
  {
    if (!this.closed)
      try
      {
        if (!this.eof)
          exhaustInputStream(this);
        this.eof = true;
        this.closed = true;
      }
      finally
      {
        this.eof = true;
        this.closed = true;
      }
  }

  public int read()
  {
    if (this.closed)
      throw new IOException("Attempted read from closed stream.");
    if (this.eof)
      return -1;
    if (this.pos >= this.chunkSize)
    {
      nextChunk();
      if (this.eof)
        return -1;
    }
    this.pos = (1 + this.pos);
    return this.in.read();
  }

  public int read(byte[] paramArrayOfByte)
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (this.closed)
      throw new IOException("Attempted read from closed stream.");
    if (this.eof)
      return -1;
    if (this.pos >= this.chunkSize)
    {
      nextChunk();
      if (this.eof)
        return -1;
    }
    int i = Math.min(paramInt2, this.chunkSize - this.pos);
    int j = this.in.read(paramArrayOfByte, paramInt1, i);
    this.pos = (j + this.pos);
    return j;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.ChunkedInputStream
 * JD-Core Version:    0.6.1
 */