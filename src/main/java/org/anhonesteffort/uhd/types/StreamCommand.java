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
  include = {"<uhd/types/stream_cmd.hpp>"}
)
@Name("uhd::stream_cmd_t")
public class StreamCommand extends Pointer {

  public static final int
    START_CONTINUOUS   = 'a',
    STOP_CONTINUOUS    = 'o',
    NUM_SAMPS_AND_DONE = 'd',
    NUM_SAMPS_AND_MORE = 'm';

  static { Loader.load(); }
  public StreamCommand(@Cast("uhd::stream_cmd_t::stream_mode_t") int mode) {
    allocate(mode);
  }
  private native void allocate(@Cast("uhd::stream_cmd_t::stream_mode_t") int mode);

  public native void stream_mode(@Cast("uhd::stream_cmd_t::stream_mode_t") int mode);
  public native @Cast("uhd::stream_cmd_t::stream_mode_t") int stream_mode();

  public native void num_samps(@Cast("size_t") long numSamples);
  public native @Cast("size_t") long num_samps();

  public native void stream_now(@Cast("bool") boolean streamNow);
  public native @Cast("bool") boolean stream_now();

  public native @ByVal TimeSpec time_spec();
  public native void time_spec(TimeSpec timeSpec);

}
