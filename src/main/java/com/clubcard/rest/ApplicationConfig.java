package com.clubcard.rest;

import com.clubcard.resources.CompanyResource;
import com.clubcard.resources.TokenEndpoint;
import com.clubcard.resources.UserResource;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ApplicationPath("api/v1")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> resources = new java.util.HashSet();

        System.out.println("REST configuration starting: getClasses()");

        resources.add(GenericExceptionMapper.class);
        resources.add(ApplicationExceptionMapper.class);

        resources.add(org.glassfish.jersey.jackson.JacksonFeature.class);
        resources.add(MultiPartFeature.class);

        resources.add(ApplicationJacksonProvider.class);
        resources.add(MessageBodyReader.class);
        resources.add(MessageBodyWriter.class);

        resources.add(UserResource.class);
        resources.add(TokenEndpoint.class);
        resources.add(CompanyResource.class);

        return resources;
    }

    @Override
    public Set<Object> getSingletons() {
        return Collections.emptySet();
    }

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap();

        //in Jersey WADL generation is enabled by default, but we don't
        //want to expose too much information about our apis.
        //therefore we want to disable wadl (http://localhost:8080/service/application.wadl should return http 404)
        //see https://jersey.java.net/nonav/documentation/latest/user-guide.html#d0e9020 for details
        properties.put("jersey.config.server.wadl.disableWadl", true);


        //we could also use something like this instead of adding each of our resources
        //explicitly in getClasses():
        //properties.put("jersey.config.server.provider.packages", "com.nabisoft.tutorials.mavenstruts.service");
            properties.put("jersey.config.server.provider.packages",
                    "com.clubcard.resources,com.clubcard.resources.provider");
        return properties;
    }

}