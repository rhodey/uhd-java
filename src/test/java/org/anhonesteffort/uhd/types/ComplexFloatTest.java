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
import org.junit.Test;

public class ComplexFloatTest {

  @Test
  public void testConstructor() {
    final float REAL      = 4.0f;
    final float IMAGINARY = 0.1f;

    final ComplexFloat FLOAT = new ComplexFloat(REAL, IMAGINARY);

    assert FLOAT.real() == REAL;
    assert FLOAT.imag() == IMAGINARY;
  }

  @Test
  public void testPut() {
    final float REAL      = 4.0f;
    final float IMAGINARY = 0.1f;

    final ComplexFloat FLOAT = new ComplexFloat(0f, 0f).put(new ComplexFloat(REAL, IMAGINARY));

    assert FLOAT.real() == REAL;
    assert FLOAT.imag() == IMAGINARY;
  }

}
