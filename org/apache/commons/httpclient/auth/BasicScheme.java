package org.apache.commons.httpclient.auth;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BasicScheme extends RFC2617Scheme
{
  private static final Log LOG = LogFactory.getLog(localClass);
  static Class class$org$apache$commons$httpclient$auth$BasicScheme;
  private boolean complete;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$auth$BasicScheme == null)
    {
      localClass = class$("org.apache.commons.httpclient.auth.BasicScheme");
      class$org$apache$commons$httpclient$auth$BasicScheme = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$auth$BasicScheme;
    }
  }

  public BasicScheme()
  {
    this.complete = false;
  }

  public BasicScheme(String paramString)
  {
    super(paramString);
    this.complete = true;
  }

  public static String authenticate(UsernamePasswordCredentials paramUsernamePasswordCredentials)
  {
    return authenticate(paramUsernamePasswordCredentials, "ISO-8859-1");
  }

  public static String authenticate(UsernamePasswordCredentials paramUsernamePasswordCredentials, String paramString)
  {
    LOG.trace("enter BasicScheme.authenticate(UsernamePasswordCredentials, String)");
    if (paramUsernamePasswordCredentials == null)
      throw new IllegalArgumentException("Credentials may not be null");
    if ((paramString != null) && (paramString.length() != 0))
    {
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append(paramUsernamePasswordCredentials.getUserName());
      localStringBuffer1.append(":");
      localStringBuffer1.append(paramUsernamePasswordCredentials.getPassword());
      StringBuffer localStringBuffer2 = new StringBuffer();
      localStringBuffer2.append("Basic ");
      localStringBuffer2.append(EncodingUtil.getAsciiString(Base64.encodeBase64(EncodingUtil.getBytes(localStringBuffer1.toString(), paramString))));
      return localStringBuffer2.toString();
    }
    throw new IllegalArgumentException("charset may not be null or empty");
  }

  static Class class$(String paramString)
  {
    try
    {
      Class localClass = Class.forName(paramString);
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
    }
  }

  // ERROR //
  public String authenticate(org.apache.commons.httpclient.Credentials paramCredentials, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: getstatic 28	org/apache/commons/httpclient/auth/BasicScheme:LOG	Lorg/apache/commons/logging/Log;
    //   3: ldc 121
    //   5: invokeinterface 51 2 0
    //   10: aload_1
    //   11: checkcast 67	org/apache/commons/httpclient/UsernamePasswordCredentials
    //   14: astore 7
    //   16: aload 7
    //   18: invokestatic 123	org/apache/commons/httpclient/auth/BasicScheme:authenticate	(Lorg/apache/commons/httpclient/UsernamePasswordCredentials;)Ljava/lang/String;
    //   21: areturn
    //   22: new 64	java/lang/StringBuffer
    //   25: dup
    //   26: invokespecial 65	java/lang/StringBuffer:<init>	()V
    //   29: astore 4
    //   31: aload 4
    //   33: ldc 125
    //   35: invokevirtual 75	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   38: pop
    //   39: aload 4
    //   41: aload_1
    //   42: invokevirtual 131	java/lang/Object:getClass	()Ljava/lang/Class;
    //   45: invokevirtual 134	java/lang/Class:getName	()Ljava/lang/String;
    //   48: invokevirtual 75	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   51: pop
    //   52: new 136	org/apache/commons/httpclient/auth/InvalidCredentialsException
    //   55: dup
    //   56: aload 4
    //   58: invokevirtual 85	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   61: invokespecial 137	org/apache/commons/httpclient/auth/InvalidCredentialsException:<init>	(Ljava/lang/String;)V
    //   64: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   10	16	22	java/lang/ClassCastException
  }

  // ERROR //
  public String authenticate(org.apache.commons.httpclient.Credentials paramCredentials, org.apache.commons.httpclient.HttpMethod paramHttpMethod)
  {
    // Byte code:
    //   0: getstatic 28	org/apache/commons/httpclient/auth/BasicScheme:LOG	Lorg/apache/commons/logging/Log;
    //   3: ldc 140
    //   5: invokeinterface 51 2 0
    //   10: aload_2
    //   11: ifnonnull +13 -> 24
    //   14: new 53	java/lang/IllegalArgumentException
    //   17: dup
    //   18: ldc 142
    //   20: invokespecial 56	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   23: athrow
    //   24: aload_1
    //   25: checkcast 67	org/apache/commons/httpclient/UsernamePasswordCredentials
    //   28: astore 6
    //   30: aload 6
    //   32: aload_2
    //   33: invokeinterface 148 1 0
    //   38: invokevirtual 153	org/apache/commons/httpclient/params/HttpMethodParams:getCredentialCharset	()Ljava/lang/String;
    //   41: invokestatic 43	org/apache/commons/httpclient/auth/BasicScheme:authenticate	(Lorg/apache/commons/httpclient/UsernamePasswordCredentials;Ljava/lang/String;)Ljava/lang/String;
    //   44: areturn
    //   45: new 64	java/lang/StringBuffer
    //   48: dup
    //   49: invokespecial 65	java/lang/StringBuffer:<init>	()V
    //   52: astore_3
    //   53: aload_3
    //   54: ldc 125
    //   56: invokevirtual 75	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   59: pop
    //   60: aload_3
    //   61: aload_1
    //   62: invokevirtual 131	java/lang/Object:getClass	()Ljava/lang/Class;
    //   65: invokevirtual 134	java/lang/Class:getName	()Ljava/lang/String;
    //   68: invokevirtual 75	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   71: pop
    //   72: new 136	org/apache/commons/httpclient/auth/InvalidCredentialsException
    //   75: dup
    //   76: aload_3
    //   77: invokevirtual 85	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   80: invokespecial 137	org/apache/commons/httpclient/auth/InvalidCredentialsException:<init>	(Ljava/lang/String;)V
    //   83: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   24	30	45	java/lang/ClassCastException
  }

  public String getSchemeName()
  {
    return "basic";
  }

  public boolean isComplete()
  {
    return this.complete;
  }

  public boolean isConnectionBased()
  {
    return false;
  }

  public void processChallenge(String paramString)
  {
    super.processChallenge(paramString);
    this.complete = true;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.BasicScheme
 * JD-Core Version:    0.6.1
 */