Beluga Foundry is a backend project for a virtual pet site.

When I was younger, I wanted nothing more than to make one of these, but never had the technical chops.

Now I figure . . . why not?

Originally I started this project for the purpose of making something full-fledged even though I had no
intention of ever launching the site. As time has passed, I realize I would rather make up a very basic set
of APIs and then play with JavaScript, at which I am attempting to gain some degree of mastery. Not only that,
but since this is a side project, I have 3 - 4 extra hours a week to work on it in between the other things I'm
working at. For something that will never see the light of day, that means a long, long, LONG committment that doesn't
fit in the bigger picture.

Which explains some of the features that a reader might find baffling, such as not recording HP (requires
extra work which I did not want to do at the time of writing), no inventory for the user, etc. Also no images,
though that one was more of an oversight than anything else.

This project also became a micro-playground for my forays into increasing mastery in software architecture.

As such, while this project has been undertaken in all seriousness, there are still bound to be parts which
are a little rough or strange.

In the future I might add more to Beluga Foundry, but probably not. I may attempt the project again when I
start working on strengthening Python skills.

Beluga Foundry is 100% a Java project (most pet sites run off of PHP, it seems), using Spring for dependency injection,
Maven for builds/dependencies, Hibernate for data access, MySQL for data store, and Shiro for authentication and authorization.
It runs without a web.xml, so no need to panic when you realize there isn't one to be found.

In order to deploy, build and drop the WAR into your container of choice. I prefer Tomcat.

You will need to support Java 8 in order to build and deploy Beluga Foundry.