package com.google.zxing.oned.rss.expanded.decoders;

final class CurrentParsingState
{
  private State encoding = State.NUMERIC;
  private int position = 0;

  int getPosition()
  {
    return this.position;
  }

  void incrementPosition(int paramInt)
  {
    this.position = (paramInt + this.position);
  }

  boolean isAlpha()
  {
    boolean bool;
    if (this.encoding == State.ALPHA)
      bool = true;
    else
      bool = false;
    return bool;
  }

  boolean isIsoIec646()
  {
    boolean bool;
    if (this.encoding == State.ISO_IEC_646)
      bool = true;
    else
      bool = false;
    return bool;
  }

  boolean isNumeric()
  {
    boolean bool;
    if (this.encoding == State.NUMERIC)
      bool = true;
    else
      bool = false;
    return bool;
  }

  void setAlpha()
  {
    this.encoding = State.ALPHA;
  }

  void setIsoIec646()
  {
    this.encoding = State.ISO_IEC_646;
  }

  void setNumeric()
  {
    this.encoding = State.NUMERIC;
  }

  void setPosition(int paramInt)
  {
    this.position = paramInt;
  }

  private static enum State
  {
    static
    {
      ALPHA = new State("ALPHA", 1);
      ISO_IEC_646 = new State("ISO_IEC_646", 2);
      State[] arrayOfState = new State[3];
      arrayOfState[0] = NUMERIC;
      arrayOfState[1] = ALPHA;
      arrayOfState[2] = ISO_IEC_646;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.expanded.decoders.CurrentParsingState
 * JD-Core Version:    0.6.1
 */