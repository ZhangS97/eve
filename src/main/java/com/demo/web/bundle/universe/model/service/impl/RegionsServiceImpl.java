package com.demo.web.bundle.universe.model.service.impl;

import com.demo.web.bundle.universe.entity.Regions;
import com.demo.web.bundle.universe.model.dao.RegionsDao;
import com.demo.web.bundle.universe.model.service.RegionsService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Regions)表服务实现类
 *
 * @author makejava
 * @since 2020-06-03 14:17:53
 */
@Service("regionsService")
public class RegionsServiceImpl implements RegionsService
{
    @Resource
    private RegionsDao dao;

    public List<String> showHighSecureRegionsID()
    {
        Sort sort = Sort.by(Sort.Direction.ASC, "regionId");
        Pageable pageable = PageRequest.of(0, 67, sort);
        return dao.findAllHighSecureRegion(pageable).getContent();
    }

    @Override
    public void save(Regions regions)
    {
        dao.save(regions);
    }
}