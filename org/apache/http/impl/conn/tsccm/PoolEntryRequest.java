package org.apache.http.impl.conn.tsccm;

import java.util.concurrent.TimeUnit;

@Deprecated
public abstract interface PoolEntryRequest
{
  public abstract void abortRequest();

  public abstract BasicPoolEntry getPoolEntry(long paramLong, TimeUnit paramTimeUnit);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.conn.tsccm.PoolEntryRequest
 * JD-Core Version:    0.6.1
 */