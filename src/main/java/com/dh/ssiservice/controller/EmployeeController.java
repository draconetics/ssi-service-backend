/**
 * @author: Edson A. Terceros T.
 */

package com.dh.ssiservice.controller;

import com.dh.ssiservice.dao.EmployeeCommand;
import com.dh.ssiservice.model.Employee;
import com.dh.ssiservice.services.EmployeeService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Controller
@CrossOrigin
public class EmployeeController {
    private EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GET
    public Response getEmployees() {
        List<EmployeeCommand> employeeList = new ArrayList<>();
        service.findAll().forEach(employee -> {
            employeeList.add(new EmployeeCommand(employee));
        });
        return Response.ok(employeeList).build();
    }

    @GET
    @Path("/{id}")
    public Response getEmployeeById(@PathParam("id") long id) {
        Employee employee = service.findById(id);
        return Response.ok(new EmployeeCommand(employee)).build();
    }

    @OPTIONS
    public Response prefligth() {
        return Response.ok().build();
    }

    @POST
    public EmployeeCommand addEmployee(EmployeeCommand employeeCommand) {
        Employee employee = service.save(employeeCommand.toEmployee());
        return new EmployeeCommand(employee);
    }

    @PUT
    public EmployeeCommand updateEmployee(EmployeeCommand employeeCommand) {
        Employee e = service.findById(employeeCommand.getId());
        e.setFirstName(employeeCommand.getFirstName());
        e.setLastName(employeeCommand.getLastName());
        service.save(e);
        return new EmployeeCommand(e);
//        Employee employee = service.save(employeeCommand.toEmployee());
//        return new EmployeeCommand(employee);
    }

    @DELETE
    @Path("/{id}")
    public void deleteEmployee(@PathParam("id") long id) {
        service.deleteById(id);
    }

    @Path("/{id}/image")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadFile(@PathParam("id") String id,
                               @FormDataParam("file") InputStream file,
                               @FormDataParam("file") FormDataContentDisposition fileDisposition) {
        service.saveImage(Long.valueOf(id), file);
        return Response.ok("Data uploaded successfully !!").build();
    }

    /*
    https://www.getpostman.com/collections/cb9764af6c5d5bcaa0c9
    */
}
