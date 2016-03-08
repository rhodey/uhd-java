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

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Index;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(
    link    = "uhd",
    include = {"<uhd/types/ranges.hpp>"}
)
@Name("uhd::meta_range_t")
public class MetaRange extends Pointer {

  static { Loader.load(); }

  public MetaRange() { allocate(); }
  public MetaRange(double start, double stop, double step) { allocate(start, stop, step); }

  public MetaRange(Pointer ptr) {
    super(ptr);
  }
  public MetaRange(Range... array) {
    this();
    resize(array.length);
    put(array);
  }

  private native void allocate();
  private native void allocate(double start, double stop, double step);

  public native long size();
  public native void resize(@Cast("size_t") long n);
  public native @ByRef Pointer front();
  public native @ByRef Pointer back();

  @Index public native @ByRef Range get(@Cast("size_t") long i);
  public native MetaRange put(@Cast("size_t") long i, Range value);

  public MetaRange put(Range... array) {
    if (size() != array.length) {
      resize(array.length);
    }
    for (int i = 0; i < array.length; i++) {
      put(i, array[i]);
    }
    return this;
  }

  public native double start();
  public native double stop();
  public native double step();

}
