/**
 * @author: Edson A. Terceros T.
 */

package com.dh.ssiservice.repositories;

import com.dh.ssiservice.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Optional<List<Employee>> findByFirstName(String firstName);
}
