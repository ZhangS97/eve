package com.demo.service;

import java.util.List;

public interface DogmaService
{
    /*
     *Attributes service begin
     *
     * */
    void updateAttributes();

    List<String> getAllAttributeIds();

    /*
     *Effects service begin
     *
     * */
    void updateEffects();

    /**
     *
     */
    List<String> getAllEffectIds();
}
