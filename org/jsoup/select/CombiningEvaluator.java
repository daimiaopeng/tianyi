package org.jsoup.select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Element;

abstract class CombiningEvaluator extends Evaluator
{
  final ArrayList<Evaluator> evaluators = new ArrayList();
  int num = 0;

  CombiningEvaluator()
  {
  }

  CombiningEvaluator(Collection<Evaluator> paramCollection)
  {
    this();
    this.evaluators.addAll(paramCollection);
    updateNumEvaluators();
  }

  void replaceRightMostEvaluator(Evaluator paramEvaluator)
  {
    this.evaluators.set(-1 + this.num, paramEvaluator);
  }

  Evaluator rightMostEvaluator()
  {
    Evaluator localEvaluator;
    if (this.num > 0)
      localEvaluator = (Evaluator)this.evaluators.get(-1 + this.num);
    else
      localEvaluator = null;
    return localEvaluator;
  }

  void updateNumEvaluators()
  {
    this.num = this.evaluators.size();
  }

  static final class And extends CombiningEvaluator
  {
    And(Collection<Evaluator> paramCollection)
    {
      super();
    }

    And(Evaluator[] paramArrayOfEvaluator)
    {
      this(Arrays.asList(paramArrayOfEvaluator));
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      for (int i = 0; i < this.num; i++)
        if (!((Evaluator)this.evaluators.get(i)).matches(paramElement1, paramElement2))
          return false;
      return true;
    }

    public String toString()
    {
      return StringUtil.join(this.evaluators, " ");
    }
  }

  static final class Or extends CombiningEvaluator
  {
    Or()
    {
    }

    Or(Collection<Evaluator> paramCollection)
    {
      if (this.num > 1)
        this.evaluators.add(new CombiningEvaluator.And(paramCollection));
      else
        this.evaluators.addAll(paramCollection);
      updateNumEvaluators();
    }

    public void add(Evaluator paramEvaluator)
    {
      this.evaluators.add(paramEvaluator);
      updateNumEvaluators();
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      for (int i = 0; i < this.num; i++)
        if (((Evaluator)this.evaluators.get(i)).matches(paramElement1, paramElement2))
          return true;
      return false;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.evaluators;
      return String.format(":or%s", arrayOfObject);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.select.CombiningEvaluator
 * JD-Core Version:    0.6.1
 */