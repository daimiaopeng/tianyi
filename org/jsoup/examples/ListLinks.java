package org.jsoup.examples;

import java.io.PrintStream;
import java.util.Iterator;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ListLinks
{
  public static void main(String[] paramArrayOfString)
  {
    boolean bool;
    if (paramArrayOfString.length == 1)
      bool = true;
    else
      bool = false;
    Validate.isTrue(bool, "usage: supply url to fetch");
    String str = paramArrayOfString[0];
    print("Fetching %s...", new Object[] { str });
    Document localDocument = Jsoup.connect(str).get();
    Elements localElements1 = localDocument.select("a[href]");
    Elements localElements2 = localDocument.select("[src]");
    Elements localElements3 = localDocument.select("link[href]");
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = Integer.valueOf(localElements2.size());
    print("\nMedia: (%d)", arrayOfObject1);
    Iterator localIterator1 = localElements2.iterator();
    while (localIterator1.hasNext())
    {
      Element localElement3 = (Element)localIterator1.next();
      if (localElement3.tagName().equals("img"))
      {
        Object[] arrayOfObject7 = new Object[5];
        arrayOfObject7[0] = localElement3.tagName();
        arrayOfObject7[1] = localElement3.attr("abs:src");
        arrayOfObject7[2] = localElement3.attr("width");
        arrayOfObject7[3] = localElement3.attr("height");
        arrayOfObject7[4] = trim(localElement3.attr("alt"), 20);
        print(" * %s: <%s> %sx%s (%s)", arrayOfObject7);
      }
      else
      {
        Object[] arrayOfObject6 = new Object[2];
        arrayOfObject6[0] = localElement3.tagName();
        arrayOfObject6[1] = localElement3.attr("abs:src");
        print(" * %s: <%s>", arrayOfObject6);
      }
    }
    Object[] arrayOfObject2 = new Object[1];
    arrayOfObject2[0] = Integer.valueOf(localElements3.size());
    print("\nImports: (%d)", arrayOfObject2);
    Iterator localIterator2 = localElements3.iterator();
    while (localIterator2.hasNext())
    {
      Element localElement2 = (Element)localIterator2.next();
      Object[] arrayOfObject5 = new Object[3];
      arrayOfObject5[0] = localElement2.tagName();
      arrayOfObject5[1] = localElement2.attr("abs:href");
      arrayOfObject5[2] = localElement2.attr("rel");
      print(" * %s <%s> (%s)", arrayOfObject5);
    }
    Object[] arrayOfObject3 = new Object[1];
    arrayOfObject3[0] = Integer.valueOf(localElements1.size());
    print("\nLinks: (%d)", arrayOfObject3);
    Iterator localIterator3 = localElements1.iterator();
    while (localIterator3.hasNext())
    {
      Element localElement1 = (Element)localIterator3.next();
      Object[] arrayOfObject4 = new Object[2];
      arrayOfObject4[0] = localElement1.attr("abs:href");
      arrayOfObject4[1] = trim(localElement1.text(), 35);
      print(" * a: <%s>  (%s)", arrayOfObject4);
    }
  }

  private static void print(String paramString, Object[] paramArrayOfObject)
  {
    System.out.println(String.format(paramString, paramArrayOfObject));
  }

  private static String trim(String paramString, int paramInt)
  {
    if (paramString.length() > paramInt)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString.substring(0, paramInt - 1));
      localStringBuilder.append(".");
      return localStringBuilder.toString();
    }
    return paramString;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.examples.ListLinks
 * JD-Core Version:    0.6.1
 */