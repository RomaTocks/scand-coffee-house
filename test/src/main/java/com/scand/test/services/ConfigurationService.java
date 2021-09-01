package com.scand.test.services;

import com.scand.test.models.Configuration;

public interface ConfigurationService
{
    Configuration findById(String id);
}
