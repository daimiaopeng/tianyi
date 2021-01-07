package org.apache.commons.httpclient.auth;

import org.apache.commons.httpclient.NTCredentials;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NTLMScheme
  implements AuthScheme
{
  private static final int FAILED = 2147483647;
  private static final int INITIATED = 1;
  private static final Log LOG = LogFactory.getLog(localClass);
  private static final int TYPE1_MSG_GENERATED = 2;
  private static final int TYPE2_MSG_RECEIVED = 3;
  private static final int TYPE3_MSG_GENERATED = 4;
  private static final int UNINITIATED;
  static Class class$org$apache$commons$httpclient$auth$NTLMScheme;
  private String ntlmchallenge = null;
  private int state;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$auth$NTLMScheme == null)
    {
      localClass = class$("org.apache.commons.httpclient.auth.NTLMScheme");
      class$org$apache$commons$httpclient$auth$NTLMScheme = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$auth$NTLMScheme;
    }
  }

  public NTLMScheme()
  {
    this.state = 0;
  }

  public NTLMScheme(String paramString)
  {
    processChallenge(paramString);
  }

  public static String authenticate(NTCredentials paramNTCredentials, String paramString)
  {
    LOG.trace("enter NTLMScheme.authenticate(NTCredentials, String)");
    if (paramNTCredentials == null)
      throw new IllegalArgumentException("Credentials may not be null");
    String str = new NTLM().getResponseFor(paramString, paramNTCredentials.getUserName(), paramNTCredentials.getPassword(), paramNTCredentials.getHost(), paramNTCredentials.getDomain());
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("NTLM ");
    localStringBuffer.append(str);
    return localStringBuffer.toString();
  }

  public static String authenticate(NTCredentials paramNTCredentials, String paramString1, String paramString2)
  {
    LOG.trace("enter NTLMScheme.authenticate(NTCredentials, String)");
    if (paramNTCredentials == null)
      throw new IllegalArgumentException("Credentials may not be null");
    NTLM localNTLM = new NTLM();
    localNTLM.setCredentialCharset(paramString2);
    String str = localNTLM.getResponseFor(paramString1, paramNTCredentials.getUserName(), paramNTCredentials.getPassword(), paramNTCredentials.getHost(), paramNTCredentials.getDomain());
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("NTLM ");
    localStringBuffer.append(str);
    return localStringBuffer.toString();
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
    //   0: getstatic 43	org/apache/commons/httpclient/auth/NTLMScheme:LOG	Lorg/apache/commons/logging/Log;
    //   3: ldc 126
    //   5: invokeinterface 64 2 0
    //   10: aload_1
    //   11: checkcast 75	org/apache/commons/httpclient/NTCredentials
    //   14: astore 7
    //   16: aload 7
    //   18: aload_0
    //   19: getfield 48	org/apache/commons/httpclient/auth/NTLMScheme:ntlmchallenge	Ljava/lang/String;
    //   22: invokestatic 128	org/apache/commons/httpclient/auth/NTLMScheme:authenticate	(Lorg/apache/commons/httpclient/NTCredentials;Ljava/lang/String;)Ljava/lang/String;
    //   25: areturn
    //   26: new 94	java/lang/StringBuffer
    //   29: dup
    //   30: invokespecial 95	java/lang/StringBuffer:<init>	()V
    //   33: astore 4
    //   35: aload 4
    //   37: ldc 130
    //   39: invokevirtual 101	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   42: pop
    //   43: aload 4
    //   45: aload_1
    //   46: invokevirtual 134	java/lang/Object:getClass	()Ljava/lang/Class;
    //   49: invokevirtual 137	java/lang/Class:getName	()Ljava/lang/String;
    //   52: invokevirtual 101	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   55: pop
    //   56: new 139	org/apache/commons/httpclient/auth/InvalidCredentialsException
    //   59: dup
    //   60: aload 4
    //   62: invokevirtual 104	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   65: invokespecial 140	org/apache/commons/httpclient/auth/InvalidCredentialsException:<init>	(Ljava/lang/String;)V
    //   68: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   10	16	26	java/lang/ClassCastException
  }

  // ERROR //
  public String authenticate(org.apache.commons.httpclient.Credentials paramCredentials, org.apache.commons.httpclient.HttpMethod paramHttpMethod)
  {
    // Byte code:
    //   0: getstatic 43	org/apache/commons/httpclient/auth/NTLMScheme:LOG	Lorg/apache/commons/logging/Log;
    //   3: ldc 143
    //   5: invokeinterface 64 2 0
    //   10: aload_0
    //   11: getfield 50	org/apache/commons/httpclient/auth/NTLMScheme:state	I
    //   14: ifne +13 -> 27
    //   17: new 145	java/lang/IllegalStateException
    //   20: dup
    //   21: ldc 147
    //   23: invokespecial 148	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   26: athrow
    //   27: aload_1
    //   28: checkcast 75	org/apache/commons/httpclient/NTCredentials
    //   31: astore 6
    //   33: new 72	org/apache/commons/httpclient/auth/NTLM
    //   36: dup
    //   37: invokespecial 73	org/apache/commons/httpclient/auth/NTLM:<init>	()V
    //   40: astore 7
    //   42: aload 7
    //   44: aload_2
    //   45: invokeinterface 154 1 0
    //   50: invokevirtual 159	org/apache/commons/httpclient/params/HttpMethodParams:getCredentialCharset	()Ljava/lang/String;
    //   53: invokevirtual 108	org/apache/commons/httpclient/auth/NTLM:setCredentialCharset	(Ljava/lang/String;)V
    //   56: aload_0
    //   57: getfield 50	org/apache/commons/httpclient/auth/NTLMScheme:state	I
    //   60: iconst_1
    //   61: if_icmpeq +59 -> 120
    //   64: aload_0
    //   65: getfield 50	org/apache/commons/httpclient/auth/NTLMScheme:state	I
    //   68: ldc 9
    //   70: if_icmpne +6 -> 76
    //   73: goto +47 -> 120
    //   76: aload 7
    //   78: aload 6
    //   80: invokevirtual 79	org/apache/commons/httpclient/NTCredentials:getUserName	()Ljava/lang/String;
    //   83: aload 6
    //   85: invokevirtual 82	org/apache/commons/httpclient/NTCredentials:getPassword	()Ljava/lang/String;
    //   88: aload 6
    //   90: invokevirtual 85	org/apache/commons/httpclient/NTCredentials:getHost	()Ljava/lang/String;
    //   93: aload 6
    //   95: invokevirtual 88	org/apache/commons/httpclient/NTCredentials:getDomain	()Ljava/lang/String;
    //   98: aload 7
    //   100: aload_0
    //   101: getfield 48	org/apache/commons/httpclient/auth/NTLMScheme:ntlmchallenge	Ljava/lang/String;
    //   104: invokevirtual 163	org/apache/commons/httpclient/auth/NTLM:parseType2Message	(Ljava/lang/String;)[B
    //   107: invokevirtual 167	org/apache/commons/httpclient/auth/NTLM:getType3Message	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[B)Ljava/lang/String;
    //   110: astore 8
    //   112: aload_0
    //   113: iconst_4
    //   114: putfield 50	org/apache/commons/httpclient/auth/NTLMScheme:state	I
    //   117: goto +25 -> 142
    //   120: aload 7
    //   122: aload 6
    //   124: invokevirtual 85	org/apache/commons/httpclient/NTCredentials:getHost	()Ljava/lang/String;
    //   127: aload 6
    //   129: invokevirtual 88	org/apache/commons/httpclient/NTCredentials:getDomain	()Ljava/lang/String;
    //   132: invokevirtual 171	org/apache/commons/httpclient/auth/NTLM:getType1Message	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   135: astore 8
    //   137: aload_0
    //   138: iconst_2
    //   139: putfield 50	org/apache/commons/httpclient/auth/NTLMScheme:state	I
    //   142: new 94	java/lang/StringBuffer
    //   145: dup
    //   146: invokespecial 95	java/lang/StringBuffer:<init>	()V
    //   149: astore 9
    //   151: aload 9
    //   153: ldc 97
    //   155: invokevirtual 101	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   158: pop
    //   159: aload 9
    //   161: aload 8
    //   163: invokevirtual 101	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   166: pop
    //   167: aload 9
    //   169: invokevirtual 104	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   172: areturn
    //   173: new 94	java/lang/StringBuffer
    //   176: dup
    //   177: invokespecial 95	java/lang/StringBuffer:<init>	()V
    //   180: astore_3
    //   181: aload_3
    //   182: ldc 130
    //   184: invokevirtual 101	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   187: pop
    //   188: aload_3
    //   189: aload_1
    //   190: invokevirtual 134	java/lang/Object:getClass	()Ljava/lang/Class;
    //   193: invokevirtual 137	java/lang/Class:getName	()Ljava/lang/String;
    //   196: invokevirtual 101	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   199: pop
    //   200: new 139	org/apache/commons/httpclient/auth/InvalidCredentialsException
    //   203: dup
    //   204: aload_3
    //   205: invokevirtual 104	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   208: invokespecial 140	org/apache/commons/httpclient/auth/InvalidCredentialsException:<init>	(Ljava/lang/String;)V
    //   211: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   27	33	173	java/lang/ClassCastException
  }

  public String getID()
  {
    return this.ntlmchallenge;
  }

  public String getParameter(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Parameter name may not be null");
    return null;
  }

  public String getRealm()
  {
    return null;
  }

  public String getSchemeName()
  {
    return "ntlm";
  }

  public boolean isComplete()
  {
    boolean bool;
    if ((this.state != 4) && (this.state != 2147483647))
      bool = false;
    else
      bool = true;
    return bool;
  }

  public boolean isConnectionBased()
  {
    return true;
  }

  public void processChallenge(String paramString)
  {
    if (!AuthChallengeParser.extractScheme(paramString).equalsIgnoreCase(getSchemeName()))
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("Invalid NTLM challenge: ");
      localStringBuffer.append(paramString);
      throw new MalformedChallengeException(localStringBuffer.toString());
    }
    int i = paramString.indexOf(' ');
    if (i != -1)
    {
      this.ntlmchallenge = paramString.substring(i, paramString.length()).trim();
      this.state = 3;
    }
    else
    {
      this.ntlmchallenge = "";
      if (this.state == 0)
        this.state = 1;
      else
        this.state = 2147483647;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.NTLMScheme
 * JD-Core Version:    0.6.1
 */