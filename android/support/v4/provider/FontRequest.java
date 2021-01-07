package android.support.v4.provider;

import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.util.Preconditions;
import android.util.Base64;
import java.util.List;

public final class FontRequest
{
  private final List<List<byte[]>> mCertificates;
  private final int mCertificatesArray;
  private final String mIdentifier;
  private final String mProviderAuthority;
  private final String mProviderPackage;
  private final String mQuery;

  public FontRequest(@NonNull String paramString1, @NonNull String paramString2, @NonNull String paramString3, @ArrayRes int paramInt)
  {
    this.mProviderAuthority = ((String)Preconditions.checkNotNull(paramString1));
    this.mProviderPackage = ((String)Preconditions.checkNotNull(paramString2));
    this.mQuery = ((String)Preconditions.checkNotNull(paramString3));
    this.mCertificates = null;
    boolean bool;
    if (paramInt != 0)
      bool = true;
    else
      bool = false;
    Preconditions.checkArgument(bool);
    this.mCertificatesArray = paramInt;
    StringBuilder localStringBuilder = new StringBuilder(this.mProviderAuthority);
    localStringBuilder.append("-");
    localStringBuilder.append(this.mProviderPackage);
    localStringBuilder.append("-");
    localStringBuilder.append(this.mQuery);
    this.mIdentifier = localStringBuilder.toString();
  }

  public FontRequest(@NonNull String paramString1, @NonNull String paramString2, @NonNull String paramString3, @NonNull List<List<byte[]>> paramList)
  {
    this.mProviderAuthority = ((String)Preconditions.checkNotNull(paramString1));
    this.mProviderPackage = ((String)Preconditions.checkNotNull(paramString2));
    this.mQuery = ((String)Preconditions.checkNotNull(paramString3));
    this.mCertificates = ((List)Preconditions.checkNotNull(paramList));
    this.mCertificatesArray = 0;
    StringBuilder localStringBuilder = new StringBuilder(this.mProviderAuthority);
    localStringBuilder.append("-");
    localStringBuilder.append(this.mProviderPackage);
    localStringBuilder.append("-");
    localStringBuilder.append(this.mQuery);
    this.mIdentifier = localStringBuilder.toString();
  }

  @Nullable
  public List<List<byte[]>> getCertificates()
  {
    return this.mCertificates;
  }

  @ArrayRes
  public int getCertificatesArrayResId()
  {
    return this.mCertificatesArray;
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public String getIdentifier()
  {
    return this.mIdentifier;
  }

  @NonNull
  public String getProviderAuthority()
  {
    return this.mProviderAuthority;
  }

  @NonNull
  public String getProviderPackage()
  {
    return this.mProviderPackage;
  }

  @NonNull
  public String getQuery()
  {
    return this.mQuery;
  }

  public String toString()
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("FontRequest {mProviderAuthority: ");
    localStringBuilder2.append(this.mProviderAuthority);
    localStringBuilder2.append(", mProviderPackage: ");
    localStringBuilder2.append(this.mProviderPackage);
    localStringBuilder2.append(", mQuery: ");
    localStringBuilder2.append(this.mQuery);
    localStringBuilder2.append(", mCertificates:");
    localStringBuilder1.append(localStringBuilder2.toString());
    for (int i = 0; i < this.mCertificates.size(); i++)
    {
      localStringBuilder1.append(" [");
      List localList = (List)this.mCertificates.get(i);
      for (int j = 0; j < localList.size(); j++)
      {
        localStringBuilder1.append(" \"");
        localStringBuilder1.append(Base64.encodeToString((byte[])localList.get(j), 0));
        localStringBuilder1.append("\"");
      }
      localStringBuilder1.append(" ]");
    }
    localStringBuilder1.append("}");
    StringBuilder localStringBuilder3 = new StringBuilder();
    localStringBuilder3.append("mCertificatesArray: ");
    localStringBuilder3.append(this.mCertificatesArray);
    localStringBuilder1.append(localStringBuilder3.toString());
    return localStringBuilder1.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.provider.FontRequest
 * JD-Core Version:    0.6.1
 */