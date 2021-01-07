package org.apache.commons.httpclient.cookie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HeaderElement;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.util.ParameterFormatter;
import org.apache.commons.logging.Log;

public class RFC2965Spec extends CookieSpecBase
  implements CookieVersionSupport
{
  private static final Comparator PATH_COMPOARATOR = new CookiePathComparator();
  public static final String SET_COOKIE2_KEY = "set-cookie2";
  private final List attribHandlerList;
  private final Map attribHandlerMap;
  private final ParameterFormatter formatter = new ParameterFormatter();
  private final CookieSpec rfc2109;

  public RFC2965Spec()
  {
    this.formatter.setAlwaysUseQuotes(true);
    this.attribHandlerMap = new HashMap(10);
    this.attribHandlerList = new ArrayList(10);
    this.rfc2109 = new RFC2109Spec();
    registerAttribHandler("path", new Cookie2PathAttributeHandler(null));
    registerAttribHandler("domain", new Cookie2DomainAttributeHandler(null));
    registerAttribHandler("port", new Cookie2PortAttributeHandler(null));
    registerAttribHandler("max-age", new Cookie2MaxageAttributeHandler(null));
    registerAttribHandler("secure", new CookieSecureAttributeHandler(null));
    registerAttribHandler("comment", new CookieCommentAttributeHandler(null));
    registerAttribHandler("commenturl", new CookieCommentUrlAttributeHandler(null));
    registerAttribHandler("discard", new CookieDiscardAttributeHandler(null));
    registerAttribHandler("version", new Cookie2VersionAttributeHandler(null));
  }

  private String createPortAttribute(int[] paramArrayOfInt)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = paramArrayOfInt.length;
    for (int j = 0; j < i; j++)
    {
      if (j > 0)
        localStringBuffer.append(",");
      localStringBuffer.append(paramArrayOfInt[j]);
    }
    return localStringBuffer.toString();
  }

  private void doFormatCookie2(Cookie2 paramCookie2, StringBuffer paramStringBuffer)
  {
    String str1 = paramCookie2.getName();
    String str2 = paramCookie2.getValue();
    if (str2 == null)
      str2 = "";
    this.formatter.format(paramStringBuffer, new NameValuePair(str1, str2));
    if ((paramCookie2.getDomain() != null) && (paramCookie2.isDomainAttributeSpecified()))
    {
      paramStringBuffer.append("; ");
      this.formatter.format(paramStringBuffer, new NameValuePair("$Domain", paramCookie2.getDomain()));
    }
    if ((paramCookie2.getPath() != null) && (paramCookie2.isPathAttributeSpecified()))
    {
      paramStringBuffer.append("; ");
      this.formatter.format(paramStringBuffer, new NameValuePair("$Path", paramCookie2.getPath()));
    }
    if (paramCookie2.isPortAttributeSpecified())
    {
      String str3 = "";
      if (!paramCookie2.isPortAttributeBlank())
        str3 = createPortAttribute(paramCookie2.getPorts());
      paramStringBuffer.append("; ");
      this.formatter.format(paramStringBuffer, new NameValuePair("$Port", str3));
    }
  }

  private static String getEffectiveHost(String paramString)
  {
    String str = paramString.toLowerCase();
    if (paramString.indexOf('.') < 0)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append(str);
      localStringBuffer.append(".local");
      str = localStringBuffer.toString();
    }
    return str;
  }

  private int[] parsePortAttribute(String paramString)
  {
    StringTokenizer localStringTokenizer = new StringTokenizer(paramString, ",");
    int[] arrayOfInt = new int[localStringTokenizer.countTokens()];
    int i = 0;
    try
    {
      while (localStringTokenizer.hasMoreTokens())
      {
        arrayOfInt[i] = Integer.parseInt(localStringTokenizer.nextToken().trim());
        if (arrayOfInt[i] < 0)
          throw new MalformedCookieException("Invalid Port attribute.");
        i++;
      }
      return arrayOfInt;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("Invalid Port attribute: ");
      localStringBuffer.append(localNumberFormatException.getMessage());
      throw new MalformedCookieException(localStringBuffer.toString());
    }
  }

  private boolean portMatch(int paramInt, int[] paramArrayOfInt)
  {
    int i = paramArrayOfInt.length;
    boolean bool;
    for (int j = 0; ; j++)
    {
      bool = false;
      if (j >= i)
        break;
      if (paramInt == paramArrayOfInt[j])
      {
        bool = true;
        break;
      }
    }
    return bool;
  }

  public boolean domainMatch(String paramString1, String paramString2)
  {
    boolean bool;
    if ((!paramString1.equals(paramString2)) && ((!paramString2.startsWith(".")) || (!paramString1.endsWith(paramString2))))
      bool = false;
    else
      bool = true;
    return bool;
  }

  protected CookieAttributeHandler findAttribHandler(String paramString)
  {
    return (CookieAttributeHandler)this.attribHandlerMap.get(paramString);
  }

  public String formatCookie(Cookie paramCookie)
  {
    LOG.trace("enter RFC2965Spec.formatCookie(Cookie)");
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if ((paramCookie instanceof Cookie2))
    {
      Cookie2 localCookie2 = (Cookie2)paramCookie;
      int i = localCookie2.getVersion();
      StringBuffer localStringBuffer = new StringBuffer();
      this.formatter.format(localStringBuffer, new NameValuePair("$Version", Integer.toString(i)));
      localStringBuffer.append("; ");
      doFormatCookie2(localCookie2, localStringBuffer);
      return localStringBuffer.toString();
    }
    return this.rfc2109.formatCookie(paramCookie);
  }

  public String formatCookies(Cookie[] paramArrayOfCookie)
  {
    LOG.trace("enter RFC2965Spec.formatCookieHeader(Cookie[])");
    if (paramArrayOfCookie == null)
      throw new IllegalArgumentException("Cookies may not be null");
    int i = 0;
    int j = 0;
    int k = -1;
    while (j < paramArrayOfCookie.length)
    {
      Cookie localCookie = paramArrayOfCookie[j];
      if (!(localCookie instanceof Cookie2))
      {
        m = 1;
        break label84;
      }
      if (localCookie.getVersion() > k)
        k = localCookie.getVersion();
      j++;
    }
    int m = 0;
    label84: if (k < 0)
      k = 0;
    if ((m == 0) && (k >= 1))
    {
      Arrays.sort(paramArrayOfCookie, PATH_COMPOARATOR);
      StringBuffer localStringBuffer = new StringBuffer();
      this.formatter.format(localStringBuffer, new NameValuePair("$Version", Integer.toString(k)));
      while (i < paramArrayOfCookie.length)
      {
        localStringBuffer.append("; ");
        doFormatCookie2((Cookie2)paramArrayOfCookie[i], localStringBuffer);
        i++;
      }
      return localStringBuffer.toString();
    }
    return this.rfc2109.formatCookies(paramArrayOfCookie);
  }

  protected CookieAttributeHandler getAttribHandler(String paramString)
  {
    CookieAttributeHandler localCookieAttributeHandler = findAttribHandler(paramString);
    if (localCookieAttributeHandler == null)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("Handler not registered for ");
      localStringBuffer.append(paramString);
      localStringBuffer.append(" attribute.");
      throw new IllegalStateException(localStringBuffer.toString());
    }
    return localCookieAttributeHandler;
  }

  protected Iterator getAttribHandlerIterator()
  {
    return this.attribHandlerList.iterator();
  }

  public int getVersion()
  {
    return 1;
  }

  public Header getVersionHeader()
  {
    ParameterFormatter localParameterFormatter = new ParameterFormatter();
    StringBuffer localStringBuffer = new StringBuffer();
    localParameterFormatter.format(localStringBuffer, new NameValuePair("$Version", Integer.toString(getVersion())));
    return new Header("Cookie2", localStringBuffer.toString(), true);
  }

  public boolean match(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Cookie paramCookie)
  {
    LOG.trace("enter RFC2965.match(String, int, String, boolean, Cookie");
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if ((paramCookie instanceof Cookie2))
    {
      if ((paramCookie.isPersistent()) && (paramCookie.isExpired()))
        return false;
      CookieOrigin localCookieOrigin = new CookieOrigin(getEffectiveHost(paramString1), paramInt, paramString2, paramBoolean);
      Iterator localIterator = getAttribHandlerIterator();
      while (localIterator.hasNext())
        if (!((CookieAttributeHandler)localIterator.next()).match(paramCookie, localCookieOrigin))
          return false;
      return true;
    }
    return this.rfc2109.match(paramString1, paramInt, paramString2, paramBoolean, paramCookie);
  }

  public Cookie[] parse(String paramString1, int paramInt, String paramString2, boolean paramBoolean, String paramString3)
  {
    int i = paramInt;
    LOG.trace("enter RFC2965Spec.parse(String, int, String, boolean, String)");
    if (paramString1 == null)
      throw new IllegalArgumentException("Host of origin may not be null");
    if (paramString1.trim().equals(""))
      throw new IllegalArgumentException("Host of origin may not be blank");
    if (i < 0)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("Invalid port: ");
      localStringBuffer.append(i);
      throw new IllegalArgumentException(localStringBuffer.toString());
    }
    if (paramString2 == null)
      throw new IllegalArgumentException("Path of origin may not be null.");
    if (paramString3 == null)
      throw new IllegalArgumentException("Header may not be null.");
    String str1;
    if (paramString2.trim().equals(""))
      str1 = "/";
    else
      str1 = paramString2;
    String str2 = getEffectiveHost(paramString1);
    HeaderElement[] arrayOfHeaderElement = HeaderElement.parseElements(paramString3.toCharArray());
    LinkedList localLinkedList = new LinkedList();
    int j = 0;
    while (j < arrayOfHeaderElement.length)
    {
      HeaderElement localHeaderElement = arrayOfHeaderElement[j];
      try
      {
        String str3 = localHeaderElement.getName();
        String str4 = localHeaderElement.getValue();
        int[] arrayOfInt = { i };
        Cookie2 localCookie2 = new Cookie2(str2, str3, str4, str1, null, false, arrayOfInt);
        NameValuePair[] arrayOfNameValuePair = localHeaderElement.getParameters();
        if (arrayOfNameValuePair != null)
        {
          HashMap localHashMap = new HashMap(arrayOfNameValuePair.length);
          for (int k = -1 + arrayOfNameValuePair.length; k >= 0; k--)
          {
            NameValuePair localNameValuePair = arrayOfNameValuePair[k];
            localHashMap.put(localNameValuePair.getName().toLowerCase(), localNameValuePair);
          }
          Iterator localIterator = localHashMap.entrySet().iterator();
          while (localIterator.hasNext())
            parseAttribute((NameValuePair)((Map.Entry)localIterator.next()).getValue(), localCookie2);
        }
        localLinkedList.add(localCookie2);
        j++;
        i = paramInt;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        throw new MalformedCookieException(localIllegalArgumentException.getMessage());
      }
    }
    return (Cookie[])localLinkedList.toArray(new Cookie[localLinkedList.size()]);
  }

  public Cookie[] parse(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Header paramHeader)
  {
    LOG.trace("enter RFC2965.parse(String, int, String, boolean, Header)");
    if (paramHeader == null)
      throw new IllegalArgumentException("Header may not be null.");
    if (paramHeader.getName() == null)
      throw new IllegalArgumentException("Header name may not be null.");
    if (paramHeader.getName().equalsIgnoreCase("set-cookie2"))
      return parse(paramString1, paramInt, paramString2, paramBoolean, paramHeader.getValue());
    if (paramHeader.getName().equalsIgnoreCase("set-cookie"))
      return this.rfc2109.parse(paramString1, paramInt, paramString2, paramBoolean, paramHeader.getValue());
    throw new MalformedCookieException("Header name is not valid. RFC 2965 supports \"set-cookie\" and \"set-cookie2\" headers.");
  }

  public void parseAttribute(NameValuePair paramNameValuePair, Cookie paramCookie)
  {
    if (paramNameValuePair == null)
      throw new IllegalArgumentException("Attribute may not be null.");
    if (paramNameValuePair.getName() == null)
      throw new IllegalArgumentException("Attribute Name may not be null.");
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null.");
    String str1 = paramNameValuePair.getName().toLowerCase();
    String str2 = paramNameValuePair.getValue();
    CookieAttributeHandler localCookieAttributeHandler = findAttribHandler(str1);
    if (localCookieAttributeHandler == null)
    {
      if (LOG.isDebugEnabled())
      {
        Log localLog = LOG;
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append("Unrecognized cookie attribute: ");
        localStringBuffer.append(paramNameValuePair.toString());
        localLog.debug(localStringBuffer.toString());
      }
    }
    else
      localCookieAttributeHandler.parse(paramCookie, str2);
  }

  protected void registerAttribHandler(String paramString, CookieAttributeHandler paramCookieAttributeHandler)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Attribute name may not be null");
    if (paramCookieAttributeHandler == null)
      throw new IllegalArgumentException("Attribute handler may not be null");
    if (!this.attribHandlerList.contains(paramCookieAttributeHandler))
      this.attribHandlerList.add(paramCookieAttributeHandler);
    this.attribHandlerMap.put(paramString, paramCookieAttributeHandler);
  }

  public void validate(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Cookie paramCookie)
  {
    LOG.trace("enter RFC2965Spec.validate(String, int, String, boolean, Cookie)");
    if ((paramCookie instanceof Cookie2))
    {
      if (paramCookie.getName().indexOf(' ') != -1)
        throw new MalformedCookieException("Cookie name may not contain blanks");
      if (paramCookie.getName().startsWith("$"))
        throw new MalformedCookieException("Cookie name may not start with $");
      CookieOrigin localCookieOrigin = new CookieOrigin(getEffectiveHost(paramString1), paramInt, paramString2, paramBoolean);
      Iterator localIterator = getAttribHandlerIterator();
      while (localIterator.hasNext())
        ((CookieAttributeHandler)localIterator.next()).validate(paramCookie, localCookieOrigin);
    }
    this.rfc2109.validate(paramString1, paramInt, paramString2, paramBoolean, paramCookie);
  }

  private class Cookie2DomainAttributeHandler
    implements CookieAttributeHandler
  {
    private Cookie2DomainAttributeHandler()
    {
    }

    Cookie2DomainAttributeHandler(RFC2965Spec.1 arg2)
    {
      this();
    }

    public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    {
      if (paramCookie == null)
        throw new IllegalArgumentException("Cookie may not be null");
      if (paramCookieOrigin == null)
        throw new IllegalArgumentException("Cookie origin may not be null");
      String str1 = paramCookieOrigin.getHost().toLowerCase();
      String str2 = paramCookie.getDomain();
      if (!RFC2965Spec.this.domainMatch(str1, str2))
        return false;
      return str1.substring(0, str1.length() - str2.length()).indexOf('.') == -1;
    }

    public void parse(Cookie paramCookie, String paramString)
    {
      if (paramCookie == null)
        throw new IllegalArgumentException("Cookie may not be null");
      if (paramString == null)
        throw new MalformedCookieException("Missing value for domain attribute");
      if (paramString.trim().equals(""))
        throw new MalformedCookieException("Blank value for domain attribute");
      String str = paramString.toLowerCase();
      if (!str.startsWith("."))
      {
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append(".");
        localStringBuffer.append(str);
        str = localStringBuffer.toString();
      }
      paramCookie.setDomain(str);
      paramCookie.setDomainAttributeSpecified(true);
    }

    public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    {
      if (paramCookie == null)
        throw new IllegalArgumentException("Cookie may not be null");
      if (paramCookieOrigin == null)
        throw new IllegalArgumentException("Cookie origin may not be null");
      String str1 = paramCookieOrigin.getHost().toLowerCase();
      if (paramCookie.getDomain() == null)
        throw new MalformedCookieException("Invalid cookie state: domain not specified");
      String str2 = paramCookie.getDomain().toLowerCase();
      if (paramCookie.isDomainAttributeSpecified())
      {
        if (!str2.startsWith("."))
        {
          StringBuffer localStringBuffer2 = new StringBuffer();
          localStringBuffer2.append("Domain attribute \"");
          localStringBuffer2.append(paramCookie.getDomain());
          localStringBuffer2.append("\" violates RFC 2109: domain must start with a dot");
          throw new MalformedCookieException(localStringBuffer2.toString());
        }
        int i = str2.indexOf('.', 1);
        if (((i < 0) || (i == str2.length() - 1)) && (!str2.equals(".local")))
        {
          StringBuffer localStringBuffer3 = new StringBuffer();
          localStringBuffer3.append("Domain attribute \"");
          localStringBuffer3.append(paramCookie.getDomain());
          localStringBuffer3.append("\" violates RFC 2965: the value contains no embedded dots ");
          localStringBuffer3.append("and the value is not .local");
          throw new MalformedCookieException(localStringBuffer3.toString());
        }
        if (!RFC2965Spec.this.domainMatch(str1, str2))
        {
          StringBuffer localStringBuffer4 = new StringBuffer();
          localStringBuffer4.append("Domain attribute \"");
          localStringBuffer4.append(paramCookie.getDomain());
          localStringBuffer4.append("\" violates RFC 2965: effective host name does not ");
          localStringBuffer4.append("domain-match domain attribute.");
          throw new MalformedCookieException(localStringBuffer4.toString());
        }
        if (str1.substring(0, str1.length() - str2.length()).indexOf('.') != -1)
        {
          StringBuffer localStringBuffer5 = new StringBuffer();
          localStringBuffer5.append("Domain attribute \"");
          localStringBuffer5.append(paramCookie.getDomain());
          localStringBuffer5.append("\" violates RFC 2965: ");
          localStringBuffer5.append("effective host minus domain may not contain any dots");
          throw new MalformedCookieException(localStringBuffer5.toString());
        }
      }
      else if (!paramCookie.getDomain().equals(str1))
      {
        StringBuffer localStringBuffer1 = new StringBuffer();
        localStringBuffer1.append("Illegal domain attribute: \"");
        localStringBuffer1.append(paramCookie.getDomain());
        localStringBuffer1.append("\".");
        localStringBuffer1.append("Domain of origin: \"");
        localStringBuffer1.append(str1);
        localStringBuffer1.append("\"");
        throw new MalformedCookieException(localStringBuffer1.toString());
      }
    }
  }

  private class Cookie2MaxageAttributeHandler
    implements CookieAttributeHandler
  {
    private Cookie2MaxageAttributeHandler()
    {
    }

    Cookie2MaxageAttributeHandler(RFC2965Spec.1 arg2)
    {
      this();
    }

    public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    {
      return true;
    }

    // ERROR //
    public void parse(Cookie paramCookie, String paramString)
    {
      // Byte code:
      //   0: aload_1
      //   1: ifnonnull +13 -> 14
      //   4: new 26	java/lang/IllegalArgumentException
      //   7: dup
      //   8: ldc 28
      //   10: invokespecial 31	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
      //   13: athrow
      //   14: aload_2
      //   15: ifnonnull +13 -> 28
      //   18: new 33	org/apache/commons/httpclient/cookie/MalformedCookieException
      //   21: dup
      //   22: ldc 35
      //   24: invokespecial 36	org/apache/commons/httpclient/cookie/MalformedCookieException:<init>	(Ljava/lang/String;)V
      //   27: athrow
      //   28: aload_2
      //   29: invokestatic 42	java/lang/Integer:parseInt	(Ljava/lang/String;)I
      //   32: istore_3
      //   33: goto +5 -> 38
      //   36: iconst_m1
      //   37: istore_3
      //   38: iload_3
      //   39: ifge +13 -> 52
      //   42: new 33	org/apache/commons/httpclient/cookie/MalformedCookieException
      //   45: dup
      //   46: ldc 44
      //   48: invokespecial 36	org/apache/commons/httpclient/cookie/MalformedCookieException:<init>	(Ljava/lang/String;)V
      //   51: athrow
      //   52: aload_1
      //   53: new 46	java/util/Date
      //   56: dup
      //   57: invokestatic 52	java/lang/System:currentTimeMillis	()J
      //   60: ldc2_w 53
      //   63: iload_3
      //   64: i2l
      //   65: lmul
      //   66: ladd
      //   67: invokespecial 57	java/util/Date:<init>	(J)V
      //   70: invokevirtual 63	org/apache/commons/httpclient/Cookie:setExpiryDate	(Ljava/util/Date;)V
      //   73: return
      //
      // Exception table:
      //   from	to	target	type
      //   28	33	36	java/lang/NumberFormatException
    }

    public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    {
    }
  }

  private class Cookie2PathAttributeHandler
    implements CookieAttributeHandler
  {
    private Cookie2PathAttributeHandler()
    {
    }

    Cookie2PathAttributeHandler(RFC2965Spec.1 arg2)
    {
      this();
    }

    public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    {
      if (paramCookie == null)
        throw new IllegalArgumentException("Cookie may not be null");
      if (paramCookieOrigin == null)
        throw new IllegalArgumentException("Cookie origin may not be null");
      String str = paramCookieOrigin.getPath();
      if (paramCookie.getPath() == null)
      {
        CookieSpecBase.LOG.warn("Invalid cookie state: path attribute is null.");
        return false;
      }
      if (str.trim().equals(""))
        str = "/";
      return RFC2965Spec.this.pathMatch(str, paramCookie.getPath());
    }

    public void parse(Cookie paramCookie, String paramString)
    {
      if (paramCookie == null)
        throw new IllegalArgumentException("Cookie may not be null");
      if (paramString == null)
        throw new MalformedCookieException("Missing value for path attribute");
      if (paramString.trim().equals(""))
        throw new MalformedCookieException("Blank value for path attribute");
      paramCookie.setPath(paramString);
      paramCookie.setPathAttributeSpecified(true);
    }

    public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    {
      if (paramCookie == null)
        throw new IllegalArgumentException("Cookie may not be null");
      if (paramCookieOrigin == null)
        throw new IllegalArgumentException("Cookie origin may not be null");
      String str = paramCookieOrigin.getPath();
      if (str == null)
        throw new IllegalArgumentException("Path of origin host may not be null.");
      if (paramCookie.getPath() == null)
        throw new MalformedCookieException("Invalid cookie state: path attribute is null.");
      if (str.trim().equals(""))
        str = "/";
      if (!RFC2965Spec.this.pathMatch(str, paramCookie.getPath()))
      {
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append("Illegal path attribute \"");
        localStringBuffer.append(paramCookie.getPath());
        localStringBuffer.append("\". Path of origin: \"");
        localStringBuffer.append(str);
        localStringBuffer.append("\"");
        throw new MalformedCookieException(localStringBuffer.toString());
      }
    }
  }

  private class Cookie2PortAttributeHandler
    implements CookieAttributeHandler
  {
    private Cookie2PortAttributeHandler()
    {
    }

    Cookie2PortAttributeHandler(RFC2965Spec.1 arg2)
    {
      this();
    }

    public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    {
      if (paramCookie == null)
        throw new IllegalArgumentException("Cookie may not be null");
      if (paramCookieOrigin == null)
        throw new IllegalArgumentException("Cookie origin may not be null");
      if ((paramCookie instanceof Cookie2))
      {
        Cookie2 localCookie2 = (Cookie2)paramCookie;
        int i = paramCookieOrigin.getPort();
        if (localCookie2.isPortAttributeSpecified())
        {
          if (localCookie2.getPorts() == null)
          {
            CookieSpecBase.LOG.warn("Invalid cookie state: port not specified");
            return false;
          }
          if (!RFC2965Spec.this.portMatch(i, localCookie2.getPorts()))
            return false;
        }
        return true;
      }
      return false;
    }

    public void parse(Cookie paramCookie, String paramString)
    {
      if (paramCookie == null)
        throw new IllegalArgumentException("Cookie may not be null");
      if ((paramCookie instanceof Cookie2))
      {
        Cookie2 localCookie2 = (Cookie2)paramCookie;
        if ((paramString != null) && (!paramString.trim().equals("")))
          localCookie2.setPorts(RFC2965Spec.this.parsePortAttribute(paramString));
        else
          localCookie2.setPortAttributeBlank(true);
        localCookie2.setPortAttributeSpecified(true);
      }
    }

    public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    {
      if (paramCookie == null)
        throw new IllegalArgumentException("Cookie may not be null");
      if (paramCookieOrigin == null)
        throw new IllegalArgumentException("Cookie origin may not be null");
      if ((paramCookie instanceof Cookie2))
      {
        Cookie2 localCookie2 = (Cookie2)paramCookie;
        int i = paramCookieOrigin.getPort();
        if ((localCookie2.isPortAttributeSpecified()) && (!RFC2965Spec.this.portMatch(i, localCookie2.getPorts())))
          throw new MalformedCookieException("Port attribute violates RFC 2965: Request port not found in cookie's port list.");
      }
    }
  }

  private class Cookie2VersionAttributeHandler
    implements CookieAttributeHandler
  {
    private Cookie2VersionAttributeHandler()
    {
    }

    Cookie2VersionAttributeHandler(RFC2965Spec.1 arg2)
    {
      this();
    }

    public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    {
      return true;
    }

    // ERROR //
    public void parse(Cookie paramCookie, String paramString)
    {
      // Byte code:
      //   0: aload_1
      //   1: ifnonnull +13 -> 14
      //   4: new 26	java/lang/IllegalArgumentException
      //   7: dup
      //   8: ldc 28
      //   10: invokespecial 31	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
      //   13: athrow
      //   14: aload_1
      //   15: instanceof 33
      //   18: ifeq +60 -> 78
      //   21: aload_1
      //   22: checkcast 33	org/apache/commons/httpclient/cookie/Cookie2
      //   25: astore_3
      //   26: aload_2
      //   27: ifnonnull +13 -> 40
      //   30: new 35	org/apache/commons/httpclient/cookie/MalformedCookieException
      //   33: dup
      //   34: ldc 37
      //   36: invokespecial 38	org/apache/commons/httpclient/cookie/MalformedCookieException:<init>	(Ljava/lang/String;)V
      //   39: athrow
      //   40: aload_2
      //   41: invokestatic 44	java/lang/Integer:parseInt	(Ljava/lang/String;)I
      //   44: istore 4
      //   46: goto +6 -> 52
      //   49: iconst_m1
      //   50: istore 4
      //   52: iload 4
      //   54: ifge +13 -> 67
      //   57: new 35	org/apache/commons/httpclient/cookie/MalformedCookieException
      //   60: dup
      //   61: ldc 46
      //   63: invokespecial 38	org/apache/commons/httpclient/cookie/MalformedCookieException:<init>	(Ljava/lang/String;)V
      //   66: athrow
      //   67: aload_3
      //   68: iload 4
      //   70: invokevirtual 50	org/apache/commons/httpclient/cookie/Cookie2:setVersion	(I)V
      //   73: aload_3
      //   74: iconst_1
      //   75: invokevirtual 54	org/apache/commons/httpclient/cookie/Cookie2:setVersionAttributeSpecified	(Z)V
      //   78: return
      //
      // Exception table:
      //   from	to	target	type
      //   40	46	49	java/lang/NumberFormatException
    }

    public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    {
      if (paramCookie == null)
        throw new IllegalArgumentException("Cookie may not be null");
      if (((paramCookie instanceof Cookie2)) && (!((Cookie2)paramCookie).isVersionAttributeSpecified()))
        throw new MalformedCookieException("Violates RFC 2965. Version attribute is required.");
    }
  }

  private class CookieCommentAttributeHandler
    implements CookieAttributeHandler
  {
    private CookieCommentAttributeHandler()
    {
    }

    CookieCommentAttributeHandler(RFC2965Spec.1 arg2)
    {
      this();
    }

    public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    {
      return true;
    }

    public void parse(Cookie paramCookie, String paramString)
    {
      paramCookie.setComment(paramString);
    }

    public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    {
    }
  }

  private class CookieCommentUrlAttributeHandler
    implements CookieAttributeHandler
  {
    private CookieCommentUrlAttributeHandler()
    {
    }

    CookieCommentUrlAttributeHandler(RFC2965Spec.1 arg2)
    {
      this();
    }

    public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    {
      return true;
    }

    public void parse(Cookie paramCookie, String paramString)
    {
      if ((paramCookie instanceof Cookie2))
        ((Cookie2)paramCookie).setCommentURL(paramString);
    }

    public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    {
    }
  }

  private class CookieDiscardAttributeHandler
    implements CookieAttributeHandler
  {
    private CookieDiscardAttributeHandler()
    {
    }

    CookieDiscardAttributeHandler(RFC2965Spec.1 arg2)
    {
      this();
    }

    public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    {
      return true;
    }

    public void parse(Cookie paramCookie, String paramString)
    {
      if ((paramCookie instanceof Cookie2))
        ((Cookie2)paramCookie).setDiscard(true);
    }

    public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    {
    }
  }

  private class CookieSecureAttributeHandler
    implements CookieAttributeHandler
  {
    private CookieSecureAttributeHandler()
    {
    }

    CookieSecureAttributeHandler(RFC2965Spec.1 arg2)
    {
      this();
    }

    public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    {
      if (paramCookie == null)
        throw new IllegalArgumentException("Cookie may not be null");
      if (paramCookieOrigin == null)
        throw new IllegalArgumentException("Cookie origin may not be null");
      boolean bool;
      if (paramCookie.getSecure() == paramCookieOrigin.isSecure())
        bool = true;
      else
        bool = false;
      return bool;
    }

    public void parse(Cookie paramCookie, String paramString)
    {
      paramCookie.setSecure(true);
    }

    public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    {
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.cookie.RFC2965Spec
 * JD-Core Version:    0.6.1
 */