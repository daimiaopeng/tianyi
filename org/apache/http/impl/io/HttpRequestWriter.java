package org.apache.http.impl.io;

import org.apache.http.HttpMessage;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.message.LineFormatter;
import org.apache.http.params.HttpParams;

@Deprecated
public class HttpRequestWriter extends AbstractMessageWriter
{
  public HttpRequestWriter(SessionOutputBuffer paramSessionOutputBuffer, LineFormatter paramLineFormatter, HttpParams paramHttpParams)
  {
    super((SessionOutputBuffer)null, (LineFormatter)null, (HttpParams)null);
    throw new RuntimeException("Stub!");
  }

  protected void writeHeadLine(HttpMessage paramHttpMessage)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.io.HttpRequestWriter
 * JD-Core Version:    0.6.1
 */