package ch.vd.keewebholster.json;

import java.util.ArrayList;
import java.util.List;

public class ConfigJson {
    public boolean showOnlyFilesFromConfig = true;

    public final Settings settings = new Settings();
    public final List<File> files = new ArrayList<>();

    public static class Settings {
        public String theme = "wh";
        public boolean autoSave = true;
        public boolean dropbox = false;
        public boolean canOpen = false;
        public boolean canOpenDemo = false;
        public boolean canOpenSettings = false;
        public boolean canCreate = false;
        public boolean canImportXml = false;
        public boolean webdav = true;
        public String webdavSaveMethod = "put";
        public boolean gdrive = false;
        public boolean onedrive = false;
    }

    public static class File {
        public final String storage = "webdav";
        public String name;
        public String path;

    }
}
