package android.support.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.CONSTRUCTOR, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.PACKAGE})
public @interface RestrictTo
{
  public abstract Scope[] value();

  public static enum Scope
  {
    static
    {
      GROUP_ID = new Scope("GROUP_ID", 2);
      TESTS = new Scope("TESTS", 3);
      SUBCLASSES = new Scope("SUBCLASSES", 4);
      Scope[] arrayOfScope = new Scope[5];
      arrayOfScope[0] = LIBRARY;
      arrayOfScope[1] = LIBRARY_GROUP;
      arrayOfScope[2] = GROUP_ID;
      arrayOfScope[3] = TESTS;
      arrayOfScope[4] = SUBCLASSES;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.annotation.RestrictTo
 * JD-Core Version:    0.6.1
 */