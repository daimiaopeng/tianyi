package org.jsoup.select;

import org.jsoup.nodes.Node;

public class NodeTraversor
{
  private NodeVisitor visitor;

  public NodeTraversor(NodeVisitor paramNodeVisitor)
  {
    this.visitor = paramNodeVisitor;
  }

  public void traverse(Node paramNode)
  {
    Node localNode = paramNode;
    int i = 0;
    while (localNode != null)
    {
      this.visitor.head(localNode, i);
      if (localNode.childNodeSize() > 0)
      {
        localNode = localNode.childNode(0);
        i++;
      }
      else
      {
        while ((localNode.nextSibling() == null) && (i > 0))
        {
          this.visitor.tail(localNode, i);
          localNode = localNode.parentNode();
          i--;
        }
        this.visitor.tail(localNode, i);
        if (localNode == paramNode)
          break;
        localNode = localNode.nextSibling();
      }
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.select.NodeTraversor
 * JD-Core Version:    0.6.1
 */