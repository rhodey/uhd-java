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
package org.anhonesteffort.uhd.types;

import org.junit.Test;

public class RangeTest {

  @Test
  public void testConstructor() {
    final double VALUE = 1000;
    final Range RANGE = new Range(VALUE);
    assert RANGE.start() == VALUE;
  }

  @Test
  public void testConstructWithStartStopStep() {
    final double START = 1000d;
    final double STOP  = 10000d;
    final double STEP  = 1000d;

    final Range RANGE = new Range(START, STOP, STEP);

    assert RANGE.start() == START;
    assert RANGE.stop()  == STOP;
    assert RANGE.step()  == STEP;
  }

}
