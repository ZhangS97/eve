package com.demo.component.query;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class PageableOptions
{
    private String domain;

    private String uri = null;  // 分页的URI地址

    private boolean showNext = true;  // 是否显示下一页

    private boolean showPrev = true;  // 是否显示上一页

    private boolean showLast = true;  // 是否显示最后一页

    private boolean showFirst = true; // 是否显示第一页

    private boolean showTotal = true; // 是否显示总数

    private boolean showTotalPage = true; // 是否显示总页数

    private boolean showCurrent = true;  // 是否显示当前第几页

    private int leftShowNum = 5;   // 左边显示的页数

    private String middleHolder = "..."; // 中间的占位符

    private int rightShowNum = 5;  // 右边显示的页数

    private String containerId = null;  // 分页的容器易元素ID

    private String containerClass = null; // 分页的容器元素class

    private String navId = null; // 分页导航的ID

    private String navClass = null; // 分页导航的class

    private Map<String, String> parameters = new LinkedHashMap<String, String>();

    public PageableOptions()
    {
    }

    public PageableOptions(String uri)
    {
        this.uri = uri;
    }
}
