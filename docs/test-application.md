# Testing application

1. Asggin an employee to a department:

```sh
$ curl -X POST 'localhost/api/v1/hr/employee/2/assign/department/2' -w "\n" -i
```

2. Unassign an employee:

```sh
$ curl -X DELETE 'localhost/api/v1/hr/employee/2/unassign' -w "\n" -i
```