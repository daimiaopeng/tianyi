package org.apache.http.protocol;

import java.util.List;
import org.apache.http.HttpResponseInterceptor;

@Deprecated
public abstract interface HttpResponseInterceptorList
{
  public abstract void addResponseInterceptor(HttpResponseInterceptor paramHttpResponseInterceptor);

  public abstract void addResponseInterceptor(HttpResponseInterceptor paramHttpResponseInterceptor, int paramInt);

  public abstract void clearResponseInterceptors();

  public abstract HttpResponseInterceptor getResponseInterceptor(int paramInt);

  public abstract int getResponseInterceptorCount();

  public abstract void removeResponseInterceptorByClass(Class paramClass);

  public abstract void setInterceptors(List paramList);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.protocol.HttpResponseInterceptorList
 * JD-Core Version:    0.6.1
 */