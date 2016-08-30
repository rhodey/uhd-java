/**
 * Copyright (C) 2016 An Honest Effort LLC.
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
package org.anhonesteffort.uhd.usrp;

import org.junit.Test;

public class SubDeviceSpecTest {

  @Test
  public void testResize() {
    final SubDeviceSpec spec = new SubDeviceSpec();
    spec.resize(2);

    assert spec.size() == 2;
  }

  @Test
  public void testGet() {
    final String markup = "A:0 A:1";

    assert new SubDeviceSpec(markup).get(0).db_name().equals("A");
    assert new SubDeviceSpec(markup).get(0).sd_name().equals("0");

    assert new SubDeviceSpec(markup).get(1).db_name().equals("A");
    assert new SubDeviceSpec(markup).get(1).sd_name().equals("1");
  }

  @Test
  public void testPut() {
    final SubDeviceSpec spec = new SubDeviceSpec();
    spec.resize(2);

    spec.put(0, new SubDeviceSpecPair("A", "0"));
    spec.put(1, new SubDeviceSpecPair("B", "1"));

    assert spec.get(0).db_name().equals("A");
    assert spec.get(0).sd_name().equals("0");

    assert spec.get(1).db_name().equals("B");
    assert spec.get(1).sd_name().equals("1");
  }

  @Test
  public void testValueError() {
    try {

      new SubDeviceSpec("A:0:1");
      assert false;

    } catch (RuntimeException e) {
      assert true;
    }
  }

  @Test
  public void testStringRepresentations() {
    assert new SubDeviceSpec("A:0").to_string().length() > 0;
    assert new SubDeviceSpec("A:0").to_pp_string().length() > 0;
  }

}
