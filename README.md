# Demo tests for thecatapi.com
This project contains demo tests.

## API_KEY
Get api key from https://thecatapi.com/

## Configuring .env
Copy `.env.dist` to `.env` \
Put your api key to `API_KEY` parameter in `.env`

## Windows
### Running
`gradlew.bat clean test`
### Downloading allure
`gradlew.bat downloadAllure`
### Serving allure report
`gradlew.bat allureServe`

## Linux
### Running
`./gradlew clean test`
### Downloading allure
`./gradlew downloadAllure`
### Serving allure report
`./gradlew allureServe`