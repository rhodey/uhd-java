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
package org.anhonesteffort.uhd.types;

import org.junit.Test;

public class StreamCommandTest {

  @Test
  public void testConstructors() {
    StreamCommand STREAM_CMD = new StreamCommand(StreamCommand.NUM_SAMPS_AND_DONE);

    STREAM_CMD.num_samps(4000);
    STREAM_CMD.stream_now(true);
    STREAM_CMD.time_spec(new TimeSpec(0));

    assert STREAM_CMD.stream_mode() == StreamCommand.NUM_SAMPS_AND_DONE;
    assert STREAM_CMD.num_samps() == 4000;
    assert STREAM_CMD.stream_now();
    assert STREAM_CMD.time_spec() != null;

    STREAM_CMD.stream_mode(StreamCommand.NUM_SAMPS_AND_MORE);
    assert STREAM_CMD.stream_mode() == StreamCommand.NUM_SAMPS_AND_MORE;
  }

}
