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

import org.anhonesteffort.uhd.types.TxMetadata;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.*;

@Platform(
  link    = "uhd",
  include = {"<uhd/stream.hpp>"}
)
@Name("uhd::tx_streamer")
public class TxStreamer extends Pointer {

  static { Loader.load(); }

  private TxStreamerSharedPtr ptr;
  public void saveReference(TxStreamerSharedPtr ptr) {
    this.ptr = ptr;
  }

  public native @Cast("size_t") long get_num_channels();
  public native @Cast("size_t") long get_max_num_samps();

  public native @Cast("size_t") long send(@Cast("uhd::tx_streamer::buffs_type") Pointer buffs,
                                          @Cast("size_t") long nsamps_per_buff,
                                          @ByVal TxMetadata metadata,
                                          @Const double timeout);

}
