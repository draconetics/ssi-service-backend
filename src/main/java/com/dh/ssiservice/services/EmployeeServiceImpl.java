/**
 * @author: Edson A. Terceros T.
 */

package com.dh.ssiservice.services;

import com.dh.ssiservice.model.Employee;
import com.dh.ssiservice.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class EmployeeServiceImpl extends GenericServiceImpl<Employee> implements EmployeeService {
    private static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    protected CrudRepository<Employee, Long> getRepository() {
        return employeeRepository;
    }

    @Override
    public void saveImage(Long id, InputStream file) {
        Employee employeePersisted = findById(id);
        System.out.println(employeePersisted.getFirstName());
        try {
            Byte[] bytes = ImageUtils.inputStreamToByteArray(file);
            employeePersisted.setImage(bytes);
            getRepository().save(employeePersisted);
        } catch (IOException e) {
            logger.error("Error reading file", e);
            e.printStackTrace();
        }
    }
}