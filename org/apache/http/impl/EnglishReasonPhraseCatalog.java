package org.apache.http.impl;

import java.util.Locale;
import org.apache.http.ReasonPhraseCatalog;

@Deprecated
public class EnglishReasonPhraseCatalog
  implements ReasonPhraseCatalog
{
  public static final EnglishReasonPhraseCatalog INSTANCE;

  protected EnglishReasonPhraseCatalog()
  {
    throw new RuntimeException("Stub!");
  }

  public String getReason(int paramInt, Locale paramLocale)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.EnglishReasonPhraseCatalog
 * JD-Core Version:    0.6.1
 */