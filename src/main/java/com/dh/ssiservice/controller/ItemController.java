/**
 * @author: Edson A. Terceros T.
 */

package com.dh.ssiservice.controller;

import com.dh.ssiservice.dao.ItemCommand;
import com.dh.ssiservice.model.Item;
import com.dh.ssiservice.services.ItemService;
import com.dh.ssiservice.services.SubCategoryService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@Path("/items")
@Produces("application/json")
@CrossOrigin
public class ItemController{

    private ItemService service;
    private SubCategoryService subCategoryService;

    public ItemController(ItemService service, SubCategoryService subCategoryService) {
        this.service = service;
        this.subCategoryService = subCategoryService;
    }

    @GET
    public Response getItems() {
        List<ItemCommand> items = new ArrayList<>();
        service.findAll().forEach(item -> {
            ItemCommand itemCommand = new ItemCommand(item);
            items.add(itemCommand);
        });
        return Response.ok(items).build();
    }

    @GET
    @Path("/{id}")
    public Response getItemsById(@PathParam("id") @NotNull Long id) {
        Item item = service.findById(id);
        return Response.ok(new ItemCommand(item)).build();
    }

    @POST
    public Response saveItem(ItemCommand item) {
        System.out.println(item);
        Item model = item.toDomain();
        model.setSubCategory(subCategoryService.findById(item.getSubCategoryId()));
        Item itemPersisted = service.save(model);
        return Response.ok(new ItemCommand(itemPersisted)).build();
    }

    @PUT
    public Response updateItem(Item item) {
        Item itemPersisted = service.save(item);
        return Response.ok(new ItemCommand(itemPersisted)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteItem(@PathParam("id") String id) {
        service.deleteById(Long.valueOf(id));
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
        service.saveImage(Long.valueOf(id), file);
        return Response.ok("Data uploaded successfully !!").build();
    }
}