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
@Name("uhd::rx_metadata_t")
public class RxMetadata extends Pointer {

  public static final int
    ERROR_NONE          = 0x00,
    ERROR_TIMEOUT       = 0x01,
    ERROR_LATE_COMMANDS = 0x02,
    ERROR_BROKEN_CHAIN  = 0x04,
    ERROR_OVERFLOW      = 0x08,
    ERROR_ALIGNMENT     = 0x0C,
    ERROR_BAD_PACKET    = 0x0F;

  static { Loader.load(); }
  public RxMetadata() { allocate(); }
  private native void allocate();

  public native void has_time_spec(@Cast("bool") boolean hasTimeSpec);
  public native @Cast("bool") boolean has_time_spec();

  public native void time_spec(TimeSpec timeSpec);
  public native @ByVal TimeSpec time_spec();

  public native void more_fragments(@Cast("bool") boolean moreFragments);
  public native @Cast("bool") boolean more_fragments();

  public native void fragment_offset(@Cast("size_t") long offset);
  public native @Cast("size_t") long fragment_offset();

  public native void start_of_burst(@Cast("bool") boolean startOfBurst);
  public native @Cast("bool") boolean start_of_burst();

  public native void end_of_burst(@Cast("bool") boolean endOfBurst);
  public native @Cast("bool") boolean end_of_burst();

  public native void error_code(@Cast("uhd::rx_metadata_t::error_code_t") int errorCode);
  public native @Cast("uhd::rx_metadata_t::error_code_t") int error_code();

}
