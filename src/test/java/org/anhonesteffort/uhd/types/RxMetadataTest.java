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

public class RxMetadataTest {

  @Test
  public void test() {
    final RxMetadata RX_META = new RxMetadata();

    RX_META.has_time_spec(true);
    assert RX_META.has_time_spec();

    RX_META.time_spec(new TimeSpec(4.0));
    assert Math.abs(RX_META.time_spec().get_real_secs() - 4.0) < 0.00001;

    RX_META.more_fragments(true);
    assert RX_META.more_fragments();

    RX_META.fragment_offset(400);
    assert RX_META.fragment_offset() == 400;

    RX_META.start_of_burst(true);
    assert RX_META.start_of_burst();

    RX_META.end_of_burst(false);
    assert !RX_META.end_of_burst();

    RX_META.error_code(RxMetadata.ERROR_BAD_PACKET);
    assert RX_META.error_code() == RxMetadata.ERROR_BAD_PACKET;
  }

}
