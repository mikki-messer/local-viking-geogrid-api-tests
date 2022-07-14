# Automated tests for the localviking.com GeoGrid API tests

## <a href = "https://localviking.com" target ="_blank">localviking.com</a>

<p align="center">
<img title="Local Viking Welcome" src="images/screenshots/LocalVikingWelcome.png">
</p>

## :crossed_swords: Contents

- <a href="#crossed_swords-what-is-Local-Viking?">What is Local Viking?</a>
- <a href="#crossed_swords-coverage">Coverage</a>
- <a href="#rescue_worker_helmet-technology-stack">Technology stack</a>
- <a href="#rescue_worker_helmet-how-to-launch-from-the-command-line">How to launch from the command line</a>
- <a href="#rescue_worker_helmet-jenkins-build-example">Jenkins build example</a>
- <a href="#rescue_worker_helmet-allure-reports-integration">Allure reports integration</a>
- <a href="#rescue_worker_helmet-telegram-Notification">Telegram Notification</a>
- <a href="#rescue_worker_helmet-selenoid-launch-example">Selenoid launch example</a>

## :crossed_swords: What is Local Viking?

<p>
Local Viking is one of the most popular Google My Business software management platforms. 
It allows schedule GMB Posts, track location rankings, upload photos, and manage reviews and Q&A 
– all from a single dashboard. GeoGrid rank tracker is one of the most popular features of Local Viking. 
It provides an opportunity to see the visibility of a GMB listing for a specific keyword from a number of different
latitude and longitude coordinates, in other words, it gives a view of how GMB Listing ranks from different positions
across one geographic area (a square to be precise).
</p>

## :crossed_swords: Coverage


- Check that GET request to the `/geogrids/:id` endpoint with the `:id` of the GeoGrid existing in the database and valid authorization token returns the Geogrid
- Check that GET request to the `/geogrids/:id` endpoint with the `:id` of the GeoGrid existing in the database and non-existing authorization token gets 403 result code
- Check that GET request to the `/geogrids/:id` endpoint with the `:id` of the GeoGrid existing in the database and without authorization token gets 403 result code
- Check that GET request to the `/geogrids/:id` endpoint with the `:id` of the GeoGrid non-existing in the database and valid authorization token get 404 result code
- Check that GET request to the `/geogrids/:id` endpoint with the `:id` of the GeoGrid non-existing in the database and valid authorization token returns the Geogrid
- Check that GET request to the `/geogrids` endpoint with the `page` query parameter returns the array of Geogrids from the specific page
- Check that POST request to the `/geogrids` endpoint with the GeoGrid data in the body creates the GeoGrid

## :crossed_swords: Technology stack

<p align="center">
<img width="6%" title="IntelliJ IDEA" src="images/logos/Intelij_IDEA.svg">
<img width="6%" title="Java" src="images/logos/Java.svg">
<img width="6%" title="Rest-Assured" src="images/logos/RestAssured.svg">
<img width="6%" title="Allure Report" src="images/logos/Allure_Report.svg">
<img width="6%" title="Gradle" src="images/logos/Gradle.svg">
<img width="6%" title="JUnit5" src="images/logos/JUnit5.svg">
<img width="6%" title="GitHub" src="images/logos/GitHub.svg">
<img width="6%" title="Jenkins" src="images/logos/Jenkins.svg">
<img width="6%" title="Telegram" src="images/logos/Telegram.svg">
</p>

## :rescue_worker_helmet: How to launch from the command line

### How to launch on the local machine

<p>
Default params
</p>

- browser: chrome
- browser size: 1280x800
- remoteURL: selenoid.autotests.cloud

```
gradle clean test
```

Arbitrary params
```
gradle clean test -DselenoidURL="yourAwesomeURL" -Dbrowser="yourPreciousBrowser" -DbrowserSize="AAAAxBBBB"
```

### How to launch remotely on Jenkins

```
clean
test
-DselenoidURL=${SELENOID_URL}
-Dbrowser=${BROWSER}
-DbrowserSize=${BROWSER_SIZE}
```

### Jenkins build params

- selenoidURL - the URL of the Selenoid instance to run tests on, default value: `selenoid.autotests.cloud`
- browser - the browser, chrome, firefox, and opera are supported, default value: `chrome`
- browserSize - the size of the browser window in AAAAxBBBB format, default value: `1280x800`

> Don't forget to create the `credentials.properties` file in the `src/test/resources/configuration/` folder with the
login and password to the Selenoid

#### Credentials.properties example, put your real login and password there

```
login=myAwesomeLogin
password=mySecurePassword
```

## :rescue_worker_helmet: Jenkins build example

### <a target="_blank" href="https://jenkins.autotests.cloud/job/C12-Mike-B-lesson-13-redrift.com/">Jenkins build</a>

<p align="center">
<img title="Jenkins Dashboard" src="images/screenshots/jenkins-redrift-build-main-page.png">
</p>

## :rescue_worker_helmet: Allure reports integration

### Overview

<p align="center">
<img title="Allure reports Overview tab screenshot" src="images/screenshots/allure-reports-redrift-main-page.png">
</p>

### Test Suites

<p align="center">
<img title="Allure reports Test suites tab screenshot" src="images/screenshots/allure-redrift-reports-tests.png">
</p>

> Please note, saving browser console logs to Allure reports is not supported for Firefox!!!

## :rescue_worker_helmet: Telegram Notification example

> <a href="https://github.com/qa-guru/allure-notifications">qa-guru/allure-notifications</a> is used

<p align="center">
<img title="Telegram notification screenshot" src="images/screenshots/telegram-redrift-tests-notification.png">
</p>

## :rescue_worker_helmet: Selenoid launch example

There is a video for each test demonstrating the flow.

<p align="center">
<img title="Selenoid Video" src="images/gifs/closing-the-lion.gif">
</p>

