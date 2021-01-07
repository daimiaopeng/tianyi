package org.jsoup.nodes;

import org.jsoup.helper.StringUtil;

public class DocumentType extends Node
{
  public DocumentType(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    super(paramString4);
    attr("name", paramString1);
    attr("publicId", paramString2);
    attr("systemId", paramString3);
  }

  public String nodeName()
  {
    return "#doctype";
  }

  void outerHtmlHead(StringBuilder paramStringBuilder, int paramInt, Document.OutputSettings paramOutputSettings)
  {
    paramStringBuilder.append("<!DOCTYPE");
    if (!StringUtil.isBlank(attr("name")))
    {
      paramStringBuilder.append(" ");
      paramStringBuilder.append(attr("name"));
    }
    if (!StringUtil.isBlank(attr("publicId")))
    {
      paramStringBuilder.append(" PUBLIC \"");
      paramStringBuilder.append(attr("publicId"));
      paramStringBuilder.append('"');
    }
    if (!StringUtil.isBlank(attr("systemId")))
    {
      paramStringBuilder.append(" \"");
      paramStringBuilder.append(attr("systemId"));
      paramStringBuilder.append('"');
    }
    paramStringBuilder.append('>');
  }

  void outerHtmlTail(StringBuilder paramStringBuilder, int paramInt, Document.OutputSettings paramOutputSettings)
  {
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.nodes.DocumentType
 * JD-Core Version:    0.6.1
 */