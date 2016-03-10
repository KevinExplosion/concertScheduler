# Concert Scheduler!

#### Enter a band and link them to a corresponding venue where they will be playing a concert!

#### By Kevin Mattison

## Uses a sql database with many to many relationships. Link a band to multiple venues and link a venue to multiple bands. Use this app to follow concert schedules.

## Installation Instructions

Clone this repository:

$ cd ~/Desktop $ git clone https://github.com/KevinExplosion/concertScheduler $ cd band_venues Open terminal and run Postgres:

$ postgres Open a new tab in terminal and create the band_venues database:

$ psql $ CREATE DATABASE band_venues; $ TABLES bands, venues $ BANDS COLUMNS id int PRIMARY KEY, venues varchar $ VENUES COLUMNS id int PRIMARY KEY, venues varchar

Navigate back to the directory where this repository has been cloned and run gradle:

$ gradle run

## Technologies Used

* Java
* SQL
* Gradle
* Spark
* junit
* Velocity
* Fluentlenium
* CSS3
* HTML5

### License

Licensed under the GPL.

Copyright (c) 2016 **Kevin Mattison**
