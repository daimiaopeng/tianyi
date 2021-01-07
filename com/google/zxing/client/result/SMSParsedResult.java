package com.google.zxing.client.result;

public final class SMSParsedResult extends ParsedResult
{
  private final String body;
  private final String[] numbers;
  private final String subject;
  private final String[] vias;

  public SMSParsedResult(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    super(ParsedResultType.SMS);
    this.numbers = new String[] { paramString1 };
    this.vias = new String[] { paramString2 };
    this.subject = paramString3;
    this.body = paramString4;
  }

  public SMSParsedResult(String[] paramArrayOfString1, String[] paramArrayOfString2, String paramString1, String paramString2)
  {
    super(ParsedResultType.SMS);
    this.numbers = paramArrayOfString1;
    this.vias = paramArrayOfString2;
    this.subject = paramString1;
    this.body = paramString2;
  }

  public String getBody()
  {
    return this.body;
  }

  public String getDisplayResult()
  {
    StringBuilder localStringBuilder = new StringBuilder(100);
    maybeAppend(this.numbers, localStringBuilder);
    maybeAppend(this.subject, localStringBuilder);
    maybeAppend(this.body, localStringBuilder);
    return localStringBuilder.toString();
  }

  public String[] getNumbers()
  {
    return this.numbers;
  }

  public String getSMSURI()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("sms:");
    int i = 0;
    int j = 1;
    while (i < this.numbers.length)
    {
      if (j != 0)
        j = 0;
      else
        localStringBuilder.append(',');
      localStringBuilder.append(this.numbers[i]);
      if ((this.vias != null) && (this.vias[i] != null))
      {
        localStringBuilder.append(";via=");
        localStringBuilder.append(this.vias[i]);
      }
      i++;
    }
    int k;
    if (this.body != null)
      k = 1;
    else
      k = 0;
    String str = this.subject;
    int m = 0;
    if (str != null)
      m = 1;
    if ((k != 0) || (m != 0))
    {
      localStringBuilder.append('?');
      if (k != 0)
      {
        localStringBuilder.append("body=");
        localStringBuilder.append(this.body);
      }
      if (m != 0)
      {
        if (k != 0)
          localStringBuilder.append('&');
        localStringBuilder.append("subject=");
        localStringBuilder.append(this.subject);
      }
    }
    return localStringBuilder.toString();
  }

  public String getSubject()
  {
    return this.subject;
  }

  public String[] getVias()
  {
    return this.vias;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.SMSParsedResult
 * JD-Core Version:    0.6.1
 */