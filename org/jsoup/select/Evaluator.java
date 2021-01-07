package org.jsoup.select;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.parser.Tag;

public abstract class Evaluator
{
  public abstract boolean matches(Element paramElement1, Element paramElement2);

  public static final class AllElements extends Evaluator
  {
    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return true;
    }

    public String toString()
    {
      return "*";
    }
  }

  public static final class Attribute extends Evaluator
  {
    private String key;

    public Attribute(String paramString)
    {
      this.key = paramString;
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return paramElement2.hasAttr(this.key);
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.key;
      return String.format("[%s]", arrayOfObject);
    }
  }

  public static abstract class AttributeKeyPair extends Evaluator
  {
    String key;
    String value;

    public AttributeKeyPair(String paramString1, String paramString2)
    {
      Validate.notEmpty(paramString1);
      Validate.notEmpty(paramString2);
      this.key = paramString1.trim().toLowerCase();
      if ((paramString2.startsWith("\"")) && (paramString2.endsWith("\"")))
        paramString2 = paramString2.substring(1, paramString2.length() - 1);
      this.value = paramString2.trim().toLowerCase();
    }
  }

  public static final class AttributeStarting extends Evaluator
  {
    private String keyPrefix;

    public AttributeStarting(String paramString)
    {
      this.keyPrefix = paramString;
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      Iterator localIterator = paramElement2.attributes().asList().iterator();
      while (localIterator.hasNext())
        if (((Attribute)localIterator.next()).getKey().startsWith(this.keyPrefix))
          return true;
      return false;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.keyPrefix;
      return String.format("[^%s]", arrayOfObject);
    }
  }

  public static final class AttributeWithValue extends Evaluator.AttributeKeyPair
  {
    public AttributeWithValue(String paramString1, String paramString2)
    {
      super(paramString2);
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      boolean bool;
      if ((paramElement2.hasAttr(this.key)) && (this.value.equalsIgnoreCase(paramElement2.attr(this.key))))
        bool = true;
      else
        bool = false;
      return bool;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.key;
      arrayOfObject[1] = this.value;
      return String.format("[%s=%s]", arrayOfObject);
    }
  }

  public static final class AttributeWithValueContaining extends Evaluator.AttributeKeyPair
  {
    public AttributeWithValueContaining(String paramString1, String paramString2)
    {
      super(paramString2);
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      boolean bool;
      if ((paramElement2.hasAttr(this.key)) && (paramElement2.attr(this.key).toLowerCase().contains(this.value)))
        bool = true;
      else
        bool = false;
      return bool;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.key;
      arrayOfObject[1] = this.value;
      return String.format("[%s*=%s]", arrayOfObject);
    }
  }

  public static final class AttributeWithValueEnding extends Evaluator.AttributeKeyPair
  {
    public AttributeWithValueEnding(String paramString1, String paramString2)
    {
      super(paramString2);
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      boolean bool;
      if ((paramElement2.hasAttr(this.key)) && (paramElement2.attr(this.key).toLowerCase().endsWith(this.value)))
        bool = true;
      else
        bool = false;
      return bool;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.key;
      arrayOfObject[1] = this.value;
      return String.format("[%s$=%s]", arrayOfObject);
    }
  }

  public static final class AttributeWithValueMatching extends Evaluator
  {
    String key;
    Pattern pattern;

    public AttributeWithValueMatching(String paramString, Pattern paramPattern)
    {
      this.key = paramString.trim().toLowerCase();
      this.pattern = paramPattern;
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      boolean bool;
      if ((paramElement2.hasAttr(this.key)) && (this.pattern.matcher(paramElement2.attr(this.key)).find()))
        bool = true;
      else
        bool = false;
      return bool;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.key;
      arrayOfObject[1] = this.pattern.toString();
      return String.format("[%s~=%s]", arrayOfObject);
    }
  }

  public static final class AttributeWithValueNot extends Evaluator.AttributeKeyPair
  {
    public AttributeWithValueNot(String paramString1, String paramString2)
    {
      super(paramString2);
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return true ^ this.value.equalsIgnoreCase(paramElement2.attr(this.key));
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.key;
      arrayOfObject[1] = this.value;
      return String.format("[%s!=%s]", arrayOfObject);
    }
  }

  public static final class AttributeWithValueStarting extends Evaluator.AttributeKeyPair
  {
    public AttributeWithValueStarting(String paramString1, String paramString2)
    {
      super(paramString2);
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      boolean bool;
      if ((paramElement2.hasAttr(this.key)) && (paramElement2.attr(this.key).toLowerCase().startsWith(this.value)))
        bool = true;
      else
        bool = false;
      return bool;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.key;
      arrayOfObject[1] = this.value;
      return String.format("[%s^=%s]", arrayOfObject);
    }
  }

  public static final class Class extends Evaluator
  {
    private String className;

    public Class(String paramString)
    {
      this.className = paramString;
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return paramElement2.hasClass(this.className);
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.className;
      return String.format(".%s", arrayOfObject);
    }
  }

  public static final class ContainsOwnText extends Evaluator
  {
    private String searchText;

    public ContainsOwnText(String paramString)
    {
      this.searchText = paramString.toLowerCase();
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return paramElement2.ownText().toLowerCase().contains(this.searchText);
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.searchText;
      return String.format(":containsOwn(%s", arrayOfObject);
    }
  }

  public static final class ContainsText extends Evaluator
  {
    private String searchText;

    public ContainsText(String paramString)
    {
      this.searchText = paramString.toLowerCase();
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return paramElement2.text().toLowerCase().contains(this.searchText);
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.searchText;
      return String.format(":contains(%s", arrayOfObject);
    }
  }

  public static abstract class CssNthEvaluator extends Evaluator
  {
    protected final int a;
    protected final int b;

    public CssNthEvaluator(int paramInt)
    {
      this(0, paramInt);
    }

    public CssNthEvaluator(int paramInt1, int paramInt2)
    {
      this.a = paramInt1;
      this.b = paramInt2;
    }

    protected abstract int calculatePosition(Element paramElement1, Element paramElement2);

    protected abstract String getPseudoClass();

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      Element localElement = paramElement2.parent();
      if ((localElement != null) && (!(localElement instanceof Document)))
      {
        int i = calculatePosition(paramElement1, paramElement2);
        int j = this.a;
        boolean bool = true;
        if (j == 0)
        {
          if (i != this.b)
            bool = false;
          return bool;
        }
        if (((i - this.b) * this.a < 0) || ((i - this.b) % this.a != 0))
          bool = false;
        return bool;
      }
      return false;
    }

    public String toString()
    {
      if (this.a == 0)
      {
        Object[] arrayOfObject3 = new Object[2];
        arrayOfObject3[0] = getPseudoClass();
        arrayOfObject3[1] = Integer.valueOf(this.b);
        return String.format(":%s(%d)", arrayOfObject3);
      }
      if (this.b == 0)
      {
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = getPseudoClass();
        arrayOfObject2[1] = Integer.valueOf(this.a);
        return String.format(":%s(%dn)", arrayOfObject2);
      }
      Object[] arrayOfObject1 = new Object[3];
      arrayOfObject1[0] = getPseudoClass();
      arrayOfObject1[1] = Integer.valueOf(this.a);
      arrayOfObject1[2] = Integer.valueOf(this.b);
      return String.format(":%s(%dn%+d)", arrayOfObject1);
    }
  }

  public static final class Id extends Evaluator
  {
    private String id;

    public Id(String paramString)
    {
      this.id = paramString;
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return this.id.equals(paramElement2.id());
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.id;
      return String.format("#%s", arrayOfObject);
    }
  }

  public static final class IndexEquals extends Evaluator.IndexEvaluator
  {
    public IndexEquals(int paramInt)
    {
      super();
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      boolean bool;
      if (paramElement2.elementSiblingIndex().intValue() == this.index)
        bool = true;
      else
        bool = false;
      return bool;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(this.index);
      return String.format(":eq(%d)", arrayOfObject);
    }
  }

  public static abstract class IndexEvaluator extends Evaluator
  {
    int index;

    public IndexEvaluator(int paramInt)
    {
      this.index = paramInt;
    }
  }

  public static final class IndexGreaterThan extends Evaluator.IndexEvaluator
  {
    public IndexGreaterThan(int paramInt)
    {
      super();
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      boolean bool;
      if (paramElement2.elementSiblingIndex().intValue() > this.index)
        bool = true;
      else
        bool = false;
      return bool;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(this.index);
      return String.format(":gt(%d)", arrayOfObject);
    }
  }

  public static final class IndexLessThan extends Evaluator.IndexEvaluator
  {
    public IndexLessThan(int paramInt)
    {
      super();
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      boolean bool;
      if (paramElement2.elementSiblingIndex().intValue() < this.index)
        bool = true;
      else
        bool = false;
      return bool;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(this.index);
      return String.format(":lt(%d)", arrayOfObject);
    }
  }

  public static final class IsEmpty extends Evaluator
  {
    public boolean matches(Element paramElement1, Element paramElement2)
    {
      List localList = paramElement2.childNodes();
      for (int i = 0; i < localList.size(); i++)
      {
        Node localNode = (Node)localList.get(i);
        if ((!(localNode instanceof Comment)) && (!(localNode instanceof XmlDeclaration)) && (!(localNode instanceof DocumentType)))
          return false;
      }
      return true;
    }

    public String toString()
    {
      return ":empty";
    }
  }

  public static final class IsFirstChild extends Evaluator
  {
    public boolean matches(Element paramElement1, Element paramElement2)
    {
      Element localElement = paramElement2.parent();
      boolean bool;
      if ((localElement != null) && (!(localElement instanceof Document)) && (paramElement2.elementSiblingIndex().intValue() == 0))
        bool = true;
      else
        bool = false;
      return bool;
    }

    public String toString()
    {
      return ":first-child";
    }
  }

  public static final class IsFirstOfType extends Evaluator.IsNthOfType
  {
    public IsFirstOfType()
    {
      super(1);
    }

    public String toString()
    {
      return ":first-of-type";
    }
  }

  public static final class IsLastChild extends Evaluator
  {
    public boolean matches(Element paramElement1, Element paramElement2)
    {
      Element localElement = paramElement2.parent();
      int i = 1;
      if ((localElement == null) || ((localElement instanceof Document)) || (paramElement2.elementSiblingIndex().intValue() != localElement.children().size() - i))
        i = 0;
      return i;
    }

    public String toString()
    {
      return ":last-child";
    }
  }

  public static final class IsLastOfType extends Evaluator.IsNthLastOfType
  {
    public IsLastOfType()
    {
      super(1);
    }

    public String toString()
    {
      return ":last-of-type";
    }
  }

  public static final class IsNthChild extends Evaluator.CssNthEvaluator
  {
    public IsNthChild(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }

    protected int calculatePosition(Element paramElement1, Element paramElement2)
    {
      return 1 + paramElement2.elementSiblingIndex().intValue();
    }

    protected String getPseudoClass()
    {
      return "nth-child";
    }
  }

  public static final class IsNthLastChild extends Evaluator.CssNthEvaluator
  {
    public IsNthLastChild(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }

    protected int calculatePosition(Element paramElement1, Element paramElement2)
    {
      return paramElement2.parent().children().size() - paramElement2.elementSiblingIndex().intValue();
    }

    protected String getPseudoClass()
    {
      return "nth-last-child";
    }
  }

  public static class IsNthLastOfType extends Evaluator.CssNthEvaluator
  {
    public IsNthLastOfType(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }

    protected int calculatePosition(Element paramElement1, Element paramElement2)
    {
      Elements localElements = paramElement2.parent().children();
      int i = paramElement2.elementSiblingIndex().intValue();
      int j = 0;
      while (i < localElements.size())
      {
        if (localElements.get(i).tag().equals(paramElement2.tag()))
          j++;
        i++;
      }
      return j;
    }

    protected String getPseudoClass()
    {
      return "nth-last-of-type";
    }
  }

  public static class IsNthOfType extends Evaluator.CssNthEvaluator
  {
    public IsNthOfType(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }

    protected int calculatePosition(Element paramElement1, Element paramElement2)
    {
      Elements localElements = paramElement2.parent().children();
      int i = 0;
      int j = 0;
      while (i < localElements.size())
      {
        if (localElements.get(i).tag().equals(paramElement2.tag()))
          j++;
        if (localElements.get(i) == paramElement2)
          break;
        i++;
      }
      return j;
    }

    protected String getPseudoClass()
    {
      return "nth-of-type";
    }
  }

  public static final class IsOnlyChild extends Evaluator
  {
    public boolean matches(Element paramElement1, Element paramElement2)
    {
      Element localElement = paramElement2.parent();
      boolean bool;
      if ((localElement != null) && (!(localElement instanceof Document)) && (paramElement2.siblingElements().size() == 0))
        bool = true;
      else
        bool = false;
      return bool;
    }

    public String toString()
    {
      return ":only-child";
    }
  }

  public static final class IsOnlyOfType extends Evaluator
  {
    public boolean matches(Element paramElement1, Element paramElement2)
    {
      Element localElement = paramElement2.parent();
      if ((localElement != null) && (!(localElement instanceof Document)))
      {
        Elements localElements = localElement.children();
        int i = 0;
        int j = 0;
        while (i < localElements.size())
        {
          if (localElements.get(i).tag().equals(paramElement2.tag()))
            j++;
          i++;
        }
        boolean bool = true;
        if (j != bool)
          bool = false;
        return bool;
      }
      return false;
    }

    public String toString()
    {
      return ":only-of-type";
    }
  }

  public static final class IsRoot extends Evaluator
  {
    public boolean matches(Element paramElement1, Element paramElement2)
    {
      if ((paramElement1 instanceof Document))
        paramElement1 = paramElement1.child(0);
      boolean bool = false;
      if (paramElement2 == paramElement1)
        bool = true;
      return bool;
    }

    public String toString()
    {
      return ":root";
    }
  }

  public static final class Matches extends Evaluator
  {
    private Pattern pattern;

    public Matches(Pattern paramPattern)
    {
      this.pattern = paramPattern;
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return this.pattern.matcher(paramElement2.text()).find();
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.pattern;
      return String.format(":matches(%s", arrayOfObject);
    }
  }

  public static final class MatchesOwn extends Evaluator
  {
    private Pattern pattern;

    public MatchesOwn(Pattern paramPattern)
    {
      this.pattern = paramPattern;
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return this.pattern.matcher(paramElement2.ownText()).find();
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.pattern;
      return String.format(":matchesOwn(%s", arrayOfObject);
    }
  }

  public static final class Tag extends Evaluator
  {
    private String tagName;

    public Tag(String paramString)
    {
      this.tagName = paramString;
    }

    public boolean matches(Element paramElement1, Element paramElement2)
    {
      return paramElement2.tagName().equals(this.tagName);
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.tagName;
      return String.format("%s", arrayOfObject);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.select.Evaluator
 * JD-Core Version:    0.6.1
 */