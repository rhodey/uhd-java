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
package org.anhonesteffort.uhd.usrp;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.StdString;

@Platform(
  link    = "uhd",
  include = {"<uhd/usrp/subdev_spec.hpp>"}
)
@Name("uhd::usrp::subdev_spec_pair_t")
public class SubDeviceSpecPair extends Pointer {

  static { Loader.load(); }

  public SubDeviceSpecPair(String dBoardName,
                           String subDevName) { allocate(dBoardName, subDevName); }
  private native void allocate(@ByRef @StdString String dBoardName,
                               @ByRef @StdString String subDevName);

  public native void db_name(@StdString String dBoardName);
  public native @StdString String db_name();

  public native void sd_name(@StdString String subDevName);
  public native @StdString String sd_name();

}
