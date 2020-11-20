# About the project

to build the backend REST APIs needed to support a web application 
to that allows a user to view accounts and subsequently view 
transactions on any of the accounts they hold.

## Required functionality

- view account list
- view account transactions

# Getting Started

## Prerequisites

1. java
    1. install sdkman `https://sdkman.io/install`
    1. install java `sdk install java`


## Installation
1. Clone the repo
run `git clone https://github.com/boraoren/project-wholesale.git`
1. Download dependencies
run `./gradlew clean build`

# Usage

## Run Application
1. run `./gradlew bootRun`
2. to get account list open link 
`http://localhost:8080/api/v1/accounts`

## Run Test
`./gradlew test`

## Generate API documentation
1. run `./gradlew clean asciidoctor`
2. check `build/asciidoc/html5` folder for index.html 
and open ot

 

