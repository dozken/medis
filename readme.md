## Installation
*   Download and install Eclipse Oxygen
*   Install Lombok v1.18.2 or later
*   Install Hotswap-agent v1.3.0 or later `java -jar hotswap-agent-1.3.0.jar`
*   Download and install Mongo version 4.x or higher, open mongod.conf file and add 
```
replication:
  replSetName: mersys
```
*   restart mongo service
*   run mongo in terminal to open mongodb console, run `rs.initiate()` command once
*   import project `medis-service` in eclipse
*   open `project settings` -> `Java Compiler` -> `Annotation Processing` -> add tick to `Enable project specific changes` -> `Apply`
*   open `project settings` -> `Java Compiler` -> `Annotation Processing` -> `Factory Path` -> `Add JARs...` -> add mapstruct jar example `M2_REPO/org/mapstruct/mapstruct-processor/1.1.0.Final/mapstruct-processor-1.1.0.Final.jar`
*   create spring boot run configuration for medis-app, configure hotswap for it  `-XXaltjvm="dcevm" -javaagent:<path-to-hotswap-agent-1.3.0.jar>/hotswap-agent-1.3.0.jar=autoHotswap=true`
*   open terminal in medis-ui folder, run `npm install`, `ng serve --proxy-config proxy.conf.json` (in order to use this commands, u'll need, `angular-cli` & `npm` installed)

## New Medis Prototype
https://projects.invisionapp.com/share/EZO37JTTXD8#/screens/320278618

## Medis Demo
https://medis.mersys.io
username: admin@demo
password: ManageD18

## Swagger
* http://localhost:8888/swagger-ui.html