package com.demo.component.tree;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
public class TreeNode implements Tree, Comparable<TreeNode>
{
    @Nullable
    protected String parentId;

    protected int depth;

    protected int ordered;

    protected boolean current;

    @Nullable
    protected List children;

    @Nullable
    @JsonIgnore
    protected Tree parent;

    @Override
    public int compareTo(TreeNode o)
    {
        int v = (null != o) ? (o.getDepth() + getDepth()) : getDepth();
        return v;
    }
}
