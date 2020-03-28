# OrganizationChartEmployee

This program reads three sets of data from 3 files 1) Personal.csv 2) Organization.csv 3) Team.csv and provides a consolidated output given a person's first name, last name or both. The program is a java maven project.

## Assumptions

I assumed that the data from Personal, Organization, and Team will be in CSV files with comma-separated values. Also, I am assuming that one level consists of the person whose name matches the command line arguments and his/her direct subordinates. Finally, if the user searches for someone with matching first name, last name, or both then the program will print everyone information that matches the command line arguments.

## Java Source Files
All the java files are located in \src\main\java\Equinix\OrgChartEmployee.
The test file which contains the unit tests can be found in \src\test\java\Equinix\OrgChartEmployee.

If you wanted to just run the java files you can run:
```
mvn compile
```
The java classes will then be located in \OrgChartEmployee\target\classes.

## Build Project

```
mvn package
```

## Run Unit Tests

```
mvn test
```

