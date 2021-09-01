package com.scand.test.services.implementations;

import com.scand.test.models.Configuration;
import com.scand.test.repositories.ConfigurationRepository;
import com.scand.test.services.ConfigurationService;
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
        if(configurationRepository.findById(id).isPresent()) return configurationRepository.findById(id).get();
        else return null;
    }
}
