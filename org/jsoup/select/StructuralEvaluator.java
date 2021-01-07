package org.jsoup.select;

import java.util.Iterator;
import org.jsoup.nodes.Element;

abstract class StructuralEvaluator extends Evaluator
{
  Evaluator evaluator;

  static class Has extends StructuralEvaluator
  {
    public Has(Evaluator paramEvaluator)
    {
      this.evaluator = paramEvaluator;
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      Iterator localIterator = paramElement2.getAllElements().iterator();
      while (localIterator.hasNext())
      {
        Element localElement = (Element)localIterator.next();
        if ((localElement != paramElement2) && (this.evaluator.matches(paramElement1, localElement)))
          return true;
      }
      return false;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.evaluator;
      return String.format(":has(%s)", arrayOfObject);
    }
  }

  static class ImmediateParent extends StructuralEvaluator
  {
    public ImmediateParent(Evaluator paramEvaluator)
    {
      this.evaluator = paramEvaluator;
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      if (paramElement1 == paramElement2)
        return false;
      Element localElement = paramElement2.parent();
      boolean bool1 = false;
      if (localElement != null)
      {
        boolean bool2 = this.evaluator.matches(paramElement1, localElement);
        bool1 = false;
        if (bool2)
          bool1 = true;
      }
      return bool1;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.evaluator;
      return String.format(":ImmediateParent%s", arrayOfObject);
    }
  }

  static class ImmediatePreviousSibling extends StructuralEvaluator
  {
    public ImmediatePreviousSibling(Evaluator paramEvaluator)
    {
      this.evaluator = paramEvaluator;
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      if (paramElement1 == paramElement2)
        return false;
      Element localElement = paramElement2.previousElementSibling();
      boolean bool1 = false;
      if (localElement != null)
      {
        boolean bool2 = this.evaluator.matches(paramElement1, localElement);
        bool1 = false;
        if (bool2)
          bool1 = true;
      }
      return bool1;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.evaluator;
      return String.format(":prev%s", arrayOfObject);
    }
  }

  static class Not extends StructuralEvaluator
  {
    public Not(Evaluator paramEvaluator)
    {
      this.evaluator = paramEvaluator;
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return true ^ this.evaluator.matches(paramElement1, paramElement2);
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.evaluator;
      return String.format(":not%s", arrayOfObject);
    }
  }

  static class Parent extends StructuralEvaluator
  {
    public Parent(Evaluator paramEvaluator)
    {
      this.evaluator = paramEvaluator;
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      if (paramElement1 == paramElement2)
        return false;
      for (Element localElement = paramElement2.parent(); localElement != paramElement1; localElement = localElement.parent())
        if (this.evaluator.matches(paramElement1, localElement))
          return true;
      return false;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.evaluator;
      return String.format(":parent%s", arrayOfObject);
    }
  }

  static class PreviousSibling extends StructuralEvaluator
  {
    public PreviousSibling(Evaluator paramEvaluator)
    {
      this.evaluator = paramEvaluator;
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      if (paramElement1 == paramElement2)
        return false;
      for (Element localElement = paramElement2.previousElementSibling(); localElement != null; localElement = localElement.previousElementSibling())
        if (this.evaluator.matches(paramElement1, localElement))
          return true;
      return false;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.evaluator;
      return String.format(":prev*%s", arrayOfObject);
    }
  }

  static class Root extends Evaluator
  {
    public boolean matches(Element paramElement1, Element paramElement2)
    {
      boolean bool;
      if (paramElement1 == paramElement2)
        bool = true;
      else
        bool = false;
      return bool;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.select.StructuralEvaluator
 * JD-Core Version:    0.6.1
 */