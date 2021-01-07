package org.apache.http.client.methods;

import java.net.URI;
import org.apache.http.HttpRequest;

@Deprecated
public abstract interface HttpUriRequest extends HttpRequest
{
  public abstract void abort();

  public abstract String getMethod();

  public abstract URI getURI();

  public abstract boolean isAborted();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.client.methods.HttpUriRequest
 * JD-Core Version:    0.6.1
 */