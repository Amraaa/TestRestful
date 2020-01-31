package services;

import com.google.gson.Gson;
import helper.UserHelper;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pojos.UserTbl;

/**
 * REST Web Service
 *
 * @author topA
 */
@Path("user")
public class UserResource {

    private UserHelper helper;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
        helper = new UserHelper();
    }

    /**
     * Retrieves representation of an instance of services.UserResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of UserResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @GET
    @Path("getUserList")
    @Produces("application/json")
    public String getUserList() {

        Gson gson = new Gson();
        List<UserTbl> l = null;
        try {
            l = new ArrayList(helper.userList());

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return "{\"user\":" + gson.toJson(l) + "}";
    }

    @GET
    @Path("getUserById")
    @Produces("application/json")
    public String getUserById(@QueryParam("idUser") String idUser) {

        Gson gson = new Gson();

        UserTbl ut = null;
        try {
            List<UserTbl> l = new ArrayList(helper.getUserbyId(Integer.parseInt(idUser)));
            for (int i = 0; i < l.size(); i++) {
                ut = new UserTbl();
                ut.setAdress(l.get(i).getAdress());
                ut.setGender(l.get(i).getGender());
                ut.setIdUser(l.get(i).getIdUser());
                ut.setName(l.get(i).getName());

            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return "{\"user\":" + gson.toJson(ut) + "}";
    }

    @POST
    @Path("saveDataUser")
    @Consumes("application/json")
    public Response saveDataUser(String data) {
        Gson gson = new Gson();
        UserTbl ut = gson.fromJson(data, UserTbl.class);
        try {
            helper.saveDataUser(ut.getIdUser(), ut.getName(), ut.getGender(), ut.getAdress());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Response.status(200).entity(ut).build();

    }

    @PUT
    @Path("updateName")
    @Consumes("application/json")
    public void updateName(@QueryParam("idUser") String idUser, @QueryParam("name") String name) {
        try {
            helper.updateName(Integer.parseInt(idUser), name);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @DELETE
    @Path("deleteUser")
    @Consumes("application/json")
    public void deleteUser(@QueryParam("idUser") String idUser) {
        try {
            helper.deleteUser(Integer.parseInt(idUser));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
