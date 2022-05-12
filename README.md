# CasoPB

As duas automações foram feitas utilizando a Mavem como gerenciado de pacotes, então para executar a automação a máquina precisa:
  - Instalar o Java 8 e adicionar o Java as variaveis do Windows
  - Instalar o Apache Mavem na máquina e adicionar o Mavem as variaveis do Windows
  - Um executavel do chromedriver compativel com a versão do google chrome da máquina

Para executar a automação da API feita em Rest Assured:
  - Abra o CMD como administrador
  - Vá até a pasta raiz do projeto "(caminha da máquina).../CasoRest" pelo CMD
  - Execute o comando "mvn clean install"
  - Execute o comando "mvn test surefire-report:report"
  - Então o relatório sera criado na pasta ".../CasoRest/target/site/surefire-report.html"

Para executar a automação da pagina Web:
  - Adicione o chromedriver na pasta "C:\drivers" com o nome de "chromedriver.exe"
    OU
    Altere a variavel caminhoChromeDriver para o caminho do "chromedriver.exe" no arquivo "...\CasoWeb\test\java\Teste_Seletores.java" 
  - Abra o CMD como administrador
  - Vá até a pasta raiz do projeto "(caminha da máquina).../CasoWeb" pelo CMD
  - Execute o comando "mvn clean install"
  - Execute o comando "mvn test surefire-report:report"
  - Então o relatório sera criado na pasta ".../CasoWeb/target/site/surefire-report.html"
