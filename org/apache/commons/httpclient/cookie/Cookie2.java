package org.apache.commons.httpclient.cookie;

import java.util.Date;
import org.apache.commons.httpclient.Cookie;

public class Cookie2 extends Cookie
{
  public static final String COMMENT = "comment";
  public static final String COMMENTURL = "commenturl";
  public static final String DISCARD = "discard";
  public static final String DOMAIN = "domain";
  public static final String MAXAGE = "max-age";
  public static final String PATH = "path";
  public static final String PORT = "port";
  public static final String SECURE = "secure";
  public static final String VERSION = "version";
  private String cookieCommentURL;
  private int[] cookiePorts;
  private boolean discard = false;
  private boolean hasPortAttribute = false;
  private boolean hasVersionAttribute = false;
  private boolean isPortAttributeBlank = false;

  public Cookie2()
  {
    super(null, "noname", null, null, null, false);
  }

  public Cookie2(String paramString1, String paramString2, String paramString3)
  {
    super(paramString1, paramString2, paramString3);
  }

  public Cookie2(String paramString1, String paramString2, String paramString3, String paramString4, Date paramDate, boolean paramBoolean)
  {
    super(paramString1, paramString2, paramString3, paramString4, paramDate, paramBoolean);
  }

  public Cookie2(String paramString1, String paramString2, String paramString3, String paramString4, Date paramDate, boolean paramBoolean, int[] paramArrayOfInt)
  {
    super(paramString1, paramString2, paramString3, paramString4, paramDate, paramBoolean);
    setPorts(paramArrayOfInt);
  }

  public String getCommentURL()
  {
    return this.cookieCommentURL;
  }

  public int[] getPorts()
  {
    return this.cookiePorts;
  }

  public boolean isPersistent()
  {
    boolean bool;
    if ((getExpiryDate() != null) && (!this.discard))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public boolean isPortAttributeBlank()
  {
    return this.isPortAttributeBlank;
  }

  public boolean isPortAttributeSpecified()
  {
    return this.hasPortAttribute;
  }

  public boolean isVersionAttributeSpecified()
  {
    return this.hasVersionAttribute;
  }

  public void setCommentURL(String paramString)
  {
    this.cookieCommentURL = paramString;
  }

  public void setDiscard(boolean paramBoolean)
  {
    this.discard = paramBoolean;
  }

  public void setPortAttributeBlank(boolean paramBoolean)
  {
    this.isPortAttributeBlank = paramBoolean;
  }

  public void setPortAttributeSpecified(boolean paramBoolean)
  {
    this.hasPortAttribute = paramBoolean;
  }

  public void setPorts(int[] paramArrayOfInt)
  {
    this.cookiePorts = paramArrayOfInt;
  }

  public void setVersionAttributeSpecified(boolean paramBoolean)
  {
    this.hasVersionAttribute = paramBoolean;
  }

  public String toExternalForm()
  {
    return CookiePolicy.getCookieSpec("rfc2965").formatCookie(this);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.cookie.Cookie2
 * JD-Core Version:    0.6.1
 */