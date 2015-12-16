/**
 * Copyright (C) 2015 An Honest Effort LLC, coping.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.anhonesteffort.uhd;

import org.anhonesteffort.uhd.types.DeviceAddress;
import org.anhonesteffort.uhd.types.DeviceAddresses;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DeviceTest {

  private Properties properties;
  private String     DEVICE_ARGS;

  @Before
  public void setUp() throws IOException {
    if (properties == null) {
      properties = new Properties();
      properties.load(new FileInputStream("usrp.properties"));

      DEVICE_ARGS = properties.getProperty("device_args");
    }
  }

  @Test
  public void testFind() {
    final DeviceAddress   HINT      = new DeviceAddress("");
    final DeviceAddresses ADDRESSES = Device.find(HINT);

    assert ADDRESSES.size() > 0;
    assert ADDRESSES.get(0).to_string().length() > 0;
  }

  @Test
  public void testMake() {
    final DeviceAddress ADDRESS = new DeviceAddress(DEVICE_ARGS);
    final Device        DEVICE  = Device.build(ADDRESS, 0);

    assert DEVICE != null;
  }

  @Test
  public void testMakeLookupError() {
    final DeviceAddress ADDRESS = new DeviceAddress("name=,serial=nope,type=nope");

    try {

      Device.build(ADDRESS, 0);
      assert false;

    } catch (RuntimeException e) {
      assert true;
    }
  }

}
