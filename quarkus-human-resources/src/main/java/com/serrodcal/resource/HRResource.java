package com.serrodcal.resource;

import com.serrodcal.service.HRService;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.quarkus.vertx.web.Param;
import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RouteBase;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.reactivex.ext.web.RoutingContext;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@RouteBase(path = "api/v1")
public class HRResource {

    private static final Logger logger = Logger.getLogger(HRResource.class);

    private static final String TEXT_PLAIN = "text/plain";

    @Inject
    HRService hrService;

    @Route(path = "hr/employee/:employeeId/assign/department/:deptId", methods = HttpMethod.POST)
    void assignEmployeeToDept(RoutingContext rc, @Param("employeeId") String employeeId, @Param("deptId") String deptId) {
        logger.info("assignEmployeeToDept with [employeeId:" + employeeId + ", dept:" + deptId + "]");
        this.hrService.assignEmployeeToDept(Long.valueOf(employeeId), Long.valueOf(deptId)).subscribe().with(result -> {
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

    @Route(path = "hr/employee/:employeeId/unassign", methods = HttpMethod.DELETE)
    void unassignEmployee(RoutingContext rc, @Param("id") String id) {
        logger.info("unassignEmployeeToDept wit [id:" + id + "]");
        this.hrService.unassignEmployee(Long.valueOf(id)).subscribe().with(result -> {
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
                            .putHeader(HttpHeaders.CONTENT_TYPE, TEXT_PLAIN)
                            .setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
                            .end(failure.getMessage());
                });
    }

    @Route(path = "hr/department/:deptId", methods = HttpMethod.DELETE)
    void unassignEmployees(RoutingContext rc, @Param("deptId") String deptId) {
        this.hrService.unassignEmployees(Long.valueOf(deptId)).subscribe().with(result -> {
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
                            .putHeader(HttpHeaders.CONTENT_TYPE, TEXT_PLAIN)
                            .setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
                            .end(failure.getMessage());
                });
    }

}
