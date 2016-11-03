# Selenium tests for the Vagrant/Docker tutorial

Simple Selenium tests for the Vagrant/Docker tutorial.

Hard-coded to test using Firefox to load the web site at
http://192.168.33.11/ and wait for the paragraph under the states table 
to load (waits for up to `TIMEOUT_MILLIS` milliseconds). After that loads, 
looks for the **State** column header and **Ontario** in the first cell of the 9th row.

Edit `src/test/java/com/coveros/tutorial/functionaltests/StatesTest.java` to change
the `BASE_URL` to be tested or the `driver` in `setUp()` for the browser.
 
## To test the web site:

    mvn test
		