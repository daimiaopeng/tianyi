package org.apache.commons.httpclient.params;

import java.io.Serializable;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DefaultHttpParams
  implements Serializable, Cloneable, HttpParams
{
  private static final Log LOG = LogFactory.getLog(localClass);
  static Class class$org$apache$commons$httpclient$params$DefaultHttpParams;
  private static HttpParamsFactory httpParamsFactory = new DefaultHttpParamsFactory();
  private HttpParams defaults = null;
  private HashMap parameters = null;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$params$DefaultHttpParams == null)
    {
      localClass = class$("org.apache.commons.httpclient.params.DefaultHttpParams");
      class$org$apache$commons$httpclient$params$DefaultHttpParams = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$params$DefaultHttpParams;
    }
  }

  public DefaultHttpParams()
  {
    this(getDefaultParams());
  }

  public DefaultHttpParams(HttpParams paramHttpParams)
  {
    this.defaults = paramHttpParams;
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

  public static HttpParams getDefaultParams()
  {
    return httpParamsFactory.getDefaultParams();
  }

  public static void setHttpParamsFactory(HttpParamsFactory paramHttpParamsFactory)
  {
    if (paramHttpParamsFactory == null)
      throw new IllegalArgumentException("httpParamsFactory may not be null");
    httpParamsFactory = paramHttpParamsFactory;
  }

  public void clear()
  {
    this.parameters = null;
  }

  public Object clone()
  {
    DefaultHttpParams localDefaultHttpParams = (DefaultHttpParams)super.clone();
    if (this.parameters != null)
      localDefaultHttpParams.parameters = ((HashMap)this.parameters.clone());
    localDefaultHttpParams.setDefaults(this.defaults);
    return localDefaultHttpParams;
  }

  public boolean getBooleanParameter(String paramString, boolean paramBoolean)
  {
    Object localObject = getParameter(paramString);
    if (localObject == null)
      return paramBoolean;
    return ((Boolean)localObject).booleanValue();
  }

  public HttpParams getDefaults()
  {
    try
    {
      HttpParams localHttpParams = this.defaults;
      return localHttpParams;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public double getDoubleParameter(String paramString, double paramDouble)
  {
    Object localObject = getParameter(paramString);
    if (localObject == null)
      return paramDouble;
    return ((Double)localObject).doubleValue();
  }

  public int getIntParameter(String paramString, int paramInt)
  {
    Object localObject = getParameter(paramString);
    if (localObject == null)
      return paramInt;
    return ((Integer)localObject).intValue();
  }

  public long getLongParameter(String paramString, long paramLong)
  {
    Object localObject = getParameter(paramString);
    if (localObject == null)
      return paramLong;
    return ((Long)localObject).longValue();
  }

  public Object getParameter(String paramString)
  {
    try
    {
      Object localObject2;
      if (this.parameters != null)
        localObject2 = this.parameters.get(paramString);
      else
        localObject2 = null;
      if (localObject2 != null)
        return localObject2;
      if (this.defaults != null)
      {
        Object localObject3 = this.defaults.getParameter(paramString);
        return localObject3;
      }
      return null;
    }
    finally
    {
    }
  }

  public boolean isParameterFalse(String paramString)
  {
    return true ^ getBooleanParameter(paramString, false);
  }

  public boolean isParameterSet(String paramString)
  {
    boolean bool;
    if (getParameter(paramString) != null)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public boolean isParameterSetLocally(String paramString)
  {
    boolean bool;
    if ((this.parameters != null) && (this.parameters.get(paramString) != null))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public boolean isParameterTrue(String paramString)
  {
    return getBooleanParameter(paramString, false);
  }

  public void setBooleanParameter(String paramString, boolean paramBoolean)
  {
    Boolean localBoolean;
    if (paramBoolean)
      localBoolean = Boolean.TRUE;
    else
      localBoolean = Boolean.FALSE;
    setParameter(paramString, localBoolean);
  }

  public void setDefaults(HttpParams paramHttpParams)
  {
    try
    {
      this.defaults = paramHttpParams;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setDoubleParameter(String paramString, double paramDouble)
  {
    setParameter(paramString, new Double(paramDouble));
  }

  public void setIntParameter(String paramString, int paramInt)
  {
    setParameter(paramString, new Integer(paramInt));
  }

  public void setLongParameter(String paramString, long paramLong)
  {
    setParameter(paramString, new Long(paramLong));
  }

  public void setParameter(String paramString, Object paramObject)
  {
    try
    {
      if (this.parameters == null)
        this.parameters = new HashMap();
      this.parameters.put(paramString, paramObject);
      if (LOG.isDebugEnabled())
      {
        Log localLog = LOG;
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append("Set parameter ");
        localStringBuffer.append(paramString);
        localStringBuffer.append(" = ");
        localStringBuffer.append(paramObject);
        localLog.debug(localStringBuffer.toString());
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setParameters(String[] paramArrayOfString, Object paramObject)
  {
    int i = 0;
    try
    {
      while (i < paramArrayOfString.length)
      {
        setParameter(paramArrayOfString[i], paramObject);
        i++;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.params.DefaultHttpParams
 * JD-Core Version:    0.6.1
 */