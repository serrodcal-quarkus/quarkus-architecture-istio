package com.serrodcal.resource;

import com.serrodcal.domain.Employee;
import com.serrodcal.service.EmployeeService;
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
public class EmployeeResource {

    private static final Logger logger = Logger.getLogger(EmployeeResource.class);

    private static final String APPLICATION_JSON = "application/json";

    private static final String TEXT_PLAIN = "text/plain";

    @Inject
    EmployeeService employeeService;

    @Route(path = "employee", methods = HttpMethod.GET)
    void getEmployees(RoutingContext rc) {
        logger.info("getEmployees");
        this.employeeService.getEmployees().collectItems().asList().subscribe().with(result -> {
                    if (result.size() > 0) {
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


    @Route(path = "employee/:id", methods = HttpMethod.GET)
    void getEmployee(RoutingContext rc, @Param("id") String id) {
        logger.info("getEmployee with [id:" + id.toString() + "]");
        this.employeeService.getEmployee(Long.valueOf(id)).subscribe().with(result -> {
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

    @Route(path = "employee/department/:deptId", methods = HttpMethod.GET)
    void getEmployeesByDept(RoutingContext rc, @Param("deptId") String deptId) {
        logger.info("getEmployeesByDept with [deptId: " + deptId.toString() + "]");
        this.employeeService.getEmployeesByDept(Long.valueOf(deptId)).collectItems().asList().subscribe().with(result -> {
                    if (result.size() > 0) {
                        rc.response()
                                .putHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON)
                                .setStatusCode(HttpResponseStatus.OK.code())
                                .end(Json.encode(result));
                    } else {
                        rc.response()
                                .putHeader(HttpHeaders.CONTENT_TYPE, TEXT_PLAIN)
                                .setStatusCode(HttpResponseStatus.NO_CONTENT.code())
                                .end(Json.encode(result));
                    }
                },
                failure -> {
                    rc.response()
                            .setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
                            .putHeader(HttpHeaders.CONTENT_TYPE, TEXT_PLAIN)
                            .end(failure.getMessage());
                });
    }

    @Route(path = "employee", methods = HttpMethod.POST)
    void createEmployee(RoutingContext rc, @Body Employee employee) {
        logger.info("createEmployee with [name:" + employee.name + "]");
        this.employeeService.createEmployee(employee).subscribe().with(result -> {
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

    @Route(path = "employee", methods = HttpMethod.PUT)
    void updateEmployee(RoutingContext rc, @Body Employee employee) {
        logger.info("updateEmployee with [name:" + employee.name + "]");
        this.employeeService.updateEmployee(employee).subscribe().with(result -> {
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

    @Route(path = "employee/:id", methods = HttpMethod.DELETE)
    void deleteEmployee(RoutingContext rc, @Param("id") String id) {
        logger.info("deleteEmployee wit [id:" + id.toString() + "]");
        this.employeeService.deleteEmployee(Long.valueOf(id)).subscribe().with(result -> {
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

    @Route(path = "employee/department/:deptId/unassign", methods = HttpMethod.POST)
    void unassignedEmployees(RoutingContext rc, @Param("deptId") String deptId) {
        logger.info("unassignedEmployees with [deptId:" + deptId + "]");
        this.employeeService.unassignEmployees(Long.valueOf(deptId)).subscribe().with(result -> {
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
