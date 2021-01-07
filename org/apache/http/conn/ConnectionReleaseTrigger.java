package org.apache.http.conn;

@Deprecated
public abstract interface ConnectionReleaseTrigger
{
  public abstract void abortConnection();

  public abstract void releaseConnection();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.conn.ConnectionReleaseTrigger
 * JD-Core Version:    0.6.1
 */