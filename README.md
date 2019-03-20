[![Build Status](https://app.bitrise.io/app/8cf60b9ca019878e/status.svg?token=y0L_3dh4Ox9ADKMKJsZ7jw)](https://app.bitrise.io/app/8cf60b9ca019878e)

## Projeto 100 Top Game
Projeto para teste dos meus conhecimento.

![Screenshot_1553050352.png](:storage/41a12f74-8cc5-4c52-9d48-fb26c5d8a729/cf4703ee.png)

![Screenshot_1553050435.png](:storage/41a12f74-8cc5-4c52-9d48-fb26c5d8a729/8718c1be.png)

### Arquitetura
Usando MVP para padrao da views, clear architecture como base de arquitetura do projeto.
Usado Dagger para gerenciar o grafico de dependencia
Usado Room para persistencia local dos dados, Retrofit para fazer requisicao no servidor usando Restful.
Usando databinding para fazer bind de dados para view

### stack de conhecimento
- RxJava2 até a camada do presenter
- Dagger 2.19 para injeção de dependencias
- OKHttp3 e Retrofit para requisição http
- Room para cache local

### CI
Usando o bitrise como CI server

### Setuo o projeto
Necessario acessar a platforma [Home \| Twitch Developers](https://dev.twitch.tv/) e adicionar uma nova aplicacao, copiar api e colocar no arquivo de local.properties como a chave twitch_api

```
twitch_api="API_KEY"
```

### Executar teste

```
./gradlew test
```
### Running IDE
Android Studio 3.0 ou superior

## Running CLI
```
./gradlew build
```

### TODO 
* Melhoria da comportamento sem internet
* Adicionar novos componentes do material designer 
* Cobertura de teste unitario
* Teste de interface


## LICENSE

```
The MIT License (MIT)

Copyright (c) 2018 Ubiratan Soares

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```

