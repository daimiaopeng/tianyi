package org.apache.commons.httpclient.methods;

import java.io.IOException;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.ProtocolException;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HeadMethod extends HttpMethodBase
{
  private static final Log LOG = LogFactory.getLog(localClass);
  static Class class$org$apache$commons$httpclient$methods$HeadMethod;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$methods$HeadMethod == null)
    {
      localClass = class$("org.apache.commons.httpclient.methods.HeadMethod");
      class$org$apache$commons$httpclient$methods$HeadMethod = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$methods$HeadMethod;
    }
  }

  public HeadMethod()
  {
    setFollowRedirects(true);
  }

  public HeadMethod(String paramString)
  {
    super(paramString);
    setFollowRedirects(true);
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

  public int getBodyCheckTimeout()
  {
    return getParams().getIntParameter("http.protocol.head-body-timeout", -1);
  }

  public String getName()
  {
    return "HEAD";
  }

  protected void readResponseBody(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    LOG.trace("enter HeadMethod.readResponseBody(HttpState, HttpConnection)");
    int i = getParams().getIntParameter("http.protocol.head-body-timeout", -1);
    if (i < 0)
    {
      responseBodyConsumed();
    }
    else
    {
      if (LOG.isDebugEnabled())
      {
        Log localLog = LOG;
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append("Check for non-compliant response body. Timeout in ");
        localStringBuffer.append(i);
        localStringBuffer.append(" ms");
        localLog.debug(localStringBuffer.toString());
      }
      boolean bool;
      try
      {
        bool = paramHttpConnection.isResponseAvailable(i);
      }
      catch (IOException localIOException)
      {
        LOG.debug("An IOException occurred while testing if a response was available, we will assume one is not.", localIOException);
        bool = false;
      }
      if (bool)
      {
        if (getParams().isParameterTrue("http.protocol.reject-head-body"))
          throw new ProtocolException("Body content may not be sent in response to HTTP HEAD request");
        LOG.warn("Body content returned in response to HTTP HEAD");
        super.readResponseBody(paramHttpState, paramHttpConnection);
      }
    }
  }

  public void recycle()
  {
    super.recycle();
    setFollowRedirects(true);
  }

  public void setBodyCheckTimeout(int paramInt)
  {
    getParams().setIntParameter("http.protocol.head-body-timeout", paramInt);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.HeadMethod
 * JD-Core Version:    0.6.1
 */