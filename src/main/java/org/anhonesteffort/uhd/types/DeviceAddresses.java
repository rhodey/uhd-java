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

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Index;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(
  link    = "uhd",
  include = {"<uhd/types/device_addr.hpp>"}
)
@Name("uhd::device_addrs_t")
public class DeviceAddresses extends Pointer {

  static { Loader.load(); }

  public DeviceAddresses(Pointer p) { super(p); }
  public DeviceAddresses() { allocate(); }
  public DeviceAddresses(long n) { allocate(n); }
  public DeviceAddresses(DeviceAddress... array) {
    this(array.length);
    put(array);
  }

  private native void allocate();
  private native void allocate(@Cast("size_t") long n);

  public native long size();
  public native void resize(@Cast("size_t") long n);

  @Index public native @ByVal DeviceAddress get(@Cast("size_t") long i);
  public native DeviceAddresses put(@Cast("size_t") long i, DeviceAddress value);

  public DeviceAddresses put(DeviceAddress ... array) {
    if (size() != array.length) {
      resize(array.length);
    }
    for (int i = 0; i < array.length; i++) {
      put(i, array[i]);
    }
    return this;
  }

}