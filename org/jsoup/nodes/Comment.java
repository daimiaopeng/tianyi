package org.jsoup.nodes;

public class Comment extends Node
{
  private static final String COMMENT_KEY = "comment";

  public Comment(String paramString1, String paramString2)
  {
    super(paramString2);
    this.attributes.put("comment", paramString1);
  }

  public String getData()
  {
    return this.attributes.get("comment");
  }

  public String nodeName()
  {
    return "#comment";
  }

  void outerHtmlHead(StringBuilder paramStringBuilder, int paramInt, Document.OutputSettings paramOutputSettings)
  {
    if (paramOutputSettings.prettyPrint())
      indent(paramStringBuilder, paramInt, paramOutputSettings);
    paramStringBuilder.append("<!--");
    paramStringBuilder.append(getData());
    paramStringBuilder.append("-->");
  }

  void outerHtmlTail(StringBuilder paramStringBuilder, int paramInt, Document.OutputSettings paramOutputSettings)
  {
  }

  public String toString()
  {
    return outerHtml();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.nodes.Comment
 * JD-Core Version:    0.6.1
 */