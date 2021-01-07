package com.cndatacom.lang;

import java.util.Comparator;
import net.sourceforge.pinyin4j.PinyinHelper;

public class Pinyin
  implements Comparator<String>
{
  private String jionPinyin(String[] paramArrayOfString)
  {
    String str = "";
    StringBuffer localStringBuffer = new StringBuffer();
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0))
      for (int i = 0; ; i++)
      {
        if (i >= paramArrayOfString.length)
        {
          str = localStringBuffer.toString();
          break;
        }
        localStringBuffer.append(paramArrayOfString[i]);
      }
    return str;
  }

  public int compare(String paramString1, String paramString2)
  {
    char[] arrayOfChar1 = paramString1.toCharArray();
    char[] arrayOfChar2 = paramString2.toCharArray();
    String[] arrayOfString1 = PinyinHelper.toHanyuPinyinStringArray(arrayOfChar1[0]);
    String[] arrayOfString2 = PinyinHelper.toHanyuPinyinStringArray(arrayOfChar2[0]);
    return jionPinyin(arrayOfString1).compareTo(jionPinyin(arrayOfString2));
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.lang.Pinyin
 * JD-Core Version:    0.6.1
 */