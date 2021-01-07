package com.stub.stub01;

import android.os.Looper;
import android.widget.Toast;

class UpdateThread$ShowToastThread extends Thread
{
  public String msg;

  public UpdateThread$ShowToastThread(UpdateThread paramUpdateThread, String paramString)
  {
    this.msg = paramString;
  }

  public void run()
  {
    Looper.prepare();
    Toast.makeText(UpdateThread.access$000(this.this$0), this.msg, 1).show();
    Looper.loop();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.stub.stub01.UpdateThread.ShowToastThread
 * JD-Core Version:    0.6.1
 */