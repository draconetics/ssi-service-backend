/**
 * (C) 2017 Agilysys NV, LLC.  All Rights Reserved.  Confidential Information of Agilysys NV, LLC.
 */
package com.dh.ssiservice.dao;


import com.dh.ssiservice.model.Contract;
import com.dh.ssiservice.model.Employee;
import com.dh.ssiservice.model.ModelBase;
import org.apache.tomcat.util.codec.binary.Base64;

public class EmployeeCommand extends ModelBase {

    private String firstName;
    private String lastName;
    private String name;
    private String image;
    private String jobPosition;
    private String jobCode;
    private Boolean featured;
    private String jobDescription;

    public EmployeeCommand() {
    }

    public EmployeeCommand(Employee employee) {
        setId(employee.getId());
        setVersion(employee.getVersion());
        setCreatedOn(employee.getCreatedOn());
        setUpdatedOn(employee.getUpdatedOn());
        firstName = employee.getFirstName();
        lastName = employee.getLastName();
        for (Contract contract : employee.getContracts()) {
            jobPosition = contract.getPosition().getName();
            jobCode = contract.getPosition().getName();
        }
        featured = true;
        setImageBase64(employee);
        jobDescription = "Descripcion de job";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    private void setImageBase64(Employee employee) {
        System.out.println("***************************************");
        if (employee.getImage() != null) {
            byte[] bytes = new byte[employee.getImage().length];
            for (int i = 0; i < employee.getImage().length; i++) {
                bytes[i] = employee.getImage()[i];
                //System.out.println(employee.getImage()[i]);
            }
            String imageStr = Base64.encodeBase64String(bytes);
            this.setImage(imageStr);
        }
    }

    public String getName() {
        return getFirstName() + " " + getLastName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setFirstName(getFirstName());
        employee.setLastName(getLastName());
        employee.setId(getId());
        employee.setVersion(getVersion());
        employee.setCreatedOn(getCreatedOn());
        employee.setUpdatedOn(getUpdatedOn());
        
        
        //employee.setImage((Byte[]) Base64.decodeBase64(getImage()));

//        if(getImage() != null){
//            System.out.println("decoding !!!!");
//            //Byte [] image = org.yaml.snakeyaml.util.ArrayUtils.toObject(Base64.decodeBase64(getImage()));
//            employee.setImage(decodeString(getImage()));
//            System.out.println("decoded");
//        }

        return employee;
    }

    public Byte[] decodeString(String image){


            byte[] bytes = Base64.decodeBase64(image);
            Byte [] b = new Byte[bytes.length];
            for(int i=0; i< bytes.length;i++){
                    b[i] = Byte.valueOf(b[i]);
            }
            return b;


    }
}
