package com.demo.component.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageableQuery extends Page implements Pageable, Serializable
{

    protected int total = 0;// 总的记录数

    protected int totalPage = 0;// 总的页数

    protected int begin = DEFAULT_BEGIN; // 分页查询起始值

    protected int offset = DEFAULT_OFFSET; // 前端设置的每页查询数量

    protected int limit = DEFAULT_LIMIT; // 每页查询多少条 如果小于等于0则不限制

    protected Object items; //结果集

    protected String keyword; // 搜索字符串

    protected boolean unlimited = false;

    protected boolean pageable = true; // 是否支持分页

    @JsonIgnore
    protected PageableOptions options;

    @JsonIgnore
    protected String within;

    @JsonIgnore
    protected String without = null;

    @JsonIgnore
    protected List<String> withinList;

    public PageableQuery()
    {
    }

    public PageableQuery(PageableOptions options)
    {
        this.options = options;
    }

    public PageableQuery reset()
    {
        this.total = 0;
        this.page = 1;
        this.offset = DEFAULT_OFFSET;
        this.begin = DEFAULT_BEGIN;
        this.limit = DEFAULT_LIMIT;
        return this;
    }
}
