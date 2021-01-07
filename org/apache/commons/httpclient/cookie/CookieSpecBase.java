package org.apache.commons.httpclient.cookie;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.util.DateParseException;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CookieSpecBase
  implements CookieSpec
{
  protected static final Log LOG = LogFactory.getLog(localClass);
  static Class class$org$apache$commons$httpclient$cookie$CookieSpec;
  private Collection datepatterns = null;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$cookie$CookieSpec == null)
    {
      localClass = class$("org.apache.commons.httpclient.cookie.CookieSpec");
      class$org$apache$commons$httpclient$cookie$CookieSpec = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$cookie$CookieSpec;
    }
  }

  private static void addInPathOrder(List paramList, Cookie paramCookie)
  {
    for (int i = 0; (i < paramList.size()) && (paramCookie.compare(paramCookie, (Cookie)paramList.get(i)) <= 0); i++);
    paramList.add(i, paramCookie);
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

  public boolean domainMatch(String paramString1, String paramString2)
  {
    boolean bool1 = paramString1.equals(paramString2);
    int i = 1;
    if (bool1)
      return i;
    if (!paramString2.startsWith("."))
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append(".");
      localStringBuffer.append(paramString2);
      paramString2 = localStringBuffer.toString();
    }
    boolean bool2;
    if ((!paramString1.endsWith(paramString2)) && (!paramString1.equals(paramString2.substring(i))))
      bool2 = false;
    return bool2;
  }

  public String formatCookie(Cookie paramCookie)
  {
    LOG.trace("enter CookieSpecBase.formatCookie(Cookie)");
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(paramCookie.getName());
    localStringBuffer.append("=");
    String str = paramCookie.getValue();
    if (str != null)
      localStringBuffer.append(str);
    return localStringBuffer.toString();
  }

  public Header formatCookieHeader(Cookie paramCookie)
  {
    LOG.trace("enter CookieSpecBase.formatCookieHeader(Cookie)");
    return new Header("Cookie", formatCookie(paramCookie));
  }

  public Header formatCookieHeader(Cookie[] paramArrayOfCookie)
  {
    LOG.trace("enter CookieSpecBase.formatCookieHeader(Cookie[])");
    return new Header("Cookie", formatCookies(paramArrayOfCookie));
  }

  public String formatCookies(Cookie[] paramArrayOfCookie)
  {
    LOG.trace("enter CookieSpecBase.formatCookies(Cookie[])");
    if (paramArrayOfCookie == null)
      throw new IllegalArgumentException("Cookie array may not be null");
    if (paramArrayOfCookie.length == 0)
      throw new IllegalArgumentException("Cookie array may not be empty");
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < paramArrayOfCookie.length; i++)
    {
      if (i > 0)
        localStringBuffer.append("; ");
      localStringBuffer.append(formatCookie(paramArrayOfCookie[i]));
    }
    return localStringBuffer.toString();
  }

  public Collection getValidDateFormats()
  {
    return this.datepatterns;
  }

  public boolean match(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Cookie paramCookie)
  {
    LOG.trace("enter CookieSpecBase.match(String, int, String, boolean, Cookie");
    if (paramString1 == null)
      throw new IllegalArgumentException("Host of origin may not be null");
    if (paramString1.trim().equals(""))
      throw new IllegalArgumentException("Host of origin may not be blank");
    if (paramInt < 0)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("Invalid port: ");
      localStringBuffer.append(paramInt);
      throw new IllegalArgumentException(localStringBuffer.toString());
    }
    if (paramString2 == null)
      throw new IllegalArgumentException("Path of origin may not be null.");
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramString2.trim().equals(""))
      paramString2 = "/";
    String str = paramString1.toLowerCase();
    if (paramCookie.getDomain() == null)
    {
      LOG.warn("Invalid cookie state: domain not specified");
      return false;
    }
    if (paramCookie.getPath() == null)
    {
      LOG.warn("Invalid cookie state: path not specified");
      return false;
    }
    boolean bool2;
    if (paramCookie.getExpiryDate() != null)
    {
      boolean bool4 = paramCookie.getExpiryDate().after(new Date());
      bool2 = false;
      if (!bool4);
    }
    else
    {
      boolean bool1 = domainMatch(str, paramCookie.getDomain());
      bool2 = false;
      if (bool1)
      {
        boolean bool3 = pathMatch(paramString2, paramCookie.getPath());
        bool2 = false;
        if (bool3)
          if (paramCookie.getSecure())
          {
            bool2 = false;
            if (!paramBoolean);
          }
          else
          {
            bool2 = true;
          }
      }
    }
    return bool2;
  }

  public Cookie[] match(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Cookie[] paramArrayOfCookie)
  {
    LOG.trace("enter CookieSpecBase.match(String, int, String, boolean, Cookie[])");
    if (paramArrayOfCookie == null)
      return null;
    LinkedList localLinkedList = new LinkedList();
    for (int i = 0; i < paramArrayOfCookie.length; i++)
      if (match(paramString1, paramInt, paramString2, paramBoolean, paramArrayOfCookie[i]))
        addInPathOrder(localLinkedList, paramArrayOfCookie[i]);
    return (Cookie[])localLinkedList.toArray(new Cookie[localLinkedList.size()]);
  }

  // ERROR //
  public Cookie[] parse(String paramString1, int paramInt, String paramString2, boolean paramBoolean, String paramString3)
  {
    // Byte code:
    //   0: getstatic 30	org/apache/commons/httpclient/cookie/CookieSpecBase:LOG	Lorg/apache/commons/logging/Log;
    //   3: ldc 237
    //   5: invokeinterface 114 2 0
    //   10: aload_1
    //   11: ifnonnull +13 -> 24
    //   14: new 116	java/lang/IllegalArgumentException
    //   17: dup
    //   18: ldc 163
    //   20: invokespecial 119	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   23: athrow
    //   24: aload_1
    //   25: invokevirtual 166	java/lang/String:trim	()Ljava/lang/String;
    //   28: ldc 168
    //   30: invokevirtual 81	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   33: ifeq +13 -> 46
    //   36: new 116	java/lang/IllegalArgumentException
    //   39: dup
    //   40: ldc 170
    //   42: invokespecial 119	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   45: athrow
    //   46: iload_2
    //   47: ifge +40 -> 87
    //   50: new 89	java/lang/StringBuffer
    //   53: dup
    //   54: invokespecial 90	java/lang/StringBuffer:<init>	()V
    //   57: astore 6
    //   59: aload 6
    //   61: ldc 172
    //   63: invokevirtual 94	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   66: pop
    //   67: aload 6
    //   69: iload_2
    //   70: invokevirtual 175	java/lang/StringBuffer:append	(I)Ljava/lang/StringBuffer;
    //   73: pop
    //   74: new 116	java/lang/IllegalArgumentException
    //   77: dup
    //   78: aload 6
    //   80: invokevirtual 97	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   83: invokespecial 119	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   86: athrow
    //   87: aload_3
    //   88: ifnonnull +13 -> 101
    //   91: new 116	java/lang/IllegalArgumentException
    //   94: dup
    //   95: ldc 177
    //   97: invokespecial 119	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   100: athrow
    //   101: aload 5
    //   103: ifnonnull +13 -> 116
    //   106: new 116	java/lang/IllegalArgumentException
    //   109: dup
    //   110: ldc 239
    //   112: invokespecial 119	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   115: athrow
    //   116: aload_3
    //   117: invokevirtual 166	java/lang/String:trim	()Ljava/lang/String;
    //   120: ldc 168
    //   122: invokevirtual 81	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   125: ifeq +6 -> 131
    //   128: ldc 179
    //   130: astore_3
    //   131: aload_1
    //   132: invokevirtual 182	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   135: astore 9
    //   137: aload_3
    //   138: ldc 179
    //   140: invokevirtual 243	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   143: istore 10
    //   145: iload 10
    //   147: iflt +19 -> 166
    //   150: iload 10
    //   152: ifne +6 -> 158
    //   155: iconst_1
    //   156: istore 10
    //   158: aload_3
    //   159: iconst_0
    //   160: iload 10
    //   162: invokevirtual 246	java/lang/String:substring	(II)Ljava/lang/String;
    //   165: astore_3
    //   166: aload 5
    //   168: invokevirtual 182	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   171: ldc 248
    //   173: invokevirtual 251	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   176: istore 11
    //   178: iload 11
    //   180: iconst_m1
    //   181: if_icmpeq +61 -> 242
    //   184: iload 11
    //   186: ldc 248
    //   188: invokevirtual 254	java/lang/String:length	()I
    //   191: iadd
    //   192: istore 21
    //   194: aload 5
    //   196: ldc_w 256
    //   199: iload 21
    //   201: invokevirtual 259	java/lang/String:indexOf	(Ljava/lang/String;I)I
    //   204: istore 22
    //   206: iload 22
    //   208: iconst_m1
    //   209: if_icmpne +10 -> 219
    //   212: aload 5
    //   214: invokevirtual 254	java/lang/String:length	()I
    //   217: istore 22
    //   219: aload 5
    //   221: iload 21
    //   223: iload 22
    //   225: invokevirtual 246	java/lang/String:substring	(II)Ljava/lang/String;
    //   228: aload_0
    //   229: getfield 35	org/apache/commons/httpclient/cookie/CookieSpecBase:datepatterns	Ljava/util/Collection;
    //   232: invokestatic 265	org/apache/commons/httpclient/util/DateUtil:parseDate	(Ljava/lang/String;Ljava/util/Collection;)Ljava/util/Date;
    //   235: pop
    //   236: iconst_1
    //   237: istore 12
    //   239: goto +6 -> 245
    //   242: iconst_0
    //   243: istore 12
    //   245: iload 12
    //   247: ifeq +28 -> 275
    //   250: iconst_1
    //   251: anewarray 267	org/apache/commons/httpclient/HeaderElement
    //   254: astore 13
    //   256: aload 13
    //   258: iconst_0
    //   259: new 267	org/apache/commons/httpclient/HeaderElement
    //   262: dup
    //   263: aload 5
    //   265: invokevirtual 271	java/lang/String:toCharArray	()[C
    //   268: invokespecial 274	org/apache/commons/httpclient/HeaderElement:<init>	([C)V
    //   271: aastore
    //   272: goto +13 -> 285
    //   275: aload 5
    //   277: invokevirtual 271	java/lang/String:toCharArray	()[C
    //   280: invokestatic 278	org/apache/commons/httpclient/HeaderElement:parseElements	([C)[Lorg/apache/commons/httpclient/HeaderElement;
    //   283: astore 13
    //   285: aload 13
    //   287: arraylength
    //   288: anewarray 49	org/apache/commons/httpclient/Cookie
    //   291: astore 14
    //   293: iconst_0
    //   294: istore 15
    //   296: iload 15
    //   298: aload 13
    //   300: arraylength
    //   301: if_icmpge +102 -> 403
    //   304: aload 13
    //   306: iload 15
    //   308: aaload
    //   309: astore 16
    //   311: new 49	org/apache/commons/httpclient/Cookie
    //   314: dup
    //   315: aload 9
    //   317: aload 16
    //   319: invokevirtual 279	org/apache/commons/httpclient/HeaderElement:getName	()Ljava/lang/String;
    //   322: aload 16
    //   324: invokevirtual 280	org/apache/commons/httpclient/HeaderElement:getValue	()Ljava/lang/String;
    //   327: aload_3
    //   328: aconst_null
    //   329: iconst_0
    //   330: invokespecial 283	org/apache/commons/httpclient/Cookie:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Date;Z)V
    //   333: astore 17
    //   335: aload 16
    //   337: invokevirtual 287	org/apache/commons/httpclient/HeaderElement:getParameters	()[Lorg/apache/commons/httpclient/NameValuePair;
    //   340: astore 18
    //   342: aload 18
    //   344: ifnull +31 -> 375
    //   347: iconst_0
    //   348: istore 19
    //   350: iload 19
    //   352: aload 18
    //   354: arraylength
    //   355: if_icmpge +20 -> 375
    //   358: aload_0
    //   359: aload 18
    //   361: iload 19
    //   363: aaload
    //   364: aload 17
    //   366: invokevirtual 291	org/apache/commons/httpclient/cookie/CookieSpecBase:parseAttribute	(Lorg/apache/commons/httpclient/NameValuePair;Lorg/apache/commons/httpclient/Cookie;)V
    //   369: iinc 19 1
    //   372: goto -22 -> 350
    //   375: aload 14
    //   377: iload 15
    //   379: aload 17
    //   381: aastore
    //   382: iinc 15 1
    //   385: goto -89 -> 296
    //   388: astore 20
    //   390: new 293	org/apache/commons/httpclient/cookie/MalformedCookieException
    //   393: dup
    //   394: aload 20
    //   396: invokevirtual 294	java/lang/IllegalArgumentException:getMessage	()Ljava/lang/String;
    //   399: invokespecial 295	org/apache/commons/httpclient/cookie/MalformedCookieException:<init>	(Ljava/lang/String;)V
    //   402: athrow
    //   403: aload 14
    //   405: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   219	236	242	org/apache/commons/httpclient/util/DateParseException
    //   311	335	388	java/lang/IllegalArgumentException
  }

  public Cookie[] parse(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Header paramHeader)
  {
    LOG.trace("enter CookieSpecBase.parse(String, port, path, boolean, String)");
    if (paramHeader == null)
      throw new IllegalArgumentException("Header may not be null.");
    return parse(paramString1, paramInt, paramString2, paramBoolean, paramHeader.getValue());
  }

  public void parseAttribute(NameValuePair paramNameValuePair, Cookie paramCookie)
  {
    if (paramNameValuePair == null)
      throw new IllegalArgumentException("Attribute may not be null.");
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null.");
    String str1 = paramNameValuePair.getName().toLowerCase();
    String str2 = paramNameValuePair.getValue();
    if (str1.equals("path"))
    {
      if ((str2 == null) || (str2.trim().equals("")))
        str2 = "/";
      paramCookie.setPath(str2);
      paramCookie.setPathAttributeSpecified(true);
    }
    else if (str1.equals("domain"))
    {
      if (str2 == null)
        throw new MalformedCookieException("Missing value for domain attribute");
      if (str2.trim().equals(""))
        throw new MalformedCookieException("Blank value for domain attribute");
      paramCookie.setDomain(str2);
      paramCookie.setDomainAttributeSpecified(true);
    }
    else if (str1.equals("max-age"))
    {
      if (str2 == null)
        throw new MalformedCookieException("Missing value for max-age attribute");
      try
      {
        int i = Integer.parseInt(str2);
        paramCookie.setExpiryDate(new Date(System.currentTimeMillis() + 1000L * i));
      }
      catch (NumberFormatException localNumberFormatException)
      {
        StringBuffer localStringBuffer3 = new StringBuffer();
        localStringBuffer3.append("Invalid max-age attribute: ");
        localStringBuffer3.append(localNumberFormatException.getMessage());
        throw new MalformedCookieException(localStringBuffer3.toString());
      }
    }
    else if (str1.equals("secure"))
    {
      paramCookie.setSecure(true);
    }
    else if (str1.equals("comment"))
    {
      paramCookie.setComment(str2);
    }
    else if (str1.equals("expires"))
    {
      if (str2 == null)
        throw new MalformedCookieException("Missing value for expires attribute");
      try
      {
        paramCookie.setExpiryDate(DateUtil.parseDate(str2, this.datepatterns));
      }
      catch (DateParseException localDateParseException)
      {
        LOG.debug("Error parsing cookie date", localDateParseException);
        StringBuffer localStringBuffer2 = new StringBuffer();
        localStringBuffer2.append("Unable to parse expiration date parameter: ");
        localStringBuffer2.append(str2);
        throw new MalformedCookieException(localStringBuffer2.toString());
      }
    }
    else if (LOG.isDebugEnabled())
    {
      Log localLog = LOG;
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append("Unrecognized cookie attribute: ");
      localStringBuffer1.append(paramNameValuePair.toString());
      localLog.debug(localStringBuffer1.toString());
    }
  }

  public boolean pathMatch(String paramString1, String paramString2)
  {
    boolean bool = paramString1.startsWith(paramString2);
    if ((bool) && (paramString1.length() != paramString2.length()) && (!paramString2.endsWith("/")))
      if (paramString1.charAt(paramString2.length()) == CookieSpec.PATH_DELIM_CHAR)
        bool = true;
      else
        bool = false;
    return bool;
  }

  public void setValidDateFormats(Collection paramCollection)
  {
    this.datepatterns = paramCollection;
  }

  public void validate(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Cookie paramCookie)
  {
    LOG.trace("enter CookieSpecBase.validate(String, port, path, boolean, Cookie)");
    if (paramString1 == null)
      throw new IllegalArgumentException("Host of origin may not be null");
    if (paramString1.trim().equals(""))
      throw new IllegalArgumentException("Host of origin may not be blank");
    if (paramInt < 0)
    {
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append("Invalid port: ");
      localStringBuffer1.append(paramInt);
      throw new IllegalArgumentException(localStringBuffer1.toString());
    }
    if (paramString2 == null)
      throw new IllegalArgumentException("Path of origin may not be null.");
    if (paramString2.trim().equals(""))
      paramString2 = "/";
    String str1 = paramString1.toLowerCase();
    if (paramCookie.getVersion() < 0)
    {
      StringBuffer localStringBuffer2 = new StringBuffer();
      localStringBuffer2.append("Illegal version number ");
      localStringBuffer2.append(paramCookie.getValue());
      throw new MalformedCookieException(localStringBuffer2.toString());
    }
    if (str1.indexOf(".") >= 0)
    {
      if (!str1.endsWith(paramCookie.getDomain()))
      {
        String str2 = paramCookie.getDomain();
        if (str2.startsWith("."))
          str2 = str2.substring(1, str2.length());
        if (!str1.equals(str2))
        {
          StringBuffer localStringBuffer5 = new StringBuffer();
          localStringBuffer5.append("Illegal domain attribute \"");
          localStringBuffer5.append(paramCookie.getDomain());
          localStringBuffer5.append("\". Domain of origin: \"");
          localStringBuffer5.append(str1);
          localStringBuffer5.append("\"");
          throw new MalformedCookieException(localStringBuffer5.toString());
        }
      }
    }
    else if (!str1.equals(paramCookie.getDomain()))
    {
      StringBuffer localStringBuffer3 = new StringBuffer();
      localStringBuffer3.append("Illegal domain attribute \"");
      localStringBuffer3.append(paramCookie.getDomain());
      localStringBuffer3.append("\". Domain of origin: \"");
      localStringBuffer3.append(str1);
      localStringBuffer3.append("\"");
      throw new MalformedCookieException(localStringBuffer3.toString());
    }
    if (!paramString2.startsWith(paramCookie.getPath()))
    {
      StringBuffer localStringBuffer4 = new StringBuffer();
      localStringBuffer4.append("Illegal path attribute \"");
      localStringBuffer4.append(paramCookie.getPath());
      localStringBuffer4.append("\". Path of origin: \"");
      localStringBuffer4.append(paramString2);
      localStringBuffer4.append("\"");
      throw new MalformedCookieException(localStringBuffer4.toString());
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.cookie.CookieSpecBase
 * JD-Core Version:    0.6.1
 */