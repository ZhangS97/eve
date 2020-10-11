package com.demo.component.query;

import lombok.Data;

@Data
public class Page
{
    protected int page;

    public int getPage()
    {
        if (this.page <= 0)
        {
            this.page = 1;
        }
        return this.page;
    }
}
