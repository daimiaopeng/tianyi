package android.support.v4.text.util;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.util.PatternsCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.text.util.Linkify.MatchFilter;
import android.text.util.Linkify.TransformFilter;
import android.webkit.WebView;
import android.widget.TextView;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LinkifyCompat
{
  private static final Comparator<LinkSpec> COMPARATOR = new Comparator()
  {
    public int compare(LinkifyCompat.LinkSpec paramAnonymousLinkSpec1, LinkifyCompat.LinkSpec paramAnonymousLinkSpec2)
    {
      if (paramAnonymousLinkSpec1.start < paramAnonymousLinkSpec2.start)
        return -1;
      if (paramAnonymousLinkSpec1.start > paramAnonymousLinkSpec2.start)
        return 1;
      if (paramAnonymousLinkSpec1.end < paramAnonymousLinkSpec2.end)
        return 1;
      if (paramAnonymousLinkSpec1.end > paramAnonymousLinkSpec2.end)
        return -1;
      return 0;
    }
  };
  private static final String[] EMPTY_STRING = new String[0];

  private static void addLinkMovementMethod(@NonNull TextView paramTextView)
  {
    MovementMethod localMovementMethod = paramTextView.getMovementMethod();
    if (((localMovementMethod == null) || (!(localMovementMethod instanceof LinkMovementMethod))) && (paramTextView.getLinksClickable()))
      paramTextView.setMovementMethod(LinkMovementMethod.getInstance());
  }

  public static void addLinks(@NonNull TextView paramTextView, @NonNull Pattern paramPattern, @Nullable String paramString)
  {
    if (shouldAddLinksFallbackToFramework())
    {
      Linkify.addLinks(paramTextView, paramPattern, paramString);
      return;
    }
    addLinks(paramTextView, paramPattern, paramString, null, null, null);
  }

  public static void addLinks(@NonNull TextView paramTextView, @NonNull Pattern paramPattern, @Nullable String paramString, @Nullable Linkify.MatchFilter paramMatchFilter, @Nullable Linkify.TransformFilter paramTransformFilter)
  {
    if (shouldAddLinksFallbackToFramework())
    {
      Linkify.addLinks(paramTextView, paramPattern, paramString, paramMatchFilter, paramTransformFilter);
      return;
    }
    addLinks(paramTextView, paramPattern, paramString, null, paramMatchFilter, paramTransformFilter);
  }

  @SuppressLint({"NewApi"})
  public static void addLinks(@NonNull TextView paramTextView, @NonNull Pattern paramPattern, @Nullable String paramString, @Nullable String[] paramArrayOfString, @Nullable Linkify.MatchFilter paramMatchFilter, @Nullable Linkify.TransformFilter paramTransformFilter)
  {
    if (shouldAddLinksFallbackToFramework())
    {
      Linkify.addLinks(paramTextView, paramPattern, paramString, paramArrayOfString, paramMatchFilter, paramTransformFilter);
      return;
    }
    SpannableString localSpannableString = SpannableString.valueOf(paramTextView.getText());
    if (addLinks(localSpannableString, paramPattern, paramString, paramArrayOfString, paramMatchFilter, paramTransformFilter))
    {
      paramTextView.setText(localSpannableString);
      addLinkMovementMethod(paramTextView);
    }
  }

  public static boolean addLinks(@NonNull Spannable paramSpannable, int paramInt)
  {
    if (shouldAddLinksFallbackToFramework())
      return Linkify.addLinks(paramSpannable, paramInt);
    if (paramInt == 0)
      return false;
    URLSpan[] arrayOfURLSpan = (URLSpan[])paramSpannable.getSpans(0, paramSpannable.length(), URLSpan.class);
    for (int i = arrayOfURLSpan.length - 1; i >= 0; i--)
      paramSpannable.removeSpan(arrayOfURLSpan[i]);
    if ((paramInt & 0x4) != 0)
      Linkify.addLinks(paramSpannable, 4);
    ArrayList localArrayList = new ArrayList();
    if ((paramInt & 0x1) != 0)
      gatherLinks(localArrayList, paramSpannable, PatternsCompat.AUTOLINK_WEB_URL, new String[] { "http://", "https://", "rtsp://" }, Linkify.sUrlMatchFilter, null);
    if ((paramInt & 0x2) != 0)
      gatherLinks(localArrayList, paramSpannable, PatternsCompat.AUTOLINK_EMAIL_ADDRESS, new String[] { "mailto:" }, null, null);
    if ((paramInt & 0x8) != 0)
      gatherMapLinks(localArrayList, paramSpannable);
    pruneOverlaps(localArrayList, paramSpannable);
    if (localArrayList.size() == 0)
      return false;
    Iterator localIterator = localArrayList.iterator();
    while (localIterator.hasNext())
    {
      LinkSpec localLinkSpec = (LinkSpec)localIterator.next();
      if (localLinkSpec.frameworkAddedSpan == null)
        applyLink(localLinkSpec.url, localLinkSpec.start, localLinkSpec.end, paramSpannable);
    }
    return true;
  }

  public static boolean addLinks(@NonNull Spannable paramSpannable, @NonNull Pattern paramPattern, @Nullable String paramString)
  {
    if (shouldAddLinksFallbackToFramework())
      return Linkify.addLinks(paramSpannable, paramPattern, paramString);
    return addLinks(paramSpannable, paramPattern, paramString, null, null, null);
  }

  public static boolean addLinks(@NonNull Spannable paramSpannable, @NonNull Pattern paramPattern, @Nullable String paramString, @Nullable Linkify.MatchFilter paramMatchFilter, @Nullable Linkify.TransformFilter paramTransformFilter)
  {
    if (shouldAddLinksFallbackToFramework())
      return Linkify.addLinks(paramSpannable, paramPattern, paramString, paramMatchFilter, paramTransformFilter);
    return addLinks(paramSpannable, paramPattern, paramString, null, paramMatchFilter, paramTransformFilter);
  }

  @SuppressLint({"NewApi"})
  public static boolean addLinks(@NonNull Spannable paramSpannable, @NonNull Pattern paramPattern, @Nullable String paramString, @Nullable String[] paramArrayOfString, @Nullable Linkify.MatchFilter paramMatchFilter, @Nullable Linkify.TransformFilter paramTransformFilter)
  {
    if (shouldAddLinksFallbackToFramework())
      return Linkify.addLinks(paramSpannable, paramPattern, paramString, paramArrayOfString, paramMatchFilter, paramTransformFilter);
    if (paramString == null)
      paramString = "";
    if ((paramArrayOfString == null) || (paramArrayOfString.length < 1))
      paramArrayOfString = EMPTY_STRING;
    String[] arrayOfString = new String[1 + paramArrayOfString.length];
    arrayOfString[0] = paramString.toLowerCase(Locale.ROOT);
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      String str1 = paramArrayOfString[i];
      i++;
      String str2;
      if (str1 == null)
        str2 = "";
      else
        str2 = str1.toLowerCase(Locale.ROOT);
      arrayOfString[i] = str2;
    }
    Matcher localMatcher = paramPattern.matcher(paramSpannable);
    boolean bool1 = false;
    while (localMatcher.find())
    {
      int j = localMatcher.start();
      int k = localMatcher.end();
      boolean bool2;
      if (paramMatchFilter != null)
        bool2 = paramMatchFilter.acceptMatch(paramSpannable, j, k);
      else
        bool2 = true;
      if (bool2)
      {
        applyLink(makeUrl(localMatcher.group(0), arrayOfString, localMatcher, paramTransformFilter), j, k, paramSpannable);
        bool1 = true;
      }
    }
    return bool1;
  }

  public static boolean addLinks(@NonNull TextView paramTextView, int paramInt)
  {
    if (shouldAddLinksFallbackToFramework())
      return Linkify.addLinks(paramTextView, paramInt);
    if (paramInt == 0)
      return false;
    CharSequence localCharSequence = paramTextView.getText();
    if ((localCharSequence instanceof Spannable))
    {
      if (addLinks((Spannable)localCharSequence, paramInt))
      {
        addLinkMovementMethod(paramTextView);
        return true;
      }
      return false;
    }
    SpannableString localSpannableString = SpannableString.valueOf(localCharSequence);
    if (addLinks(localSpannableString, paramInt))
    {
      addLinkMovementMethod(paramTextView);
      paramTextView.setText(localSpannableString);
      return true;
    }
    return false;
  }

  private static void applyLink(String paramString, int paramInt1, int paramInt2, Spannable paramSpannable)
  {
    paramSpannable.setSpan(new URLSpan(paramString), paramInt1, paramInt2, 33);
  }

  private static String findAddress(String paramString)
  {
    if (Build.VERSION.SDK_INT >= 28)
      return WebView.findAddress(paramString);
    return FindAddress.findAddress(paramString);
  }

  private static void gatherLinks(ArrayList<LinkSpec> paramArrayList, Spannable paramSpannable, Pattern paramPattern, String[] paramArrayOfString, Linkify.MatchFilter paramMatchFilter, Linkify.TransformFilter paramTransformFilter)
  {
    Matcher localMatcher = paramPattern.matcher(paramSpannable);
    while (localMatcher.find())
    {
      int i = localMatcher.start();
      int j = localMatcher.end();
      if ((paramMatchFilter == null) || (paramMatchFilter.acceptMatch(paramSpannable, i, j)))
      {
        LinkSpec localLinkSpec = new LinkSpec();
        localLinkSpec.url = makeUrl(localMatcher.group(0), paramArrayOfString, localMatcher, paramTransformFilter);
        localLinkSpec.start = i;
        localLinkSpec.end = j;
        paramArrayList.add(localLinkSpec);
      }
    }
  }

  // ERROR //
  private static void gatherMapLinks(ArrayList<LinkSpec> paramArrayList, Spannable paramSpannable)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 263	java/lang/Object:toString	()Ljava/lang/String;
    //   4: astore_2
    //   5: iconst_0
    //   6: istore_3
    //   7: goto +4 -> 11
    //   10: pop
    //   11: aload_2
    //   12: invokestatic 264	android/support/v4/text/util/LinkifyCompat:findAddress	(Ljava/lang/String;)Ljava/lang/String;
    //   15: astore 4
    //   17: aload 4
    //   19: ifnull +121 -> 140
    //   22: aload_2
    //   23: aload 4
    //   25: invokevirtual 268	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   28: istore 5
    //   30: iload 5
    //   32: ifge +6 -> 38
    //   35: goto +105 -> 140
    //   38: new 158	android/support/v4/text/util/LinkifyCompat$LinkSpec
    //   41: dup
    //   42: invokespecial 251	android/support/v4/text/util/LinkifyCompat$LinkSpec:<init>	()V
    //   45: astore 6
    //   47: iload 5
    //   49: aload 4
    //   51: invokevirtual 269	java/lang/String:length	()I
    //   54: iadd
    //   55: istore 7
    //   57: aload 6
    //   59: iload 5
    //   61: iload_3
    //   62: iadd
    //   63: putfield 170	android/support/v4/text/util/LinkifyCompat$LinkSpec:start	I
    //   66: iload_3
    //   67: iload 7
    //   69: iadd
    //   70: istore_3
    //   71: aload 6
    //   73: iload_3
    //   74: putfield 173	android/support/v4/text/util/LinkifyCompat$LinkSpec:end	I
    //   77: aload_2
    //   78: iload 7
    //   80: invokevirtual 272	java/lang/String:substring	(I)Ljava/lang/String;
    //   83: astore_2
    //   84: aload 4
    //   86: ldc_w 274
    //   89: invokestatic 280	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   92: astore 8
    //   94: new 282	java/lang/StringBuilder
    //   97: dup
    //   98: invokespecial 283	java/lang/StringBuilder:<init>	()V
    //   101: astore 9
    //   103: aload 9
    //   105: ldc_w 285
    //   108: invokevirtual 289	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: pop
    //   112: aload 9
    //   114: aload 8
    //   116: invokevirtual 289	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: pop
    //   120: aload 6
    //   122: aload 9
    //   124: invokevirtual 290	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   127: putfield 166	android/support/v4/text/util/LinkifyCompat$LinkSpec:url	Ljava/lang/String;
    //   130: aload_0
    //   131: aload 6
    //   133: invokevirtual 255	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   136: pop
    //   137: goto -127 -> 10
    //   140: return
    //   141: return
    //
    // Exception table:
    //   from	to	target	type
    //   84	94	10	java/io/UnsupportedEncodingException
    //   10	84	141	java/lang/UnsupportedOperationException
    //   84	94	141	java/lang/UnsupportedOperationException
    //   94	137	141	java/lang/UnsupportedOperationException
  }

  private static String makeUrl(@NonNull String paramString, @NonNull String[] paramArrayOfString, Matcher paramMatcher, @Nullable Linkify.TransformFilter paramTransformFilter)
  {
    if (paramTransformFilter != null)
      paramString = paramTransformFilter.transformUrl(paramMatcher, paramString);
    for (int i = 0; ; i++)
    {
      int j = paramArrayOfString.length;
      k = 1;
      if (i >= j)
        break;
      String str1 = paramArrayOfString[i];
      int m = paramArrayOfString[i].length();
      if (paramString.regionMatches(true, 0, str1, 0, m))
      {
        String str2 = paramArrayOfString[i];
        int n = paramArrayOfString[i].length();
        if (paramString.regionMatches(false, 0, str2, 0, n))
          break label142;
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append(paramArrayOfString[i]);
        localStringBuilder2.append(paramString.substring(paramArrayOfString[i].length()));
        paramString = localStringBuilder2.toString();
        break label142;
      }
    }
    int k = 0;
    label142: if ((k == 0) && (paramArrayOfString.length > 0))
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append(paramArrayOfString[0]);
      localStringBuilder1.append(paramString);
      paramString = localStringBuilder1.toString();
    }
    return paramString;
  }

  private static void pruneOverlaps(ArrayList<LinkSpec> paramArrayList, Spannable paramSpannable)
  {
    int i = paramSpannable.length();
    int j = 0;
    URLSpan[] arrayOfURLSpan = (URLSpan[])paramSpannable.getSpans(0, i, URLSpan.class);
    for (int k = 0; k < arrayOfURLSpan.length; k++)
    {
      LinkSpec localLinkSpec1 = new LinkSpec();
      localLinkSpec1.frameworkAddedSpan = arrayOfURLSpan[k];
      localLinkSpec1.start = paramSpannable.getSpanStart(arrayOfURLSpan[k]);
      localLinkSpec1.end = paramSpannable.getSpanEnd(arrayOfURLSpan[k]);
      paramArrayList.add(localLinkSpec1);
    }
    Collections.sort(paramArrayList, COMPARATOR);
    int m = paramArrayList.size();
    while (j < m - 1)
    {
      LinkSpec localLinkSpec2 = (LinkSpec)paramArrayList.get(j);
      int n = j + 1;
      LinkSpec localLinkSpec3 = (LinkSpec)paramArrayList.get(n);
      if ((localLinkSpec2.start <= localLinkSpec3.start) && (localLinkSpec2.end > localLinkSpec3.start))
      {
        if (localLinkSpec3.end <= localLinkSpec2.end);
        int i1;
        while (localLinkSpec2.end - localLinkSpec2.start > localLinkSpec3.end - localLinkSpec3.start)
        {
          i1 = n;
          break;
        }
        if (localLinkSpec2.end - localLinkSpec2.start < localLinkSpec3.end - localLinkSpec3.start)
          i1 = j;
        else
          i1 = -1;
        if (i1 != -1)
        {
          URLSpan localURLSpan = ((LinkSpec)paramArrayList.get(i1)).frameworkAddedSpan;
          if (localURLSpan != null)
            paramSpannable.removeSpan(localURLSpan);
          paramArrayList.remove(i1);
          m--;
        }
      }
      else
      {
        j = n;
      }
    }
  }

  private static boolean shouldAddLinksFallbackToFramework()
  {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 28)
      bool = true;
    else
      bool = false;
    return bool;
  }

  private static class LinkSpec
  {
    int end;
    URLSpan frameworkAddedSpan;
    int start;
    String url;
  }

  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface LinkifyMask
  {
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.text.util.LinkifyCompat
 * JD-Core Version:    0.6.1
 */