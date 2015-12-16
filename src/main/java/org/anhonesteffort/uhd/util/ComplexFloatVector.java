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

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Index;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Platform;

import java.nio.FloatBuffer;

@Platform(
  include = {"<complex>", "<vector>"}
)
@Name("std::vector<std::complex<float> >")
public class ComplexFloatVector extends Pointer {

  static { Loader.load(); }

  public ComplexFloatVector() {
    allocate();
  }
  public ComplexFloatVector(long size) {
    allocate(size);
  }
  public ComplexFloatVector(Pointer ptr) {
    super(ptr);
  }
  public ComplexFloatVector(ComplexFloat... array) {
    this(array.length);
    put(array);
  }

  private native void allocate();
  private native void allocate(@Cast("size_t") long n);

  public native @Name("operator=") @ByRef ComplexFloatVector put(@ByRef ComplexFloatVector x);

  public native long size();
  public native void resize(@Cast("size_t") long n);
  public native @ByRef Pointer front();
  public native @ByRef Pointer back();

  @Index public native @ByRef ComplexFloat get(@Cast("size_t") long i);
  public native ComplexFloatVector put(@Cast("size_t") long i, ComplexFloat value);

  public ComplexFloatVector put(ComplexFloat... array) {
    if (size() != array.length) {
      resize(array.length);
    }
    for (int i = 0; i < array.length; i++) {
      put(i, array[i]);
    }
    return this;
  }

  public FloatBuffer toFloatBuffer() {
    FloatPointer floatPointer = new FloatPointer(front());
    floatPointer.limit((int) (size() * 2));
    return floatPointer.asBuffer();
  }

}
