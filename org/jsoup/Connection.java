package org.jsoup;

import java.net.URL;
import java.util.Collection;
import java.util.Map;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

public abstract interface Connection
{
  public abstract Connection cookie(String paramString1, String paramString2);

  public abstract Connection cookies(Map<String, String> paramMap);

  public abstract Connection data(String paramString1, String paramString2);

  public abstract Connection data(Collection<KeyVal> paramCollection);

  public abstract Connection data(Map<String, String> paramMap);

  public abstract Connection data(String[] paramArrayOfString);

  public abstract Response execute();

  public abstract Connection followRedirects(boolean paramBoolean);

  public abstract Document get();

  public abstract Connection header(String paramString1, String paramString2);

  public abstract Connection ignoreContentType(boolean paramBoolean);

  public abstract Connection ignoreHttpErrors(boolean paramBoolean);

  public abstract Connection maxBodySize(int paramInt);

  public abstract Connection method(Method paramMethod);

  public abstract Connection parser(Parser paramParser);

  public abstract Document post();

  public abstract Connection referrer(String paramString);

  public abstract Request request();

  public abstract Connection request(Request paramRequest);

  public abstract Response response();

  public abstract Connection response(Response paramResponse);

  public abstract Connection timeout(int paramInt);

  public abstract Connection url(String paramString);

  public abstract Connection url(URL paramURL);

  public abstract Connection userAgent(String paramString);

  public static abstract interface Base<T extends Base>
  {
    public abstract String cookie(String paramString);

    public abstract T cookie(String paramString1, String paramString2);

    public abstract Map<String, String> cookies();

    public abstract boolean hasCookie(String paramString);

    public abstract boolean hasHeader(String paramString);

    public abstract String header(String paramString);

    public abstract T header(String paramString1, String paramString2);

    public abstract Map<String, String> headers();

    public abstract T method(Connection.Method paramMethod);

    public abstract Connection.Method method();

    public abstract T removeCookie(String paramString);

    public abstract T removeHeader(String paramString);

    public abstract URL url();

    public abstract T url(URL paramURL);
  }

  public static abstract interface KeyVal
  {
    public abstract String key();

    public abstract KeyVal key(String paramString);

    public abstract String value();

    public abstract KeyVal value(String paramString);
  }

  public static enum Method
  {
    static
    {
      Method[] arrayOfMethod = new Method[2];
      arrayOfMethod[0] = GET;
      arrayOfMethod[1] = POST;
    }
  }

  public static abstract interface Request extends Connection.Base<Request>
  {
    public abstract Collection<Connection.KeyVal> data();

    public abstract Request data(Connection.KeyVal paramKeyVal);

    public abstract Request followRedirects(boolean paramBoolean);

    public abstract boolean followRedirects();

    public abstract Request ignoreContentType(boolean paramBoolean);

    public abstract boolean ignoreContentType();

    public abstract Request ignoreHttpErrors(boolean paramBoolean);

    public abstract boolean ignoreHttpErrors();

    public abstract int maxBodySize();

    public abstract Request maxBodySize(int paramInt);

    public abstract Request parser(Parser paramParser);

    public abstract Parser parser();

    public abstract int timeout();

    public abstract Request timeout(int paramInt);
  }

  public static abstract interface Response extends Connection.Base<Response>
  {
    public abstract String body();

    public abstract byte[] bodyAsBytes();

    public abstract String charset();

    public abstract String contentType();

    public abstract Document parse();

    public abstract int statusCode();

    public abstract String statusMessage();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.Connection
 * JD-Core Version:    0.6.1
 */