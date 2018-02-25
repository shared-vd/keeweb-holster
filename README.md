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

```./start.sh```

To stop

```./stop.sh```

## Switches

### HTTP port

The default value is 7070
Edit start.sh and change the PORT variable

### Data dir

The default KDBX is saved in the directory: ${kdbx.dir}/

_Default value_: kdbx.dir=<current directory>

Overriding kdbx.dir:
Edit start.sh and change the KDBX_DIR variable


### KDBX filename

The default KDBX file is named Secrets.kdbx

Overriding default filename: 
Edit start.sh and change the KDBX_NAME variable


## KeeWeb config options

KeeWeb can be configured by a config.json file
By default, keewebholster automatically provides such a file.
You can override this default file with yours. Just provide one file named config.json and put it in ${kdbx.dir}
