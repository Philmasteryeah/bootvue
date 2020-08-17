Tutorial for the Spring Data REST Telosys bundle
===========================================

Note
-----
For this tutorial, we start from a derby database. This database manage a bookstore (Book, Author, Reviews etc ..). 
You can follow this tutorial with your own database.  
If you want to start with the same db : [https://dl.dropboxusercontent.com/u/52079610/BookstoreDB_v2_Derby10.9.zip](https://dl.dropboxusercontent.com/u/52079610/BookstoreDB_v2_Derby10.9.zip)  
The database name is bookstore and user/pwd is root/admin.

1. Project Creation
----------------

* You can start by create a new __maven__ project or start on an existing one.
* The packaging of this project must be __"war"__
* The java __compliance level must be 1.7__

2. Initialize Telosys Tools
----------------------------------
- Open the properties window of your project
- In the left entries, select __"Telosys Tools"__ and fill all project informations in "General", "Packages" and "Folders" tab (right side)
- Click on the __"Apply"__ button (don't forget this step!)
- Click on __"Init Telosys Tools"__ in the "General" tab => __The "Telosys Tools" directory is now added in the project__
- In the "download" tab, download from this Github repository the __"spring-data-rest-templates-TT210"__
- Click on __"Download selected bundle(s)"__ and verify the checkbox __"Install downloaded bundle(s)"__ is checked.
- After this, close the project properties window.
- In the project, you should now have the __"spring-data-rest-templates-TT210"__ directory under __"TelosysTools/templates"__

3. Update maven dependencies
----------------------------------
- Under the __"spring-data-rest-templates-TT210"__ directory, open the __/misc/maven-pom.txt__
- Copy and paste the dependencies to your project pom (if they are not already exist)
- For this example, we use a dependency for the Derby driver. Adapt it to your database if needed.

4. Database repository generation
-------------------------------
- Open the database configuration file : "Telosys Tools / databases.dbcfg"
- The database editor is displayed
- Add a new connection to your database
- Test the connection to get "Connection is OK"
- Click on "Meta-data" tab, fill the schema name and click on "Get tables" to see the tables of your database.
- If all is OK, click on "Generate repository" button to generate a new ".dbrep" file in the "TelosysTools" directory 

5. Generate the REST API
----------------------------
- Click on the __"Bulk generation"__ tab
- In the entities list, __select all entities__ you want to manage (be careful for links, __all linked entities must be selected__)
- Select the __"spring-data-rest-templates-TT210"__ templates bundle in the dropdown list => all templates are now visible in right list
- Select all templates and select __"copy static resources"__
- Click on the __"Generate"__ button
- You should have a successfull message
- The project source code __should compile successfully__

6. The Spring configuration
-----------------------------
- For this project, the spring configuration was performed in a java way.
- __RestWebApplicationInitilizer__ : Servlet context initilization
- __JpaRepositoryConfig__ : Database access configuration
- __ApplicationConfig__ : Declaration of MessageSource, validators, converters ...


7. The JPA Entities
----------------
- The JPA Entities were generated under the package you have enter in the Telosys Tools properties window.

8. The Spring data JPA repositories
------------------------------------------
- The JPA Repositories were generated under the "repository" package
- Search methods for the Many-to-One relationships were have also been generated

9. Converters
------------------------------
- Use for entity how have composite primary key

10. Validators
------------------------------
- Each entity had an associated validator wich is executed before the update



Getting Started 
----------------
You can deploy the project under your server or launch ``` mvn tomcat7:run ```  
To browse the resources, here are several ways to:
- Access URL from your web browser (http://localhost:8989/)
- Use Postman extension for Chrome
- Launch the spring rest-shell (see : [https://github.com/spring-projects/rest-shell](https://github.com/spring-projects/rest-shell)

Enjoy!
