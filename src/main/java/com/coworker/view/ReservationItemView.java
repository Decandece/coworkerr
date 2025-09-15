package com.coworker.view;

import java.time.OffsetDateTime;

public record ReservationItemView(Long reservationId , Long roomId ,
                                  OffsetDateTime startAT,
                                  OffsetDateTime endAT) {
}
