package com.clubcard.resources;

import com.clubcard.ejb.services.UserService;
import com.clubcard.entities.Socnet;
import com.clubcard.entities.User;
import com.clubcard.rest.ApplicationException;
import com.clubcard.rest.model.RequestModel;
import com.clubcard.rest.model.ResponseModel;
import com.clubcard.rest.model.SuccessResponse;
import com.sun.istack.internal.NotNull;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path(value = "user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @EJB
    UserService userService;

    @POST
//    @Path("/")
    public Response index(@NotNull RequestModel<User> requestModel) throws ApplicationException {

        User authUser = this.processRequest(requestModel);

        ResponseModel responseModel = new SuccessResponse();
        responseModel.setData(User.class, authUser);

        return Response.ok()
                .entity(responseModel).build();
    }

    @GET
    @Path("/list")
    public Response list(@NotNull RequestModel<User> requestModel) throws ApplicationException {

        List<User> userList = userService.getAll();
        ResponseModel responseModel = new SuccessResponse();

        responseModel.setData(User.class, userList);
        return Response.ok()
                .entity(responseModel).build();
    }

    @PUT
//    @Path("/")
    public Response add(@NotNull RequestModel<User> requestModel) throws ApplicationException {
        if (requestModel == null) {
            throw new ApplicationException(ApplicationException.Error.E_EMPT_RQST_BODY);
        }

        List<User> userList = requestModel.getData(User.class);

        ResponseModel<List<User>> responseModel = new SuccessResponse();

        for (final User user : userList) {
            if ((user.getSocnetUid() == null)
                    || user.getSocnetUid().isEmpty()) {
                throw new ApplicationException(ApplicationException.Error.E_REQUEST_WRNG);
            }

            if (user.getSocnetType() == null) {
                throw new ApplicationException(ApplicationException.Error.E_REQUEST_WRNG);
            }

            final User dbUser = userService.find(user.getSocnetUid(), user.getSocnetType());
            if (dbUser == null) {
                if (user.getSocnetType().equals(Socnet.T_EMAIL)) {
                    addUserEmail(user);
                } else if (user.getSocnetType().equals(Socnet.T_FACEBOOK)
                        || user.getSocnetType().equals(Socnet.T_GOOGLE)) {
                    addSocnetUser(user);
                } else {
                    throw new ApplicationException(ApplicationException.Error.E_REQUEST_WRNG);
                }

                responseModel.setData(User.class, new ArrayList(){
                    {
                        add(user);
                    }
                });
            } else {
                if (user.getSocnetType().equals(Socnet.T_EMAIL)) {
                    if (!dbUser.checkPassword(user.getPassword())) {
                        throw new ApplicationException(ApplicationException.Error.E_ISNOTAUTH);
                    }
                }

                responseModel.setData(User.class, new ArrayList(){
                    {
                        add(dbUser);
                    }
                });
            }

//            responseModel.setData(User.class, new User());
        }

        return Response.ok().entity(responseModel).build();
    }

    private void addSocnetUser(User user) {
        System.out.println("||| addSocnetUser");
        userService.persist(user);
    }

    private void addUserEmail(User user) throws ApplicationException {

        if (user.getPassword().isEmpty()) {
            throw new ApplicationException(ApplicationException.Error.E_REQUEST_WRNG);
        }

        String salt = String.valueOf(System.nanoTime());

        user.setPassword(User.generatePasswordHash(salt, user.getPassword()));
        user.setSalt(salt);

        userService.persist(user);
    }

    @DELETE
    @Path("/delete")
    public Response delete(@NotNull RequestModel<User> requestModel) throws ApplicationException {

        User authUser = this.processRequest(requestModel);
        authUser.setStatus(User.S_DELETED);
        userService.merge(authUser);

        return Response.ok()
                .entity(new SuccessResponse()).build();
    }

    @POST
    @Path("/update")
    public Response update() {
        return Response.ok().build();
    }

    private User processRequest(@NotNull RequestModel<User> requestModel) throws ApplicationException {
        if (requestModel == null) {
            throw new ApplicationException(ApplicationException.Error.E_EMPT_RQST_BODY);
        }

        String authKey = requestModel.getAuthorization().getAuthKey();
        if (authKey == null) {
            throw new ApplicationException(ApplicationException.Error.E_PERM_DENY);
        }


        User authUser = userService.findByAuthKey(authKey);
        if (authUser == null) {
            throw new ApplicationException(ApplicationException.Error.E_PERM_DENY);
        }

        return authUser;
    }

    @POST
    @Path("/confirm")
    public Response confirm(@NotNull RequestModel<User> user) {

        return Response.ok().build();
    }
}