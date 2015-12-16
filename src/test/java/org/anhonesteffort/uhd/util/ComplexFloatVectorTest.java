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
package org.anhonesteffort.uhd.util;

import org.junit.Test;

import java.nio.FloatBuffer;

public class ComplexFloatVectorTest {

  @Test
  public void testSizeConstructor() {
    assert new ComplexFloatVector(40).size() == 40;
  }

  @Test
  public void testArrayConstructor() {
    final ComplexFloatVector VECTOR = new ComplexFloatVector(
        new ComplexFloat(0, 0),
        new ComplexFloat(1, 1),
        new ComplexFloat(2, 2)
    );

    assert VECTOR.size() == 3;

    assert VECTOR.get(0).real() == 0;
    assert VECTOR.get(0).imag() == 0;

    assert VECTOR.get(1).real() == 1;
    assert VECTOR.get(1).imag() == 1;

    assert VECTOR.get(2).real() == 2;
    assert VECTOR.get(2).imag() == 2;

    assert new ComplexFloat(VECTOR.front()).real() == 0;
    assert new ComplexFloat(VECTOR.front()).imag() == 0;

    assert new ComplexFloat(VECTOR.back()).real() == 2;
    assert new ComplexFloat(VECTOR.back()).imag() == 2;
  }

  @Test
  public void testPutGet() {
    final ComplexFloatVector VECTOR = new ComplexFloatVector(3);

    VECTOR.put(0, new ComplexFloat(0, 0));
    VECTOR.put(1, new ComplexFloat(1, 1));
    VECTOR.put(2, new ComplexFloat(2, 2));

    assert VECTOR.size() == 3;

    assert VECTOR.get(0).real() == 0;
    assert VECTOR.get(0).imag() == 0;

    assert VECTOR.get(1).real() == 1;
    assert VECTOR.get(1).imag() == 1;

    assert VECTOR.get(2).real() == 2;
    assert VECTOR.get(2).imag() == 2;

    assert new ComplexFloat(VECTOR.front()).real() == 0;
    assert new ComplexFloat(VECTOR.front()).imag() == 0;

    assert new ComplexFloat(VECTOR.back()).real() == 2;
    assert new ComplexFloat(VECTOR.back()).imag() == 2;

    VECTOR.put(new ComplexFloat(3, 3), new ComplexFloat(4, 4), new ComplexFloat(5, 5));

    assert VECTOR.size() == 3;

    assert VECTOR.get(0).real() == 3;
    assert VECTOR.get(0).imag() == 3;

    assert VECTOR.get(1).real() == 4;
    assert VECTOR.get(1).imag() == 4;

    assert VECTOR.get(2).real() == 5;
    assert VECTOR.get(2).imag() == 5;

    assert new ComplexFloat(VECTOR.front()).real() == 3;
    assert new ComplexFloat(VECTOR.front()).imag() == 3;

    assert new ComplexFloat(VECTOR.back()).real() == 5;
    assert new ComplexFloat(VECTOR.back()).imag() == 5;

    VECTOR.put(new ComplexFloatVector(
            new ComplexFloat(6, 6), new ComplexFloat(7, 7), new ComplexFloat(8, 8)
    ));

    assert VECTOR.size() == 3;

    assert VECTOR.get(0).real() == 6;
    assert VECTOR.get(0).imag() == 6;

    assert VECTOR.get(1).real() == 7;
    assert VECTOR.get(1).imag() == 7;

    assert VECTOR.get(2).real() == 8;
    assert VECTOR.get(2).imag() == 8;

    assert new ComplexFloat(VECTOR.front()).real() == 6;
    assert new ComplexFloat(VECTOR.front()).imag() == 6;

    assert new ComplexFloat(VECTOR.back()).real() == 8;
    assert new ComplexFloat(VECTOR.back()).imag() == 8;
  }

  @Test
  public void testToFloatArray() {
    final ComplexFloatVector VECTOR = new ComplexFloatVector(
        new ComplexFloat(0, 1),
        new ComplexFloat(2, 3),
        new ComplexFloat(4, 5)
    );

    final FloatBuffer FLOATS = VECTOR.toFloatBuffer();

    assert FLOATS.limit()    == 6;
    assert FLOATS.capacity() == 6;

    assert FLOATS.get(0) == 0;
    assert FLOATS.get(1) == 1;
    assert FLOATS.get(2) == 2;
    assert FLOATS.get(3) == 3;
    assert FLOATS.get(4) == 4;
    assert FLOATS.get(5) == 5;
  }

}
