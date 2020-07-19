package com.demo.component;

import lombok.Data;

@Data
public abstract class Cacheable
{

    private static final long serialVersionUID = -7876629741785837545L;

    public static String NAME = "";

    public static String KEY = "";

    public static String KEY_CHILDREN_ID = "";

    public static String KEY_PARENT_ID = "";

    public void doLoad()
    {

    }

}
