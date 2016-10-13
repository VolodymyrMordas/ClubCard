package com.clubcard.resources.provider;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Provider
public class SecurityInterceptor implements ContainerRequestFilter
{

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED).build() ;//("Access denied for this resource", 401, new Headers<Object>());;
    private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN).build();//"Nobody can access this resource",403
    private static final Response SERVER_ERROR = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();//("INTERNAL SERVER ERROR", 500, new Headers<Object>());;
//

    @Context
    private ResourceInfo resourceInfo;

    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        List<Annotation> annotations = Arrays
                .asList(resourceInfo.getResourceMethod().getDeclaredAnnotations());
        Iterator<Annotation> iterator = annotations.iterator();
        while (iterator.hasNext()){
            Annotation annotation = iterator.next();
            System.out.println("-->"+annotation.toString()+"<--");
        }
//        //Access allowed for all
//        if( ! method.isAnnotationPresent(PermitAll.class))
//        {
//            //Access denied for all
//            if(method.isAnnotationPresent(DenyAll.class))
//            {
//                containerRequestContext.abortWith(ACCESS_FORBIDDEN);
//                return;
//            }
//
//            //Get request headers
//            final MultivaluedMap<String, String> headers = containerRequestContext.getHeaders();
//
//            //Fetch authorization header
//            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
//
//            //If no authorization information present; block access
//            if(authorization == null || authorization.isEmpty())
//            {
//                containerRequestContext.abortWith(ACCESS_DENIED);
//                return;
//            }
//
//            //Get encoded username and password
//            final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
//
//            //Decode username and password
//            String usernameAndPassword = null;
////            try {
//                usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));
////            } catch (IOException e) {
////                containerRequestContext.abortWith(SERVER_ERROR);
////                return;
////            }
//
//            //Split username and password tokens
//            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
//            final String username = tokenizer.nextToken();
//            final String password = tokenizer.nextToken();
//
//            //Verifying Username and password
//            System.out.println(username);
//            System.out.println(password);
//
//            //Verify user access
//            if(method.isAnnotationPresent(RolesAllowed.class))
//            {
//                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
//                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
//
//                //Is user valid?
//                if( ! isUserAllowed(username, password, rolesSet))
//                {
//                    containerRequestContext.abortWith(ACCESS_DENIED);
//                    return;
//                }
//            }
//        }
    }


    private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet)
    {
        boolean isAllowed = false;

        //Step 1. Fetch password from database and match with password in argument
        //If both match then get the defined role for user from database and continue; else return isAllowed [false]
        //Access the database and do this part yourself
        //String userRole = userMgr.getUserRole(username);
        String userRole = "ADMIN";

        //Step 2. Verify user role
        if(rolesSet.contains(userRole))
        {
            isAllowed = true;
        }
        return isAllowed;
    }
}