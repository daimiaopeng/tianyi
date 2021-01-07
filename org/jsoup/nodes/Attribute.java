package org.jsoup.nodes;

import java.util.Arrays;
import java.util.Map.Entry;
import org.jsoup.helper.Validate;

public class Attribute
  implements Cloneable, Map.Entry<String, String>
{
  private static final String[] booleanAttributes = { "allowfullscreen", "async", "autofocus", "checked", "compact", "declare", "default", "defer", "disabled", "formnovalidate", "hidden", "inert", "ismap", "itemscope", "multiple", "muted", "nohref", "noresize", "noshade", "novalidate", "nowrap", "open", "readonly", "required", "reversed", "seamless", "selected", "sortable", "truespeed", "typemustmatch" };
  private String key;
  private String value;

  public Attribute(String paramString1, String paramString2)
  {
    Validate.notEmpty(paramString1);
    Validate.notNull(paramString2);
    this.key = paramString1.trim().toLowerCase();
    this.value = paramString2;
  }

  public static Attribute createFromEncoded(String paramString1, String paramString2)
  {
    return new Attribute(paramString1, Entities.unescape(paramString2, true));
  }

  public Attribute clone()
  {
    try
    {
      Attribute localAttribute = (Attribute)super.clone();
      return localAttribute;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new RuntimeException(localCloneNotSupportedException);
    }
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if (!(paramObject instanceof Attribute))
      return false;
    Attribute localAttribute = (Attribute)paramObject;
    if (this.key != null ? !this.key.equals(localAttribute.key) : localAttribute.key != null)
      return false;
    return this.value != null ? this.value.equals(localAttribute.value) : localAttribute.value == null;
  }

  public String getKey()
  {
    return this.key;
  }

  public String getValue()
  {
    return this.value;
  }

  public int hashCode()
  {
    int i;
    if (this.key != null)
      i = this.key.hashCode();
    else
      i = 0;
    int j = i * 31;
    String str = this.value;
    int k = 0;
    if (str != null)
      k = this.value.hashCode();
    return j + k;
  }

  public String html()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    html(localStringBuilder, new Document("").outputSettings());
    return localStringBuilder.toString();
  }

  protected void html(StringBuilder paramStringBuilder, Document.OutputSettings paramOutputSettings)
  {
    paramStringBuilder.append(this.key);
    if (!shouldCollapseAttribute(paramOutputSettings))
    {
      paramStringBuilder.append("=\"");
      Entities.escape(paramStringBuilder, this.value, paramOutputSettings, true, false, false);
      paramStringBuilder.append('"');
    }
  }

  protected boolean isDataAttribute()
  {
    boolean bool;
    if ((this.key.startsWith("data-")) && (this.key.length() > "data-".length()))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public void setKey(String paramString)
  {
    Validate.notEmpty(paramString);
    this.key = paramString.trim().toLowerCase();
  }

  public String setValue(String paramString)
  {
    Validate.notNull(paramString);
    String str = this.value;
    this.value = paramString;
    return str;
  }

  protected final boolean shouldCollapseAttribute(Document.OutputSettings paramOutputSettings)
  {
    boolean bool;
    if ((("".equals(this.value)) || (this.value.equalsIgnoreCase(this.key))) && (paramOutputSettings.syntax() == Document.OutputSettings.Syntax.html) && (Arrays.binarySearch(booleanAttributes, this.key) >= 0))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public String toString()
  {
    return html();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.nodes.Attribute
 * JD-Core Version:    0.6.1
 */