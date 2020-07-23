package com.demo.service;

import java.util.List;

public interface UniverseService
{

    void updateTypes();

    void updateRegions();

    void updateConstellations();

    void updateSystems();

    List<String> showHighSecureRegionsID();

    List<String> getAllTypeID();
}
