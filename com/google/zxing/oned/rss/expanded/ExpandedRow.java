package com.google.zxing.oned.rss.expanded;

import java.util.ArrayList;
import java.util.List;

final class ExpandedRow
{
  private final List<ExpandedPair> pairs;
  private final int rowNumber;
  private final boolean wasReversed;

  ExpandedRow(List<ExpandedPair> paramList, int paramInt, boolean paramBoolean)
  {
    this.pairs = new ArrayList(paramList);
    this.rowNumber = paramInt;
    this.wasReversed = paramBoolean;
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof ExpandedRow))
      return false;
    ExpandedRow localExpandedRow = (ExpandedRow)paramObject;
    boolean bool1 = this.pairs.equals(localExpandedRow.getPairs());
    boolean bool2 = false;
    if (bool1)
    {
      boolean bool3 = this.wasReversed;
      boolean bool4 = localExpandedRow.wasReversed;
      bool2 = false;
      if (bool3 == bool4)
        bool2 = true;
    }
    return bool2;
  }

  List<ExpandedPair> getPairs()
  {
    return this.pairs;
  }

  int getRowNumber()
  {
    return this.rowNumber;
  }

  public int hashCode()
  {
    return this.pairs.hashCode() ^ Boolean.valueOf(this.wasReversed).hashCode();
  }

  boolean isEquivalent(List<ExpandedPair> paramList)
  {
    return this.pairs.equals(paramList);
  }

  boolean isReversed()
  {
    return this.wasReversed;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("{ ");
    localStringBuilder.append(this.pairs);
    localStringBuilder.append(" }");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.expanded.ExpandedRow
 * JD-Core Version:    0.6.1
 */