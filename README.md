# spring-boot-base

## Dev Notes

### Installation

Config gradle\wrapper\gradle-wrapper.properties distributionUrl for offline gradle installation
Config wrapper

```
./gradlew wrapper --gradle-version=8.12.1 --distribution-type=bin
```

### Hot reload

Terminal 1:

```
./gradlew build --continuous
```

Terminal 2:

```
./gradlew run # or ./gradlew bootRun --args='--spring.profiles.active=dev'
```
