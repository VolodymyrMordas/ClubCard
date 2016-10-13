package com.clubcard.resources;

import com.clubcard.ejb.repository.CompanyRepository;
import com.clubcard.entities.Company;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Volodymyr Mordas <volodymyrmordas@gmail.com>
 */
@Path(value = "company")
@Produces(MediaType.APPLICATION_JSON)
public class CompanyResource {

    @EJB
    CompanyRepository companyRepository;

    /**
     *
     * @return Response of user entity
     */
    @GET
    @Path("/{id}")
    public Response profile(@PathParam("id") Long companyId){
        Company company = companyRepository.find(companyId);
        return Response.ok()
                .entity(company).build();
    }

    /**
     *
     * @return
     */
    @PUT
    @Path("/add")
    public Response add(){
        return Response.ok()
                .header("user/update", "ok").build();
    }


    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long companyId){
        companyRepository.remove(companyId);
        return Response.ok().build();
    }

    @POST
    @Path("/update")
    public Response update(){
        return Response.ok().build();
    }

}