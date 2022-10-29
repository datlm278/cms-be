package com.example.cmsbe.common.constant;

public class CMSConstant {

    //Constant url request mapping
    public static final String PREFIX_API_URL = "api/v1/cms";

    //Constant for status in CMS_TICKET
    public static final Long NOT_SOLD_YET = 1L;
    public static final Long SOLD = -1L;

    //Constant for type of ticket in CMS_TICKET
    public static final Long ADULT = 1L;
    public static final Long STUDENT = 2L;
    public static final Long CHILDREN = 0L;
    public static final Long CHILDREN_UNDER_SIX = -1L;

    //Constant for status in CMS database
    public static final Long NORMAL_STATUS = 1L;
    public static final Long DELETE_STATUS = -1L;

    public static final int SEATS_OF_ROW = 20;
}
