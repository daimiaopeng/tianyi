package org.jsoup.nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

public abstract class Node
  implements Cloneable
{
  Attributes attributes;
  String baseUri;
  List<Node> childNodes;
  Node parentNode;
  int siblingIndex;

  protected Node()
  {
    this.childNodes = Collections.emptyList();
    this.attributes = null;
  }

  protected Node(String paramString)
  {
    this(paramString, new Attributes());
  }

  protected Node(String paramString, Attributes paramAttributes)
  {
    Validate.notNull(paramString);
    Validate.notNull(paramAttributes);
    this.childNodes = new ArrayList(4);
    this.baseUri = paramString.trim();
    this.attributes = paramAttributes;
  }

  private void addSiblingHtml(int paramInt, String paramString)
  {
    Validate.notNull(paramString);
    Validate.notNull(this.parentNode);
    Element localElement;
    if ((parent() instanceof Element))
      localElement = (Element)parent();
    else
      localElement = null;
    List localList = Parser.parseFragment(paramString, localElement, baseUri());
    this.parentNode.addChildren(paramInt, (Node[])localList.toArray(new Node[localList.size()]));
  }

  private Element getDeepChild(Element paramElement)
  {
    Elements localElements = paramElement.children();
    if (localElements.size() > 0)
      return getDeepChild((Element)localElements.get(0));
    return paramElement;
  }

  private void reindexChildren()
  {
    for (int i = 0; i < this.childNodes.size(); i++)
      ((Node)this.childNodes.get(i)).setSiblingIndex(i);
  }

  private void reparentChild(Node paramNode)
  {
    if (paramNode.parentNode != null)
      paramNode.parentNode.removeChild(paramNode);
    paramNode.setParentNode(this);
  }

  // ERROR //
  public String absUrl(String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 122	org/jsoup/helper/Validate:notEmpty	(Ljava/lang/String;)V
    //   4: aload_0
    //   5: aload_1
    //   6: invokevirtual 125	org/jsoup/nodes/Node:attr	(Ljava/lang/String;)Ljava/lang/String;
    //   9: astore_2
    //   10: aload_0
    //   11: aload_1
    //   12: invokevirtual 129	org/jsoup/nodes/Node:hasAttr	(Ljava/lang/String;)Z
    //   15: ifne +6 -> 21
    //   18: ldc 131
    //   20: areturn
    //   21: new 133	java/net/URL
    //   24: dup
    //   25: aload_0
    //   26: getfield 57	org/jsoup/nodes/Node:baseUri	Ljava/lang/String;
    //   29: invokespecial 135	java/net/URL:<init>	(Ljava/lang/String;)V
    //   32: astore_3
    //   33: aload_2
    //   34: ldc 137
    //   36: invokevirtual 140	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   39: ifeq +35 -> 74
    //   42: new 142	java/lang/StringBuilder
    //   45: dup
    //   46: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   49: astore 4
    //   51: aload 4
    //   53: aload_3
    //   54: invokevirtual 146	java/net/URL:getPath	()Ljava/lang/String;
    //   57: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: pop
    //   61: aload 4
    //   63: aload_2
    //   64: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: pop
    //   68: aload 4
    //   70: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   73: astore_2
    //   74: new 133	java/net/URL
    //   77: dup
    //   78: aload_3
    //   79: aload_2
    //   80: invokespecial 156	java/net/URL:<init>	(Ljava/net/URL;Ljava/lang/String;)V
    //   83: invokevirtual 159	java/net/URL:toExternalForm	()Ljava/lang/String;
    //   86: areturn
    //   87: new 133	java/net/URL
    //   90: dup
    //   91: aload_2
    //   92: invokespecial 135	java/net/URL:<init>	(Ljava/lang/String;)V
    //   95: invokevirtual 159	java/net/URL:toExternalForm	()Ljava/lang/String;
    //   98: astore 7
    //   100: aload 7
    //   102: areturn
    //   103: ldc 131
    //   105: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   21	33	87	java/net/MalformedURLException
    //   33	100	103	java/net/MalformedURLException
  }

  protected void addChildren(int paramInt, Node[] paramArrayOfNode)
  {
    Validate.noNullElements(paramArrayOfNode);
    for (int i = -1 + paramArrayOfNode.length; i >= 0; i--)
    {
      Node localNode = paramArrayOfNode[i];
      reparentChild(localNode);
      this.childNodes.add(paramInt, localNode);
    }
    reindexChildren();
  }

  protected void addChildren(Node[] paramArrayOfNode)
  {
    int i = paramArrayOfNode.length;
    for (int j = 0; j < i; j++)
    {
      Node localNode = paramArrayOfNode[j];
      reparentChild(localNode);
      this.childNodes.add(localNode);
      localNode.setSiblingIndex(-1 + this.childNodes.size());
    }
  }

  public Node after(String paramString)
  {
    addSiblingHtml(1 + siblingIndex(), paramString);
    return this;
  }

  public Node after(Node paramNode)
  {
    Validate.notNull(paramNode);
    Validate.notNull(this.parentNode);
    this.parentNode.addChildren(1 + siblingIndex(), new Node[] { paramNode });
    return this;
  }

  public String attr(String paramString)
  {
    Validate.notNull(paramString);
    if (this.attributes.hasKey(paramString))
      return this.attributes.get(paramString);
    if (paramString.toLowerCase().startsWith("abs:"))
      return absUrl(paramString.substring("abs:".length()));
    return "";
  }

  public Node attr(String paramString1, String paramString2)
  {
    this.attributes.put(paramString1, paramString2);
    return this;
  }

  public Attributes attributes()
  {
    return this.attributes;
  }

  public String baseUri()
  {
    return this.baseUri;
  }

  public Node before(String paramString)
  {
    addSiblingHtml(siblingIndex(), paramString);
    return this;
  }

  public Node before(Node paramNode)
  {
    Validate.notNull(paramNode);
    Validate.notNull(this.parentNode);
    this.parentNode.addChildren(siblingIndex(), new Node[] { paramNode });
    return this;
  }

  public Node childNode(int paramInt)
  {
    return (Node)this.childNodes.get(paramInt);
  }

  public final int childNodeSize()
  {
    return this.childNodes.size();
  }

  public List<Node> childNodes()
  {
    return Collections.unmodifiableList(this.childNodes);
  }

  protected Node[] childNodesAsArray()
  {
    return (Node[])this.childNodes.toArray(new Node[childNodeSize()]);
  }

  public List<Node> childNodesCopy()
  {
    ArrayList localArrayList = new ArrayList(this.childNodes.size());
    Iterator localIterator = this.childNodes.iterator();
    while (localIterator.hasNext())
      localArrayList.add(((Node)localIterator.next()).clone());
    return localArrayList;
  }

  public Node clone()
  {
    Node localNode1 = doClone(null);
    LinkedList localLinkedList = new LinkedList();
    localLinkedList.add(localNode1);
    while (!localLinkedList.isEmpty())
    {
      Node localNode2 = (Node)localLinkedList.remove();
      for (int i = 0; i < localNode2.childNodes.size(); i++)
      {
        Node localNode3 = ((Node)localNode2.childNodes.get(i)).doClone(localNode2);
        localNode2.childNodes.set(i, localNode3);
        localLinkedList.add(localNode3);
      }
    }
    return localNode1;
  }

  protected Node doClone(Node paramNode)
  {
    try
    {
      Node localNode1 = (Node)super.clone();
      localNode1.parentNode = paramNode;
      int i;
      if (paramNode == null)
        i = 0;
      else
        i = this.siblingIndex;
      localNode1.siblingIndex = i;
      Attributes localAttributes;
      if (this.attributes != null)
        localAttributes = this.attributes.clone();
      else
        localAttributes = null;
      localNode1.attributes = localAttributes;
      localNode1.baseUri = this.baseUri;
      localNode1.childNodes = new ArrayList(this.childNodes.size());
      Iterator localIterator = this.childNodes.iterator();
      while (localIterator.hasNext())
      {
        Node localNode2 = (Node)localIterator.next();
        localNode1.childNodes.add(localNode2);
      }
      return localNode1;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new RuntimeException(localCloneNotSupportedException);
    }
  }

  public boolean equals(Object paramObject)
  {
    return this == paramObject;
  }

  Document.OutputSettings getOutputSettings()
  {
    if (ownerDocument() != null);
    Document.OutputSettings localOutputSettings;
    for (Document localDocument = ownerDocument(); ; localDocument = new Document(""))
    {
      localOutputSettings = localDocument.outputSettings();
      break;
    }
    return localOutputSettings;
  }

  public boolean hasAttr(String paramString)
  {
    Validate.notNull(paramString);
    if (paramString.startsWith("abs:"))
    {
      String str = paramString.substring("abs:".length());
      if ((this.attributes.hasKey(str)) && (!absUrl(str).equals("")))
        return true;
    }
    return this.attributes.hasKey(paramString);
  }

  public int hashCode()
  {
    int i;
    if (this.parentNode != null)
      i = this.parentNode.hashCode();
    else
      i = 0;
    int j = i * 31;
    Attributes localAttributes = this.attributes;
    int k = 0;
    if (localAttributes != null)
      k = this.attributes.hashCode();
    return j + k;
  }

  protected void indent(StringBuilder paramStringBuilder, int paramInt, Document.OutputSettings paramOutputSettings)
  {
    paramStringBuilder.append("\n");
    paramStringBuilder.append(StringUtil.padding(paramInt * paramOutputSettings.indentAmount()));
  }

  public Node nextSibling()
  {
    if (this.parentNode == null)
      return null;
    List localList = this.parentNode.childNodes;
    Integer localInteger = Integer.valueOf(siblingIndex());
    Validate.notNull(localInteger);
    if (localList.size() > 1 + localInteger.intValue())
      return (Node)localList.get(1 + localInteger.intValue());
    return null;
  }

  public abstract String nodeName();

  public String outerHtml()
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    outerHtml(localStringBuilder);
    return localStringBuilder.toString();
  }

  protected void outerHtml(StringBuilder paramStringBuilder)
  {
    new NodeTraversor(new OuterHtmlVisitor(paramStringBuilder, getOutputSettings())).traverse(this);
  }

  abstract void outerHtmlHead(StringBuilder paramStringBuilder, int paramInt, Document.OutputSettings paramOutputSettings);

  abstract void outerHtmlTail(StringBuilder paramStringBuilder, int paramInt, Document.OutputSettings paramOutputSettings);

  public Document ownerDocument()
  {
    if ((this instanceof Document))
      return (Document)this;
    if (this.parentNode == null)
      return null;
    return this.parentNode.ownerDocument();
  }

  public Node parent()
  {
    return this.parentNode;
  }

  public final Node parentNode()
  {
    return this.parentNode;
  }

  public Node previousSibling()
  {
    if (this.parentNode == null)
      return null;
    List localList = this.parentNode.childNodes;
    Integer localInteger = Integer.valueOf(siblingIndex());
    Validate.notNull(localInteger);
    if (localInteger.intValue() > 0)
      return (Node)localList.get(-1 + localInteger.intValue());
    return null;
  }

  public void remove()
  {
    Validate.notNull(this.parentNode);
    this.parentNode.removeChild(this);
  }

  public Node removeAttr(String paramString)
  {
    Validate.notNull(paramString);
    this.attributes.remove(paramString);
    return this;
  }

  protected void removeChild(Node paramNode)
  {
    boolean bool;
    if (paramNode.parentNode == this)
      bool = true;
    else
      bool = false;
    Validate.isTrue(bool);
    int i = paramNode.siblingIndex();
    this.childNodes.remove(i);
    reindexChildren();
    paramNode.parentNode = null;
  }

  protected void replaceChild(Node paramNode1, Node paramNode2)
  {
    boolean bool;
    if (paramNode1.parentNode == this)
      bool = true;
    else
      bool = false;
    Validate.isTrue(bool);
    Validate.notNull(paramNode2);
    if (paramNode2.parentNode != null)
      paramNode2.parentNode.removeChild(paramNode2);
    Integer localInteger = Integer.valueOf(paramNode1.siblingIndex());
    this.childNodes.set(localInteger.intValue(), paramNode2);
    paramNode2.parentNode = this;
    paramNode2.setSiblingIndex(localInteger.intValue());
    paramNode1.parentNode = null;
  }

  public void replaceWith(Node paramNode)
  {
    Validate.notNull(paramNode);
    Validate.notNull(this.parentNode);
    this.parentNode.replaceChild(this, paramNode);
  }

  public void setBaseUri(final String paramString)
  {
    Validate.notNull(paramString);
    traverse(new NodeVisitor()
    {
      public void head(Node paramAnonymousNode, int paramAnonymousInt)
      {
        paramAnonymousNode.baseUri = paramString;
      }

      public void tail(Node paramAnonymousNode, int paramAnonymousInt)
      {
      }
    });
  }

  protected void setParentNode(Node paramNode)
  {
    if (this.parentNode != null)
      this.parentNode.removeChild(this);
    this.parentNode = paramNode;
  }

  protected void setSiblingIndex(int paramInt)
  {
    this.siblingIndex = paramInt;
  }

  public int siblingIndex()
  {
    return this.siblingIndex;
  }

  public List<Node> siblingNodes()
  {
    if (this.parentNode == null)
      return Collections.emptyList();
    List localList = this.parentNode.childNodes;
    ArrayList localArrayList = new ArrayList(-1 + localList.size());
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      Node localNode = (Node)localIterator.next();
      if (localNode != this)
        localArrayList.add(localNode);
    }
    return localArrayList;
  }

  public String toString()
  {
    return outerHtml();
  }

  public Node traverse(NodeVisitor paramNodeVisitor)
  {
    Validate.notNull(paramNodeVisitor);
    new NodeTraversor(paramNodeVisitor).traverse(this);
    return this;
  }

  public Node unwrap()
  {
    Validate.notNull(this.parentNode);
    int i = this.siblingIndex;
    Node localNode;
    if (this.childNodes.size() > 0)
      localNode = (Node)this.childNodes.get(0);
    else
      localNode = null;
    this.parentNode.addChildren(i, childNodesAsArray());
    remove();
    return localNode;
  }

  public Node wrap(String paramString)
  {
    Validate.notEmpty(paramString);
    Element localElement1;
    if ((parent() instanceof Element))
      localElement1 = (Element)parent();
    else
      localElement1 = null;
    List localList = Parser.parseFragment(paramString, localElement1, baseUri());
    Node localNode1 = (Node)localList.get(0);
    if ((localNode1 != null) && ((localNode1 instanceof Element)))
    {
      Element localElement2 = (Element)localNode1;
      Element localElement3 = getDeepChild(localElement2);
      this.parentNode.replaceChild(this, localElement2);
      localElement3.addChildren(new Node[] { this });
      int i = localList.size();
      int j = 0;
      if (i > 0)
        while (j < localList.size())
        {
          Node localNode2 = (Node)localList.get(j);
          localNode2.parentNode.removeChild(localNode2);
          localElement2.appendChild(localNode2);
          j++;
        }
      return this;
    }
    return null;
  }

  private static class OuterHtmlVisitor
    implements NodeVisitor
  {
    private StringBuilder accum;
    private Document.OutputSettings out;

    OuterHtmlVisitor(StringBuilder paramStringBuilder, Document.OutputSettings paramOutputSettings)
    {
      this.accum = paramStringBuilder;
      this.out = paramOutputSettings;
    }

    public void head(Node paramNode, int paramInt)
    {
      paramNode.outerHtmlHead(this.accum, paramInt, this.out);
    }

    public void tail(Node paramNode, int paramInt)
    {
      if (!paramNode.nodeName().equals("#text"))
        paramNode.outerHtmlTail(this.accum, paramInt, this.out);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.nodes.Node
 * JD-Core Version:    0.6.1
 */