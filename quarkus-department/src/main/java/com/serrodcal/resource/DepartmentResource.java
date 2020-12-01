package com.serrodcal.resource;

import com.serrodcal.domain.Department;
import com.serrodcal.service.DepartmentService;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.quarkus.vertx.web.Body;
import io.quarkus.vertx.web.Param;
import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RouteBase;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.reactivex.ext.web.RoutingContext;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@RouteBase(path = "api/v1")
public class DepartmentResource {

    private static final Logger logger = Logger.getLogger(DepartmentResource.class);

    private static final String APPLICATION_JSON = "application/json";

    private static final String TEXT_PLAIN = "text/plain";

    @Inject
    DepartmentService departmentService;

    @Route(path = "department", methods = HttpMethod.GET)
    void getDepartments(RoutingContext rc) {
        logger.info("getDepartments");
        this.departmentService.getDepartments().collectItems().asList().subscribe().with(result -> {
                    if (result != null) {
                        rc.response()
                                .putHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON)
                                .setStatusCode(HttpResponseStatus.OK.code())
                                .end(Json.encode(result));
                    } else {
                        rc.response()
                                .setStatusCode(HttpResponseStatus.NO_CONTENT.code())
                                .end();
                    }
                },
                failure -> {
                    rc.response()
                            .setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
                            .putHeader(HttpHeaders.CONTENT_TYPE, TEXT_PLAIN)
                            .end(failure.getMessage());
                });
    }


    @Route(path = "department/:id", methods = HttpMethod.GET)
    void getDepartment(RoutingContext rc, @Param("id") String id) {
        logger.info("getDepartment with [id:" + id.toString() + "]");
        this.departmentService.getDepartment(Long.valueOf(id)).subscribe().with(result -> {
                    if (result != null) {
                        rc.response()
                                .putHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON)
                                .setStatusCode(HttpResponseStatus.OK.code())
                                .end(Json.encode(result));
                    } else {
                        rc.response()
                                .setStatusCode(HttpResponseStatus.NO_CONTENT.code())
                                .end();
                    }
                },
                failure -> {
                    rc.response()
                            .setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
                            .putHeader(HttpHeaders.CONTENT_TYPE, TEXT_PLAIN)
                            .end(failure.getMessage());
                });
    }

    @Route(path = "department", methods = HttpMethod.POST)
    void createDepartment(RoutingContext rc, @Body Department department) {
        logger.info("createDepartment with [name:" + department.name + "]");
        this.departmentService.createDepartment(department).subscribe().with(result -> {
                    if (result != null) {
                        rc.response()
                                .putHeader(HttpHeaders.CONTENT_TYPE, TEXT_PLAIN)
                                .setStatusCode(HttpResponseStatus.OK.code())
                                .end(Json.encode(result));
                    } else {
                        rc.response()
                                .setStatusCode(HttpResponseStatus.ACCEPTED.code())
                                .end();
                    }
                },
                failure -> {
                    rc.response()
                            .setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
                            .putHeader(HttpHeaders.CONTENT_TYPE, TEXT_PLAIN)
                            .end(failure.getMessage());
                });
    }

    @Route(path = "department", methods = HttpMethod.PUT)
    void updateDepartment(RoutingContext rc, @Body Department department) {
        logger.info("updateDepartment with [name:" + department.name + "]");
        this.departmentService.updateDepartment(department).subscribe().with(result -> {
                    if (result) {
                        rc.response()
                                .setStatusCode(HttpResponseStatus.OK.code())
                                .end();
                    } else {
                        rc.response()
                                .setStatusCode(HttpResponseStatus.ACCEPTED.code())
                                .end();
                    }
                },
                failure -> {
                    rc.response()
                            .setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
                            .putHeader(HttpHeaders.CONTENT_TYPE, TEXT_PLAIN)
                            .end(failure.getMessage());
                });
    }

    @Route(path = "department/:id", methods = HttpMethod.DELETE)
    void deleteDepartment(RoutingContext rc, @Param("id") String id) {
        logger.info("deleteDepartment wit [id:" + id.toString() + "]");
        this.departmentService.deleteDepartment(Long.valueOf(id)).subscribe().with(result -> {
                    if (result) {
                        rc.response()
                                .setStatusCode(HttpResponseStatus.OK.code())
                                .end();
                    } else {
                        rc.response()
                                .setStatusCode(HttpResponseStatus.ACCEPTED.code())
                                .end();
                    }
                },
                failure -> {
                    rc.response()
                            .setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
                            .putHeader(HttpHeaders.CONTENT_TYPE, TEXT_PLAIN)
                            .end(failure.getMessage());
                });
    }

}
