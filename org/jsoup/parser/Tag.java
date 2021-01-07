package org.jsoup.parser;

import java.util.HashMap;
import java.util.Map;
import org.jsoup.helper.Validate;

public class Tag
{
  private static final String[] blockTags;
  private static final String[] emptyTags;
  private static final String[] formListedTags;
  private static final String[] formSubmitTags;
  private static final String[] formatAsInlineTags;
  private static final String[] inlineTags;
  private static final String[] preserveWhitespaceTags;
  private static final Map<String, Tag> tags = new HashMap();
  private boolean canContainBlock = true;
  private boolean canContainInline = true;
  private boolean empty = false;
  private boolean formList = false;
  private boolean formSubmit = false;
  private boolean formatAsBlock = true;
  private boolean isBlock = true;
  private boolean preserveWhitespace = false;
  private boolean selfClosing = false;
  private String tagName;

  static
  {
    String[] arrayOfString1 = new String[59];
    int i = 0;
    arrayOfString1[0] = "html";
    arrayOfString1[1] = "head";
    arrayOfString1[2] = "body";
    arrayOfString1[3] = "frameset";
    arrayOfString1[4] = "script";
    arrayOfString1[5] = "noscript";
    arrayOfString1[6] = "style";
    arrayOfString1[7] = "meta";
    arrayOfString1[8] = "link";
    arrayOfString1[9] = "title";
    arrayOfString1[10] = "frame";
    arrayOfString1[11] = "noframes";
    arrayOfString1[12] = "section";
    arrayOfString1[13] = "nav";
    arrayOfString1[14] = "aside";
    arrayOfString1[15] = "hgroup";
    arrayOfString1[16] = "header";
    arrayOfString1[17] = "footer";
    arrayOfString1[18] = "p";
    arrayOfString1[19] = "h1";
    arrayOfString1[20] = "h2";
    arrayOfString1[21] = "h3";
    arrayOfString1[22] = "h4";
    arrayOfString1[23] = "h5";
    arrayOfString1[24] = "h6";
    arrayOfString1[25] = "ul";
    arrayOfString1[26] = "ol";
    arrayOfString1[27] = "pre";
    arrayOfString1[28] = "div";
    arrayOfString1[29] = "blockquote";
    arrayOfString1[30] = "hr";
    arrayOfString1[31] = "address";
    arrayOfString1[32] = "figure";
    arrayOfString1[33] = "figcaption";
    arrayOfString1[34] = "form";
    arrayOfString1[35] = "fieldset";
    arrayOfString1[36] = "ins";
    arrayOfString1[37] = "del";
    arrayOfString1[38] = "s";
    arrayOfString1[39] = "dl";
    arrayOfString1[40] = "dt";
    arrayOfString1[41] = "dd";
    arrayOfString1[42] = "li";
    arrayOfString1[43] = "table";
    arrayOfString1[44] = "caption";
    arrayOfString1[45] = "thead";
    arrayOfString1[46] = "tfoot";
    arrayOfString1[47] = "tbody";
    arrayOfString1[48] = "colgroup";
    arrayOfString1[49] = "col";
    arrayOfString1[50] = "tr";
    arrayOfString1[51] = "th";
    arrayOfString1[52] = "td";
    arrayOfString1[53] = "video";
    arrayOfString1[54] = "audio";
    arrayOfString1[55] = "canvas";
    arrayOfString1[56] = "details";
    arrayOfString1[57] = "menu";
    arrayOfString1[58] = "plaintext";
    blockTags = arrayOfString1;
    inlineTags = new String[] { "object", "base", "font", "tt", "i", "b", "u", "big", "small", "em", "strong", "dfn", "code", "samp", "kbd", "var", "cite", "abbr", "time", "acronym", "mark", "ruby", "rt", "rp", "a", "img", "br", "wbr", "map", "q", "sub", "sup", "bdo", "iframe", "embed", "span", "input", "select", "textarea", "label", "button", "optgroup", "option", "legend", "datalist", "keygen", "output", "progress", "meter", "area", "param", "source", "track", "summary", "command", "device", "area", "basefont", "bgsound", "menuitem", "param", "source", "track" };
    emptyTags = new String[] { "meta", "link", "base", "frame", "img", "br", "wbr", "embed", "hr", "input", "keygen", "col", "command", "device", "area", "basefont", "bgsound", "menuitem", "param", "source", "track" };
    formatAsInlineTags = new String[] { "title", "a", "p", "h1", "h2", "h3", "h4", "h5", "h6", "pre", "address", "li", "th", "td", "script", "style", "ins", "del", "s" };
    preserveWhitespaceTags = new String[] { "pre", "plaintext", "title", "textarea" };
    formListedTags = new String[] { "button", "fieldset", "input", "keygen", "object", "output", "select", "textarea" };
    formSubmitTags = new String[] { "input", "keygen", "object", "select", "textarea" };
    String[] arrayOfString2 = blockTags;
    int j = arrayOfString2.length;
    for (int k = 0; k < j; k++)
      register(new Tag(arrayOfString2[k]));
    String[] arrayOfString3 = inlineTags;
    int m = arrayOfString3.length;
    for (int n = 0; n < m; n++)
    {
      Tag localTag1 = new Tag(arrayOfString3[n]);
      localTag1.isBlock = false;
      localTag1.canContainBlock = false;
      localTag1.formatAsBlock = false;
      register(localTag1);
    }
    for (String str5 : emptyTags)
    {
      Tag localTag6 = (Tag)tags.get(str5);
      Validate.notNull(localTag6);
      localTag6.canContainBlock = false;
      localTag6.canContainInline = false;
      localTag6.empty = true;
    }
    for (String str4 : formatAsInlineTags)
    {
      Tag localTag5 = (Tag)tags.get(str4);
      Validate.notNull(localTag5);
      localTag5.formatAsBlock = false;
    }
    for (String str3 : preserveWhitespaceTags)
    {
      Tag localTag4 = (Tag)tags.get(str3);
      Validate.notNull(localTag4);
      localTag4.preserveWhitespace = true;
    }
    for (String str2 : formListedTags)
    {
      Tag localTag3 = (Tag)tags.get(str2);
      Validate.notNull(localTag3);
      localTag3.formList = true;
    }
    String[] arrayOfString8 = formSubmitTags;
    int i9 = arrayOfString8.length;
    while (i < i9)
    {
      String str1 = arrayOfString8[i];
      Tag localTag2 = (Tag)tags.get(str1);
      Validate.notNull(localTag2);
      localTag2.formSubmit = true;
      i++;
    }
  }

