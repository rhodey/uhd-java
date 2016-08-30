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
package org.anhonesteffort.uhd;

import org.anhonesteffort.uhd.types.RxMetadata;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(
  link    = "uhd",
  include = {"<uhd/stream.hpp>"}
)
@Name("uhd::rx_streamer")
public class RxStreamer extends Pointer {

  static { Loader.load(); }

  private RxStreamerSharedPtr ptr;
  public void saveReference(RxStreamerSharedPtr ptr) {
    this.ptr = ptr;
  }

  public native @Cast("size_t") long get_num_channels();
  public native @Cast("size_t") long get_max_num_samps();

  public native @Cast("size_t") long recv(@Cast("uhd::rx_streamer::buffs_type") Pointer buffs,
                                          @Cast("size_t") long samples_per_buff,
                                          @ByVal RxMetadata metadata,
                                          @Const double timeout,
                                          @Cast("bool") boolean onePacket);

}
