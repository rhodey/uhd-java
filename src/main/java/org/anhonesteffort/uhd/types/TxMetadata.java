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
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(
  link    = "uhd",
  include = {"<uhd/types/metadata.hpp>"}
)
@Name("uhd::tx_metadata_t")
public class TxMetadata extends Pointer {

  static { Loader.load(); }
  public TxMetadata() { allocate(); }
  private native void allocate();

  public native void has_time_spec(@Cast("bool") boolean hasTimeSpec);
  public native @Cast("bool") boolean has_time_spec();

  public native void time_spec(TimeSpec timeSpec);
  public native @ByVal TimeSpec time_spec();

  public native void start_of_burst(@Cast("bool") boolean startOfBurst);
  public native @Cast("bool") boolean start_of_burst();

  public native void end_of_burst(@Cast("bool") boolean endOfBurst);
  public native @Cast("bool") boolean end_of_burst();

}
