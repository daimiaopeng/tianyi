package org.jsoup.nodes;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

public class Document extends Element
{
  private String location;
  private OutputSettings outputSettings = new OutputSettings();
  private QuirksMode quirksMode = QuirksMode.noQuirks;

  public Document(String paramString)
  {
    super(Tag.valueOf("#root"), paramString);
    this.location = paramString;
  }

  public static Document createShell(String paramString)
  {
    Validate.notNull(paramString);
    Document localDocument = new Document(paramString);
    Element localElement = localDocument.appendElement("html");
    localElement.appendElement("head");
    localElement.appendElement("body");
    return localDocument;
  }

  private Element findFirstElementByTagName(String paramString, Node paramNode)
  {
    if (paramNode.nodeName().equals(paramString))
      return (Element)paramNode;
    Iterator localIterator = paramNode.childNodes.iterator();
    while (localIterator.hasNext())
    {
      Element localElement = findFirstElementByTagName(paramString, (Node)localIterator.next());
      if (localElement != null)
        return localElement;
    }
    return null;
  }

  private void normaliseStructure(String paramString, Element paramElement)
  {
    Elements localElements = getElementsByTag(paramString);
    Element localElement1 = localElements.first();
    int i = localElements.size();
    int j = 1;
    if (i > j)
    {
      ArrayList localArrayList = new ArrayList();
      while (j < localElements.size())
      {
        Element localElement2 = localElements.get(j);
        Iterator localIterator2 = localElement2.childNodes.iterator();
        while (localIterator2.hasNext())
          localArrayList.add((Node)localIterator2.next());
        localElement2.remove();
        j++;
      }
      Iterator localIterator1 = localArrayList.iterator();
      while (localIterator1.hasNext())
        localElement1.appendChild((Node)localIterator1.next());
    }
    if (!localElement1.parent().equals(paramElement))
      paramElement.appendChild(localElement1);
  }

