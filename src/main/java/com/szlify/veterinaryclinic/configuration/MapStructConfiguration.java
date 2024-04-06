package com.szlify.veterinaryclinic.configuration;

import com.szlify.veterinaryclinic.mapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfiguration {

    @Bean
    public PetMapper petMapper() {
        return new PetMapperImpl();
    }

    @Bean
    public OwnerMapper ownerMapper() {
        return new OwnerMapperImpl();
    }

    @Bean
    public VetMapper vetMapper() {
        return new VetMapperImpl();
    }

    @Bean
    public VisitMapper visitMapper() {
        return new VisitMapperImpl();
    }
}
