# financial-control

BACKEND
	IDE
	Eclipse Spring Tool Suite - 3.9.0-RELEASE

	JAVA
	1.8.0_101

	MySQL
	5.5.44

	Server
	Tomcat 8.0.48

FRONTEND
	Angular CLI 
	1.4.4

	TypeScript: 
	~2.3.3

	Node 
	6.11.3

*********************************************************************************
Passos para rodar a aplicação:

	1 - Adicionar o arquivo financial-control.war no diretorio webapps do tomcat
	2 - Acessar url: http://localhost:[porta]/financial-control/

*********************************************************************************

Configurar fontes na máquina:

	JAVA:
		Configurar projeto no Eclipse
			Importar como maven

		Buildar
			Via linha de comando
				cd [diretorio]/java
				mvn clean compile install

			Via Eclipse:
				Run as -> Maven Build

	Angular
		Configurar projeto
			cd [diretorio]/angular
			npm install
			ng serve --open

		Build do projeto angular 
			cd [diretorio]
			ng build --watch

Obs: 
	Após realizar alguma modificaçção no frontend(angular), deve ser gerado o build do projeto angular conforme acima.
	Os fonts ficarão disponíveis na pasta /dist
	Esses fonts devem ser copiados para a pasta WEB-INF\static do projeto Java, mas antes deve-se alterar o arquivo "index.html", trocando 
	<base href="/"> por <base href="/financial-control/">