  private void normaliseTextNodes(Element paramElement)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramElement.childNodes.iterator();
    while (localIterator.hasNext())
    {
      Node localNode2 = (Node)localIterator.next();
      if ((localNode2 instanceof TextNode))
      {
        TextNode localTextNode = (TextNode)localNode2;
        if (!localTextNode.isBlank())
          localArrayList.add(localTextNode);
      }
    }
    for (int i = -1 + localArrayList.size(); i >= 0; i--)
    {
      Node localNode1 = (Node)localArrayList.get(i);
      paramElement.removeChild(localNode1);
      body().prependChild(new TextNode(" ", ""));
      body().prependChild(localNode1);
    }
  }

  public Element body()
  {
    return findFirstElementByTagName("body", this);
  }

  public Document clone()
  {
    Document localDocument = (Document)super.clone();
    localDocument.outputSettings = this.outputSettings.clone();
    return localDocument;
  }

  public Element createElement(String paramString)
  {
    return new Element(Tag.valueOf(paramString), baseUri());
  }

  public boolean equals(Object paramObject)
  {
    return super.equals(paramObject);
  }

  public Element head()
  {
    return findFirstElementByTagName("head", this);
  }

  public String location()
  {
    return this.location;
  }

  public String nodeName()
  {
    return "#document";
  }

  public Document normalise()
  {
    Element localElement = findFirstElementByTagName("html", this);
    if (localElement == null)
      localElement = appendElement("html");
    if (head() == null)
      localElement.prependElement("head");
    if (body() == null)
      localElement.appendElement("body");
    normaliseTextNodes(head());
    normaliseTextNodes(localElement);
    normaliseTextNodes(this);
    normaliseStructure("head", localElement);
    normaliseStructure("body", localElement);
    return this;
  }

  public String outerHtml()
  {
    return super.html();
  }

  public OutputSettings outputSettings()
  {
    return this.outputSettings;
  }

  public Document outputSettings(OutputSettings paramOutputSettings)
  {
    Validate.notNull(paramOutputSettings);
    this.outputSettings = paramOutputSettings;
    return this;
  }

  public QuirksMode quirksMode()
  {
    return this.quirksMode;
  }

  public Document quirksMode(QuirksMode paramQuirksMode)
  {
    this.quirksMode = paramQuirksMode;
    return this;
  }

  public Element text(String paramString)
  {
    body().text(paramString);
    return this;
  }

  public String title()
  {
    Element localElement = getElementsByTag("title").first();
    String str;
    if (localElement != null)
      str = StringUtil.normaliseWhitespace(localElement.text()).trim();
    else
      str = "";
    return str;
  }

  public void title(String paramString)
  {
    Validate.notNull(paramString);
    Element localElement = getElementsByTag("title").first();
    if (localElement == null)
      head().appendElement("title").text(paramString);
    else
      localElement.text(paramString);
  }

  public static class OutputSettings
    implements Cloneable
  {
    private Charset charset = Charset.forName("UTF-8");
    private CharsetEncoder charsetEncoder = this.charset.newEncoder();
    private Entities.EscapeMode escapeMode = Entities.EscapeMode.base;
    private int indentAmount = 1;
    private boolean outline = false;
    private boolean prettyPrint = true;
    private Syntax syntax = Syntax.html;

    public Charset charset()
    {
      return this.charset;
    }

    public OutputSettings charset(String paramString)
    {
      charset(Charset.forName(paramString));
      return this;
    }

    public OutputSettings charset(Charset paramCharset)
    {
      this.charset = paramCharset;
      this.charsetEncoder = paramCharset.newEncoder();
      return this;
    }

    public OutputSettings clone()
    {
      try
      {
        OutputSettings localOutputSettings = (OutputSettings)super.clone();
        localOutputSettings.charset(this.charset.name());
        localOutputSettings.escapeMode = Entities.EscapeMode.valueOf(this.escapeMode.name());
        return localOutputSettings;
      }
      catch (CloneNotSupportedException localCloneNotSupportedException)
      {
        throw new RuntimeException(localCloneNotSupportedException);
      }
    }

    CharsetEncoder encoder()
    {
      return this.charsetEncoder;
    }

    public OutputSettings escapeMode(Entities.EscapeMode paramEscapeMode)
    {
      this.escapeMode = paramEscapeMode;
      return this;
    }

    public Entities.EscapeMode escapeMode()
    {
      return this.escapeMode;
    }

    public int indentAmount()
    {
      return this.indentAmount;
    }

    public OutputSettings indentAmount(int paramInt)
    {
      boolean bool;
      if (paramInt >= 0)
        bool = true;
      else
        bool = false;
      Validate.isTrue(bool);
      this.indentAmount = paramInt;
      return this;
    }

    public OutputSettings outline(boolean paramBoolean)
    {
      this.outline = paramBoolean;
      return this;
    }

    public boolean outline()
    {
      return this.outline;
    }

    public OutputSettings prettyPrint(boolean paramBoolean)
    {
      this.prettyPrint = paramBoolean;
      return this;
    }

    public boolean prettyPrint()
    {
      return this.prettyPrint;
    }

    public Syntax syntax()
    {
      return this.syntax;
    }

    public OutputSettings syntax(Syntax paramSyntax)
    {
      this.syntax = paramSyntax;
      return this;
    }

    public static enum Syntax
    {
      static
      {
        Syntax[] arrayOfSyntax = new Syntax[2];
        arrayOfSyntax[0] = html;
        arrayOfSyntax[1] = xml;
      }
    }
  }

  public static enum QuirksMode
  {
    static
    {
      limitedQuirks = new QuirksMode("limitedQuirks", 2);
      QuirksMode[] arrayOfQuirksMode = new QuirksMode[3];
      arrayOfQuirksMode[0] = noQuirks;
      arrayOfQuirksMode[1] = quirks;
      arrayOfQuirksMode[2] = limitedQuirks;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.nodes.Document
 * JD-Core Version:    0.6.1
 */