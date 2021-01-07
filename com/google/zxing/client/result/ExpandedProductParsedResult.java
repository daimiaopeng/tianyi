package com.google.zxing.client.result;

import java.util.Map;

public final class ExpandedProductParsedResult extends ParsedResult
{
  public static final String KILOGRAM = "KG";
  public static final String POUND = "LB";
  private final String bestBeforeDate;
  private final String expirationDate;
  private final String lotNumber;
  private final String packagingDate;
  private final String price;
  private final String priceCurrency;
  private final String priceIncrement;
  private final String productID;
  private final String productionDate;
  private final String rawText;
  private final String sscc;
  private final Map<String, String> uncommonAIs;
  private final String weight;
  private final String weightIncrement;
  private final String weightType;

  public ExpandedProductParsedResult(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14, Map<String, String> paramMap)
  {
    super(ParsedResultType.PRODUCT);
    this.rawText = paramString1;
    this.productID = paramString2;
    this.sscc = paramString3;
    this.lotNumber = paramString4;
    this.productionDate = paramString5;
    this.packagingDate = paramString6;
    this.bestBeforeDate = paramString7;
    this.expirationDate = paramString8;
    this.weight = paramString9;
    this.weightType = paramString10;
    this.weightIncrement = paramString11;
    this.price = paramString12;
    this.priceIncrement = paramString13;
    this.priceCurrency = paramString14;
    this.uncommonAIs = paramMap;
  }

  private static boolean equalsOrNull(Object paramObject1, Object paramObject2)
  {
    boolean bool;
    if (paramObject1 == null)
    {
      if (paramObject2 == null)
        bool = true;
      else
        bool = false;
    }
    else
      bool = paramObject1.equals(paramObject2);
    return bool;
  }

  private static int hashNotNull(Object paramObject)
  {
    int i;
    if (paramObject == null)
      i = 0;
    else
      i = paramObject.hashCode();
    return i;
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof ExpandedProductParsedResult))
      return false;
    ExpandedProductParsedResult localExpandedProductParsedResult = (ExpandedProductParsedResult)paramObject;
    boolean bool1 = equalsOrNull(this.productID, localExpandedProductParsedResult.productID);
    boolean bool2 = false;
    if (bool1)
    {
      boolean bool3 = equalsOrNull(this.sscc, localExpandedProductParsedResult.sscc);
      bool2 = false;
      if (bool3)
      {
        boolean bool4 = equalsOrNull(this.lotNumber, localExpandedProductParsedResult.lotNumber);
        bool2 = false;
        if (bool4)
        {
          boolean bool5 = equalsOrNull(this.productionDate, localExpandedProductParsedResult.productionDate);
          bool2 = false;
          if (bool5)
          {
            boolean bool6 = equalsOrNull(this.bestBeforeDate, localExpandedProductParsedResult.bestBeforeDate);
            bool2 = false;
            if (bool6)
            {
              boolean bool7 = equalsOrNull(this.expirationDate, localExpandedProductParsedResult.expirationDate);
              bool2 = false;
              if (bool7)
              {
                boolean bool8 = equalsOrNull(this.weight, localExpandedProductParsedResult.weight);
                bool2 = false;
                if (bool8)
                {
                  boolean bool9 = equalsOrNull(this.weightType, localExpandedProductParsedResult.weightType);
                  bool2 = false;
                  if (bool9)
                  {
                    boolean bool10 = equalsOrNull(this.weightIncrement, localExpandedProductParsedResult.weightIncrement);
                    bool2 = false;
                    if (bool10)
                    {
                      boolean bool11 = equalsOrNull(this.price, localExpandedProductParsedResult.price);
                      bool2 = false;
                      if (bool11)
                      {
                        boolean bool12 = equalsOrNull(this.priceIncrement, localExpandedProductParsedResult.priceIncrement);
                        bool2 = false;
                        if (bool12)
                        {
                          boolean bool13 = equalsOrNull(this.priceCurrency, localExpandedProductParsedResult.priceCurrency);
                          bool2 = false;
                          if (bool13)
                          {
                            boolean bool14 = equalsOrNull(this.uncommonAIs, localExpandedProductParsedResult.uncommonAIs);
                            bool2 = false;
                            if (bool14)
                              bool2 = true;
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return bool2;
  }

  public String getBestBeforeDate()
  {
    return this.bestBeforeDate;
  }

  public String getDisplayResult()
  {
    return String.valueOf(this.rawText);
  }

  public String getExpirationDate()
  {
    return this.expirationDate;
  }

  public String getLotNumber()
  {
    return this.lotNumber;
  }

  public String getPackagingDate()
  {
    return this.packagingDate;
  }

  public String getPrice()
  {
    return this.price;
  }

  public String getPriceCurrency()
  {
    return this.priceCurrency;
  }

  public String getPriceIncrement()
  {
    return this.priceIncrement;
  }

  public String getProductID()
  {
    return this.productID;
  }

  public String getProductionDate()
  {
    return this.productionDate;
  }

  public String getRawText()
  {
    return this.rawText;
  }

  public String getSscc()
  {
    return this.sscc;
  }

  public Map<String, String> getUncommonAIs()
  {
    return this.uncommonAIs;
  }

  public String getWeight()
  {
    return this.weight;
  }

  public String getWeightIncrement()
  {
    return this.weightIncrement;
  }

  public String getWeightType()
  {
    return this.weightType;
  }

  public int hashCode()
  {
    return 0x0 ^ hashNotNull(this.productID) ^ hashNotNull(this.sscc) ^ hashNotNull(this.lotNumber) ^ hashNotNull(this.productionDate) ^ hashNotNull(this.bestBeforeDate) ^ hashNotNull(this.expirationDate) ^ hashNotNull(this.weight) ^ hashNotNull(this.weightType) ^ hashNotNull(this.weightIncrement) ^ hashNotNull(this.price) ^ hashNotNull(this.priceIncrement) ^ hashNotNull(this.priceCurrency) ^ hashNotNull(this.uncommonAIs);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.ExpandedProductParsedResult
 * JD-Core Version:    0.6.1
 */