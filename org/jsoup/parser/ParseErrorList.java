package org.jsoup.parser;

import java.util.ArrayList;

class ParseErrorList extends ArrayList<ParseError>
{
  private static final int INITIAL_CAPACITY = 16;
  private final int maxSize;

  ParseErrorList(int paramInt1, int paramInt2)
  {
    super(paramInt1);
    this.maxSize = paramInt2;
  }

  static ParseErrorList noTracking()
  {
    return new ParseErrorList(0, 0);
  }

  static ParseErrorList tracking(int paramInt)
  {
    return new ParseErrorList(16, paramInt);
  }

  boolean canAddError()
  {
    boolean bool;
    if (size() < this.maxSize)
      bool = true;
    else
      bool = false;
    return bool;
  }

  int getMaxSize()
  {
    return this.maxSize;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.parser.ParseErrorList
 * JD-Core Version:    0.6.1
 */