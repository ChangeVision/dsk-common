package dsk.common.util;

import java.util.Date;


public class LocalApplication implements Util {

	@Override
	public Date now() {
		return new Date();
	}
}
