package org.jsoup.select;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.parser.TokenQueue;

class QueryParser
{
  private static final String[] AttributeEvals = { "=", "!=", "^=", "$=", "*=", "~=" };
  private static final Pattern NTH_AB = Pattern.compile("((\\+|-)?(\\d+)?)n(\\s*(\\+|-)?\\s*\\d+)?", 2);
  private static final Pattern NTH_B = Pattern.compile("(\\+|-)?(\\d+)");
  private static final String[] combinators = { ",", ">", "+", "~", " " };
  private List<Evaluator> evals = new ArrayList();
  private String query;
  private TokenQueue tq;

  private QueryParser(String paramString)
  {
    this.query = paramString;
    this.tq = new TokenQueue(paramString);
  }

  private void allElements()
  {
    this.evals.add(new Evaluator.AllElements());
  }

  private void byAttribute()
  {
    TokenQueue localTokenQueue = new TokenQueue(this.tq.chompBalanced('[', ']'));
    String str = localTokenQueue.consumeToAny(AttributeEvals);
    Validate.notEmpty(str);
    localTokenQueue.consumeWhitespace();
    if (localTokenQueue.isEmpty())
    {
      if (str.startsWith("^"))
        this.evals.add(new Evaluator.AttributeStarting(str.substring(1)));
      else
        this.evals.add(new Evaluator.Attribute(str));
    }
    else if (localTokenQueue.matchChomp("="))
    {
      this.evals.add(new Evaluator.AttributeWithValue(str, localTokenQueue.remainder()));
    }
    else if (localTokenQueue.matchChomp("!="))
    {
      this.evals.add(new Evaluator.AttributeWithValueNot(str, localTokenQueue.remainder()));
    }
    else if (localTokenQueue.matchChomp("^="))
    {
      this.evals.add(new Evaluator.AttributeWithValueStarting(str, localTokenQueue.remainder()));
    }
    else if (localTokenQueue.matchChomp("$="))
    {
      this.evals.add(new Evaluator.AttributeWithValueEnding(str, localTokenQueue.remainder()));
    }
    else if (localTokenQueue.matchChomp("*="))
    {
      this.evals.add(new Evaluator.AttributeWithValueContaining(str, localTokenQueue.remainder()));
    }
    else
    {
      if (!localTokenQueue.matchChomp("~="))
        break label303;
      this.evals.add(new Evaluator.AttributeWithValueMatching(str, Pattern.compile(localTokenQueue.remainder())));
    }
    return;
    label303: Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.query;
    arrayOfObject[1] = localTokenQueue.remainder();
    throw new Selector.SelectorParseException("Could not parse attribute query '%s': unexpected token at '%s'", arrayOfObject);
  }

  private void byClass()
  {
    String str = this.tq.consumeCssIdentifier();
    Validate.notEmpty(str);
    this.evals.add(new Evaluator.Class(str.trim().toLowerCase()));
  }

  private void byId()
  {
    String str = this.tq.consumeCssIdentifier();
    Validate.notEmpty(str);
    this.evals.add(new Evaluator.Id(str));
  }

  private void byTag()
  {
    String str = this.tq.consumeElementSelector();
    Validate.notEmpty(str);
    if (str.contains("|"))
      str = str.replace("|", ":");
    this.evals.add(new Evaluator.Tag(str.trim().toLowerCase()));
  }