  private Tag(String paramString)
  {
    this.tagName = paramString.toLowerCase();
  }

  public static boolean isKnownTag(String paramString)
  {
    return tags.containsKey(paramString);
  }

  private static void register(Tag paramTag)
  {
    tags.put(paramTag.tagName, paramTag);
  }

  public static Tag valueOf(String paramString)
  {
    Validate.notNull(paramString);
    Tag localTag = (Tag)tags.get(paramString);
    if (localTag == null)
    {
      String str = paramString.trim().toLowerCase();
      Validate.notEmpty(str);
      localTag = (Tag)tags.get(str);
      if (localTag == null)
      {
        localTag = new Tag(str);
        localTag.isBlock = false;
        localTag.canContainBlock = true;
      }
    }
    return localTag;
  }

  public boolean canContainBlock()
  {
    return this.canContainBlock;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if (!(paramObject instanceof Tag))
      return false;
    Tag localTag = (Tag)paramObject;
    if (this.canContainBlock != localTag.canContainBlock)
      return false;
    if (this.canContainInline != localTag.canContainInline)
      return false;
    if (this.empty != localTag.empty)
      return false;
    if (this.formatAsBlock != localTag.formatAsBlock)
      return false;
    if (this.isBlock != localTag.isBlock)
      return false;
    if (this.preserveWhitespace != localTag.preserveWhitespace)
      return false;
    if (this.selfClosing != localTag.selfClosing)
      return false;
    if (this.formList != localTag.formList)
      return false;
    if (this.formSubmit != localTag.formSubmit)
      return false;
    return this.tagName.equals(localTag.tagName);
  }

  public boolean formatAsBlock()
  {
    return this.formatAsBlock;
  }

  public String getName()
  {
    return this.tagName;
  }

  public int hashCode()
  {
    return 31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * this.tagName.hashCode() + this.isBlock) + this.formatAsBlock) + this.canContainBlock) + this.canContainInline) + this.empty) + this.selfClosing) + this.preserveWhitespace) + this.formList) + this.formSubmit;
  }

  public boolean isBlock()
  {
    return this.isBlock;
  }

  public boolean isData()
  {
    boolean bool;
    if ((!this.canContainInline) && (!isEmpty()))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public boolean isEmpty()
  {
    return this.empty;
  }

  public boolean isFormListed()
  {
    return this.formList;
  }

  public boolean isFormSubmittable()
  {
    return this.formSubmit;
  }

  public boolean isInline()
  {
    return true ^ this.isBlock;
  }

  public boolean isKnownTag()
  {
    return tags.containsKey(this.tagName);
  }

  public boolean isSelfClosing()
  {
    boolean bool;
    if ((!this.empty) && (!this.selfClosing))
      bool = false;
    else
      bool = true;
    return bool;
  }

  public boolean preserveWhitespace()
  {
    return this.preserveWhitespace;
  }

  Tag setSelfClosing()
  {
    this.selfClosing = true;
    return this;
  }

  public String toString()
  {
    return this.tagName;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.parser.Tag
 * JD-Core Version:    0.6.1
 */