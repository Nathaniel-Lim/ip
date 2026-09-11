# Manual Test Plan

## C-NaturalDates

Before testing, note the computer's current local date. Start Melodie with an
empty task list.

| ID | Command | Expected result |
| --- | --- | --- |
| ND-1 | `deadline numeric /by 2/12/2026 1800` | Adds a deadline for 2 December 2026 at 6:00 PM. |
| ND-2 | `deadline same day /by today 1800` | Adds a deadline for today at 6:00 PM. |
| ND-3 | `deadline next day /by tomorrow 0900` | Adds a deadline for tomorrow at 9:00 AM. |
| ND-4 | `deadline weekday /by Mon 1400` | Adds a deadline for the first Monday strictly after today at 2:00 PM. |
| ND-5 | `deadline full weekday /by mOnDaY 1400` | Produces the same date as ND-4, showing that names are case-insensitive. |
| ND-6 | `event overnight /from today 2300 /to tomorrow 0100` | Adds an event from today at 11:00 PM to tomorrow at 1:00 AM. |
| ND-7 | `deadline invalid /by someday 1800` | Rejects the command and displays the supported date/time formats. |
| ND-8 | `deadline invalid time /by tomorrow 2500` | Rejects the command and displays the supported date/time formats. |

After restarting Melodie, run `list` and confirm that the dates resolved in
ND-1 to ND-6 remain the same calendar dates rather than being recalculated.
