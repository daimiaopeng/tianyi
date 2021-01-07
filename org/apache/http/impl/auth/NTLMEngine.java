package org.apache.http.impl.auth;

@Deprecated
public abstract interface NTLMEngine
{
  public abstract String generateType1Msg(String paramString1, String paramString2);

  public abstract String generateType3Msg(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.auth.NTLMEngine
 * JD-Core Version:    0.6.1
 */