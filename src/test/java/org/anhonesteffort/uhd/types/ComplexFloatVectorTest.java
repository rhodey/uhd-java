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

import org.anhonesteffort.uhd.util.ComplexFloat;
import org.anhonesteffort.uhd.util.ComplexFloatVector;
import org.junit.Test;

public class ComplexFloatVectorTest {

  @Test
  public void testSize() {
    assert new ComplexFloatVector(4).size() == 4;
    assert new ComplexFloatVector(
        new ComplexFloat(0f, 0f),
        new ComplexFloat(0f, 0f)
    ).size() == 2;
  }

  @Test
  public void testResize() {
    final ComplexFloatVector FLOATS = new ComplexFloatVector(1);
    FLOATS.resize(2);
    assert FLOATS.size() == 2;
  }

  @Test
  public void testArrayConstructor() {
    final ComplexFloat       FLOAT0 = new ComplexFloat(4.0f, 0.1f);
    final ComplexFloat       FLOAT1 = new ComplexFloat(4.0f, 0.1f);
    final ComplexFloat       FLOAT2 = new ComplexFloat(4.0f, 0.1f);
    final ComplexFloat       FLOAT3 = new ComplexFloat(4.0f, 0.1f);
    final ComplexFloatVector FLOATS = new ComplexFloatVector(
        FLOAT0, FLOAT1, FLOAT2, FLOAT3
    );

    assert FLOATS.get(0).real() == FLOAT0.real();
    assert FLOATS.get(0).imag() == FLOAT0.imag();

    assert FLOATS.get(1).real() == FLOAT1.real();
    assert FLOATS.get(1).imag() == FLOAT1.imag();

    assert FLOATS.get(2).real() == FLOAT2.real();
    assert FLOATS.get(2).imag() == FLOAT2.imag();

    assert FLOATS.get(3).real() == FLOAT3.real();
    assert FLOATS.get(3).imag() == FLOAT3.imag();
  }

  @Test
  public void testPutGet() {
    final ComplexFloat       FLOAT0 = new ComplexFloat(4.0f, 0.1f);
    final ComplexFloat       FLOAT1 = new ComplexFloat(4.0f, 0.1f);
    final ComplexFloat       FLOAT2 = new ComplexFloat(4.0f, 0.1f);
    final ComplexFloat       FLOAT3 = new ComplexFloat(4.0f, 0.1f);
    final ComplexFloatVector FLOATS = new ComplexFloatVector(4);

    FLOATS.put(0, FLOAT0);
    FLOATS.put(1, FLOAT1);
    FLOATS.put(2, FLOAT2);
    FLOATS.put(3, FLOAT3);

    assert FLOATS.get(0).real() == FLOAT0.real();
    assert FLOATS.get(0).imag() == FLOAT0.imag();

    assert FLOATS.get(1).real() == FLOAT1.real();
    assert FLOATS.get(1).imag() == FLOAT1.imag();

    assert FLOATS.get(2).real() == FLOAT2.real();
    assert FLOATS.get(2).imag() == FLOAT2.imag();

    assert FLOATS.get(3).real() == FLOAT3.real();
    assert FLOATS.get(3).imag() == FLOAT3.imag();
  }

}
