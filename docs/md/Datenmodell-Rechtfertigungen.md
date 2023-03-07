### MovieScreening_Movie containment:
- Für eine Filmaufführung macht es keinen Sinn den Film zu ändern.
- Der Film kann eindeutig über die Aufführung identifiziert werden.
- Jede Aufführung braucht einen Film.


### CinemaHall_MovieScreening containment:
- Screening kann nicht ohne Halle existieren.
- Es gibt nur eine Halle pro Screening.
- Die Halle kann eindeutig über eine Aufführung identifiziert werden.

### SeatRow_CinemaHall containment:
- SeatRow kann nicht ohne Halle existieren.
- Es gibt nur eine Halle pro SeatRow.
- Die Halle kann eindeutig über eine SeatRow identifiziert werden.

### SeatRow_PriceCategory kein containment:
- PriceCategory ist nur ein enum und soll keine weiteren Eigenschaften (wie SeatRow beinhalten).
- PriceCategory einer SeatRow sollte jederzeit geändert werden können.

### Seat_SeatRow containment:
- Eine SeatRow hat mehrere Seats.
- Ein Seat kann nicht ohne SeatRow existieren.
- Ein Seat identifiziert eine SeatRow eindeutig.

### BookingState_MovieScreening containment:
- Zu jedem BookingState existiert ein MovieScreening.
- Ein BookingState kann nicht ohne ein MovieScreening existieren.

### BookingState_Seat:
- Ein BookingState kann nicht ohne Seat existieren.
- Ein BookingState identifiziert einen Seat eindeutig.

### Customer_BookingState:
- Ein BookingState kann nicht ohne Customer existieren.
- Ein BookingState identifiziert einen Customer eindeutig.
- Ein Customer hat eine Liste von BookingState's.