package com.demo.service;

import java.util.List;

public interface UniverseService
{

    void updateUniverseInfo();

    void updateTypes();

    void updateRegions();

    void updateConstellations();

    void updateSystems();

    void updateStations();

    List<String> getHighSecureRegionsID();

}
