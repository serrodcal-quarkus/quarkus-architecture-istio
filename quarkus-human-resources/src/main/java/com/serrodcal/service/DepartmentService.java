package com.serrodcal.service;

import com.serrodcal.domain.Department;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/department")
@RegisterRestClient(configKey="department-api")
public interface DepartmentService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Department> getDepartments();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Department> getDepartment(@PathParam("id") Long id);

}
