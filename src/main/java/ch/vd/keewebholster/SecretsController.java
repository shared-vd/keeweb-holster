package ch.vd.keewebholster;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("/")
public class SecretsController {

    private static final Logger log = LoggerFactory.getLogger(SecretsController.class);

    @Autowired
    private KdbxProperties properties;

    private static final String NAME_MISMATCH_ERROR = "The name of the Secrets file is not the same as the one configured";

    @GetMapping("/{name}.kdbx")
    public ResponseEntity<byte[]> getSecrets(@PathVariable String name) throws IOException {
        log.info("Asking file "+name);
        if (!Arrays.asList(properties.getNames()).contains(name)) {
            return new ResponseEntity<>(NAME_MISMATCH_ERROR.getBytes(), HttpStatus.NOT_FOUND);
        }

        final byte[] data;
        final HttpHeaders headers = new HttpHeaders();

        final File file = getSecretsFile(name);
        if (file.exists()) {
            log.info("Returning secrets file from " + file);
            headers.setLastModified(file.lastModified());
            try (InputStream fis = new FileInputStream(file)) {
                data = IOUtils.toByteArray(fis);
            }
        } else {
            log.info("Returning a new secrets file");
            headers.setLastModified(new Date().getTime());
            try (InputStream is = getClass().getResourceAsStream("/Empty.kdbx")) {
                data = IOUtils.toByteArray(is);
            }
        }
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @PutMapping("/{name}.kdbx")
    public ResponseEntity<String> putSecrets(@PathVariable String name, @RequestBody byte[] kdbx) throws IOException {
        if (!Arrays.asList(properties.getNames()).contains(name)) {
            return new ResponseEntity<>(NAME_MISMATCH_ERROR, HttpStatus.NOT_FOUND);
        }

        final File file = getSecretsFile(name);
        log.info("Persisting secrets file to " + file);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            IOUtils.write(kdbx, fos);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private File getSecretsFile(String name) {
        final File path = new File(properties.getDir());
        path.mkdirs();
        final File file = new File(path, name + ".kdbx");
        return file;
    }
}
