package org.snippets.spring.ioc.overview.dependency.domain;

import org.springframework.core.io.Resource;

import java.util.Arrays;
import java.util.List;

public class User {
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", workCities=" + Arrays.toString(workCities) +
                ", lifeCities=" + lifeCities +
                ", configFileResource=" + configFileResource +
                '}';
    }

    private Long id;
    private String name;
    private City city;
    private City[] workCities;

    public List<City> getLifeCities() {
        return lifeCities;
    }

    public void setLifeCities(List<City> lifeCities) {
        this.lifeCities = lifeCities;
    }

    private List<City> lifeCities;
    private Resource configFileResource;

    public City[] getWorkCities() {
        return workCities;
    }

    public void setWorkCities(City[] workCities) {
        this.workCities = workCities;
    }

    public Resource getConfigFileResource() {
        return configFileResource;
    }

    public void setConfigFileResource(Resource configFileResource) {
        this.configFileResource = configFileResource;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static User createUser() {
        User user = new User();
        user.setId(34l);
        user.setName("ww");
        return user;
    }


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
