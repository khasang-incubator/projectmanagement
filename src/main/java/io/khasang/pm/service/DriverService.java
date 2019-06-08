package io.khasang.pm.service;

import io.khasang.pm.entity.Driver;
import java.util.List;

public interface DriverService {

    /**
     *
     * required for adding driver to db
     *
     * @param driver
     * @return added driver
     */
    public Driver add(Driver driver);
    /**
     *
     * getting specify driver by id
     *
     * @param id
     * @return driver by id
     */
    public Driver getById(long id);

    /**
     * gettint all drivers
     *
     * @return list of all drivers
     */
    public List<Driver> getAll();

    /**
     *  update driver in db
     *
     * @return updated driver
     */
    public Driver update(Driver driver);

    /**
     *  save or update driver in db
     *
     * @return saved or updated driver
     */
    public Driver saveOrUpdate(Driver driver);
}
