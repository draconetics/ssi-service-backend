/**
 * @author: Edson A. Terceros T.
 */

package com.dh.ssiservice.services;

import com.dh.ssiservice.model.Employee;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public interface EmployeeService extends GenericService<Employee> {
    void saveImage(Long id, InputStream inputStream);
}
