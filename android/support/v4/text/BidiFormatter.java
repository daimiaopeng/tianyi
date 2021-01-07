package android.support.v4.text;

import android.text.SpannableStringBuilder;
import java.util.Locale;

public final class BidiFormatter
{
  private static final int DEFAULT_FLAGS = 2;
  static final BidiFormatter DEFAULT_LTR_INSTANCE = new BidiFormatter(false, 2, DEFAULT_TEXT_DIRECTION_HEURISTIC);
  static final BidiFormatter DEFAULT_RTL_INSTANCE = new BidiFormatter(true, 2, DEFAULT_TEXT_DIRECTION_HEURISTIC);
  static final TextDirectionHeuristicCompat DEFAULT_TEXT_DIRECTION_HEURISTIC = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
  private static final int DIR_LTR = -1;
  private static final int DIR_RTL = 1;
  private static final int DIR_UNKNOWN = 0;
  private static final String EMPTY_STRING = "";
  private static final int FLAG_STEREO_RESET = 2;
  private static final char LRE = '‪';
  private static final char LRM = '‎';
  private static final String LRM_STRING = Character.toString('‎');
  private static final char PDF = '‬';
  private static final char RLE = '‫';
  private static final char RLM = '‏';
  private static final String RLM_STRING = Character.toString('‏');
  private final TextDirectionHeuristicCompat mDefaultTextDirectionHeuristicCompat;
  private final int mFlags;
  private final boolean mIsRtlContext;

  BidiFormatter(boolean paramBoolean, int paramInt, TextDirectionHeuristicCompat paramTextDirectionHeuristicCompat)
  {
    this.mIsRtlContext = paramBoolean;
    this.mFlags = paramInt;
    this.mDefaultTextDirectionHeuristicCompat = paramTextDirectionHeuristicCompat;
  }

  private static int getEntryDir(CharSequence paramCharSequence)
  {
    return new DirectionalityEstimator(paramCharSequence, false).getEntryDir();
  }

  private static int getExitDir(CharSequence paramCharSequence)
  {
    return new DirectionalityEstimator(paramCharSequence, false).getExitDir();
  }

  public static BidiFormatter getInstance()
  {
    return new Builder().build();
  }

  public static BidiFormatter getInstance(Locale paramLocale)
  {
    return new Builder(paramLocale).build();
  }

  public static BidiFormatter getInstance(boolean paramBoolean)
  {
    return new Builder(paramBoolean).build();
  }

  static boolean isRtlLocale(Locale paramLocale)
  {
    int i = TextUtilsCompat.getLayoutDirectionFromLocale(paramLocale);
    int j = 1;
    if (i != j)
      j = 0;
    return j;
  }

  private String markAfter(CharSequence paramCharSequence, TextDirectionHeuristicCompat paramTextDirectionHeuristicCompat)
  {
    boolean bool = paramTextDirectionHeuristicCompat.isRtl(paramCharSequence, 0, paramCharSequence.length());
    if ((!this.mIsRtlContext) && ((bool) || (getExitDir(paramCharSequence) == 1)))
      return LRM_STRING;
    if ((this.mIsRtlContext) && ((!bool) || (getExitDir(paramCharSequence) == -1)))
      return RLM_STRING;
    return "";
  }

  private String markBefore(CharSequence paramCharSequence, TextDirectionHeuristicCompat paramTextDirectionHeuristicCompat)
  {
    boolean bool = paramTextDirectionHeuristicCompat.isRtl(paramCharSequence, 0, paramCharSequence.length());
    if ((!this.mIsRtlContext) && ((bool) || (getEntryDir(paramCharSequence) == 1)))
      return LRM_STRING;
    if ((this.mIsRtlContext) && ((!bool) || (getEntryDir(paramCharSequence) == -1)))
      return RLM_STRING;
    return "";
  }

