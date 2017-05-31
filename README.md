Bromate Android
=====================

An application under development to manage bills sharing.
I'm using this project for study, experimenting new technologies and new patterns.

Technologies
=====================
The architecture that I chose is MVVM, which Google's Architecture guides started to recommend since **Google IO 17**, but I'm not using the official Google's architecture library.
_(I'm experimenting a code without fragments)_

I tried to use very recent technologies from the Android community, and others that I've never used like `Moshi` and `DataBinding`.

Known issues
=====================
- Save Instance State is not yet implemented in this architecture
- For some weird reason my customs `@BindingAdapter` are throwing **ResourceNotFound** in my instrumentation tests
- Lack of persistence
- Lack of a webserver
