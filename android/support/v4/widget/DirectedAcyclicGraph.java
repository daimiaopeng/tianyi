package android.support.v4.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SimplePool;
import android.support.v4.util.SimpleArrayMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
public final class DirectedAcyclicGraph<T>
{
  private final SimpleArrayMap<T, ArrayList<T>> mGraph = new SimpleArrayMap();
  private final Pools.Pool<ArrayList<T>> mListPool = new Pools.SimplePool(10);
  private final ArrayList<T> mSortResult = new ArrayList();
  private final HashSet<T> mSortTmpMarked = new HashSet();

  private void dfs(T paramT, ArrayList<T> paramArrayList, HashSet<T> paramHashSet)
  {
    if (paramArrayList.contains(paramT))
      return;
    if (paramHashSet.contains(paramT))
      throw new RuntimeException("This graph contains cyclic dependencies");
    paramHashSet.add(paramT);
    ArrayList localArrayList = (ArrayList)this.mGraph.get(paramT);
    if (localArrayList != null)
    {
      int i = 0;
      int j = localArrayList.size();
      while (i < j)
      {
        dfs(localArrayList.get(i), paramArrayList, paramHashSet);
        i++;
      }
    }
    paramHashSet.remove(paramT);
    paramArrayList.add(paramT);
  }

  @NonNull
  private ArrayList<T> getEmptyList()
  {
    ArrayList localArrayList = (ArrayList)this.mListPool.acquire();
    if (localArrayList == null)
      localArrayList = new ArrayList();
    return localArrayList;
  }

  private void poolList(@NonNull ArrayList<T> paramArrayList)
  {
    paramArrayList.clear();
    this.mListPool.release(paramArrayList);
  }

  public void addEdge(@NonNull T paramT1, @NonNull T paramT2)
  {
    if ((this.mGraph.containsKey(paramT1)) && (this.mGraph.containsKey(paramT2)))
    {
      ArrayList localArrayList = (ArrayList)this.mGraph.get(paramT1);
      if (localArrayList == null)
      {
        localArrayList = getEmptyList();
        this.mGraph.put(paramT1, localArrayList);
      }
      localArrayList.add(paramT2);
      return;
    }
    throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
  }

  public void addNode(@NonNull T paramT)
  {
    if (!this.mGraph.containsKey(paramT))
      this.mGraph.put(paramT, null);
  }

  public void clear()
  {
    int i = this.mGraph.size();
    for (int j = 0; j < i; j++)
    {
      ArrayList localArrayList = (ArrayList)this.mGraph.valueAt(j);
      if (localArrayList != null)
        poolList(localArrayList);
    }
    this.mGraph.clear();
  }

  public boolean contains(@NonNull T paramT)
  {
    return this.mGraph.containsKey(paramT);
  }

  @Nullable
  public List getIncomingEdges(@NonNull T paramT)
  {
    return (List)this.mGraph.get(paramT);
  }

  @Nullable
  public List<T> getOutgoingEdges(@NonNull T paramT)
  {
    int i = this.mGraph.size();
    ArrayList localArrayList1 = null;
    for (int j = 0; j < i; j++)
    {
      ArrayList localArrayList2 = (ArrayList)this.mGraph.valueAt(j);
      if ((localArrayList2 != null) && (localArrayList2.contains(paramT)))
      {
        if (localArrayList1 == null)
          localArrayList1 = new ArrayList();
        localArrayList1.add(this.mGraph.keyAt(j));
      }
    }
    return localArrayList1;
  }

  @NonNull
  public ArrayList<T> getSortedList()
  {
    this.mSortResult.clear();
    this.mSortTmpMarked.clear();
    int i = this.mGraph.size();
    for (int j = 0; j < i; j++)
      dfs(this.mGraph.keyAt(j), this.mSortResult, this.mSortTmpMarked);
    return this.mSortResult;
  }

  public boolean hasOutgoingEdges(@NonNull T paramT)
  {
    int i = this.mGraph.size();
    for (int j = 0; j < i; j++)
    {
      ArrayList localArrayList = (ArrayList)this.mGraph.valueAt(j);
      if ((localArrayList != null) && (localArrayList.contains(paramT)))
        return true;
    }
    return false;
  }

  int size()
  {
    return this.mGraph.size();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.widget.DirectedAcyclicGraph
 * JD-Core Version:    0.6.1
 */