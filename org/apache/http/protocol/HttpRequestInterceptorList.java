package org.apache.http.protocol;

import java.util.List;
import org.apache.http.HttpRequestInterceptor;

@Deprecated
public abstract interface HttpRequestInterceptorList
{
  public abstract void addRequestInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor);

  public abstract void addRequestInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor, int paramInt);

  public abstract void clearRequestInterceptors();

  public abstract HttpRequestInterceptor getRequestInterceptor(int paramInt);

  public abstract int getRequestInterceptorCount();

  public abstract void removeRequestInterceptorByClass(Class paramClass);

  public abstract void setInterceptors(List paramList);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.protocol.HttpRequestInterceptorList
 * JD-Core Version:    0.6.1
 */