package org.apache.commons.httpclient.methods;

import java.util.Iterator;
import java.util.Vector;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PostMethod extends EntityEnclosingMethod
{
  public static final String FORM_URL_ENCODED_CONTENT_TYPE = "application/x-www-form-urlencoded";
  private static final Log LOG = LogFactory.getLog(localClass);
  static Class class$org$apache$commons$httpclient$methods$PostMethod;
  private Vector params = new Vector();

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$methods$PostMethod == null)
    {
      localClass = class$("org.apache.commons.httpclient.methods.PostMethod");
      class$org$apache$commons$httpclient$methods$PostMethod = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$methods$PostMethod;
    }
  }

  public PostMethod()
  {
  }

  public PostMethod(String paramString)
  {
    super(paramString);
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

  public void addParameter(String paramString1, String paramString2)
  {
    LOG.trace("enter PostMethod.addParameter(String, String)");
    if ((paramString1 != null) && (paramString2 != null))
    {
      super.clearRequestBody();
      this.params.add(new NameValuePair(paramString1, paramString2));
      return;
    }
    throw new IllegalArgumentException("Arguments to addParameter(String, String) cannot be null");
  }

  public void addParameter(NameValuePair paramNameValuePair)
  {
    LOG.trace("enter PostMethod.addParameter(NameValuePair)");
    if (paramNameValuePair == null)
      throw new IllegalArgumentException("NameValuePair may not be null");
    addParameter(paramNameValuePair.getName(), paramNameValuePair.getValue());
  }

  public void addParameters(NameValuePair[] paramArrayOfNameValuePair)
  {
    LOG.trace("enter PostMethod.addParameters(NameValuePair[])");
    if (paramArrayOfNameValuePair == null)
    {
      LOG.warn("Attempt to addParameters(null) ignored");
    }
    else
    {
      super.clearRequestBody();
      for (int i = 0; i < paramArrayOfNameValuePair.length; i++)
        this.params.add(paramArrayOfNameValuePair[i]);
    }
  }

  protected void clearRequestBody()
  {
    LOG.trace("enter PostMethod.clearRequestBody()");
    this.params.clear();
    super.clearRequestBody();
  }

  protected RequestEntity generateRequestEntity()
  {
    if (!this.params.isEmpty())
      return new ByteArrayRequestEntity(EncodingUtil.getAsciiBytes(EncodingUtil.formUrlEncode(getParameters(), getRequestCharSet())), "application/x-www-form-urlencoded");
    return super.generateRequestEntity();
  }

  public String getName()
  {
    return "POST";
  }

  public NameValuePair getParameter(String paramString)
  {
    LOG.trace("enter PostMethod.getParameter(String)");
    if (paramString == null)
      return null;
    Iterator localIterator = this.params.iterator();
    while (localIterator.hasNext())
    {
      NameValuePair localNameValuePair = (NameValuePair)localIterator.next();
      if (paramString.equals(localNameValuePair.getName()))
        return localNameValuePair;
    }
    return null;
  }

  public NameValuePair[] getParameters()
  {
    LOG.trace("enter PostMethod.getParameters()");
    int i = this.params.size();
    Object[] arrayOfObject = this.params.toArray();
    NameValuePair[] arrayOfNameValuePair = new NameValuePair[i];
    for (int j = 0; j < i; j++)
      arrayOfNameValuePair[j] = ((NameValuePair)arrayOfObject[j]);
    return arrayOfNameValuePair;
  }

  protected boolean hasRequestContent()
  {
    LOG.trace("enter PostMethod.hasRequestContent()");
    if (!this.params.isEmpty())
      return true;
    return super.hasRequestContent();
  }

  public boolean removeParameter(String paramString)
  {
    LOG.trace("enter PostMethod.removeParameter(String)");
    if (paramString == null)
      throw new IllegalArgumentException("Argument passed to removeParameter(String) cannot be null");
    boolean bool = false;
    Iterator localIterator = this.params.iterator();
    while (localIterator.hasNext())
      if (paramString.equals(((NameValuePair)localIterator.next()).getName()))
      {
        localIterator.remove();
        bool = true;
      }
    return bool;
  }

  public boolean removeParameter(String paramString1, String paramString2)
  {
    LOG.trace("enter PostMethod.removeParameter(String, String)");
    if (paramString1 == null)
      throw new IllegalArgumentException("Parameter name may not be null");
    if (paramString2 == null)
      throw new IllegalArgumentException("Parameter value may not be null");
    Iterator localIterator = this.params.iterator();
    while (localIterator.hasNext())
    {
      NameValuePair localNameValuePair = (NameValuePair)localIterator.next();
      if ((paramString1.equals(localNameValuePair.getName())) && (paramString2.equals(localNameValuePair.getValue())))
      {
        localIterator.remove();
        return true;
      }
    }
    return false;
  }

  public void setParameter(String paramString1, String paramString2)
  {
    LOG.trace("enter PostMethod.setParameter(String, String)");
    removeParameter(paramString1);
    addParameter(paramString1, paramString2);
  }

  public void setRequestBody(NameValuePair[] paramArrayOfNameValuePair)
  {
    LOG.trace("enter PostMethod.setRequestBody(NameValuePair[])");
    if (paramArrayOfNameValuePair == null)
      throw new IllegalArgumentException("Array of parameters may not be null");
    clearRequestBody();
    addParameters(paramArrayOfNameValuePair);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.PostMethod
 * JD-Core Version:    0.6.1
 */