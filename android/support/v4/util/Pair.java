package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Pair<F, S>
{

  @Nullable
  public final F first;

  @Nullable
  public final S second;

  public Pair(@Nullable F paramF, @Nullable S paramS)
  {
    this.first = paramF;
    this.second = paramS;
  }

  @NonNull
  public static <A, B> Pair<A, B> create(@Nullable A paramA, @Nullable B paramB)
  {
    return new Pair(paramA, paramB);
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Pair))
      return false;
    Pair localPair = (Pair)paramObject;
    boolean bool1 = ObjectsCompat.equals(localPair.first, this.first);
    boolean bool2 = false;
    if (bool1)
    {
      boolean bool3 = ObjectsCompat.equals(localPair.second, this.second);
      bool2 = false;
      if (bool3)
        bool2 = true;
    }
    return bool2;
  }

  public int hashCode()
  {
    int i;
    if (this.first == null)
      i = 0;
    else
      i = this.first.hashCode();
    int j;
    if (this.second == null)
      j = 0;
    else
      j = this.second.hashCode();
    return i ^ j;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Pair{");
    localStringBuilder.append(String.valueOf(this.first));
    localStringBuilder.append(" ");
    localStringBuilder.append(String.valueOf(this.second));
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.util.Pair
 * JD-Core Version:    0.6.1
 */