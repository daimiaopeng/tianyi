package org.jsoup.safety;

import java.util.Iterator;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Tag;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

public class Cleaner
{
  private Whitelist whitelist;

  public Cleaner(Whitelist paramWhitelist)
  {
    Validate.notNull(paramWhitelist);
    this.whitelist = paramWhitelist;
  }

  private int copySafeNodes(Element paramElement1, Element paramElement2)
  {
    CleaningVisitor localCleaningVisitor = new CleaningVisitor(paramElement1, paramElement2, null);
    new NodeTraversor(localCleaningVisitor).traverse(paramElement1);
    return localCleaningVisitor.numDiscarded;
  }

  private ElementMeta createSafeElement(Element paramElement)
  {
    String str = paramElement.tagName();
    Attributes localAttributes = new Attributes();
    Element localElement = new Element(Tag.valueOf(str), paramElement.baseUri(), localAttributes);
    Iterator localIterator = paramElement.attributes().iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      Attribute localAttribute = (Attribute)localIterator.next();
      if (this.whitelist.isSafeAttribute(str, paramElement, localAttribute))
        localAttributes.put(localAttribute);
      else
        i++;
    }
    localAttributes.addAll(this.whitelist.getEnforcedAttributes(str));
    return new ElementMeta(localElement, i);
  }

  public Document clean(Document paramDocument)
  {
    Validate.notNull(paramDocument);
    Document localDocument = Document.createShell(paramDocument.baseUri());
    if (paramDocument.body() != null)
      copySafeNodes(paramDocument.body(), localDocument.body());
    return localDocument;
  }

  public boolean isValid(Document paramDocument)
  {
    Validate.notNull(paramDocument);
    Document localDocument = Document.createShell(paramDocument.baseUri());
    boolean bool;
    if (copySafeNodes(paramDocument.body(), localDocument.body()) == 0)
      bool = true;
    else
      bool = false;
    return bool;
  }

  private final class CleaningVisitor
    implements NodeVisitor
  {
    private Element destination;
    private int numDiscarded = 0;
    private final Element root;

    private CleaningVisitor(Element paramElement1, Element arg3)
    {
      this.root = paramElement1;
      Object localObject;
      this.destination = localObject;
    }

    public void head(Node paramNode, int paramInt)
    {
      if ((paramNode instanceof Element))
      {
        Element localElement1 = (Element)paramNode;
        if (Cleaner.this.whitelist.isSafeTag(localElement1.tagName()))
        {
          Cleaner.ElementMeta localElementMeta = Cleaner.this.createSafeElement(localElement1);
          Element localElement2 = localElementMeta.el;
          this.destination.appendChild(localElement2);
          this.numDiscarded += localElementMeta.numAttribsDiscarded;
          this.destination = localElement2;
        }
        else if (paramNode != this.root)
        {
          this.numDiscarded = (1 + this.numDiscarded);
        }
      }
      else if ((paramNode instanceof TextNode))
      {
        TextNode localTextNode = new TextNode(((TextNode)paramNode).getWholeText(), paramNode.baseUri());
        this.destination.appendChild(localTextNode);
      }
      else if (((paramNode instanceof DataNode)) && (Cleaner.this.whitelist.isSafeTag(paramNode.parent().nodeName())))
      {
        DataNode localDataNode = new DataNode(((DataNode)paramNode).getWholeData(), paramNode.baseUri());
        this.destination.appendChild(localDataNode);
      }
      else
      {
        this.numDiscarded = (1 + this.numDiscarded);
      }
    }

    public void tail(Node paramNode, int paramInt)
    {
      if (((paramNode instanceof Element)) && (Cleaner.this.whitelist.isSafeTag(paramNode.nodeName())))
        this.destination = this.destination.parent();
    }
  }

  private static class ElementMeta
  {
    Element el;
    int numAttribsDiscarded;

    ElementMeta(Element paramElement, int paramInt)
    {
      this.el = paramElement;
      this.numAttribsDiscarded = paramInt;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.safety.Cleaner
 * JD-Core Version:    0.6.1
 */