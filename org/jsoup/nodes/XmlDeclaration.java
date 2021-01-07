package org.jsoup.nodes;

public class XmlDeclaration extends Node
{
  private static final String DECL_KEY = "declaration";
  private final boolean isProcessingInstruction;

  public XmlDeclaration(String paramString1, String paramString2, boolean paramBoolean)
  {
    super(paramString2);
    this.attributes.put("declaration", paramString1);
    this.isProcessingInstruction = paramBoolean;
  }

  public String getWholeDeclaration()
  {
    return this.attributes.get("declaration");
  }

  public String nodeName()
  {
    return "#declaration";
  }

  void outerHtmlHead(StringBuilder paramStringBuilder, int paramInt, Document.OutputSettings paramOutputSettings)
  {
    paramStringBuilder.append("<");
    String str;
    if (this.isProcessingInstruction)
      str = "!";
    else
      str = "?";
    paramStringBuilder.append(str);
    paramStringBuilder.append(getWholeDeclaration());
    paramStringBuilder.append(">");
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
 * Qualified Name:     org.jsoup.nodes.XmlDeclaration
 * JD-Core Version:    0.6.1
 */