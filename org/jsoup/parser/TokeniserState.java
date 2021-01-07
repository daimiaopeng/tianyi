package org.jsoup.parser;

 enum TokeniserState
{
  private static final char eof = 'èøø';
  private static final char nullChar = '\000';
  private static final char replacementChar = 'ÔøΩ';
  private static final String replacementStr = String.valueOf(65533);

  static
  {
    // Byte code:
    //   0: new 88	org/jsoup/parser/TokeniserState$1
    //   3: dup
    //   4: ldc 89
    //   6: iconst_0
    //   7: invokespecial 93	org/jsoup/parser/TokeniserState$1:<init>	(Ljava/lang/String;I)V
    //   10: putstatic 95	org/jsoup/parser/TokeniserState:Data	Lorg/jsoup/parser/TokeniserState;
    //   13: new 97	org/jsoup/parser/TokeniserState$2
    //   16: dup
    //   17: ldc 98
    //   19: iconst_1
    //   20: invokespecial 99	org/jsoup/parser/TokeniserState$2:<init>	(Ljava/lang/String;I)V
    //   23: putstatic 101	org/jsoup/parser/TokeniserState:CharacterReferenceInData	Lorg/jsoup/parser/TokeniserState;
    //   26: new 103	org/jsoup/parser/TokeniserState$3
    //   29: dup
    //   30: ldc 104
    //   32: iconst_2
    //   33: invokespecial 105	org/jsoup/parser/TokeniserState$3:<init>	(Ljava/lang/String;I)V
    //   36: putstatic 107	org/jsoup/parser/TokeniserState:Rcdata	Lorg/jsoup/parser/TokeniserState;
    //   39: new 109	org/jsoup/parser/TokeniserState$4
    //   42: dup
    //   43: ldc 110
    //   45: iconst_3
    //   46: invokespecial 111	org/jsoup/parser/TokeniserState$4:<init>	(Ljava/lang/String;I)V
    //   49: putstatic 113	org/jsoup/parser/TokeniserState:CharacterReferenceInRcdata	Lorg/jsoup/parser/TokeniserState;
    //   52: new 115	org/jsoup/parser/TokeniserState$5
    //   55: dup
    //   56: ldc 116
    //   58: iconst_4
    //   59: invokespecial 117	org/jsoup/parser/TokeniserState$5:<init>	(Ljava/lang/String;I)V
    //   62: putstatic 119	org/jsoup/parser/TokeniserState:Rawtext	Lorg/jsoup/parser/TokeniserState;
    //   65: new 121	org/jsoup/parser/TokeniserState$6
    //   68: dup
    //   69: ldc 122
    //   71: iconst_5
    //   72: invokespecial 123	org/jsoup/parser/TokeniserState$6:<init>	(Ljava/lang/String;I)V
    //   75: putstatic 125	org/jsoup/parser/TokeniserState:ScriptData	Lorg/jsoup/parser/TokeniserState;
    //   78: new 127	org/jsoup/parser/TokeniserState$7
    //   81: dup
    //   82: ldc 128
    //   84: bipush 6
    //   86: invokespecial 129	org/jsoup/parser/TokeniserState$7:<init>	(Ljava/lang/String;I)V
    //   89: putstatic 131	org/jsoup/parser/TokeniserState:PLAINTEXT	Lorg/jsoup/parser/TokeniserState;
    //   92: new 133	org/jsoup/parser/TokeniserState$8
    //   95: dup
    //   96: ldc 134
    //   98: bipush 7
    //   100: invokespecial 135	org/jsoup/parser/TokeniserState$8:<init>	(Ljava/lang/String;I)V
    //   103: putstatic 137	org/jsoup/parser/TokeniserState:TagOpen	Lorg/jsoup/parser/TokeniserState;
    //   106: new 139	org/jsoup/parser/TokeniserState$9
    //   109: dup
    //   110: ldc 140
    //   112: bipush 8
    //   114: invokespecial 141	org/jsoup/parser/TokeniserState$9:<init>	(Ljava/lang/String;I)V
    //   117: putstatic 143	org/jsoup/parser/TokeniserState:EndTagOpen	Lorg/jsoup/parser/TokeniserState;
    //   120: new 145	org/jsoup/parser/TokeniserState$10
    //   123: dup
    //   124: ldc 146
    //   126: bipush 9
    //   128: invokespecial 147	org/jsoup/parser/TokeniserState$10:<init>	(Ljava/lang/String;I)V
    //   131: putstatic 149	org/jsoup/parser/TokeniserState:TagName	Lorg/jsoup/parser/TokeniserState;
    //   134: new 151	org/jsoup/parser/TokeniserState$11
    //   137: dup
    //   138: ldc 152
    //   140: bipush 10
    //   142: invokespecial 153	org/jsoup/parser/TokeniserState$11:<init>	(Ljava/lang/String;I)V
    //   145: putstatic 155	org/jsoup/parser/TokeniserState:RcdataLessthanSign	Lorg/jsoup/parser/TokeniserState;
    //   148: new 157	org/jsoup/parser/TokeniserState$12
    //   151: dup
    //   152: ldc 158
    //   154: bipush 11
    //   156: invokespecial 159	org/jsoup/parser/TokeniserState$12:<init>	(Ljava/lang/String;I)V
    //   159: putstatic 161	org/jsoup/parser/TokeniserState:RCDATAEndTagOpen	Lorg/jsoup/parser/TokeniserState;
    //   162: new 163	org/jsoup/parser/TokeniserState$13
    //   165: dup
    //   166: ldc 164
    //   168: bipush 12
    //   170: invokespecial 165	org/jsoup/parser/TokeniserState$13:<init>	(Ljava/lang/String;I)V
    //   173: putstatic 167	org/jsoup/parser/TokeniserState:RCDATAEndTagName	Lorg/jsoup/parser/TokeniserState;
    //   176: new 169	org/jsoup/parser/TokeniserState$14
    //   179: dup
    //   180: ldc 170
    //   182: bipush 13
    //   184: invokespecial 171	org/jsoup/parser/TokeniserState$14:<init>	(Ljava/lang/String;I)V
    //   187: putstatic 173	org/jsoup/parser/TokeniserState:RawtextLessthanSign	Lorg/jsoup/parser/TokeniserState;
    //   190: new 175	org/jsoup/parser/TokeniserState$15
    //   193: dup
    //   194: ldc 176
    //   196: bipush 14
    //   198: invokespecial 177	org/jsoup/parser/TokeniserState$15:<init>	(Ljava/lang/String;I)V
    //   201: putstatic 179	org/jsoup/parser/TokeniserState:RawtextEndTagOpen	Lorg/jsoup/parser/TokeniserState;
    //   204: new 181	org/jsoup/parser/TokeniserState$16
    //   207: dup
    //   208: ldc 182
    //   210: bipush 15
    //   212: invokespecial 183	org/jsoup/parser/TokeniserState$16:<init>	(Ljava/lang/String;I)V
    //   215: putstatic 185	org/jsoup/parser/TokeniserState:RawtextEndTagName	Lorg/jsoup/parser/TokeniserState;
    //   218: new 187	org/jsoup/parser/TokeniserState$17
    //   221: dup
    //   222: ldc 188
    //   224: bipush 16
    //   226: invokespecial 189	org/jsoup/parser/TokeniserState$17:<init>	(Ljava/lang/String;I)V
    //   229: putstatic 191	org/jsoup/parser/TokeniserState:ScriptDataLessthanSign	Lorg/jsoup/parser/TokeniserState;
    //   232: new 193	org/jsoup/parser/TokeniserState$18
    //   235: dup
    //   236: ldc 194
    //   238: bipush 17
    //   240: invokespecial 195	org/jsoup/parser/TokeniserState$18:<init>	(Ljava/lang/String;I)V
    //   243: putstatic 197	org/jsoup/parser/TokeniserState:ScriptDataEndTagOpen	Lorg/jsoup/parser/TokeniserState;
    //   246: new 199	org/jsoup/parser/TokeniserState$19
    //   249: dup
    //   250: ldc 200
    //   252: bipush 18
    //   254: invokespecial 201	org/jsoup/parser/TokeniserState$19:<init>	(Ljava/lang/String;I)V
    //   257: putstatic 203	org/jsoup/parser/TokeniserState:ScriptDataEndTagName	Lorg/jsoup/parser/TokeniserState;
    //   260: new 205	org/jsoup/parser/TokeniserState$20
    //   263: dup
    //   264: ldc 206
    //   266: bipush 19
    //   268: invokespecial 207	org/jsoup/parser/TokeniserState$20:<init>	(Ljava/lang/String;I)V
    //   271: putstatic 209	org/jsoup/parser/TokeniserState:ScriptDataEscapeStart	Lorg/jsoup/parser/TokeniserState;
    //   274: new 211	org/jsoup/parser/TokeniserState$21
    //   277: dup
    //   278: ldc 212
    //   280: bipush 20
    //   282: invokespecial 213	org/jsoup/parser/TokeniserState$21:<init>	(Ljava/lang/String;I)V
    //   285: putstatic 215	org/jsoup/parser/TokeniserState:ScriptDataEscapeStartDash	Lorg/jsoup/parser/TokeniserState;
    //   288: new 217	org/jsoup/parser/TokeniserState$22
    //   291: dup
    //   292: ldc 218
    //   294: bipush 21
    //   296: invokespecial 219	org/jsoup/parser/TokeniserState$22:<init>	(Ljava/lang/String;I)V
    //   299: putstatic 221	org/jsoup/parser/TokeniserState:ScriptDataEscaped	Lorg/jsoup/parser/TokeniserState;
    //   302: new 223	org/jsoup/parser/TokeniserState$23
    //   305: dup
    //   306: ldc 224
    //   308: bipush 22
    //   310: invokespecial 225	org/jsoup/parser/TokeniserState$23:<init>	(Ljava/lang/String;I)V
    //   313: putstatic 227	org/jsoup/parser/TokeniserState:ScriptDataEscapedDash	Lorg/jsoup/parser/TokeniserState;
    //   316: new 229	org/jsoup/parser/TokeniserState$24
    //   319: dup
    //   320: ldc 230
    //   322: bipush 23
    //   324: invokespecial 231	org/jsoup/parser/TokeniserState$24:<init>	(Ljava/lang/String;I)V
    //   327: putstatic 233	org/jsoup/parser/TokeniserState:ScriptDataEscapedDashDash	Lorg/jsoup/parser/TokeniserState;
    //   330: new 235	org/jsoup/parser/TokeniserState$25
    //   333: dup
    //   334: ldc 236
    //   336: bipush 24
    //   338: invokespecial 237	org/jsoup/parser/TokeniserState$25:<init>	(Ljava/lang/String;I)V
    //   341: putstatic 239	org/jsoup/parser/TokeniserState:ScriptDataEscapedLessthanSign	Lorg/jsoup/parser/TokeniserState;
    //   344: new 241	org/jsoup/parser/TokeniserState$26
    //   347: dup
    //   348: ldc 242
    //   350: bipush 25
    //   352: invokespecial 243	org/jsoup/parser/TokeniserState$26:<init>	(Ljava/lang/String;I)V
    //   355: putstatic 245	org/jsoup/parser/TokeniserState:ScriptDataEscapedEndTagOpen	Lorg/jsoup/parser/TokeniserState;
    //   358: new 247	org/jsoup/parser/TokeniserState$27
    //   361: dup
    //   362: ldc 248
    //   364: bipush 26
    //   366: invokespecial 249	org/jsoup/parser/TokeniserState$27:<init>	(Ljava/lang/String;I)V
    //   369: putstatic 251	org/jsoup/parser/TokeniserState:ScriptDataEscapedEndTagName	Lorg/jsoup/parser/TokeniserState;
    //   372: new 253	org/jsoup/parser/TokeniserState$28
    //   375: dup
    //   376: ldc 254
    //   378: bipush 27
    //   380: invokespecial 255	org/jsoup/parser/TokeniserState$28:<init>	(Ljava/lang/String;I)V
    //   383: putstatic 257	org/jsoup/parser/TokeniserState:ScriptDataDoubleEscapeStart	Lorg/jsoup/parser/TokeniserState;
    //   386: new 259	org/jsoup/parser/TokeniserState$29
    //   389: dup
    //   390: ldc_w 260
    //   393: bipush 28
    //   395: invokespecial 261	org/jsoup/parser/TokeniserState$29:<init>	(Ljava/lang/String;I)V
    //   398: putstatic 263	org/jsoup/parser/TokeniserState:ScriptDataDoubleEscaped	Lorg/jsoup/parser/TokeniserState;
    //   401: new 265	org/jsoup/parser/TokeniserState$30
    //   404: dup
    //   405: ldc_w 266
    //   408: bipush 29
    //   410: invokespecial 267	org/jsoup/parser/TokeniserState$30:<init>	(Ljava/lang/String;I)V
    //   413: putstatic 269	org/jsoup/parser/TokeniserState:ScriptDataDoubleEscapedDash	Lorg/jsoup/parser/TokeniserState;
    //   416: new 271	org/jsoup/parser/TokeniserState$31
    //   419: dup
    //   420: ldc_w 272
    //   423: bipush 30
    //   425: invokespecial 273	org/jsoup/parser/TokeniserState$31:<init>	(Ljava/lang/String;I)V
    //   428: putstatic 275	org/jsoup/parser/TokeniserState:ScriptDataDoubleEscapedDashDash	Lorg/jsoup/parser/TokeniserState;
    //   431: new 277	org/jsoup/parser/TokeniserState$32
    //   434: dup
    //   435: ldc_w 278
    //   438: bipush 31
    //   440: invokespecial 279	org/jsoup/parser/TokeniserState$32:<init>	(Ljava/lang/String;I)V
    //   443: putstatic 281	org/jsoup/parser/TokeniserState:ScriptDataDoubleEscapedLessthanSign	Lorg/jsoup/parser/TokeniserState;
    //   446: new 283	org/jsoup/parser/TokeniserState$33
    //   449: dup
    //   450: ldc_w 284
    //   453: bipush 32
    //   455: invokespecial 285	org/jsoup/parser/TokeniserState$33:<init>	(Ljava/lang/String;I)V
    //   458: putstatic 287	org/jsoup/parser/TokeniserState:ScriptDataDoubleEscapeEnd	Lorg/jsoup/parser/TokeniserState;
    //   461: new 289	org/jsoup/parser/TokeniserState$34
    //   464: dup
    //   465: ldc_w 290
    //   468: bipush 33
    //   470: invokespecial 291	org/jsoup/parser/TokeniserState$34:<init>	(Ljava/lang/String;I)V
    //   473: putstatic 293	org/jsoup/parser/TokeniserState:BeforeAttributeName	Lorg/jsoup/parser/TokeniserState;
    //   476: new 295	org/jsoup/parser/TokeniserState$35
    //   479: dup
    //   480: ldc_w 296
    //   483: bipush 34
    //   485: invokespecial 297	org/jsoup/parser/TokeniserState$35:<init>	(Ljava/lang/String;I)V
    //   488: putstatic 299	org/jsoup/parser/TokeniserState:AttributeName	Lorg/jsoup/parser/TokeniserState;
    //   491: new 301	org/jsoup/parser/TokeniserState$36
    //   494: dup
    //   495: ldc_w 302
    //   498: bipush 35
    //   500: invokespecial 303	org/jsoup/parser/TokeniserState$36:<init>	(Ljava/lang/String;I)V
    //   503: putstatic 305	org/jsoup/parser/TokeniserState:AfterAttributeName	Lorg/jsoup/parser/TokeniserState;
    //   506: new 307	org/jsoup/parser/TokeniserState$37
    //   509: dup
    //   510: ldc_w 308
    //   513: bipush 36
    //   515: invokespecial 309	org/jsoup/parser/TokeniserState$37:<init>	(Ljava/lang/String;I)V
    //   518: putstatic 311	org/jsoup/parser/TokeniserState:BeforeAttributeValue	Lorg/jsoup/parser/TokeniserState;
    //   521: new 313	org/jsoup/parser/TokeniserState$38
    //   524: dup
    //   525: ldc_w 314
    //   528: bipush 37
    //   530: invokespecial 315	org/jsoup/parser/TokeniserState$38:<init>	(Ljava/lang/String;I)V
    //   533: putstatic 317	org/jsoup/parser/TokeniserState:AttributeValue_doubleQuoted	Lorg/jsoup/parser/TokeniserState;
    //   536: new 319	org/jsoup/parser/TokeniserState$39
    //   539: dup
    //   540: ldc_w 320
    //   543: bipush 38
    //   545: invokespecial 321	org/jsoup/parser/TokeniserState$39:<init>	(Ljava/lang/String;I)V
    //   548: putstatic 323	org/jsoup/parser/TokeniserState:AttributeValue_singleQuoted	Lorg/jsoup/parser/TokeniserState;
    //   551: new 325	org/jsoup/parser/TokeniserState$40
    //   554: dup
    //   555: ldc_w 326
    //   558: bipush 39
    //   560: invokespecial 327	org/jsoup/parser/TokeniserState$40:<init>	(Ljava/lang/String;I)V
    //   563: putstatic 329	org/jsoup/parser/TokeniserState:AttributeValue_unquoted	Lorg/jsoup/parser/TokeniserState;
    //   566: new 331	org/jsoup/parser/TokeniserState$41
    //   569: dup
    //   570: ldc_w 332
    //   573: bipush 40
    //   575: invokespecial 333	org/jsoup/parser/TokeniserState$41:<init>	(Ljava/lang/String;I)V
    //   578: putstatic 335	org/jsoup/parser/TokeniserState:AfterAttributeValue_quoted	Lorg/jsoup/parser/TokeniserState;
    //   581: new 337	org/jsoup/parser/TokeniserState$42
    //   584: dup
    //   585: ldc_w 338
    //   588: bipush 41
    //   590: invokespecial 339	org/jsoup/parser/TokeniserState$42:<init>	(Ljava/lang/String;I)V
    //   593: putstatic 341	org/jsoup/parser/TokeniserState:SelfClosingStartTag	Lorg/jsoup/parser/TokeniserState;
    //   596: new 343	org/jsoup/parser/TokeniserState$43
    //   599: dup
    //   600: ldc_w 344
    //   603: bipush 42
    //   605: invokespecial 345	org/jsoup/parser/TokeniserState$43:<init>	(Ljava/lang/String;I)V
    //   608: putstatic 347	org/jsoup/parser/TokeniserState:BogusComment	Lorg/jsoup/parser/TokeniserState;
    //   611: new 349	org/jsoup/parser/TokeniserState$44
    //   614: dup
    //   615: ldc_w 350
    //   618: bipush 43
    //   620: invokespecial 351	org/jsoup/parser/TokeniserState$44:<init>	(Ljava/lang/String;I)V
    //   623: putstatic 353	org/jsoup/parser/TokeniserState:MarkupDeclarationOpen	Lorg/jsoup/parser/TokeniserState;
    //   626: new 355	org/jsoup/parser/TokeniserState$45
    //   629: dup
    //   630: ldc_w 356
    //   633: bipush 44
    //   635: invokespecial 357	org/jsoup/parser/TokeniserState$45:<init>	(Ljava/lang/String;I)V
    //   638: putstatic 359	org/jsoup/parser/TokeniserState:CommentStart	Lorg/jsoup/parser/TokeniserState;
    //   641: new 361	org/jsoup/parser/TokeniserState$46
    //   644: dup
    //   645: ldc_w 362
    //   648: bipush 45
    //   650: invokespecial 363	org/jsoup/parser/TokeniserState$46:<init>	(Ljava/lang/String;I)V
    //   653: putstatic 365	org/jsoup/parser/TokeniserState:CommentStartDash	Lorg/jsoup/parser/TokeniserState;
    //   656: new 367	org/jsoup/parser/TokeniserState$47
    //   659: dup
    //   660: ldc_w 368
    //   663: bipush 46
    //   665: invokespecial 369	org/jsoup/parser/TokeniserState$47:<init>	(Ljava/lang/String;I)V
    //   668: putstatic 371	org/jsoup/parser/TokeniserState:Comment	Lorg/jsoup/parser/TokeniserState;
    //   671: new 373	org/jsoup/parser/TokeniserState$48
    //   674: dup
    //   675: ldc_w 374
    //   678: bipush 47
    //   680: invokespecial 375	org/jsoup/parser/TokeniserState$48:<init>	(Ljava/lang/String;I)V
    //   683: putstatic 377	org/jsoup/parser/TokeniserState:CommentEndDash	Lorg/jsoup/parser/TokeniserState;
    //   686: new 379	org/jsoup/parser/TokeniserState$49
    //   689: dup
    //   690: ldc_w 380
    //   693: bipush 48
    //   695: invokespecial 381	org/jsoup/parser/TokeniserState$49:<init>	(Ljava/lang/String;I)V
    //   698: putstatic 383	org/jsoup/parser/TokeniserState:CommentEnd	Lorg/jsoup/parser/TokeniserState;
    //   701: new 385	org/jsoup/parser/TokeniserState$50
    //   704: dup
    //   705: ldc_w 386
    //   708: bipush 49
    //   710: invokespecial 387	org/jsoup/parser/TokeniserState$50:<init>	(Ljava/lang/String;I)V
    //   713: putstatic 389	org/jsoup/parser/TokeniserState:CommentEndBang	Lorg/jsoup/parser/TokeniserState;
    //   716: new 391	org/jsoup/parser/TokeniserState$51
    //   719: dup
    //   720: ldc_w 392
    //   723: bipush 50
    //   725: invokespecial 393	org/jsoup/parser/TokeniserState$51:<init>	(Ljava/lang/String;I)V
    //   728: putstatic 395	org/jsoup/parser/TokeniserState:Doctype	Lorg/jsoup/parser/TokeniserState;
    //   731: new 397	org/jsoup/parser/TokeniserState$52
    //   734: dup
    //   735: ldc_w 398
    //   738: bipush 51
    //   740: invokespecial 399	org/jsoup/parser/TokeniserState$52:<init>	(Ljava/lang/String;I)V
    //   743: putstatic 401	org/jsoup/parser/TokeniserState:BeforeDoctypeName	Lorg/jsoup/parser/TokeniserState;
    //   746: new 403	org/jsoup/parser/TokeniserState$53
    //   749: dup
    //   750: ldc_w 404
    //   753: bipush 52
    //   755: invokespecial 405	org/jsoup/parser/TokeniserState$53:<init>	(Ljava/lang/String;I)V
    //   758: putstatic 407	org/jsoup/parser/TokeniserState:DoctypeName	Lorg/jsoup/parser/TokeniserState;
    //   761: new 409	org/jsoup/parser/TokeniserState$54
    //   764: dup
    //   765: ldc_w 410
    //   768: bipush 53
    //   770: invokespecial 411	org/jsoup/parser/TokeniserState$54:<init>	(Ljava/lang/String;I)V
    //   773: putstatic 413	org/jsoup/parser/TokeniserState:AfterDoctypeName	Lorg/jsoup/parser/TokeniserState;
    //   776: new 415	org/jsoup/parser/TokeniserState$55
    //   779: dup
    //   780: ldc_w 416
    //   783: bipush 54
    //   785: invokespecial 417	org/jsoup/parser/TokeniserState$55:<init>	(Ljava/lang/String;I)V
    //   788: putstatic 419	org/jsoup/parser/TokeniserState:AfterDoctypePublicKeyword	Lorg/jsoup/parser/TokeniserState;
    //   791: new 421	org/jsoup/parser/TokeniserState$56
    //   794: dup
    //   795: ldc_w 422
    //   798: bipush 55
    //   800: invokespecial 423	org/jsoup/parser/TokeniserState$56:<init>	(Ljava/lang/String;I)V
    //   803: putstatic 425	org/jsoup/parser/TokeniserState:BeforeDoctypePublicIdentifier	Lorg/jsoup/parser/TokeniserState;
    //   806: new 427	org/jsoup/parser/TokeniserState$57
    //   809: dup
    //   810: ldc_w 428
    //   813: bipush 56
    //   815: invokespecial 429	org/jsoup/parser/TokeniserState$57:<init>	(Ljava/lang/String;I)V
    //   818: putstatic 431	org/jsoup/parser/TokeniserState:DoctypePublicIdentifier_doubleQuoted	Lorg/jsoup/parser/TokeniserState;
    //   821: new 433	org/jsoup/parser/TokeniserState$58
    //   824: dup
    //   825: ldc_w 434
    //   828: bipush 57
    //   830: invokespecial 435	org/jsoup/parser/TokeniserState$58:<init>	(Ljava/lang/String;I)V
    //   833: putstatic 437	org/jsoup/parser/TokeniserState:DoctypePublicIdentifier_singleQuoted	Lorg/jsoup/parser/TokeniserState;
    //   836: new 439	org/jsoup/parser/TokeniserState$59
    //   839: dup
    //   840: ldc_w 440
    //   843: bipush 58
    //   845: invokespecial 441	org/jsoup/parser/TokeniserState$59:<init>	(Ljava/lang/String;I)V
    //   848: putstatic 443	org/jsoup/parser/TokeniserState:AfterDoctypePublicIdentifier	Lorg/jsoup/parser/TokeniserState;
    //   851: new 445	org/jsoup/parser/TokeniserState$60
    //   854: dup
    //   855: ldc_w 446
    //   858: bipush 59
    //   860: invokespecial 447	org/jsoup/parser/TokeniserState$60:<init>	(Ljava/lang/String;I)V
    //   863: putstatic 449	org/jsoup/parser/TokeniserState:BetweenDoctypePublicAndSystemIdentifiers	Lorg/jsoup/parser/TokeniserState;
    //   866: new 451	org/jsoup/parser/TokeniserState$61
    //   869: dup
    //   870: ldc_w 452
    //   873: bipush 60
    //   875: invokespecial 453	org/jsoup/parser/TokeniserState$61:<init>	(Ljava/lang/String;I)V
    //   878: putstatic 455	org/jsoup/parser/TokeniserState:AfterDoctypeSystemKeyword	Lorg/jsoup/parser/TokeniserState;
    //   881: new 457	org/jsoup/parser/TokeniserState$62
    //   884: dup
    //   885: ldc_w 458
    //   888: bipush 61
    //   890: invokespecial 459	org/jsoup/parser/TokeniserState$62:<init>	(Ljava/lang/String;I)V
    //   893: putstatic 461	org/jsoup/parser/TokeniserState:BeforeDoctypeSystemIdentifier	Lorg/jsoup/parser/TokeniserState;
    //   896: new 463	org/jsoup/parser/TokeniserState$63
    //   899: dup
    //   900: ldc_w 464
    //   903: bipush 62
    //   905: invokespecial 465	org/jsoup/parser/TokeniserState$63:<init>	(Ljava/lang/String;I)V
    //   908: putstatic 467	org/jsoup/parser/TokeniserState:DoctypeSystemIdentifier_doubleQuoted	Lorg/jsoup/parser/TokeniserState;
    //   911: new 469	org/jsoup/parser/TokeniserState$64
    //   914: dup
    //   915: ldc_w 470
    //   918: bipush 63
    //   920: invokespecial 471	org/jsoup/parser/TokeniserState$64:<init>	(Ljava/lang/String;I)V
    //   923: putstatic 473	org/jsoup/parser/TokeniserState:DoctypeSystemIdentifier_singleQuoted	Lorg/jsoup/parser/TokeniserState;
    //   926: new 475	org/jsoup/parser/TokeniserState$65
    //   929: dup
    //   930: ldc_w 476
    //   933: bipush 64
    //   935: invokespecial 477	org/jsoup/parser/TokeniserState$65:<init>	(Ljava/lang/String;I)V
    //   938: putstatic 479	org/jsoup/parser/TokeniserState:AfterDoctypeSystemIdentifier	Lorg/jsoup/parser/TokeniserState;
    //   941: new 481	org/jsoup/parser/TokeniserState$66
    //   944: dup
    //   945: ldc_w 482
    //   948: bipush 65
    //   950: invokespecial 483	org/jsoup/parser/TokeniserState$66:<init>	(Ljava/lang/String;I)V
    //   953: putstatic 485	org/jsoup/parser/TokeniserState:BogusDoctype	Lorg/jsoup/parser/TokeniserState;
    //   956: new 487	org/jsoup/parser/TokeniserState$67
    //   959: dup
    //   960: ldc_w 488
    //   963: bipush 66
    //   965: invokespecial 489	org/jsoup/parser/TokeniserState$67:<init>	(Ljava/lang/String;I)V
    //   968: putstatic 491	org/jsoup/parser/TokeniserState:CdataSection	Lorg/jsoup/parser/TokeniserState;
    //   971: bipush 67
    //   973: anewarray 2	org/jsoup/parser/TokeniserState
    //   976: astore_0
    //   977: aload_0
    //   978: iconst_0
    //   979: getstatic 95	org/jsoup/parser/TokeniserState:Data	Lorg/jsoup/parser/TokeniserState;
    //   982: aastore
    //   983: aload_0
    //   984: iconst_1
    //   985: getstatic 101	org/jsoup/parser/TokeniserState:CharacterReferenceInData	Lorg/jsoup/parser/TokeniserState;
    //   988: aastore
    //   989: aload_0
    //   990: iconst_2
    //   991: getstatic 107	org/jsoup/parser/TokeniserState:Rcdata	Lorg/jsoup/parser/TokeniserState;
    //   994: aastore
    //   995: aload_0
    //   996: iconst_3
    //   997: getstatic 113	org/jsoup/parser/TokeniserState:CharacterReferenceInRcdata	Lorg/jsoup/parser/TokeniserState;
    //   1000: aastore
    //   1001: aload_0
    //   1002: iconst_4
    //   1003: getstatic 119	org/jsoup/parser/TokeniserState:Rawtext	Lorg/jsoup/parser/TokeniserState;
    //   1006: aastore
    //   1007: aload_0
    //   1008: iconst_5
    //   1009: getstatic 125	org/jsoup/parser/TokeniserState:ScriptData	Lorg/jsoup/parser/TokeniserState;
    //   1012: aastore
    //   1013: aload_0
    //   1014: bipush 6
    //   1016: getstatic 131	org/jsoup/parser/TokeniserState:PLAINTEXT	Lorg/jsoup/parser/TokeniserState;
    //   1019: aastore
    //   1020: aload_0
    //   1021: bipush 7
    //   1023: getstatic 137	org/jsoup/parser/TokeniserState:TagOpen	Lorg/jsoup/parser/TokeniserState;
    //   1026: aastore
    //   1027: aload_0
    //   1028: bipush 8
    //   1030: getstatic 143	org/jsoup/parser/TokeniserState:EndTagOpen	Lorg/jsoup/parser/TokeniserState;
    //   1033: aastore
    //   1034: aload_0
    //   1035: bipush 9
    //   1037: getstatic 149	org/jsoup/parser/TokeniserState:TagName	Lorg/jsoup/parser/TokeniserState;
    //   1040: aastore
    //   1041: aload_0
    //   1042: bipush 10
    //   1044: getstatic 155	org/jsoup/parser/TokeniserState:RcdataLessthanSign	Lorg/jsoup/parser/TokeniserState;
    //   1047: aastore
    //   1048: aload_0
    //   1049: bipush 11
    //   1051: getstatic 161	org/jsoup/parser/TokeniserState:RCDATAEndTagOpen	Lorg/jsoup/parser/TokeniserState;
    //   1054: aastore
    //   1055: aload_0
    //   1056: bipush 12
    //   1058: getstatic 167	org/jsoup/parser/TokeniserState:RCDATAEndTagName	Lorg/jsoup/parser/TokeniserState;
    //   1061: aastore
    //   1062: aload_0
    //   1063: bipush 13
    //   1065: getstatic 173	org/jsoup/parser/TokeniserState:RawtextLessthanSign	Lorg/jsoup/parser/TokeniserState;
    //   1068: aastore
    //   1069: aload_0
    //   1070: bipush 14
    //   1072: getstatic 179	org/jsoup/parser/TokeniserState:RawtextEndTagOpen	Lorg/jsoup/parser/TokeniserState;
    //   1075: aastore
    //   1076: aload_0
    //   1077: bipush 15
    //   1079: getstatic 185	org/jsoup/parser/TokeniserState:RawtextEndTagName	Lorg/jsoup/parser/TokeniserState;
    //   1082: aastore
    //   1083: aload_0
    //   1084: bipush 16
    //   1086: getstatic 191	org/jsoup/parser/TokeniserState:ScriptDataLessthanSign	Lorg/jsoup/parser/TokeniserState;
    //   1089: aastore
    //   1090: aload_0
    //   1091: bipush 17
    //   1093: getstatic 197	org/jsoup/parser/TokeniserState:ScriptDataEndTagOpen	Lorg/jsoup/parser/TokeniserState;
    //   1096: aastore
    //   1097: aload_0
    //   1098: bipush 18
    //   1100: getstatic 203	org/jsoup/parser/TokeniserState:ScriptDataEndTagName	Lorg/jsoup/parser/TokeniserState;
    //   1103: aastore
    //   1104: aload_0
    //   1105: bipush 19
    //   1107: getstatic 209	org/jsoup/parser/TokeniserState:ScriptDataEscapeStart	Lorg/jsoup/parser/TokeniserState;
    //   1110: aastore
    //   1111: aload_0
    //   1112: bipush 20
    //   1114: getstatic 215	org/jsoup/parser/TokeniserState:ScriptDataEscapeStartDash	Lorg/jsoup/parser/TokeniserState;
    //   1117: aastore
    //   1118: aload_0
    //   1119: bipush 21
    //   1121: getstatic 221	org/jsoup/parser/TokeniserState:ScriptDataEscaped	Lorg/jsoup/parser/TokeniserState;
    //   1124: aastore
    //   1125: aload_0
    //   1126: bipush 22
    //   1128: getstatic 227	org/jsoup/parser/TokeniserState:ScriptDataEscapedDash	Lorg/jsoup/parser/TokeniserState;
    //   1131: aastore
    //   1132: aload_0
    //   1133: bipush 23
    //   1135: getstatic 233	org/jsoup/parser/TokeniserState:ScriptDataEscapedDashDash	Lorg/jsoup/parser/TokeniserState;
    //   1138: aastore
    //   1139: aload_0
    //   1140: bipush 24
    //   1142: getstatic 239	org/jsoup/parser/TokeniserState:ScriptDataEscapedLessthanSign	Lorg/jsoup/parser/TokeniserState;
    //   1145: aastore
    //   1146: aload_0
    //   1147: bipush 25
    //   1149: getstatic 245	org/jsoup/parser/TokeniserState:ScriptDataEscapedEndTagOpen	Lorg/jsoup/parser/TokeniserState;
    //   1152: aastore
    //   1153: aload_0
    //   1154: bipush 26
    //   1156: getstatic 251	org/jsoup/parser/TokeniserState:ScriptDataEscapedEndTagName	Lorg/jsoup/parser/TokeniserState;
    //   1159: aastore
    //   1160: aload_0
    //   1161: bipush 27
    //   1163: getstatic 257	org/jsoup/parser/TokeniserState:ScriptDataDoubleEscapeStart	Lorg/jsoup/parser/TokeniserState;
    //   1166: aastore
    //   1167: aload_0
    //   1168: bipush 28
    //   1170: getstatic 263	org/jsoup/parser/TokeniserState:ScriptDataDoubleEscaped	Lorg/jsoup/parser/TokeniserState;
    //   1173: aastore
    //   1174: aload_0
    //   1175: bipush 29
    //   1177: getstatic 269	org/jsoup/parser/TokeniserState:ScriptDataDoubleEscapedDash	Lorg/jsoup/parser/TokeniserState;
    //   1180: aastore
    //   1181: aload_0
    //   1182: bipush 30
    //   1184: getstatic 275	org/jsoup/parser/TokeniserState:ScriptDataDoubleEscapedDashDash	Lorg/jsoup/parser/TokeniserState;
    //   1187: aastore
    //   1188: aload_0
    //   1189: bipush 31
    //   1191: getstatic 281	org/jsoup/parser/TokeniserState:ScriptDataDoubleEscapedLessthanSign	Lorg/jsoup/parser/TokeniserState;
    //   1194: aastore
    //   1195: aload_0
    //   1196: bipush 32
    //   1198: getstatic 287	org/jsoup/parser/TokeniserState:ScriptDataDoubleEscapeEnd	Lorg/jsoup/parser/TokeniserState;
    //   1201: aastore
    //   1202: aload_0
    //   1203: bipush 33
    //   1205: getstatic 293	org/jsoup/parser/TokeniserState:BeforeAttributeName	Lorg/jsoup/parser/TokeniserState;
    //   1208: aastore
    //   1209: aload_0
    //   1210: bipush 34
    //   1212: getstatic 299	org/jsoup/parser/TokeniserState:AttributeName	Lorg/jsoup/parser/TokeniserState;
    //   1215: aastore
    //   1216: aload_0
    //   1217: bipush 35
    //   1219: getstatic 305	org/jsoup/parser/TokeniserState:AfterAttributeName	Lorg/jsoup/parser/TokeniserState;
    //   1222: aastore
    //   1223: aload_0
    //   1224: bipush 36
    //   1226: getstatic 311	org/jsoup/parser/TokeniserState:BeforeAttributeValue	Lorg/jsoup/parser/TokeniserState;
    //   1229: aastore
    //   1230: aload_0
    //   1231: bipush 37
    //   1233: getstatic 317	org/jsoup/parser/TokeniserState:AttributeValue_doubleQuoted	Lorg/jsoup/parser/TokeniserState;
    //   1236: aastore
    //   1237: aload_0
    //   1238: bipush 38
    //   1240: getstatic 323	org/jsoup/parser/TokeniserState:AttributeValue_singleQuoted	Lorg/jsoup/parser/TokeniserState;
    //   1243: aastore
    //   1244: aload_0
    //   1245: bipush 39
    //   1247: getstatic 329	org/jsoup/parser/TokeniserState:AttributeValue_unquoted	Lorg/jsoup/parser/TokeniserState;
    //   1250: aastore
    //   1251: aload_0
    //   1252: bipush 40
    //   1254: getstatic 335	org/jsoup/parser/TokeniserState:AfterAttributeValue_quoted	Lorg/jsoup/parser/TokeniserState;
    //   1257: aastore
    //   1258: aload_0
    //   1259: bipush 41
    //   1261: getstatic 341	org/jsoup/parser/TokeniserState:SelfClosingStartTag	Lorg/jsoup/parser/TokeniserState;
    //   1264: aastore
    //   1265: aload_0
    //   1266: bipush 42
    //   1268: getstatic 347	org/jsoup/parser/TokeniserState:BogusComment	Lorg/jsoup/parser/TokeniserState;
    //   1271: aastore
    //   1272: aload_0
    //   1273: bipush 43
    //   1275: getstatic 353	org/jsoup/parser/TokeniserState:MarkupDeclarationOpen	Lorg/jsoup/parser/TokeniserState;
    //   1278: aastore
    //   1279: aload_0
    //   1280: bipush 44
    //   1282: getstatic 359	org/jsoup/parser/TokeniserState:CommentStart	Lorg/jsoup/parser/TokeniserState;
    //   1285: aastore
    //   1286: aload_0
    //   1287: bipush 45
    //   1289: getstatic 365	org/jsoup/parser/TokeniserState:CommentStartDash	Lorg/jsoup/parser/TokeniserState;
    //   1292: aastore
    //   1293: aload_0
    //   1294: bipush 46
    //   1296: getstatic 371	org/jsoup/parser/TokeniserState:Comment	Lorg/jsoup/parser/TokeniserState;
    //   1299: aastore
    //   1300: aload_0
    //   1301: bipush 47
    //   1303: getstatic 377	org/jsoup/parser/TokeniserState:CommentEndDash	Lorg/jsoup/parser/TokeniserState;
    //   1306: aastore
    //   1307: aload_0
    //   1308: bipush 48
    //   1310: getstatic 383	org/jsoup/parser/TokeniserState:CommentEnd	Lorg/jsoup/parser/TokeniserState;
    //   1313: aastore
    //   1314: aload_0
    //   1315: bipush 49
    //   1317: getstatic 389	org/jsoup/parser/TokeniserState:CommentEndBang	Lorg/jsoup/parser/TokeniserState;
    //   1320: aastore
    //   1321: aload_0
    //   1322: bipush 50
    //   1324: getstatic 395	org/jsoup/parser/TokeniserState:Doctype	Lorg/jsoup/parser/TokeniserState;
    //   1327: aastore
    //   1328: aload_0
    //   1329: bipush 51
    //   1331: getstatic 401	org/jsoup/parser/TokeniserState:BeforeDoctypeName	Lorg/jsoup/parser/TokeniserState;
    //   1334: aastore
    //   1335: aload_0
    //   1336: bipush 52
    //   1338: getstatic 407	org/jsoup/parser/TokeniserState:DoctypeName	Lorg/jsoup/parser/TokeniserState;
    //   1341: aastore
    //   1342: aload_0
    //   1343: bipush 53
    //   1345: getstatic 413	org/jsoup/parser/TokeniserState:AfterDoctypeName	Lorg/jsoup/parser/TokeniserState;
    //   1348: aastore
    //   1349: aload_0
    //   1350: bipush 54
    //   1352: getstatic 419	org/jsoup/parser/TokeniserState:AfterDoctypePublicKeyword	Lorg/jsoup/parser/TokeniserState;
    //   1355: aastore
    //   1356: aload_0
    //   1357: bipush 55
    //   1359: getstatic 425	org/jsoup/parser/TokeniserState:BeforeDoctypePublicIdentifier	Lorg/jsoup/parser/TokeniserState;
    //   1362: aastore
    //   1363: aload_0
    //   1364: bipush 56
    //   1366: getstatic 431	org/jsoup/parser/TokeniserState:DoctypePublicIdentifier_doubleQuoted	Lorg/jsoup/parser/TokeniserState;
    //   1369: aastore
    //   1370: aload_0
    //   1371: bipush 57
    //   1373: getstatic 437	org/jsoup/parser/TokeniserState:DoctypePublicIdentifier_singleQuoted	Lorg/jsoup/parser/TokeniserState;
    //   1376: aastore
    //   1377: aload_0
    //   1378: bipush 58
    //   1380: getstatic 443	org/jsoup/parser/TokeniserState:AfterDoctypePublicIdentifier	Lorg/jsoup/parser/TokeniserState;
    //   1383: aastore
    //   1384: aload_0
    //   1385: bipush 59
    //   1387: getstatic 449	org/jsoup/parser/TokeniserState:BetweenDoctypePublicAndSystemIdentifiers	Lorg/jsoup/parser/TokeniserState;
    //   1390: aastore
    //   1391: aload_0
    //   1392: bipush 60
    //   1394: getstatic 455	org/jsoup/parser/TokeniserState:AfterDoctypeSystemKeyword	Lorg/jsoup/parser/TokeniserState;
    //   1397: aastore
    //   1398: aload_0
    //   1399: bipush 61
    //   1401: getstatic 461	org/jsoup/parser/TokeniserState:BeforeDoctypeSystemIdentifier	Lorg/jsoup/parser/TokeniserState;
    //   1404: aastore
    //   1405: aload_0
    //   1406: bipush 62
    //   1408: getstatic 467	org/jsoup/parser/TokeniserState:DoctypeSystemIdentifier_doubleQuoted	Lorg/jsoup/parser/TokeniserState;
    //   1411: aastore
    //   1412: aload_0
    //   1413: bipush 63
    //   1415: getstatic 473	org/jsoup/parser/TokeniserState:DoctypeSystemIdentifier_singleQuoted	Lorg/jsoup/parser/TokeniserState;
    //   1418: aastore
    //   1419: aload_0
    //   1420: bipush 64
    //   1422: getstatic 479	org/jsoup/parser/TokeniserState:AfterDoctypeSystemIdentifier	Lorg/jsoup/parser/TokeniserState;
    //   1425: aastore
    //   1426: aload_0
    //   1427: bipush 65
    //   1429: getstatic 485	org/jsoup/parser/TokeniserState:BogusDoctype	Lorg/jsoup/parser/TokeniserState;
    //   1432: aastore
    //   1433: aload_0
    //   1434: bipush 66
    //   1436: getstatic 491	org/jsoup/parser/TokeniserState:CdataSection	Lorg/jsoup/parser/TokeniserState;
    //   1439: aastore
    //   1440: aload_0
    //   1441: putstatic 493	org/jsoup/parser/TokeniserState:$VALUES	[Lorg/jsoup/parser/TokeniserState;
    //   1444: ldc 82
    //   1446: invokestatic 499	java/lang/String:valueOf	(C)Ljava/lang/String;
    //   1449: putstatic 501	org/jsoup/parser/TokeniserState:replacementStr	Ljava/lang/String;
    //   1452: return
  }

