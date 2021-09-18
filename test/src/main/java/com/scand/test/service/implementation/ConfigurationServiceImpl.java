package com.scand.test.service.implementation;

import com.scand.test.model.Configuration;
import com.scand.test.repository.ConfigurationRepository;
import com.scand.test.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationServiceImpl implements ConfigurationService
{
    private final ConfigurationRepository configurationRepository;

    @Autowired
    public ConfigurationServiceImpl(ConfigurationRepository configurationRepository)
    {
        this.configurationRepository = configurationRepository;
    }

    @Override
    public Configuration findById(String id)
    {
        return configurationRepository.findById(id).get();
    }
}
