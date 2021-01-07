package com.qihoo.jiagutracker.utils;

import com.qihoo.jiagutracker.QVMProtect;
import com.stub.StubApp;
import java.util.LinkedList;

@QVMProtect
public class CircularBuffer<E>
{
  private final LinkedList<E> buffer;
  private final int mCapacity;

  static
  {
    StubApp.interface11(2747);
  }

  public CircularBuffer(int paramInt)
  {
    this.mCapacity = paramInt;
    this.buffer = new LinkedList();
  }

  public native void addElement(E paramE);

  public native Object[] getData();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.jiagutracker.utils.CircularBuffer
 * JD-Core Version:    0.6.1
 */