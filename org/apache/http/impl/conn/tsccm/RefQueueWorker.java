package org.apache.http.impl.conn.tsccm;

import java.lang.ref.ReferenceQueue;

@Deprecated
public class RefQueueWorker
  implements Runnable
{
  protected final RefQueueHandler refHandler;
  protected final ReferenceQueue<?> refQueue;
  protected volatile Thread workerThread;

  public RefQueueWorker(ReferenceQueue<?> paramReferenceQueue, RefQueueHandler paramRefQueueHandler)
  {
    throw new RuntimeException("Stub!");
  }

  public void run()
  {
    throw new RuntimeException("Stub!");
  }

  public void shutdown()
  {
    throw new RuntimeException("Stub!");
  }

  public String toString()
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.conn.tsccm.RefQueueWorker
 * JD-Core Version:    0.6.1
 */