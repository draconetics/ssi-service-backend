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
        Category cpCategory = new Category();
        cpCategory.setCode("IS-CP");
        cpCategory.setName("Clothing Protection");
        categoryRepository.save(cpCategory);

        Category epCategory = new Category();
        epCategory.setCode("IS-EP");
        epCategory.setName("Equipment Protection");
        categoryRepository.save(epCategory);

        Category ssCategory = new Category();
        ssCategory.setCode("IS-SS");
        ssCategory.setName("Security Shoes");
        categoryRepository.save(ssCategory);

        // Hand protection subcategory
        SubCategory safetySubCategory = new SubCategory();
        safetySubCategory.setCategory(epCategory);
        safetySubCategory.setCode("SIS-HP01");
        safetySubCategory.setName("Hand Protection");
        subCategoryRepository.save(safetySubCategory);

        // jacket protection subcategory
        SubCategory rawMaterialSubCategory = new SubCategory();
        rawMaterialSubCategory.setCategory(cpCategory);
        rawMaterialSubCategory.setCode("SIS-CP01");
        rawMaterialSubCategory.setName("Jacket Proteccion");
        subCategoryRepository.save(rawMaterialSubCategory);

        // jacket protection subcategory
        SubCategory shoesSubCategory = new SubCategory();
        shoesSubCategory.setCategory(ssCategory);
        shoesSubCategory.setCode("SIS-SS01");
        shoesSubCategory.setName("Boot Proteccion");
        subCategoryRepository.save(shoesSubCategory);


        // Helmet Item
        Item helmet = new Item();
        helmet.setCode("HELMET-TONKA");
        helmet.setName("Occunomix RB405 Classic Regular Length Winter Liner Twill (Each)\n" +
                "$9.09");
        helmet.setSubCategory(safetySubCategory);

        itemRepository.save(helmet);

        // BOOT Item
        Item boot = new Item();
        boot.setCode("BOOT-VOLVO-01");
        boot.setName("HF 44230 Black Unisex Value Boots (8\"-13\")");
        boot.setSubCategory(shoesSubCategory);
        itemRepository.save(boot);

//***************************************************
 //        jacket Item
        Item jacket = new Item();
        jacket.setCode("JACKET-VOLVO-01");
        jacket.setName("Radians SV55-2Z Class 2 Woven Two Tone Engineer's Safety Vest");
        jacket.setSubCategory(rawMaterialSubCategory);
        itemRepository.save(jacket);

  //       BOOT Item
        Item fallteck = new Item();
        fallteck.setCode("HALTECK-SCANNIA-01");
        fallteck.setName("FallTech Contractors Full Body Harness / 7015");
        fallteck.setSubCategory(safetySubCategory);
        itemRepository.save(fallteck);




        //rain jacket
        
        Item rainJacket = new Item();
        rainJacket.setCode("RADIAN RW32");
        rainJacket.setName("Radians RW32-3Z1Y Heavy Duty Rip Stop Waterproof Rain Jacket\r $72.34");
        rainJacket.setSubCategory(rawMaterialSubCategory);
        itemRepository.save(fallteck);
        
        
        
        
        
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
