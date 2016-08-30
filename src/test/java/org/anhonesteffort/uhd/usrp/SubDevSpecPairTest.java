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

public class SubDevSpecPairTest {

  @Test
  public void testConstruct() {
    final SubDeviceSpecPair pair = new SubDeviceSpecPair("A", "0");

    assert pair.db_name().equals("A");
    assert pair.sd_name().equals("0");
  }

  @Test
  public void testSetGet() {
    final SubDeviceSpecPair pair = new SubDeviceSpecPair("Q", "Q");

    pair.db_name("A");
    assert pair.db_name().equals("A");

    pair.sd_name("0");
    assert pair.sd_name().equals("0");
  }

}