  private void combinator(char paramChar)
  {
    this.tq.consumeWhitespace();
    Evaluator localEvaluator1 = parse(consumeSubQuery());
    Object localObject1;
    int i;
    Object localObject2;
    if (this.evals.size() == 1)
    {
      localObject1 = (Evaluator)this.evals.get(0);
      if (((localObject1 instanceof CombiningEvaluator.Or)) && (paramChar != ','))
      {
        Evaluator localEvaluator2 = ((CombiningEvaluator.Or)localObject1).rightMostEvaluator();
        i = 1;
        localObject2 = localObject1;
        localObject1 = localEvaluator2;
        break label108;
      }
    }
    while (true)
    {
      localObject2 = localObject1;
      i = 0;
      break;
      localObject1 = new CombiningEvaluator.And(this.evals);
    }
    label108: this.evals.clear();
    Object localObject3;
    if (paramChar == '>')
    {
      Evaluator[] arrayOfEvaluator4 = new Evaluator[2];
      arrayOfEvaluator4[0] = localEvaluator1;
      arrayOfEvaluator4[1] = new StructuralEvaluator.ImmediateParent((Evaluator)localObject1);
      localObject3 = new CombiningEvaluator.And(arrayOfEvaluator4);
    }
    else if (paramChar == ' ')
    {
      Evaluator[] arrayOfEvaluator3 = new Evaluator[2];
      arrayOfEvaluator3[0] = localEvaluator1;
      arrayOfEvaluator3[1] = new StructuralEvaluator.Parent((Evaluator)localObject1);
      localObject3 = new CombiningEvaluator.And(arrayOfEvaluator3);
    }
    else if (paramChar == '+')
    {
      Evaluator[] arrayOfEvaluator2 = new Evaluator[2];
      arrayOfEvaluator2[0] = localEvaluator1;
      arrayOfEvaluator2[1] = new StructuralEvaluator.ImmediatePreviousSibling((Evaluator)localObject1);
      localObject3 = new CombiningEvaluator.And(arrayOfEvaluator2);
    }
    else if (paramChar == '~')
    {
      Evaluator[] arrayOfEvaluator1 = new Evaluator[2];
      arrayOfEvaluator1[0] = localEvaluator1;
      arrayOfEvaluator1[1] = new StructuralEvaluator.PreviousSibling((Evaluator)localObject1);
      localObject3 = new CombiningEvaluator.And(arrayOfEvaluator1);
    }
    else
    {
      if (paramChar != ',')
        break label381;
      if ((localObject1 instanceof CombiningEvaluator.Or))
      {
        CombiningEvaluator.Or localOr = (CombiningEvaluator.Or)localObject1;
        localOr.add(localEvaluator1);
        localObject3 = localOr;
      }
      else
      {
        localObject3 = new CombiningEvaluator.Or();
        ((CombiningEvaluator.Or)localObject3).add((Evaluator)localObject1);
        ((CombiningEvaluator.Or)localObject3).add(localEvaluator1);
      }
    }
    if (i != 0)
    {
      ((CombiningEvaluator.Or)localObject2).replaceRightMostEvaluator((Evaluator)localObject3);
      localObject3 = localObject2;
    }
    this.evals.add(localObject3);
    return;
    label381: StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Unknown combinator: ");
    localStringBuilder.append(paramChar);
    throw new Selector.SelectorParseException(localStringBuilder.toString(), new Object[0]);
  }

  private int consumeIndex()
  {
    String str = this.tq.chompTo(")").trim();
    Validate.isTrue(StringUtil.isNumeric(str), "Index must be numeric");
    return Integer.parseInt(str);
  }

