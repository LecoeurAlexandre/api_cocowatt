package com.example.config;


import com.example.domain.entity.Rating;
import com.example.domain.service.*;
import com.example.infrastructure.repository.impl.*;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class ApiRestInitializer implements ApplicationContextInitializer {

    private final ConfigurableApplicationContext infraContext;
    public ApiRestInitializer(ConfigurableApplicationContext infraContext) {
        this.infraContext = infraContext;
    }
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableListableBeanFactory registry = applicationContext.getBeanFactory();
        registry.registerSingleton("CarService", new CarServiceImpl(infraContext.getBean(CarRepositoryImpl.class)));
        registry.registerSingleton("RatingService", new RatingServiceImpl(infraContext.getBean(RatingRepositoryImpl.class),infraContext.getBean(UserRepositoryImpl.class)));
        registry.registerSingleton("UserService", new UserServiceImpl(infraContext.getBean(UserRepositoryImpl.class),infraContext.getBean(CarRepositoryImpl.class)));
        registry.registerSingleton("TripService", new TripServiceImpl(infraContext.getBean(TripRepositoryImpl.class), infraContext.getBean(UserRepositoryImpl.class)));
        registry.registerSingleton("ReservationService", new ReservationServiceImpl(infraContext.getBean(ReservationRepositoryImpl.class)));
    }
}
