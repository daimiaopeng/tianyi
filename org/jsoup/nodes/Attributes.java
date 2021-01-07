package org.jsoup.nodes;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.jsoup.helper.Validate;

public class Attributes
  implements Cloneable, Iterable<Attribute>
{
  protected static final String dataPrefix = "data-";
  private LinkedHashMap<String, Attribute> attributes = null;

  private static String dataKey(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("data-");
    localStringBuilder.append(paramString);
    return localStringBuilder.toString();
  }

  public void addAll(Attributes paramAttributes)
  {
    if (paramAttributes.size() == 0)
      return;
    if (this.attributes == null)
      this.attributes = new LinkedHashMap(paramAttributes.size());
    this.attributes.putAll(paramAttributes.attributes);
  }

  public List<Attribute> asList()
  {
    if (this.attributes == null)
      return Collections.emptyList();
    ArrayList localArrayList = new ArrayList(this.attributes.size());
    Iterator localIterator = this.attributes.entrySet().iterator();
    while (localIterator.hasNext())
      localArrayList.add(((Map.Entry)localIterator.next()).getValue());
    return Collections.unmodifiableList(localArrayList);
  }

  public Attributes clone()
  {
    if (this.attributes == null)
      return new Attributes();
    try
    {
      Attributes localAttributes = (Attributes)super.clone();
      localAttributes.attributes = new LinkedHashMap(this.attributes.size());
      Iterator localIterator = iterator();
      while (localIterator.hasNext())
      {
        Attribute localAttribute = (Attribute)localIterator.next();
        localAttributes.attributes.put(localAttribute.getKey(), localAttribute.clone());
      }
      return localAttributes;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new RuntimeException(localCloneNotSupportedException);
    }
  }

  public Map<String, String> dataset()
  {
    return new Dataset(null);
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if (!(paramObject instanceof Attributes))
      return false;
    Attributes localAttributes = (Attributes)paramObject;
    return this.attributes != null ? this.attributes.equals(localAttributes.attributes) : localAttributes.attributes == null;
  }

  public String get(String paramString)
  {
    Validate.notEmpty(paramString);
    if (this.attributes == null)
      return "";
    Attribute localAttribute = (Attribute)this.attributes.get(paramString.toLowerCase());
    String str;
    if (localAttribute != null)
      str = localAttribute.getValue();
    else
      str = "";
    return str;
  }

  public boolean hasKey(String paramString)
  {
    boolean bool;
    if ((this.attributes != null) && (this.attributes.containsKey(paramString.toLowerCase())))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public int hashCode()
  {
    int i;
    if (this.attributes != null)
      i = this.attributes.hashCode();
    else
      i = 0;
    return i;
  }

  public String html()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    html(localStringBuilder, new Document("").outputSettings());
    return localStringBuilder.toString();
  }

  void html(StringBuilder paramStringBuilder, Document.OutputSettings paramOutputSettings)
  {
    if (this.attributes == null)
      return;
    Iterator localIterator = this.attributes.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Attribute localAttribute = (Attribute)((Map.Entry)localIterator.next()).getValue();
      paramStringBuilder.append(" ");
      localAttribute.html(paramStringBuilder, paramOutputSettings);
    }
  }

  public Iterator<Attribute> iterator()
  {
    return asList().iterator();
  }

  public void put(String paramString1, String paramString2)
  {
    put(new Attribute(paramString1, paramString2));
  }

  public void put(Attribute paramAttribute)
  {
    Validate.notNull(paramAttribute);
    if (this.attributes == null)
      this.attributes = new LinkedHashMap(2);
    this.attributes.put(paramAttribute.getKey(), paramAttribute);
  }

  public void remove(String paramString)
  {
    Validate.notEmpty(paramString);
    if (this.attributes == null)
      return;
    this.attributes.remove(paramString.toLowerCase());
  }

  public int size()
  {
    if (this.attributes == null)
      return 0;
    return this.attributes.size();
  }

  public String toString()
  {
    return html();
  }

  private class Dataset extends AbstractMap<String, String>
  {
    private Dataset()
    {
      if (Attributes.this.attributes == null)
        Attributes.access$102(Attributes.this, new LinkedHashMap(2));
    }

    public Set<Map.Entry<String, String>> entrySet()
    {
      return new EntrySet(null);
    }

    public String put(String paramString1, String paramString2)
    {
      String str1 = Attributes.dataKey(paramString1);
      String str2;
      if (Attributes.this.hasKey(str1))
        str2 = ((Attribute)Attributes.this.attributes.get(str1)).getValue();
      else
        str2 = null;
      Attribute localAttribute = new Attribute(str1, paramString2);
      Attributes.this.attributes.put(str1, localAttribute);
      return str2;
    }

    private class DatasetIterator
      implements Iterator<Map.Entry<String, String>>
    {
      private Attribute attr;
      private Iterator<Attribute> attrIter = Attributes.this.attributes.values().iterator();

      private DatasetIterator()
      {
      }

      public boolean hasNext()
      {
        while (this.attrIter.hasNext())
        {
          this.attr = ((Attribute)this.attrIter.next());
          if (this.attr.isDataAttribute())
            return true;
        }
        return false;
      }

      public Map.Entry<String, String> next()
      {
        return new Attribute(this.attr.getKey().substring("data-".length()), this.attr.getValue());
      }

      public void remove()
      {
        Attributes.this.attributes.remove(this.attr.getKey());
      }
    }

    private class EntrySet extends AbstractSet<Map.Entry<String, String>>
    {
      private EntrySet()
      {
      }

      public Iterator<Map.Entry<String, String>> iterator()
      {
        return new Attributes.Dataset.DatasetIterator(Attributes.Dataset.this, null);
      }

      public int size()
      {
        Attributes.Dataset.DatasetIterator localDatasetIterator = new Attributes.Dataset.DatasetIterator(Attributes.Dataset.this, null);
        for (int i = 0; localDatasetIterator.hasNext(); i++);
        return i;
      }
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.nodes.Attributes
 * JD-Core Version:    0.6.1
 */