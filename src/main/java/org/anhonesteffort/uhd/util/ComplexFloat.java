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

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(
    include = {"<complex>"}
)
@Name("std::complex<float>")
public class ComplexFloat extends Pointer {

  static { Loader.load(); }

  public ComplexFloat(@Const Pointer p) { super(p); }
  public ComplexFloat(float real, float imaginary) { allocate(real, imaginary); }

  private native void allocate(float real, float imaginary);

  public native @Name("operator=") @ByRef ComplexFloat put(@Const @ByRef ComplexFloat x);

  public native float real();
  public native float imag();

}