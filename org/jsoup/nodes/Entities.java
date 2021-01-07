package org.jsoup.nodes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.CharsetEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.Set;
import org.jsoup.helper.StringUtil;
import org.jsoup.parser.Parser;

public class Entities
{
  private static final Map<String, Character> base;
  private static final Map<Character, String> baseByVal;
  private static final Map<String, Character> full;
  private static final Map<Character, String> fullByVal;
  private static final Object[][] xhtmlArray;
  private static final Map<Character, String> xhtmlByVal;

  static
  {
    Object[][] arrayOfObject; = new Object[4][];
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = "quot";
    arrayOfObject1[1] = Integer.valueOf(34);
    arrayOfObject;[0] = arrayOfObject1;
    Object[] arrayOfObject2 = new Object[2];
    arrayOfObject2[0] = "amp";
    arrayOfObject2[1] = Integer.valueOf(38);
    arrayOfObject;[1] = arrayOfObject2;
    Object[] arrayOfObject3 = new Object[2];
    arrayOfObject3[0] = "lt";
    arrayOfObject3[1] = Integer.valueOf(60);
    arrayOfObject;[2] = arrayOfObject3;
    Object[] arrayOfObject4 = new Object[2];
    arrayOfObject4[0] = "gt";
    arrayOfObject4[1] = Integer.valueOf(62);
    arrayOfObject;[3] = arrayOfObject4;
    xhtmlArray = arrayOfObject;;
    xhtmlByVal = new HashMap();
    base = loadEntities("entities-base.properties");
    baseByVal = toCharacterKey(base);
    full = loadEntities("entities-full.properties");
    fullByVal = toCharacterKey(full);
    for (Object[] arrayOfObject5 : xhtmlArray)
    {
      Character localCharacter = Character.valueOf((char)((Integer)arrayOfObject5[1]).intValue());
      xhtmlByVal.put(localCharacter, (String)arrayOfObject5[0]);
    }
  }

  static String escape(String paramString, Document.OutputSettings paramOutputSettings)
  {
    StringBuilder localStringBuilder = new StringBuilder(2 * paramString.length());
    escape(localStringBuilder, paramString, paramOutputSettings, false, false, false);
    return localStringBuilder.toString();
  }

  static void escape(StringBuilder paramStringBuilder, String paramString, Document.OutputSettings paramOutputSettings, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    EscapeMode localEscapeMode = paramOutputSettings.escapeMode();
    CharsetEncoder localCharsetEncoder = paramOutputSettings.encoder();
    Map localMap = localEscapeMode.getMap();
    int i = paramString.length();
    int j = 0;
    int k = 0;
    int m = 0;
    while (j < i)
    {
      int n = paramString.codePointAt(j);
      if (paramBoolean2)
      {
        if (StringUtil.isWhitespace(n))
        {
          if (((!paramBoolean3) || (k != 0)) && (m == 0))
          {
            paramStringBuilder.append(' ');
            m = 1;
          }
        }
        else
        {
          k = 1;
          m = 0;
        }
      }
      else if (n < 65536)
      {
        char c = (char)n;
        if (c != '"')
        {
          if (c != '&')
          {
            if (c != '<')
            {
              if (c != '>')
              {
                if (c != ' ')
                {
                  if (localCharsetEncoder.canEncode(c))
                  {
                    paramStringBuilder.append(c);
                  }
                  else if (localMap.containsKey(Character.valueOf(c)))
                  {
                    paramStringBuilder.append('&');
                    paramStringBuilder.append((String)localMap.get(Character.valueOf(c)));
                    paramStringBuilder.append(';');
                  }
                  else
                  {
                    paramStringBuilder.append("&#x");
                    paramStringBuilder.append(Integer.toHexString(n));
                    paramStringBuilder.append(';');
                  }
                }
                else if (localEscapeMode != EscapeMode.xhtml)
                  paramStringBuilder.append("&nbsp;");
                else
                  paramStringBuilder.append(c);
              }
              else if (!paramBoolean1)
                paramStringBuilder.append("&gt;");
              else
                paramStringBuilder.append(c);
            }
            else if (!paramBoolean1)
              paramStringBuilder.append("&lt;");
            else
              paramStringBuilder.append(c);
          }
          else
            paramStringBuilder.append("&amp;");
        }
        else if (paramBoolean1)
          paramStringBuilder.append("&quot;");
        else
          paramStringBuilder.append(c);
      }
      else
      {
        String str = new String(Character.toChars(n));
        if (localCharsetEncoder.canEncode(str))
        {
          paramStringBuilder.append(str);
        }
        else
        {
          paramStringBuilder.append("&#x");
          paramStringBuilder.append(Integer.toHexString(n));
          paramStringBuilder.append(';');
        }
      }
      j += Character.charCount(n);
    }
  }

  public static Character getCharacterByName(String paramString)
  {
    return (Character)full.get(paramString);
  }

  public static boolean isBaseNamedEntity(String paramString)
  {
    return base.containsKey(paramString);
  }

  public static boolean isNamedEntity(String paramString)
  {
    return full.containsKey(paramString);
  }

  private static Map<String, Character> loadEntities(String paramString)
  {
    Properties localProperties = new Properties();
    HashMap localHashMap = new HashMap();
    try
    {
      InputStream localInputStream = Entities.class.getResourceAsStream(paramString);
      localProperties.load(localInputStream);
      localInputStream.close();
      Iterator localIterator = localProperties.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        Character localCharacter = Character.valueOf((char)Integer.parseInt((String)localEntry.getValue(), 16));
        localHashMap.put((String)localEntry.getKey(), localCharacter);
      }
      return localHashMap;
    }
    catch (IOException localIOException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Error loading entities resource: ");
      localStringBuilder.append(localIOException.getMessage());
      throw new MissingResourceException(localStringBuilder.toString(), "Entities", paramString);
    }
  }

  private static Map<Character, String> toCharacterKey(Map<String, Character> paramMap)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Character localCharacter = (Character)localEntry.getValue();
      String str = (String)localEntry.getKey();
      if (localHashMap.containsKey(localCharacter))
      {
        if (str.toLowerCase().equals(str))
          localHashMap.put(localCharacter, str);
      }
      else
        localHashMap.put(localCharacter, str);
    }
    return localHashMap;
  }

  static String unescape(String paramString)
  {
    return unescape(paramString, false);
  }

  static String unescape(String paramString, boolean paramBoolean)
  {
    return Parser.unescapeEntities(paramString, paramBoolean);
  }

  public static enum EscapeMode
  {
    private Map<Character, String> map;

    static
    {
      base = new EscapeMode("base", 1, Entities.baseByVal);
      extended = new EscapeMode("extended", 2, Entities.fullByVal);
      EscapeMode[] arrayOfEscapeMode = new EscapeMode[3];
      arrayOfEscapeMode[0] = xhtml;
      arrayOfEscapeMode[1] = base;
      arrayOfEscapeMode[2] = extended;
    }

    private EscapeMode(Map<Character, String> paramMap)
    {
      this.map = paramMap;
    }

    public Map<Character, String> getMap()
    {
      return this.map;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.nodes.Entities
 * JD-Core Version:    0.6.1
 */