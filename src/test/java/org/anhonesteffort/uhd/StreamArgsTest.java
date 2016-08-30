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

import org.junit.Test;

public class StreamArgsTest {

  @Test
  public void test() {
    final String     CPU_FORMAT0  = "fc32";
    final String     CPU_FORMAT1  = "fc64";
    final String     OTW_FORMAT0  = "sc8";
    final String     OTW_FORMAT1  = "sc16";
    final StreamArgs STREAM_ARGS = new StreamArgs(CPU_FORMAT0, OTW_FORMAT0);

    assert STREAM_ARGS.cpu_format().equals(CPU_FORMAT0);
    assert STREAM_ARGS.otw_format().equals(OTW_FORMAT0);

    STREAM_ARGS.cpu_format(CPU_FORMAT1);
    STREAM_ARGS.otw_format(OTW_FORMAT1);

    assert STREAM_ARGS.cpu_format().equals(CPU_FORMAT1);
    assert STREAM_ARGS.otw_format().equals(OTW_FORMAT1);
  }

}
