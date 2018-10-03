/**
 * @author: Edson A. Terceros T.
 */

package com.dh.ssiservice.controller;

import com.dh.ssiservice.dao.EmployeeCommand;
import com.dh.ssiservice.dao.ItemCommand;
import com.dh.ssiservice.exception.StudentNotFoundException;
import com.dh.ssiservice.model.Employee;
import com.dh.ssiservice.model.Item;
import com.dh.ssiservice.repositories.ItemRepository;
import com.dh.ssiservice.services.ItemService;
import com.dh.ssiservice.services.SubCategoryService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Path("/items")
@Produces("application/json")
@CrossOrigin
public class ItemController{

    private ItemService itemService;
    private SubCategoryService subCategoryService;
    
    private ItemRepository ir;

    public ItemController(ItemService itemService, SubCategoryService subCategoryService, ItemRepository ir) {
        this.itemService = itemService;
        this.subCategoryService = subCategoryService;
        this.ir = ir;
    }

    @GET
    public Response getItems() {
        List<ItemCommand> items = new ArrayList<>();
        itemService.findAll().forEach(item -> {
            ItemCommand itemCommand = new ItemCommand(item);
            items.add(itemCommand);
        });
        
      
        return Response.ok(items).build();
    }

    @GET
    @Path("/{id}")
    public Response getItemsById(@PathParam("id") @NotNull Long id){

    	Item item = itemService.findById(id);
    	if (item == null)
    	      throw new StudentNotFoundException("id-" + id);
//    	if(item == null) {
//    		throw new DataNotFoundException("hello word .................$$$$$$"+id);
//    	}

        return Response.ok(new ItemCommand(item)).build();
    }
//    
//	@ExceptionHandler(ItemException.class)
//	public ResponseEntity<ErrorMessage> exceptionHandler(Exception ex) {
//		ErrorMessage error = new ErrorMessage();
//		error.setErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value()+"");
//		//error.setMessage(ex.getMessage());
//		return new ResponseEntity<ErrorMessage>(error, HttpStatus.OK);
//	}

    @POST
    public Response saveItem(ItemCommand item) {
        //System.out.println(item);
        Item model = item.toDomain();
        model.setSubCategory(subCategoryService.findById(item.getSubCategoryId()));
        Item itemPersisted = itemService.save(model);
        return Response.ok(new ItemCommand(itemPersisted)).build();
    }

    @PUT
    public Response updateItem(ItemCommand itemCommand) {
        Item i = itemService.findById(itemCommand.getId());
        i.setName(itemCommand.getName());
        i.setCode(itemCommand.getCode());
        itemService.save(i);
        
        return Response.ok(new ItemCommand(i)).build();
        
    }

    @DELETE
    @Path("/{id}")
    public Response deleteItem(@PathParam("id") String id) {
        itemService.deleteById(Long.valueOf(id));
        return Response.ok().build();
    }

    @OPTIONS
    public Response prefligth() {
        return Response.ok().build();
    }

    @Path("/{id}/image")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadFile(@PathParam("id") String id,
                               @FormDataParam("file") InputStream file,
                               @FormDataParam("file") FormDataContentDisposition fileDisposition) {
        itemService.saveImage(Long.valueOf(id), file);
        return Response.ok("Data uploaded successfully !!").build();
    }

    @GET
    @Path("/page/{number}")
    public Response getItemsPerPage(@PathParam("number") Integer number) {
    	Page<Item> lista = itemService.findItemsRandom(new PageRequest(number,9));
        List<ItemCommand> items = new ArrayList();
        
        for (Item i: lista.getContent()) {
        	ItemCommand itemCommand = new ItemCommand(i);
            items.add(itemCommand);
		}
      
        return Response.ok(items).build();
    }
    
    @GET
    @Path("/sub/{number}/{subcat}")
    public Response getItemsBySubCategory(@PathParam("number") Integer number,
    										@PathParam("subcat") Integer subcat) {
    	Page<Item> lista = itemService.findItemBySubCategory(new PageRequest(number,1),Long.valueOf(subcat));
        List<ItemCommand> items = new ArrayList();
        
        for (Item i: lista.getContent()) {
        	ItemCommand itemCommand = new ItemCommand(i);
            items.add(itemCommand);
		}
      
        return Response.ok(items).build();
    }
    
    @GET
    @Path("/cat/{number}/{cat}")
    public Response getItemsByCategory(@PathParam("number") Integer number,
    										@PathParam("cat") Integer cat) {
    	Page<Item> lista = itemService.findItemByCategory(new PageRequest(number,1),Long.valueOf(cat));
        List<ItemCommand> items = new ArrayList();
        
        for (Item i: lista.getContent()) {
        	ItemCommand itemCommand = new ItemCommand(i);
            items.add(itemCommand);
		}
      
        return Response.ok(items).build();
    }
    
    @GET
    @Path("/search/{page}/{search}")
    public Response getItemsByCategory(@PathParam("search") String search,
    										@PathParam("page") Integer page) {
    	Page<Item> lista = itemService.findItemByString(new PageRequest(page,1),search);
        List<ItemCommand> items = new ArrayList();
        
        for (Item i: lista.getContent()) {
        	ItemCommand itemCommand = new ItemCommand(i);
            items.add(itemCommand);
		}
      
        return Response.ok(items).build();
    }

}