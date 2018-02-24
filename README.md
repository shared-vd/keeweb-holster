# keeweb-holster

This is a hosting server for the popular [KeeWeb](https://keeweb.info) application

## Building

To build the app, run:

```mvn clean install```

### Update KeeWeb

```
git clone https://github.com/keeweb/keeweb.git
cd keeweb
grunt
cp -r dist/* <keeweb-holster>/src/main/resources/static/
cd <keeweb-holster>
git commit -m "Update of keeweb" && git push
```

## Running

This is a Spring Boot application, it can be run different ways:

### As a WAR in a Tomcat Container

Copy ```target/keewebholster.war``` to ```<tomcat>/webapps/keewebholster.war```

### As an execuable JAR

Run

```target/keewebholster.war start```

## Parameters

### Data dir

The default KDBX is saved in the directory: ${kdbx.dir}/

_Default value_: kdbx.dir=<directory where keewebholster.war resides

Overriding kdbx.dir:
Run

```target/keewebholster.war -Dkdbx.dir=<mydir> start```

### KDBX filename

The default KDBX file is named Secrets.kdbx

Overriding default filenae: 
Run

```target/keewebholster.war -Dkdbx.name=<mykdbx> start```


## KeeWeb config options

KeeWeb can be configured by a config.json file
By default, keewebholster automatically provides such a file.
You can override this default file with yours. Just provide one file named config.json and put it in ${kdbx.dir}

