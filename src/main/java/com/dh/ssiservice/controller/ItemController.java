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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

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

    public ItemController(ItemService itemService, SubCategoryService subCategoryService) {
        this.itemService = itemService;
        this.subCategoryService = subCategoryService;
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
    public Response getItemsById(@PathParam("id") @NotNull Long id) {
        Item item = itemService.findById(id);
        return Response.ok(new ItemCommand(item)).build();
    }

    @POST
    public Response saveItem(ItemCommand item) {
        System.out.println(item);
        Item model = item.toDomain();
        model.setSubCategory(subCategoryService.findById(item.getSubCategoryId()));
        Item itemPersisted = itemService.save(model);
        return Response.ok(new ItemCommand(itemPersisted)).build();
    }

    @PUT
    public Response updateItem(Item item) {
        Item itemPersisted = itemService.save(item);
        return Response.ok(new ItemCommand(itemPersisted)).build();
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
    @Path("/page/{page}")
    public Response getItemsRandom(@PathParam("page") @NotNull Integer page){
        Page<ItemCommand> itemPaging = itemService.getRandomItemsPageable(new PageRequest(page, 2));
        return Response.ok(itemPaging).build();
    }
}