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
package org.anhonesteffort.uhd;

import org.anhonesteffort.uhd.types.DeviceAddress;
import org.anhonesteffort.uhd.types.DeviceAddresses;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(
  link    = "uhd",
  include = {"<uhd/device.hpp>"}
)
@Name("uhd::device")
public class Device extends Pointer {

  static { Loader.load(); }

  private DeviceSharedPtr ptr;
  public void saveReference(DeviceSharedPtr ptr) {
    this.ptr = ptr;
  }

  public native static @ByVal DeviceAddresses find(@ByRef DeviceAddress hint);
  private native static @ByVal DeviceSharedPtr make(@ByRef DeviceAddress hint, @Cast("size_t") long which);
  public static Device build(DeviceAddress hint, long which) {
    DeviceSharedPtr ptr    = make(hint, which);
    Device          device = ptr.get();

    device.saveReference(ptr);
    return device;
  }

  public native @ByVal RxStreamerSharedPtr get_rx_stream(@ByRef StreamArgs args);

}
