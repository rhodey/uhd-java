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
package org.anhonesteffort.uhd.usrp;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Index;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.StdString;

@Platform(
  link    = "uhd",
  include = {"<uhd/usrp/subdev_spec.hpp>"}
)
@Name("uhd::usrp::subdev_spec_t")
public class SubDeviceSpec extends Pointer {

  static { Loader.load(); }

  public SubDeviceSpec() { allocate(); }
  public SubDeviceSpec(String markup) { allocate(markup); }

  private native void allocate();
  private native void allocate(@ByRef @StdString String markup);

  public native long size();
  public native void resize(@Cast("size_t") long n);

  @Index public native @ByRef SubDeviceSpecPair get(@Cast("size_t") long i);
  public native SubDeviceSpec put(@Cast("size_t") long i, SubDeviceSpecPair value);

  public native @StdString String to_pp_string();
  public native @StdString String to_string();

}
