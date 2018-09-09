/**
 * @author: Edson A. Terceros T.
 */

package com.dh.ssiservice.bootstrap;

import com.dh.ssiservice.model.*;
import com.dh.ssiservice.repositories.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private CategoryRepository categoryRepository;
    private ContractRepository contractRepository;
    private EmployeeRepository employeeRepository;
    private ItemRepository itemRepository;
    private PositionRepository positionRepository;
    private SubCategoryRepository subCategoryRepository;

    public DevBootstrap(CategoryRepository categoryRepository, ContractRepository contractRepository, EmployeeRepository employeeRepository, ItemRepository itemRepository, PositionRepository positionRepository, SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.contractRepository = contractRepository;
        this.employeeRepository = employeeRepository;
        this.itemRepository = itemRepository;
        this.positionRepository = positionRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Category eppCategory = new Category();
        eppCategory.setCode("EPPAA");
        eppCategory.setName("EPP");

        categoryRepository.save(eppCategory);

//        RES category
        Category resourceCategory = new Category();
        resourceCategory.setCode("RES");
        resourceCategory.setName("RESOURCE");
        categoryRepository.save(resourceCategory);

        // safety subcategory
        SubCategory safetySubCategory = new SubCategory();
        safetySubCategory.setCategory(eppCategory);
        safetySubCategory.setCode("SAF");
        safetySubCategory.setName("SAFETY");

        subCategoryRepository.save(safetySubCategory);

        // raw material subcategory
        SubCategory rawMaterialSubCategory = new SubCategory();
        rawMaterialSubCategory.setCategory(resourceCategory);
        rawMaterialSubCategory.setCode("RM");
        rawMaterialSubCategory.setName("RAW MATERIAL");

        subCategoryRepository.save(rawMaterialSubCategory);

        // Helmet Item
        Item helmet = new Item();
        helmet.setCode("HEL");
        helmet.setName("HELMET");
        helmet.setSubCategory(safetySubCategory);

        itemRepository.save(helmet);

        // ink Item
        Item ink = new Item();
        ink.setCode("INK");
        ink.setName("INK");
        ink.setSubCategory(rawMaterialSubCategory);
        itemRepository.save(ink);

        // John Employee
        Employee john = new Employee();
        john.setFirstName("John");
        john.setLastName("Doe");

        // Position
        Position position = new Position();
        position.setName("OPERATIVE");
        positionRepository.save(position);

        // contract
        Contract contract = new Contract();
        contract.setEmployee(john);
        contract.setInitDate(new Date(2010, 1, 1));
        contract.setPosition(position);

        john.getContracts().add(contract);
        employeeRepository.save(john);

        contract.setInitDate(new Date(0, 1, 1));

        contractRepository.save(contract);

    }

}