  private String consumeSubQuery()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    while (!this.tq.isEmpty())
      if (this.tq.matches("("))
      {
        localStringBuilder.append("(");
        localStringBuilder.append(this.tq.chompBalanced('(', ')'));
        localStringBuilder.append(")");
      }
      else if (this.tq.matches("["))
      {
        localStringBuilder.append("[");
        localStringBuilder.append(this.tq.chompBalanced('[', ']'));
        localStringBuilder.append("]");
      }
      else
      {
        if (this.tq.matchesAny(combinators))
          break;
        localStringBuilder.append(this.tq.consume());
      }
    return localStringBuilder.toString();
  }

  private void contains(boolean paramBoolean)
  {
    TokenQueue localTokenQueue = this.tq;
    String str1;
    if (paramBoolean)
      str1 = ":containsOwn";
    else
      str1 = ":contains";
    localTokenQueue.consume(str1);
    String str2 = TokenQueue.unescape(this.tq.chompBalanced('(', ')'));
    Validate.notEmpty(str2, ":contains(text) query must not be empty");
    if (paramBoolean)
      this.evals.add(new Evaluator.ContainsOwnText(str2));
    else
      this.evals.add(new Evaluator.ContainsText(str2));
  }

  private void cssNthChild(boolean paramBoolean1, boolean paramBoolean2)
  {
    String str1 = this.tq.chompTo(")").trim().toLowerCase();
    Matcher localMatcher1 = NTH_AB.matcher(str1);
    Matcher localMatcher2 = NTH_B.matcher(str1);
    boolean bool = "odd".equals(str1);
    int i = 2;
    int j;
    if (bool)
    {
      j = 1;
    }
    else if ("even".equals(str1))
    {
      j = 0;
    }
    else if (localMatcher1.matches())
    {
      if (localMatcher1.group(3) != null)
        i = Integer.parseInt(localMatcher1.group(1).replaceFirst("^\\+", ""));
      else
        i = 1;
      String str2 = localMatcher1.group(4);
      j = 0;
      if (str2 != null)
        j = Integer.parseInt(localMatcher1.group(4).replaceFirst("^\\+", ""));
    }
    else
    {
      if (!localMatcher2.matches())
        break label292;
      j = Integer.parseInt(localMatcher2.group().replaceFirst("^\\+", ""));
      i = 0;
    }
    if (paramBoolean2)
    {
      if (paramBoolean1)
        this.evals.add(new Evaluator.IsNthLastOfType(i, j));
      else
        this.evals.add(new Evaluator.IsNthOfType(i, j));
    }
    else if (paramBoolean1)
      this.evals.add(new Evaluator.IsNthLastChild(i, j));
    else
      this.evals.add(new Evaluator.IsNthChild(i, j));
    return;
    label292: throw new Selector.SelectorParseException("Could not parse nth-index '%s': unexpected format", new Object[] { str1 });
  }

  private void findElements()
  {
    if (this.tq.matchChomp("#"))
    {
      byId();
    }
    else if (this.tq.matchChomp("."))
    {
      byClass();
    }
    else if (this.tq.matchesWord())
    {
      byTag();
    }
    else if (this.tq.matches("["))
    {
      byAttribute();
    }
    else if (this.tq.matchChomp("*"))
    {
      allElements();
    }
    else if (this.tq.matchChomp(":lt("))
    {
      indexLessThan();
    }
    else if (this.tq.matchChomp(":gt("))
    {
      indexGreaterThan();
    }
    else if (this.tq.matchChomp(":eq("))
    {
      indexEquals();
    }
    else if (this.tq.matches(":has("))
    {
      has();
    }
    else if (this.tq.matches(":contains("))
    {
      contains(false);
    }
    else if (this.tq.matches(":containsOwn("))
    {
      contains(true);
    }
    else if (this.tq.matches(":matches("))
    {
      matches(false);
    }
    else if (this.tq.matches(":matchesOwn("))
    {
      matches(true);
    }
    else if (this.tq.matches(":not("))
    {
      not();
    }
    else if (this.tq.matchChomp(":nth-child("))
    {
      cssNthChild(false, false);
    }
    else if (this.tq.matchChomp(":nth-last-child("))
    {
      cssNthChild(true, false);
    }
    else if (this.tq.matchChomp(":nth-of-type("))
    {
      cssNthChild(false, true);
    }
    else if (this.tq.matchChomp(":nth-last-of-type("))
    {
      cssNthChild(true, true);
    }
    else if (this.tq.matchChomp(":first-child"))
    {
      this.evals.add(new Evaluator.IsFirstChild());
    }
    else if (this.tq.matchChomp(":last-child"))
    {
      this.evals.add(new Evaluator.IsLastChild());
    }
    else if (this.tq.matchChomp(":first-of-type"))
    {
      this.evals.add(new Evaluator.IsFirstOfType());
    }
    else if (this.tq.matchChomp(":last-of-type"))
    {
      this.evals.add(new Evaluator.IsLastOfType());
    }
    else if (this.tq.matchChomp(":only-child"))
    {
      this.evals.add(new Evaluator.IsOnlyChild());
    }
    else if (this.tq.matchChomp(":only-of-type"))
    {
      this.evals.add(new Evaluator.IsOnlyOfType());
    }
    else if (this.tq.matchChomp(":empty"))
    {
      this.evals.add(new Evaluator.IsEmpty());
    }
    else
    {
      if (!this.tq.matchChomp(":root"))
        break label631;
      this.evals.add(new Evaluator.IsRoot());
    }
    return;
    label631: Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.query;
    arrayOfObject[1] = this.tq.remainder();
    throw new Selector.SelectorParseException("Could not parse query '%s': unexpected token at '%s'", arrayOfObject);
  }

  private void has()
  {
    this.tq.consume(":has");
    String str = this.tq.chompBalanced('(', ')');
    Validate.notEmpty(str, ":has(el) subselect must not be empty");
    this.evals.add(new StructuralEvaluator.Has(parse(str)));
  }

  private void indexEquals()
  {
    this.evals.add(new Evaluator.IndexEquals(consumeIndex()));
  }

  private void indexGreaterThan()
  {
    this.evals.add(new Evaluator.IndexGreaterThan(consumeIndex()));
  }

  private void indexLessThan()
  {
    this.evals.add(new Evaluator.IndexLessThan(consumeIndex()));
  }

  private void matches(boolean paramBoolean)
  {
    TokenQueue localTokenQueue = this.tq;
    String str1;
    if (paramBoolean)
      str1 = ":matchesOwn";
    else
      str1 = ":matches";
    localTokenQueue.consume(str1);
    String str2 = this.tq.chompBalanced('(', ')');
    Validate.notEmpty(str2, ":matches(regex) query must not be empty");
    if (paramBoolean)
      this.evals.add(new Evaluator.MatchesOwn(Pattern.compile(str2)));
    else
      this.evals.add(new Evaluator.Matches(Pattern.compile(str2)));
  }

  private void not()
  {
    this.tq.consume(":not");
    String str = this.tq.chompBalanced('(', ')');
    Validate.notEmpty(str, ":not(selector) subselect must not be empty");
    this.evals.add(new StructuralEvaluator.Not(parse(str)));
  }

  public static Evaluator parse(String paramString)
  {
    return new QueryParser(paramString).parse();
  }

  Evaluator parse()
  {
    this.tq.consumeWhitespace();
    if (this.tq.matchesAny(combinators))
    {
      this.evals.add(new StructuralEvaluator.Root());
      combinator(this.tq.consume());
    }
    else
    {
      findElements();
    }
    while (!this.tq.isEmpty())
    {
      boolean bool = this.tq.consumeWhitespace();
      if (this.tq.matchesAny(combinators))
        combinator(this.tq.consume());
      else if (bool)
        combinator(' ');
      else
        findElements();
    }
    if (this.evals.size() == 1)
      return (Evaluator)this.evals.get(0);
    return new CombiningEvaluator.And(this.evals);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.select.QueryParser
 * JD-Core Version:    0.6.1
 */