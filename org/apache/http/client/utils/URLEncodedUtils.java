package org.apache.http.client.utils;

import java.net.URI;
import java.util.List;
import java.util.Scanner;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;

@Deprecated
public class URLEncodedUtils
{
  public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";

  public URLEncodedUtils()
  {
    throw new RuntimeException("Stub!");
  }

  public static String format(List<? extends NameValuePair> paramList, String paramString)
  {
    throw new RuntimeException("Stub!");
  }

  public static boolean isEncoded(HttpEntity paramHttpEntity)
  {
    throw new RuntimeException("Stub!");
  }

  public static List<NameValuePair> parse(URI paramURI, String paramString)
  {
    throw new RuntimeException("Stub!");
  }

  public static List<NameValuePair> parse(HttpEntity paramHttpEntity)
  {
    throw new RuntimeException("Stub!");
  }

  public static void parse(List<NameValuePair> paramList, Scanner paramScanner, String paramString)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.client.utils.URLEncodedUtils
 * JD-Core Version:    0.6.1
 */