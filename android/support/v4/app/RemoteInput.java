package android.support.v4.app;

import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipDescription;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class RemoteInput
{
  private static final String EXTRA_DATA_TYPE_RESULTS_DATA = "android.remoteinput.dataTypeResultsData";
  public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
  public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";
  private static final String TAG = "RemoteInput";
  private final boolean mAllowFreeFormTextInput;
  private final Set<String> mAllowedDataTypes;
  private final CharSequence[] mChoices;
  private final Bundle mExtras;
  private final CharSequence mLabel;
  private final String mResultKey;

  RemoteInput(String paramString, CharSequence paramCharSequence, CharSequence[] paramArrayOfCharSequence, boolean paramBoolean, Bundle paramBundle, Set<String> paramSet)
  {
    this.mResultKey = paramString;
    this.mLabel = paramCharSequence;
    this.mChoices = paramArrayOfCharSequence;
    this.mAllowFreeFormTextInput = paramBoolean;
    this.mExtras = paramBundle;
    this.mAllowedDataTypes = paramSet;
  }

  public static void addDataResultToIntent(RemoteInput paramRemoteInput, Intent paramIntent, Map<String, Uri> paramMap)
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      android.app.RemoteInput.addDataResultToIntent(fromCompat(paramRemoteInput), paramIntent, paramMap);
    }
    else if (Build.VERSION.SDK_INT >= 16)
    {
      Intent localIntent = getClipDataIntentFromIntent(paramIntent);
      if (localIntent == null)
        localIntent = new Intent();
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        String str = (String)localEntry.getKey();
        Uri localUri = (Uri)localEntry.getValue();
        if (str != null)
        {
          Bundle localBundle = localIntent.getBundleExtra(getExtraResultsKeyForData(str));
          if (localBundle == null)
            localBundle = new Bundle();
          localBundle.putString(paramRemoteInput.getResultKey(), localUri.toString());
          localIntent.putExtra(getExtraResultsKeyForData(str), localBundle);
        }
      }
      paramIntent.setClipData(ClipData.newIntent("android.remoteinput.results", localIntent));
    }
    else
    {
      Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
    }
  }

  public static void addResultsToIntent(RemoteInput[] paramArrayOfRemoteInput, Intent paramIntent, Bundle paramBundle)
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      android.app.RemoteInput.addResultsToIntent(fromCompat(paramArrayOfRemoteInput), paramIntent, paramBundle);
    }
    else
    {
      int i = Build.VERSION.SDK_INT;
      int j = 0;
      if (i >= 20)
      {
        Bundle localBundle2 = getResultsFromIntent(paramIntent);
        if (localBundle2 != null)
        {
          localBundle2.putAll(paramBundle);
          paramBundle = localBundle2;
        }
        int m = paramArrayOfRemoteInput.length;
        for (int n = 0; n < m; n++)
        {
          RemoteInput localRemoteInput2 = paramArrayOfRemoteInput[n];
          Map localMap = getDataResultsFromIntent(paramIntent, localRemoteInput2.getResultKey());
          android.app.RemoteInput.addResultsToIntent(fromCompat(new RemoteInput[] { localRemoteInput2 }), paramIntent, paramBundle);
          if (localMap != null)
            addDataResultToIntent(localRemoteInput2, paramIntent, localMap);
        }
      }
      if (Build.VERSION.SDK_INT >= 16)
      {
        Intent localIntent = getClipDataIntentFromIntent(paramIntent);
        if (localIntent == null)
          localIntent = new Intent();
        Bundle localBundle1 = localIntent.getBundleExtra("android.remoteinput.resultsData");
        if (localBundle1 == null)
          localBundle1 = new Bundle();
        int k = paramArrayOfRemoteInput.length;
        while (j < k)
        {
          RemoteInput localRemoteInput1 = paramArrayOfRemoteInput[j];
          Object localObject = paramBundle.get(localRemoteInput1.getResultKey());
          if ((localObject instanceof CharSequence))
            localBundle1.putCharSequence(localRemoteInput1.getResultKey(), (CharSequence)localObject);
          j++;
        }
        localIntent.putExtra("android.remoteinput.resultsData", localBundle1);
        paramIntent.setClipData(ClipData.newIntent("android.remoteinput.results", localIntent));
      }
      else
      {
        Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
      }
    }
  }

  @RequiresApi(20)
  static android.app.RemoteInput fromCompat(RemoteInput paramRemoteInput)
  {
    return new android.app.RemoteInput.Builder(paramRemoteInput.getResultKey()).setLabel(paramRemoteInput.getLabel()).setChoices(paramRemoteInput.getChoices()).setAllowFreeFormInput(paramRemoteInput.getAllowFreeFormInput()).addExtras(paramRemoteInput.getExtras()).build();
  }

  @RequiresApi(20)
  static android.app.RemoteInput[] fromCompat(RemoteInput[] paramArrayOfRemoteInput)
  {
    if (paramArrayOfRemoteInput == null)
      return null;
    android.app.RemoteInput[] arrayOfRemoteInput = new android.app.RemoteInput[paramArrayOfRemoteInput.length];
    for (int i = 0; i < paramArrayOfRemoteInput.length; i++)
      arrayOfRemoteInput[i] = fromCompat(paramArrayOfRemoteInput[i]);
    return arrayOfRemoteInput;
  }

  @RequiresApi(16)
  private static Intent getClipDataIntentFromIntent(Intent paramIntent)
  {
    ClipData localClipData = paramIntent.getClipData();
    if (localClipData == null)
      return null;
    ClipDescription localClipDescription = localClipData.getDescription();
    if (!localClipDescription.hasMimeType("text/vnd.android.intent"))
      return null;
    if (!localClipDescription.getLabel().equals("android.remoteinput.results"))
      return null;
    return localClipData.getItemAt(0).getIntent();
  }

  public static Map<String, Uri> getDataResultsFromIntent(Intent paramIntent, String paramString)
  {
    if (Build.VERSION.SDK_INT >= 26)
      return android.app.RemoteInput.getDataResultsFromIntent(paramIntent, paramString);
    if (Build.VERSION.SDK_INT >= 16)
    {
      Intent localIntent = getClipDataIntentFromIntent(paramIntent);
      if (localIntent == null)
        return null;
      HashMap localHashMap = new HashMap();
      Iterator localIterator = localIntent.getExtras().keySet().iterator();
      while (localIterator.hasNext())
      {
        String str1 = (String)localIterator.next();
        if (str1.startsWith("android.remoteinput.dataTypeResultsData"))
        {
          String str2 = str1.substring("android.remoteinput.dataTypeResultsData".length());
          if (!str2.isEmpty())
          {
            String str3 = localIntent.getBundleExtra(str1).getString(paramString);
            if ((str3 != null) && (!str3.isEmpty()))
              localHashMap.put(str2, Uri.parse(str3));
          }
        }
      }
      if (localHashMap.isEmpty())
        localHashMap = null;
      return localHashMap;
    }
    Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
    return null;
  }

  private static String getExtraResultsKeyForData(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("android.remoteinput.dataTypeResultsData");
    localStringBuilder.append(paramString);
    return localStringBuilder.toString();
  }

  public static Bundle getResultsFromIntent(Intent paramIntent)
  {
    if (Build.VERSION.SDK_INT >= 20)
      return android.app.RemoteInput.getResultsFromIntent(paramIntent);
    if (Build.VERSION.SDK_INT >= 16)
    {
      Intent localIntent = getClipDataIntentFromIntent(paramIntent);
      if (localIntent == null)
        return null;
      return (Bundle)localIntent.getExtras().getParcelable("android.remoteinput.resultsData");
    }
    Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
    return null;
  }

  public boolean getAllowFreeFormInput()
  {
    return this.mAllowFreeFormTextInput;
  }

  public Set<String> getAllowedDataTypes()
  {
    return this.mAllowedDataTypes;
  }

  public CharSequence[] getChoices()
  {
    return this.mChoices;
  }

  public Bundle getExtras()
  {
    return this.mExtras;
  }

  public CharSequence getLabel()
  {
    return this.mLabel;
  }

  public String getResultKey()
  {
    return this.mResultKey;
  }

  public boolean isDataOnly()
  {
    boolean bool;
    if ((!getAllowFreeFormInput()) && ((getChoices() == null) || (getChoices().length == 0)) && (getAllowedDataTypes() != null) && (!getAllowedDataTypes().isEmpty()))
      bool = true;
    else
      bool = false;
    return bool;
  }

  public static final class Builder
  {
    private boolean mAllowFreeFormTextInput = true;
    private final Set<String> mAllowedDataTypes = new HashSet();
    private CharSequence[] mChoices;
    private final Bundle mExtras = new Bundle();
    private CharSequence mLabel;
    private final String mResultKey;

    public Builder(@NonNull String paramString)
    {
      if (paramString == null)
        throw new IllegalArgumentException("Result key can't be null");
      this.mResultKey = paramString;
    }

    @NonNull
    public Builder addExtras(@NonNull Bundle paramBundle)
    {
      if (paramBundle != null)
        this.mExtras.putAll(paramBundle);
      return this;
    }

    @NonNull
    public RemoteInput build()
    {
      RemoteInput localRemoteInput = new RemoteInput(this.mResultKey, this.mLabel, this.mChoices, this.mAllowFreeFormTextInput, this.mExtras, this.mAllowedDataTypes);
      return localRemoteInput;
    }

    @NonNull
    public Bundle getExtras()
    {
      return this.mExtras;
    }

    @NonNull
    public Builder setAllowDataType(@NonNull String paramString, boolean paramBoolean)
    {
      if (paramBoolean)
        this.mAllowedDataTypes.add(paramString);
      else
        this.mAllowedDataTypes.remove(paramString);
      return this;
    }

    @NonNull
    public Builder setAllowFreeFormInput(boolean paramBoolean)
    {
      this.mAllowFreeFormTextInput = paramBoolean;
      return this;
    }

    @NonNull
    public Builder setChoices(@Nullable CharSequence[] paramArrayOfCharSequence)
    {
      this.mChoices = paramArrayOfCharSequence;
      return this;
    }

    @NonNull
    public Builder setLabel(@Nullable CharSequence paramCharSequence)
    {
      this.mLabel = paramCharSequence;
      return this;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.RemoteInput
 * JD-Core Version:    0.6.1
 */