#Spring Boot Application to autosuggest cities name

- This application will suggest the city names to the user based on start entered by user in input fields.
- Application is using TRIE data structure to store city names.

### Source City List
- City list is populated from CityList.csv file placed in src/main/resources.
- To change source file to populate city list, we need to update file.path property in application.yml file in src/main/resources.

### Configuration

- application.yml file is placed in src/main/resources to configure port number, contextPath, file path and column index to load cities.
- Default port is configured as 8080.
- Default contextPath is configured as /api/v1/autocompletecities.
- File name cane be changed by making change in file.path property.
- Default file name is src/main/resources/CityList.csv
- Default index to load city names is 0 that can be changed as per list provided in column index of configured file by making change in city.list.index property.


### Running Examples
- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open Command Prompt and Change directory (cd) to folder containing pom.xml
- Open Eclipse 
   - File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
   - Select the right project
- Choose the Spring Boot Application file App.java (search for @SpringBootApplication)
- Right Click on the file and Run as Java Application
- You are all Set

### How to test
- Hit below URl from RESR Client or browser
- http://localhost:8080/api/v1/autocompletecities/suggest_cities?start=ca&atmost=2


