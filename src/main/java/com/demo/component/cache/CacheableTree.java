package com.demo.component.cache;

public class CacheableTree extends Cacheable
{

    public String fetchAllChildrenById(String id)
    {
        return null;
    }

    public String fetchAllParentsById(String id)
    {
        return null;
    }

    public void saveOneTree(String id, String selfVal, String childrenId,
            String childrenVal, String parentId, String parentVal)
    {
        //保存当前完整记录
        cacheManager.saveOne(new CacheItem(
                id,
                selfVal));
        //保存与当前id相对于的children id
        cacheManager.saveOne(new CacheItem(
                childrenId,
                childrenVal));
        //保存与当前id相对于的parent id
        cacheManager.saveOne(new CacheItem(
                parentId,
                parentVal));

    }

}
