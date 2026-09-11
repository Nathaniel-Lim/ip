# Melodie User Guide

Melodie is a task chatbot that manages todos, deadlines, and events through
typed commands.

## Date and time formats

Deadlines and events accept the original numeric format as well as natural
date words. A time in 24-hour `HHmm` format is always required.

| Input | Meaning |
| --- | --- |
| `2/12/2026 1800` | 2 December 2026 at 6:00 PM |
| `today 1800` | Today at 6:00 PM |
| `tomorrow 0900` | Tomorrow at 9:00 AM |
| `Mon 1400` | The next Monday at 2:00 PM |
| `Monday 1400` | The next Monday at 2:00 PM |

Natural date words and weekday names are case-insensitive. A weekday always
means the next occurrence strictly after today. For example, `Mon` entered on
a Monday refers to the Monday of the following week.

## Adding deadlines

Use `deadline DESCRIPTION /by DATE_TIME`.

Examples:

```text
deadline return book /by 2/12/2026 1800
deadline submit report /by tomorrow 2359
deadline attend consultation /by Mon 1400
```

## Adding events

Use `event DESCRIPTION /from DATE_TIME /to DATE_TIME`.

Examples:

```text
event project meeting /from today 1400 /to today 1600
event workshop /from Sat 0900 /to Sat 1200
```

An event's end date and time cannot be earlier than its start date and time.
