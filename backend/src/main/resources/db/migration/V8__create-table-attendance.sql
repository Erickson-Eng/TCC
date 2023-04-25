create table attendance (
    booking_id INTEGER NOT NULL,
    athlete_id INTEGER NOT NULL,
    attendance_confirmation VARCHAR(30) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    modified_date TIMESTAMP,
    created_by VARCHAR(255),
    modified_by VARCHAR(255),

    PRIMARY KEY (booking_id, athlete_id),
    CONSTRAINT fk_attendance_booking
                        FOREIGN KEY (booking_id)
                        REFERENCES public.booking (id),
    CONSTRAINT fk_attendance_athlete
                        FOREIGN KEY (athlete_id)
                        REFERENCES public.athlete (id)
)