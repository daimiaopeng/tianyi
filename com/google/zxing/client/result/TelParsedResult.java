package com.google.zxing.client.result;

public final class TelParsedResult extends ParsedResult
{
  private final String number;
  private final String telURI;
  private final String title;

  public TelParsedResult(String paramString1, String paramString2, String paramString3)
  {
    super(ParsedResultType.TEL);
    this.number = paramString1;
    this.telURI = paramString2;
    this.title = paramString3;
  }

  public String getDisplayResult()
  {
    StringBuilder localStringBuilder = new StringBuilder(20);
    maybeAppend(this.number, localStringBuilder);
    maybeAppend(this.title, localStringBuilder);
    return localStringBuilder.toString();
  }

  public String getNumber()
  {
    return this.number;
  }

  public String getTelURI()
  {
    return this.telURI;
  }

  public String getTitle()
  {
    return this.title;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.TelParsedResult
 * JD-Core Version:    0.6.1
 */