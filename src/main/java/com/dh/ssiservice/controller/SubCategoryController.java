package com.dh.ssiservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dh.ssiservice.dao.ItemCommand;
import com.dh.ssiservice.dao.SubCategoryCommand;
import com.dh.ssiservice.model.Category;
import com.dh.ssiservice.model.Item;
import com.dh.ssiservice.model.SubCategory;
import com.dh.ssiservice.services.CategoryService;
import com.dh.ssiservice.services.SubCategoryService;

@Controller
@Path("/subcategories")
@Produces("application/json")
@CrossOrigin
public class SubCategoryController {
	
	private SubCategoryService subCategoryService;
	private CategoryService categoryService;
	
	public SubCategoryController(SubCategoryService subCategoryService, CategoryService categoryService) {
		// TODO Auto-generated constructor stub
		this.subCategoryService = subCategoryService;
		this.categoryService = categoryService;
	}

    @GET
    public Response getSubCategories() {
        List<SubCategory> subCategories= subCategoryService.findAll();
        List<SubCategoryCommand> resp = new ArrayList<>();
        for(SubCategory sub:subCategories) {
        	resp.add(new SubCategoryCommand(sub));
        }
        return Response.ok(resp).build();
    }

    @POST
    public Response saveItem(SubCategoryCommand subCategoryCommand) {
        
    	SubCategory model = subCategoryCommand.toDomain();
        model.setCategory(categoryService.findById(subCategoryCommand.getCategoryId()));

        SubCategory subCategory = subCategoryService.save(model);
        return Response.ok(new SubCategoryCommand(subCategory)).build();
    }

    @PUT
    public Response updateItem(SubCategoryCommand subCategoryCommand) {
    	SubCategory sub = subCategoryService.findById(subCategoryCommand.getId());
    	sub.setName(subCategoryCommand.getName());
    	sub.setCode(subCategoryCommand.getCode());
    	sub.setCategory(categoryService.findById(subCategoryCommand.getCategoryId()));
        SubCategory resp = subCategoryService.save(sub);
        return Response.ok(new SubCategoryCommand(resp)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSubCategory(@PathParam("id") String id) {
        subCategoryService.deleteById(Long.valueOf(id));
        return Response.ok().build();
    }
    
    @GET
    @Path("/{id}")
    public Response getSubCategoryById(@PathParam("id") @NotNull Long id) {
        SubCategory resp = subCategoryService.findById(id);
        return Response.ok(new SubCategoryCommand(resp)).build();
    }
    
    @GET
    @Path("/cat/{id}")
    public Response getSubCategoriesByCategory(@PathParam("id") @NotNull Long id) {
        List<SubCategory> subCategories = subCategoryService.getSubCategoriesByCategory(id);
        List<SubCategoryCommand> resp = new ArrayList<>();
        for(SubCategory sub:subCategories) {
        	resp.add(new SubCategoryCommand(sub));
        }
        return Response.ok(resp).build();
    }

}
