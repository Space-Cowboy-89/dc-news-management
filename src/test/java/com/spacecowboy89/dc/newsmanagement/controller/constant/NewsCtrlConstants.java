package com.spacecowboy89.dc.newsmanagement.controller.constant;

import java.time.LocalDateTime;

public class NewsCtrlConstants {
    public final static String NEWSCODE_SAMPLE_1 = "news_code_sample_111";
    public final static String TITLE_SAMPLE_1 = "title_sample_1";
    public final static String SUMMARY_SAMPLE_1 = "summary_sample_1";
    public final static LocalDateTime PUBLICATION_DATE_1 = LocalDateTime.now().minusDays(3l);
    public final static String CATEGORY_CODE_SAMPLE_1 = "category_code_sample_1";
    public final static String JOURNALIST_CODE_SAMPLE_1 = "jourmalist_code_sample_1";


    public final static String NEWSCODE_SAMPLE_2 = "news_code_sample_222";
    public final static String TITLE_SAMPLE_2 = "title_sample_2";
    public final static String SUMMARY_SAMPLE_2 = "summary_sample_2";
    public final static LocalDateTime PUBLICATION_DATE_2 = LocalDateTime.now().plusDays(1l);

    public final static String NEWSCODE_SAMPLE_3 = "news_code_sample_333";
    public final static String TITLE_SAMPLE_3 = "title_sample_3";
    public final static String SUMMARY_SAMPLE_3 = "summary_sample_3";
    public final static LocalDateTime PUBLICATION_DATE_3 = LocalDateTime.now().plusDays(4l);
}
