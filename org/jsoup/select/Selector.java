package org.jsoup.select;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Element;

public class Selector
{
  private final Evaluator evaluator;
  private final Element root;

  private Selector(String paramString, Element paramElement)
  {
    Validate.notNull(paramString);
    String str = paramString.trim();
    Validate.notEmpty(str);
    Validate.notNull(paramElement);
    this.evaluator = QueryParser.parse(str);
    this.root = paramElement;
  }

  static Elements filterOut(Collection<Element> paramCollection1, Collection<Element> paramCollection2)
  {
    Elements localElements = new Elements();
    Iterator localIterator1 = paramCollection1.iterator();
    while (localIterator1.hasNext())
    {
      Element localElement = (Element)localIterator1.next();
      Iterator localIterator2 = paramCollection2.iterator();
      do
      {
        boolean bool = localIterator2.hasNext();
        i = 0;
        if (!bool)
          break;
      }
      while (!localElement.equals((Element)localIterator2.next()));
      int i = 1;
      if (i == 0)
        localElements.add(localElement);
    }
    return localElements;
  }

  private Elements select()
  {
    return Collector.collect(this.evaluator, this.root);
  }

  public static Elements select(String paramString, Iterable<Element> paramIterable)
  {
    Validate.notEmpty(paramString);
    Validate.notNull(paramIterable);
    LinkedHashSet localLinkedHashSet = new LinkedHashSet();
    Iterator localIterator = paramIterable.iterator();
    while (localIterator.hasNext())
      localLinkedHashSet.addAll(select(paramString, (Element)localIterator.next()));
    return new Elements(localLinkedHashSet);
  }

  public static Elements select(String paramString, Element paramElement)
  {
    return new Selector(paramString, paramElement).select();
  }

  public static class SelectorParseException extends IllegalStateException
  {
    public SelectorParseException(String paramString, Object[] paramArrayOfObject)
    {
      super();
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.select.Selector
 * JD-Core Version:    0.6.1
 */