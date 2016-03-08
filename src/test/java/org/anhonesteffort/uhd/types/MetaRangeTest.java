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
package org.anhonesteffort.uhd.types;

import org.junit.Test;

public class MetaRangeTest {

  @Test
  public void testConstructorWithArray() {
    final double VALUE1 = 1000;
    final double VALUE2 = 1000;
    final Range  RANGE1 = new Range(VALUE1);
    final Range  RANGE2 = new Range(VALUE2);

    final MetaRange META_RANGE = new MetaRange(RANGE1, RANGE2);

    assert META_RANGE.size() == 2;
    assert META_RANGE.get(0).start() == VALUE1;
    assert META_RANGE.get(1).start() == VALUE2;
  }

  @Test
  public void testConstructWithStartStopStep() {
    final double START = 1000d;
    final double STOP  = 10000d;
    final double STEP  = 1000d;

    final MetaRange META_RANGE = new MetaRange(START, STOP, STEP);

    assert META_RANGE.size()  == 1;
    assert META_RANGE.start() == START;
    assert META_RANGE.stop()  == STOP;
    assert META_RANGE.step()  == STEP;
  }

}
