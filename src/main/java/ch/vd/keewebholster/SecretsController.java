package ch.vd.keewebholster;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/")
public class SecretsController {

    @Autowired
    private KdbxProperties properties;

    @GetMapping("/{name}.kdbx")
    public ResponseEntity<byte[]> getSecrets(@PathVariable String name) throws IOException {
        if (!properties.getName().equals(name)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        final File path = new File(properties.getDir());
        path.mkdirs();
        final File file = new File(path, name + ".kdbx");
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                final byte[] data = IOUtils.toByteArray(fis);
                return new ResponseEntity<>(data, HttpStatus.OK);
            }
        }

        // new file
        final byte[] data = IOUtils.toByteArray(getClass().getResourceAsStream("/Empty.kdbx"));
        final HttpHeaders headers = new HttpHeaders();
        headers.setLastModified(new Date().getTime());
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @PutMapping("/{name}.kdbx")
    public void putSecrets(@PathVariable String name) {
        if (!properties.getName().equals(name)) {
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
