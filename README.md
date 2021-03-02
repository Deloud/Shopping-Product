# Shopping-Product

**[서비스 개요]**  

## Getting Started 🚀

- Postgres & Pact broker 설치

```
$ docker run -d --name postgres -p  5432:5432 \
-e POSTGRES_USER=oauth -e POSTGRES_PASSWORD=oauth123 -e POSTGRES_DB=oauth postgres
```

```
$ docker run -d --name pact-broker --link postgres:postgres -p 9292:9292 \
-e PACT_BROKER_DATABASE_USERNAME=oauth \
-e PACT_BROKER_DATABASE_PASSWORD=oauth123 \
-e PACT_BROKER_DATABASE_HOST=postgres \
-e PACT_BROKER_DATABASE_NAME=oauth pactfoundation/pact-broker
```

## Running the Contract test 🤖

#### Consumer - 1) Contract test Pact file 생성

**경로** : /client
```
$ mvn install
```

#### Consumer - 2) Pact file Pact broker에 배포

- docker를 통해 실행되고 있는 Pact broker에 Contract가 담긴 Pact file 배포

**경로** : /client
```
$ mvn pact:publish
```

#### Provider - Shopping-Product Service Build & Run

- Service Build

**경로** : /Shopping-Product
```
$ mvn install
```

- Service Run

**경로** : /Shopping-Product
```
$ java -jar target/*.jar
```

#### Provider - Contract test

- Pact broker에 올라가있는 Contract를 기반으로 Contract test 검증하기

**경로** : /Shopping-Product
```
$ mvn pact:verify
```

## Pact Result & Verify 🎫

#### consumer - ProductServiceClient2 & provider - ProductService2

<img src="https://user-images.githubusercontent.com/43091713/109666539-9dfe0e80-7bb2-11eb-9e8c-845b72f8cf3f.png" height=60% width=60%>





