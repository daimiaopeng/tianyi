package org.jsoup.parser;

public class ParseError
{
  private String errorMsg;
  private int pos;

  ParseError(int paramInt, String paramString)
  {
    this.pos = paramInt;
    this.errorMsg = paramString;
  }

  ParseError(int paramInt, String paramString, Object[] paramArrayOfObject)
  {
    this.errorMsg = String.format(paramString, paramArrayOfObject);
    this.pos = paramInt;
  }

  public String getErrorMessage()
  {
    return this.errorMsg;
  }

  public int getPosition()
  {
    return this.pos;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.pos);
    localStringBuilder.append(": ");
    localStringBuilder.append(this.errorMsg);
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.parser.ParseError
 * JD-Core Version:    0.6.1
 */