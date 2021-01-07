package org.apache.http.impl.io;

import java.net.Socket;
import org.apache.http.params.HttpParams;

@Deprecated
public class SocketInputBuffer extends AbstractSessionInputBuffer
{
  public SocketInputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
  {
    throw new RuntimeException("Stub!");
  }

  public boolean isDataAvailable(int paramInt)
  {
    throw new RuntimeException("Stub!");
  }

  public boolean isStale()
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.io.SocketInputBuffer
 * JD-Core Version:    0.6.1
 */