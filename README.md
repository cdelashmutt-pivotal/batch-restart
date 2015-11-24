# Spring Batch with Restart

This project is a simple example of a Spring Boot based Spring Batch project that has a job configured with restart capabilities.

You can test this project by running the Application class, and then sending a POST to http://localhost:8080/job/importUserJob

This will cause the importUserJob to be launched.  You will see the first chunk of two records in the src/main/resources/sample-data.csv are processed, but an exception is thrown when trying to process the 4th record from the src/main/resources/sample-data.csv file.  This file has a bad record in it, and the job is not configured to skip errors so it is stopped with an error.

If you fix the 4th row by putting a valid number into that row, then you can POST to http://localhost:8080/job/importUserJob again and notice that the first two records aren't processed, but the 3rd and beyond are (due to the chunk size set in the step1 step in the BatchConfiguration class).  The final query from the destination table in memory shows that we only have 5 records so no record was inserted more than once.