  private static final void handleDataDoubleEscapeTag(Tokeniser paramTokeniser, CharacterReader paramCharacterReader, TokeniserState paramTokeniserState1, TokeniserState paramTokeniserState2)
  {
    if (paramCharacterReader.matchesLetter())
    {
      String str = paramCharacterReader.consumeLetterSequence();
      paramTokeniser.dataBuffer.append(str.toLowerCase());
      paramTokeniser.emit(str);
      return;
    }
    char c = paramCharacterReader.consume();
    switch (c)
    {
    default:
      paramCharacterReader.unconsume();
      paramTokeniser.transition(paramTokeniserState2);
      break;
    case '\t':
    case '\n':
    case '\f':
    case '\r':
    case ' ':
    case '/':
    case '>':
      if (paramTokeniser.dataBuffer.toString().equals("script"))
        paramTokeniser.transition(paramTokeniserState1);
      else
        paramTokeniser.transition(paramTokeniserState2);
      paramTokeniser.emit(c);
    }
  }

  private static final void handleDataEndTag(Tokeniser paramTokeniser, CharacterReader paramCharacterReader, TokeniserState paramTokeniserState)
  {
    if (paramCharacterReader.matchesLetter())
    {
      String str = paramCharacterReader.consumeLetterSequence();
      paramTokeniser.tagPending.appendTagName(str.toLowerCase());
      paramTokeniser.dataBuffer.append(str);
      return;
    }
    boolean bool = paramTokeniser.isAppropriateEndTagToken();
    int i = 1;
    if ((bool) && (!paramCharacterReader.isEmpty()))
    {
      char c = paramCharacterReader.consume();
      switch (c)
      {
      default:
        paramTokeniser.dataBuffer.append(c);
        break;
      case '>':
        paramTokeniser.emitTagPending();
        paramTokeniser.transition(Data);
        break;
      case '/':
        paramTokeniser.transition(SelfClosingStartTag);
        break;
      case '\t':
      case '\n':
      case '\f':
      case '\r':
      case ' ':
        paramTokeniser.transition(BeforeAttributeName);
      }
      i = 0;
    }
    if (i != 0)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("</");
      localStringBuilder.append(paramTokeniser.dataBuffer.toString());
      paramTokeniser.emit(localStringBuilder.toString());
      paramTokeniser.transition(paramTokeniserState);
    }
  }

  abstract void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.parser.TokeniserState
 * JD-Core Version:    0.6.1
 */