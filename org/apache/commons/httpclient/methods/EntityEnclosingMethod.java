package org.apache.commons.httpclient.methods;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import org.apache.commons.httpclient.ChunkedOutputStream;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.ProtocolException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class EntityEnclosingMethod extends ExpectContinueMethod
{
  public static final long CONTENT_LENGTH_AUTO = -2L;
  public static final long CONTENT_LENGTH_CHUNKED = -1L;
  private static final Log LOG = LogFactory.getLog(localClass);
  static Class class$org$apache$commons$httpclient$methods$EntityEnclosingMethod;
  private boolean chunked = false;
  private int repeatCount = 0;
  private long requestContentLength = -2L;
  private RequestEntity requestEntity;
  private InputStream requestStream = null;
  private String requestString = null;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$methods$EntityEnclosingMethod == null)
    {
      localClass = class$("org.apache.commons.httpclient.methods.EntityEnclosingMethod");
      class$org$apache$commons$httpclient$methods$EntityEnclosingMethod = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$methods$EntityEnclosingMethod;
    }
  }

  public EntityEnclosingMethod()
  {
    setFollowRedirects(false);
  }

  public EntityEnclosingMethod(String paramString)
  {
    super(paramString);
    setFollowRedirects(false);
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

  protected void addContentLengthRequestHeader(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    LOG.trace("enter EntityEnclosingMethod.addContentLengthRequestHeader(HttpState, HttpConnection)");
    if ((getRequestHeader("content-length") == null) && (getRequestHeader("Transfer-Encoding") == null))
    {
      long l = getRequestContentLength();
      if (l < 0L)
      {
        if (getEffectiveVersion().greaterEquals(HttpVersion.HTTP_1_1))
        {
          addRequestHeader("Transfer-Encoding", "chunked");
        }
        else
        {
          StringBuffer localStringBuffer = new StringBuffer();
          localStringBuffer.append(getEffectiveVersion());
          localStringBuffer.append(" does not support chunk encoding");
          throw new ProtocolException(localStringBuffer.toString());
        }
      }
      else
        addRequestHeader("Content-Length", String.valueOf(l));
    }
  }

  protected void addRequestHeaders(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    LOG.trace("enter EntityEnclosingMethod.addRequestHeaders(HttpState, HttpConnection)");
    super.addRequestHeaders(paramHttpState, paramHttpConnection);
    addContentLengthRequestHeader(paramHttpState, paramHttpConnection);
    if (getRequestHeader("Content-Type") == null)
    {
      RequestEntity localRequestEntity = getRequestEntity();
      if ((localRequestEntity != null) && (localRequestEntity.getContentType() != null))
        setRequestHeader("Content-Type", localRequestEntity.getContentType());
    }
  }

  protected void clearRequestBody()
  {
    LOG.trace("enter EntityEnclosingMethod.clearRequestBody()");
    this.requestStream = null;
    this.requestString = null;
    this.requestEntity = null;
  }

  protected byte[] generateRequestBody()
  {
    LOG.trace("enter EntityEnclosingMethod.renerateRequestBody()");
    return null;
  }

  protected RequestEntity generateRequestEntity()
  {
    byte[] arrayOfByte = generateRequestBody();
    String str;
    if (arrayOfByte != null)
    {
      this.requestEntity = new ByteArrayRequestEntity(arrayOfByte);
    }
    else if (this.requestStream != null)
    {
      this.requestEntity = new InputStreamRequestEntity(this.requestStream, this.requestContentLength);
      this.requestStream = null;
    }
    else if (this.requestString != null)
    {
      str = getRequestCharSet();
      try
      {
        this.requestEntity = new StringRequestEntity(this.requestString, null, str);
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException1)
      {
        if (LOG.isWarnEnabled())
        {
          Log localLog = LOG;
          StringBuffer localStringBuffer = new StringBuffer();
          localStringBuffer.append(str);
          localStringBuffer.append(" not supported");
          localLog.warn(localStringBuffer.toString());
        }
        this.requestEntity = new StringRequestEntity(this.requestString, null, null);
        tmpTernaryOp = localUnsupportedEncodingException1;
      }
    }
    try
    {
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException2)
    {
    }
    return this.requestEntity;
  }

  public boolean getFollowRedirects()
  {
    return false;
  }

  public String getRequestCharSet()
  {
    if (getRequestHeader("Content-Type") == null)
    {
      if (this.requestEntity != null)
        return getContentCharSet(new Header("Content-Type", this.requestEntity.getContentType()));
      return super.getRequestCharSet();
    }
    return super.getRequestCharSet();
  }

  protected long getRequestContentLength()
  {
    LOG.trace("enter EntityEnclosingMethod.getRequestContentLength()");
    boolean bool = hasRequestContent();
    long l = 0L;
    if (!bool)
      return l;
    if (this.chunked)
      return -1L;
    if (this.requestEntity == null)
      this.requestEntity = generateRequestEntity();
    if (this.requestEntity != null)
      l = this.requestEntity.getContentLength();
    return l;
  }

  public RequestEntity getRequestEntity()
  {
    return generateRequestEntity();
  }

  protected boolean hasRequestContent()
  {
    LOG.trace("enter EntityEnclosingMethod.hasRequestContent()");
    boolean bool;
    if ((this.requestEntity == null) && (this.requestStream == null) && (this.requestString == null))
      bool = false;
    else
      bool = true;
    return bool;
  }

  public void recycle()
  {
    LOG.trace("enter EntityEnclosingMethod.recycle()");
    clearRequestBody();
    this.requestContentLength = -2L;
    this.repeatCount = 0;
    this.chunked = false;
    super.recycle();
  }

  public void setContentChunked(boolean paramBoolean)
  {
    this.chunked = paramBoolean;
  }

  public void setFollowRedirects(boolean paramBoolean)
  {
    if (paramBoolean == true)
      throw new IllegalArgumentException("Entity enclosing requests cannot be redirected without user intervention");
    super.setFollowRedirects(false);
  }

  public void setRequestBody(InputStream paramInputStream)
  {
    LOG.trace("enter EntityEnclosingMethod.setRequestBody(InputStream)");
    clearRequestBody();
    this.requestStream = paramInputStream;
  }

  public void setRequestBody(String paramString)
  {
    LOG.trace("enter EntityEnclosingMethod.setRequestBody(String)");
    clearRequestBody();
    this.requestString = paramString;
  }

  public void setRequestContentLength(int paramInt)
  {
    LOG.trace("enter EntityEnclosingMethod.setRequestContentLength(int)");
    this.requestContentLength = paramInt;
  }

  public void setRequestContentLength(long paramLong)
  {
    LOG.trace("enter EntityEnclosingMethod.setRequestContentLength(int)");
    this.requestContentLength = paramLong;
  }

  public void setRequestEntity(RequestEntity paramRequestEntity)
  {
    clearRequestBody();
    this.requestEntity = paramRequestEntity;
  }

  protected boolean writeRequestBody(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    LOG.trace("enter EntityEnclosingMethod.writeRequestBody(HttpState, HttpConnection)");
    if (!hasRequestContent())
    {
      LOG.debug("Request body has not been specified");
      return true;
    }
    if (this.requestEntity == null)
      this.requestEntity = generateRequestEntity();
    if (this.requestEntity == null)
    {
      LOG.debug("Request body is empty");
      return true;
    }
    long l = getRequestContentLength();
    if ((this.repeatCount > 0) && (!this.requestEntity.isRepeatable()))
      throw new ProtocolException("Unbuffered entity enclosing request can not be repeated.");
    this.repeatCount = (1 + this.repeatCount);
    Object localObject = paramHttpConnection.getRequestOutputStream();
    if (l < 0L)
      localObject = new ChunkedOutputStream((OutputStream)localObject);
    this.requestEntity.writeRequest((OutputStream)localObject);
    if ((localObject instanceof ChunkedOutputStream))
      ((ChunkedOutputStream)localObject).finish();
    ((OutputStream)localObject).flush();
    LOG.debug("Request body sent");
    return true;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.EntityEnclosingMethod
 * JD-Core Version:    0.6.1
 */