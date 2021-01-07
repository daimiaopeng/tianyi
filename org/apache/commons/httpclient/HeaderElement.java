package org.apache.commons.httpclient;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.httpclient.util.ParameterParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HeaderElement extends NameValuePair
{
  private static final Log LOG = LogFactory.getLog(localClass);
  static Class class$org$apache$commons$httpclient$HeaderElement;
  private NameValuePair[] parameters = null;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$HeaderElement == null)
    {
      localClass = class$("org.apache.commons.httpclient.HeaderElement");
      class$org$apache$commons$httpclient$HeaderElement = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$HeaderElement;
    }
  }

  public HeaderElement()
  {
    this(null, null, null);
  }

  public HeaderElement(String paramString1, String paramString2)
  {
    this(paramString1, paramString2, null);
  }

  public HeaderElement(String paramString1, String paramString2, NameValuePair[] paramArrayOfNameValuePair)
  {
    super(paramString1, paramString2);
    this.parameters = paramArrayOfNameValuePair;
  }

  public HeaderElement(char[] paramArrayOfChar)
  {
    this(paramArrayOfChar, 0, paramArrayOfChar.length);
  }

  public HeaderElement(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    this();
    if (paramArrayOfChar == null)
      return;
    List localList = new ParameterParser().parse(paramArrayOfChar, paramInt1, paramInt2, ';');
    if (localList.size() > 0)
    {
      NameValuePair localNameValuePair = (NameValuePair)localList.remove(0);
      setName(localNameValuePair.getName());
      setValue(localNameValuePair.getValue());
      if (localList.size() > 0)
        this.parameters = ((NameValuePair[])localList.toArray(new NameValuePair[localList.size()]));
    }
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

  public static final HeaderElement[] parse(String paramString)
  {
    LOG.trace("enter HeaderElement.parse(String)");
    if (paramString == null)
      return new HeaderElement[0];
    return parseElements(paramString.toCharArray());
  }

  public static final HeaderElement[] parseElements(String paramString)
  {
    LOG.trace("enter HeaderElement.parseElements(String)");
    if (paramString == null)
      return new HeaderElement[0];
    return parseElements(paramString.toCharArray());
  }

  public static final HeaderElement[] parseElements(char[] paramArrayOfChar)
  {
    LOG.trace("enter HeaderElement.parseElements(char[])");
    int i = 0;
    if (paramArrayOfChar == null)
      return new HeaderElement[0];
    ArrayList localArrayList = new ArrayList();
    int j = paramArrayOfChar.length;
    int k = 0;
    int m = 0;
    while (i < j)
    {
      int n = paramArrayOfChar[i];
      if (n == 34)
        k ^= 1;
      HeaderElement localHeaderElement;
      if ((k == 0) && (n == 44))
      {
        localHeaderElement = new HeaderElement(paramArrayOfChar, m, i);
        m = i + 1;
      }
      else
      {
        int i1 = j - 1;
        localHeaderElement = null;
        if (i == i1)
          localHeaderElement = new HeaderElement(paramArrayOfChar, m, j);
      }
      if ((localHeaderElement != null) && (localHeaderElement.getName() != null))
        localArrayList.add(localHeaderElement);
      i++;
    }
    return (HeaderElement[])localArrayList.toArray(new HeaderElement[localArrayList.size()]);
  }

  public NameValuePair getParameterByName(String paramString)
  {
    LOG.trace("enter HeaderElement.getParameterByName(String)");
    if (paramString == null)
      throw new IllegalArgumentException("Name may not be null");
    NameValuePair[] arrayOfNameValuePair = getParameters();
    Object localObject = null;
    if (arrayOfNameValuePair != null)
      for (int i = 0; ; i++)
      {
        int j = arrayOfNameValuePair.length;
        localObject = null;
        if (i >= j)
          break;
        NameValuePair localNameValuePair = arrayOfNameValuePair[i];
        if (localNameValuePair.getName().equalsIgnoreCase(paramString))
        {
          localObject = localNameValuePair;
          break;
        }
      }
    return localObject;
  }

  public NameValuePair[] getParameters()
  {
    return this.parameters;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HeaderElement
 * JD-Core Version:    0.6.1
 */