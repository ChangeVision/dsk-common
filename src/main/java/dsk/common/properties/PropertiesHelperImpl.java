package dsk.common.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import dsk.common.exception.DskRuntimeException;
import dsk.common.util.IoTools;

public class PropertiesHelperImpl implements PropertiesHelper {
	private Properties prop;
	private File propFile;

	public PropertiesHelperImpl(Properties prop, String dirPath, String filename) {
		super();
		this.prop = prop;
		this.propFile = new File(String.format("%s/%s", IoTools.createDirectory(dirPath), filename));

		this.load();
	}

	@Override
	public void load() {
		if (this.propFile.exists()) {
			InputStream is = null;
			try {
				is = new FileInputStream(this.propFile);
				this.prop.loadFromXML(is);
			} catch (Exception e) {
				throw new DskRuntimeException(e);
			} finally {
				IoTools.close(is);
			}
		}
	}

	@Override
	public void save() {
		OutputStream os = null;
		try {
			os = new FileOutputStream(this.propFile);
			this.prop.storeToXML(os, "env", "UTF-8");
		} catch (Exception e) {
			throw new DskRuntimeException(e);
		} finally {
			IoTools.close(os);
		}
	}

	@Override
	public String get(String key) {
		return this.prop.getProperty(key);
	}

	@Override
	public String get(String key, String defaultValue) {
		return this.prop.getProperty(key, defaultValue);
	}

	@Override
	public void set(String key, String value) {
		this.prop.setProperty(key, value);
	}
}
