# Trellis Linked Data Server (MicroProfile)

Trellis is a scalable platform for building [linked data](https://www.w3.org/TR/ldp/) applications.
The `trellis-microprofile` project implements a persistence layer based on a triplestore and a relational database.

![Build Status](https://github.com/trellis-ldp/trellis-microprofile/workflows/GitHub%20CI/badge.svg)

PostgreSQL database connections are supported.

Java 8+ is required to run Trellis. To build this project, use this command:

```
$ ./gradlew assemble
```

## Database setup

In order to connect Trellis to a database, please first ensure that a database server is running and accessible. Please also
ensure that the database itself has been created along with a user account with read/write access. The database schema will
be generated by Trellis upon startup.

For more information about Trellis, please visit either the
[main source repository](https://github.com/trellis-ldp/trellis) or the
[project website](https://www.trellisldp.org).

