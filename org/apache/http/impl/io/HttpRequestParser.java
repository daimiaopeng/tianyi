package org.apache.http.impl.io;

import org.apache.http.HttpMessage;
import org.apache.http.HttpRequestFactory;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.LineParser;
import org.apache.http.params.HttpParams;

@Deprecated
public class HttpRequestParser extends AbstractMessageParser
{
  public HttpRequestParser(SessionInputBuffer paramSessionInputBuffer, LineParser paramLineParser, HttpRequestFactory paramHttpRequestFactory, HttpParams paramHttpParams)
  {
    super((SessionInputBuffer)null, (LineParser)null, (HttpParams)null);
    throw new RuntimeException("Stub!");
  }

  protected HttpMessage parseHead(SessionInputBuffer paramSessionInputBuffer)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.io.HttpRequestParser
 * JD-Core Version:    0.6.1
 */