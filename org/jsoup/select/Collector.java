package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class Collector
{
  public static Elements collect(Evaluator paramEvaluator, Element paramElement)
  {
    Elements localElements = new Elements();
    new NodeTraversor(new Accumulator(paramElement, localElements, paramEvaluator)).traverse(paramElement);
    return localElements;
  }

  private static class Accumulator
    implements NodeVisitor
  {
    private final Elements elements;
    private final Evaluator eval;
    private final Element root;

    Accumulator(Element paramElement, Elements paramElements, Evaluator paramEvaluator)
    {
      this.root = paramElement;
      this.elements = paramElements;
      this.eval = paramEvaluator;
    }

    public void head(Node paramNode, int paramInt)
    {
      if ((paramNode instanceof Element))
      {
        Element localElement = (Element)paramNode;
        if (this.eval.matches(this.root, localElement))
          this.elements.add(localElement);
      }
    }

    public void tail(Node paramNode, int paramInt)
    {
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.select.Collector
 * JD-Core Version:    0.6.1
 */