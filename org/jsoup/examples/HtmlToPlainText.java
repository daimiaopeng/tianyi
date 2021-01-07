package org.jsoup.examples;

import java.io.PrintStream;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

public class HtmlToPlainText
{
  public static void main(String[] paramArrayOfString)
  {
    int i = paramArrayOfString.length;
    int j = 1;
    if (i != j)
      j = 0;
    Validate.isTrue(j, "usage: supply url to fetch");
    Document localDocument = Jsoup.connect(paramArrayOfString[0]).get();
    String str = new HtmlToPlainText().getPlainText(localDocument);
    System.out.println(str);
  }

  public String getPlainText(Element paramElement)
  {
    FormattingVisitor localFormattingVisitor = new FormattingVisitor(null);
    new NodeTraversor(localFormattingVisitor).traverse(paramElement);
    return localFormattingVisitor.toString();
  }

  private class FormattingVisitor
    implements NodeVisitor
  {
    private static final int maxWidth = 80;
    private StringBuilder accum = new StringBuilder();
    private int width = 0;

    private FormattingVisitor()
    {
    }

    private void append(String paramString)
    {
      if (paramString.startsWith("\n"))
        this.width = 0;
      if (paramString.equals(" "))
        if (this.accum.length() != 0)
        {
          if (!StringUtil.in(this.accum.substring(this.accum.length() - 1), new String[] { " ", "\n" }));
        }
        else
          return;
      if (paramString.length() + this.width > 80)
      {
        String[] arrayOfString = paramString.split("\\s+");
        for (int i = 0; i < arrayOfString.length; i++)
        {
          String str = arrayOfString[i];
          int j;
          if (i == arrayOfString.length - 1)
            j = 1;
          else
            j = 0;
          if (j == 0)
          {
            StringBuilder localStringBuilder1 = new StringBuilder();
            localStringBuilder1.append(str);
            localStringBuilder1.append(" ");
            str = localStringBuilder1.toString();
          }
          if (str.length() + this.width > 80)
          {
            StringBuilder localStringBuilder2 = this.accum;
            localStringBuilder2.append("\n");
            localStringBuilder2.append(str);
            this.width = str.length();
          }
          else
          {
            this.accum.append(str);
            this.width += str.length();
          }
        }
      }
      this.accum.append(paramString);
      this.width += paramString.length();
    }

    public void head(Node paramNode, int paramInt)
    {
      String str = paramNode.nodeName();
      if ((paramNode instanceof TextNode))
        append(((TextNode)paramNode).text());
      else if (str.equals("li"))
        append("\n * ");
    }

    public void tail(Node paramNode, int paramInt)
    {
      String str = paramNode.nodeName();
      if (str.equals("br"))
      {
        append("\n");
      }
      else if (StringUtil.in(str, new String[] { "p", "h1", "h2", "h3", "h4", "h5" }))
      {
        append("\n\n");
      }
      else if (str.equals("a"))
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = paramNode.absUrl("href");
        append(String.format(" <%s>", arrayOfObject));
      }
    }

    public String toString()
    {
      return this.accum.toString();
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.examples.HtmlToPlainText
 * JD-Core Version:    0.6.1
 */