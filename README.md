**Framework Features:**
1. **Cucumber Report Generation**: Automatically generates Cucumber reports with detailed step information.
2. **Execution Logs**: Provides detailed request and response information in execution logs.
3. **Datatable Use Cases**: Includes examples of datatable use cases in feature files.
4. **Rerun of Failed Tests**: Implemented functionality to rerun failed tests.
5. **Response Validation**: Supports validation of response body using JSON schema and Java POJO classes.
6. **Command Line Execution**: Test execution can be triggered from the command line.
7. **CI/CD Integration**: Easily integratable into CI/CD pipelines.
8. **Support Both Junit4&5**: Framework is compatible with both junit 4 and 5

**Required Setup:**
1. Java installed and configured. (Preferbale Version 11 and above)
2. Maven installed and configured. (preferable version 3.9 and above)
3. Download the files from the Git repository either as a zip file or using Git
   
**Running Tests:**
Open the command prompt and navigate to the folder containing the pom.xml file. Run the following Maven command:

    mvn clean test

Once the execution completes, the report will be generated in the following folder:
**Report: target/report**
