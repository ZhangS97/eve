package com.demo.web.bundle.universe.model.service.impl;

import com.demo.web.bundle.universe.entity.Region;
import com.demo.web.bundle.universe.model.dao.RegionDao;
import com.demo.web.bundle.universe.model.service.RegionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Region)表服务实现类
 *
 * @author makejava
 * @since 2020-06-03 14:17:53
 */
@Service("regionsService")
public class RegionServiceImpl implements RegionService
{
    @Resource
    private RegionDao dao;

    public List<String> getHighSecureRegionsID()
    {
        Sort sort = Sort.by(Sort.Direction.ASC, "regionId");
        Pageable pageable = PageRequest.of(0, 67, sort);
        return dao.findAllHighSecureRegion(pageable).getContent();
    }

    @Override
    public List<Region> findAll()
    {
        return dao.findAll();
    }

    @Override
    public void save(Region region)
    {
        dao.save(region);
    }
}