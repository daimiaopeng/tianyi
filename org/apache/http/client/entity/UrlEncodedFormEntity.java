package org.apache.http.client.entity;

import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.entity.StringEntity;

@Deprecated
public class UrlEncodedFormEntity extends StringEntity
{
  public UrlEncodedFormEntity(List<? extends NameValuePair> paramList)
  {
    super((String)null);
    throw new RuntimeException("Stub!");
  }

  public UrlEncodedFormEntity(List<? extends NameValuePair> paramList, String paramString)
  {
    super((String)null);
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.client.entity.UrlEncodedFormEntity
 * JD-Core Version:    0.6.1
 */