package android.support.v4.app;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.drawable.IconCompat;

public class Person
{
  private static final String ICON_KEY = "icon";
  private static final String IS_BOT_KEY = "isBot";
  private static final String IS_IMPORTANT_KEY = "isImportant";
  private static final String KEY_KEY = "key";
  private static final String NAME_KEY = "name";
  private static final String URI_KEY = "uri";

  @Nullable
  IconCompat mIcon;
  boolean mIsBot;
  boolean mIsImportant;

  @Nullable
  String mKey;

  @Nullable
  CharSequence mName;

  @Nullable
  String mUri;

  Person(Builder paramBuilder)
  {
    this.mName = paramBuilder.mName;
    this.mIcon = paramBuilder.mIcon;
    this.mUri = paramBuilder.mUri;
    this.mKey = paramBuilder.mKey;
    this.mIsBot = paramBuilder.mIsBot;
    this.mIsImportant = paramBuilder.mIsImportant;
  }

  @NonNull
  @RequiresApi(28)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static Person fromAndroidPerson(@NonNull android.app.Person paramPerson)
  {
    Builder localBuilder = new Builder().setName(paramPerson.getName());
    IconCompat localIconCompat;
    if (paramPerson.getIcon() != null)
      localIconCompat = IconCompat.createFromIcon(paramPerson.getIcon());
    else
      localIconCompat = null;
    return localBuilder.setIcon(localIconCompat).setUri(paramPerson.getUri()).setKey(paramPerson.getKey()).setBot(paramPerson.isBot()).setImportant(paramPerson.isImportant()).build();
  }

  @NonNull
  public static Person fromBundle(@NonNull Bundle paramBundle)
  {
    Bundle localBundle = paramBundle.getBundle("icon");
    Builder localBuilder = new Builder().setName(paramBundle.getCharSequence("name"));
    IconCompat localIconCompat;
    if (localBundle != null)
      localIconCompat = IconCompat.createFromBundle(localBundle);
    else
      localIconCompat = null;
    return localBuilder.setIcon(localIconCompat).setUri(paramBundle.getString("uri")).setKey(paramBundle.getString("key")).setBot(paramBundle.getBoolean("isBot")).setImportant(paramBundle.getBoolean("isImportant")).build();
  }

  @Nullable
  public IconCompat getIcon()
  {
    return this.mIcon;
  }

  @Nullable
  public String getKey()
  {
    return this.mKey;
  }

  @Nullable
  public CharSequence getName()
  {
    return this.mName;
  }

  @Nullable
  public String getUri()
  {
    return this.mUri;
  }

  public boolean isBot()
  {
    return this.mIsBot;
  }

  public boolean isImportant()
  {
    return this.mIsImportant;
  }

  @NonNull
  @RequiresApi(28)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public android.app.Person toAndroidPerson()
  {
    android.app.Person.Builder localBuilder = new android.app.Person.Builder().setName(getName());
    Icon localIcon;
    if (getIcon() != null)
      localIcon = getIcon().toIcon();
    else
      localIcon = null;
    return localBuilder.setIcon(localIcon).setUri(getUri()).setKey(getKey()).setBot(isBot()).setImportant(isImportant()).build();
  }

  @NonNull
  public Builder toBuilder()
  {
    return new Builder(this);
  }

  @NonNull
  public Bundle toBundle()
  {
    Bundle localBundle1 = new Bundle();
    localBundle1.putCharSequence("name", this.mName);
    Bundle localBundle2;
    if (this.mIcon != null)
      localBundle2 = this.mIcon.toBundle();
    else
      localBundle2 = null;
    localBundle1.putBundle("icon", localBundle2);
    localBundle1.putString("uri", this.mUri);
    localBundle1.putString("key", this.mKey);
    localBundle1.putBoolean("isBot", this.mIsBot);
    localBundle1.putBoolean("isImportant", this.mIsImportant);
    return localBundle1;
  }

  public static class Builder
  {

    @Nullable
    IconCompat mIcon;
    boolean mIsBot;
    boolean mIsImportant;

    @Nullable
    String mKey;

    @Nullable
    CharSequence mName;

    @Nullable
    String mUri;

    public Builder()
    {
    }

    Builder(Person paramPerson)
    {
      this.mName = paramPerson.mName;
      this.mIcon = paramPerson.mIcon;
      this.mUri = paramPerson.mUri;
      this.mKey = paramPerson.mKey;
      this.mIsBot = paramPerson.mIsBot;
      this.mIsImportant = paramPerson.mIsImportant;
    }

    @NonNull
    public Person build()
    {
      return new Person(this);
    }

    @NonNull
    public Builder setBot(boolean paramBoolean)
    {
      this.mIsBot = paramBoolean;
      return this;
    }

    @NonNull
    public Builder setIcon(@Nullable IconCompat paramIconCompat)
    {
      this.mIcon = paramIconCompat;
      return this;
    }

    @NonNull
    public Builder setImportant(boolean paramBoolean)
    {
      this.mIsImportant = paramBoolean;
      return this;
    }

    @NonNull
    public Builder setKey(@Nullable String paramString)
    {
      this.mKey = paramString;
      return this;
    }

    @NonNull
    public Builder setName(@Nullable CharSequence paramCharSequence)
    {
      this.mName = paramCharSequence;
      return this;
    }

    @NonNull
    public Builder setUri(@Nullable String paramString)
    {
      this.mUri = paramString;
      return this;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.Person
 * JD-Core Version:    0.6.1
 */