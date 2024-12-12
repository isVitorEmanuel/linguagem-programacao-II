# LPII - 2ª Unidade
## - Execução

Inicialmente, você deve estar localizado na pasta raiz do projeto `project`
O projeto é compilado através de arquivo `compile.sh`. Para garantir que
esse arquivo seja executável e possa compilar o projeto, execute:

```
chmod +x compile.sh
```

Agora podemos compilar o projeto. Para tanto, execute:

```
./compile.sh
```

Esse trecho, se executado com sucesso, gerará a saída `Compile successfully`.
Por fim, basta rodar o projeto buildado. Execute o comando:

```
java -cp out Main
```
Aproveite!

## - Panorama Geral das Funções
###  interfaces

- ``Functionary``:

Nessa `interface` temos o seguinte método:

```java
public Double calculateSalary();
```
Sua implementação é de responsabilidade das classes modelos: 
`ADMTechnician` e `Teacher`.

### models

- `Address`:

Nessa classe temos o seguinte método:

```java
public String toString ();
```
Esse método é responsável por criar uma `string` organizada e identada para impressão
dos dados e informações do endereço do usuário.

- `ADMTechnician`:

Nessa classe temos os seguintes métodos:

```java
public Double calculateSalary();
```

O método `calculateSalary()` tem como função calcular o salário de um téncico
de acordo com o salário base, nível e formação, além de insalubridade e gratificação
de seu cargo. A implementação desse método segue a especificação dada para cálculo do salário do técnico no documento
ao método `calcularSalario()` da classe `Operacoes`.

```java
public String toString();
```

Esse método é responsável por criar uma `string` organizada e identada para impressão dos dados do técnico.

- `Teacher`:

Nessa classe temos os seguintes métodos:

```java
public Double calculateSalary();
```

O método `calculateSalary()` tem como função calcular o salário de um professor
de acordo com o salário base, nível e formação. A implementação desse método segue
a especificação dada para cálculo do salário do professor no documento
ao método `calcularSalario()` da classe `Operacoes`.

```java
public String toString();
```

Esse método é responsável por criar uma `string` organizada e identada para impressão dos dados do professor.

_OBS: A classe abstrata `People` possui apenas métodos `get` e `set`, pois 
ela é implementada pela classes `ADMTechnician` e `Teacher`._

### services

- `Operations`

Nessa classe, temos os seguintes métodos:

```java
public Operations();
```

Esse método é o construtor da classe. Nele, nós carregamos para a memória os
dados salvos em arquivo.

```java
public void closeApplication();
```
Esse método para a aplicação e salva os dados da memória no arquivo.

```java
public void registerTeacher();
```

Nessa função temos a criação do usuário de um professor. Após a criação, 
adicionamos esse professor ao database na memória do programa para que possa ser utilizado e,
posteriormente, salvo em arquivo.

```java
public void registerADMTechnician();
```

Nesse método temos a criação do usuário de um técnico. Após a criação, adicionamos
esse professor ao database na memória do programa para que possa ser utilizado e, posteriormente, salvo em arquivo.

```java
public void listTeachers();
```
Nesse método percorremos o database de usuários, e listamos no terminal todos
os professores,

```java
public void listADMTechnicians();
```
Nesse método percorremos o database de usuários, e listamos no terminal todos 
os professores.

```java
public void searchTeacher();
```

Nesse método mostramos ao usuário os dados de um determinado professor baseado no
número da matrícula do docente que é informado pelo usuário.

```java
public void searchADMTechnician();
```

Nesse método mostramos ao usuários os dados de um determinado técnico baseado no
número da matrícula do técnico que é informado pelo usuário.

```java
public void deleteTeacher();
```

Nesse método solicitamos a matrícula do professor que o usuário deseja deletar.
Com a matrícula, deletamos o professor do database do sistema.

```java
public void deleteTechnician();
```

Nesse método solicitamos a matrícula do técnico que o usuário deseja deletar.
Com a matrícula, deletamos o técnico do database do sistema.

### util

- `FileUtils`
  
Nessa classe temos os seguintes métodos:

```java
public static void saveFunctionaries(String pathData, 
                                     ArrayList<People> functionaries);
```

Esse método é responsável por salvar os dados dos funcionários que estão em memória 
no arquivo. Recebemos o `caminho / path` do arquivo e a lista com os dados de todos
os funcionários e salvamos esses dados no arquivo.

```java
public static ArrayList<People> getFunctionaries(String pathData);
```

Esse método é responsável por recuperar os dados do arquivo binário e colocá-lo, em tempo
de execução, na memória. Recebe-se o caminho do arquivo binário onde os dados estão salvos
permanentemente, recupera-se os dados desse colocando-os em memória e, por fim, retorna um 
array com todos os funcionários recuperados.

- `Utils`

Nessa classe temos os seguintes métodos:

```java
private Address createAddress();
```
Esse método tem como objetivo criar um objeto `Address` que representa
o endereço de residência de um professor ou de um técnico.
Aqui coletamos os dados do endereço do usuário e tratamos essas informações
capturando excessões e erros. Por fim, retornamos o objeto com as 
informações do endereço.

```java
public Teacher createTeacher();
```

Esse método tem como objetivo criar um objeto `Teacher` que representa o usuário
de um professor. Aqui coletamos os dados do professor como seu nome, matrícula, 
endereço, formação, nível, disciplinas entre outros. Também
tratamos execessões, erros na coleta e fazemos o cálculo do salário. Por fim,
retornamos o objeto com as informações do professor.

```java
public ADMTechnician createTechnician();
```
Esse método tem como objetivo criar um objeto `ADMTechnician` que representa o usuário
de um técnico.  Aqui coletamos os dados do técnico como seu nome, matrícula, endereço, 
formação, nível, gratificação, insalubridade, entre outros. Também
tratamos execessões, erros na coleta e fazemos o cálculo do salário. Por fim,
retornamos o objeto com as informações do técnico.
