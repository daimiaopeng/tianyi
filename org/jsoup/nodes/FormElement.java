package org.jsoup.nodes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Connection.KeyVal;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection.KeyVal;
import org.jsoup.helper.Validate;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

public class FormElement extends Element
{
  private final Elements elements = new Elements();

  public FormElement(Tag paramTag, String paramString, Attributes paramAttributes)
  {
    super(paramTag, paramString, paramAttributes);
  }

  public FormElement addElement(Element paramElement)
  {
    this.elements.add(paramElement);
    return this;
  }

  public Elements elements()
  {
    return this.elements;
  }

  public boolean equals(Object paramObject)
  {
    return super.equals(paramObject);
  }

  public List<Connection.KeyVal> formData()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator1 = this.elements.iterator();
    while (localIterator1.hasNext())
    {
      Element localElement = (Element)localIterator1.next();
      if (localElement.tag().isFormSubmittable())
      {
        String str = localElement.attr("name");
        if (str.length() != 0)
          if ("select".equals(localElement.tagName()))
          {
            Iterator localIterator2 = localElement.select("option[selected]").iterator();
            while (localIterator2.hasNext())
              localArrayList.add(HttpConnection.KeyVal.create(str, ((Element)localIterator2.next()).val()));
          }
          else
          {
            localArrayList.add(HttpConnection.KeyVal.create(str, localElement.val()));
          }
      }
    }
    return localArrayList;
  }

  public Connection submit()
  {
    String str;
    if (hasAttr("action"))
      str = absUrl("action");
    else
      str = baseUri();
    Validate.notEmpty(str, "Could not determine a form action URL for submit. Ensure you set a base URI when parsing.");
    Connection.Method localMethod;
    if (attr("method").toUpperCase().equals("POST"))
      localMethod = Connection.Method.POST;
    else
      localMethod = Connection.Method.GET;
    return Jsoup.connect(str).data(formData()).method(localMethod);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.nodes.FormElement
 * JD-Core Version:    0.6.1
 */