  public boolean getStereoReset()
  {
    boolean bool;
    if ((0x2 & this.mFlags) != 0)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public boolean isRtl(CharSequence paramCharSequence)
  {
    return this.mDefaultTextDirectionHeuristicCompat.isRtl(paramCharSequence, 0, paramCharSequence.length());
  }

  public boolean isRtl(String paramString)
  {
    return isRtl(paramString);
  }

  public boolean isRtlContext()
  {
    return this.mIsRtlContext;
  }

  public CharSequence unicodeWrap(CharSequence paramCharSequence)
  {
    return unicodeWrap(paramCharSequence, this.mDefaultTextDirectionHeuristicCompat, true);
  }

  public CharSequence unicodeWrap(CharSequence paramCharSequence, TextDirectionHeuristicCompat paramTextDirectionHeuristicCompat)
  {
    return unicodeWrap(paramCharSequence, paramTextDirectionHeuristicCompat, true);
  }

  public CharSequence unicodeWrap(CharSequence paramCharSequence, TextDirectionHeuristicCompat paramTextDirectionHeuristicCompat, boolean paramBoolean)
  {
    if (paramCharSequence == null)
      return null;
    boolean bool = paramTextDirectionHeuristicCompat.isRtl(paramCharSequence, 0, paramCharSequence.length());
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder();
    if ((getStereoReset()) && (paramBoolean))
    {
      TextDirectionHeuristicCompat localTextDirectionHeuristicCompat2;
      if (bool)
        localTextDirectionHeuristicCompat2 = TextDirectionHeuristicsCompat.RTL;
      else
        localTextDirectionHeuristicCompat2 = TextDirectionHeuristicsCompat.LTR;
      localSpannableStringBuilder.append(markBefore(paramCharSequence, localTextDirectionHeuristicCompat2));
    }
    if (bool != this.mIsRtlContext)
    {
      int i;
      if (bool)
        i = 8235;
      else
        i = 8234;
      localSpannableStringBuilder.append(i);
      localSpannableStringBuilder.append(paramCharSequence);
      localSpannableStringBuilder.append('‬');
    }
    else
    {
      localSpannableStringBuilder.append(paramCharSequence);
    }
    if (paramBoolean)
    {
      TextDirectionHeuristicCompat localTextDirectionHeuristicCompat1;
      if (bool)
        localTextDirectionHeuristicCompat1 = TextDirectionHeuristicsCompat.RTL;
      else
        localTextDirectionHeuristicCompat1 = TextDirectionHeuristicsCompat.LTR;
      localSpannableStringBuilder.append(markAfter(paramCharSequence, localTextDirectionHeuristicCompat1));
    }
    return localSpannableStringBuilder;
  }

  public CharSequence unicodeWrap(CharSequence paramCharSequence, boolean paramBoolean)
  {
    return unicodeWrap(paramCharSequence, this.mDefaultTextDirectionHeuristicCompat, paramBoolean);
  }

  public String unicodeWrap(String paramString)
  {
    return unicodeWrap(paramString, this.mDefaultTextDirectionHeuristicCompat, true);
  }

  public String unicodeWrap(String paramString, TextDirectionHeuristicCompat paramTextDirectionHeuristicCompat)
  {
    return unicodeWrap(paramString, paramTextDirectionHeuristicCompat, true);
  }

  public String unicodeWrap(String paramString, TextDirectionHeuristicCompat paramTextDirectionHeuristicCompat, boolean paramBoolean)
  {
    if (paramString == null)
      return null;
    return unicodeWrap(paramString, paramTextDirectionHeuristicCompat, paramBoolean).toString();
  }

  public String unicodeWrap(String paramString, boolean paramBoolean)
  {
    return unicodeWrap(paramString, this.mDefaultTextDirectionHeuristicCompat, paramBoolean);
  }

  public static final class Builder
  {
    private int mFlags;
    private boolean mIsRtlContext;
    private TextDirectionHeuristicCompat mTextDirectionHeuristicCompat;

    public Builder()
    {
      initialize(BidiFormatter.isRtlLocale(Locale.getDefault()));
    }

    public Builder(Locale paramLocale)
    {
      initialize(BidiFormatter.isRtlLocale(paramLocale));
    }

    public Builder(boolean paramBoolean)
    {
      initialize(paramBoolean);
    }

    private static BidiFormatter getDefaultInstanceFromContext(boolean paramBoolean)
    {
      BidiFormatter localBidiFormatter;
      if (paramBoolean)
        localBidiFormatter = BidiFormatter.DEFAULT_RTL_INSTANCE;
      else
        localBidiFormatter = BidiFormatter.DEFAULT_LTR_INSTANCE;
      return localBidiFormatter;
    }

    private void initialize(boolean paramBoolean)
    {
      this.mIsRtlContext = paramBoolean;
      this.mTextDirectionHeuristicCompat = BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC;
      this.mFlags = 2;
    }

    public BidiFormatter build()
    {
      if ((this.mFlags == 2) && (this.mTextDirectionHeuristicCompat == BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC))
        return getDefaultInstanceFromContext(this.mIsRtlContext);
      return new BidiFormatter(this.mIsRtlContext, this.mFlags, this.mTextDirectionHeuristicCompat);
    }

    public Builder setTextDirectionHeuristic(TextDirectionHeuristicCompat paramTextDirectionHeuristicCompat)
    {
      this.mTextDirectionHeuristicCompat = paramTextDirectionHeuristicCompat;
      return this;
    }

    public Builder stereoReset(boolean paramBoolean)
    {
      if (paramBoolean)
        this.mFlags = (0x2 | this.mFlags);
      else
        this.mFlags = (0xFFFFFFFD & this.mFlags);
      return this;
    }
  }

  private static class DirectionalityEstimator
  {
    private static final byte[] DIR_TYPE_CACHE = new byte[1792];
    private static final int DIR_TYPE_CACHE_SIZE = 1792;
    private int charIndex;
    private final boolean isHtml;
    private char lastChar;
    private final int length;
    private final CharSequence text;

    static
    {
      for (int i = 0; i < 1792; i++)
        DIR_TYPE_CACHE[i] = Character.getDirectionality(i);
    }

    DirectionalityEstimator(CharSequence paramCharSequence, boolean paramBoolean)
    {
      this.text = paramCharSequence;
      this.isHtml = paramBoolean;
      this.length = paramCharSequence.length();
    }

    private static byte getCachedDirectionality(char paramChar)
    {
      byte b;
      if (paramChar < '܀')
        b = DIR_TYPE_CACHE[paramChar];
      else
        b = Character.getDirectionality(paramChar);
      return b;
    }

    private byte skipEntityBackward()
    {
      int i = this.charIndex;
      do
      {
        if (this.charIndex <= 0)
          break;
        CharSequence localCharSequence = this.text;
        int j = -1 + this.charIndex;
        this.charIndex = j;
        this.lastChar = localCharSequence.charAt(j);
        if (this.lastChar == '&')
          return 12;
      }
      while (this.lastChar != ';');
      this.charIndex = i;
      this.lastChar = ';';
      return 13;
    }

    private byte skipEntityForward()
    {
      char c;
      do
      {
        if (this.charIndex >= this.length)
          break;
        CharSequence localCharSequence = this.text;
        int i = this.charIndex;
        this.charIndex = (i + 1);
        c = localCharSequence.charAt(i);
        this.lastChar = c;
      }
      while (c != ';');
      return 12;
    }

    private byte skipTagBackward()
    {
      int i = this.charIndex;
      label140: 
      while (this.charIndex > 0)
      {
        CharSequence localCharSequence1 = this.text;
        int j = -1 + this.charIndex;
        this.charIndex = j;
        this.lastChar = localCharSequence1.charAt(j);
        if (this.lastChar == '<')
          return 12;
        if (this.lastChar == '>')
          break;
        if ((this.lastChar == '"') || (this.lastChar == '\''))
        {
          int k = this.lastChar;
          while (true)
          {
            if (this.charIndex <= 0)
              break label140;
            CharSequence localCharSequence2 = this.text;
            int m = -1 + this.charIndex;
            this.charIndex = m;
            char c = localCharSequence2.charAt(m);
            this.lastChar = c;
            if (c == k)
              break;
          }
        }
      }
      this.charIndex = i;
      this.lastChar = '>';
      return 13;
    }

    private byte skipTagForward()
    {
      int i = this.charIndex;
      label136: 
      while (this.charIndex < this.length)
      {
        CharSequence localCharSequence1 = this.text;
        int j = this.charIndex;
        this.charIndex = (j + 1);
        this.lastChar = localCharSequence1.charAt(j);
        if (this.lastChar == '>')
          return 12;
        if ((this.lastChar == '"') || (this.lastChar == '\''))
        {
          int k = this.lastChar;
          while (true)
          {
            if (this.charIndex >= this.length)
              break label136;
            CharSequence localCharSequence2 = this.text;
            int m = this.charIndex;
            this.charIndex = (m + 1);
            char c = localCharSequence2.charAt(m);
            this.lastChar = c;
            if (c == k)
              break;
          }
        }
      }
      this.charIndex = i;
      this.lastChar = '<';
      return 13;
    }

    byte dirTypeBackward()
    {
      this.lastChar = this.text.charAt(-1 + this.charIndex);
      if (Character.isLowSurrogate(this.lastChar))
      {
        int i = Character.codePointBefore(this.text, this.charIndex);
        this.charIndex -= Character.charCount(i);
        return Character.getDirectionality(i);
      }
      this.charIndex = (-1 + this.charIndex);
      byte b = getCachedDirectionality(this.lastChar);
      if (this.isHtml)
        if (this.lastChar == '>')
          b = skipTagBackward();
        else if (this.lastChar == ';')
          b = skipEntityBackward();
      return b;
    }

    byte dirTypeForward()
    {
      this.lastChar = this.text.charAt(this.charIndex);
      if (Character.isHighSurrogate(this.lastChar))
      {
        int i = Character.codePointAt(this.text, this.charIndex);
        this.charIndex += Character.charCount(i);
        return Character.getDirectionality(i);
      }
      this.charIndex = (1 + this.charIndex);
      byte b = getCachedDirectionality(this.lastChar);
      if (this.isHtml)
        if (this.lastChar == '<')
          b = skipTagForward();
        else if (this.lastChar == '&')
          b = skipEntityForward();
      return b;
    }

    int getEntryDir()
    {
      this.charIndex = 0;
      int i = 0;
      int j = 0;
      int k = 0;
      while ((this.charIndex < this.length) && (i == 0))
      {
        int m = dirTypeForward();
        if (m != 9)
          switch (m)
          {
          default:
            switch (m)
            {
            default:
              break;
            case 18:
              k--;
              j = 0;
              break;
            case 16:
            case 17:
              k++;
              j = 1;
              break;
            case 14:
            case 15:
              k++;
              j = -1;
            }
            break;
          case 1:
          case 2:
            if (k == 0)
              return 1;
          case 0:
            if (k == 0)
              return -1;
            i = k;
          }
      }
      if (i == 0)
        return 0;
      if (j != 0)
        return j;
      while (this.charIndex > 0)
        switch (dirTypeBackward())
        {
        default:
          break;
        case 18:
          k++;
          break;
        case 16:
        case 17:
          if (i == k)
            return 1;
          k--;
          break;
        case 14:
        case 15:
          if (i == k)
            return -1;
          k--;
        }
      return 0;
    }

    int getExitDir()
    {
      this.charIndex = this.length;
      int i = 0;
      int j = 0;
      while (this.charIndex > 0)
      {
        int k = dirTypeBackward();
        if (k != 9)
          switch (k)
          {
          default:
            switch (k)
            {
            default:
              if (i != 0);
              break;
            case 18:
              j++;
              break;
            case 16:
            case 17:
              if (i == j)
                return 1;
              j--;
              break;
            case 14:
            case 15:
              if (i == j)
                return -1;
              j--;
            }
            break;
          case 1:
          case 2:
            if (j == 0)
              return 1;
            if (i != 0);
            break;
          case 0:
            if (j == 0)
              return -1;
            if (i == 0)
              i = j;
            break;
          }
      }
      return 0;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.text.BidiFormatter
 * JD-Core Version:    0.6.1
 */