# WarrenGoalsApp

WarrenGoalsApp é um aplicativo que permite ao usuário visualizar planos financeiros para o futuro e ver o quanto foi salvo assim como o prazo estipulado.

# Tecnologia

### Linguagem
Este aplicativo foi escrito utilizando a linguagem [Kotlin](https://kotlinlang.org/), visto que a linguagem possibilita o uso do paradigma funcional.

### Arquitetura
O aplicativo segue com base da arquitetura [MVVM](https://www.journaldev.com/20292/android-mvvm-design-pattern)(Model-View-ViewModel) e se beneficía das bibliotecas do [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/).

O projeto foi dividio em uma camada de repositório em que os dados serão buscados através do Retrofif respeitando assim os princípios do [SOLID](https://en.wikipedia.org/wiki/SOLID).

### Testes
Neste projeto foi somente criado testes unitários da camada de ViewModel dos fragmentos assim como dos repositórios e todos os testes foram escritos seguindo a teoria do [TDD](https://pt.wikipedia.org/wiki/Test_Driven_Development).

### Bibliotecas externas
Das bibliotecas externas utilizadas no aplicativo, destaca-se o [Navigation](https://developer.android.com/topic/libraries/architecture/navigation) do Architecture Components, que visa facilitar as mudanças de tela do aplicativo de acordo com a necessidade e o [Koin](https://insert-˜.io/) para injeção de dependência pois é menos verbosa que o Dagger 2 e 100% escrita em Kotlin assim como a utilização de coroutines para lidar com chamadas assincronas.
